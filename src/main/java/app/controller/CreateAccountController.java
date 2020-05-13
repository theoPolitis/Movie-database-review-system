package app.controller;

import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import io.javalin.http.Handler;

public class CreateAccountController {

	public static Handler serveCreateAccountPage = ctx -> {
		 Map<String, Object> model = ViewUtil.baseModel(ctx);
	        // You'll have to update the model... maybe here
	     ctx.render(Template.CREATEACCOUNT, model);
	};
}
