import model.categorization.Category;
import model.categorization.CategoryMembership;
import model.categorization.ScoringCriterion;
import model.categorization.enums.SuperiorityStrategy;
import model.inventory.*;
import model.inventory.enums.CustomerType;
import model.inventory.enums.OperationType;
import model.inventory.enums.RecursionType;
import model.organization.Employee;
import model.organization.Organization;
import model.organization.User;
import model.process.*;
import model.process.Process;
import model.process.enums.StateType;
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
public class CreateSampleData {
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
            session.createQuery("update Component set state = null").executeUpdate();

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

        //Organizations
        Organization org1 = os.createOrganization("History Machine Organization", "Hisotr yMachine");
        Organization org2 = os.createOrganization("Portfolio Manager", "Portfolio Manager");

        //Employees
        Employee employee_user1_org1 = os.createEmployee("Wojciech", "Oksiñski", org1, user1);
        Employee employee_user1_org2 = os.createEmployee("Wojciech", "Oksiñski", org2, user1);
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

        //Processes
        Process process1 = ps.createProcess("PPM Classic", "Klasyczny ppm", portfolio2);
        Process process2 = ps.createProcess("IT Process", "Proces IT", portfolio2);


        //State helpers
        State state1_arch = ps.createState(process1, "1.A Archived", "Evaluation stage", StateType.A, "440000",
                null, null);
        State state1_can = ps.createState(process1, "1.C Canceled", "Evaluation stage", StateType.C, "440000",
                state1_arch, null);
        State state1_del = ps.createState(process1, "1.D Delayed", "Evaluation stage", StateType.D, "440000",
                state1_can, null);

        //States helpers
        State state1_8 = ps.createState(process1, "1.8 Finished", "Finished stage", StateType.A, "440000",
                state1_arch, null);
        State state1_7 = ps.createState(process1, "1.7 Collecting benefits", "Collecting stage", StateType.A, "440000",
                state1_8, state1_del);
        State state1_6 = ps.createState(process1, "1.6 Executing", "Executing stage", StateType.X, "440000",
                state1_7, state1_del);
        State state1_5 = ps.createState(process1, "1.5 Waiting for resources", "Waiting stage", StateType.W, "440000",
                state1_6, state1_del);
        State state1_4 = ps.createState(process1, "1.4 Budgeting", "Budgeting stage", StateType.V, "440000",
                state1_5, state1_del);
        State state1_3 = ps.createState(process1, "1.3 Planning", "Planning stage", StateType.P, "440000",
                state1_4, state1_del);
        State state1_2 = ps.createState(process1, "1.2 Evaluation", "Evaluation stage", StateType.E, "440000",
                state1_3, state1_del);
        State state1_1 = ps.createState(process1, "1.1 Initiation", "Initiation stage", StateType.B, "440000",
                state1_2, state1_del);

        //Programs
        Program program2_1 = is.createProgram("Prgrm2.1", "Portfolio Manager App version 1.0 development",
                CustomerType.EX, "Rynek", "WO", "WO",
                "Stworzenie dzia³aj¹cej aplikacji do zarz¹dzania portfelem projektów",
                "Stworzenie dzia³aj¹cej aplikacji do zarz¹dzania portfelem projektów",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime()), user2.getUsername(),
                portfolio2, state1_1, null, null, null, null,
                null, null, null,
                new BigDecimal(40), new BigDecimal(5),
                new BigDecimal(10), new BigDecimal(10));
        Program program2_1_3 = is.createProgram("Prg2.1.3", "Devevelopment of Web App",
                CustomerType.EX, "Rynek", "WO", "WO",
                "Stworzenie dzia³aj¹cej aplikacji przegl¹darkowej do zarz¹dzania portfelem projektów",
                "Stworzenie dzia³aj¹cej aplikacji przegl¹darkowej do zarz¹dzania portfelem projektów",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime()), user2.getUsername(),
                portfolio2, state1_1, null, null, null, null,
                null, null, null,
                null, null, null, null);
        //Operations
        Operation operation = is.createOperation("Op2.1", "Nadzór promotora nad kszta³tem narzêdzia",
                CustomerType.EX, "Rynek", "WO", "WO",
                "Stworzenie dzia³aj¹cej aplikacji przegl¹darkowej do zarz¹dzania portfelem projektów",
                "Stworzenie dzia³aj¹cej aplikacji przegl¹darkowej do zarz¹dzania portfelem projektów",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime()), user2.getUsername(),
                program2_1, state1_1,
                OperationType.P, RecursionType.D, 14);




        //Projects
        Project project2_1_1 = is.createProject("Prjc2.1.1", "Creation of database",
                CustomerType.EX, "Rynek", "WO", "WO",
                "Stworzenie dzia³aj¹cej aplikacji do zarz¹dzania portfelem projektów",
                "Stworzenie dzia³aj¹cej aplikacji do zarz¹dzania portfelem projektów",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime()), user2.getUsername(),
                program2_1, state1_1 ,null,  null,  null,  null,
                null,  null,  null);


        Project project2_1_2 = is.createProject("Prjc2.1.2", "Creation of Web API",
                CustomerType.EX, "Rynek", "WO", "WO",
                "Stworzenie dzia³aj¹cej aplikacji do zarz¹dzania portfelem projektów",
                "Stworzenie dzia³aj¹cej aplikacji do zarz¹dzania portfelem projektów",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime()), user2.getUsername(),
                program2_1, state1_1 ,null,  null,  null,  null,
                null,  null,  null);


        Project project2_1_3_1 = is.createProject("Pj2.1.3.1", "Creation of web app architecture in FLUX",
                CustomerType.EX, "Rynek", "WO", "WO",
                "Stworzenie dzia³aj¹cej aplikacji do zarz¹dzania portfelem projektów",
                "Stworzenie dzia³aj¹cej aplikacji do zarz¹dzania portfelem projektów",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime()), user2.getUsername(),
                program2_1_3, state1_1 ,null,  null,  null,  null,
                null,  null,  null);


        Project project2_1_3_2 = is.createProject("Pj2.1.3.2", "Creation of Views",
                CustomerType.EX, "Rynek", "WO", "WO",
                "Stworzenie dzia³aj¹cej aplikacji do zarz¹dzania portfelem projektów",
                "Stworzenie dzia³aj¹cej aplikacji do zarz¹dzania portfelem projektów",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime()), user2.getUsername(),
                program2_1_3, state1_1 ,null,  null,  null,  null,
                null,  null,  null);


        Project project2_2 = is.createProject("Prjc2.2", "Zaznajomienie siê z litaratur¹ dziedziny",
                CustomerType.IN, "WO", "WO", "WO",
                "Stworzenie dzia³aj¹cej aplikacji do zarz¹dzania portfelem projektów",
                "Stworzenie dzia³aj¹cej aplikacji do zarz¹dzania portfelem projektów",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime()), user2.getUsername(),
                portfolio2, state1_1 ,null,  null,  null,  null,
                null,  null,  null);


        Project project2_3 = is.createProject("Prjc2.3", "Napisanie pracy in¿ynierskiej",
                CustomerType.EX, "Rynek", "WO", "WO",
                "Stworzenie dzia³aj¹cej aplikacji do zarz¹dzania portfelem projektów",
                "Stworzenie dzia³aj¹cej aplikacji do zarz¹dzania portfelem projektów",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime()), user2.getUsername(),
                portfolio2, state1_1 ,null,  null,  null,  null,
                null,  null,  null);


        Project project2_4 = is.createProject("Prjc2.4", "Deployent of version 1.0 to the cloud",
                CustomerType.EX, "Rynek", "WO", "WO",
                "Stworzenie dzia³aj¹cej aplikacji do zarz¹dzania portfelem projektów",
                "Stworzenie dzia³aj¹cej aplikacji do zarz¹dzania portfelem projektów",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime()), user2.getUsername(),
                portfolio2, state1_1 ,null,  null,  null,  null,
                null,  null,  null);

        //Stakeholders

        //Costs
        //Budgets

        //CATEGORIZATION

        //Category
        Category category1 = cs.createCategory("ProductQ", "Product quality improvement","Opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        Category category2 = cs.createCategory("ProductDev", "Product development","Opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        Category category3 = cs.createCategory("Customers", "Acquire customers","Opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        Category category4 = cs.createCategory("Profit", "Profit growth","Opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        Category category5 = cs.createCategory("HRQ", "Human resources quality improvment","Opis kategorii ktory jest niezwykle wyczerpuj¹cy");

        //AreaOfFocus

        cs.createAreaOfFocus(portfolio2, category1, (short) 15);
        cs.createAreaOfFocus(portfolio2, category2, (short) 60);
        cs.createAreaOfFocus(portfolio2, category3, (short) 0);
        cs.createAreaOfFocus(portfolio2, category4, (short) 10);
        cs.createAreaOfFocus(portfolio2, category5, (short) 15);


        //Categories to components (Component component, Category category, Short percentageOfSupport, BigDecimal overallScore, Short rankInCategory)
        cs.createCategoryMembership(program2_1, category4, (short) 100, new BigDecimal(2.4), (short) 2);
        cs.createCategoryMembership(program2_1, category2, (short) 100, new BigDecimal(3.9), (short) 4);
        cs.createCategoryMembership(program2_1, category1, (short) 80, new BigDecimal(2.7), (short) 2);


        cs.createCategoryMembership(project2_1_1, category2, (short) 100, new BigDecimal(1.1), (short) 1);
        cs.createCategoryMembership(project2_1_2, category2, (short) 100, new BigDecimal(2.7), (short) 2);

        cs.createCategoryMembership(program2_1_3, category2, (short) 100, new BigDecimal(3.9), (short) 4);
        cs.createCategoryMembership(program2_1_3, category1, (short) 80, new BigDecimal(2.7), (short) 2);

        cs.createCategoryMembership(project2_1_3_1, category1, (short) 100, new BigDecimal(1.1), (short) 1);
        cs.createCategoryMembership(project2_1_3_1, category2, (short) 50, new BigDecimal(2.7), (short) 2);

        cs.createCategoryMembership(project2_1_3_2, category1, (short) 50, new BigDecimal(4.1), (short) 4);
        cs.createCategoryMembership(project2_1_3_2, category2, (short) 100, new BigDecimal(2.7), (short) 2);
        //end of programs

        cs.createCategoryMembership(project2_2, category5, (short) 100, new BigDecimal(1.7), (short) 1);

        cs.createCategoryMembership(project2_3, category3, (short) 100, new BigDecimal(1.0), (short) 1);
        cs.createCategoryMembership(project2_3, category4, (short) 100, new BigDecimal(2.7), (short) 2);

        cs.createCategoryMembership(project2_4, category4, (short) 100, new BigDecimal(1.7), (short) 1);
        cs.createCategoryMembership(project2_4, category3, (short) 100, new BigDecimal(1.5), (short) 1);
        cs.createCategoryMembership(project2_4, category1, (short) 40, new BigDecimal(2.7), (short) 2);

        //Scoring criteria



/*

        //scroing criteria
        ScoringCriterion scoringCriterionROI = cs.createScoringCriterion("ROI", "Return on investment", "Pi?kny opis tego czym ten wska?nik jest", SuperiorityStrategy.MAX);
        ScoringCriterion scoringCriterionEfficiency = cs.createScoringCriterion("Eff", "Efficiency improvement", "Pi?kny opis tego czym ten wska?nik jest", SuperiorityStrategy.MAX);



        Project portfolioProject = is.createProject("PF1", "portfolioProject", "customer jakis", "Opis Opisik", portfolioTop);

        //subportfolio
        Portfolio subPortfolio = is.createPortfolio("SPT", "subPortfolio", "customer jakis", "Opis Opisik", portfolioTop);
        Program subPortfolioProgram = is.createProgram("PF1", "subPortfolioProgram", "customer jakis", "Opis Opisik", subPortfolio);
        Project subPortfolioProgramProject = is.createProject("PF1", "subPortfolioProgramProject", "customer jakis", "Opis Opisik", subPortfolioProgram);
        Project subPortfolioProject = is.createProject("PF1", "subPortfolioProject", "customer jakis", "Opis Opisik", subPortfolio);

        //program
        Program portfolioProgram = is.createProgram("PF1", "portfolioProgram", "customer jakis", "Opis Opisik", portfolioTop);
        Operation portfolioProgramOperation = is.createOperation("PF2", "portfolioProgramOperation", "customer jakis", "Opis Opisik", portfolioProgram, RecursionType.M);
        Project portfolioProgramProject = is.createProject("PF1", "portfolioProgramProject", "customer jakis", "Opis Opisik", portfolioProgram);

        //subprogram
        Program portfolioProgramSubprogram = is.createProgram("PF1", "portfolioProgramSubprogram", "customer jakis", "Opis Opisik", portfolioProgram);
        Project portfolioProgramSubprogramProject = is.createProject("PF1", "portfolioProgramSubprogramProject", "customer jakis", "Opis Opisik", portfolioProgramSubprogram);



        //category
        Category category1 = cs.createCategory("CA1", "Kategoria wyborna","opis kategorii ktory jest niezwykle wyczerpuj¹cy");
        Category category2 = cs.createCategory("CA2", "Kategoria mniej wyborna","opis kategorii ktory jest zwykle wyczerpuj¹cy");

        //category to portfolios
        cs.createAreaOfFocus(portfolioTop, category1, (short) 65);
        cs.createAreaOfFocus(portfolioTop, category2, (short) 35);
        cs.createAreaOfFocus(subPortfolio, category2, (short) 100);

        //category to components
        cs.createCategoryMembership(portfolioProject, category1);
        cs.createCategoryMembership(subPortfolio, category2);
        cs.createCategoryMembership(subPortfolioProgram, category2);
        cs.createCategoryMembership(subPortfolioProgramProject, category2);
        cs.createCategoryMembership(subPortfolioProject, category2);
        cs.createCategoryMembership(portfolioProgramOperation, category1);
        cs.createCategoryMembership(portfolioProgramProject, category1);
        cs.createCategoryMembership(portfolioProgramSubprogram, category1);
        cs.createCategoryMembership(portfolioProgramSubprogramProject, category1);

        //scroing criteria
        ScoringCriterion scoringCriterionROI = cs.createScoringCriterion("ROI", "Return on investment", "Pi?kny opis tego czym ten wska?nik jest", SuperiorityStrategy.MAX);
        ScoringCriterion scoringCriterionEfficiency = cs.createScoringCriterion("Eff", "Efficiency improvement", "Pi?kny opis tego czym ten wska?nik jest", SuperiorityStrategy.MAX);

        //criteria to category
        cs.createCategoryEvaluation(scoringCriterionROI, category1);
        cs.createCategoryEvaluation(scoringCriterionEfficiency, category2);

        //scores
        cs.createScore(portfolioProject, scoringCriterionROI, new BigDecimal(456.232), "To jes dla tego, ze jest");
        cs.createScore(portfolioProgramProject, scoringCriterionROI, new BigDecimal(346.232), "To jes dla tego, ze jest");
        cs.createScore(portfolioProgramSubprogram, scoringCriterionROI, new BigDecimal(77.232), "To jes dla tego, ze jest");
        cs.createScore(portfolioProgramSubprogramProject, scoringCriterionROI, new BigDecimal(654), "To jes dla tego, ze jest");

        cs.createScore(subPortfolio, scoringCriterionEfficiency, new BigDecimal(66.232), "To jes dla tego, ze jest");
        cs.createScore(subPortfolioProgram, scoringCriterionEfficiency, new BigDecimal(634.232), "To jes dla tego, ze jest");
        cs.createScore(subPortfolioProgramProject, scoringCriterionEfficiency, new BigDecimal(226.232), "To jes dla tego, ze jest");
        cs.createScore(subPortfolioProject, scoringCriterionEfficiency, new BigDecimal(236.232), "To jes dla tego, ze jest");

*/


    }
}
