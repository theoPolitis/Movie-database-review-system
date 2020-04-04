package app.controller;


import java.util.List;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.*;
import app.dao.CreditsRollDAO;
import app.dao.PersonDAO;
import app.dao.ProductionCompanyDAO;
import app.dao.ShowDAO;
import app.model.CreditsRoll;
import app.model.ProductionCompany;
import app.model.Show;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ShowController {
	
//	public static Handler serveShowPage = ctx -> {
//		 Map<String, Object> model = ViewUtil.baseModel(ctx);
//		 
//	}
	
//	public static Handler handleSearchTitlePost = ctx -> {
//		Map<String, Object> model = ViewUtil.baseModel(ctx);
//		
//		//check if the show exists
//		if(!SearchController.checkIfShowExistsByTitle(getQueryTitle(ctx))) {
//			model.put("titleSearchFailed", true);
//			ctx.render(Template.INDEX, model);
//		}else {
//			model.put("titleSearchSuceeded", true);
//			ctx.render(Template.SHOW, model);
//		}
//	};
////	
	
	public static void getShow(Context ctx, Map<String, Object> model) {
		Show show = ShowDAO.getShowByTitle(RequestUtil.getQueryTitle(ctx));
		ProductionCompany prC = ProductionCompanyDAO.getProductionCompanyById(show.getProcoId());
		List<CreditsRoll> creditsRoll = CreditsRollDAO.getCreditsRollByMovieId(show.getShowid());		
		System.out.println(creditsRoll);
		model.put("show", show); //adds information about the show its searching for 
		model.put("productionCompany", prC); //add info about the production company that made that show
		model.put("actors", creditsRoll);
		ctx.render(Template.SHOW, model);
	}
	
//	public static Handler fetchShowByTitle = ctx -> {
//		Map<String, Object> model = ViewUtil.baseModel(ctx);
//		model.put("show", ShowDAO.getShowByTitle(RequestUtil.getQueryTitle(ctx)));
//		ctx.render(Template.SHOW, model);
//	};
	
	

}
