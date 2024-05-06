package amr.AdminTesting;

import Constants.ApiForAdminConstants;
import Helpers.ErrorsCheckers;
import Models.Input.RegisterModel;
import Models.Response.ResponseModel;
import Models.Response.SingleErrorModel;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Admin_CreateManager_Testing {
    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://transportationsystem.somee.com/";
    }

    @Test(priority = 3)
    public void   Create_WithValidInput(){
        RegisterModel model = new RegisterModel("NewManager@gmail.com","@Aa123456789","@Aa123456789","new");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .header("Authorization","Bearer "+ Admin_Login_Testing.Token)
                .when()
                .post(ApiForAdminConstants.CreateManagerEndPoint_Admin);

        Assert.assertEquals(200, res.getStatusCode());
        ResponseModel<Boolean> responseBody = res.as(new TypeRef<ResponseModel<Boolean>>() {});

        Assert.assertTrue(200 == responseBody.statusCode,"status code must be 200");

        Assert.assertTrue(responseBody.body);

    }
    @Test(priority = 3)
    public void   Create_WithInValidEmail(){
        RegisterModel model = new RegisterModel("NewExample2@gmail","@Aa123456789","@Aa123456789","amr");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .header("Authorization","Bearer "+ Admin_Login_Testing.Token)
                .when()
                .post(ApiForAdminConstants.CreateManagerEndPoint_Admin);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body),"body is null");

        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"Email"}, responseBody.body),"Body doesn't contain full objects for errors");
    }
    @Test(priority = 3)
    public void   Create_WithEmptyEmail(){
        RegisterModel model = new RegisterModel("","@Aa123456789","@Aa123456789","amr");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .header("Authorization","Bearer "+ Admin_Login_Testing.Token)
                .when()
                .post(ApiForAdminConstants.CreateManagerEndPoint_Admin);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body),"body is null");

        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"Email"}, responseBody.body),"Body doesn't contain full objects for errors");
    }
    @Test(priority = 3)
    public void   Create_WithExistEmail(){
        RegisterModel model = new RegisterModel("user@example.com","@Aa123456789","@Aa123456789","amr");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .header("Authorization","Bearer "+ Admin_Login_Testing.Token)
                .when()
                .post(ApiForAdminConstants.CreateManagerEndPoint_Admin);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<Boolean> responseBody = res.as(new TypeRef<ResponseModel<Boolean>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");
    }
    @Test(priority = 3)
    public void   Create_WithInvalidPassword(){
        RegisterModel model = new RegisterModel("NewExample4@gmail.com","@A123456789","@A123456789","amr");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .header("Authorization","Bearer "+ Admin_Login_Testing.Token)
                .when()
                .post(ApiForAdminConstants.CreateManagerEndPoint_Admin);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"Password"},responseBody.body));
    }
    @Test(priority = 3)
    public void   Create_WithEmptyPassword(){
        RegisterModel model = new RegisterModel("NewExample4@gmail.com","","@A123456789","amr");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .header("Authorization","Bearer "+ Admin_Login_Testing.Token)
                .when()
                .post(ApiForAdminConstants.CreateManagerEndPoint_Admin);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"Password"},responseBody.body));
    }
    @Test(priority = 3)
    public void   Create_WithInvalidConfirmPassword(){
        RegisterModel model = new RegisterModel("NewExample4@gmail.com","@Aa123456789","@A123456789","amr");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .header("Authorization","Bearer "+ Admin_Login_Testing.Token)
                .when()
                .post(ApiForAdminConstants.CreateManagerEndPoint_Admin);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"ConfirmPassword"},responseBody.body));
    }
    @Test(priority = 3)
    public void   Create_WithEmptyConfirmPassword(){
        RegisterModel model = new RegisterModel("NewExample4@gmail.com","@Aa123456789","","amr");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .header("Authorization","Bearer "+ Admin_Login_Testing.Token)
                .when()
                .post(ApiForAdminConstants.CreateManagerEndPoint_Admin);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"ConfirmPassword"},responseBody.body));
    }
    @Test(priority = 3)
    public void   Create_WithInvalidInput(){
        RegisterModel model = new RegisterModel("NewExamplegmail.com","@a123456789","@A123456789","amr");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .header("Authorization","Bearer "+ Admin_Login_Testing.Token)
                .when()
                .post(ApiForAdminConstants.CreateManagerEndPoint_Admin);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"ConfirmPassword","Email","Password"},responseBody.body));
    }
    @Test(priority = 3)
    public void   Create_WithInvalidName_length(){
        RegisterModel model = new RegisterModel("NewManager@gmail.com","@Aa123456789","@Aa123456789","n");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .header("Authorization","Bearer "+ Admin_Login_Testing.Token)
                .when()
                .post(ApiForAdminConstants.CreateManagerEndPoint_Admin);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"Name"},responseBody.body));

    }
    @Test(priority = 3)
    public void   Create_WithInvalidName_Digits(){
        RegisterModel model = new RegisterModel("NewManager@gmail.com","@Aa123456789","@Aa123456789","213dsan");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .header("Authorization","Bearer "+ Admin_Login_Testing.Token)
                .when()
                .post(ApiForAdminConstants.CreateManagerEndPoint_Admin);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"Name"},responseBody.body));

    }
    @Test(priority = 3)
    public void   Create_WithNoToken(){
        RegisterModel model = new RegisterModel("NewManager@gmail.com","@Aa123456789","@Aa123456789","new");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForAdminConstants.CreateManagerEndPoint_Admin);

        Assert.assertEquals(401, res.getStatusCode());
       }
}
