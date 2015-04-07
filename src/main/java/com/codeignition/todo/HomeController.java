package com.codeignition.todo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static spark.Spark.*;
import static spark.SparkBase.staticFileLocation;

/**
 * Created by sinister on 07/04/15.
 */
public class HomeController {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/ping", (req, res) -> "Hello World");

        get("/api/*", (request, response) -> {
            HttpClient client = new DefaultHttpClient();
            HttpGet apiRequest = new HttpGet("http://localhost:4000/" + request.splat()[0]);
            HttpResponse apiResponse = client.execute(apiRequest);
            response.status(apiResponse.getStatusLine().getStatusCode());
            response.type("application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader((apiResponse.getEntity().getContent())));
            return br.readLine();
        });

        post("/api/*", (request, response) -> {
            HttpClient client = new DefaultHttpClient();
            HttpPost apiRequest = new HttpPost("http://localhost:4000/" + request.splat()[0]);
            apiRequest.setEntity(new StringEntity(request.body()));
            HttpResponse apiResponse = client.execute(apiRequest);
            response.status(apiResponse.getStatusLine().getStatusCode());
            response.type("application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader((apiResponse.getEntity().getContent())));
            return br.readLine();
        });

        put("/api/*", (request, response) -> {
            HttpClient client = new DefaultHttpClient();
            HttpPut apiRequest = new HttpPut("http://localhost:4000/" + request.splat()[0]);
            apiRequest.setEntity(new StringEntity(request.body()));
            HttpResponse apiResponse = client.execute(apiRequest);
            response.status(apiResponse.getStatusLine().getStatusCode());
            response.type("application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader((apiResponse.getEntity().getContent())));
            return br.readLine();
        });

        delete("/api/*", (request, response) -> {
            HttpClient client = new DefaultHttpClient();
            HttpDelete apiRequest = new HttpDelete("http://localhost:4000/" + request.splat()[0]);
            HttpResponse apiResponse = client.execute(apiRequest);
            response.status(apiResponse.getStatusLine().getStatusCode());
            response.type("application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader((apiResponse.getEntity().getContent())));
            return br.readLine();
        });
    }
}