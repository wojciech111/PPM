package model.categorization;

import com.google.gson.annotations.Expose;
import model.inventory.Component;
import model.categorization.pk.CategoryMembershipPK;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "category_memberships", schema = "public")
public class CategoryMembership {
    //ID
    @EmbeddedId
    protected CategoryMembershipPK categoryMembershipPK;

    //BASICS
    @Expose
    @Basic
    @Column(name = "percentage_of_support", nullable = true, insertable = true, updatable = true)
    private Short percentageOfSupport;
    @Basic
    @Column(name = "overall_score", nullable = true, insertable = true, updatable = true, precision = 2)
    private BigDecimal overallScore;
    @Basic
    @Column(name = "rank_in_category", nullable = true, insertable = true, updatable = true)
    private Short rankInCategory;

    //RELATIONS
    @Expose
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false)
    private Category category;
    @ManyToOne(optional = false)
    @JoinColumn(name = "component_id", referencedColumnName = "component_id", insertable = false, updatable = false)
    private Component component;

    public CategoryMembership() {
    }

    public CategoryMembership(Component component, Category category) {
        this.categoryMembershipPK= new CategoryMembershipPK(component.getId(),category.getId());
        this.component = component;
        this.category = category;
    }

    public CategoryMembershipPK getCategoryMembershipPK() {
        return categoryMembershipPK;
    }

    public void setCategoryMembershipPK(CategoryMembershipPK categoryMembershipPK) {
        this.categoryMembershipPK = categoryMembershipPK;
    }

    public Short getPercentageOfSupport() {
        return percentageOfSupport;
    }

    public void setPercentageOfSupport(Short percentageOfSupport) {
        this.percentageOfSupport = percentageOfSupport;
    }


    public BigDecimal getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(BigDecimal overallScore) {
        this.overallScore = overallScore;
    }

    public Short getRankInCategory() {
        return rankInCategory;
    }

    public void setRankInCategory(Short rankInCategory) {
        this.rankInCategory = rankInCategory;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category categoriesByCategoryId) {
        this.category = categoriesByCategoryId;
    }


    public Component getComponent() {
        return component;
    }

    public void setComponent(Component componentsByComponentId) {
        this.component = componentsByComponentId;
    }
}
