package model.inventory;

import com.google.gson.annotations.Expose;
import model.inventory.enums.ComponentType;
import util.exception.InvalidParentComponentException;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "projects", schema = "public")
public class Project extends Component  {
    //ID

    //BASICS
    @Expose
    @Basic
    @Column(name = "health", nullable = true, insertable = true, updatable = true, length = 1)
    private String health;
    @Expose
    @Basic
    @Column(name = "scope", nullable = true, insertable = true, updatable = true, length = 2)
    private String scope;
    @Expose
    @Basic
    @Column(name = "schedule", nullable = true, insertable = true, updatable = true, length = 2)
    private String schedule;
    @Expose
    @Basic
    @Column(name = "budget", nullable = true, insertable = true, updatable = true, length = 2)
    private String budget;
    @Expose
    @Basic
    @Column(name = "start_date", nullable = true, insertable = true, updatable = true)
    private Date startDate;
    @Expose
    @Basic
    @Column(name = "end_date", nullable = true, insertable = true, updatable = true)
    private Date endDate;
    @Expose
    @Basic
    @Column(name = "deadline_date", nullable = true, insertable = true, updatable = true)
    private Date deadlineDate;

    @Expose
    @Transient
    private final ComponentType componentType = ComponentType.PROJECT;
    //RELATIONS


    public Project() {
    }

    public Project(String code, String name, String customer, String description) {
        super(code, name, customer, description);
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
