package models;

import java.util.*;
import java.security.*;
import java.math.*;

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
        this.password = Security.encryptPassword(password);
    }

    public static AccountDetails create(String uId, String email, String password)
    {
        AccountDetails accDet = new AccountDetails(uId,email,password);
        accDet.save();
        return accDet;
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
            .eq("emailId", email)
            .eq("password", Security.encryptPassword(password))
            .findUnique();
    }
    
}