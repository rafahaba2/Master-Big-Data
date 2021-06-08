package org.mbd5.rdd;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

public class Films {
    public static void main(String[] args) throws FileNotFoundException {

        Logger.getLogger("org").setLevel(Level.OFF);
        // Step 1: create a SparkConf object
        SparkConf conf = new SparkConf()
                .setAppName("Add numbers from files")
                .setMaster("local[4]") ; ;

        // STEP 2: create a Java Spark context
        JavaSparkContext sparkContext = new JavaSparkContext(conf);

        JavaPairRDD<Integer, String> reversePairs= sparkContext
                .textFile("C:\\Users\\Usuario\\Desktop\\Master Big Data\\Modulo8-Scalable Data Processing\\sparkjava\\src\\main\\java\\org\\mbd5\\rdd\\Film_Locations_in_San_Francisco.csv")
                .map(line -> line.split(","))
                .map(array ->array[0])
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((integer, integer2) -> integer + integer2)
                .mapToPair(pair -> new Tuple2<>(pair._2(), pair._1()));

        List<Tuple2<Integer, String>> resultFilter = reversePairs
                .sortByKey(false)
                .filter(y -> y._1() >= 20)
                .collect();


        PrintWriter pw = new PrintWriter(new FileOutputStream("Output.txt"));


        for (Tuple2<Integer, String> tuple : resultFilter) {
            System.out.println("("+tuple._1() + ", " + tuple._2() +")");
            pw.write("("+tuple._1() + ", " + tuple._2() +")" + "\n");
        }


        List<Tuple2<Integer, String>> resultFilter2 = reversePairs
                .sortByKey(false)
                .collect();


        double total_films = 0;
        double films_locations = 0;
        for (Tuple2<Integer, String> tuple : resultFilter2) {
            total_films += 1;
            films_locations += tuple._1;
        }

        double mean_film_locations = films_locations/total_films;
        pw.write("\nTotal number of films: " + total_films + "\n");
        System.out.println("\nTotal number of films: " + total_films);
        pw.write("The average of films locations per film: " + mean_film_locations);
        System.out.println("The average of films locations per film: " + mean_film_locations);

        pw.close();
        // STEP 19: stop the spark context
        sparkContext.stop();
    }
}
