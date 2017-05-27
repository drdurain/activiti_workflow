package org.camunda.bpm.example.loanapproval;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.TaskListener;

public class StartSendForm implements ExecutionListener, JavaDelegate, TaskListener
{

	@Override
	public void notify(DelegateExecution delegate) throws Exception
	{
		System.out.println("Execution listener: Event " + delegate.getEventName() + " started");
	}

	@Override
	public void execute(DelegateExecution delegate) throws Exception
	{
		System.out.println("Event executed");
		
	}

	@Override
	public void notify(DelegateTask delegate) 
	{
		System.out.println("Task listener: Event " + delegate.getEventName() + " started");
		delegate.complete();
	}

}
