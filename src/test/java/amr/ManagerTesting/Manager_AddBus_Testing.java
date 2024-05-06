package amr.ManagerTesting;

import Constants.ApiForManagerConstants;
import Constants.ApiForUserConstants;
import Helpers.ErrorsCheckers;
import Models.Input.ManagerInputs.AddingBusModel;
import Models.Input.UserInputs.BookingModel;
import Models.Response.ResponseModel;
import Models.Response.SingleErrorModel;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Manager_AddBus_Testing {

    @Test(priority = 4)
    public void Add_WithValidNumberOfSeats(){
        AddingBusModel model = new AddingBusModel(35);
        Response res = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+ Manager_LogIn_Testing.Token)
                .body(model)
                .when()
                .post(ApiForManagerConstants.AddBusEndPoint_Manager);

        Assert.assertEquals(res.getStatusCode(), 200);
        ResponseModel<Boolean> responseBody = res.as(new TypeRef<ResponseModel<Boolean>>() {});
        Assert.assertEquals(responseBody.statusCode,200);
    }
    @Test(priority = 4)
    public void Add_WithOverLimitNumberOfSeats(){
        AddingBusModel model = new AddingBusModel(45);
        Response res = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+ Manager_LogIn_Testing.Token)
                .body(model)
                .when()
                .post(ApiForManagerConstants.AddBusEndPoint_Manager);

        Assert.assertEquals(res.getStatusCode(), 400);
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertEquals(responseBody.statusCode,400);
        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body),"body is not null");
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"NumberOfSeats"},responseBody.body),"body is not null");
    }
    @Test(priority = 4)
    public void Add_WithUnderLimitNumberOfSeats(){
        AddingBusModel model = new AddingBusModel(20);
        Response res = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+ Manager_LogIn_Testing.Token)
                .body(model)
                .when()
                .post(ApiForManagerConstants.AddBusEndPoint_Manager);

        Assert.assertEquals(res.getStatusCode(), 400);
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertEquals(responseBody.statusCode,400);
        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body),"body is not null");
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"NumberOfSeats"},responseBody.body),"body is not null");
    }
    @Test(priority = 4)
    public void Add_WithValidInputAndNoToken(){
        AddingBusModel model = new AddingBusModel(20);
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForManagerConstants.AddBusEndPoint_Manager);

        Assert.assertEquals(401, res.getStatusCode());
    }
}
