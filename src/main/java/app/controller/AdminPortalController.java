package app.controller;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.AccountDAO;
import app.dao.ProductionCompanyDAO;
import app.dao.ShowDAO;
import app.model.Account;
import app.model.Show;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminPortalController {
	//final variables for all the different SHOW status's
	private static final String INVESTIGATION = "UnderInvestigation";
	private static final String SUSPENDED = "Suspended";
	private static final String VISIBLE = "Visible";
	private static final String SUBMITTED = "Submitted";

    public static Handler serveAdminPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        
        //update page with database state of the model
        ctx.render(Template.ADMINPORTAL, updateModel(model));
    };

    public static Handler alterEntry = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        //gets value of the button in the ADMIN portal 
        String formData = ctx.formParam("alter");
        String alterCommands[] = formData.split(", ");
        String sqlCommand;
        
        //dependant on what button is pressed then we update database accordingly
        if (alterCommands[1].equals("Remove")) {
            sqlCommand = "DELETE FROM imbd.show WHERE showid=" + alterCommands[0];
            ShowDAO.alterShow(sqlCommand);
        } else if(alterCommands[1].equals("Delete")){
        	sqlCommand = "DELETE FROM imbd.account WHERE username='" + alterCommands[0] + "'";
        	AccountDAO.alterAccount(sqlCommand);
        }else if(alterCommands[1].equals("Approve")){
        		sqlCommand = "UPDATE imbd.account SET approved='1' WHERE username='" + alterCommands[0] + "'";
        		AccountDAO.alterAccount(sqlCommand);
        		
        		
        	
        }else {
            sqlCommand = "UPDATE imbd.show SET status='" + alterCommands[1] + "' WHERE showid=" + alterCommands[0];
            ShowDAO.alterShow(sqlCommand);
        }
        
        
        if(alterCommands.length == 3) {
			ProductionCompanyDAO.AlterPCO(alterCommands[2], false);
		}
        
        if(alterCommands.length == 4) {
        	ProductionCompanyDAO.AlterPCO(alterCommands[2], true);
        }
    
        
        
        
        //render page with updated model
        ctx.render(Template.ADMINPORTAL, updateModel(model));
    };
    
    //private method that updates the model and returns the object map
    private static Map<String, Object> updateModel(Map<String, Object> model){
    	
    	model.put("pendingList", ShowDAO.getShowsByStatus(INVESTIGATION));
        model.put("suspendedList", ShowDAO.getShowsByStatus(SUSPENDED));
        model.put("visibleList", ShowDAO.getShowsByStatus(VISIBLE));
        model.put("submittedList", ShowDAO.getShowsByStatus(SUBMITTED));
        
        List<Account> PCO = new ArrayList<Account>();
        List<Account> FC = new ArrayList<Account>();
        
        for(Account a : AccountDAO.getUnApprovedAccounts()) {
        	if(a.isProco()) {
        		PCO.add(a);
        	}
        	
        	if(a.isFilmCritic()) {
        		FC.add(a);
        	}
        }
        
        List<Account> PCOApproved = new ArrayList<Account>();
        List<Account> FCApproved = new ArrayList<Account>();
        
        for(Account a : AccountDAO.getApprovedPCOAndFCAccounts()) {
        	if(a.isFilmCritic()) {
        		FCApproved.add(a);
        	}
        	
        	if(a.isProco()) {
        		PCOApproved.add(a);
        	}
        }
        
        model.put("PCOApproved", PCOApproved);
        model.put("FCApproved", FCApproved);
        model.put("PCO", PCO);
        model.put("FC", FC);
    	return model;
    }

}
