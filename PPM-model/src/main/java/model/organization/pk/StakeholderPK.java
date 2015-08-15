package model.organization.pk;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Wojciech on 2015-08-12.
 */
@Embeddable
public class StakeholderPK implements Serializable {
    @Column(name = "employee_id")
    @Basic(optional = false)
    private long employeeId;
    @Column(name = "component_id")
    @Basic(optional = false)
    private long componentId;

    public StakeholderPK() {
    }

    public StakeholderPK(long employeeId, long componentId) {
        this.employeeId = employeeId;
        this.componentId = componentId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }


    public long getComponentId() {
        return componentId;
    }

    public void setComponentId(long componentId) {
        this.componentId = componentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StakeholderPK)) return false;

        StakeholderPK that = (StakeholderPK) o;

        if (employeeId != that.employeeId) return false;
        return componentId == that.componentId;

    }

    @Override
    public int hashCode() {
        int result = (int) (employeeId ^ (employeeId >>> 32));
        result = 31 * result + (int) (componentId ^ (componentId >>> 32));
        return result;
    }
}
