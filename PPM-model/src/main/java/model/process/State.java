package model.process;

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
    @Column(name = "nr_in_process", nullable = false, insertable = true, updatable = true)
    private short nrInProcess;
    @ManyToOne
    @JoinColumn(name = "proces_id", referencedColumnName = "proces_id", nullable = false)
    private Process process;
    @ManyToOne
    @JoinColumn(name = "next_state_id", referencedColumnName = "state_id", nullable = true)
    private State nextState;


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

    public short getNrInProcess() {
        return nrInProcess;
    }

    public void setNrInProcess(short nrInProcess) {
        this.nrInProcess = nrInProcess;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public State getNextState() {
        return nextState;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }
}

