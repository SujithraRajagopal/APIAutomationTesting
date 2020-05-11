package testMethods;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import client.HttpGetClient;
import client.HttpPostClient;
import jsonParsingUtil.JSONParsingUtility;
import jsonUsers.JSONUsers;
import loadProperties.LoadConfigProperties;

public class TestHttpMethods extends LoadConfigProperties {

	LoadConfigProperties loadprop;
	String EndURL;
	String ServiceURL;
	String URI;
	HttpGetClient getclient;
	CloseableHttpResponse closeableresponse;
	HttpPostClient postClient;

	@BeforeMethod
	public void setUp() {
		// call properties
		loadprop = new LoadConfigProperties();

		EndURL = prop.getProperty("EndURL");
		ServiceURL = prop.getProperty("ServiceURL");

		URI = EndURL + ServiceURL;

	}

	@Test(priority = 2)
	public void HttpGetCall() throws ClientProtocolException, IOException {

		getclient = new HttpGetClient();
		closeableresponse = getclient.HttpGetCall(URI);

		// statuscode
		int statuscode = closeableresponse.getStatusLine().getStatusCode();
		System.out.println("The status of GET response is " + statuscode);
		Assert.assertEquals(statuscode, response_code_200);

		// JSONObject and parsing
		String JSONEntity = EntityUtils.toString(closeableresponse.getEntity(), "UTF-8");
		JSONObject responseJSONObject = new JSONObject(JSONEntity);
		System.out.println("The JSONobject of GET response is " + responseJSONObject);

		String per_page = JSONParsingUtility.getValueByJpath(responseJSONObject, "/per_page");
		System.out.println("The per page value is " + per_page);
		Assert.assertEquals(Integer.parseInt(per_page), 6);

		String total = JSONParsingUtility.getValueByJpath(responseJSONObject, "/total");
		System.out.println("The total value is " + total);
		Assert.assertEquals(Integer.parseInt(total), 12);

		String last_name_0 = JSONParsingUtility.getValueByJpath(responseJSONObject, "/data[0]/last_name");
		String id_0 = JSONParsingUtility.getValueByJpath(responseJSONObject, "/data[0]/id");
		String avatar_0 = JSONParsingUtility.getValueByJpath(responseJSONObject, "/data[0]/avatar");
		String first_name_0 = JSONParsingUtility.getValueByJpath(responseJSONObject, "/data[0]/first_name");
		String email_0 = JSONParsingUtility.getValueByJpath(responseJSONObject, "/data[0]/email");

		System.out.println("The last name is " + last_name_0);
		System.out.println("The id is " + id_0);
		System.out.println("The avatar is " + avatar_0);
		System.out.println("The first name is " + first_name_0);
		System.out.println("The email is " + email_0);

		// Header
		Header[] headerarray = closeableresponse.getAllHeaders();

		HashMap<String, String> headermap = new HashMap<String, String>();

		for (Header h : headerarray) {

			headermap.put(h.getName(), h.getValue());

		}

		System.out.println("The header for GET repsonse is " + headermap);

	}

	@Test(priority = 1)
	public void HttpGetCallWithHeaders() throws ClientProtocolException, IOException {

		getclient = new HttpGetClient();

		HashMap<String, String> headermapobj = new HashMap<String, String>();
		headermapobj.put("Content-Type", "application/json");

		closeableresponse = getclient.HttpGetCallWithHeaders(URI, headermapobj);

		// statuscode
		int statuscode = closeableresponse.getStatusLine().getStatusCode();
		System.out.println("The status of GET response is " + statuscode);
		Assert.assertEquals(statuscode, response_code_200);

		// JSONObject and parsing
		String JSONEntity = EntityUtils.toString(closeableresponse.getEntity(), "UTF-8");
		JSONObject responseJSONObject = new JSONObject(JSONEntity);
		System.out.println("The JSONobject of GET response is " + responseJSONObject);

		String per_page = JSONParsingUtility.getValueByJpath(responseJSONObject, "/per_page");
		System.out.println("The per page value is " + per_page);
		Assert.assertEquals(Integer.parseInt(per_page), 6);

		String total = JSONParsingUtility.getValueByJpath(responseJSONObject, "/total");
		System.out.println("The total value is " + total);
		Assert.assertEquals(Integer.parseInt(total), 12);

		String last_name_0 = JSONParsingUtility.getValueByJpath(responseJSONObject, "/data[0]/last_name");
		String id_0 = JSONParsingUtility.getValueByJpath(responseJSONObject, "/data[0]/id");
		String avatar_0 = JSONParsingUtility.getValueByJpath(responseJSONObject, "/data[0]/avatar");
		String first_name_0 = JSONParsingUtility.getValueByJpath(responseJSONObject, "/data[0]/first_name");
		String email_0 = JSONParsingUtility.getValueByJpath(responseJSONObject, "/data[0]/email");

		System.out.println("The last name is " + last_name_0);
		System.out.println("The id is " + id_0);
		System.out.println("The avatar is " + avatar_0);
		System.out.println("The first name is " + first_name_0);
		System.out.println("The email is " + email_0);

		// Header
		Header[] headerarray = closeableresponse.getAllHeaders();

		HashMap<String, String> headermap = new HashMap<String, String>();

		for (Header h : headerarray) {

			headermap.put(h.getName(), h.getValue());

		}

		System.out.println("The header for GET repsonse is " + headermap);

	}

	@Test(priority = 3)
	public void HttpPostCall() throws JsonGenerationException, JsonMappingException, IOException {

		postClient = new HttpPostClient();

		// Header
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");

		// create json
		ObjectMapper mapper = new ObjectMapper();
		JSONUsers users = new JSONUsers("sss", "dev");

		// json object
		mapper.writeValue(new File(
				"C:\\Users\\Sam\\git\\APIAutomationTesting\\RestClientFramework\\src\\main\\java\\jsonUsers\\jsonUsers.data"),
				users);

		// json object to json string
		String userJSonString = mapper.writeValueAsString(users);
		System.out.println(userJSonString);

		closeableresponse = postClient.httpPostCall(URI, userJSonString, headermap);

		// status code
		int statuscode = closeableresponse.getStatusLine().getStatusCode();
		System.out.println(statuscode);

		Assert.assertEquals(statuscode, HttpPostClient.response_201);

		// json object
		String jsonresponseString = EntityUtils.toString(closeableresponse.getEntity(), "UTF-8");
		JSONObject jsonobject = new JSONObject(jsonresponseString);
		System.out.println(jsonobject);

		// json object to java object
		/*
		 * String responsename = JSONParsingUtility.getValueByJpath(jsonobject,
		 * "/name"); System.out.println(responsename); Assert.assertEquals(responsename,
		 * "sss");
		 */

		JSONUsers userresponseobj = mapper.readValue(jsonresponseString, JSONUsers.class);
		System.out.println(userresponseobj);

		System.out.println(users.getName().equals(userresponseobj.getName()));
		System.out.println(users.getJob().equals(userresponseobj.getJob()));

		// header
		Header[] headerarray = closeableresponse.getAllHeaders();

		HashMap<String, String> responseheadermap = new HashMap<String, String>();

		for (Header mapheader : headerarray) {
			responseheadermap.put(mapheader.getName(), mapheader.getValue());
		}
		System.out.println(responseheadermap);

	}

}
