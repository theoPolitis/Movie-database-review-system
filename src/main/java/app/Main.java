package app;

import app.controller.*;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;
import app.model.Show;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import static io.javalin.apibuilder.ApiBuilder.*;





public class Main {





    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public");
            config.registerPlugin(new RouteOverviewPlugin("/routes"));
        }).start(getHerokuAssignedPort());

        app.routes(() -> {
            // You will have to update this, to limit who can see the reviews
            // before(LoginController.ensureLoginBeforeViewing);

            get(Web.INDEX, IndexController.serveIndexPage);
            //get the post data from the webpage index which is the page that we are search from
            post(Web.INDEX, SearchController.searchIndex);

            get(Web.LOGIN, LoginController.serveLoginPage);
            post(Web.LOGIN, LoginController.handleLoginPost);
            post(Web.LOGOUT, LoginController.handleLogoutPost);
            //get(Web.SHOW, ShowController.showIndex);
            post(Web.SHOW, ShowController.showIndex);
            
            

            get(Web.ACCOUNT, AccountController.serveAccountPage);

            // Add new actions here
            // Seeing pages (get) and sending information in forms (post)

            get(Web.ADMINPORTAL, AdminPortalController.serveAdminPage);

            post(Web.ADMINPORTAL, AdminPortalController.alterEntry);


            get(Web.ENTRY, EntryController.serveEntryPage);

            post(Web.ENTRY, EntryController.addEntry);
            
            get(Web.CREATEACCOUNT, CreateAccountController.serveCreateAccountPage);
        });

        app.error(404, ViewUtil.notFound);
    }






    public static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 7000;
    }



}
