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
    @SequenceGenerator(name="process_seq", sequenceName="process_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="process_seq")
    @Column(name = "process_id", nullable = false, insertable = true, updatable = true)
    private long processId;
    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 50)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 2147483647)
    private String description;
    @OneToMany(mappedBy = "process", fetch = FetchType.EAGER)
    private Set<State> statesByProcesId = new HashSet<State>();

    public long getId() {
        return processId;
    }

    public void setId(long processId) {
        this.processId = processId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<State> getStatesByProcesId() {
        return statesByProcesId;
    }

    public void setStatesByProcesId(Set<State> statesByProcesId) {
        this.statesByProcesId = statesByProcesId;
    }
}

