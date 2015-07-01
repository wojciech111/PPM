package model.categorization;



import model.categorization.enums.SuperiorityStrategy;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Wojciech on 2015-07-01.
 */
@Entity
@DiscriminatorValue("N")
public class NumericScoringCriterion extends ScoringCriterion {
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "best_is", nullable = true, insertable = true, updatable = true, length = 3)
    private SuperiorityStrategy bestIs;
    @Basic
    @Column(name = "min_score_required", nullable = true, insertable = true, updatable = true, precision = 2)
    private BigDecimal minScoreRequired;
    @Basic
    @Column(name = "max_score_required", nullable = true, insertable = true, updatable = true, precision = 2)
    private BigDecimal maxScoreRequired;

    public NumericScoringCriterion() {
    }

    public NumericScoringCriterion(String code, String name, String description, SuperiorityStrategy bestIs) {
        super(code, name, description);
        this.bestIs = bestIs;
    }

    public SuperiorityStrategy getBestIs() {
        return bestIs;
    }

    public void setBestIs(SuperiorityStrategy bestIs) {
        this.bestIs = bestIs;
    }

    public BigDecimal getMinScoreRequired() {
        return minScoreRequired;
    }

    public void setMinScoreRequired(BigDecimal minScoreRequired) {
        this.minScoreRequired = minScoreRequired;
    }

    public BigDecimal getMaxScoreRequired() {
        return maxScoreRequired;
    }

    public void setMaxScoreRequired(BigDecimal maxScoreRequired) {
        this.maxScoreRequired = maxScoreRequired;
    }
}
