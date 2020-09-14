package com.kl.wework.contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        department.create("北京研发中心", "1")
        .then().body("errcode", equalTo(60008));

    }
}