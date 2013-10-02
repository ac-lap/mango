package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;

public class Application extends Controller {

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
     * Signup page actions
    */
        public static class Signup
        {
            public String email;
            public String password;
        }

        public static Result signup() 
        {
        	return ok(signup.render(form(Signup.class)));
    	}

    	public static Result newUser() 
    	{
            Form<Signup> signForm = form(Signup.class).bindFromRequest();

            if(signForm.hasErrors()) 
            {
                return badRequest(
                    signup.render(signForm)
                );
            } 
            else 
            {
                User.create(signForm.get().email, signForm.get().password);
                session().clear();
                session("email", signForm.get().email);
                return redirect(
                    routes.Application.index()
                );
            }

    	}

    	public static Result deleteUser(String id) {
       		User.remove(id);
       		return redirect(routes.Application.signup());
      	}

}
