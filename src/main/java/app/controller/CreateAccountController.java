package app.controller;

import java.util.Map;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;
import app.dao.AccountDAO;
import app.model.Account;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class CreateAccountController {

	public static Handler serveCreateAccountPage = ctx -> {
		 Map<String, Object> model = ViewUtil.baseModel(ctx);
	        //render basic create account page
	     ctx.render(Template.CREATEACCOUNT, model);
	};
	
	//uses this when the form is submitted
	public static Handler createAccount = ctx -> {
	//create model
   	 Map<String, Object> model = ViewUtil.baseModel(ctx);
   	 //geta all variables from the database
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
   	 
   	 //checks to see what type of account is being created and uses the approriate constructor to insert into the database
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

        ctx.redirect(Web.INDEX);
   };
   
   //if the account has an organization then adds the organization to the database
   private static void setOrganisation(Account account, Context ctx) {
	   account.setOrganisationName(ctx.formParam("organisationname"));
	   account.setOrganisationPhone(ctx.formParam("organisationphone"));
   }
}
