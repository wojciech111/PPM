package model.categorization;

import com.google.gson.annotations.Expose;
import model.categorization.pk.AreaOfFocusPK;
import model.inventory.Portfolio;
import util.annotation.PortfolioTree;

import javax.persistence.*;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "areas_of_focus", schema = "public")
public class AreaOfFocus {
    //ID
    @PortfolioTree
    @EmbeddedId
    protected model.categorization.pk.AreaOfFocusPK areaOfFocusPK;

    //BASICS
    @PortfolioTree
    @Basic
    @Column(name = "percentage_of_focus", nullable = true, insertable = true, updatable = true)
    private Short percentageOfFocus;

    //RELATIONS
    @PortfolioTree
    @ManyToOne(optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false)
    private Category category;

    @ManyToOne(optional = false)
    @JoinColumn(name = "component_id", referencedColumnName = "component_id", insertable = false, updatable = false)
    private Portfolio portfolio;

    public AreaOfFocus() {
    }

    public AreaOfFocus(Portfolio portfolio,Category category, Short percentageOfFocus ) {
        this.areaOfFocusPK= new AreaOfFocusPK(portfolio.getId(),category.getId());
        this.percentageOfFocus = percentageOfFocus;
        this.category = category;
        this.portfolio = portfolio;

    }

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
