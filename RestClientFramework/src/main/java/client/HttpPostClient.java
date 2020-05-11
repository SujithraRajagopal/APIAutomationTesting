package client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpPostClient {
	
	CloseableHttpClient httpClient;
	CloseableHttpResponse httpresponse;
	
	public static int response_201 = 201;
	
	public CloseableHttpResponse httpPostCall(String URL, String PayloadEntity, HashMap<String, String> Header) throws ClientProtocolException, IOException {
		
		httpClient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(URL);
		
		httppost.setEntity(new StringEntity(PayloadEntity)); //for payload
		
		for(Map.Entry<String, String> headermap: Header.entrySet()) {
			httppost.addHeader(headermap.getKey(), headermap.getValue());
		}
		
		httpresponse = httpClient.execute(httppost);
		
		return httpresponse;
		
	}

}
