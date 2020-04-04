package app.controller;


import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.*;
import app.dao.PersonDAO;
import app.dao.ShowDAO;
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
		model.put("show", ShowDAO.getShowByTitle(RequestUtil.getQueryTitle(ctx)));
		ctx.render(Template.SHOW, model);
	}
	
//	public static Handler fetchShowByTitle = ctx -> {
//		Map<String, Object> model = ViewUtil.baseModel(ctx);
//		model.put("show", ShowDAO.getShowByTitle(RequestUtil.getQueryTitle(ctx)));
//		ctx.render(Template.SHOW, model);
//	};
	
	

}
