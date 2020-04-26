package app.controller.utils;


import app.controller.paths.Template;
import app.dao.AccountDAO;
import io.javalin.http.Context;
import io.javalin.http.ErrorHandler;
import java.util.HashMap;
import java.util.Map;





public class ViewUtil {
	//do#if($currentuser) tells you if your logged in
    public static Map<String, Object> baseModel(Context ctx) {
        Map<String, Object> model = new HashMap<>();
        model.put("currentUser", RequestUtil.getSessionCurrentUser(ctx));

        model.put("userObject", AccountDAO.getUserByUsername(RequestUtil.getSessionCurrentUser(ctx)));

        return model;
    }

    public static ErrorHandler notFound = ctx -> {
        ctx.render(Template.NOT_FOUND, baseModel(ctx));
    };

}
