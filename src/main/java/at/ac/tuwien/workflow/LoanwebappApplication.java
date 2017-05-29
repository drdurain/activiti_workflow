package at.ac.tuwien.workflow;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.spring.boot.starter.SpringBootProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.ProcessApplicationStartedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.util.Assert;

@SpringBootApplication
public class LoanwebappApplication extends SpringBootProcessApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanwebappApplication.class, args);
    }

    private final Logger logger = LoggerFactory.getLogger(LoanwebappApplication.class);

    @Autowired
    private RepositoryService repositoryService;

    @Bean
    public ProcessEnginePlugin userConfiguration() {
        return new CamundaUserConfiguration();
    }

    @EventListener
    public void notify(final ProcessApplicationStartedEvent unused) {
        final ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("Sample").singleResult();

        logger.info("Found deployed process: {}", processDefinition);
        Assert.notNull(processDefinition, "process 'Sample' should be deployed!");
    }
}
