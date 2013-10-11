package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class BookDetails extends Model
{
	@Id
	String bookId;

	String title;

	List<String> authors;

	String isbn;

	int numPages;

	String summary;

	String imgLocation;

	String bookLocation;

	public BookDetails(String bookId,String title,int num)
	{
		this.bookId=bookId;
		this.title=title;
		this.numPages=num;
	}

    public static Model.Finder<String,BookDetails> find = 
             new Model.Finder<String,BookDetails>(String.class, BookDetails.class);

    public static BookDetails findByTitle(String title) 
    {
    	
       return find.where().eq("title", title).findUnique();
       
    }

    public static BookDetails findByAuthor(String author) 
    {
    	
       return find.where().eq("authors", author).findUnique();
       
    }

	public static BookDetails create(String bookId,String title,int num)
	{
		BookDetails bookdetails=new BookDetails(bookId,title,num);
		bookdetails.save();
		return bookdetails;
	}

}