package app.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

import app.controller.utils.RequestUtil;
import app.controller.utils.ViewUtil;
import app.dao.RatingDAO;
import app.dao.utils.DatabaseUtils;
import app.model.UserReview;
import io.javalin.http.Handler;

public class SearchController {
	public static Handler searchIndex = ctx -> {
		Map<String, Object> model = ViewUtil.baseModel(ctx);
		//quick if statement to check what we are searching for either an actor or a person
		if(RequestUtil.getQueryTitle(ctx) != null) {
			ShowController.getShow(ctx, model);
		}else if(RequestUtil.getQueryActor(ctx)!= null) {
			PersonController.getActor(ctx, model);
		}else if(RequestUtil.getShowId(ctx) != null) {
			UserReview userReview = new UserReview(DatabaseUtils.getNewReviewNumber(), Integer.parseInt(RequestUtil.getShowId(ctx)),
					RequestUtil.getSessionCurrentUser(ctx), Integer.parseInt(RequestUtil.getRating(ctx)), RequestUtil.getReviewText(ctx), new Date());
			RatingDAO.insertReviewIntoDataBase(userReview);
			ShowController.getShow(ctx, model);
		}
	};

}
