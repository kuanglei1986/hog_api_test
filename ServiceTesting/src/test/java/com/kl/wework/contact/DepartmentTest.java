package com.kl.wework.contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.put;
import static java.lang.Math.random;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {
    Department department;

    @BeforeEach
    void setUp() {
        if (department == null) {
            department = new Department();

        }

    }

    @Test
    void list() {
//        Department department = new Department();
        department.list("3").then().statusCode(200)
        .body("department.name[0]", equalTo("kl研发中"));

    }

    @Test
    void create() {
        department.create("北京研发中心1", "1")
        .then().body("errcode", equalTo(60008));

    }

    @Test
    void createbymap() {
        HashMap<String,Object> map = new HashMap<String,Object>(){{
            put("name", "dep"+random());
            put("parentid", "1");

        }};

        department.createbymap(map)
                .then().body("errcode", equalTo(0));

    }

    @Test
    void delete() {

    }

    @Test
    void update() {
        String oldname = "北京研发中心2";
//        department.create(oldname,"1");
        String id = String.valueOf(department.list("").path("department.find{it.name == '"+ oldname +"'}.id"));
        department.update("恒大2",id).then().body("errcode",equalTo(0));

    }
}