package amr.UserTesting;

import Constants.ApiForUserConstants;
import Helpers.ErrorsCheckers;
import Models.Input.RegisterModel;
import Models.Input.UserInputs.RegisterAsUserModel;
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
import static org.hamcrest.Matchers.*;

public class User_SignUP_Testing {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://transportationsystem.somee.com/";
    }

    @Test(priority = 0)
    public void SignUp_WithValidInput(){
        RegisterAsUserModel model = new RegisterAsUserModel("NewExample5@gmail.com","@Aa123456789","+201152899886","@Aa123456789","amr");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.SignUp_User);

        Assert.assertEquals(200, res.getStatusCode());
        ResponseModel<Boolean> responseBody = res.as(new TypeRef<ResponseModel<Boolean>>() {});

        Assert.assertTrue(200 == responseBody.statusCode,"status code must be 200");

        Assert.assertTrue(responseBody.body);

    }
    @Test(priority = 0)
    public void SignUp_WithInValidEmail(){
         RegisterAsUserModel model = new RegisterAsUserModel("NewExample2@gmail","@Aa123456789","+201152899886","@Aa123456789","amr");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.SignUp_User);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body),"body is null");

        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"Email"}, responseBody.body),"Body doesn't contain full objects for errors");
    }
    @Test(priority = 0)
    public void SignUp_WithEmptyEmail(){
        RegisterAsUserModel model = new RegisterAsUserModel("","@Aa123456789","+201152899886","@Aa123456789","amr");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.SignUp_User);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body),"body is null");

        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"Email"}, responseBody.body),"Body doesn't contain full objects for errors");
    }
    @Test(priority = 0)
    public void SignUp_WithExistEmail(){
        RegisterAsUserModel model = new RegisterAsUserModel("user@example.com","@Aa123456789","+201152899886","@Aa123456789","amr");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.SignUp_User);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<Boolean> responseBody = res.as(new TypeRef<ResponseModel<Boolean>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");
    }
    @Test(priority = 0)
    public void SignUp_WithInvalidPassword(){
        RegisterAsUserModel model = new RegisterAsUserModel("NewExample4@gmail.com","@A123456789","+201152899886","@A123456789","amr");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.SignUp_User);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"Password"},responseBody.body));
    }
    @Test(priority = 0)
    public void SignUp_WithEmptyPassword(){
        RegisterAsUserModel model = new RegisterAsUserModel("NewExample4@gmail.com","","+201152899886","@A123456789","amr");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.SignUp_User);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"Password"},responseBody.body));
    }
    @Test(priority = 0)
    public void SignUp_WithInvalidConfirmPassword(){
        RegisterAsUserModel model = new RegisterAsUserModel("NewExample4@gmail.com","@Aa123456789","+201152899886","@A123456789","amr");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.SignUp_User);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"ConfirmPassword"},responseBody.body));
    }
    @Test(priority = 0)
    public void SignUp_WithEmptyConfirmPassword(){
        RegisterAsUserModel model = new RegisterAsUserModel("NewExample4@gmail.com","@Aa123456789","+201152899886","","amr");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.SignUp_User);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"ConfirmPassword"},responseBody.body));
    }
    @Test(priority = 0)
    public void SignUp_WithInvalidPhoneNumber(){
        RegisterAsUserModel model = new RegisterAsUserModel("NewExample4@gmail.com","@Aa123456789","+20115299886","@Aa123456789","amr");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.SignUp_User);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"PhoneNumber"},responseBody.body));
    }
    @Test(priority = 0)
    public void SignUp_WithEmptyPhoneNumber(){
        RegisterAsUserModel model = new RegisterAsUserModel("NewExample4@gmail.com","@Aa123456789","","@Aa123456789","amr");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.SignUp_User);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"PhoneNumber"},responseBody.body));
    }
    @Test(priority = 0)
    public void SignUp_WithEmptyName(){
        RegisterAsUserModel model = new RegisterAsUserModel("NewExample4@gmail.com","@Aa123456789","+201152899886","@Aa123456789","");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.SignUp_User);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"Name"},responseBody.body));
    }
    @Test(priority = 0)
    public void SignUp_WithInvalidName_Digits(){
        RegisterAsUserModel model = new RegisterAsUserModel("NewExample4@gmail.com","@Aa123456789","+201152899886","@Aa123456789","548ads");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.SignUp_User);
res.then().log().all();
        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"Name"},responseBody.body));
    }
    @Test(priority = 0)
    public void SignUp_WithInvalidName_Length(){
        RegisterAsUserModel model = new RegisterAsUserModel("NewExample4@gmail.com","@Aa123456789","+201152899886","@Aa123456789","ds");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.SignUp_User);
        res.then().log().all();
        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"Name"},responseBody.body));
    }
    @Test(priority = 0)
    public void SignUp_WithInvalidInput(){


        RegisterAsUserModel model = new RegisterAsUserModel("NewExamplegmail.com","@a123456789","+20115299886","@A123456789","amr");

        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.SignUp_User);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});

        Assert.assertTrue(400 == responseBody.statusCode,"status code must be 400");

        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"ConfirmPassword","PhoneNumber","Email","Password"},responseBody.body));
    }

}
