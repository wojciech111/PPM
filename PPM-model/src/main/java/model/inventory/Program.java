package model.inventory;


import model.inventory.enums.ComponentType;
import model.inventory.enums.CustomerType;
import model.process.State;
import util.annotation.PortfolioTree;
import util.exception.InvalidParentComponentException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "programs", schema = "public")
public class Program extends Component  {
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
    @Basic
    @Column(name = "cost_of_start", nullable = true, insertable = true, updatable = true, precision = 2)
    private BigDecimal costOfStart;
    @PortfolioTree
    @Basic
    @Column(name = "cost_of_stop", nullable = true, insertable = true, updatable = true, precision = 2)
    private BigDecimal costOfStop;
    @PortfolioTree
    @Basic
    @Column(name = "cost_of_restart", nullable = true, insertable = true, updatable = true, precision = 2)
    private BigDecimal costOfRestart;
    @PortfolioTree
    @Basic
    @Column(name = "cost_of_close", nullable = true, insertable = true, updatable = true, precision = 2)
    private BigDecimal costOfClose;

    @PortfolioTree
    @Transient
    private final ComponentType componentType = ComponentType.PROGRAM;

    //RELATIONS


    public Program() {
    }

    public Program(String code, String name, String customer, String description) {
        super(code, name, customer, description);
    }

    public Program(String code, String name, CustomerType customerType,
                   String customer, String sponsor, String manager,
                   String purpose, String description,
                   Timestamp creationDate, String createdBy, Timestamp updateDate, String updatedBy,
                   Component parent, State state,
                   String health, String scope, String schedule, String budget,
                   Date startDate, Date endDate, Date deadlineDate,
                   BigDecimal costOfStart, BigDecimal costOfStop,
                   BigDecimal costOfRestart, BigDecimal costOfClose) {
        super(code, name, customerType, customer, sponsor, manager, purpose, description, creationDate, createdBy, updateDate, updatedBy, parent, state);
        this.health = health;
        this.scope = scope;
        this.schedule = schedule;
        this.budget = budget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deadlineDate = deadlineDate;
        this.costOfStart = costOfStart;
        this.costOfStop = costOfStop;
        this.costOfRestart = costOfRestart;
        this.costOfClose = costOfClose;
    }

    @Override
    public void setParent(Component parent) throws InvalidParentComponentException {
        if ( parent instanceof Portfolio || parent instanceof Program)
            super.setParent(parent);
        else
            throw new InvalidParentComponentException("Program can only be child of Portfolio or Program.");
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

    public BigDecimal getCostOfStart() {
        return costOfStart;
    }

    public void setCostOfStart(BigDecimal costOfStart) {
        this.costOfStart = costOfStart;
    }

    public BigDecimal getCostOfStop() {
        return costOfStop;
    }

    public void setCostOfStop(BigDecimal costOfStop) {
        this.costOfStop = costOfStop;
    }

    public BigDecimal getCostOfRestart() {
        return costOfRestart;
    }

    public void setCostOfRestart(BigDecimal costOfRestart) {
        this.costOfRestart = costOfRestart;
    }

    public BigDecimal getCostOfClose() {
        return costOfClose;
    }

    public void setCostOfClose(BigDecimal costOfClose) {
        this.costOfClose = costOfClose;
    }
}
