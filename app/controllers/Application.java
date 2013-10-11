package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;

public class Application extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result index() 
    {
        return ok(index.render(User.find.where().eq("userId",session().get("userId")).findUnique()));
    }

    /*
     * Login
    */

        public static class Login 
        {
    	    public String email;
    	    public String password;

            public Login()
            {
                ;
            }

            public Login(String email, String password)
            {
                this.email=email;
                this.password=password;
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
                loginForm = loginForm.fill(new Login(loginForm.get().email, loginForm.get().password));
                flash("error", "Bad Username or password");
                return badRequest(login.render(loginForm));
            } 
            else 
            {
                session().clear();
                session("userId", loginForm.get().email);
                return redirect(
                    routes.Application.index()
                );
            }
        }

    /*
     * Logout
    */
        public static Result logout() {
            session().clear();
            flash("success", "You've been logged out");
            return redirect(
                routes.Application.login()
            );
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
                if (User.checkExists(signForm.get().email) == null)
                {
                    User.create(signForm.get().email, signForm.get().password);
                    session().clear();
                    session("userId", signForm.get().email);
                    return redirect(
                        routes.Application.index()
                    );
                }
                else
                {
                    flash("error", "User already exists");
                    return redirect(routes.Application.signup());
                }
            }

    	}

    /*
     * 	Readeer Actions Implementations
    */
    	public static Result reader(){
    		return ok(reader.render());
    	}


    /*
     *  Admin Signup Page
    */
        public static Result adminLogin()
        {
            return ok(adminLogin.render(form(Login.class)));
        }

        public static Result adminAuthenticate() 
        {
            Form<Login> loginForm = form(Login.class).bindFromRequest();
            
            if (loginForm.get().email.equals("anubhav") && loginForm.get().password.equals("password"))
            {
                session().clear();
                session("userId", loginForm.get().email);
                
                return ok(adminHome.render(loginForm.get().email));
            }
            else
            {
                loginForm = loginForm.fill(new Login(loginForm.get().email, loginForm.get().password));
                flash("error", "Bad Username or password");
                return badRequest(adminLogin.render(loginForm));
            } 
        }

}
