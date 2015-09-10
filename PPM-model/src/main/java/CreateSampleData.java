import model.categorization.Category;
import model.categorization.CategoryMembership;
import model.categorization.ScoringCriterion;
import model.categorization.enums.CriterionType;
import model.categorization.enums.SuperiorityStrategy;
import model.finance.Budget;
import model.finance.Cost;
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
import service.finance.FinanceService;
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
        FinanceService fs = new FinanceService();
        ProcessService ps = new ProcessService();

        //Users
        User user1 = os.createUser("Wojciech111", "php789", "oxio22@gmail.com");
        User user2 = os.createUser("TestUser", "php789", "test@gmail.com");

        //Organizations
        Organization org1 = os.createOrganization("History Machine Organization", "HistoryMachine");
        Organization org2 = os.createOrganization("Portfolio Manager", "Portfolio Manager");

        //Employees
        Employee employee_user1_org1 = os.createEmployee("Wojciech", "Oksi�ski", org1, user1);
        Employee employee_user1_org2 = os.createEmployee("Wojciech", "Oksi�ski", org2, user1);
        Employee employee_user2_org2 = os.createEmployee("Jan", "Testowy", org2, user2);

        //Portfolios
        Portfolio portfolio2 = is.createPortfolio("PrtflMngr", "Portfolio Manager App",
                CustomerType.EX, "Rynek rozwi�za� PPM", null,
                "Wojciech Oksi�ski", "Wype�nienie luki rynkowej rozwi�za� wspomagajacych ma�e i �rednie przedsi�biorstwa zoreintowane projektowo",
                "Stworzenie narz�dzia wspomagaj�cego organizacje w zarz�dzaniu portfelem projekt�w",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime() + 2000), user2.getUsername(),
                null, null, org2);
        Portfolio portfolio1 = is.createPortfolio("HM.org", "HistoryMachine.org",
                CustomerType.EX, "Rynek rozwi�za� PPM", null,
                "Wojciech Oksi�ski", "Wype�nienie luki rynkowej rozwi�za� wspomagajacych ma�e i �rednie przedsi�biorstwa zoreintowane projektowo",
                "Stworzenie narz�dzia wspomagaj�cego organizacje w zarz�dzaniu portfelem projekt�w",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime() + 2000), user2.getUsername(),
                null, null, org1);

        //Processes
        Process process1 = ps.createProcess("PPM Classic", "Klasyczny ppm", portfolio2);
        Process process2 = ps.createProcess("IT Process", "Proces IT", portfolio2);

        //Budgets
        Budget budget1 = fs.createBudget(portfolio2,org2,
                new java.sql.Date(new Date().getTime()),new java.sql.Date(new Date().getTime()),
                new BigDecimal(500), "Project budget","Description");

        //States
        State state1_arch = ps.createState(process1, "Archived", "Evaluation stage", StateType.A, 155,155,120,
                9);
        State state1_8 = ps.createState(process1, "Finished", "Finished stage", StateType.A, 166,166,166,
                8);
        State state1_7 = ps.createState(process1, "Collecting benefits", "Collecting stage", StateType.A, 155,0,75,
                7);
        State state1_6 = ps.createState(process1, "Executing", "Executing stage. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce finibus pulvinar dui et aliquet. Nunc pretium purus vitae odio tempor sagittis. Sed at accumsan sapien. Sed imperdiet venenatis purus, et sagittis justo dapibus at. Sed pretium justo vitae sapien venenatis elementum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Morbi bibendum sagittis ligula quis tempor. Morbi magna neque, maximus ac ipsum et, tempor tempor ligula.",
                StateType.X, 0,255,0,
                6);
        State state1_5 = ps.createState(process1, "Waiting for resources", "Waiting stage.", StateType.W, 102,255,102,
                5);
        State state1_4 = ps.createState(process1, "Budgeting", "Budgeting stage", StateType.V, 0,0,155,
                4);
        State state1_3 = ps.createState(process1, "Planning", "Planning stage", StateType.P, 0,128,255,
                3);
        State state1_2 = ps.createState(process1, "Evaluation", "Evaluation stage", StateType.E, 155,155,0,
                2);
        State state1_1 = ps.createState(process1, "Initiation", "Initiation stage", StateType.B, 255,255,0,
                1);

        //Programs
        Program program2_1 = is.createProgram("Prgrm2.1", "Portfolio Manager App version 1.0 development",
                CustomerType.EX, "Rynek", "WO", "WO",
                "Stworzenie dzia�aj�cej aplikacji do zarz�dzania portfelem projekt�w",
                "Stworzenie dzia�aj�cej aplikacji do zarz�dzania portfelem projekt�w",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime()), user2.getUsername(),
                portfolio2, state1_1,process1, null, null, null, null,
                null, null, null,
                new BigDecimal(40), new BigDecimal(5),
                new BigDecimal(10), new BigDecimal(10));
        Program program2_1_3 = is.createProgram("Prg2.1.3", "Devevelopment of Web App",
                CustomerType.EX, "Rynek", "WO", "WO",
                "Stworzenie dzia�aj�cej aplikacji przegl�darkowej do zarz�dzania portfelem projekt�w",
                "Stworzenie dzia�aj�cej aplikacji przegl�darkowej do zarz�dzania portfelem projekt�w",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime()), user2.getUsername(),
                portfolio2, state1_1,process1, null, null, null, null,
                null, null, null,
                null, null, null, null);
        //Operations
        Operation operation = is.createOperation("Op2.1", "Nadz�r promotora nad kszta�tem narz�dzia",
                CustomerType.EX, "Rynek", "WO", "WO",
                "Stworzenie dzia�aj�cej aplikacji przegl�darkowej do zarz�dzania portfelem projekt�w",
                "Stworzenie dzia�aj�cej aplikacji przegl�darkowej do zarz�dzania portfelem projekt�w",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime()), user2.getUsername(),
                program2_1, state1_1,process1,
                OperationType.P, RecursionType.D, 14);




        //Projects
        Project project2_1_1 = is.createProject("Prjc2.1.1", "Creation of database",
                CustomerType.EX, "Rynek", "WO", "WO",
                "Stworzenie dzia�aj�cej aplikacji do zarz�dzania portfelem projekt�w",
                "Stworzenie dzia�aj�cej aplikacji do zarz�dzania portfelem projekt�w",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime()), user2.getUsername(),
                program2_1, state1_1,process1,null,  null,  null,  null,
                null,  null,  null);


        Project project2_1_2 = is.createProject("Prjc2.1.2", "Creation of Web API",
                CustomerType.EX, "Rynek", "WO", "WO",
                "Stworzenie dzia�aj�cej aplikacji do zarz�dzania portfelem projekt�w",
                "Stworzenie dzia�aj�cej aplikacji do zarz�dzania portfelem projekt�w",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime()), user2.getUsername(),
                program2_1, state1_1,process1,null,  null,  null,  null,
                null,  null,  null);


        Project project2_1_3_1 = is.createProject("Pj2.1.3.1", "Creation of web app architecture in FLUX",
                CustomerType.EX, "Rynek", "WO", "WO",
                "Stworzenie dzia�aj�cej aplikacji do zarz�dzania portfelem projekt�w",
                "Stworzenie dzia�aj�cej aplikacji do zarz�dzania portfelem projekt�w",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime()), user2.getUsername(),
                program2_1_3, state1_1,process1,null,  null,  null,  null,
                null,  null,  null);


        Project project2_1_3_2 = is.createProject("Pj2.1.3.2", "Creation of Views",
                CustomerType.EX, "Rynek", "WO", "WO",
                "Stworzenie dzia�aj�cej aplikacji do zarz�dzania portfelem projekt�w",
                "Stworzenie dzia�aj�cej aplikacji do zarz�dzania portfelem projekt�w",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime()), user2.getUsername(),
                program2_1_3, state1_1,process1,null,  null,  null,  null,
                null,  null,  null);


        Project project2_2 = is.createProject("Prjc2.2", "Zaznajomienie si� z litaratur� dziedziny",
                CustomerType.IN, "WO", "WO", "WO",
                "Stworzenie dzia�aj�cej aplikacji do zarz�dzania portfelem projekt�w",
                "Stworzenie dzia�aj�cej aplikacji do zarz�dzania portfelem projekt�w",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime()), user2.getUsername(),
                portfolio2, state1_1,process1,null,  null,  null,  null,
                null,  null,  null);


        Project project2_3 = is.createProject("Prjc2.3", "Writing thesis",
                CustomerType.EX, "Rynek", "WO", "WO",
                "Stworzenie dzia�aj�cej aplikacji do zarz�dzania portfelem projekt�w",
                "Stworzenie dzia�aj�cej aplikacji do zarz�dzania portfelem projekt�w",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime()), user2.getUsername(),
                portfolio2, state1_1,process1,null,  null,  null,  null,
                null,  null,  null);


        Project project2_4 = is.createProject("Prjc2.4", "Deployment of version 1.0 to the cloud",
                CustomerType.EX, "Rynek", "WO", "WO",
                "Stworzenie dzia�aj�cej aplikacji do zarz�dzania portfelem projekt�w",
                "Stworzenie dzia�aj�cej aplikacji do zarz�dzania portfelem projekt�w",
                new Timestamp(new Date().getTime()), user1.getUsername(),
                new Timestamp(new Date().getTime()), user2.getUsername(),
                portfolio2, state1_1,process1,null,  null,  null,  null,
                null,  null,  null);

        //Stakeholders

        //Costs
        Cost cost2_1_3_1 = fs.createCost("Costs of learning materials", new BigDecimal(100), project2_1_3_1,
                "Costs of buying learning materials about React and FLux architecture", 1);
        //Budgets

        //CATEGORIZATION

        //Category
        Category category1 = cs.createCategory("ProductQ", "Product quality improvement","Opis kategorii ktory jest niezwykle wyczerpuj�cy", 0,77,64);
        Category category2 = cs.createCategory("ProductDev", "Product development","Opis kategorii ktory jest niezwykle wyczerpuj�cy",74,20,140);
        Category category3 = cs.createCategory("Customers", "Acquire customers","Opis kategorii ktory jest niezwykle wyczerpuj�cy", 183,28,28);
        Category category4 = cs.createCategory("Profit", "Profit growth","Opis kategorii ktory jest niezwykle wyczerpuj�cy", 62,39,35);
        Category category5 = cs.createCategory("HRQ", "Human resources quality improvment","Opis kategorii ktory jest niezwykle wyczerpuj�cy", 130,119,23);

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

        ScoringCriterion scoringCriterion1_1 = cs.createScoringCriterion("CDI", "Code maintenance increase",
                "Description", "How it will increase code maintenance",
                SuperiorityStrategy.MAX, CriterionType.P,
                new BigDecimal(1),new BigDecimal(10),portfolio2);

        
        
         ScoringCriterion scoringCriterion1_2 = cs.createScoringCriterion("CDI", "Development speed increase",
                "Description", "How it will increase development speed",
                SuperiorityStrategy.MAX, CriterionType.P,new BigDecimal(1),new BigDecimal(10),portfolio2);

        
        
         ScoringCriterion scoringCriterion1_3 = cs.createScoringCriterion("OMI", "Overall maintainability increase ",
                "Description", null,
                SuperiorityStrategy.MAX, CriterionType.P,new BigDecimal(1),new BigDecimal(10),portfolio2);

        
        
         ScoringCriterion scoringCriterion1_4 = cs.createScoringCriterion("CD", "Cost decrease",
                "Description", null,
                SuperiorityStrategy.MAX, CriterionType.P,new BigDecimal(0),null,portfolio2);

        
        
         ScoringCriterion scoringCriterion2_1 = cs.createScoringCriterion("NNF", "No of new features ",
                "Description", "What are the features",
                SuperiorityStrategy.MAX, CriterionType.P,new BigDecimal(1),new BigDecimal(10),portfolio2);

        
        
         ScoringCriterion scoringCriterion2_2 = cs.createScoringCriterion("CFI", "Current functions improvement",
                "Description", "How it will improve current functions",
                SuperiorityStrategy.MAX, CriterionType.P,new BigDecimal(1),new BigDecimal(10),portfolio2);

        
        
         ScoringCriterion scoringCriterion3_1 = cs.createScoringCriterion("CGNW", "Customer growth in new markets",
                "Description", "Why",
                SuperiorityStrategy.MAX, CriterionType.P,null,null,portfolio2);

        
        
         ScoringCriterion scoringCriterion3_2 = cs.createScoringCriterion("CGCM", "Customer growth in current markets",
                "Description", "Why",
                SuperiorityStrategy.MAX, CriterionType.P,null,null,portfolio2);

        
        
         ScoringCriterion scoringCriterion4_1 = cs.createScoringCriterion("PI", "Profit increase",
                "Description", "Why it will increase profit",
                SuperiorityStrategy.MAX, CriterionType.P,null,null,portfolio2);

        
        
         ScoringCriterion scoringCriterion5_1 = cs.createScoringCriterion("NNS", "Need of new skill",
                "Description", "Why",
                SuperiorityStrategy.MAX, CriterionType.P,new BigDecimal(1),new BigDecimal(10),portfolio2);

        
        
         ScoringCriterion scoringCriterion5_2 = cs.createScoringCriterion("CDI", "Usage of new skill",
                "Description", "Why",
                SuperiorityStrategy.MAX, CriterionType.P,new BigDecimal(1),new BigDecimal(10),portfolio2);

        
        
         ScoringCriterion scoringCriterionX_1 = cs.createScoringCriterion("NoU", "Number of users",
                "Description", "How it will increase code maintenance",
                SuperiorityStrategy.MAX, CriterionType.A,new BigDecimal(1),null,portfolio2);

        
        //Category to ScoringCriteria

        cs.createCategoryEvaluation(scoringCriterion1_1, category1, "N",(short) 10 );
        cs.createCategoryEvaluation(scoringCriterion1_2, category1, "Y",(short) 30 );
        cs.createCategoryEvaluation(scoringCriterion1_3, category1, "N",(short) 10 );
        cs.createCategoryEvaluation(scoringCriterion1_4, category1, "N",(short) 50 );
        cs.createCategoryEvaluation(scoringCriterion2_1, category2, "N",(short) 30 );
        cs.createCategoryEvaluation(scoringCriterion2_2, category2, "N",(short) 70 );
        cs.createCategoryEvaluation(scoringCriterion3_1, category3, "Y",(short) 80 );
        cs.createCategoryEvaluation(scoringCriterion3_2, category3, "N",(short) 20 );
        cs.createCategoryEvaluation(scoringCriterion4_1, category4, "N",(short) 40 );
        cs.createCategoryEvaluation(scoringCriterion5_1, category5, "N",(short) 50 );
        cs.createCategoryEvaluation(scoringCriterion5_2, category5, "Y",(short) 60 );

        //Scores

        cs.createScore(project2_1_3_1, scoringCriterion1_1, new BigDecimal(3), "To jest dla tego, �e jest");
        cs.createScore(project2_1_3_1, scoringCriterion1_2, new BigDecimal(3), "To jest dla tego, �e jest");
        cs.createScore(project2_1_3_1, scoringCriterion1_3, new BigDecimal(9), "To jest dla tego, �e jest");
        cs.createScore(project2_1_3_1, scoringCriterion1_4, new BigDecimal(0), "To jest dla tego, �e jest");
        cs.createScore(project2_1_3_1, scoringCriterion2_1, new BigDecimal(0), "To jest dla tego, �e jest");
        cs.createScore(project2_1_3_1, scoringCriterion2_2, new BigDecimal(3), "To jest dla tego, �e jest");

        



    }
}
