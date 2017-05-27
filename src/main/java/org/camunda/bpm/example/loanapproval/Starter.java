package org.camunda.bpm.example.loanapproval;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.rest.impl.CamundaRestResources;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class Starter implements InitializingBean
{

	@Autowired
	private RuntimeService runtimeService;
	
	public void afterPropertiesSet() throws Exception 
	{
		runtimeService.startProcessInstanceByKey("loanApproval");
		
	}

	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
		
	}
	
}
