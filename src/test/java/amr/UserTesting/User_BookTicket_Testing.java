package amr.UserTesting;

import Constants.ApiForUserConstants;
import Helpers.ErrorsCheckers;
import Models.Input.UserInputs.BookingModel;
import Models.Response.ResponseModel;
import Models.Response.SingleErrorModel;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class User_BookTicket_Testing {
    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://transportationsystem.somee.com/";
    }

    @Test(priority = 4)
    public void Book_WithValidInput(){
        BookingModel model = new BookingModel("f445df7b-105c-4531-8801-05dfb3152f21","a615bc5b-ca79-4be7-90e8-ee2b486deeae");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .header("Authorization","Bearer "+User_SignIn_Testing.Token)
                .when()
                .post(ApiForUserConstants.BookTicket_USer);

        Assert.assertEquals(200, res.getStatusCode());
        ResponseModel<String> responseBody = res.as(new TypeRef<ResponseModel<String>>() {});
        Assert.assertTrue(200 == responseBody.statusCode,"Status body in body isn't true");
    }
    @Test(priority = 4)
    public void Book_WithInValidSeatId(){
        BookingModel model = new BookingModel( "72c13d","a615bc5b-ca79-4be7-90e8-ee2b486deeae");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .header("Authorization", "Bearer " + User_SignIn_Testing.Token)
                .when()
                .post(ApiForUserConstants.BookTicket_USer);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"SeatId"},responseBody.body));
    }
    @Test(priority = 4)
    public void Book_WithEmptySeatId(){
        BookingModel model = new BookingModel( "","a615bc5b-ca79-4be7-90e8-ee2b486deeae");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .header("Authorization", "Bearer " + User_SignIn_Testing.Token)
                .when()
                .post(ApiForUserConstants.BookTicket_USer);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"SeatId"},responseBody.body));
    }
    @Test(priority = 4)
    public void Book_WithNotRealSeatId(){
        BookingModel model = new BookingModel( "72c13dfb-e798-4f13-bdb9-014eb5862c3f","a615bc5b-ca79-4be7-90e8-ee2b486deeae");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .header("Authorization", "Bearer " + User_SignIn_Testing.Token)
                .when()
                .post(ApiForUserConstants.BookTicket_USer);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<String> responseBody = res.as(new TypeRef<ResponseModel<String>>() {});
        Assert.assertTrue(400 == responseBody.statusCode,"Status body in body isn't true");
    }
    @Test(priority = 4)
    public void Book_WithInValidJourneyId(){
        BookingModel model = new BookingModel( "72c13dfb-e798-4f13-bdb9-014eb5862c3c","a615b");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .header("Authorization","Bearer "+User_SignIn_Testing.Token)
                .when()
                .post(ApiForUserConstants.BookTicket_USer);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"JourneyId"},responseBody.body));
    }
    @Test(priority = 4)
    public void Book_WithEmptyJourneyId(){
        BookingModel model = new BookingModel( "72c13dfb-e798-4f13-bdb9-014eb5862c3c","");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .header("Authorization","Bearer "+User_SignIn_Testing.Token)
                .when()
                .post(ApiForUserConstants.BookTicket_USer);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"JourneyId"},responseBody.body));
    }
    @Test(priority = 4)
    public void Book_WithNotRealJourneyId(){
        BookingModel model = new BookingModel( "72c13dfb-e798-4f13-bdb9-014eb5862c3c","a615bc5b-ca79-4be7-90e8-ee2b486deeab");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .header("Authorization","Bearer "+User_SignIn_Testing.Token)
                .when()
                .post(ApiForUserConstants.BookTicket_USer);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<String> responseBody = res.as(new TypeRef<ResponseModel<String>>() {});
        Assert.assertTrue(400 == responseBody.statusCode,"Status body in body isn't true");
    }
    @Test(priority = 4)
    public void Book_WithValidInputAndNoToken(){
        BookingModel model = new BookingModel( "72c13dfb-e798-4f13-bdb9-014eb5862c3c","a615bc5b-ca79-4be7-90e8-ee2b486deeab");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.BookTicket_USer);

        Assert.assertEquals(401, res.getStatusCode());
    }
}
