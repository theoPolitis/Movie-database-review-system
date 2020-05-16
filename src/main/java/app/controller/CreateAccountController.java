package app.controller;

import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.AccountDAO;
import app.model.Account;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class CreateAccountController {

	public static Handler serveCreateAccountPage = ctx -> {
		 Map<String, Object> model = ViewUtil.baseModel(ctx);
	        // You'll have to update the model... maybe here
	     ctx.render(Template.CREATEACCOUNT, model);
	};
	
	public static Handler createAccount = ctx -> {
   	 Map<String, Object> model = ViewUtil.baseModel(ctx);
   	 String firstName = ctx.formParam("firstname");
   	 String lastName = ctx.formParam("lastname");
   	 String gender = ctx.formParam("gender");
   	 int yearOfBirth = Integer.parseInt(ctx.formParam("year"));
   	 String country = ctx.formParam("country");
   	 String postCode = ctx.formParam("postCode");
   	 String email = ctx.formParam("email");
   	 String username = ctx.formParam("username");
   	 String password = ctx.formParam("password");
   	 
   	 Account account = null;
   	     
   	 if(ctx.formParam("account").equals("standard")) {
   		 account = new Account(firstName, lastName, postCode, country, gender, yearOfBirth, email, username, password, false, false, false, true);
   		 AccountDAO.insertNewAccount(account, true); 
   	 }else if(ctx.formParam("account").equals("PCO")) {
   		account = new Account(firstName, lastName, postCode, country, gender, yearOfBirth, email, username, password, false, true, false, false);
   		setOrganisation(account, ctx);
   		AccountDAO.insertNewAccount(account, false);
   	 }else if(ctx.formParam("account").equals("FC")) {
   		account = new Account(firstName, lastName, postCode, country, gender, yearOfBirth, email, username, password, false, false, true, false);
   		setOrganisation(account, ctx);
   		AccountDAO.insertNewAccount(account, false);
   	 }
        // You'll have to update the model... maybe here

        ctx.render(Template.LOGIN, model);
   };
   
   private static void setOrganisation(Account account, Context ctx) {
	   account.setOrganisationName(ctx.formParam("organisationname"));
	   account.setOrganisationPhone(Integer.parseInt(ctx.formParam("organisationphone")));
   }
}
