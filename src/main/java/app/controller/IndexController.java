package app.controller;

import app.controller.paths.Template;
import app.controller.utils.RequestUtil;
import app.controller.utils.ViewUtil;
import app.dao.AccountDAO;
import io.javalin.http.Handler;
import java.util.Map;




public class IndexController {
    public static Handler serveIndexPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("userObject", AccountDAO.getUserByUsername(RequestUtil.getSessionCurrentUser(ctx)));
        ctx.render(Template.INDEX, model);
    };


}
