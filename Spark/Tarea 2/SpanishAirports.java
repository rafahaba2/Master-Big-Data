package org.mbd5.rdd;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class Airports {

    public static void main(String[] args) throws FileNotFoundException {

        Logger.getLogger("org").setLevel(Level.OFF);
        // Step 1: create a SparkConf object
        SparkConf conf = new SparkConf()
                .setAppName("Add numbers from files")
                .setMaster("local[4]") ; ;

        // STEP 2: create a Java Spark context
        JavaSparkContext sparkContext = new JavaSparkContext(conf);


        //------------------------LONG FORMAT---------------------------//
        // STEP 3: read lines of files
        //JavaRDD<String> lines = sparkContext.textFile("C:\\Users\\Usuario\\Desktop\\Master Big Data\\Modulo8-Scalable Data Processing\\sparkjava\\src\\main\\java\\org\\mbd5\\rdd\\airports.csv");

        /*

        // STEP 4: CSV-> Split using ","
        JavaRDD<String[]> linesData = lines.map(line -> line.split(","));

        JavaRDD<String[]> linesDatafiltered = linesData.filter(line -> line[8].contains("ES"));
        JavaRDD<String> typeFiltered = linesDatafiltered.map(array ->array[2]);


        // STEP 6: map operation to create pairs <word, 1> per every word
        JavaPairRDD<String, Integer> pairs = typeFiltered
                .mapToPair(word -> new Tuple2<>(word, 1));

        // STEP 6: reduce operation that sum the values of all the pairs having the same key (word),
        //         generating a pair <key, sum>
        JavaPairRDD<String, Integer> groupedPairs = pairs
                .reduceByKey((integer, integer2) -> integer + integer2);

        // STEP 7: map operation to get an RDD of pairs <sum, key>. We need this step because Spark
        //         Spark provides a sortByKey() funcion (see next step) but not a sortByValue()
        JavaPairRDD<Integer, String> reversePairs = groupedPairs
                .mapToPair(pair -> new Tuple2<>(pair._2(), pair._1()));

        // STEP 8: sort the results by key ant take the first 20 elements
        List<Tuple2<Integer, String>> output = reversePairs
                .sortByKey(false)
                .take(20);

        */

        //------------------SHORT FORMAT------------------//
        List<Tuple2<Integer, String>> output = sparkContext
                .textFile("C:\\Users\\Usuario\\Desktop\\Master Big Data\\Modulo8-Scalable Data Processing\\sparkjava\\src\\main\\java\\org\\mbd5\\rdd\\airports.csv")
                .map(line -> line.split(","))
                .filter(line -> line[8].contains("ES"))
                .map(array ->array[2])
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((integer, integer2) -> integer + integer2)
                .mapToPair(pair -> new Tuple2<>(pair._2(), pair._1()))
                .sortByKey(false)
                .take(20);
        PrintWriter pw = new PrintWriter(new FileOutputStream("Airports.txt"));
        // STEP 9: print the results
        for (Tuple2<?, ?> tuple : output) {
            pw.write(tuple._1() + ": " + tuple._2()+ "\n");
            System.out.println(tuple._1() + ": " + tuple._2());
        }

        // STEP 19: stop the spark context
        pw.close();
        sparkContext.stop();
    }
}