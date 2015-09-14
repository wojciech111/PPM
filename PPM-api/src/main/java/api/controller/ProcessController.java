package api.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.categorization.ScoringCriterion;
import model.inventory.Component;
import model.inventory.Portfolio;
import model.inventory.Project;
import model.organization.User;
import model.process.Decision;
import model.process.State;
import service.inventory.InventoryServiceInterface;
import service.organization.OrganizationServiceInterface;
import service.process.ProcessService;
import spark.Request;
import spark.Response;
import spark.Spark;
import util.*;
import util.exception.CannotScoreIfNotMemberOfPortfolioException;

import java.math.BigDecimal;

import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.put;

/**
 * Created by Wojciech on 2015-08-15.
 */
public class ProcessController {
    private Long getParentPortfolio(Component component){
        if (component.getParent() instanceof Portfolio){
            return component.getParent().getId();
        } else {
            return getParentPortfolio(component.getParent());
        }
    }
    public ProcessController(ProcessService processService, InventoryServiceInterface inventoryService) {

        //DECISION
        Spark.post("projects/:componentId/decisions", (Request req, Response res) ->
                {
                    String componentId = req.params(":componentId");
                    String body = req.body();
                    Gson gson = new Gson();
                    CreateDecisionRequest decisionRequest = gson.fromJson(body, CreateDecisionRequest.class);
                    Project project = inventoryService.getProject(Long.parseLong(componentId));
                    State fromState=processService.getState(Long.parseLong(decisionRequest.getFromStateId()));
                    State toState=processService.getState(Long.parseLong(decisionRequest.getToStateId()));
                    Long portfolioId = getParentPortfolio(project);
                    Decision decision = processService.createDecision(project,fromState, toState,
                            decisionRequest.getDecisionState(), decisionRequest.getDecisionType(), decisionRequest.getMotivation(),
                            inventoryService);
                    Portfolio portfolio = inventoryService.getPortfolio(portfolioId);
                    if (portfolio != null) {
                        return portfolio;
                    }
                    res.status(400);
                    return new ResponseError("No portfolio with id '%s' found", portfolioId.toString());
                }, PortfolioJsonUtil.json()
        );
        put("projects/:componentId/decisions/:id", (Request req, Response res) -> {
            String componentId = req.params(":componentId");
            String decisionId = req.params(":id");
            String body = req.body();
            Project project = inventoryService.getProject(Long.parseLong(componentId));
            Gson gson = new GsonBuilder()
                    //.registerTypeAdapter(Component.class, new ComponentDeserializer())
                    .setDateFormat("dd/MM/yyyy")
                    .create();
            Decision decision = gson.fromJson(req.body(), Decision.class);
            Long portfolioId = getParentPortfolio(project);
            Decision savedDecision = processService.updateDecision(project,decision, inventoryService);
            Portfolio returnedPortfolio = inventoryService.getPortfolio(portfolioId);
            return returnedPortfolio;
        }, PortfolioJsonUtil.json());
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
            res.body(UserJsonUtil.toJson(new ResponseError(e)));
        });
    }

}
