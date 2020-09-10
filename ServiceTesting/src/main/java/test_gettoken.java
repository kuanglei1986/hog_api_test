

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class test_gettoken {
    @Test
    void testToken() {
        given().log().all()
                .queryParam("corpid","ww73530bb447ccc302")
                .queryParam("corpsecret","yQvRMp3M3aNHnWpGZ8tnNRLMBY84GefYNE__B74im74")
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all().statusCode(200).body("errcode",equalTo(0));


    }


}
