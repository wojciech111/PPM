import model.categorization.*;
import model.categorization.enums.SuperiorityStrategy;
import model.inventory.Operation;
import model.inventory.Portfolio;
import model.inventory.Program;
import model.inventory.Project;
import model.inventory.enums.CustomerType;
import model.inventory.enums.RecursionType;
import model.organization.Employee;
import model.organization.Organization;
import model.organization.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.categorization.CategorizationService;
import service.categorization.CategorizationServiceInterface;
import service.inventory.InventoryService;
import service.inventory.InventoryServiceInterface;
import service.organization.OrganizationService;
import service.organization.OrganizationServiceInterface;
import service.process.ProcessService;
import util.HibernateUtil;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Wojciech on 2015-07-04.
 */
public class CreateTestData {
    public static void main(final String[] args) throws Exception {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx =session.beginTransaction();



            session.createQuery("delete from AreaOfFocus").executeUpdate();
            session.createQuery("delete from CategoryMembership ").executeUpdate();
            session.createQuery("delete from CategoryEvaluation ").executeUpdate();
            session.createQuery("delete from Score ").executeUpdate();

            session.createQuery("delete from Category ").executeUpdate();
            session.createQuery("delete from ScoringCriterion ").executeUpdate();

            session.createQuery("delete from Cost ").executeUpdate();
            session.createQuery("delete from Budget ").executeUpdate();

            session.createQuery("delete from Decision ").executeUpdate();
            session.createQuery("delete from State ").executeUpdate();
            session.createQuery("delete from Process ").executeUpdate();

            session.createQuery("delete from Component").executeUpdate();
            session.createQuery("delete from Program").executeUpdate();
            session.createQuery("delete from Project ").executeUpdate();
            session.createQuery("delete from Operation ").executeUpdate();
            session.createQuery("delete from Portfolio").executeUpdate();






            session.createQuery("delete from Stakeholder ").executeUpdate();

            session.createQuery("delete from Employee ").executeUpdate();
            session.createQuery("delete from Organization  ").executeUpdate();
            session.createQuery("delete from User ").executeUpdate();


            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        OrganizationServiceInterface os = new OrganizationService();
        InventoryServiceInterface is = new InventoryService();
        CategorizationServiceInterface cs = new CategorizationService();
        ProcessService ps = new ProcessService();

        //Users
        User user1 = os.createUser("Wojciech111", "php789", "oxio22@gmail.com");
        User user2 = os.createUser("TestUser", "php789", "test@gmail.com");
/*
        //Organizations
        Organization org1 = os.createOrganization("History Machine Organization", "Hisotr yMachine");
        Organization org2 = os.createOrganization("Portfolio Manager", "Portfolio Manager");

        //Employees
        Employee employee_user1_org1 = os.createEmployee("Wojciech", "Oksiñski", org1, user1);
        Employee employee_user1_org2 = os.createEmployee("Wojciech", "Oksiñski", org1, user2);
        Employee employee_user2_org2 = os.createEmployee("Jan", "Testowy", org2, user2);

        //Portfolios
        Portfolio portfolio2 = is.createPortfolio("PrtflMngr", "Portfolio Manager App",
                CustomerType.EX, "Rynek rozwi¹zañ PPM", null,
                "Wojciech Oksiñski", "Wype³nienie luki rynkowej rozwi¹zañ wspomagajacych ma³e i œrednie przedsiêbiorstwa zoreintowane projektowo" ,
                "Stworzenie narzêdzia wspomagaj¹cego organizacje w zarz¹dzaniu portfelem projektów",
                new Timestamp(new Date().getTime()) ,user1.getUsername(),
                new Timestamp(new Date().getTime()+2000), user2.getUsername(),
                null, null, org2);
        Portfolio portfolio1 = is.createPortfolio("HM.org", "HistoryMachine.org",
                CustomerType.EX, "Rynek rozwi¹zañ PPM", null,
                "Wojciech Oksiñski", "Wype³nienie luki rynkowej rozwi¹zañ wspomagajacych ma³e i œrednie przedsiêbiorstwa zoreintowane projektowo",
                "Stworzenie narzêdzia wspomagaj¹cego organizacje w zarz¹dzaniu portfelem projektów",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime() + 2000), user2.getUsername(),
                null, null, org1);
*/
        /*OrganizationServiceInterface organizationService = new OrganizationService();

        User user = organizationService.createUser("oxio", "php", "oxio22@gmail.com");
        Organization organization = organizationService.createOrganization("History Machine org co zoo inc", "HisotryMachine");
        Employee employee = organizationService.createEmployee("Wojciech", "Oksiñski", organization );
        employee.setUser(user);
        employee = organizationService.updateEmployee(employee);


        InventoryServiceInterface inventoryService = new InventoryService();
        Portfolio portfolioTop = inventoryService.createPortfolio("PT", "portfolioTop", "customer jakis", "Opis Opisik", null);
        portfolioTop.setOrganization(organization);
        portfolioTop = inventoryService.updatePortfolio(portfolioTop);
        Project portfolioProject = inventoryService.createProject("PF1", "portfolioProject", "customer jakis", "Opis Opisik", portfolioTop);

        //subportfolio
        Portfolio subPortfolio = inventoryService.createPortfolio("SPT", "subPortfolio", "customer jakis", "Opis Opisik", portfolioTop);
        Program subPortfolioProgram = inventoryService.createProgram("PF1", "subPortfolioProgram", "customer jakis", "Opis Opisik", subPortfolio);
        Project subPortfolioProgramProject = inventoryService.createProject("PF1", "subPortfolioProgramProject", "customer jakis", "Opis Opisik", subPortfolioProgram);
        Project subPortfolioProject = inventoryService.createProject("PF1", "subPortfolioProject", "customer jakis", "Opis Opisik", subPortfolio);

        //program
        Program portfolioProgram = inventoryService.createProgram("PF1", "portfolioProgram", "customer jakis", "Opis Opisik", portfolioTop);
        Operation portfolioProgramOperation = inventoryService.createOperation("PF2", "portfolioProgramOperation", "customer jakis", "Opis Opisik", portfolioProgram, RecursionType.M);
        Project portfolioProgramProject = inventoryService.createProject("PF1", "portfolioProgramProject", "customer jakis", "Opis Opisik", portfolioProgram);

        //subprogram
        Program portfolioProgramSubprogram = inventoryService.createProgram("PF1", "portfolioProgramSubprogram", "customer jakis", "Opis Opisik", portfolioProgram);
        Project portfolioProgramSubprogramProject = inventoryService.createProject("PF1", "portfolioProgramSubprogramProject", "customer jakis", "Opis Opisik", portfolioProgramSubprogram);


        CategorizationServiceInterface categorizationService = new CategorizationService();

        //category
        Category category1 = categorizationService.createCategory("CA1", "Kategoria wyborna","opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        Category category2 = categorizationService.createCategory("CA2", "Kategoria mniej wyborna","opis kategorii ktory jest zwykle wyczerpuj¹cy");

        //category to portfolios
        categorizationService.createAreaOfFocus(portfolioTop, category1, (short) 65);
        categorizationService.createAreaOfFocus(portfolioTop, category2, (short) 35);
        categorizationService.createAreaOfFocus(subPortfolio, category2, (short) 100);

        //category to components
        categorizationService.createCategoryMembership(portfolioProject, category1);
        categorizationService.createCategoryMembership(subPortfolio, category2);
        categorizationService.createCategoryMembership(subPortfolioProgram, category2);
        categorizationService.createCategoryMembership(subPortfolioProgramProject, category2);
        categorizationService.createCategoryMembership(subPortfolioProject, category2);
        categorizationService.createCategoryMembership(portfolioProgramOperation, category1);
        categorizationService.createCategoryMembership(portfolioProgramProject, category1);
        categorizationService.createCategoryMembership(portfolioProgramSubprogram, category1);
        categorizationService.createCategoryMembership(portfolioProgramSubprogramProject, category1);

        //scroing criteria
        ScoringCriterion scoringCriterionROI = categorizationService.createScoringCriterion("ROI", "Return on investment", "Pi?kny opis tego czym ten wska?nik jest", SuperiorityStrategy.MAX);
        ScoringCriterion scoringCriterionEfficiency = categorizationService.createScoringCriterion("Eff", "Efficiency improvement", "Pi?kny opis tego czym ten wska?nik jest", SuperiorityStrategy.MAX);

        //criteria to category
        categorizationService.createCategoryEvaluation(scoringCriterionROI, category1);
        categorizationService.createCategoryEvaluation(scoringCriterionEfficiency, category2);

        //scores
        categorizationService.createScore(portfolioProject, scoringCriterionROI, new BigDecimal(456.232), "To jes dla tego, ze jest");
        categorizationService.createScore(portfolioProgramProject, scoringCriterionROI, new BigDecimal(346.232), "To jes dla tego, ze jest");
        categorizationService.createScore(portfolioProgramSubprogram, scoringCriterionROI, new BigDecimal(77.232), "To jes dla tego, ze jest");
        categorizationService.createScore(portfolioProgramSubprogramProject, scoringCriterionROI, new BigDecimal(654), "To jes dla tego, ze jest");

        categorizationService.createScore(subPortfolio, scoringCriterionEfficiency, new BigDecimal(66.232), "To jes dla tego, ze jest");
        categorizationService.createScore(subPortfolioProgram, scoringCriterionEfficiency, new BigDecimal(634.232), "To jes dla tego, ze jest");
        categorizationService.createScore(subPortfolioProgramProject, scoringCriterionEfficiency, new BigDecimal(226.232), "To jes dla tego, ze jest");
        categorizationService.createScore(subPortfolioProject, scoringCriterionEfficiency, new BigDecimal(236.232), "To jes dla tego, ze jest");


*/

    }
}
