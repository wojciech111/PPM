package model.categorization;

import model.categorization.pk.ScorePK;
import model.inventory.Component;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Wojciech on 2015-07-01.
 */
@Entity
@DiscriminatorValue("T")
public class TextScore extends Score{
    //BASICS
    @Basic
    @Column(name = "answer", nullable = true, insertable = true, updatable = true, length = 150)
    private String answer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "scoring_criterion_id", referencedColumnName = "scoring_criterion_id", insertable = false, updatable = false)
    private ScoringCriterion scoringCriterion;

    public TextScore() {
    }

    public TextScore(Component component, TextScoringCriterion scoringCriterion, String answer, String motivation) {
        super(component, motivation);
        this.scoresPK = new ScorePK(component.getId(),scoringCriterion.getId());
        this.answer = answer;
        this.scoringCriterion = scoringCriterion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ScoringCriterion getScoringCriterion() {
        return scoringCriterion;
    }

    public void setScoringCriterion(ScoringCriterion scoringCriterion) {
        this.scoringCriterion = scoringCriterion;
    }
}
