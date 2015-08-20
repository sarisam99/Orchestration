/**
 * 
 */
package com.mindtree.msdp.orchestration.httpconfig;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.ProxyHost;

import com.mindtree.msdp.orchestration.tokengeneration.TokenGeneration;


/**
 * @author M1018352
 * 
 */
public class HttpConnection {

	private HttpClient httpClient =new HttpClient();
	private HostConfiguration hconfig =new HostConfiguration();


	/**
	 * @param method
	 * @return
	 * @throws IOException
	 * @throws HttpException
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public String executeMethod(HttpMethod method) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		hconfig.setProxyHost(new ProxyHost("172.22.218.218", 8085));
		
		httpClient.setHostConfiguration(hconfig);
		
		
		
		String token = TokenGeneration.getToken();

		method.setRequestHeader("Content-Language", "en-US");
		method.setRequestHeader("Authorization", token);
		method.setRequestHeader("Content-Type", "Text/XML");
		
		int responseCode = httpClient.executeMethod(method);
		
		String xml = method.getResponseBodyAsString();

		System.out.println("Response from service URL is \n" + xml);
		System.out.println("\nresponse code " + responseCode);
	
		if(method.getName().equals("GET"))
		if (responseCode >= 200 && responseCode < 300 ) {
			System.out.println("GET CALL WAS SUCCESSFUL");
		} else {
			System.out.println("GET CALL WAS UNSUCCESSFUL");

		}
		else if (method.getName().equals("POST")) {
			if (responseCode >= 200 && responseCode < 300 ) {
				System.out.println("POST CALL WAS SUCCESSFUL");
			} else {
				System.out.println("POST CALL WAS UNSUCCESSFUL");

			}
		}
		return xml;

	}


	/**
	 * @param responseCode
	 * @return
	 */
	public boolean getValue(String xml) {
		
		if(xml.contains("true")){
			return true;
		}
		return false;
	}

}
