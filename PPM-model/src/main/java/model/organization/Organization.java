package model.organization;

import model.inventory.Portfolio;
import util.annotation.UserTree;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wojciech on 2015-08-12.
 */
@Entity
@Table(name = "organizations", schema = "public")
public class Organization {
    @UserTree
    @Id
    @SequenceGenerator(name="organization_seq", sequenceName="organization_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="organization_seq")
    @Column(name = "organization_id", nullable = false, insertable = true, updatable = true)
    private long organizationId;
    @UserTree
    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 150)
    private String name;
    @UserTree
    @Basic
    @Column(name = "short_name", nullable = false, insertable = true, updatable = true, length = 30)
    private String shortName;
    @UserTree
    @OneToMany(mappedBy = "organization", fetch=FetchType.EAGER )
    private Set<Portfolio> portfolios = new HashSet<Portfolio>();
    @OneToMany(mappedBy = "organization")
    private Set<Employee> employees = new HashSet<Employee>();

    public Organization() {
    }

    public Organization(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public long getId() {
        return organizationId;
    }

    public void setId(long organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(Set<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }
}
