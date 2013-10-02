package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


@Entity
public class AccountDetails extends Model
{
	@Id
	String userId;

	@Constraints.Required
	String emailId;

	@Constraints.Required
	String password;

	//personal details of the user
//	PersonalDetails p_details;

//**-------- p_details are not being taken
    public AccountDetails(String uId, String email, String password)
    {
        this.userId = uId;
        this.emailId = email;
        this.password = password;
    }


    // -------- Queries
	public static Model.Finder<String,AccountDetails> find = 
			new Model.Finder<String,AccountDetails>(String.class, AccountDetails.class);

	/*
     * Retrieve AccountDetails from email.
     */
    public static AccountDetails findByEmail(String email) {
    	
        return find.where().eq("emailId", email).findUnique();
       
    }
    
    /*
     * Authenticate a given account details.
     */
    public static AccountDetails authenticate(String email, String password) 
    {    	
        return find.where()
            .eq("email", email)
            .eq("password", password)
            .findUnique();
    }
    
}