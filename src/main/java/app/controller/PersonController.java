package app.controller;

import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.RequestUtil;
import app.dao.PersonDAO;
import io.javalin.http.Context;

public class PersonController {
	public static void getActor(Context ctx, Map<String, Object> model) {
		model.put("person", PersonDAO.getActorByName(RequestUtil.getQueryActor(ctx)));
		ctx.render(Template.PERSON, model);
	}
}
