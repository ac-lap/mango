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

	public void OpenBook(String bId)
	{
		;
	}
}