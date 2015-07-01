package model.categorization;

import model.categorization.pk.CategoryEvaluationPK;

import javax.persistence.*;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "describing_criteria", schema = "public")
public class CategoryEvaluation {
    //ID
    @EmbeddedId
    protected CategoryEvaluationPK categoryEvaluationPK;

    //BASICS
    @Basic
    @Column(name = "is_key", nullable = false, insertable = true, updatable = true, length = 1)
    private String isKey;
    @Basic
    @Column(name = "weight", nullable = true, insertable = true, updatable = true)
    private Short weight;

    //RELATIONS
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false)
    private Category category;
    @ManyToOne(optional = false)
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
