/**
 * 
 */
package com.mindtree.msdp.orchestration.tokengeneration;

/**
 * @author M1017429
 *
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class TokenGeneration {
	public static String getToken() throws NoSuchAlgorithmException,
			InvalidKeyException, IOException {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(
				"https://msdpazurecloudpractice.accesscontrol.windows.net/Wrapv0.9");
		try {

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			/*
			 * nameValuePairs.add(new BasicNameValuePair("wrap_name",
			 * "PossServiceIdentities")); nameValuePairs.add(new
			 * BasicNameValuePair("wrap_password",
			 * "voOVBCpVFR2IdarCP3uqQhI6PKkEx2AqMcqpnZjtvfY="));
			 */
			nameValuePairs.add(new BasicNameValuePair("wrap_scope",
					"http://c1ml05792.mindtree.com:8080/maas/"));
			nameValuePairs.add(new BasicNameValuePair("wrap_assertion_format",
					"SWT"));

			/*
			 * MessageDigest digest = MessageDigest.getInstance("SHA256");
			 * digest
			 * .update("voOVBCpVFR2IdarCP3uqQhI6PKkEx2AqMcqpnZjtvfY=".getBytes
			 * ()); digest.update(input)
			 */
			System.out.println("Default Charset=" + Charset.defaultCharset());
			Mac mac = Mac.getInstance("HmacSHA256");
			// System.out.println(Base64.decodeBase64("voOVBCpVFR2IdarCP3uqQhI6PKkEx2AqMcqpnZjtvfY=").length);
			mac.init(new SecretKeySpec(
					Base64.decodeBase64("VgMwgQLfV7nJh10OX/Txnndg71tjXE+a31Fp1KoyoLo="),
					"HmacSHA256"));
			byte[] encode = mac.doFinal("Issuer=MSDPPossServiceIdentities"
					.getBytes());
			String code = new String(Base64.encodeBase64(encode), "ASCII");
			
			
			//System.out.println(code);
			
			nameValuePairs.add(new BasicNameValuePair("wrap_assertion",
					"Issuer=MSDPPossServiceIdentities&HMACSHA256="
							+ URLEncoder.encode(code)));

			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpHost proxy = new HttpHost("172.22.218.218", 8085);
			client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);

			HttpResponse response = client.execute(post);
			//System.out.println("response..............." + response);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			
			//System.out.println(response.getStatusLine().getStatusCode());
			
			String line = "";
			String decodedValue = null;
			while ((line = rd.readLine()) != null) {
				
				//System.out.println("line.........." + line);
				
				decodedValue = URLDecoder.decode(line, "UTF-8");
				// System.out.println("decodedvalue is ..........."+decodedValue);
				// if (line.startsWith("Auth=")) {
				// String key = line.substring(5);
				// System.out.println("key........."+key);
				// do something with the key
				// }
				 
			}
			String token =decodedValue;
			token = token.replace("wrap_access_token=", "bearer ");

			System.out.println("\nTOKEN VALUE =  " + token + "\n");
			return token;
		} catch (IOException e) {
			System.out.println("in catch... unable to hit the service");
			e.printStackTrace();
			throw new IOException();
		}
	
	}
}