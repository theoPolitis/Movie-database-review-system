package app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.RequestUtil;
import app.dao.CreditsRollDAO;
import app.dao.PersonDAO;
import app.dao.ShowDAO;
import app.model.CreditsRoll;
import app.model.Person;
import app.model.Show;
import io.javalin.http.Context;

public class PersonController {
	//adds relevant information that i need to the show page and uses variables passed over from the search controller
	public static void getActor(Context ctx, Map<String, Object> model) {
		//create the person object linked to the person that we are looking for
		Person person = PersonDAO.getActorByName(RequestUtil.getQueryActor(ctx));
		if(person != null) {
			//creates the credits roll which gets information about the movies that the actor has been in
			List<CreditsRoll> creditRoll = CreditsRollDAO.getMoviesByActor(person.getPersonId());
			//checks of any movies exist and adds them to a new array full of movies
			if(creditRoll != null) {
				List<Show> movies = new ArrayList<>();
				for(int i = 0; i < creditRoll.size(); i++) {
					movies.add(ShowDAO.getShowById(creditRoll.get(i).getShowId()));
					System.out.println(movies.get(i));
				}
				
				model.put("movies", movies);
			}
		
		//adds the Person to the model to be sent to the page
			model.put("person", person);
			ctx.render(Template.PERSON, model);
		}else {
			ctx.render(Template.ACTORERROR, model);
		}
	}
}
