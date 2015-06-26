package model.categorization;

import model.inventory.Component;
import model.categorization.pk.ScorePK;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "scores", schema = "public")
public class Score {
    //ID
    @EmbeddedId
    protected ScorePK scoresPK;

    //BASICS
    @Basic
    @Column(name = "score", nullable = true, insertable = true, updatable = true, precision = 2)
    private BigDecimal score;
    @Basic
    @Column(name = "answer", nullable = true, insertable = true, updatable = true, length = 150)
    private String answer;
    @Basic
    @Column(name = "percentage", nullable = true, insertable = true, updatable = true)
    private Short percentage;
    @Basic
    @Column(name = "motivation", nullable = true, insertable = true, updatable = true, length = 2147483647)
    private String motivation;

    //RELATIONS
    @ManyToOne(optional = false)
    @JoinColumn(name = "component_id", referencedColumnName = "component_id", insertable = false, updatable = false)
    private Component component;
    @ManyToOne(optional = false)
    @JoinColumn(name = "scoring_criterion_id", referencedColumnName = "scoring_criterion_id", insertable = false, updatable = false)
    private ScoringCriterion scoringCriterion;



    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public Short getPercentage() {
        return percentage;
    }

    public void setPercentage(Short percentage) {
        this.percentage = percentage;
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


    public ScoringCriterion getScoringCriterion() {
        return scoringCriterion;
    }

    public void setScoringCriterion(ScoringCriterion scoringCriteriaByScoringCriterionId) {
        this.scoringCriterion = scoringCriteriaByScoringCriterionId;
    }
}
