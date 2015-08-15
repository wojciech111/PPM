package model.organization;

import model.inventory.Component;
import model.organization.pk.StakeholderPK;

import javax.persistence.*;

/**
 * Created by Wojciech on 2015-08-12.
 */
@Entity
@Table(name = "stakeholders", schema = "public")
public class Stakeholder {
    @EmbeddedId
    protected StakeholderPK stakeholderPK;

    @Basic
    @Column(name = "is_key_stakeholder", nullable = true, insertable = true, updatable = true, length = 1)
    private String isKeyStakeholder;
    @Basic
    @Column(name = "is_customer", nullable = true, insertable = true, updatable = true, length = 1)
    private String isCustomer;
    @Basic
    @Column(name = "is_manager", nullable = true, insertable = true, updatable = true, length = 1)
    private String isManager;
    @Basic
    @Column(name = "is_sponsor", nullable = true, insertable = true, updatable = true, length = 1)
    private String isSponsor;
    @Basic
    @Column(name = "is_governing_body", nullable = true, insertable = true, updatable = true, length = 1)
    private String isGoverningBody;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", insertable = false, updatable = false)
    private Employee employee;
    @ManyToOne(optional = false)
    @JoinColumn(name = "component_id", referencedColumnName = "component_id", insertable = false, updatable = false)
    private Component component;

    public Stakeholder() {
    }

    public Stakeholder(Employee employee, Component component) {
        this.stakeholderPK= new StakeholderPK(employee.getId(),component.getId());
        this.employee = employee;
        this.component = component;
    }

    public String getIsKeyStakeholder() {
        return isKeyStakeholder;
    }

    public void setIsKeyStakeholder(String isKeyStakeholder) {
        this.isKeyStakeholder = isKeyStakeholder;
    }


    public String getIsCustomer() {
        return isCustomer;
    }

    public void setIsCustomer(String isCustomer) {
        this.isCustomer = isCustomer;
    }


    public String getIsManager() {
        return isManager;
    }

    public void setIsManager(String isManager) {
        this.isManager = isManager;
    }


    public String getIsSponsor() {
        return isSponsor;
    }

    public void setIsSponsor(String isSponsor) {
        this.isSponsor = isSponsor;
    }


    public String getIsGoverningBody() {
        return isGoverningBody;
    }

    public void setIsGoverningBody(String isGoverningBody) {
        this.isGoverningBody = isGoverningBody;
    }

    public StakeholderPK getStakeholderPK() {
        return stakeholderPK;
    }

    public void setStakeholderPK(StakeholderPK stakeholderPK) {
        this.stakeholderPK = stakeholderPK;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
