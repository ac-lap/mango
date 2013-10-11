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

    double avgRating;

    int count;

    public List<Review> reviewList = new ArrayList<Review>();

    public BookReview(String uId,String bId,double avgRating, int count)
    {
    	 this.bookId=bId;
         this.avgRating=avgRating;
         this.count=count;
    }

}