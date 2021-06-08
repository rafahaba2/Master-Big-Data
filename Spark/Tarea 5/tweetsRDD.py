# -*- coding: utf-8 -*-
"""
Created on Sat Mar 20 10:48:33 2021

@author: Usuario
"""


from pyspark import SparkConf, SparkContext
import numpy as np
from nltk.corpus import stopwords
import nltk


def main(file_name: str) -> None:
    spark_conf = SparkConf()\
        .setAppName("AddNumbers")\
        .setMaster("local[4]")

    spark_context = SparkContext(conf=spark_conf)

    logger = spark_context._jvm.org.apache.log4j
    logger.LogManager.getLogger("org").setLevel(logger.Level.WARN)    
    
    #Must download the stopwords from NLTK
    #nltk.download('stopwords')
    
    stop_words = set(stopwords.words('english'))
    
    #Using own stop words "RT" "http" "-"
    my_stop_words = set(["RT", "http", "-"])
    
    #First block splits when founds "\t"
    splitTab = spark_context \
        .textFile("tweets.tsv") \
        .map(lambda line: line.split("\t")) 
        
    #Block to find the firtst 10 trending words, filtering own and nltk stopwords
    TrendingWords = splitTab\
        .map(lambda array: (array[2])) \
        .flatMap(lambda array: array.split(" "))\
        .filter(lambda word: word not in stop_words)\
        .filter(lambda word: word not in my_stop_words)\
        .map(lambda word: (word, 1)) \
        .reduceByKey(lambda a, b: a + b)\
        .sortBy(lambda pair: pair[1], ascending = False) \
        .take(10)
    
    print("\n------Trending Words------")    
    for (word, count) in TrendingWords:
        print("(%s, %i)"% (word,count))
        
    #Block to find the user with more tweets
    mostTweetsUser = splitTab\
        .map(lambda array: array[1])\
        .map(lambda user: (user, 1))\
        .reduceByKey(lambda a, b: a + b)\
        .sortBy(lambda pair: pair[1], ascending = False)\
        .take(1)
    
    print("\n------User with More Tweets------")
    print(mostTweetsUser)    
    
    #Block to find the shortest tweet   
    shortestTweet = splitTab\
        .map(lambda array: array[1:4])\
        .map(lambda array: (array, len(array[1])))\
        .sortBy(lambda pair: pair[-1], ascending = True)\
        .take(1)
    
    print("\n------Shortest Tweet------")     
    print(shortestTweet)
    
    spark_context.stop()


if __name__ == "__main__":
    """
    Python program that uses Apache Spark to sum a list of numbers stored in files
    """
    main("data/numbers.txt")