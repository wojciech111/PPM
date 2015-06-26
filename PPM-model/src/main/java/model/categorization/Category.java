package model.categorization;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "categories", schema = "public")
public class Category {
    //ID
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "category_id", nullable = false, insertable = true, updatable = true)
    private long categoryId;
    //BASICS
    @Basic
    @Column(name = "code", nullable = false, insertable = true, updatable = true, length = 8)
    private String code;
    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 50)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 2147483647)
    private String description;
    //RELATIONS
    @OneToMany(mappedBy = "category")
    private Collection<AreaOfFocus> areasOfFocus;
    @OneToMany(mappedBy = "category")
    private Collection<CategoryMembership> categoryMemberships;
    @OneToMany(mappedBy = "category")
    private Collection<DescribingCriterion> describingCriteria;


    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        Category that = (Category) o;

        if (categoryId != that.categoryId) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (categoryId ^ (categoryId >>> 32));
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public Collection<AreaOfFocus> getAreasOfFocus() {
        return areasOfFocus;
    }

    public void setAreasOfFocus(Collection<AreaOfFocus> areasOfFocus) {
        this.areasOfFocus = areasOfFocus;
    }
}
