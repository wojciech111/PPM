package model.categorization;

import com.google.gson.annotations.Expose;
import util.annotation.PortfolioTree;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "categories", schema = "public")
public class Category {
    //ID
    @PortfolioTree
    @Id
    @SequenceGenerator(name="category_seq", sequenceName="category_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="category_seq")
    @Column(name = "category_id", nullable = false, insertable = true, updatable = true)
    private long categoryId;
    //BASICS
    @PortfolioTree
    @Basic
    @Column(name = "code", nullable = true, insertable = true, updatable = true, length = 10)
    private String code;
    @PortfolioTree
    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 50)
    private String name;
    @PortfolioTree
    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 2147483647)
    private String description;
    //RELATIONS
    /*Kategoria nie musi wiedzie� w jakich portfelach jest u�yta
    @OneToMany(mappedBy = "category")
    private Collection<AreaOfFocus> areasOfFocus;*/
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<CategoryMembership> categoryMemberships = new HashSet<CategoryMembership>();
    @PortfolioTree
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<CategoryEvaluation> categoryEvaluations = new HashSet<CategoryEvaluation>();

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

    public Set<CategoryEvaluation> getCategoryEvaluations() {
        return categoryEvaluations;
    }

    public void setCategoryEvaluations(Set<CategoryEvaluation> categoryEvaluations) {
        this.categoryEvaluations = categoryEvaluations;
    }
}
