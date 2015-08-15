package model.process;

import model.organization.Employee;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Wojciech on 2015-08-12.
 */
@Entity
@Table(name = "decisions", schema = "public")
public class Decision {
    @Id
    @SequenceGenerator(name="decision_seq", sequenceName="decision_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="decision_seq")
    @Column(name = "decision_id", nullable = false, insertable = true, updatable = true)
    private long decisionId;
    @Basic
    @Column(name = "type_of_decision", nullable = false, insertable = true, updatable = true, length = 1)
    private String typeOfDecision;
    @Basic
    @Column(name = "state_of_decision", nullable = false, insertable = true, updatable = true, length = 1)
    private String stateOfDecision;
    @Basic
    @Column(name = "date_of_apperance", nullable = false, insertable = true, updatable = true)
    private Timestamp dateOfApperance;
    @Basic
    @Column(name = "last_update_date", nullable = false, insertable = true, updatable = true)
    private Timestamp lastUpdateDate;
    @Basic
    @Column(name = "date_of_settlement", nullable = false, insertable = true, updatable = true)
    private Timestamp dateOfSettlement;
    @Basic
    @Column(name = "motivation", nullable = true, insertable = true, updatable = true, length = 2147483647)
    private String motivation;
    @ManyToOne
    @JoinColumn(name = "employee_id_who_proposed", referencedColumnName = "employee_id", nullable = false)
    private Employee employeeWhoProposed;


    public long getDecisionId() {
        return decisionId;
    }

    public void setDecisionId(long decisionId) {
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
}
