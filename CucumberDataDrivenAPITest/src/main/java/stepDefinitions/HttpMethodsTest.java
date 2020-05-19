package stepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import jsonParsing.JSONParsingUtility;

public class HttpMethodsTest {

	public CloseableHttpResponse httpresponse;
	CloseableHttpResponse httpresponseWithHeader;

	@Given("^The GET URI is provided$")
	public void setUpMethod() {
		System.out.println("The URI is provided");
	}

	@Then("^Send the URI \"(.*)\"$")
	public CloseableHttpResponse sendHttpGet(String URI) throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(URI);

		httpresponse = httpclient.execute(httpget);

		return httpresponse;

	}

	@Then("^Verify the response$")
	public void verifyResponse() throws ParseException, IOException {

		// status code
		int statusCode = httpresponse.getStatusLine().getStatusCode();
		System.out.println("The status code of Get Response is " + statusCode);

		// JSONObject
		String JSONEntity = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
		JSONObject jsonObj = new JSONObject(JSONEntity);
		System.out.println("The JSON object is " + jsonObj);

		String per_page = JSONParsingUtility.getValueByJpath(jsonObj, "/per_page");
		System.out.println("The per page value is " + per_page);
		Assert.assertEquals(6, Integer.parseInt(per_page));

		// Header
		Header[] headerArray = httpresponse.getAllHeaders();
		HashMap<String, String> headermap = new HashMap<String, String>();
		for (Header h : headerArray) {
			headermap.put(h.getName(), h.getValue());
		}
		System.out.println("The header of response is " + headermap);

	}

	@Given("^The GET URI is given$")
	public void the_GET_URI_is_given() {
		System.out.println("The URI is provided");
	}

	@Then("^Send the URI \"(.*)\" with headers$")
	public CloseableHttpResponse send_the_URI_with_headers(String URI)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(URI);

		HashMap<String, String> header = new HashMap<String, String>();
		for (Map.Entry<String, String> requestHeader : header.entrySet()) {
			httpget.addHeader(requestHeader.getKey(), requestHeader.getValue());
		}

		httpresponseWithHeader = httpclient.execute(httpget);

		return httpresponseWithHeader;

	}

	@Then("^Verify the response for URI with headers$")
	public void verify_the_response_for_URI_with_headers() throws ParseException, IOException {

		// status code
		int statusCode = httpresponseWithHeader.getStatusLine().getStatusCode();
		System.out.println("The status code of Get Response is " + statusCode);

		// JSONObject
		String JSONEntity = EntityUtils.toString(httpresponseWithHeader.getEntity(), "UTF-8");
		JSONObject jsonObj = new JSONObject(JSONEntity);
		System.out.println("The JSON object is " + jsonObj);

		String per_page = JSONParsingUtility.getValueByJpath(jsonObj, "/per_page");
		System.out.println("The per page value is " + per_page);
		Assert.assertEquals(6, Integer.parseInt(per_page));

		// Header
		Header[] headerArray = httpresponseWithHeader.getAllHeaders();
		HashMap<String, String> headermap = new HashMap<String, String>();
		for (Header h : headerArray) {
			headermap.put(h.getName(), h.getValue());
		}
		System.out.println("The header of response is " + headermap);

	}

}
