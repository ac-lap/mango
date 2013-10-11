package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class BookReview extends Model
{
	@Id
	String bookId;

	@Id
	public String userId;

    double avgRating;

    Map <String, Review> r;

    int count;
 
     public BookReview(String uId,String bId,double avgRating, int count)
     {
     	 this.userId=uId;
     	 this.bookId=bId;
 	     this.avgRating=avgRating;
 	     this.count=count;
     }

     public void giveReview(String uId,String bId,String review, double rating)
     {
     	Review tempReview=new Review(bId,review,rating);
        // map tempReview and save in BookReview
     //   r[uId]=tempReview;
     	double sum=avgRating*count;
     	sum=sum+rating;
     	count++;
     	avgRating=sum/count;
     }

     public static BookReview create(String uId,String bId,double avgRating, int count)
    {
    	BookReview bookReview=new BookReview(uId, bId, avgRating, count);
    	bookReview.save();
    	return bookReview;
    }

    public static void create(BookReview bookReview) 
    {
        bookReview.save();
    }
}