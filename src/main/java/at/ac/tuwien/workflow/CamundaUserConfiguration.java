package at.ac.tuwien.workflow;

import static org.camunda.bpm.engine.authorization.Authorization.ANY;
import static org.camunda.bpm.engine.authorization.Authorization.AUTH_TYPE_GRANT;
import static org.camunda.bpm.engine.authorization.Groups.CAMUNDA_ADMIN;
import static org.camunda.bpm.engine.authorization.Groups.GROUP_TYPE_WORKFLOW;
import static org.camunda.bpm.engine.authorization.Permissions.ALL;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.authorization.Groups;
import org.camunda.bpm.engine.authorization.Resource;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.persistence.entity.AuthorizationEntity;
import org.camunda.bpm.spring.boot.starter.configuration.impl.AbstractCamundaConfiguration;

import at.ac.tuwien.workflow.model.DefaultUser;

public class CamundaUserConfiguration extends AbstractCamundaConfiguration {

    public void postProcessEngineBuild(ProcessEngine processEngine) {
        final IdentityService identityService = processEngine.getIdentityService();
        final AuthorizationService authorizationService = processEngine.getAuthorizationService();

        // create group
        if (identityService.createGroupQuery().groupId(CAMUNDA_ADMIN).count() == 0) {
            Group camundaAdminGroup = identityService.newGroup(CAMUNDA_ADMIN);
            camundaAdminGroup.setName("camunda BPM Administrators");
            camundaAdminGroup.setType(Groups.GROUP_TYPE_SYSTEM);
            identityService.saveGroup(camundaAdminGroup);
        }

        if (identityService.createGroupQuery().groupId(GROUP_TYPE_WORKFLOW).count() == 0) {
            Group camundaAdminWorkflow = identityService.newGroup(GROUP_TYPE_WORKFLOW);
            camundaAdminWorkflow.setName("camunda BPM Workflow");
            camundaAdminWorkflow.setType(GROUP_TYPE_WORKFLOW);
            identityService.saveGroup(camundaAdminWorkflow);
        }

        // create ADMIN authorizations on all built-in resources
        for (Resource resource : Resources.values()) {
            if (authorizationService.createAuthorizationQuery().groupIdIn(CAMUNDA_ADMIN).resourceType(resource).resourceId(ANY).count() == 0) {
                AuthorizationEntity userAdminAuth = new AuthorizationEntity(AUTH_TYPE_GRANT);
                userAdminAuth.setGroupId(CAMUNDA_ADMIN);
                userAdminAuth.setResource(resource);
                userAdminAuth.setResourceId(ANY);
                userAdminAuth.addPermission(ALL);
                authorizationService.saveAuthorization(userAdminAuth);
            }
        }

        for (DefaultUser u : getUsers()) {
            if (u.getName() != null) {
                User singleResult = identityService.createUserQuery().userId(u.getName()).singleResult();
                if (singleResult != null) {
                    return;
                }

                logger.info("Generating user data");

                User user = identityService.newUser(u.getName());
                user.setFirstName(u.getName());
                user.setLastName(u.getName());
                user.setPassword(u.getPassword());
                user.setEmail(u.getName() + "@localhost");
                identityService.saveUser(user);

                if (u.isAdmin()) {
                    identityService.createMembership(u.getName(), CAMUNDA_ADMIN);
                } else {
                    identityService.createMembership(u.getName(), GROUP_TYPE_WORKFLOW);
                }
            }
        }
    }

    private List<DefaultUser> getUsers() {
        List<DefaultUser> users = new ArrayList<>();
        users.add(new DefaultUser("laura", "laura", true));
        users.add(new DefaultUser("test", "test"));

        return users;
    }
}
