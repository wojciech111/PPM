package model.inventory;

import javax.persistence.*;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "operations", schema = "public")
public class Operation extends Component  {
    @Basic
    @Column(name = "recursion_type", nullable = false, insertable = true, updatable = true, length = 1)
    private String recursionType;

    public Operation() {
    }

    public Operation(String code, String name, String customer, String description) {
        super(code, name, customer, description);
    }

    public String getRecursionType() {
        return recursionType;
    }

    public void setRecursionType(String recursionType) {
        this.recursionType = recursionType;
    }

}
