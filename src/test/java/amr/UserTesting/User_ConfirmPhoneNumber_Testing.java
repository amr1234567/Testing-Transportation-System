package amr.UserTesting;

import Constants.ApiForUserConstants;
import Helpers.ErrorsCheckers;
import Models.Input.UserInputs.ConfirmAccountModel;
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

public class User_ConfirmPhoneNumber_Testing {
    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://transportationsystem.somee.com/";
    }

    @Test(priority = 1)
    public void ConfirmPhoneNumber_WithValidDate(){
        ConfirmAccountModel model = new ConfirmAccountModel("584724","NewExample5@gmail.com","+201152899886");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.ConfirmPhoneNumber_User);

        Assert.assertEquals(200, res.getStatusCode());
        ResponseModel<String> responseBody = res.as(new TypeRef<ResponseModel<String>>() {});
        Assert.assertTrue(200 == responseBody.statusCode,"Status body in body isn't true");
//        Assert.assertTrue(responseBody.body != null,"body in body is null");
    }
    @Test(priority = 1)
    public void ConfirmPhoneNumber_WithNotRealEmail(){
        ConfirmAccountModel model = new ConfirmAccountModel("664278","NewExampl5@gmail.com","+201152899886");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.ConfirmPhoneNumber_User);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<String> responseBody = res.as(new TypeRef<ResponseModel<String>>() {});
        Assert.assertTrue(400 == responseBody.statusCode,"Status body in body isn't true");
    }
    @Test(priority = 1)
    public void ConfirmPhoneNumber_WithInValidEmail(){
        ConfirmAccountModel model = new ConfirmAccountModel("664278","NewExample5gmail.com","20112899888");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.ConfirmPhoneNumber_User);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"Email"},responseBody.body));
    }
    @Test(priority = 1)
    public void ConfirmPhoneNumber_WithInEmptyEmail(){
        ConfirmAccountModel model = new ConfirmAccountModel("664278","","");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.ConfirmPhoneNumber_User);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"PhoneNumber"},responseBody.body));
    }
    @Test(priority = 1)
    public void ConfirmPhoneNumber_WithNotRealPhoneNumber(){
        ConfirmAccountModel model = new ConfirmAccountModel("664278","NewExample5@gmail.com","+201152899888");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.ConfirmPhoneNumber_User);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<String> responseBody = res.as(new TypeRef<ResponseModel<String>>() {});
        Assert.assertTrue(400 == responseBody.statusCode,"Status body in body isn't true");
    }
    @Test(priority = 1)
    public void ConfirmPhoneNumber_WithInValidPhoneNumber(){
        ConfirmAccountModel model = new ConfirmAccountModel("664278","NewExample5@gmail.com","20112899888");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.ConfirmPhoneNumber_User);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"PhoneNumber"},responseBody.body));
    }
    @Test(priority = 1)
    public void ConfirmPhoneNumber_WithInEmptyPhoneNumber(){
        ConfirmAccountModel model = new ConfirmAccountModel("664278","NewExample5@gmail.com","");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.ConfirmPhoneNumber_User);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"PhoneNumber"},responseBody.body));
    }
    @Test(priority = 1)
    public void ConfirmPhoneNumber_WithNotRealVerifecationCOde(){
        ConfirmAccountModel model = new ConfirmAccountModel("664279","NewExample5@gmail.com","+201152899886");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.ConfirmPhoneNumber_User);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<String> responseBody = res.as(new TypeRef<ResponseModel<String>>() {});
        Assert.assertTrue(400 == responseBody.statusCode,"Status body in body isn't true");
    }
    @Test(priority = 1)
    public void ConfirmPhoneNumber_WithInValidVerifecationCOde(){
        ConfirmAccountModel model = new ConfirmAccountModel("66428","NewExample5@gmail.com","+201152899886");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.ConfirmPhoneNumber_User);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"verificationCode"},responseBody.body));
    }
    @Test(priority = 1)
    public void ConfirmPhoneNumber_WithEmptyVerifecationCOde(){
        ConfirmAccountModel model = new ConfirmAccountModel("","NewExample5@gmail.com","+201152899886");
        Response res = given()
                .contentType(ContentType.JSON)
                .body(model)
                .when()
                .post(ApiForUserConstants.ConfirmPhoneNumber_User);

        Assert.assertEquals(400, res.getStatusCode());
        ResponseModel<SingleErrorModel[]> responseBody = res.as(new TypeRef<ResponseModel<SingleErrorModel[]>>() {});
        Assert.assertTrue(ErrorsCheckers.CheckOnBodyExist(responseBody.body));
        Assert.assertTrue(ErrorsCheckers.CheckOnErrorsFields(new String[]{"verificationCode"},responseBody.body));
    }
}
