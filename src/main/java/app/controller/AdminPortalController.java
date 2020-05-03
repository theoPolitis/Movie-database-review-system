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
	private static final String VISIBLE = "Visible";
	private static final String SUBMITTED = "Submitted";

    public static Handler serveAdminPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        
        model.put("pendingList", ShowDAO.getShowsByStatus(INVESTIGATION));
        model.put("suspendedList", ShowDAO.getShowsByStatus(SUSPENDED));
        model.put("visibleList", ShowDAO.getShowsByStatus(VISIBLE));
        model.put("submittedList", ShowDAO.getShowsByStatus(SUBMITTED));

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
        
        model.put("pendingList", ShowDAO.getShowsByStatus(INVESTIGATION));
        model.put("suspendedList", ShowDAO.getShowsByStatus(SUSPENDED));
        model.put("visibleList", ShowDAO.getShowsByStatus(VISIBLE));
        model.put("submittedList", ShowDAO.getShowsByStatus(SUBMITTED));

        ctx.render(Template.ADMINPORTAL, model);
    };

}
