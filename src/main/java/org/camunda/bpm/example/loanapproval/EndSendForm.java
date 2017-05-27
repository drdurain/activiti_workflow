package org.camunda.bpm.example.loanapproval;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.TaskListener;

public class EndSendForm implements ExecutionListener, JavaDelegate, TaskListener
{

	@Override
	public void notify(DelegateExecution delegate) throws Exception 
	{
		System.out.println("Execution listener: Task " + delegate.getEventName() + " ended");
		
	}

	@Override
	public void execute(DelegateExecution delegate) throws Exception 
	{
		System.out.println("Task executed");
		
	}

	@Override
	public void notify(DelegateTask delegate)
	{
		System.out.println("Task listener: Task " + delegate.getEventName() + " ended");
		
	}

}
