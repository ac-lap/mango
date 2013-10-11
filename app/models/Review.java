package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Review extends Model
{
	@Id 
	String bookId;

	String userId;

	String review;

	Date reviewDate;

	double rating;

	public Review(String bId,String review,double rating)
	{
		this.bookId=bId;
		this.review=review;
		//this.reviewDate=sysDate;
		this.rating=rating;
	}

}