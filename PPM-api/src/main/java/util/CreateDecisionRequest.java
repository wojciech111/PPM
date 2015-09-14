package util;

import model.process.enums.DecisionState;

/**
 * Created by Wojciech on 2015-09-01.
 */
public class CreateDecisionRequest {
    private String fromStateId;
    private String toStateId;
    private DecisionState decisionState;
    private String decisionType;
    private String motivation;

    public CreateDecisionRequest() {
    }

    public CreateDecisionRequest(String fromStateId, String toStateId, DecisionState decisionState, String decisionType, String motivation) {
        this.fromStateId = fromStateId;
        this.toStateId = toStateId;
        this.decisionState = decisionState;
        this.decisionType = decisionType;
        this.motivation = motivation;
    }

    public String getFromStateId() {
        return fromStateId;
    }

    public void setFromStateId(String fromStateId) {
        this.fromStateId = fromStateId;
    }

    public String getToStateId() {
        return toStateId;
    }

    public void setToStateId(String toStateId) {
        this.toStateId = toStateId;
    }

    public DecisionState getDecisionState() {
        return decisionState;
    }

    public void setDecisionState(DecisionState decisionState) {
        this.decisionState = decisionState;
    }

    public String getDecisionType() {
        return decisionType;
    }

    public void setDecisionType(String decisionType) {
        this.decisionType = decisionType;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }
}
