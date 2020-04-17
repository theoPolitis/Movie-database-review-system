package app.controller.utils;


import io.javalin.http.Context;

public class RequestUtil {


    public static String getQueryLoginRedirect(Context ctx) {
        return ctx.queryParam("loginRedirect");
    }

    public static String getSessionCurrentUser(Context ctx) {
        return (String) ctx.sessionAttribute("currentUser");
    }
    
    //gets the title of the movie thats in the search box
    public static String getQueryTitle(Context ctx) {
		return ctx.formParam("showTitleSearch");
	}
	
    //gets the actor that is in the search box
	public static String getQueryActor(Context ctx) {
		return ctx.formParam("showActorSearch");
	}
	
	public static String getRating(Context ctx) {
		return ctx.formParam("ratingNumber");
	}
	
	public static String getShowId(Context ctx) {
		return ctx.formParam("showId");
	}
    




}
