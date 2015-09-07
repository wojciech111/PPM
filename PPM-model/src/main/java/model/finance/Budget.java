package model.finance;

import model.inventory.Portfolio;
import model.organization.Organization;
import util.annotation.PortfolioTree;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Wojciech on 2015-08-17.
 */
@Entity
@Table(name = "budgets", schema = "public")
public class Budget {
    @PortfolioTree
    @Id
    @SequenceGenerator(name="budget_seq", sequenceName="budget_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="budget_seq")
    @Column(name = "budget_id", nullable = false, insertable = true, updatable = true)
    private long budgetId;
    @PortfolioTree
    @Basic
    @Column(name = "from_date", nullable = false, insertable = true, updatable = true)
    private Date fromDate;
    @PortfolioTree
    @Basic
    @Column(name = "to_date", nullable = true, insertable = true, updatable = true)
    private Date toDate;
    @PortfolioTree
    @Basic
    @Column(name = "amount_of_money", nullable = false, insertable = true, updatable = true, precision = 2)
    private BigDecimal amountOfMoney;
    @PortfolioTree
    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 150)
    private String name;
    @PortfolioTree
    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 1500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "component_id", referencedColumnName = "component_id", insertable = true, updatable = false)
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "organization_id", insertable = true, updatable = false)
    private Organization organization;

    public Budget() {
    }

    public Budget(Portfolio portfolio, Organization organization, Date fromDate, Date toDate, BigDecimal amountOfMoney, String name, String description) {
        this.portfolio = portfolio;
        this.organization = organization;
        this.fromDate = fromDate;
        this.amountOfMoney = amountOfMoney;
        this.name = name;
        this.toDate = toDate;
        this.description = description;
    }

    public long getId() {
        return budgetId;
    }

    public void setId(long budgetId) {
        this.budgetId = budgetId;
    }


    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }


    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }


    public BigDecimal getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(BigDecimal amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
