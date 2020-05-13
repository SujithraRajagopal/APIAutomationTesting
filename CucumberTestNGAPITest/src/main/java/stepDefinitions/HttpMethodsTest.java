package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

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

	Properties prop;
	String EndURL;
	String ServiceURL;
	String URI;
	CloseableHttpResponse httpresponse;

	@Given("^Actual URI for GET request is provided$")
	public void setUp() throws IOException {

		prop = new Properties();
		FileInputStream ip = new FileInputStream(
				"C:\\Workspace\\CucumberTestNGAPITest\\src\\main\\java\\configProperties\\config.properties");
		prop.load(ip);

		EndURL = prop.getProperty("EndURL");
		ServiceURL = prop.getProperty("ServiceURL");

		URI = EndURL + ServiceURL;
		System.out.println(URI);

	}

	@Then("^Send the GET request$")
	public CloseableHttpResponse sendGetRequest() throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(URI);
		httpresponse = httpclient.execute(httpget);

		return httpresponse;

	}

	@Then("^Verify the GET response$")
	public void verifyGetResponse() throws ParseException, IOException {

		// statuscode

		int statuscode = httpresponse.getStatusLine().getStatusCode();
		System.out.println(statuscode);

		// json
		String JSONEntity = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
		JSONObject jsonobj = new JSONObject(JSONEntity);
		System.out.println(jsonobj);

		String per_page = JSONParsingUtility.getValueByJpath(jsonobj, "/per_page");
		Assert.assertEquals(Integer.parseInt(per_page), 6);

		// Header
		Header[] headerarray = httpresponse.getAllHeaders();

		HashMap<String, String> headermap = new HashMap<String, String>();

		for (Header h : headerarray) {

			headermap.put(h.getName(), h.getValue());

		}
		
		System.out.println(headermap);

	}

}
