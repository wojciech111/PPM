package model.organization;

import model.process.Decision;
import util.annotation.PortfolioTree;
import util.annotation.UserTree;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wojciech on 2015-08-12.
 */
@Entity
@Table(name = "employees", schema = "public")
public class Employee {
    @PortfolioTree
    @UserTree
    @Id
    @SequenceGenerator(name="employee_seq", sequenceName="employee_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employee_seq")
    @Column(name = "employee_id", nullable = false, insertable = true, updatable = true)
    private long employeeId;
    @PortfolioTree
    @UserTree
    @Basic
    @Column(name = "first_name", nullable = false, insertable = true, updatable = true, length = 30)
    private String firstName;
    @PortfolioTree
    @UserTree
    @Basic
    @Column(name = "second_name", nullable = false, insertable = true, updatable = true, length = 30)
    private String secondName;
    @PortfolioTree
    @Basic
    @Column(name = "email", nullable = true, insertable = true, updatable = true, length = 100)
    private String email;
    @OneToMany(mappedBy = "employeeWhoProposed")
    private Set<Decision> decisions = new HashSet<Decision>();
    @UserTree
    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "organization_id", nullable = false)
    private Organization organization;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
    @OneToMany(mappedBy = "employee")
    private Set<Stakeholder> stakeholders = new HashSet<Stakeholder>();

    public Employee() {
    }

    public Employee(String firstName, String secondName,Organization organization, User user) {
        this.organization = organization;
        this.secondName = secondName;
        this.firstName = firstName;
        this.user = user;
    }

    public long getId() {
        return employeeId;
    }

    public void setId(long employeeId) {
        this.employeeId = employeeId;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Decision> getDecisions() {
        return decisions;
    }

    public void setDecisions(Set<Decision> decisions) {
        this.decisions = decisions;
    }

    public Set<Stakeholder> getStakeholders() {
        return stakeholders;
    }

    public void setStakeholders(Set<Stakeholder> stakeholders) {
        this.stakeholders = stakeholders;
    }
}
