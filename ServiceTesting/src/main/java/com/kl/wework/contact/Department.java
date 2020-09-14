package com.kl.wework.contact;

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
         given().log().all().queryParam("access_token", Wework.getToken())
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "   \"name\": \""+ name + ",\n" +
                        "   \"parentid\": " + parentId + ",\n" +
                        "   \"order\": 1,\n" +
                        "}")
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().statusCode(200).body("errcode", equalTo(0));

        return null;
    }

}
