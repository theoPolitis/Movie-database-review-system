package app.controller.utils;


import io.javalin.http.Context;

public class RequestUtil {


    public static String getQueryLoginRedirect(Context ctx) {
        return ctx.queryParam("loginRedirect");
    }

    public static String getSessionCurrentUser(Context ctx) {
        return (String) ctx.sessionAttribute("currentUser");
    }
    
    public static String getQueryTitle(Context ctx) {
		return ctx.formParam("showTitleSearch");
	}
	
	public static String getQueryActor(Context ctx) {
		return ctx.formParam("showActorSearch");
	}
    




}
