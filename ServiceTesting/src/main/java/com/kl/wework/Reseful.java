package com.kl.wework;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Reseful {
    HashMap<String,Object> query = new HashMap<String, Object>();
    public RequestSpecification requestSpecification = given();

    public Response send() {
        requestSpecification = given().log().all();
        query.entrySet().forEach( entry -> {
            requestSpecification.queryParam(entry.getKey(), entry.getValue());
        });
        return requestSpecification.when().request("get", "www.baidu.com");
    }


}