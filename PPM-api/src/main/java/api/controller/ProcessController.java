package api.controller;

import com.google.gson.Gson;
import model.categorization.ScoringCriterion;
import model.inventory.Component;
import model.inventory.Portfolio;
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

        //SCORE
        Spark.post("projects/:componentId/create_decision", (Request req, Response res) ->
                {
                    String componentId = req.params(":componentId");
                    String body = req.body();
                    Gson gson = new Gson();
                    CreateDecisionRequest decisionRequest = gson.fromJson(body, CreateDecisionRequest.class);
                    Component component = inventoryService.getProject(Long.parseLong(componentId));
                    State fromState=processService.getState(Long.parseLong(decisionRequest.getFromStateId()));
                    State toState=processService.getState(Long.parseLong(decisionRequest.getToStateId()));
                    Long portfolioId = getParentPortfolio(component);
                    Decision decision = processService.createDecision(component,fromState, toState,
                            decisionRequest.getDecisionState(), decisionRequest.getDecisionType(), decisionRequest.getMotivation() );
                    Portfolio portfolio = inventoryService.getPortfolio(portfolioId);
                    if (portfolio != null) {
                        return portfolio;
                    }
                    res.status(400);
                    return new ResponseError("No portfolio with id '%s' found", portfolioId.toString());
                }, PortfolioJsonUtil.json()
        );

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
