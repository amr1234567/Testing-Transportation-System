package amr.AdminTesting;

import Constants.ApiForAdminConstants;
import Constants.ApiForUserConstants;
import Helpers.ErrorsCheckers;
import Models.Input.LoginModel;
import Models.Response.ResponseModel;
import Models.Response.SingleErrorModel;
import Models.Response.TokenResponseModel;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Admin_Login_Testing {
    public static String Token;

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://transportationsystem.somee.com/";
    }

    @Test(priority = 2)
    public void SignIn_WithValidData() {
//      "user@example.com"
        LoginModel model = new LoginModel("adminAccount@admin.com","@Aa123456789");
        Response res = given().log().all()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForAdminConstants.LoginEndPoint_Admin);

        res.then().log().all();
        Assert.assertEquals(200, res.getStatusCode());
        ResponseModel<TokenResponseModel> responseBody = res.as(new TypeRef<ResponseModel<TokenResponseModel>>() {});
        Assert.assertTrue(200 == responseBody.statusCode,"Status body in body isn't true");
        Assert.assertTrue(responseBody.body != null,"body in body is null");
        Token = responseBody.body.token;
    }
    @Test(priority = 2)
    public void SignIn_WithInValidEmail() {
        LoginModel model = new LoginModel("user@example","@Aa123456789");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForAdminConstants.LoginEndPoint_Admin);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body),"body is null");

        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"Email"}, responseBody.body),"Body doesn't contain full objects for errors");
    }
    @Test(priority = 2)
    public void SignIn_WithEmptyEmail() {
        LoginModel model = new LoginModel("","@Aa123456789");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForAdminConstants.LoginEndPoint_Admin);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body),"body is null");

        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"Email"}, responseBody.body),"Body doesn't contain full objects for errors");
    }
    @Test(priority = 2)
    public void SignIn_WithInValidPassword(){

        LoginModel model = new LoginModel("adminAccount@admin.com","Aa123456789");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForAdminConstants.LoginEndPoint_Admin);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body),"body is null");

        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"Password"}, responseBody.body),"Body doesn't contain full objects for errors");
    }
    @Test(priority = 2)
    public void SignIn_WithEmptyPassword(){

        LoginModel model = new LoginModel("adminAccount@admin.com","");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForAdminConstants.LoginEndPoint_Admin);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body),"body is null");

        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"Password"}, responseBody.body),"Body doesn't contain full objects for errors");
    }
    @Test(priority = 2)
    public void SignIn_WithInValidInput(){
        LoginModel model = new LoginModel("adminAccounadmin.com","Aa123456789");
        Response res =given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForAdminConstants.LoginEndPoint_Admin);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body),"body is null");

        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"Password", "Email"}, responseBody.body),"Body doesn't contain full objects for errors");
    }
    @Test(priority = 2)
    public void SignIn_WithWrongPassword(){
        LoginModel model = new LoginModel("adminAccount@admin.com","@Aa12345678");
        Response res =given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForAdminConstants.LoginEndPoint_Admin);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(!ErrorsCheckers.CheckOnBodyExist(responseBody.body),"body is not null");
    }

}
