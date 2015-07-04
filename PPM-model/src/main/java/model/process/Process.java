package model.process;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wojciech on 2015-07-04.
 */
@Entity
@Table(name = "processes", schema = "public")
public class Process {
    @Id
    @Column(name = "proces_id", nullable = false, insertable = true, updatable = true)
    private long procesId;
    @Basic
    @Column(name = "code", nullable = false, insertable = true, updatable = true, length = 6)
    private String code;
    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 50)
    private String name;
    @OneToMany(mappedBy = "process", fetch = FetchType.EAGER)
    @OrderBy("nrInProcess")
    private Set<State> statesByProcesId = new HashSet<State>();

    public long getId() {
        return procesId;
    }

    public void setId(long procesId) {
        this.procesId = procesId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<State> getStatesByProcesId() {
        return statesByProcesId;
    }

    public void setStatesByProcesId(Set<State> statesByProcesId) {
        this.statesByProcesId = statesByProcesId;
    }
}

