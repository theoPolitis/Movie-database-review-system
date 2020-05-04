package app.controller;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.ProductionCompanyDAO;
import app.dao.ShowDAO;
import app.model.Show;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.javalin.http.Handler;

import java.util.Map;

public class EntryController {

	//cretaes basic entry portal with a model 
    public static Handler serveEntryPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);

        model.put("proCo", ProductionCompanyDAO.getAllPCO());
        ctx.render(Template.ENTRY, model);
    };

    
    public static Handler addEntry = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);

        try {
        	//declare all variable with information form the form
            String title = ctx.formParam("title");
            String genre = ctx.formParam("genre");
            float length = Float.parseFloat(ctx.formParam("length"));
            int isMovie = Integer.parseInt(ctx.formParam("movie"));
            int isSeries = Integer.parseInt(ctx.formParam("series"));
            int proco = Integer.parseInt(ctx.formParam("proco"));
            int year = Integer.parseInt(ctx.formParam("year"));
            //base case for status
            String status = "UnderInvestigation";

            //if production company submits show then base case for status is equal
            //to "Submitted"
            if (ctx.formParam("isProco").equals("yes")) {
                status = "Submitted";
            }
            
            //uses DAO to insert into the show
            ShowDAO.createShowEntry(title, genre, length, isMovie, isSeries, proco, year, status);

            ctx.render(Template.ENTRY, model);

        } catch (Exception e) {
        	//if invalid data is inserted then  handle exception and go to error page
            ctx.render(Template.ENTRYERROR, model);
            e.printStackTrace();
        }

    };

}
