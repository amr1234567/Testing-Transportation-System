package amr.ManagerTesting;

import Constants.ApiForManagerConstants;
import Helpers.ErrorsCheckers;
import Models.Input.ManagerInputs.AddJourneyModel;
import Models.Response.ResponseModel;
import Models.Response.SingleErrorModel;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class Manager_AddJourny_Testing {
    private final String LeavingTime = LocalDateTime.now().plusDays(8).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
    private final String ArrivalTime = LocalDateTime.now().plusDays(8).plusHours(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
    private final String BusId = "78e85dbc-411b-4d83-9924-4688d2601715";
    private final String StartBusStopId = "fdfbe801-8e4e-4c52-b1ec-f257fceef7c5";
    private final String DestinationId = "f3e7098c-55d5-4aff-a783-5370fb04aabc";

    private final String InvalidLeavingTime = LocalDateTime.now().minusDays(8).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
    private final String InvalidArrivalTime = LocalDateTime.now().minusDays(8).minusHours(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));


    @Test(priority = 4)
    public void Add_WithValidInput(){
        AddJourneyModel model = new AddJourneyModel(DestinationId,StartBusStopId,LeavingTime,ArrivalTime,BusId,50);
        Response res = given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+ Manager_LogIn_Testing.Token)
                .body(model)
                .when()
                .post(ApiForManagerConstants.AddJourneyEndPoint_Manager);
res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200);
        ResponseModel<Boolean> responseBody = res.as(new TypeRef<ResponseModel<Boolean>>() {});
        Assert.assertEquals(responseBody.statusCode,200);
    }

    @Test(priority = 4)
    public void Add_WithInvalidDestenationId(){
        AddJourneyModel model = new AddJourneyModel("testTest",StartBusStopId,LeavingTime,ArrivalTime,BusId,50);
        Response res = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+ Manager_LogIn_Testing.Token)
                .body(model)
                .when()
                .post(ApiForManagerConstants.AddJourneyEndPoint_Manager);

        Assert.assertEquals(res.getStatusCode(), 400);
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertEquals(responseBody.statusCode,400);
        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body),"body is not null");
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"destinationId"},responseBody.body),"body is not null");
    }
    @Test(priority = 4)
    public void Add_WithNotRealDestenationId(){
        AddJourneyModel model = new AddJourneyModel("59953677-903e-4fd9-9a29-4941b633773f",StartBusStopId,LeavingTime,ArrivalTime,BusId,50);
        Response res = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+ Manager_LogIn_Testing.Token)
                .body(model)
                .when()
                .post(ApiForManagerConstants.AddJourneyEndPoint_Manager);

        Assert.assertEquals(res.getStatusCode(), 400);
        ResponseModel<Boolean> responseBody = res.as(new TypeRef<ResponseModel<Boolean>>() {});
        Assert.assertEquals(responseBody.statusCode,400);
        }
    @Test(priority = 4)
    public void Add_WithEmptyDestenationId(){
        AddJourneyModel model = new AddJourneyModel("",StartBusStopId,LeavingTime,ArrivalTime,BusId,50);
        Response res = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+ Manager_LogIn_Testing.Token)
                .body(model)
                .when()
                .post(ApiForManagerConstants.AddJourneyEndPoint_Manager);

        Assert.assertEquals(res.getStatusCode(), 400);
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"destinationId"},responseBody.body));
    }
    @Test(priority = 4)
    public void Add_WithInvalidStartBusStopId(){
        AddJourneyModel model = new AddJourneyModel(DestinationId,"TestTest",LeavingTime,ArrivalTime,BusId,50);
        Response res = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+ Manager_LogIn_Testing.Token)
                .body(model)
                .when()
                .post(ApiForManagerConstants.AddJourneyEndPoint_Manager);

        Assert.assertEquals(res.getStatusCode(), 400);
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertEquals(responseBody.statusCode,400);
        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body),"body is not null");
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"startBusStopId"},responseBody.body),"body is not null");
    }
    @Test(priority = 4)
    public void Add_WithNotRealStartBusStopId(){
        AddJourneyModel model = new AddJourneyModel(DestinationId,"59953677-903e-4fd9-9a29-4541b633773f",LeavingTime,ArrivalTime,BusId,50);
        Response res = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+ Manager_LogIn_Testing.Token)
                .body(model)
                .when()
                .post(ApiForManagerConstants.AddJourneyEndPoint_Manager);

        Assert.assertEquals(res.getStatusCode(), 400);
        ResponseModel<Boolean> responseBody = res.as(new TypeRef<ResponseModel<Boolean>>() {});
        Assert.assertEquals(responseBody.statusCode,400);
    }
    @Test(priority = 4)
    public void Add_WithEmptyStartBusStopId(){
        AddJourneyModel model = new AddJourneyModel(DestinationId,"",LeavingTime,ArrivalTime,BusId,50);
        Response res = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+ Manager_LogIn_Testing.Token)
                .body(model)
                .when()
                .post(ApiForManagerConstants.AddJourneyEndPoint_Manager);

        Assert.assertEquals(res.getStatusCode(), 400);
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"StartBusStopId"},responseBody.body));
    }
    @Test(priority = 4)
    public void Add_WithInvalidBusId(){
        AddJourneyModel model = new AddJourneyModel(DestinationId,StartBusStopId,LeavingTime,ArrivalTime,"59953",50);
        Response res = given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+ Manager_LogIn_Testing.Token)
                .body(model)
                .when()
                .post(ApiForManagerConstants.AddJourneyEndPoint_Manager);
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 400);
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertEquals(responseBody.statusCode,400);
        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body),"body is null");
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"busId"},responseBody.body),"body doesn't contain busId Field");
    }
    @Test(priority = 4)
    public void Add_WithNotRealBusId(){
        AddJourneyModel model = new AddJourneyModel(DestinationId,StartBusStopId,LeavingTime,ArrivalTime,"59953677-903e-4fd9-9a29-4541b633773f",50);
        Response res = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+ Manager_LogIn_Testing.Token)
                .body(model)
                .when()
                .post(ApiForManagerConstants.AddJourneyEndPoint_Manager);

        Assert.assertEquals(res.getStatusCode(), 400);
        ResponseModel<Boolean> responseBody = res.as(new TypeRef<ResponseModel<Boolean>>() {});
        Assert.assertEquals(responseBody.statusCode,400);
    }
    @Test(priority = 4)
    public void Add_WithEmptyBusId(){
        AddJourneyModel model = new AddJourneyModel(DestinationId,StartBusStopId,LeavingTime,ArrivalTime,"",50);
        Response res = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+ Manager_LogIn_Testing.Token)
                .body(model)
                .when()
                .post(ApiForManagerConstants.AddJourneyEndPoint_Manager);

        Assert.assertEquals(res.getStatusCode(), 400);
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"BusId"},responseBody.body));
    }
    @Test(priority = 4)
    public void Add_WithInvalidArrivalTime(){
        AddJourneyModel model = new AddJourneyModel(DestinationId,StartBusStopId,LeavingTime,InvalidArrivalTime,BusId,50);
        Response res = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+ Manager_LogIn_Testing.Token)
                .body(model)
                .when()
                .post(ApiForManagerConstants.AddJourneyEndPoint_Manager);

        Assert.assertEquals(res.getStatusCode(), 400);
        ResponseModel<Boolean> responseBody = res.as(new TypeRef<ResponseModel<Boolean>>() {});
        Assert.assertEquals(responseBody.statusCode,400);
    }
    @Test(priority = 4)
    public void Add_WithInvalidLeavingTime(){
        AddJourneyModel model = new AddJourneyModel(DestinationId,StartBusStopId,InvalidLeavingTime,ArrivalTime,"59953677-903e-4fd9-9a29-4541b633773f",50);
        Response res = given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+ Manager_LogIn_Testing.Token)
                .body(model)
                .when()
                .post(ApiForManagerConstants.AddJourneyEndPoint_Manager);

        Assert.assertEquals(res.getStatusCode(), 400);
        ResponseModel<Boolean> responseBody = res.as(new TypeRef<ResponseModel<Boolean>>() {});
        Assert.assertEquals(responseBody.statusCode,400);
    }
    @Test(priority = 4)
    public void Add_WithValidInputAndNoToken(){
        AddJourneyModel model = new AddJourneyModel(DestinationId,StartBusStopId,LeavingTime,ArrivalTime,BusId,50);
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForManagerConstants.AddJourneyEndPoint_Manager);

        Assert.assertEquals(401, res.getStatusCode());
    }
}
