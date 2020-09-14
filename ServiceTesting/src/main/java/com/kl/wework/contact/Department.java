package com.kl.wework.contact;

import com.jayway.jsonpath.JsonPath;
import com.kl.wework.Wework;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

public class Department {
//    查询部门
    public Response list(String id) {
        return given().log().all()
                .param("access_token", Wework.getToken())
                .param("id", id)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all()
                .statusCode(200).extract().response();

    }

//    创建部门
    public Response create(String name, String parentId) {
         String body = JsonPath.parse(this.getClass().getResourceAsStream("/data/create.json"))
                 .set("$.name", name)
                 .set("$.parentid", parentId).jsonString();
         return (Response) given().log().all().queryParam("access_token", Wework.getToken())
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().statusCode(200).extract().response();


    }

}
