package model.categorization;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "scoring_criteria", schema = "public")
public class ScoringCriterion {
    //ID
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "scoring_criterion_id", nullable = false, insertable = true, updatable = true)
    private long scoringCriterionId;

    //BASICS
    @Basic
    @Column(name = "code", nullable = false, insertable = true, updatable = true, length = 8)
    private String code;
    @Basic
    @Column(name = "type", nullable = false, insertable = true, updatable = true, length = 1)
    private String type;
    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 50)
    private String name;
    @Basic
    @Column(name = "best_is", nullable = true, insertable = true, updatable = true, length = 3)
    private String bestIs;
    @Basic
    @Column(name = "min_score_required", nullable = true, insertable = true, updatable = true, precision = 2)
    private BigDecimal minScoreRequired;
    @Basic
    @Column(name = "max_score_required", nullable = true, insertable = true, updatable = true, precision = 2)
    private BigDecimal maxScoreRequired;
    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 2147483647)
    private String description;

    //RELATIONS
    @OneToMany(mappedBy = "scoringCriterion")
    private Collection<DescribingCriterion> describingCriteria;
    @OneToMany(mappedBy = "scoringCriterion")
    private Collection<Score> scores;

    public long getScoringCriterionId() {
        return scoringCriterionId;
    }

    public void setScoringCriterionId(long scoringCriterionId) {
        this.scoringCriterionId = scoringCriterionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBestIs() {
        return bestIs;
    }

    public void setBestIs(String bestIs) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScoringCriterion that = (ScoringCriterion) o;

        if (scoringCriterionId != that.scoringCriterionId) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (bestIs != null ? !bestIs.equals(that.bestIs) : that.bestIs != null) return false;
        if (minScoreRequired != null ? !minScoreRequired.equals(that.minScoreRequired) : that.minScoreRequired != null)
            return false;
        if (maxScoreRequired != null ? !maxScoreRequired.equals(that.maxScoreRequired) : that.maxScoreRequired != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (scoringCriterionId ^ (scoringCriterionId >>> 32));
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (bestIs != null ? bestIs.hashCode() : 0);
        result = 31 * result + (minScoreRequired != null ? minScoreRequired.hashCode() : 0);
        result = 31 * result + (maxScoreRequired != null ? maxScoreRequired.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public Collection<DescribingCriterion> getDescribingCriteria() {
        return describingCriteria;
    }

    public void setDescribingCriteria(Collection<DescribingCriterion> describingCriteria) {
        this.describingCriteria = describingCriteria;
    }
}
