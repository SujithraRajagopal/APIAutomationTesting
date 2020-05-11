package testMethods;

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

import client.HttpGetClient;
import jsonParsingUtil.JSONParsingUtility;
import loadProperties.LoadConfigProperties;

public class TestGetMethod extends LoadConfigProperties {

	LoadConfigProperties loadprop;
	String EndURL;
	String ServiceURL;
	String URI;
	HttpGetClient getclient;
	CloseableHttpResponse closeableresponse;

	@BeforeMethod
	public void setUp() {
		// call properties
		loadprop = new LoadConfigProperties();

		EndURL = prop.getProperty("EndURL");
		ServiceURL = prop.getProperty("ServiceURL");

		URI = EndURL + ServiceURL;

	}

	@Test(priority=2)
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

	@Test(priority=1)
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

}
