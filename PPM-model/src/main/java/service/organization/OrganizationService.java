package service.organization;

import dao.organization.EmployeeDAO;
import dao.organization.OrganizationDAO;
import dao.organization.StakeholderDAO;
import dao.organization.UserDAO;
import model.inventory.Component;
import model.organization.Employee;
import model.organization.Organization;
import model.organization.Stakeholder;
import model.organization.User;

/**
 * Created by Wojciech on 2015-08-15.
 */
public class OrganizationService implements OrganizationServiceInterface {

    @Override
    public User login(String email, String password) {
        return UserDAO.login(email,password);
    }

    //USER
    @Override
    public User createUser(String username, String password, String email) {
        User user = new User(username,password,email);

        user = UserDAO.save(user);

        return user;
    }

    @Override
    public User getUser(long id) {
        return UserDAO.getById(id);
    }

    @Override
    public User updateUser(User user){
        return UserDAO.update(user);
    }

    @Override
    public void deleteUser(User user) {
        UserDAO.delete(user);
    }

    //EMPLOYEE
    @Override
    public Employee createEmployee(String firstName, String secondName, Organization organization, User user) {
        Employee employee = new Employee(firstName,secondName,organization, user);

        employee = EmployeeDAO.save(employee);

        return employee;
    }

    @Override
    public Employee getEmployee(long id) {
        return EmployeeDAO.getById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee){
        return EmployeeDAO.update(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        EmployeeDAO.delete(employee);
    }
    //ORGANIZATION
    @Override
    public Organization createOrganization(String name, String shortName) {
        Organization organization = new Organization(name,shortName);

        organization = OrganizationDAO.save(organization);

        return organization;
    }

    @Override
    public Organization getOrganization(long id) {
        return OrganizationDAO.getById(id);
    }

    @Override
    public Organization updateOrganization(Organization organization){
        return OrganizationDAO.update(organization);
    }

    @Override
    public void deleteOrganization(Organization organization) {
        OrganizationDAO.delete(organization);
    }
    
    //STAKEHOLDER
    @Override
    public Stakeholder createStakeholder(Employee employee, Component component){
        Stakeholder stakeholder = new Stakeholder(employee,component);
        employee.getStakeholders().add(stakeholder);
        component.getStakeholders().add(stakeholder);
        stakeholder = StakeholderDAO.save(stakeholder);
        return stakeholder;
    }
    @Override
    public Stakeholder updateStakeholder(Stakeholder stakeholder){
        
        stakeholder=StakeholderDAO.update(stakeholder);
        return stakeholder;
    }

    @Override
    public void deleteStakeholder(Stakeholder stakeholder) {
        StakeholderDAO.delete(stakeholder);
        stakeholder.getEmployee().getStakeholders().remove(stakeholder);
        stakeholder.getComponent().getStakeholders().remove(stakeholder);
    }
}
