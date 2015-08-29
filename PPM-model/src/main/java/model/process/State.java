package model.process;


import model.inventory.enums.OperationType;
import model.process.enums.StateType;
import util.annotation.PortfolioTree;

import javax.persistence.*;

/**
 * Created by Wojciech on 2015-07-04.
 */
@Entity
@Table(name = "states", schema = "public")
public class State {
    @PortfolioTree
    @Id
    @SequenceGenerator(name="state_seq", sequenceName="state_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="state_seq")
    @Column(name = "state_id", nullable = false, insertable = true, updatable = true)
    private long stateId;
    @PortfolioTree
    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 30)
    private String name;
    @PortfolioTree
    @Basic
    @Column(name = "color", nullable = false, insertable = true, updatable = true, length = 6)
    private String color;
    @PortfolioTree
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, insertable = true, updatable = true, length = 1)
    private StateType stateType;
    @PortfolioTree
    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 2147483647)
    private String description;
    @ManyToOne
    @JoinColumn(name = "proces_id", referencedColumnName = "process_id", nullable = false)
    private Process process;
    @PortfolioTree
    @ManyToOne
    @JoinColumn(name = "next_state_id", referencedColumnName = "state_id", nullable = true)
    private State nextState;
    @PortfolioTree
    @ManyToOne
    @JoinColumn(name = "alternative_next_state_id", referencedColumnName = "state_id", nullable = true)
    private State alternativeNextState;

    public State() {
    }

    public State(Process process, String name, StateType stateType, String description) {
        this.process = process;
        this.name = name;
        this.stateType = stateType;
        this.description = description;
    }

    public State(Process process, String name, String description, StateType stateType, String color,
                 State nextState, State alternativeNextState) {
        this.process = process;
        this.name = name;
        this.description = description;
        this.stateType = stateType;
        this.color = color;
        this.nextState = nextState;
        this.alternativeNextState = alternativeNextState;
    }

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

