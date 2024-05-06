package amr.AdminTesting;

import Constants.ApiForAdminConstants;
import Constants.ApiForManagerConstants;
import Models.Response.Journey;
import Models.Response.ResponseModel;
import amr.ManagerTesting.Manager_LogIn_Testing;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Admin_GetHistoryJourneys_Testing {
    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://transportationsystem.somee.com/";
    }

    @Test(priority = 4)
    public void GetJourneys_WithTokenSet(){
        Response res = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+ Admin_Login_Testing.Token)
                .when()
                .get(ApiForAdminConstants.GetAllHistoryJourneyEndPoint_Admin);
        Assert.assertEquals(res.getStatusCode(), 200);
        ResponseModel<Journey[]> responseBody = res.as(new TypeRef<ResponseModel<Journey[]>>() {});
        Assert.assertNotNull(responseBody.body);
    }
    @Test(priority = 4)
    public void GetJourneys_WithNoToken(){
        Response res = given()
                .contentType(ContentType.JSON)
                .when()
                .get(ApiForAdminConstants.GetAllHistoryJourneyEndPoint_Admin);
        Assert.assertEquals(res.getStatusCode(), 401);
    }
}
