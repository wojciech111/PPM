package model.categorization;

import com.google.gson.annotations.Expose;
import model.categorization.pk.CategoryEvaluationPK;
import util.annotation.PortfolioTree;

import javax.persistence.*;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "category_evaluations", schema = "public")
public class CategoryEvaluation {
    //ID
    @PortfolioTree
    @EmbeddedId
    protected CategoryEvaluationPK categoryEvaluationPK;

    //BASICS
    @PortfolioTree
    @Basic
    @Column(name = "is_key", nullable = true, insertable = true, updatable = true, length = 1)
    private String isKey;
    @PortfolioTree
    @Basic
    @Column(name = "weight", nullable = true, insertable = true, updatable = true)
    private Short weight;

    //RELATIONS
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false)
    private Category category;
    @PortfolioTree
    @ManyToOne(optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "scoring_criterion_id", referencedColumnName = "scoring_criterion_id", insertable = false, updatable = false)
    private ScoringCriterion scoringCriterion;

    public CategoryEvaluation() {
    }

    public CategoryEvaluation(ScoringCriterion scoringCriterion, Category category) {
        this.categoryEvaluationPK = new CategoryEvaluationPK(scoringCriterion.getId(),category.getId());
        this.scoringCriterion = scoringCriterion;
        this.category = category;
    }

    public String getIsKey() {
        return isKey;
    }

    public void setIsKey(String isKey) {
        this.isKey = isKey;
    }


    public Short getWeight() {
        return weight;
    }

    public void setWeight(Short weight) {
        this.weight = weight;
    }



    public Category getCategory() {
        return category;
    }

    public void setCategory(Category categoriesByCategoryId) {
        this.category = categoriesByCategoryId;
    }


    public ScoringCriterion getScoringCriterion() {
        return scoringCriterion;
    }

    public void setScoringCriterion(ScoringCriterion scoringCriteriaByScoringCriterionId) {
        this.scoringCriterion = scoringCriteriaByScoringCriterionId;
    }
}
