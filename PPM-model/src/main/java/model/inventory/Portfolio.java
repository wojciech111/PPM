package model.inventory;

import model.categorization.AreaOfFocus;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Wojciech on 2015-06-23.
 */
@Entity
@Table(name = "portfolios", schema = "public")
public class Portfolio extends Component {
    //ID

    //BASICS

    //RELATIONS
    @OneToMany(mappedBy = "portfolio")
    private Collection<AreaOfFocus> areasOfFocus;

    public Portfolio() {
    }

    public Portfolio(String code, String name, String customer, String description) {
        super(code, name, customer, description);
    }

    public Collection<AreaOfFocus> getAreasOfFocus() {
        return areasOfFocus;
    }

    public void setAreasOfFocus(Collection<AreaOfFocus> areasOfFocus) {
        this.areasOfFocus = areasOfFocus;
    }
}
