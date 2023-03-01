package org.example.helpers.api;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
//import lombok.Getter;
//import lombok.Setter;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

//@Getter
//@Setter
public class BaseClient {
    public ResponseSpecification resp200 = expect().statusCode(200);
    private String uri;
    protected String token;
    protected boolean needAuth;

    static {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.registerParser(ContentType.TEXT.toString(), Parser.JSON);
    }

    public BaseClient(String uri) {
        this.uri = uri;
    }

    public RequestSpecification getReq() {

        return given()
                .baseUri(uri)
                .contentType(ContentType.JSON)
                .filter(new AllureRestAssured())
//                .log().all()
                .then()
//                .log().all()
                .request();
    }

    public RequestSpecification getReqWithApiKey() {
        if (needAuth) {
            return getReq().auth().oauth2(token);
        } else {
            return getReq();
        }
    }

    @Step("get {endpoint}")
    public Response get(String endpoint) {
        return getReqWithApiKey()
                .expect()
                .when()
                .get(endpoint);
    }

    @Step("get {endpoint}")
    public Response get(String endpoint, Map<String, ?> queryParams, Object... pathParams) {
        return getReqWithApiKey()
                .log().all()
                .params(queryParams)
                .expect()
                .log().all()
                .when()
                .get(endpoint, pathParams);
    }

    @Step("get {endpoint}")
    public Response get(ResponseSpecification resp, String endpoint) {
        return getReqWithApiKey()
                .expect()
                .spec(resp)
                .when()
                .get(endpoint);
    }

    @Step("get {endpoint}")
    public Response get(ResponseSpecification resp, String endpoint, Object... pathParams) {
        return getReqWithApiKey()
                .expect()
                .spec(resp)
                .when()
                .get(endpoint, pathParams);
    }

    @Step("get {endpoint}")
    public Response get(ResponseSpecification resp, String endpoint, Map<String, ?> queryParams, Object... pathParams) {
        return getReqWithApiKey()
                .params(queryParams)
                .expect()
                .spec(resp)
                .when()
                .get(endpoint, pathParams);
    }

    @Step("post {endpoint}")
    public Response post(String endpoint, Object body) {
        return getReqWithApiKey()
                .body(body)
                .expect()
                .when()
                .post(endpoint);
    }

    @Step("post {endpoint}")
    public Response post(Object body, ResponseSpecification resp, String endpoint) {
        return getReqWithApiKey()
                .body(body)
                .expect()
                .spec(resp)
                .when()
                .post(endpoint);
    }

    @Step("postWithoutBody {endpoint}")
    public Response postWithoutBody(ResponseSpecification resp, String endpoint) {
        return getReqWithApiKey()
                .expect()
                .spec(resp)
                .when()
                .post(endpoint);
    }

    @Step("postWithoutBody {endpoint}")
    public Response postWithoutBody(ResponseSpecification resp, String endpoint, Object... pathParams) {
        return getReqWithApiKey()
                .expect()
                .spec(resp)
                .when()
                .post(endpoint, pathParams);
    }

    @Step("post {endpoint}")
    public Response post(ResponseSpecification resp, String endpoint, Object body, Object... params) {
        return getReqWithApiKey()
                .body(body)
                .expect()
                .spec(resp)
                .when()
                .post(endpoint, params);
    }

    @Step("post {endpoint}")
    public Response post(String endpoint, Object body, Object... params) {
        return getReqWithApiKey()
                .body(body)
                .expect()
                .when()
                .post(endpoint, params);
    }

    @Step("post {endpoint}")
    public Response postWithImage(ResponseSpecification resp,
                                  String endpoint,
                                  String imagePath,
                                  Integer targetType,
                                  long time,
                                  String targetUID) {
        return getReqWithApiKey()
                .contentType("multipart/form-data")
                .multiPart("target_uid", targetUID)
                .multiPart("target_type", targetType)
                .multiPart("timestamp", time)
                .multiPart("body", new File(imagePath), "image/*")
                .expect()
                .spec(resp)
                .when()
                .post(endpoint);
    }

    @Step("post {endpoint}")
    public Response postFile(ResponseSpecification resp, String endpoint, String filePath, String mimeType) {
        return getReqWithApiKey()
                .contentType("multipart/form-data")
                .multiPart("file", new File(filePath), mimeType)
                .expect()
                .spec(resp)
                .when()
                .post(endpoint);
    }

    @Step("post {endpoint}")
    public Response postFile(ResponseSpecification resp, String endpoint, String filePath, String mimeType, Object... pathParams) {
        return getReqWithApiKey()
                .contentType("multipart/form-data").log().all()
                .multiPart("template", new File(filePath), mimeType)
                .expect()
                .spec(resp)
                .when()
                .post(endpoint, pathParams);
    }

    @Step("post {endpoint}")
    public Response postInvalidFile(ResponseSpecification resp, String endpoint, String filePath) {
        return getReqWithApiKey()
                .contentType("multipart/form-data")
                .multiPart("file", new File(filePath))
                .expect()
                .spec(resp)
                .when()
                .post(endpoint);
    }

    @Step("delete {endpoint}")
    public Response delete(ResponseSpecification resp, String endpoint, Object... pathParams) {
        return getReqWithApiKey()
                .expect()
                .spec(resp)
                .when()
                .delete(endpoint, pathParams);
    }

    public Response delete(String endpoint, Object... pathParams) {
        return getReqWithApiKey()
                .expect()
                .when()
                .delete(endpoint, pathParams);
    }

    @Step("delete {endpoint}")
    public Response deleteWithBody(ResponseSpecification resp, String endpoint, String body, Object... pathParams) {
        return getReqWithApiKey()
                .body(body)
                .expect()
                .spec(resp)
                .when()
                .delete(endpoint, pathParams);
    }

    @Step("put {endpoint}")
    public Response put(Object body, ResponseSpecification resp, String endpoint) {
        return getReqWithApiKey()
                .body(body)
                .expect()
                .spec(resp)
                .when()
                .put(endpoint);
    }

    @Step("put {endpoint}")
    public Response put(ResponseSpecification resp, String endpoint, Object body) {
        return getReqWithApiKey()
                .body(body)
                .expect()
                .spec(resp)
                .when()
                .put(endpoint);
    }

    @Step("put {endpoint}")
    public Response put(ResponseSpecification resp, String endpoint, Object body, Object... pathParams) {
        return getReqWithApiKey()
                .body(body)
                .expect()
                .spec(resp)
                .when()
                .put(endpoint, pathParams);
    }

    @Step("patch {endpoint}")
    public Response patch(ResponseSpecification resp, String endpoint, Object body) {
        return getReqWithApiKey()
                .body(body)
                .expect()
                .spec(resp)
                .when()
                .patch(endpoint);
    }

    @Step("patch {endpoint}")
    public Response patch(ResponseSpecification resp, String endpoint, Object body, Object... pathParams) {
        return getReqWithApiKey()
                .body(body)
                .expect()
                .spec(resp)
                .when()
                .patch(endpoint, pathParams);
    }
}