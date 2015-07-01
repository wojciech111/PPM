package model.categorization;

import model.inventory.Component;
import model.categorization.pk.ScorePK;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Table(name = "scores", schema = "public")
public abstract class Score {
    //ID
    @EmbeddedId
    protected ScorePK scoresPK;

    //BASICS
    /*@Basic
    @Column(name = "percentage", nullable = true, insertable = true, updatable = true)
    private Short percentage;*/
    @Basic
    @Column(name = "motivation", nullable = true, insertable = true, updatable = true, length = 2147483647)
    private String motivation;

    //RELATIONS
    @ManyToOne(optional = false)
    @JoinColumn(name = "component_id", referencedColumnName = "component_id", insertable = false, updatable = false)
    private Component component;

    public Score() {
    }

    public Score(Component component, String motivation) {
        this.component = component;
        this.motivation = motivation;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }



    public Component getComponent() {
        return component;
    }

    public void setComponent(Component componentsByComponentId) {
        this.component = componentsByComponentId;
    }


    public abstract ScoringCriterion getScoringCriterion();

}
