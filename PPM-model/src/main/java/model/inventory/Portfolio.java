package model.inventory;


import model.categorization.AreaOfFocus;
import model.categorization.ScoringCriterion;
import model.finance.Budget;
import model.inventory.enums.ComponentType;
import model.inventory.enums.CustomerType;
import model.organization.Organization;
import model.process.*;
import model.process.Process;
import util.annotation.PortfolioTree;
import util.annotation.UserTree;
import util.exception.InvalidParentComponentException;

import javax.persistence.*;
import java.sql.Timestamp;
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
    @PortfolioTree
    @OneToMany(mappedBy = "portfolio", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<AreaOfFocus> areasOfFocus = new HashSet<AreaOfFocus>();
    @PortfolioTree
    @OneToMany(mappedBy = "portfolio", fetch = FetchType.EAGER)
    private Set<Process> processes = new HashSet<Process>();
    @PortfolioTree
    @OneToMany(mappedBy = "portfolio", fetch = FetchType.EAGER)
    private Set<Budget> budgets = new HashSet<Budget>();
    @PortfolioTree
    @OneToMany(mappedBy = "portfolio", fetch = FetchType.EAGER )
    private Set<ScoringCriterion> scoringCriterions = new HashSet<ScoringCriterion>();
    @PortfolioTree
    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "organization_id", nullable = true,insertable = true, updatable = true)
    private Organization organization;


    public Portfolio() {
    }

    public Portfolio(String code, String name, String customer, String description) {
        super(code, name, customer, description);
    }

    public Portfolio(String code, String name, CustomerType customerType, String customer, String sponsor,
                     String manager, String purpose, String description,
                     Timestamp creationDate, String createdBy,
                     Timestamp updateDate, String updatedBy,
                     Component parent, State state, Organization organization) {
        super(code, name, customerType, customer, sponsor, manager, purpose, description, creationDate, createdBy, updateDate, updatedBy, parent, state,null);
        this.organization = organization;
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

    public Set<ScoringCriterion> getScoringCriterions() {
        return scoringCriterions;
    }

    public void setScoringCriterions(Set<ScoringCriterion> scoringCriterions) {
        this.scoringCriterions = scoringCriterions;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Set<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(Set<Budget> budgets) {
        this.budgets = budgets;
    }
}
