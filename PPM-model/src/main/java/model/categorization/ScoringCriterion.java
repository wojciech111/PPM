package model.categorization;


import model.categorization.enums.CriterionType;
import model.categorization.enums.SuperiorityStrategy;
import model.inventory.Portfolio;
import org.hibernate.annotations.DiscriminatorOptions;
import util.annotation.PortfolioTree;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "scoring_criteria", schema = "public")
public class ScoringCriterion {
    //ID
    @PortfolioTree
    @Id
    @SequenceGenerator(name="scoring_criterion_seq", sequenceName="scoring_criterion_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="scoring_criterion_seq")
    @Column(name = "scoring_criterion_id", nullable = false, insertable = true, updatable = true)
    private long scoringCriterionId;

    //BASICS
    @PortfolioTree
    @Basic
    @Column(name = "code", nullable = false, insertable = true, updatable = true, length = 10)
    private String code;
    @PortfolioTree
    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 50)
    private String name;
    @PortfolioTree
    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 2147483647)
    private String description;
    @PortfolioTree
    @Basic
    @Column(name = "question", nullable = true, insertable = true, updatable = true, length = 150)
    private String question;
    @PortfolioTree
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "best_is", nullable = false, insertable = true, updatable = true, length = 3)
    private SuperiorityStrategy bestIs;
    @PortfolioTree
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = true, insertable = true, updatable = true, length = 1)
    private CriterionType type;
    @PortfolioTree
    @Basic
    @Column(name = "min_score", nullable = true, insertable = true, updatable = true, precision = 2)
    private BigDecimal minScore;
    @PortfolioTree
    @Basic
    @Column(name = "max_score", nullable = true, insertable = true, updatable = true, precision = 2)
    private BigDecimal maxScore;
    @ManyToOne(optional = false)
    @JoinColumn(name = "component_id", referencedColumnName = "component_id", insertable = true, updatable = true)
    private Portfolio portfolio;

    //RELATIONS
    /*@OneToMany(mappedBy = "scoringCriterion", fetch = FetchType.EAGER)
    private Set<CategoryEvaluation> categoryDescriptions = new HashSet<CategoryEvaluation>();
    @OneToMany(mappedBy = "scoringCriterion")
    private Collection<Score> scores;*/

    public ScoringCriterion() {
    }

    public ScoringCriterion(String code, String name, String description, SuperiorityStrategy bestIs) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.bestIs=bestIs;
    }

    public ScoringCriterion(String code, String name,
                            String description, String question,
                            SuperiorityStrategy bestIs,
                            CriterionType type,
                            BigDecimal minScore, BigDecimal maxScore,
                            Portfolio portfolio) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.question = question;
        this.bestIs = bestIs;
        this.type = type;
        this.minScore = minScore;
        this.maxScore = maxScore;
        this.portfolio = portfolio;
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

    public CriterionType getType() {
        return type;
    }

    public void setType(CriterionType type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public SuperiorityStrategy getBestIs() {
        return bestIs;
    }

    public void setBestIs(SuperiorityStrategy bestIs) {
        this.bestIs = bestIs;
    }

    public BigDecimal getMinScore() {
        return minScore;
    }

    public void setMinScore(BigDecimal minScore) {
        this.minScore = minScore;
    }

    public BigDecimal getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(BigDecimal maxScore) {
        this.maxScore = maxScore;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
}
