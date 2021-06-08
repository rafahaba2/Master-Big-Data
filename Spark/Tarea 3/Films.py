# -*- coding: utf-8 -*-
"""
Created on Tue Mar 16 17:31:29 2021

@author: Usuario
"""

from pyspark import SparkConf, SparkContext


def main(file_name: str) -> None:
    spark_conf = SparkConf()\
        .setAppName("AddNumbers")\
        .setMaster("local[4]")

    spark_context = SparkContext(conf=spark_conf)

    logger = spark_context._jvm.org.apache.log4j
    logger.LogManager.getLogger("org").setLevel(logger.Level.WARN)

    reversePairs = spark_context \
        .textFile("Film_Locations_in_San_Francisco.csv") \
        .map(lambda line: line.split(",")) \
        .map(lambda array: array[0]) \
        .map(lambda word: (word, 1)) \
        .reduceByKey(lambda a, b: a + b)
        
    resultFilter = reversePairs \
        .sortBy(lambda pair: pair[1], ascending = False) \
        .filter(lambda y: y[1] > 20) \
        .collect()
        
    

    resultFilter2 =  reversePairs \
        .sortBy(lambda pair: pair[1], ascending = False) \
        .collect()
    
    total_films = 0
    films_locations = 0
    for (word, count) in resultFilter2:
        total_films = total_films +1
        films_locations = films_locations + count
    
    file = open("FilmsPython.txt", "w")    
    for (word, count) in resultFilter:
        print("(%s, %i)"% (word,count))
        file.write("(%s, %i)\n"% (word,count));
    
    mean_film_locations = films_locations/total_films    

    print("Total number of films: " + str(total_films));
    file.write("\nTotal number of films: " + str(total_films) +"\n")
    print("The average of films locations per film: " + str(mean_film_locations));
    file.write("The average of films locations per film: " + str(mean_film_locations) + "\n")
    file.close()
    spark_context.stop()


if __name__ == "__main__":
    """
    Python program that uses Apache Spark to sum a list of numbers stored in files
    """
    main("data/numbers.txt")