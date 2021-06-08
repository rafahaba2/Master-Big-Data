from pyspark import SparkConf, SparkContext


def main(file_name: str) -> None:
    spark_conf = SparkConf()\
        .setAppName("Airports")\
        .setMaster("local[4]")

    spark_context = SparkContext(conf=spark_conf)

    logger = spark_context._jvm.org.apache.log4j
    logger.LogManager.getLogger("org").setLevel(logger.Level.WARN)

    lista = spark_context \
        .textFile("airports.csv") \
        .map(lambda line: line.split(",")) \
        .filter(lambda array: array[8].find("ES") > 0) \
        .map(lambda array: array[2]) \
        .map(lambda word: (word, 1)) \
        .reduceByKey(lambda a, b: a + b) \
        .sortBy(lambda pair: pair[1], ascending = False) \
        .take(10)

    file = open("AirportsSpain.txt", "w") 
    for (word, count) in lista:
        print("%s: %i"% (word,count))
        file.write("(%s, %i)\n"% (word,count));
    
    file.close()
    spark_context.stop()


if __name__ == "__main__":
    """
    Python program that uses Apache Spark to sum a list of numbers stored in files
    """


    main("data/numbers.txt")
