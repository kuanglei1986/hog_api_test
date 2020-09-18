package com.kl.wework.contact;

import com.kl.wework.Reseful;
import com.kl.wework.Wework;
import io.restassured.http.ContentType;

public class Contact extends Reseful {
    String random = String.valueOf(System.currentTimeMillis());

    public Contact() {
        reset();
    }

    public void reset() {
        requestSpecification
                .log().all()
                .contentType(ContentType.JSON)
                .queryParam("access_token", Wework.getToken())
                .expect().log().all().statusCode(200);
    }

}

