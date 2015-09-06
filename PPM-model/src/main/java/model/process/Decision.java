package model.process;

import model.inventory.Component;
import model.organization.Employee;
import util.annotation.PortfolioTree;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Wojciech on 2015-08-12.
 */
@Entity
@Table(name = "decisions", schema = "public")
public class Decision {
    @PortfolioTree
    @Id
    @SequenceGenerator(name="decision_seq", sequenceName="decision_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="decision_seq")
    @Column(name = "decision_id", nullable = false, insertable = true, updatable = true)
    private long decisionId;
    @PortfolioTree
    @Basic
    @Column(name = "type_of_decision", nullable = false, insertable = true, updatable = true, length = 1)
    private String typeOfDecision;
    @PortfolioTree
    @Basic
    @Column(name = "state_of_decision", nullable = false, insertable = true, updatable = true, length = 1)
    private String stateOfDecision;
    @PortfolioTree
    @Basic
    @Column(name = "date_of_apperance", nullable = true, insertable = true, updatable = true)
    private Timestamp dateOfApperance;
    @PortfolioTree
    @Basic
    @Column(name = "last_update_date", nullable = true, insertable = true, updatable = true)
    private Timestamp lastUpdateDate;
    @PortfolioTree
    @Basic
    @Column(name = "date_of_settlement", nullable = true, insertable = true, updatable = true)
    private Timestamp dateOfSettlement;
    @PortfolioTree
    @Basic
    @Column(name = "motivation", nullable = true, insertable = true, updatable = true, length = 2147483647)
    private String motivation;

    @PortfolioTree
    @ManyToOne
    @JoinColumn(name = "employee_id_who_proposed", referencedColumnName = "employee_id", nullable = true)
    private Employee employeeWhoProposed;
    @PortfolioTree
    @ManyToOne(optional = false)
    @JoinColumn(name = "from_state_id", referencedColumnName = "state_id", nullable = true,insertable = true, updatable = true)
    private State fromState;
    @PortfolioTree
    @ManyToOne(optional = false)
    @JoinColumn(name = "to_state_id", referencedColumnName = "state_id", nullable = true,insertable = true, updatable = true)
    private State toState;
    @ManyToOne(optional = false)
    @JoinColumn(name = "component_id", referencedColumnName = "component_id", insertable = true, updatable = false)
    private Component component;

    public Decision() {
    }

    public Decision(Component component, State fromState, State toState, String stateOfDecision, String typeOfDecision, String motivation,
                    Employee employeeWhoProposed, Timestamp dateOfApperance, Timestamp lastUpdateDate, Timestamp dateOfSettlement) {
        this.component = component;
        this.fromState = fromState;
        this.toState = toState;
        this.stateOfDecision = stateOfDecision;
        this.typeOfDecision = typeOfDecision;
        this.motivation = motivation;
        this.employeeWhoProposed = employeeWhoProposed;
        this.dateOfApperance = dateOfApperance;
        this.lastUpdateDate = lastUpdateDate;
        this.dateOfSettlement = dateOfSettlement;
    }

    public long getId() {
        return decisionId;
    }

    public void setId(long decisionId) {
        this.decisionId = decisionId;
    }


    public String getTypeOfDecision() {
        return typeOfDecision;
    }

    public void setTypeOfDecision(String typeOfDecision) {
        this.typeOfDecision = typeOfDecision;
    }


    public String getStateOfDecision() {
        return stateOfDecision;
    }

    public void setStateOfDecision(String stateOfDecision) {
        this.stateOfDecision = stateOfDecision;
    }


    public Timestamp getDateOfApperance() {
        return dateOfApperance;
    }

    public void setDateOfApperance(Timestamp dateOfApperance) {
        this.dateOfApperance = dateOfApperance;
    }


    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }


    public Timestamp getDateOfSettlement() {
        return dateOfSettlement;
    }

    public void setDateOfSettlement(Timestamp dateOfSettlement) {
        this.dateOfSettlement = dateOfSettlement;
    }


    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }



    public Employee getEmployeeWhoProposed() {
        return employeeWhoProposed;
    }

    public void setEmployeeWhoProposed(Employee employeeWhoProposed) {
        this.employeeWhoProposed = employeeWhoProposed;
    }

    public State getFromState() {
        return fromState;
    }

    public void setFromState(State fromState) {
        this.fromState = fromState;
    }

    public State getToState() {
        return toState;
    }

    public void setToState(State toState) {
        this.toState = toState;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}
