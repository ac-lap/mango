package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class BookId extends Model
{
	@Id
	String endId;
	
	public BookId()
	{
		endId = Integer.toString(1);
	}

	public BookId(String id)
	{
		endId = id;
	}
	
	public static Model.Finder<String,BookId> find = new Model.Finder<String,BookId>(String.class, BookId.class);
	
	public static void create()
	{
		List<BookId> bId = find.all();
		
		if (bId.size() != 0)
			return;
		
		BookId bookId = new BookId();
		bookId.save();
	}

	public static void create(String id)
	{
		List<BookId> bId = find.all();
		
		if (bId.size() != 0)
			return;
		
		BookId bookId = new BookId(id);
		bookId.save();
	}

	public static String getBookId()
	{
		List<BookId> bId = find.all();
		
		if (bId.size() == 0)
			create();
		
		String id = bId.get(0).endId;

		find.ref(id).delete();
		
		id = Integer.toString(Integer.parseInt(id) + 1);
		
		create(id);
		
		return id;
	}
}
	
