package model.categorization;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "categories", schema = "public")
public class Category {
    //ID
    @Id
    @SequenceGenerator(name="category_seq", sequenceName="category_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="category_seq")
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
    /*Kategoria nie musi wiedzieæ w jakich portfelach jest u¿yta
    @OneToMany(mappedBy = "category")
    private Collection<AreaOfFocus> areasOfFocus;*/
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<CategoryMembership> categoryMemberships = new HashSet<CategoryMembership>();
    @OneToMany(mappedBy = "category")
    private Collection<DescribingCriterion> describingCriteria;

    public Category() {
    }

    public Category(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return categoryId;
    }

    public void setId(long categoryId) {
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

    public Set<CategoryMembership> getCategoryMemberships() {
        return categoryMemberships;
    }

    public void setCategoryMemberships(Set<CategoryMembership> categoryMemberships) {
        this.categoryMemberships = categoryMemberships;
    }

    public Collection<DescribingCriterion> getDescribingCriteria() {
        return describingCriteria;
    }

    public void setDescribingCriteria(Collection<DescribingCriterion> describingCriteria) {
        this.describingCriteria = describingCriteria;
    }
}
