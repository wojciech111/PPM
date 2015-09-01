package model.inventory;



import model.categorization.CategoryMembership;
import model.categorization.Score;
import model.inventory.enums.ComponentType;
import model.inventory.enums.CustomerType;
import model.organization.Stakeholder;
import model.process.State;


import util.annotation.PortfolioTree;
import util.annotation.UserTree;
import util.exception.InvalidParentComponentException;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "components", schema = "public")
@Inheritance(strategy = InheritanceType.JOINED)
public class Component {
    //ID
    @UserTree
    @PortfolioTree
    @Id
    @SequenceGenerator(name="component_seq", sequenceName="component_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="component_seq")
    @Column(name = "component_id", nullable = false, insertable = true, updatable = true)
    private long componentId;

    //BASICS
    @UserTree
    @PortfolioTree
    @Basic
    @Column(name = "code", nullable = false, insertable = true, updatable = true, length = 10)
    private String code;
    @UserTree
    @PortfolioTree
    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 150)
    private String name;
    @PortfolioTree
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type", nullable = true, insertable = true, updatable = true, length = 2)
    private CustomerType customerType;
    @PortfolioTree
    @Basic
    @Column(name = "customer", nullable = true, insertable = true, updatable = true, length = 40)
    private String customer;
    @PortfolioTree
    @Basic
    @Column(name = "sponsor", nullable = true, insertable = true, updatable = true, length = 40)
    private String sponsor;
    @PortfolioTree
    @Basic
    @Column(name = "manager", nullable = true, insertable = true, updatable = true, length = 40)
    private String manager;
    @PortfolioTree
    @Basic
    @Column(name = "purpose", nullable = true, insertable = true, updatable = true, length = 1500)
    private String purpose;
    @UserTree
    @PortfolioTree
    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 2147483647)
    private String description;
    @PortfolioTree
    @Basic
    @Column(name = "overall_priority", nullable = true, insertable = true, updatable = true)
    private Integer overallPriority;
    @PortfolioTree
    @Basic
    @Column(name = "creation_date", nullable = true, insertable = true, updatable = true)
    private Timestamp creationDate;
    @PortfolioTree
    @Basic
    @Column(name = "created_by", nullable = true, insertable = true, updatable = true, length = 140)
    private String createdBy;
    @PortfolioTree
    @Basic
    @Column(name = "update_date", nullable = true, insertable = true, updatable = true)
    private Timestamp updateDate;
    @PortfolioTree
    @Basic
    @Column(name = "updated_by", nullable = true, insertable = true, updatable = true, length = 140)
    private String updatedBy;

    //RELATIONS
    @PortfolioTree
    @OneToMany(mappedBy = "component", fetch=FetchType.EAGER )
    private Set<CategoryMembership> categoryMemberships = new HashSet<CategoryMembership>();
    @ManyToOne
    @JoinColumn(name = "parent_component_id", referencedColumnName = "component_id", nullable = true, updatable = false)
    private Component parent;
    @PortfolioTree
    @OneToMany(mappedBy = "parent", fetch= FetchType.EAGER, orphanRemoval=true, cascade = CascadeType.ALL)
    private Set<Component> children = new HashSet<Component>();
    @PortfolioTree
    @OneToMany(mappedBy = "component", fetch=FetchType.EAGER, orphanRemoval=true, cascade=CascadeType.ALL)
    private Set<Score> scores = new HashSet<Score>();
    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "state_id", nullable = true,insertable = true, updatable = true)
    private State state;
    @OneToMany(mappedBy = "employee")
    private Set<Stakeholder> stakeholders = new HashSet<Stakeholder>();



    public Component() {
    }

    public Component(String code, String name, String customer, String description) {
        this.code = code;
        this.name = name;
        this.customer = customer;
        this.description = description;
    }

    public Component(String code, String name, CustomerType customerType, String customer,
                     String sponsor, String manager, String purpose, String description,
                     Timestamp creationDate, String createdBy,
                     Timestamp updateDate, String updatedBy,
                     Component parent, State state) {
        this.code = code;
        this.name = name;
        this.customerType = customerType;
        this.customer = customer;
        this.sponsor = sponsor;
        this.manager = manager;
        this.purpose = purpose;
        this.description = description;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.updateDate = updateDate;
        this.updatedBy = updatedBy;
        this.parent = parent;
        this.state = state;
    }

    public long getId() {
        return componentId;
    }

    public void setId(long componentId) {
        this.componentId = componentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

   public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOverallPriority() {
        return overallPriority;
    }

    public void setOverallPriority(Integer overallPriority) {
        this.overallPriority = overallPriority;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }


    public Set<CategoryMembership> getCategoryMemberships() {
        return categoryMemberships;
    }

    public void setCategoryMemberships(Set<CategoryMembership> categoryMemberships) {
        this.categoryMemberships = categoryMemberships;
    }

    public Component getParent() {
        return parent;
    }

    public void setParent(Component parent) throws InvalidParentComponentException {
        if (!this.equals(parent))
            this.parent = parent;
        else
            throw new InvalidParentComponentException("Component can't be child of himself.");
    }

    public Set<Component> getChildren() {
        return children;
    }

    public void setChildren(Set<Component> children) {
        this.children = children;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Set<Stakeholder> getStakeholders() {
        return stakeholders;
    }

    public void setStakeholders(Set<Stakeholder> stakeholders) {
        this.stakeholders = stakeholders;
    }
}
