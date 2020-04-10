package app.controller;

import java.util.Map;

import app.controller.utils.RequestUtil;
import app.controller.utils.ViewUtil;
import io.javalin.http.Handler;

public class SearchController {
	public static Handler searchIndex = ctx -> {
		Map<String, Object> model = ViewUtil.baseModel(ctx);
		
		//quick if statement to check what we are searching for either an actor or a person
		if(RequestUtil.getQueryTitle(ctx) != null) {
			ShowController.getShow(ctx, model);
		}else {
			PersonController.getActor(ctx, model);
		}
	};

}
