package app.controller;

import java.util.Map;

import app.controller.utils.RequestUtil;
import app.controller.utils.ViewUtil;
import io.javalin.http.Handler;

public class SearchController {
	public static Handler searchIndex = ctx -> {
		Map<String, Object> model = ViewUtil.baseModel(ctx);
		
		if(RequestUtil.getQueryTitle(ctx) != null) {
			ShowController.getShow(ctx, model);
		}else {
			PersonController.getActor(ctx, model);
		}
	};

}
