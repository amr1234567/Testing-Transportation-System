package amr.AdminTesting;

import Constants.ApiForAdminConstants;
import Helpers.ErrorsCheckers;
import Models.Response.ResponseModel;
import Models.Response.SingleErrorModel;
import amr.ManagerTesting.Manager_LogIn_Testing;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Admin_EnrollBusStopToBusStop_Testing {
    private final String StartBusStopId = "fdfbe801-8e4e-4c52-b1ec-f257fceef7c5";
    private final String DestinationBusStopId = "f3e7098c-55d5-4aff-a783-5370fb04aabc";

    @Test(priority = 4)
    public void Enroll_withValidParamters(){
        Response res = given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + Admin_Login_Testing.Token)
                .queryParam("StartBusStopId",StartBusStopId)
                .queryParam("DestinationBusStopId",DestinationBusStopId)
                .when()
                .post(ApiForAdminConstants.EnrollBusStopToAnotherEndPoint_Admin);
        Assert.assertEquals(res.getStatusCode(),200);
        ResponseModel<Boolean> responseBody = res.as(new TypeRef<ResponseModel<Boolean>>() {});
        Assert.assertEquals(responseBody.statusCode,200);
        Assert.assertTrue(responseBody.body);
    }
    @Test(priority = 4)
    public void Enroll_withInValidStartBusStopId(){
        Response res = given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + Admin_Login_Testing.Token)
                .queryParam("StartBusStopId","fdfbe801-8e4e-4c52-b1ef-f257fceef7c5")
                .queryParam("DestinationBusStopId",DestinationBusStopId)
                .when()
                .post(ApiForAdminConstants.EnrollBusStopToAnotherEndPoint_Admin);
        Assert.assertEquals(res.getStatusCode(),400);
        ResponseModel<Boolean> responseBody = res.as(new TypeRef<ResponseModel<Boolean>>() {});
        Assert.assertEquals(responseBody.statusCode,400);
    }
    @Test(priority = 4)
    public void Enroll_withEmptyStartBusStopId(){
        Response res = given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + Admin_Login_Testing.Token)
                .queryParam("DestinationBusStopId",DestinationBusStopId)
                .when()
                .post(ApiForAdminConstants.EnrollBusStopToAnotherEndPoint_Admin);
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(),400);
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertEquals(responseBody.statusCode,400);
        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"StartBusStopId"},responseBody.body));
    }
    @Test(priority = 4)
    public void Enroll_withInValidDestinationBusStopId(){
        Response res = given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + Admin_Login_Testing.Token)
                .queryParam("StartBusStopId",StartBusStopId)
                .queryParam("DestinationBusStopId","fdfbe801-8e4e-4c52-b1ef-f257fceef7c5")
                .when()
                .post(ApiForAdminConstants.EnrollBusStopToAnotherEndPoint_Admin);
        Assert.assertEquals(res.getStatusCode(),400);
        ResponseModel<Boolean> responseBody = res.as(new TypeRef<ResponseModel<Boolean>>() {});
        Assert.assertEquals(responseBody.statusCode,400);
    }
    @Test(priority = 4)
    public void Enroll_withEmptyDestinationBusStopId(){
        Response res = given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + Admin_Login_Testing.Token)
                .queryParam("StartBusStopId",StartBusStopId)
                .when()
                .post(ApiForAdminConstants.EnrollBusStopToAnotherEndPoint_Admin);
        Assert.assertEquals(res.getStatusCode(),400);
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertEquals(responseBody.statusCode,400);
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"DestinationBusStopId"},responseBody.body));
    }
    @Test(priority = 4)
    public void Enroll_withInValidParameters(){
        Response res = given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + Admin_Login_Testing.Token)
                .queryParam("StartBusStopId","fdfbe801-8e4e-4c52-b1ef-f257fceef7f5")
                .queryParam("DestinationBusStopId","fdfbe801-8e4e-4c52-b1ef-f257fceff7c5")
                .when()
                .post(ApiForAdminConstants.EnrollBusStopToAnotherEndPoint_Admin);
        Assert.assertEquals(res.getStatusCode(),400);
        ResponseModel<Boolean> responseBody = res.as(new TypeRef<ResponseModel<Boolean>>() {});
        Assert.assertEquals(responseBody.statusCode,400);
    }
    @Test(priority = 4)
    public void Enroll_withInEmptyParameters(){
        Response res = given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + Admin_Login_Testing.Token)
                .when()
                .post(ApiForAdminConstants.EnrollBusStopToAnotherEndPoint_Admin);
        Assert.assertEquals(res.getStatusCode(),400);
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertEquals(responseBody.statusCode,400);
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"StartBusStopId","DestinationBusStopId"},responseBody.body));
    }
    @Test(priority = 4)
    public void Enroll_withNoToken(){
        Response res = given().log().all()
                .contentType(ContentType.JSON)
                .queryParam("StartBusStopId",StartBusStopId)
                .queryParam("DestinationBusStopId",DestinationBusStopId)
                .when()
                .post(ApiForAdminConstants.EnrollBusStopToAnotherEndPoint_Admin);
        Assert.assertEquals(res.getStatusCode(),401);
    }
}
