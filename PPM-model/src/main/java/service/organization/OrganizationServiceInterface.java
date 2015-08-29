package service.organization;

import model.inventory.Component;
import model.organization.Employee;
import model.organization.Organization;
import model.organization.Stakeholder;
import model.organization.User;

/**
 * Created by Wojciech on 2015-08-15.
 */
public interface OrganizationServiceInterface {
    //USER
    User login(String email, String password);

    User createUser(String username, String password, String email);

    User getUser(long id);

    User updateUser(User user);

    void deleteUser(User user);

    //EMPLOYEE
    Employee createEmployee(String firstName, String secondName, Organization organization, User user);

    Employee getEmployee(long id);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    //ORGANIZATION
    Organization createOrganization(String name, String shortName);

    Organization getOrganization(long id);

    Organization updateOrganization(Organization organization);

    void deleteOrganization(Organization organization);

    //STAKEHOLDER
    Stakeholder createStakeholder(Employee employee, Component component);

    Stakeholder updateStakeholder(Stakeholder stakeholder);

    void deleteStakeholder(Stakeholder stakeholder);


}
