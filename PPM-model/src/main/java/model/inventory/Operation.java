package model.inventory;

import model.inventory.enums.RecurssionType;
import util.exception.InvalidParentComponentException;

import javax.persistence.*;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "operations", schema = "public")
public class Operation extends Component  {
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "recursion_type", nullable = false, insertable = true, updatable = true, length = 1)
    private RecurssionType recursionType;

    public Operation() {
    }

    public Operation(String code, String name, String customer, String description, RecurssionType recurssionType) {
        super(code, name, customer, description);
        this.recursionType=recurssionType;
    }

    @Override
    public void setParent(Component parent) throws InvalidParentComponentException {
        if ( parent instanceof Program)
            super.setParent(parent);
        else
            throw new InvalidParentComponentException("Operation can only be child of Program.");
    }
    public RecurssionType getRecursionType() {
        return recursionType;
    }

    public void setRecursionType(RecurssionType recursionType) {
        this.recursionType = recursionType;
    }

}
