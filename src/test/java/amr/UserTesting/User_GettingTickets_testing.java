package amr.UserTesting;

import Constants.ApiForUserConstants;
import Models.Response.ResponseModel;
import Models.Response.Ticket;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class User_GettingTickets_testing {
    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://transportationsystem.somee.com/";
    }

    @Test(priority = 4)
    public void GetTickets_WithTokenSet(){
        Response res = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+User_SignIn_Testing.Token)
                .when()
                .get(ApiForUserConstants.GetAllTickets_User);
        Assert.assertEquals(res.getStatusCode(), 200);
        ResponseModel<Ticket[]> responseBody = res.as(new TypeRef<ResponseModel<Ticket[]>>() {});
        Assert.assertNotNull(responseBody.body);
    }
    @Test(priority = 4)
    public void GetTickets_WithNoToken(){
        Response res = given()
                .contentType(ContentType.JSON)
                .when()
                .get(ApiForUserConstants.GetAllTickets_User);
        Assert.assertEquals(res.getStatusCode(), 401);
    }
}
