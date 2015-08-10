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
public class CategoryMembershipPK implements Serializable {
    @PortfolioTree
    @Column(name = "component_id")
    @Basic(optional = false)
    private long componentId;
    @PortfolioTree
    @Column(name = "category_id")
    @Basic(optional = false)
    private long categoryId;

    public CategoryMembershipPK() {
    }

    public CategoryMembershipPK(long componentId, long categoryId) {
        this.componentId = componentId;
        this.categoryId = categoryId;
    }

    public long getComponentId() {
        return componentId;
    }

    public void setComponentId(long componentId) {
        this.componentId = componentId;
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

        CategoryMembershipPK that = (CategoryMembershipPK) o;

        if (componentId != that.componentId) return false;
        if (categoryId != that.categoryId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (componentId ^ (componentId >>> 32));
        result = 31 * result + (int) (categoryId ^ (categoryId >>> 32));
        return result;
    }
}
