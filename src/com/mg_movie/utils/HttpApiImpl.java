package com.mg_movie.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpApiImpl {

	public String doHttpGet(String httpUrl){
		StringBuffer buffer = new StringBuffer();
		try {
			HttpGet httpRequest = new HttpGet(httpUrl);
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				buffer.append(EntityUtils.toString(httpResponse.getEntity()));
			}
		} catch (Exception e) {
		}
		return buffer.toString();
	}
	
	
}
