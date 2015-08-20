/**
 * 
 */
package com.mindtree.orchestration.testing;

import java.util.Scanner;

import com.mindtree.msdp.orchestration.rest.InfraService;



/**
 * @author M1018352
 * 
 */
public class Testing {

	public static void main(String[] args) throws Exception {
		InfraService infraService = new InfraService();
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("Enter method number \n" +
				"1 for orchectrateNewInfra service\n" +
				"2 for orchestrateShutDown service\n" +
				"3 for orchectrateWakeupInfra service\n" +
				"4 for OrchestrateTearDown service\n" +
				"5 for checkOrchestrationStatus service\n");
		int methodNumber=scanner.nextInt();
		
		if(methodNumber==1){
			System.out.println("Enter tenantName ");
			String tenantName=scanner.next();
			
			System.out.println("Enter projectID ");
			String projectID=scanner.next();

			System.out.println("Enter TemplateName ");
			String templateName=scanner.next();
			
				System.out.println("----------------orchectrateNewInfra service---------------\n");
				infraService.orchectrateNewInfra(tenantName,projectID,templateName);
				System.out.println("\n\n");
			
			
			
			
		}
		
		if(methodNumber==2){
			System.out.println("Enter tenantName ");
			String tenantName=scanner.next();
			
			System.out.println("Enter projectID ");
			String projectID=scanner.next();

			System.out.println("Enter TemplateName ");
			String template=scanner.next();

				System.out.println("----------------orchestrateShutDown service---------------\n");
				infraService.orchestrateShutDown(tenantName,projectID,template);
				System.out.println("\n\n");
			
			
		
			
		}
		if(methodNumber==3){
			System.out.println("Enter tenantName ");
			String tenantName=scanner.next();
			
			System.out.println("Enter projectID ");
			String projectID=scanner.next();

			System.out.println("Enter TemplateName ");
			String templateName=scanner.next();
				System.out.println("----------------orchectrateWakeupInfra service---------------");
				infraService.orchectrateWakeupInfra(tenantName,projectID,templateName);
				System.out.println("\n\n");
		
			
		}
		
		if(methodNumber==4){
			System.out.println("Enter tenantName ");
			String tenantName=scanner.next();
			
			System.out.println("Enter projectID ");
			String projectID=scanner.next();

			System.out.println("Enter TemplateName ");
			String template=scanner.next();
				System.out.println("----------------OrchestrateTearDown service---------------");
				infraService.OrchestrateTearDown(tenantName,projectID,template);
				System.out.println("\n\n");
			
			
			
		}
		
		if(methodNumber==5){
			System.out.println("Enter orchestration status id ");
		
				String orchestrationId=scanner.next();
				
				
				System.out.println("----------------checkOrchestrationStatus service---------------");
				infraService.checkOrchestrationStatus(orchestrationId);
				System.out.println("\n\n");
			
			
		}
	}
}
