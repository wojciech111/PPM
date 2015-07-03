package model.categorization;

import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@DiscriminatorOptions(force = true)
@Table(name = "scoring_criteria", schema = "public")
public abstract class ScoringCriterion {
    //ID
    @Id
    @SequenceGenerator(name="scoring_criterion_seq", sequenceName="scoring_criterion_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="scoring_criterion_seq")
    @Column(name = "scoring_criterion_id", nullable = false, insertable = true, updatable = true)
    private long scoringCriterionId;

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
    /*@OneToMany(mappedBy = "scoringCriterion", fetch = FetchType.EAGER)
    private Set<CategoryEvaluation> categoryDescriptions = new HashSet<CategoryEvaluation>();
    @OneToMany(mappedBy = "scoringCriterion")
    private Collection<Score> scores;*/

    public ScoringCriterion() {
    }

    public ScoringCriterion(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return scoringCriterionId;
    }

    public void setId(long scoringCriterionId) {
        this.scoringCriterionId = scoringCriterionId;
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


}
