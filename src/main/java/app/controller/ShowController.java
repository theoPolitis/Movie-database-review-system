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

public class ShowController {	
	
	public static void getShow(Context ctx, Map<String, Object> model) {
		Show show = ShowDAO.getShowByTitle(RequestUtil.getQueryTitle(ctx));
		if(show != null) {
			ProductionCompany prC = ProductionCompanyDAO.getProductionCompanyById(show.getProcoId());
			List<CreditsRoll> creditsRoll = CreditsRollDAO.getCreditsRollByMovieId(show.getShowid());		
			model.put("show", show); //adds information about the show its searching for 
			model.put("productionCompany", prC); //add info about the production company that made that show
			model.put("actors", creditsRoll);
			ctx.render(Template.SHOW, model);
		}else {
			ctx.render(Template.SHOWERROR, model);
		}
	}
}
