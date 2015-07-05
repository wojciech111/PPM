package api.controller;

import model.inventory.Portfolio;
import service.inventory.InventoryServiceInterface;
import spark.Spark;
import util.JsonUtil;
import util.ResponseError;

import static spark.Spark.*;


/**
 * Created by Wojciech on 2015-05-15.
 */
public class InventoryController {
    public InventoryController(InventoryServiceInterface inventoryService) {
        //PORTFOLIOS
        //Spark.get("/portfolios", (req, res) -> inventoryService.getPortfolios(), JsonUtil.json());

        Spark.get("/portfolios/:id", (req, res) -> {
            String id = req.params(":id");
            Portfolio portfolio = inventoryService.getPortfolio(Long.parseLong(id));
            if (portfolio != null) {
                return portfolio;
            }
            res.status(400);
            return new ResponseError("No portfolio with id '%s' found", id);
        }, JsonUtil.json());

        /*Spark.post("/portfolios", (req, res) -> inventoryService.createPortfolio(
                req.queryParams("code"),
                req.queryParams("name"),
                req.queryParams("description"),
                Long.parseLong(req.queryParams("parentComponent"))
        ), JsonUtil.json());
/*
        put("/portfolios/:id", (req, res) -> inventoryService.updatePortfolio(
                req.params(":id"),
                req.queryParams("code"),
                req.queryParams("name"),
                req.queryParams("description")
        ), util.JsonUtil.json());

        //COMPONENTS
        /*get("/portfolios/:id/components", (req, res) -> userService.getAllUsers(), util.JsonUtil.json());

        get("/portfolios/:id/components/:cid", (req, res) -> {
            String id = req.params(":id");
            User user = userService.getUser(id);
            if (user != null) {
                return user;
            }
            res.status(400);
            return new util.ResponseError("No user with id '%s' found", id);
        }, util.JsonUtil.json());

        post("/portfolios/:id/components/:cid", (req, res) -> userService.createUser(
                req.queryParams("name"),
                req.queryParams("email")
        ), util.JsonUtil.json());

        put("/portfolios/:id/components/:cid", (req, res) -> userService.updateUser(
                req.params(":id"),
                req.queryParams("name"),
                req.queryParams("email")
        ), util.JsonUtil.json());
        */


        //UTILS
        after((req, res) -> {
            res.type("application/json");
        });

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(JsonUtil.toJson(new ResponseError(e)));
        });
    }
}
