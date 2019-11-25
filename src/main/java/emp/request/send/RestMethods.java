package emp.request.send;

import static com.jayway.restassured.RestAssured.given;


import emp.tools.classes.LoggerTool;
import emp.tools.classes.LoggerTool.LOG_LEVEL;

import net.minidev.json.JSONObject;

//Class for calling REST HTTP methods using REST Assured library
public class RestMethods {
	
	/**
	 * POST HTTP Method
	 * @param url Request URL
	 * @param jsonBody Request body
	 * @param statusCode Expected status code
	 * @return Response
	 */
	public String post(final String url, final JSONObject jsonBody, final int statusCode) {
        LoggerTool.log(LOG_LEVEL.INFO, "End Point:: " + url);
        LoggerTool.log(LOG_LEVEL.INFO, "Request::"
                                   + jsonBody.toString());
        final String response = given().body(jsonBody.toJSONString()).when().post(url).then()
                .statusCode(statusCode).extract().asString();
        LoggerTool.log(LOG_LEVEL.DEBUG, "Response:: "
                                   + response.toString());
        return response;
    }
	
	/**
	 * PUT HTTP Method
	 * @param url Request URL
	 * @param jsonBody Request body
	 * @param statusCode expected status code
	 * @return Response
	 */
	public String put(final String url, final JSONObject jsonBody, final int statusCode) {
        LoggerTool.log(LOG_LEVEL.INFO, "End Point:: " + url);
        LoggerTool.log(LOG_LEVEL.INFO, "Request::"
                                   + jsonBody.toString());
        final String response = given().body(jsonBody.toJSONString()).when().put(url).then()
                .statusCode(statusCode).extract().asString();

        LoggerTool.log(LOG_LEVEL.DEBUG, "Response:: "+ response.toString());
        return response;
    }
	
	/**
	 * DELETE HTTP Method
	 * @param url Request URL
	 * @param statusCode Expected status code
	 * @return response
	 */
	public String delete(final String url, final int statusCode) {
        LoggerTool.log(LOG_LEVEL.INFO, "End Point:: " + url);
        final String response = given().delete(url).then().statusCode(statusCode).extract().asString();
        LoggerTool.log(LOG_LEVEL.DEBUG, "Response:: "+ response.toString());
        return response;
    }
	
	/**
	 * GET HTTP Method
	 * @param url request url
	 * @param statusCode Expected status code
	 * @return response
	 */
	public String get(final String url, final int statusCode) {
		LoggerTool.log(LOG_LEVEL.INFO, "End Point:: " + url);
        final String response = given().get(url).then().statusCode(statusCode).extract().asString();
        LoggerTool.log(LOG_LEVEL.DEBUG, "Response::" + response.toString());
        return response;
    }


}
