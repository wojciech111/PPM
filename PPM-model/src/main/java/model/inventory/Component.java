package model.inventory;


import model.categorization.CategoryMembership;
import model.categorization.Score;
import model.process.State;
import util.exception.InvalidParentComponentException;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "components", schema = "public")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Component {
    //ID
    @Id
    @SequenceGenerator(name="component_seq", sequenceName="component_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="component_seq")
    @Column(name = "component_id", nullable = false, insertable = true, updatable = true)
    private long componentId;

    //BASICS
    @Basic
    @Column(name = "code", nullable = false, insertable = true, updatable = true, length = 10)
    private String code;
    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 150)
    private String name;
    @Basic
    @Column(name = "customer", nullable = true, insertable = true, updatable = true, length = 50)
    private String customer;
    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 2147483647)
    private String description;
    @Basic
    @Column(name = "overall_priority", nullable = true, insertable = true, updatable = true)
    private Integer overallPriority;

    //RELATIONS
    @OneToMany(mappedBy = "component", fetch=FetchType.EAGER)
    private Set<CategoryMembership> categoryMemberships = new HashSet<CategoryMembership>();
    @ManyToOne
    @JoinColumn(name = "parent_component_id", referencedColumnName = "component_id", nullable = true)
    private Component parent;
    @OneToMany(mappedBy = "parent", fetch=FetchType.EAGER)
    private Collection<Component> children;
    @OneToMany(mappedBy = "component", fetch=FetchType.EAGER)
    private Set<Score> scores = new HashSet<Score>();
    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "state_id", nullable = true,insertable = true, updatable = true)
    private State state;

    public Component() {
    }

    public Component(String code, String name, String customer, String description) {
        this.code = code;
        this.name = name;
        this.customer = customer;
        this.description = description;
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

    public Collection<Component> getChildren() {
        return children;
    }

    public void setChildren(Collection<Component> children) {
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
}
