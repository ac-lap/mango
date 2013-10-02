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
	public String userId;

//    @Constraints.Required
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

	// Retrive user by details
    public static User findByEmail(String email) 
    {
    	String tempId = AccountDetails.findByEmail(email).userId;

        return find.ref(tempId);
    }

    // Authenticate the user details
    public static User authenticate(String email, String password) 
    {
    	AccountDetails temp = AccountDetails.authenticate(email, password);
    	
        if (temp != null)
            return find.ref(temp.userId);
        else
            return null;
    }

//**------------ No user_id is being taken 

    //creates a new user
    public static User create(String email, String password)
    {
    	User user = new User(email, email, password);
    	user.save();
    	return user;
    }

    public static void create(User user) 
    {
        user.save();
    }

    public static List<User> all() 
    {
      return find.all();
    }

    public static void remove(String id) {
      find.ref(id).delete();
    }

    public String toString() {
        return "User(" + userId + ")";
    }
    
}