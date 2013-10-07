package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class OpenBook extends Model
{
	@Id
	String userId;

	String bookId;

	//page number which was open
	int pageNum;

	//time left in 1 hour, represened in seconds
	int secLeft;

	public OpenBook(String uId, String bId, int pgNum)
	{
		this.userId = uId;
		this.bookId = bId;
		pageNum = pgNum;
		secLeft = 3600;
	}

	public static void addNew(String uId, String bId)
	{
		// do something to check if user is over writting a book
		remove(uId);

		OpenBook tempBook = new OpenBook(uId,bId,0);
		tempBook.save();
	}

	public static void addOld(String uId, String bId, int pgNum)
	{
		OpenBook tempBook = new OpenBook(uId,bId,pgNum);
		tempBook.save();
	}

	public static Model.Finder<String,OpenBook> find = new Model.Finder<String,OpenBook>(String.class, OpenBook.class);
	
	public static void remove(String uId)
	{
		find.ref(uId).delete();
	}

}