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

        System.out.println("ADD ENTRY HANDLER");

        String title = ctx.formParam("title");
        String genre = ctx.formParam("genre");
        int length = Integer.parseInt(ctx.formParam("length"));
        boolean isMovie = Boolean.parseBoolean(ctx.formParam("movie"));
        boolean isSeries = Boolean.parseBoolean(ctx.formParam("series"));
        int proco = Integer.parseInt(ctx.formParam("proco"));
        int year = Integer.parseInt(ctx.formParam("year"));

        ShowDAO.createShowEntry(title, genre, length, isMovie, isSeries, proco, year);

        ctx.render(Template.ENTRY, model);
    };

}
