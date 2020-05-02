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

    public static Handler serveEntryPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);

        model.put("proCo", ProductionCompanyDAO.getAllProCo());
        ctx.render(Template.ENTRY, model);
    };


    public static Handler addEntry = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);

        String title = ctx.formParam("title");
        String genre = ctx.formParam("genre");
        float length = Float.parseFloat(ctx.formParam("length"));

        int isMovie = Integer.parseInt(ctx.formParam("movie"));
        int isSeries = Integer.parseInt(ctx.formParam("series"));

        int proco = Integer.parseInt(ctx.formParam("proco"));
        int year = Integer.parseInt(ctx.formParam("year"));


        String status = "UnderInvestigation";

        // FIX THIS
        if (ctx.formParam("isProco").equals("1")) {
            status = "Visible";
        }

        ShowDAO.createShowEntry(title, genre, length, isMovie, isSeries, proco, year, status);

        ctx.render(Template.ENTRY, model);
    };

}
