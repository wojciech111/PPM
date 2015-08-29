package model.inventory;


import model.inventory.enums.ComponentType;
import model.inventory.enums.CustomerType;
import model.inventory.enums.OperationType;
import model.inventory.enums.RecursionType;
import model.process.State;
import util.annotation.PortfolioTree;
import util.exception.InvalidParentComponentException;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "operations", schema = "public")
public class Operation extends Component  {
    @PortfolioTree
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "recursion_type", nullable = false, insertable = true, updatable = true, length = 1)
    private RecursionType recursionType;
    @PortfolioTree
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = true, insertable = true, updatable = true, length = 1)
    private OperationType operationType;
    @PortfolioTree
    @Basic
    @Column(name = "number_of_time_units", nullable = true, insertable = true, updatable = true)
    private Integer numberOfTimeUnits;
    @PortfolioTree
    @Transient
    private final ComponentType componentType = ComponentType.OPERATION;

    public Operation() {
    }

    public Operation(String code, String name, String customer, String description, RecursionType recursionType) {
        super(code, name, customer, description);
        this.recursionType= recursionType;
    }

    public Operation(String code, String name, CustomerType customerType,
                     String customer, String sponsor, String manager,
                     String purpose, String description,
                     Timestamp creationDate, String createdBy, Timestamp updateDate, String updatedBy,
                     Component parent, State state,
                     OperationType operationType, RecursionType recursionType, Integer numberOfTimeUnits) {
        super(code, name, customerType, customer, sponsor, manager, purpose, description, creationDate, createdBy, updateDate, updatedBy, parent, state);
        this.operationType = operationType;
        this.recursionType = recursionType;
        this.numberOfTimeUnits = numberOfTimeUnits;
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
