package model.categorization;

import com.google.gson.annotations.Expose;
import model.inventory.Component;
import model.categorization.pk.ScorePK;
import org.hibernate.annotations.DiscriminatorOptions;
import util.annotation.PortfolioTree;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "scores", schema = "public")
public class Score {
    //ID
    @PortfolioTree
    @EmbeddedId
    protected ScorePK scoresPK;

    //BASICS
    @Basic
    @Column(name = "percentage", nullable = true, insertable = true, updatable = true)
    private Short percentage;
    @PortfolioTree
    @Basic
    @Column(name = "motivation", nullable = true, insertable = true, updatable = true, length = 2147483647)
    private String motivation;
    @PortfolioTree
    @Basic
    @Column(name = "score", nullable = true, insertable = true, updatable = true, precision = 2)
    private BigDecimal score;
    @PortfolioTree
    @Basic
    @Column(name = "answer", nullable = true, insertable = true, updatable = true, length = 150)
    private String answer;

    //RELATIONS
    @ManyToOne(optional = false)
    @JoinColumn(name = "component_id", referencedColumnName = "component_id", insertable = false, updatable = false)
    private Component component;

    @PortfolioTree
    @ManyToOne(optional = false)
    @JoinColumn(name = "scoring_criterion_id", referencedColumnName = "scoring_criterion_id", insertable = false, updatable = false)
    private ScoringCriterion scoringCriterion;

    public Score() {
    }

    public Score(Component component, ScoringCriterion scoringCriterion, BigDecimal score, String motivation) {
        this.scoresPK = new ScorePK(component.getId(), scoringCriterion.getId());
        this.component = component;
        this.scoringCriterion = scoringCriterion;
        this.score = score.setScale(2,BigDecimal.ROUND_HALF_UP);
        this.motivation = motivation;
    }

    public ScorePK getScoresPK() {
        return scoresPK;
    }

    public void setScoresPK(ScorePK scoresPK) {
        this.scoresPK = scoresPK;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public ScoringCriterion getScoringCriterion() {
        return scoringCriterion;
    }

    public void setScoringCriterion(ScoringCriterion scoringCriterion) {
        this.scoringCriterion = scoringCriterion;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }
}
