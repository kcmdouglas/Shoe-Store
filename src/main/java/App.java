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

  get("/store/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Store store = Store.find(Integer.parseInt(request.params(":id")));
    model.put("stores", Store.all());
    model.put("store", store);
    model.put("brand", Brand.class);
    model.put("template", "templates/store.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


//POST ROTES FOR STORES PAGE
  //ADD
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

  //UPDATE
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

  //DELETE
  post("/stores/delete", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Integer storeId = Integer.parseInt(request.queryParams("storeDelete"));
    Store store = Store.find(storeId);

    store.delete();

    response.redirect("/stores");
    return null;
  });

//POST ROUTES FOR INDIVIDUAL STORE PAGES
  //ADD BRAND
  post("/store/:id/add-brand", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Store store = Store.find(Integer.parseInt(request.params(":id")));

    String name = request.queryParams("name");
    String specialty = request.queryParams("specialty");
    Brand newBrand = new Brand(name, specialty);
    newBrand.save();
    store.addBrand(newBrand.getId());

    response.redirect("/store/" + store.getId());
    return null;
  });

  //UPDATE STORE
  post("/store/:id/update", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Store store = Store.find(Integer.parseInt(request.params(":id")));
    String name = request.queryParams("name");
    String address = request.queryParams("address");
    String phone = request.queryParams("phone");

    store.update(name, address, phone);

    response.redirect("/store/" + store.getId());
    return null;
  });

  //DELETE THIS STORE
  post("/store/:id/delete", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Store store = Store.find(Integer.parseInt(request.params(":id")));
    store.delete();

    response.redirect("/stores");
    return null;
  });


  }
}
