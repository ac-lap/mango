package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class User extends Model
{
	@Id
	@Constraints.Required
    @Formats.NonEmpty
	String userId;

//    @Constraints.Required
//    @OneToOne
    @OneToOne(cascade=CascadeType.PERSIST)
    AccountDetails accDetails;

/*
	//book currently being read by the usser
	OpenBook current;

	//books bookmarked by the user
	Bookmark bookmark;

	//books read in the past
	BookList history;

	//books user wants to read in the future
	BookList wishlist;

	UserSettings userSett;
*/
	public User(String uId, String email, String password) 
	{
		this.userId = uId;
		this.accDetails = new AccountDetails(uId,email,password);
    }

	// -------- Queries
	public static Model.Finder<String,User> find = new Model.Finder<String,User>(String.class, User.class);

	/*
     * Retrieve a User from email.
     */
    public static User findByEmail(String email) 
    {
    	String tempId = AccountDetails.findByEmail(email).userId;

        return find.ref(tempId);
    }

    /*
     * Authenticate a User.
     */
    public static User authenticate(String email, String password) 
    {
    	String tempId = AccountDetails.authenticate(email, password).userId;
    	
        return find.ref(tempId);
    }

//**------------ No user_id is being taken 
    /*
     * Create a new User
     */
    public static User create(String email, String password)
    {
    	User user = new User(email, email, password);
    	user.save();
    	return user;
    }
    
}