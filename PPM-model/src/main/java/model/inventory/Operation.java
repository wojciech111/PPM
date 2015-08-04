package model.inventory;

import com.google.gson.annotations.Expose;
import model.inventory.enums.ComponentType;
import model.inventory.enums.OperationType;
import model.inventory.enums.RecursionType;
import util.exception.InvalidParentComponentException;

import javax.persistence.*;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "operations", schema = "public")
public class Operation extends Component  {
    @Expose
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "recursion_type", nullable = false, insertable = true, updatable = true, length = 1)
    private RecursionType recursionType;
    @Expose
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = true, insertable = true, updatable = true, length = 1)
    private OperationType operationType;
    @Expose
    @Basic
    @Column(name = "number_of_time_units", nullable = true, insertable = true, updatable = true)
    private Integer numberOfTimeUnits;
    @Expose
    @Transient
    private final ComponentType componentType = ComponentType.OPERATION;

    public Operation() {
    }

    public Operation(String code, String name, String customer, String description, RecursionType recursionType) {
        super(code, name, customer, description);
        this.recursionType= recursionType;
    }

    @Override
    public void setParent(Component parent) throws InvalidParentComponentException {
        if ( parent instanceof Program)
            super.setParent(parent);
        else
            throw new InvalidParentComponentException("Operation can only be child of Program.");
    }
    public RecursionType getRecursionType() {
        return recursionType;
    }

    public void setRecursionType(RecursionType recursionType) {
        this.recursionType = recursionType;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public Integer getNumberOfTimeUnits() {
        return numberOfTimeUnits;
    }

    public void setNumberOfTimeUnits(Integer numberOfTimeUnits) {
        this.numberOfTimeUnits = numberOfTimeUnits;
    }
}
