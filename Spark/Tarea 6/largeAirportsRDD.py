# -*- coding: utf-8 -*-
"""
Created on Mon Mar 22 21:41:35 2021

@author: Usuario
"""

from pyspark import SparkConf, SparkContext
import numpy as np
from nltk.corpus import stopwords
import nltk

def main() -> None:
    spark_conf = SparkConf()\
        .setAppName("AddNumbers")\
        .setMaster("local[4]")

    spark_context = SparkContext(conf=spark_conf)

    logger = spark_context._jvm.org.apache.log4j
    logger.LogManager.getLogger("org").setLevel(logger.Level.WARN)
    
    
    split_airports = spark_context\
        .textFile("airports.csv")\
        .map(lambda sep: sep.split(","))
    
    
    airports_header = split_airports.first()
    
    rdd_airports = split_airports\
        .filter(lambda header: header != airports_header)\
        .map(lambda field: (field[2], field[8]))\
        .filter(lambda type_airport: type_airport[0].find("large_airport") > 0)\
        .map(lambda country: (country[1], 1))\
        .reduceByKey(lambda a, b: a + b)
    
    split_country = spark_context\
        .textFile("countries.csv")\
        .map(lambda sep: sep.split(","))
    
    country_header = split_country.first()

    rdd_country = split_country\
        .filter(lambda header: header != country_header)\
        .map(lambda country: (country[1], country[2]))
    
    join = rdd_airports.join(rdd_country)
    
    res = join\
    .map(lambda country: (country[1]))\
    .sortBy(lambda pair: pair[0], ascending = False)\
    .take(10)
    
    print()
    for country in res: 
        print(country)
    
    
if __name__ == '__main__':
    main()