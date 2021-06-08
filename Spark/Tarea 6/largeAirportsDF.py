# -*- coding: utf-8 -*-
"""
Created on Mon Mar 22 14:54:04 2021

@author: Usuario
"""


# -*- coding: utf-8 -*-
"""
Created on Sat Mar 20 10:48:33 2021

@author: Usuario
"""


from pyspark.sql import SparkSession
import pandas as pd
import pyspark.sql.functions as f
from pyspark.sql.functions import col
import pandas as pd
from nltk.corpus import stopwords
import nltk


def main() -> None:
   spark_session = SparkSession \
            .builder \
            .getOrCreate()
    
   logger = spark_session._jvm.org.apache.log4j
   logger.LogManager.getLogger("org").setLevel(logger.Level.WARN)      
    
   df_airports = spark_session\
            .read\
            .format("csv") \
            .options(header='true', inferschema='true')\
            .load("airports.csv")
    
   df_countries = spark_session\
            .read\
            .format("csv") \
            .options(header='true', inferschema='true')\
            .load("countries.csv")
            
   df_airports.createOrReplaceTempView("airports")
   df_countries.createOrReplaceTempView("countries")
    
    
   spark_session.sql("SELECT c.name, count(c.name) FROM countries c INNER JOIN airports a ON c.code == a.iso_country WHERE a.type == 'large_airport' GROUP BY c.name ORDER BY count(c.name) DESC" ).show(10)
    
   spark_session.stop()


if __name__ == "__main__":
    """
    Python program that uses Apache Spark to sum a list of numbers stored in files
    """
    main()