package model.inventory;

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
    @Basic
    @Column(name = "health", nullable = true, insertable = true, updatable = true, length = 1)
    private String health;
    @Basic
    @Column(name = "scope", nullable = true, insertable = true, updatable = true, length = 2)
    private String scope;
    @Basic
    @Column(name = "schedule", nullable = true, insertable = true, updatable = true, length = 2)
    private String schedule;
    @Basic
    @Column(name = "budget", nullable = true, insertable = true, updatable = true, length = 2)
    private String budget;
    @Basic
    @Column(name = "planned_start_date", nullable = true, insertable = true, updatable = true)
    private Date plannedStartDate;
    @Basic
    @Column(name = "days_length", nullable = true, insertable = true, updatable = true)
    private Integer daysLength;

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

    public Date getPlannedStartDate() {
        return plannedStartDate;
    }

    public void setPlannedStartDate(Date plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
    }

    public Integer getDaysLength() {
        return daysLength;
    }

    public void setDaysLength(Integer daysLength) {
        this.daysLength = daysLength;
    }


}
