package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Book extends Model
{
	@Id
	String bookId;

	BookDetails bookDetails;

	BookReview bookReview;

	int rate;


    public Book(String bookId,String title,int num,int rate)
    {
    	this.bookId=bookId;
    	this.rate=rate;
    	this.bookDetails= BookDetails.create(bookId,title,num);
    }

    public static Model.Finder<String,Book> find = new Model.Finder<String,Book>(String.class, Book.class);

    public static Book findByTitle(String title) 
    { 	
        BookDetails tempBook= BookDetails.findByTitle(title);
        if(tempBook!=null)
        {
        	String tempBookId=tempBook.bookId;
        	return find.ref(tempBookId);
        }
        else
        	return null;
    }

    public static Book findByAuthor(String author) 
    {	
        BookDetails tempBook= BookDetails.findByAuthor(author);
        if(tempBook!=null)
        {
        	String tempBookId=tempBook.bookId;
        	return find.ref(tempBookId);
        }
        else
        	return null;
    }

    public static Book create(String title,int num,int rate)
    {
        String bookId = BookId.getBookId();
        
    	Book book=new Book(bookId,title,num,rate);
    	book.save();
    	return book;
    }

}