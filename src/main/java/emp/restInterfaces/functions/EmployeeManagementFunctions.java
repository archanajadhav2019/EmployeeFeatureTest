package emp.restInterfaces.functions;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.TestException;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import emp.json.create.EmployeeJsonCreate;
import emp.request.send.EmployeeRestMethods;
import net.minidev.json.JSONObject;

//Class to perform all employee management functions
public class EmployeeManagementFunctions {
	public static String nameJsonPath="$.name";
	public static String salaryJsonPath="$.salary";
	public static String ageJsonPath="$.age";
	public static String imageJsonPath="$.image";
	public static String employeeNameJsonPath="$.employee_name";
	public static String employeeSalaryJsonPath="$.employee_salary";
	public static String employeeAgeJsonPath="$.employee_age";
	public static String profileImageJsonPath="$.profile_image";
	public static String idJsonPath="$.id";
	public static String empCreateURL="http://dummy.restapiexample.com/api/v1/create";
	public static String empUpdateURL="http://dummy.restapiexample.com/api/v1/update/";
	public static String empGetURL="http://dummy.restapiexample.com/api/v1/employee/";
	public static String empsGetURL="http://dummy.restapiexample.com/api/v1/employees";
	public static String empDeleteURL="http://dummy.restapiexample.com/api/v1/delete/";


	private String empCreateJsonTemplatePath="standardJSON/empCreateJSON.json";
	private String empUpdateJsonTemplatePath="standardJSON/empUpdateJSON.json";

	/**
	 * Method to add new employee
	 * @param fieldsToSend fields to be updated for new employee
	 * @param status Expected status code
	 * @return request and response 
	 */
	public String[] addEmployee(LinkedHashMap<String, Object> fieldsToSend, int status){
		EmployeeJsonCreate json=new EmployeeJsonCreate();
		JSONObject request=json.getJSONObject(getJsonFile(empCreateJsonTemplatePath));
		request=insertValues(request, fieldsToSend);
		String response=new EmployeeRestMethods().post(empCreateURL,request, status);
		return new String[]{response,request.toJSONString()};
	}

	/**
	 * Method to update employee
	 * @param fieldsToSend fields to be updated in JSON request
	 * @param status expected status code
	 * @return request and response
	 */
	public String[] updateEmployee(LinkedHashMap<String, Object> fieldsToSend,String empId, int status){
		EmployeeJsonCreate json=new EmployeeJsonCreate();
		JSONObject request=json.getJSONObject(getJsonFile(empUpdateJsonTemplatePath));
		request=insertValues(request, fieldsToSend);
		String response=new EmployeeRestMethods().put(empUpdateURL+empId,request, status);
		return new String[]{response,request.toJSONString()};
	}

	/**
	 * Method to get employee
	 * @param empId unique employee ID
	 * @param status expected status code
	 * @return response
	 */
	public String getEmployee(String empId, int status){
		return new EmployeeRestMethods().get(empGetURL+empId, status);
	}

	/**
	 * Method to delete employee
	 * @param empId unique employee ID
	 * @param status expected status code
	 * @return response
	 */
	public String deleteEmployee(String empId, int status){
		return new EmployeeRestMethods().delete(empDeleteURL+empId, status);
	}

	/**
	 * Method to get list of employees
	 * @param status expected status code
	 * @return response
	 */
	public String getEmployees(int status){
		return new EmployeeRestMethods().get(empsGetURL, status);
	}


	/**
	 * Method to read JSON template file
	 * @param path location of the file
	 * @return file
	 */
	private File getJsonFile(final String path) {
		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		File file = null;
		try {
			file = new File(classLoader.getResource(path).toURI());
		} catch (final URISyntaxException e) {
			throw new TestException ("Please check path for Brand Json file " + e.toString());
		}
		return file;

	}

	/**
	 * Method to update values in request body from given map of fields
	 * @param json JSON to be updated
	 * @param map map of field and its value
	 * @return updated JSON
	 */
	private JSONObject insertValues(JSONObject json, Map<String, Object> map) {
		if (map != null) {
			for (final Entry<String, Object> param : map.entrySet()) {
				try {
					if (!(param.getValue() == null) && param.getValue().equals("missing")) {
						json = JsonPath.parse(json).delete(param.getKey()).json();
					}else{
					JsonPath.parse(json).set(param.getKey(), param.getValue()).json();
					}
				} catch (final PathNotFoundException e) {
					throw new TestException("Incorrect JOSN path:"+param.getValue());
				}
			}
			return json;
		}else{
			return json;
		}
	}


	/**
	 * Method to read JSON value
	 * @param json json value to be parsed
	 * @param jsonpath path of the json field to verify 
	 * @return read json path value
	 */
	public String getJsonPathValue(String json, String jsonPath) {
		return JsonPath.parse(json).read(jsonPath);
	}

}
