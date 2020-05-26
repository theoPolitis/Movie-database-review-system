package app.controller;


import java.util.List;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.*;
import app.dao.CreditsRollDAO;
import app.dao.ProductionCompanyDAO;
import app.dao.RatingDAO;
import app.dao.ShowDAO;
import app.model.CreditsRoll;
import app.model.ProductionCompany;
import app.model.Show;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ShowController {
	
	public static Handler showIndex = ctx -> {
		Map<String, Object> model = ViewUtil.baseModel(ctx);

		getShow(ctx, model);
	};
	
	public static void getShow(Context ctx, Map<String, Object> model) {
		//gets the show object from the database
		Show show = ShowDAO.getShowByTitle(RequestUtil.getQueryTitle(ctx));

		//checks the paramneters to see if there is information about the show to use 
		if(show == null) {
			if(RequestUtil.getShowId(ctx) != null) {
				show = ShowDAO.getShowById(Integer.parseInt(RequestUtil.getShowId(ctx)));
			}else {
				ctx.render(Template.SHOWERROR, model);
				return;
			}
		}

		//checks to see if the show exists
		if(show != null) { 
			//creates the and gets the data needed for the model
			ProductionCompany prC = ProductionCompanyDAO.getProductionCompanyById(show.getProcoId());
			List<CreditsRoll> creditsRoll = CreditsRollDAO.getCreditsRollByMovieId(show.getShowid());
			int ID = show.getShowid();
			//adds the show reviews into the model
			model.put("allReviews", RatingDAO.getShowReviews(ID));
			//adds the average rating that is to be displayed in the page via stars
			model.put("avgRating", RatingDAO.showAverageRating(ID));
			//adds information about the show
			model.put("show", show);
			//adds information about the production company that is going to be used
			model.put("productionCompany", prC);
			//adds information about the actors into the webpage
			model.put("actors", creditsRoll);
			//reders the page inform,ation with the information that we have p[ut in the model
			ctx.render(Template.SHOW, model);
			
			//if no show exists render an error page
		}else {
			ctx.render(Template.SHOWERROR, model);
		}
	}

	public static Handler editIndex = ctx -> {
		Map<String, Object> model = ViewUtil.baseModel(ctx);

		Show show = ShowDAO.getShowById(Integer.parseInt(ctx.formParam("showId")));

		ProductionCompany prC = ProductionCompanyDAO.getProductionCompanyById(show.getProcoId());
		List<CreditsRoll> creditsRoll = CreditsRollDAO.getCreditsRollByMovieId(show.getShowid());

		model.put("show", show);
		model.put("productionCompany", prC);
		model.put("actors", creditsRoll);

		if (ctx.formParam("origin").equals("showPage")) {
			ctx.render(Template.EDITSHOW, model);
		} else if (ctx.formParam("origin").equals("editPage")) {

			try	{

				String title = ctx.formParam("showTitleSearch");
				String genre = ctx.formParam("genre");
				int year = Integer.parseInt(ctx.formParam("year"));
				double length = Double.parseDouble(ctx.formParam("length"));

				String sql = "UPDATE imbd.show SET show_title = '" + title + "', genre = '" + genre + "', length = " + length + ", year = " + year;

				ShowDAO.alterShow(sql);

				getShow(ctx, model);

			} catch (Exception e) {
				e.printStackTrace();
				ctx.render(Template.ENTRYERROR, model);
			}


		}

	};

}
