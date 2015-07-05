package boot; /**
 * Created by Wojciech on 2015-05-15.
 */

import api.controller.InventoryController;
import service.inventory.InventoryService;

import static spark.Spark.setPort;

public class Main {

    public static void main(String[] args) {
        setPort(4567);
        // new UserAPIController(new UserServiceOrmLite());
        new InventoryController(new InventoryService());
    }
}
