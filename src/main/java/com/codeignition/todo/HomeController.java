package com.codeignition.todo;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

/**
 * Created by sinister on 07/04/15.
 */
public class HomeController {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/ping", (req, res) -> "Hello World");
    }
}