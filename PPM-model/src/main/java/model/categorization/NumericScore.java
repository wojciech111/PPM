package model.categorization;

import model.categorization.pk.ScorePK;
import model.inventory.Component;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Wojciech on 2015-07-01.
 */
@Entity
@DiscriminatorValue("N")
public class NumericScore extends Score{
    //BASICS
    @Basic
    @Column(name = "score", nullable = true, insertable = true, updatable = true, precision = 2)
    private BigDecimal score;

    @ManyToOne(optional = false)
    @JoinColumn(name = "scoring_criterion_id", referencedColumnName = "scoring_criterion_id", insertable = false, updatable = false)
    private NumericScoringCriterion scoringCriterion;

    public NumericScore() {
    }

    public NumericScore(Component component, NumericScoringCriterion scoringCriterion, BigDecimal score, String motivation) {
        super(component, motivation);
        this.scoresPK = new ScorePK(component.getId(),scoringCriterion.getId());
        this.score = score.setScale(2,BigDecimal.ROUND_HALF_UP);
        this.scoringCriterion = scoringCriterion;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public NumericScoringCriterion getScoringCriterion() {
        return scoringCriterion;
    }

    public void setScoringCriterion(NumericScoringCriterion scoringCriterion) {
        this.scoringCriterion = scoringCriterion;
    }
}
