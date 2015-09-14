package model.process;

import model.inventory.Component;
import model.organization.Employee;
import model.process.enums.DecisionState;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "state_of_decision", nullable = false, insertable = true, updatable = true, length = 1)
    private DecisionState stateOfDecision;
    //DATES
    @PortfolioTree
    @Basic
    @Column(name = "last_update_date", nullable = true, insertable = true, updatable = true)
    private Timestamp lastUpdateDate;
    @PortfolioTree
    @Basic
    @Column(name = "create_date", nullable = true, insertable = true, updatable = true)
    private Timestamp createDate;
    @PortfolioTree
    @Basic
    @Column(name = "recommendation_date", nullable = true, insertable = true, updatable = true)
    private Timestamp recommendationDate;
    @PortfolioTree
    @Basic
    @Column(name = "approve_date", nullable = true, insertable = true, updatable = true)
    private Timestamp approveDate;
    @PortfolioTree
    @Basic
    @Column(name = "execution_date", nullable = true, insertable = true, updatable = true)
    private Timestamp executionDate;
    @PortfolioTree
    @Basic
    @Column(name = "discard_date", nullable = true, insertable = true, updatable = true)
    private Timestamp discardDate;
    //DATES
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

    public Decision(Component component, State fromState, State toState, DecisionState stateOfDecision, String typeOfDecision, String motivation,
                    Employee employeeWhoProposed, Timestamp createDate, Timestamp lastUpdateDate) {
        this.component = component;
        this.fromState = fromState;
        this.toState = toState;
        this.stateOfDecision = stateOfDecision;
        this.typeOfDecision = typeOfDecision;
        this.motivation = motivation;
        this.employeeWhoProposed = employeeWhoProposed;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
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


    public DecisionState getStateOfDecision() {
        return stateOfDecision;
    }

    public void setStateOfDecision(DecisionState stateOfDecision) {
        this.stateOfDecision = stateOfDecision;
    }


    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getRecommendationDate() {
        return recommendationDate;
    }

    public void setRecommendationDate(Timestamp recommendationDate) {
        this.recommendationDate = recommendationDate;
    }

    public Timestamp getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Timestamp approveDate) {
        this.approveDate = approveDate;
    }

    public Timestamp getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Timestamp executionDate) {
        this.executionDate = executionDate;
    }

    public Timestamp getDiscardDate() {
        return discardDate;
    }

    public void setDiscardDate(Timestamp discardDate) {
        this.discardDate = discardDate;
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
