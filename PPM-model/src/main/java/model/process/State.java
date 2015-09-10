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
    @Column(name = "color_red", nullable = true, insertable = true, updatable = true)
    private Integer colorRed;
     @PortfolioTree
    @Basic
    @Column(name = "color_green", nullable = true, insertable = true, updatable = true)
    private Integer colorGreen;
     @PortfolioTree
    @Basic
    @Column(name = "color_blue", nullable = true, insertable = true, updatable = true)
    private Integer colorBlue;
    @PortfolioTree
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, insertable = true, updatable = true, length = 1)
    private StateType stateType;
    @PortfolioTree
    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 2147483647)
    private String description;
    @PortfolioTree
    @Basic
    //TODO Zmienic nazwe na order
    @Column(name = "process_sequence_number", nullable = true, insertable = true, updatable = true)
    private Integer sequenceNumber;
    @ManyToOne
    @JoinColumn(name = "proces_id", referencedColumnName = "process_id", nullable = false)
    private Process process;

    public State() {
    }

    public State(Process process, String name, StateType stateType, String description) {
        this.process = process;
        this.name = name;
        this.stateType = stateType;
        this.description = description;
    }

    public State(Process process, String name, String description, StateType stateType,
                 Integer colorRed,Integer colorGreen,Integer colorBlue,
                 Integer sequenceNumber) {
        this.process = process;
        this.name = name;
        this.description = description;
        this.stateType = stateType;
        this.colorRed = colorRed;
        this.colorGreen = colorGreen;
        this.colorBlue = colorBlue;
        this.sequenceNumber = sequenceNumber;
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

    public Integer getColorRed() {
        return colorRed;
    }

    public void setColorRed(Integer colorRed) {
        this.colorRed = colorRed;
    }

    public Integer getColorGreen() {
        return colorGreen;
    }

    public void setColorGreen(Integer colorGreen) {
        this.colorGreen = colorGreen;
    }

    public Integer getColorBlue() {
        return colorBlue;
    }

    public void setColorBlue(Integer colorBlue) {
        this.colorBlue = colorBlue;
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

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
}

