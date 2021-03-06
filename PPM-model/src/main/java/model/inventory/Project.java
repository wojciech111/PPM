package model.inventory;


import model.inventory.enums.ComponentType;
import model.inventory.enums.CustomerType;
import model.process.State;
import model.process.Process;

import util.annotation.PortfolioTree;
import util.exception.InvalidParentComponentException;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "projects", schema = "public")
public class Project extends Component  {
    //ID

    //BASICS
    @PortfolioTree
    @Basic
    @Column(name = "health", nullable = true, insertable = true, updatable = true, length = 1)
    private String health;
    @PortfolioTree
    @Basic
    @Column(name = "scope", nullable = true, insertable = true, updatable = true, length = 2)
    private String scope;
    @PortfolioTree
    @Basic
    @Column(name = "schedule", nullable = true, insertable = true, updatable = true, length = 2)
    private String schedule;
    @PortfolioTree
    @Basic
    @Column(name = "budget", nullable = true, insertable = true, updatable = true, length = 2)
    private String budget;
    @PortfolioTree
    @Basic
    @Column(name = "start_date", nullable = true, insertable = true, updatable = true)
    private Date startDate;
    @PortfolioTree
    @Basic
    @Column(name = "end_date", nullable = true, insertable = true, updatable = true)
    private Date endDate;
    @PortfolioTree
    @Basic
    @Column(name = "deadline_date", nullable = true, insertable = true, updatable = true)
    private Date deadlineDate;

    @PortfolioTree
    @Transient
    private final ComponentType componentType = ComponentType.PROJECT;
    //RELATIONS


    public Project() {
    }

    public Project(String code, String name, String customer, String description) {
        super(code, name, customer, description);
    }

    public Project(String code, String name, CustomerType customerType,
                   String customer, String sponsor, String manager,
                   String purpose, String description,
                   Timestamp creationDate, String createdBy, Timestamp updateDate, String updatedBy,
                   Component parent, State state,Process process,
                   String health, String scope, String schedule, String budget,
                   Date startDate, Date endDate, Date deadlineDate) {
        super(code, name, customerType, customer, sponsor, manager, purpose, description, creationDate, createdBy, updateDate, updatedBy, parent, state,process);
        this.health = health;
        this.scope = scope;
        this.schedule = schedule;
        this.budget = budget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deadlineDate = deadlineDate;
    }

    @Override
    public void setParent(Component parent) throws InvalidParentComponentException {
        if ( parent instanceof Portfolio || parent instanceof Program)
            super.setParent(parent);
        else
            throw new InvalidParentComponentException("Project can only be child of Portfolio or Program.");
    }
    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

   public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }
}
