package app.controller;


import java.util.List;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.*;
import app.dao.CreditsRollDAO;
import app.dao.ProductionCompanyDAO;
import app.dao.ShowDAO;
import app.model.CreditsRoll;
import app.model.ProductionCompany;
import app.model.Show;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ShowController {
	
	public static Handler showIndex = ctx -> {
		Map<String, Object> model = ViewUtil.baseModel(ctx);
		getShow(ctx, model);
	};
	
	public static void getShow(Context ctx, Map<String, Object> model) {
		Show show = ShowDAO.getShowSelector(RequestUtil.getQueryTitle(ctx), 0);
		if(show != null) {//checks to see if the show exists
			ProductionCompany prC = ProductionCompanyDAO.getProductionCompanyById(show.getProcoId());
			List<CreditsRoll> creditsRoll = CreditsRollDAO.getCreditsRollByMovieId(show.getShowid());
			if(RequestUtil.getRating(ctx) != null) {
				int rating = Integer.parseInt(RequestUtil.getRating(ctx));
				System.out.println(rating);
			}
			model.put("show", show); //adds information about the show its searching for 
			model.put("productionCompany", prC); //add info about the production company that made that show
			model.put("actors", creditsRoll);//places other cators that are playing in the movie
			ctx.render(Template.SHOW, model);
			//if no show exists render an error page
		}else {
			ctx.render(Template.SHOWERROR, model);
		}
	}
}
