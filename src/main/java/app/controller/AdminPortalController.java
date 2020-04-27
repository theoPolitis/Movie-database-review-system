package app.controller;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.ShowDAO;
import io.javalin.http.Handler;

import java.util.Map;

public class AdminPortalController {

    public static Handler serveAdminPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);

        model.put("pendingList", ShowDAO.getPendingShows());
        System.out.println(ShowDAO.getPendingShows());

        // model.put("userObject", AccountDAO.getUserByUsername(RequestUtil.getSessionCurrentUser(ctx)));
        ctx.render(Template.ADMINPORTAL, model);
    };

}
