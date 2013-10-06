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

    AccountDetails accDetails;

    Credit credit;

    //book currently being read by the usser
    OpenBook curBook;
/*

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
		this.accDetails = AccountDetails.create(uId,email,password);
        this.credit = Credit.create(uId);
    }

    //creates a new user
    public static User create(String email, String password)
    {
        User user = new User(email, email, password);
        user.save();
        return user;
    }

	// -------- Queries
	public static Model.Finder<String,User> find = new Model.Finder<String,User>(String.class, User.class);

    // Authenticate the user details
    public static User authenticate(String email, String password) 
    {
    	AccountDetails temp = AccountDetails.authenticate(email, password);
    	
        if (temp != null)
            return find.ref(temp.userId);
        else
            return null;
    }

    public static User checkExists(String uId)
    {
        return find.where()
            .eq("userId", uId)
            .findUnique();
    }
//**------------ No user_id is being taken 

    public static List<User> all() 
    {
      return find.all();
    }
    
}