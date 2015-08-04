package model.process;

import com.google.gson.annotations.Expose;
import model.inventory.enums.OperationType;
import model.process.enums.StateType;

import javax.persistence.*;

/**
 * Created by Wojciech on 2015-07-04.
 */
@Entity
@Table(name = "states", schema = "public")
public class State {
    @Id
    @Column(name = "state_id", nullable = false, insertable = true, updatable = true)
    private long stateId;
    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 30)
    private String name;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, insertable = true, updatable = true, length = 1)
    private StateType stateType;
    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 2147483647)
    private String description;
    @ManyToOne
    @JoinColumn(name = "proces_id", referencedColumnName = "process_id", nullable = false)
    private Process process;
    @ManyToOne
    @JoinColumn(name = "next_state_id", referencedColumnName = "state_id", nullable = true)
    private State nextState;
    @ManyToOne
    @JoinColumn(name = "alternative_next_state_id", referencedColumnName = "state_id", nullable = true)
    private State alternativeNextState;

    public long getId() {
        return stateId;
    }

    public void setId(long stateId) {
        this.stateId = stateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StateType getStateType() {
        return stateType;
    }

    public void setStateType(StateType stateType) {
        this.stateType = stateType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public State getAlternativeNextState() {
        return alternativeNextState;
    }

    public void setAlternativeNextState(State alternativeNextState) {
        this.alternativeNextState = alternativeNextState;
    }

    public State getNextState() {
        return nextState;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }
}

