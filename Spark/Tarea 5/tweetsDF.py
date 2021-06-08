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
    
    
    data_frame = spark_session\
            .read\
            .format("csv") \
            .options(header='false', inferschema='true', sep = '\t')\
            .load("tweets.tsv")
            
    
    data_frame = data_frame.toDF("id", "user", "tweet", "date", "lat", "long", "location")
    
    
    #User with more tweets
    data_frame\
        .groupBy("user")\
        .count()\
        .sort("count", ascending = False)\
        .show(1)
    
    #Shortest tweet
    data_frame.createOrReplaceTempView("tweets")
    spark_session.sql("SELECT user, date, length(tweet) FROM tweets ORDER BY length(tweet) ASC").show(1)
    
    #Have tried to do like the teacher on Java, but this is the only way I could do it ): 
    #Column with all words isolated
    data_frame_with_word= data_frame.withColumn('word', f.explode(f.split(f.col("tweet"), ' ')))
    data_frame_with_word.createOrReplaceTempView("repeatWord")
    
    #Select words we dont want to have repeated
    stop_words = set(stopwords.words('english'))
    new_stop_words = ["RT", "http","-"]
    stop_words = stop_words.union(new_stop_words)
    
    #Create a dataframe
    df_stop = pd.DataFrame(stop_words, columns=["stop_words"])
    df_stop = spark_session.createDataFrame(df_stop)
    df_stop.createOrReplaceTempView("stopWord")
    
    #SQL consult that gives most common words
    spark_session.sql("SELECT word, count(word) From repeatWord r LEFT JOIN stopWord s on r.word == s.stop_words WHERE s.stop_words is NULL GROUP BY word ORDER BY count(word) desc" ).show(10)

if __name__ == '__main__':
    main()