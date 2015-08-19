package model.finance;

import model.inventory.Component;
import model.inventory.Portfolio;
import util.annotation.PortfolioTree;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Wojciech on 2015-08-17.
 */
@Entity
@Table(name = "costs", schema = "public")
public class Cost {
    @PortfolioTree
    @Id
    @SequenceGenerator(name="cost_seq", sequenceName="cost_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cost_seq")
    @Column(name = "cost_id", nullable = false, insertable = true, updatable = true)
    private long costId;
    @PortfolioTree
    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 150)
    private String name;
    @PortfolioTree
    @Basic
    @Column(name = "value", nullable = false, insertable = true, updatable = true, precision = 2)
    private BigDecimal value;
    @PortfolioTree
    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 1500)
    private String description;
    @PortfolioTree
    @Basic
    @Column(name = "day_of_occurence", nullable = true, insertable = true, updatable = true)
    private Integer dayOfOccurence;

    @ManyToOne(optional = false)
    @JoinColumn(name = "component_id", referencedColumnName = "component_id", insertable = false, updatable = false)
    private Component component;

    public Cost() {
    }

    public Cost(String name, BigDecimal value, Component component, String description, Integer dayOfOccurence) {
        this.name = name;
        this.component = component;
        this.value = value;
        this.description = description;
        this.dayOfOccurence = dayOfOccurence;
    }

    public long getId() {
        return costId;
    }

    public void setCostId(long costId) {
        this.costId = costId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

     public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

     public Integer getDayOfOccurence() {
        return dayOfOccurence;
    }

    public void setDayOfOccurence(Integer dayOfOccurence) {
        this.dayOfOccurence = dayOfOccurence;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}
