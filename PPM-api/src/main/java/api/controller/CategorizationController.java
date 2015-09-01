package api.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.categorization.ScoringCriterion;
import model.inventory.Component;
import model.inventory.Portfolio;
import service.categorization.CategorizationServiceInterface;
import service.inventory.InventoryServiceInterface;
import spark.Request;
import spark.Response;
import spark.Spark;
import util.ComponentDeserializer;
import util.PortfolioJsonUtil;
import util.ResponseError;
import util.exception.CannotScoreIfNotMemberOfPortfolioException;

import java.math.BigDecimal;

import static spark.Spark.*;


/**
 * Created by Wojciech on 2015-05-15.
 */
public class CategorizationController {

    private Long getParentPortfolio(Component component){
        if (component.getParent() instanceof Portfolio){
            return component.getParent().getId();
        } else {
            return getParentPortfolio(component.getParent());
        }
    }
    public CategorizationController(CategorizationServiceInterface categorizationService, InventoryServiceInterface inventoryService) {
        //SCORE
        Spark.post("projects/:componentId/scoring_criteria/:criteriaId/create_score", (Request req, Response res) ->
            {
                String componentId = req.params(":componentId");
                String criteriaId = req.params(":criteriaId");
                Component component = inventoryService.getProject(Long.parseLong(componentId));
                Long portfolioId = getParentPortfolio(component);
                ScoringCriterion scoringCriterion = categorizationService.getScoringCriterion(Long.parseLong(criteriaId));
                BigDecimal value=new BigDecimal(0);
                if(scoringCriterion.getMinScore()!=null){
                    value=scoringCriterion.getMinScore();
                }
                try {
                    categorizationService.createScore(component,scoringCriterion,value,"");
                } catch (CannotScoreIfNotMemberOfPortfolioException e) {
                    res.status(400);
                    return new ResponseError("CannotScoreIfNotMemberOfPortfolioException: '%s'", componentId);
                }
                Portfolio portfolio = inventoryService.getPortfolio(portfolioId);
                if (portfolio != null) {
                    return portfolio;
                }
                res.status(400);
                return new ResponseError("No portfolio with id '%s' found", portfolioId.toString());
            }, PortfolioJsonUtil.json()
        );
        //PORTFOLIOS
        //Spark.get("/portfolios", (req, res) -> inventoryService.getPortfolios(), PortfolioJsonUtil.json());
        /*
        Spark.get("/portfolios/:id", (req, res) -> {
            String id = req.params(":id");
            Portfolio portfolio = inventoryService.getPortfolio(Long.parseLong(id));
            if (portfolio != null) {
                return portfolio;
            }
            res.status(400);
            return new ResponseError("No portfolio with id '%s' found", id);
        }, PortfolioJsonUtil.json());

        /*Spark.post("/portfolios", (req, res) -> inventoryService.createPortfolio(
                req.queryParams("code"),
                req.queryParams("name"),
                req.queryParams("description"),
                Long.parseLong(req.queryParams("parentComponent"))
        ), PortfolioJsonUtil.json());*/
/*
        put("/portfolios/:id", (Request req, Response res) -> {
            String id = req.params(":id");
            String body = req.body();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Component.class, new ComponentDeserializer())
                    .setDateFormat("dd/MM/yyyy")
                    .create();
            Portfolio portfolio = gson.fromJson(req.body(), Portfolio.class);
            Portfolio savedPortfolio = inventoryService.updatePortfolio(portfolio);
            Portfolio returnedPortfolio = inventoryService.getPortfolio(Long.parseLong(id));
            return returnedPortfolio;
        }, PortfolioJsonUtil.json());

        //COMPONENTS
        /*get("/portfolios/:id/components", (req, res) -> userService.getAllUsers(), util.PortfolioJsonUtil.json());

        get("/portfolios/:id/components/:cid", (req, res) -> {
            String id = req.params(":id");
            User user = userService.getUser(id);
            if (user != null) {
                return user;
            }
            res.status(400);
            return new util.ResponseError("No user with id '%s' found", id);
        }, util.PortfolioJsonUtil.json());

        post("/portfolios/:id/components/:cid", (req, res) -> userService.createUser(
                req.queryParams("name"),
                req.queryParams("email")
        ), util.PortfolioJsonUtil.json());

        put("/portfolios/:id/components/:cid", (req, res) -> userService.updateUser(
                req.params(":id"),
                req.queryParams("name"),
                req.queryParams("email")
        ), util.PortfolioJsonUtil.json());
        */
        /*Spark.before((request,response)->{
            String method = request.requestMethod();
            if(method.equals("POST") || method.equals("PUT") || method.equals("DELETE")){
                String authentication = request.headers("Authentication");
                if(!"PASSWORD".equals(authentication)){
                    Spark.halt(401, "User Unauthorized");
                }
            }
        });*/
        Spark.options("/*", (request,response)->{

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if(accessControlRequestMethod != null){
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        /*Spark.before((request,response)->{
            response.header("Access-Control-Allow-Origin", "*");
        });
*/

        //UTILS
        after((req, res) -> {
            res.type("application/json");
        });

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(PortfolioJsonUtil.toJson(new ResponseError(e)));
        });
    }
}
