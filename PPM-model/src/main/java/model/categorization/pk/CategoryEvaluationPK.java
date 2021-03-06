package model.categorization.pk;

import util.annotation.PortfolioTree;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Embeddable
public class CategoryEvaluationPK implements Serializable {
    @PortfolioTree
    @Column(name = "scoring_criterion_id")
    @Basic(optional = false)
    private long scoringCriterionId;
    @PortfolioTree
    @Column(name = "category_id")
    @Basic(optional = false)
    private long categoryId;

    public CategoryEvaluationPK() {
    }

    public CategoryEvaluationPK(long scoringCriterionId, long categoryId) {
        this.scoringCriterionId = scoringCriterionId;
        this.categoryId = categoryId;
    }

    public long getScoringCriterionId() {
        return scoringCriterionId;
    }

    public void setScoringCriterionId(long scoringCriterionId) {
        this.scoringCriterionId = scoringCriterionId;
    }


    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryEvaluationPK that = (CategoryEvaluationPK) o;

        if (scoringCriterionId != that.scoringCriterionId) return false;
        if (categoryId != that.categoryId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (scoringCriterionId ^ (scoringCriterionId >>> 32));
        result = 31 * result + (int) (categoryId ^ (categoryId >>> 32));
        return result;
    }
}
