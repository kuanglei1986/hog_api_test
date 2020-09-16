package com.kl.wework.contact;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.kl.wework.Wework;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

public class Department extends Contact {
//    查询部门
    public Response list(String id) {
        Response response =  requestSpecification
                .param("id", id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().extract().response();

        reset();

        return  response;

    }

//    创建部门
    public Response create(String name, String parentId) {
         reset();
         String body = JsonPath.parse(this.getClass().getResourceAsStream("/data/create.json"))
                 .set("$.name", name)
                 .set("$.parentid", parentId).jsonString();
         return requestSpecification
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().statusCode(200).extract().response();

    }

    public Response createbymap(HashMap<String,Object> map) {
        reset();
        DocumentContext documentContext = JsonPath.parse(this.getClass().getResourceAsStream("/data/create.json"));
        map.entrySet().forEach(entry -> {
            documentContext.set(entry.getKey(),entry.getValue());
        });
//        String body = JsonPath.parse(this.getClass().getResourceAsStream("/data/create.json"))
//                .set("$.name", name)
//                .set("$.parentid", parentId).jsonString();
        return requestSpecification
                .body(documentContext.jsonString())
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().statusCode(200).extract().response();

    }

//    删除部门
    public Response delete(String id) {
        return given().log().all()
                .param("access_token", Wework.getToken())
                .param("id", id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all()
                .statusCode(200).extract().response();

    }

    //    更新部门
    public Response update(String name, String id) {
        reset();
        String body = JsonPath.parse(this.getClass().getResourceAsStream("/data/update.json"))
                .set("$.name", name)
                .set("$.id", id).jsonString();
        return requestSpecification
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then().log().all().statusCode(200).extract().response();

    }

}
