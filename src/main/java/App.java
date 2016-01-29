import java.util.ArrayList;
import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

//Get Routes for all pages
  get("/", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  get("/stores", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();

    model.put("stores", Store.all());
    model.put("store", Store.class);
    model.put("template", "templates/stores.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


//Post Routes for Adding

  post("/stores/new", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    String name = request.queryParams("name");
    String address = request.queryParams("address");
    String phone = request.queryParams("phone");


    Store store = new Store(name, address, phone);
    store.save();

    response.redirect("/stores");
    return null;
  });

//Post Routes for Updating

post("/stores/update", (request, response) -> {
  HashMap<String, Object> model = new HashMap<String, Object>();
  Integer storeId = Integer.parseInt(request.queryParams("storeUpdate"));
  String name = request.queryParams("name");
  String address = request.queryParams("address");
  String phone = request.queryParams("phone");


  Store store = Store.find(storeId);

  store.update(name, address, phone);

  response.redirect("/stores");
  return null;
});

//Post Routes for Deleting

  }
}
