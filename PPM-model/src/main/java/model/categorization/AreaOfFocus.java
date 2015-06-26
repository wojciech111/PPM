package model.categorization;

import model.inventory.Portfolio;

import javax.persistence.*;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "areas_of_focus", schema = "public")
public class AreaOfFocus {
    //ID
    @EmbeddedId
    protected model.categorization.pk.AreaOfFocusPK AreaOfFocusPK;

    //BASICS
    @Basic
    @Column(name = "percentage_of_focus", nullable = true, insertable = true, updatable = true)
    private Short percentageOfFocus;

    //RELATIONS
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false)
    private Category category;

    @ManyToOne(optional = false)
    @JoinColumn(name = "component_id", referencedColumnName = "component_id", insertable = false, updatable = false)
    private Portfolio portfolio;

    public Short getPercentageOfFocus() {
        return percentageOfFocus;
    }

    public void setPercentageOfFocus(Short percentageOfFocus) {
        this.percentageOfFocus = percentageOfFocus;
    }

      public Category getCategory() {
        return category;
    }

    public void setCategory(Category categoriesByCategoryId) {
        this.category = categoriesByCategoryId;
    }
    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfoliosByComponentId) {
        this.portfolio = portfoliosByComponentId;
    }
}
