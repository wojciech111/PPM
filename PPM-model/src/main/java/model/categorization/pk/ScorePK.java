package model.categorization.pk;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Embeddable
public class ScorePK implements Serializable {
    @Column(name = "component_id")
    @Basic(optional = false)
    private long componentId;
    @Column(name = "scoring_criterion_id")
    @Basic(optional = false)
    private long scoringCriterionId;

    public ScorePK() {
    }

    public ScorePK(long componentId, long scoringCriterionId) {
        this.componentId = componentId;
        this.scoringCriterionId = scoringCriterionId;
    }

    public long getComponentId() {
        return componentId;
    }

    public void setComponentId(long componentId) {
        this.componentId = componentId;
    }


    public long getScoringCriterionId() {
        return scoringCriterionId;
    }

    public void setScoringCriterionId(long scoringCriterionId) {
        this.scoringCriterionId = scoringCriterionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScorePK scoresPK = (ScorePK) o;

        if (componentId != scoresPK.componentId) return false;
        if (scoringCriterionId != scoresPK.scoringCriterionId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (componentId ^ (componentId >>> 32));
        result = 31 * result + (int) (scoringCriterionId ^ (scoringCriterionId >>> 32));
        return result;
    }
}
