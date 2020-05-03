package app.controller;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.ShowDAO;
import app.model.Show;
import io.javalin.http.Handler;

import java.util.Map;

public class AdminPortalController {
	private static final String INVESTIGATION = "UnderInvestigation";
	private static final String SUSPENDED = "Suspended";

    public static Handler serveAdminPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);

        System.out.println(ShowDAO.getShowsByStatus(INVESTIGATION));
        System.out.println(ShowDAO.getShowsByStatus(SUSPENDED));
        
        model.put("pendingList", ShowDAO.getShowsByStatus(INVESTIGATION));
        model.put("suspendedList", ShowDAO.getShowsByStatus(SUSPENDED));

        ctx.render(Template.ADMINPORTAL, model);
    };

    public static Handler alterEntry = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);

        String formData = ctx.formParam("alter");

        String alterCommands[] = formData.split(", ");

        String sqlCommand;

        if (alterCommands[1].equals("Remove")) {
            sqlCommand = "DELETE FROM imbd.show WHERE showid=" + alterCommands[0];
        } else {
            sqlCommand = "UPDATE imbd.show SET status='" + alterCommands[1] + "' WHERE showid=" + alterCommands[0];
        }

        ShowDAO.alterShow(sqlCommand);
        System.out.println(ShowDAO.getShowsByStatus(INVESTIGATION));
        System.out.println(ShowDAO.getShowsByStatus(SUSPENDED));
        
        model.put("pendingList", ShowDAO.getShowsByStatus(INVESTIGATION));
        model.put("suspendedList", ShowDAO.getShowsByStatus(SUSPENDED));

        ctx.render(Template.ADMINPORTAL, model);
    };

}
