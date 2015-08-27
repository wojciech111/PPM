package model.inventory;

import com.google.gson.annotations.Expose;
import model.categorization.AreaOfFocus;
import model.inventory.enums.ComponentType;
import model.organization.Organization;
import model.process.*;
import model.process.Process;
import util.annotation.PortfolioTree;
import util.annotation.UserTree;
import util.exception.InvalidParentComponentException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "portfolios", schema = "public")
public class Portfolio extends Component {
    //ID

    //BASICS
    @UserTree
    @PortfolioTree
    @Transient
    private final ComponentType componentType = ComponentType.PORTFOLIO;

    //RELATIONS
    //@PortfolioTree
    @OneToMany(mappedBy = "portfolio", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<AreaOfFocus> areasOfFocus = new HashSet<AreaOfFocus>();
    //@PortfolioTree
    @OneToMany(mappedBy = "portfolio", fetch = FetchType.EAGER)
    private Set<Process> processes = new HashSet<Process>();
    @PortfolioTree
    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "organization_id", nullable = true,insertable = true, updatable = true)
    private Organization organization;


    public Portfolio() {
    }

    public Portfolio(String code, String name, String customer, String description) {
        super(code, name, customer, description);
    }

    @Override
    public void setParent(Component parent) throws InvalidParentComponentException {
        if (parent == null || parent instanceof Portfolio)
            super.setParent(parent);
        else
            throw new InvalidParentComponentException("Portfolio can only be child of Portfolio.");
    }

    public Set<AreaOfFocus> getAreasOfFocus() {
        return areasOfFocus;
    }

    public void setAreasOfFocus(Set<AreaOfFocus> areasOfFocus) {
        this.areasOfFocus = areasOfFocus;
    }

    public Set<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(Set<Process> processes) {
        this.processes = processes;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
