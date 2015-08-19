package api.controller;

import com.google.gson.Gson;
import model.organization.Organization;
import model.organization.User;
import service.organization.OrganizationServiceInterface;
import util.LoginRequest;
import util.ResponseError;
import util.UserJsonUtil;
import spark.Request;
import spark.Response;
import spark.Spark;

import static spark.Spark.*;

/**
 * Created by Wojciech on 2015-08-15.
 */
public class OrganizationController {
    public OrganizationController(OrganizationServiceInterface organizationService) {
        Spark.get("/users/:id", (req, res) -> {
            String id = req.params(":id");
            User user = organizationService.getUser(Long.parseLong(id));
            if (user != null) {
                return user;
            }
            res.status(400);
            return new ResponseError("No user with id '%s' found", id);
        }, UserJsonUtil.json());

        Spark.post("/login", (req, res) -> {
            String body = req.body();
            Gson gson = new Gson();
            LoginRequest loginRequest = gson.fromJson(body, LoginRequest.class);
            User user = organizationService.login(loginRequest.getEmail(),loginRequest.getPassword());
            if (user != null) {
                return user;
            }
            res.status(400);
            return new ResponseError("No user with email '%s' found", loginRequest.getEmail());
        }, UserJsonUtil.json());

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
            res.body(UserJsonUtil.toJson(new ResponseError(e)));
        });
    }

}
