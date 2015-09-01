package boot; /**
 * Created by Wojciech on 2015-05-15.
 */

import api.controller.CategorizationController;
import api.controller.InventoryController;
import api.controller.OrganizationController;
import org.apache.log4j.BasicConfigurator;
import service.categorization.CategorizationService;
import service.inventory.InventoryService;
import service.organization.OrganizationService;

import static spark.Spark.setPort;

public class Main {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        setPort(4567);
        // new UserAPIController(new UserServiceOrmLite());
        new InventoryController(new InventoryService());
        new CategorizationController(new CategorizationService(),new InventoryService());
        new OrganizationController(new OrganizationService());
    }
}
