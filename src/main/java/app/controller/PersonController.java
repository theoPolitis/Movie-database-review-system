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
	public static void getActor(Context ctx, Map<String, Object> model) {
		Person person = PersonDAO.getActorByName(RequestUtil.getQueryActor(ctx));
		List<CreditsRoll> creditRoll = CreditsRollDAO.getMoviesByActor(person.getPersonId());
		List<Show> movies = new ArrayList<>();
		for(int i = 0; i < creditRoll.size(); i++) {
			movies.add(ShowDAO.getShowById(creditRoll.get(i).getShowId()));
			System.out.println(movies.get(i));
		}
		
		model.put("person", person);
		model.put("movies", movies);
		ctx.render(Template.PERSON, model);
	}
}
