package model.inventory;

import com.google.gson.annotations.Expose;
import model.categorization.AreaOfFocus;
import model.inventory.enums.ComponentType;
import model.process.*;
import model.process.Process;
import util.annotation.PortfolioTree;
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
    @PortfolioTree
    @Transient
    private final ComponentType componentType = ComponentType.PORTFOLIO;

    //RELATIONS
    @PortfolioTree
    @OneToMany(mappedBy = "portfolio", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<AreaOfFocus> areasOfFocus = new HashSet<AreaOfFocus>();
    //@PortfolioTree
    @OneToMany(mappedBy = "portfolio", fetch = FetchType.EAGER)
    private Set<Process> processes = new HashSet<Process>();

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
}
