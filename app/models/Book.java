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
    @OneToOne(cascade=CascadeType.PERSIST)
	BookDetails bookDetails;
  //  @OneToOne(cascade=CascadeType.PERSIST)
//	Tag tag;
    @OneToOne(cascade=CascadeType.PERSIST)
	BookReview bookReview;
	int rate;


public Book(String bookId,String title,int num,int rate)
{
	this.bookId=bookId;
	this.rate=rate;
	this.bookDetails=new BookDetails(bookId,title,num);
}

public static Model.Finder<String,Book> find = 
          new Model.Finder<String,Book>(String.class, Book.class);

public static Book findByTitle(String title) {
    	
        BookDetails tempBook= BookDetails.findByTitle(title);
        if(tempBook!=null)
        {
        	String tempBookId=tempBook.bookId;
        	return find.ref(tempBookId);
        }
        else
        	return null;
       }

    public static Book findByAuthor(String author) {
    	
        BookDetails tempBook= BookDetails.findByAuthor(author);
        if(tempBook!=null)
        {
        	String tempBookId=tempBook.bookId;
        	return find.ref(tempBookId);
        }
        else
        	return null;
        }

public static Book create(String bookId,String title,int num,int rate)
{
	Book book=new Book(bookId,title,num,rate);
	book.save();
	return book;
}

public static void create(Book book)
{
	book.save();
}

}