package client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpGetClient {

	public CloseableHttpResponse httpresponse;

	public CloseableHttpResponse HttpGetCall(String URL) throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(URL);

		httpresponse = httpclient.execute(httpget);

		return httpresponse;

	}

	public CloseableHttpResponse HttpGetCallWithHeaders(String URL, HashMap<String, String> headermap)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(URL);

		for (Map.Entry<String, String> header : headermap.entrySet()) {

			httpget.addHeader(header.getKey(), header.getValue());

		}

		httpresponse = httpclient.execute(httpget);
		
		return httpresponse;

	}

}
