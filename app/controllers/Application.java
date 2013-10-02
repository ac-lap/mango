package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;

public class Application extends Controller {

	static Form<User> taskForm = Form.form(User.class);

    public static Result index() 
    {
        return ok(index.render("Your new application is ready."));
    }

    /*
     * Login
     */

    public static class Login 
    {
	    public String email;
	    public String password;

        public String error_msg;

        public Login()
        {
            ;
        }

        public Login(String email, String password, String error)
        {
            this.email=email;
            this.password=password;
            this.error_msg=error;
        }
	}

    public static Result login() 
    {
	    return ok(
	        login.render(form(Login.class))
	    );
	}

    public static Result authenticate() 
    {
        Form<Login> loginForm = form(Login.class).bindFromRequest();

        if (User.authenticate(loginForm.get().email,loginForm.get().password) == null)
        {
            loginForm = loginForm.fill(new Login(loginForm.get().email, loginForm.get().password,"Error !!"));
            return badRequest(login.render(loginForm));
        } 
        else 
        {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                routes.Application.index()
            );
        }
    }


    /*
     * test page actions
     */
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
   		User.remove(id);
   		return redirect(routes.Application.test());
  	}

}
