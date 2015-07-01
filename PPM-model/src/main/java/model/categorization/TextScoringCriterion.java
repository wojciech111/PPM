package model.categorization;



import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by Wojciech on 2015-07-01.
 */
@Entity
@DiscriminatorValue("T")
public class TextScoringCriterion extends ScoringCriterion {
    @Basic
    @Column(name = "question", nullable = true, insertable = true, updatable = true, length = 150)
    private String question;

    public TextScoringCriterion() {
    }

    public TextScoringCriterion(String code, String name, String description, String question) {
        super(code, name, description);
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
