import sys
from pyspark.sql import SparkSession
from pyspark.sql import functions
from pyspark.sql.types import StructType, StructField, StringType, DoubleType, TimestampType, IntegerType
from pyspark.sql.functions import window, current_timestamp


def main(directory) -> None:
    """ Program that reads temperatures in streaming from a directory, computing the average temperature
    using time windows.

    It is assumed that an external entity is writing files in that directory, and every file contains a
    temperature value, the name of the sensor and a timestamp

    :param directory: streaming directory
    """
    spark = SparkSession \
        .builder \
        .master("local[4]") \
        .appName("ParkingStreaming_4") \
        .getOrCreate()

    fields = [StructField("poiID", IntegerType(), True),
              StructField("nombre", StringType(), True),
              StructField("direccion", StringType(), True),
              StructField("telefono",  IntegerType(), True),
              StructField("correoelectronico", StringType(), True),
              StructField("latitude", DoubleType(), True),
              StructField("longitude", DoubleType(), True),
              StructField("altitud", DoubleType(), True),
              StructField("capacidad", IntegerType(), True),
              StructField("capacidad_discapacitados", IntegerType(), True),
              StructField("fechahora_ultima_actualizacion", TimestampType(), True),
              StructField("libres", DoubleType(), True),
              StructField("libres_discapacitados", DoubleType(), True),
              StructField("nivelocupacion_naranja", IntegerType(), True),
              StructField("nivelocupacion_rojo", IntegerType(), True),
              StructField("smassa_sector_sare", IntegerType(), True)
              ]

    # Create DataFrame representing the stream of input lines from connection to localhost:9999
    lines = spark \
        .readStream \
        .format("csv") \
        .schema(StructType(fields)) \
        .options(header='false') \
        .option('includeTimestamp', 'true') \
        .load(directory)

    lines.printSchema()

    windows_size = 60 * 5
    slide_size = 60*5

    window_duration = '{} seconds'.format(windows_size)
    slide_duration = '{} seconds'.format(slide_size)

    # Compute the average temperature
    lines = lines.withColumn("timestamp", col = current_timestamp()).alias("timestamp")
    lines = lines.withColumn("ocupacion", lines.capacidad - lines.libres).alias("ocupacion")

    values = lines.groupBy(
        window(lines.timestamp, window_duration, slide_duration), lines.nombre, lines.capacidad) \
        .agg(functions.mean(lines.ocupacion)) \
        .filter(lines.capacidad > 0)\
        .orderBy("nombre")


    query = values \
        .writeStream \
        .format("console") \
        .outputMode('complete') \
        .start()

    query.awaitTermination()


if __name__ == '__main__':
    if len(sys.argv) != 2:
        print("Usage: spark-submit GetParkingData <directory>", file=sys.stderr)
        exit(-1)

    main(sys.argv[1])
