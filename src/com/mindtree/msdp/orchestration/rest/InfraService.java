/**
 * 
 */
package com.mindtree.msdp.orchestration.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.util.ResourceBundle;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import com.mindtree.msdp.orchestration.httpconfig.HttpConnection;

/**
 * @author M1018352
 * 
 */
public class InfraService {

	public ResourceBundle bundle = ResourceBundle
			.getBundle("com.mindtree.msdp.orchestration.RestURL");

	private HttpConnection httpConnection = new HttpConnection();
	private HttpMethod method;

	
	
	/**
	 * Post method for orchectrateNewInfra
	 */
	public String orchectrateNewInfra(String tenantName, String projectID,
			String templateName) throws HttpException, IOException,
			InvalidKeyException, NoSuchAlgorithmException {

		method = new PostMethod(String.format(
				bundle.getString("ORCHECTRATE_NEWINFRA_URL"), urlEncode(tenantName),urlEncode(projectID),urlEncode(templateName)));
		
		String xml = httpConnection.executeMethod(method);

		return xml;
	}

	/**
	 * Post method for orchestrateShutDown
	 * 
	 */
	public String orchestrateShutDown(String tenantName, String projectID,
			String template) throws HttpException, IOException,
			InvalidKeyException, NoSuchAlgorithmException {
		method = new PostMethod(String.format(
				bundle.getString("ORCHESTRATE_SHUTDOWN_URL"), urlEncode(tenantName),urlEncode(projectID),urlEncode(template)));
		
		String xml = httpConnection.executeMethod(method);

		return xml;
		
	}

	/**
	 * Post method for orchectrateWakeupInfra
	 * 
	 */
	public String orchectrateWakeupInfra(String tenantName, String projectID,
			String templateName) throws HttpException, IOException,
			InvalidKeyException, NoSuchAlgorithmException {
		
		method = new PostMethod(String.format(
				bundle.getString("ORCHECTRATE_WAKEUP_INFRA_URL"), urlEncode(tenantName),urlEncode(projectID),urlEncode(templateName)));
		
		String xml = httpConnection.executeMethod(method);

		return xml;
		
	}

	/**
	 * Post method for OrchestrateTearDown
	 * 
	 */
	public String OrchestrateTearDown(String tenantName, String projectID,
			String template) throws HttpException, IOException,
			InvalidKeyException, NoSuchAlgorithmException {
		
		method = new PostMethod(String.format(
				bundle.getString("ORCHESTRATE_TEAR_DOWN_URL"), urlEncode(tenantName),urlEncode(projectID),urlEncode(template)));
		
		String xml = httpConnection.executeMethod(method);

		return xml;
	}

	/**
	 * Get method for checkOrchestrationStatus
	 * 
	 * @throws Exception
	 */
	public String checkOrchestrationStatus(String orchestrationID)
			throws Exception {
		HttpMethod method = new GetMethod(String.format(
				bundle.getString("CHECK_ORCHESTRATION_STATUS_URL"),urlEncode(orchestrationID)));
		String xml = httpConnection.executeMethod(method);

		return xml;	

	}

	public String urlEncode(String parameter)
			throws UnsupportedEncodingException {

		String newParameter = URLEncoder.encode(parameter, "UTF-8");

		return newParameter;
	}

}
