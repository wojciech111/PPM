package model.process;


import com.google.gson.annotations.Expose;
import model.inventory.Portfolio;
import util.annotation.PortfolioTree;

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
    @PortfolioTree
    @Id
    @SequenceGenerator(name="process_seq", sequenceName="process_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="process_seq")
    @Column(name = "process_id", nullable = false, insertable = true, updatable = true)
    private long processId;
    @PortfolioTree
    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 50)
    private String name;
    @PortfolioTree
    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 2147483647)
    private String description;

    //RELATIONS
    @ManyToOne(optional = false)
    @JoinColumn(name = "component_id", referencedColumnName = "component_id", insertable = true, updatable = true)
    private Portfolio portfolio;
    @PortfolioTree
    @OneToMany(mappedBy = "process", fetch = FetchType.EAGER)
    private Set<State> states = new HashSet<State>();

    public Process() {
    }

    public Process(String name, String description, Portfolio portfolio) {
        this.name = name;
        this.description = description;
        this.portfolio = portfolio;
    }

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

    public Set<State> getStates() {
        return states;
    }

    public void setStates(Set<State> states) {
        this.states = states;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
}

