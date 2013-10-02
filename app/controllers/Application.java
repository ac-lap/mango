package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import models.*;

import views.html.*;

public class Application extends Controller {

	static Form<User> taskForm = Form.form(User.class);

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result test() 
    {
    	return ok(
    		views.html.test.render(User.all(), taskForm));
	}

	public static Result newUser() 
	{
	  Form<User> filledForm = taskForm.bindFromRequest();
	  if(filledForm.hasErrors()) {
	    return badRequest(
	      views.html.test.render(User.all(), filledForm)
	    );
	  } else {
	    User.create(filledForm.get());
	    return redirect(routes.Application.test());  
	  }
	}

	public static Result deleteUser(String id) {
   		return TODO;
  	}

}
