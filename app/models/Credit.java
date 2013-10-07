package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;


@Entity
public class Credit extends Model
{
	@Id
	String userId;

	private int coin;

	public Credit(String userId)
	{
		this.userId = userId;
		coin = 100;
	}

	public int getCoin()
	{
		return coin;
	}

	public int incCoin(int val)
	{
		coin+=val;
		save();
		return coin;
	}

	public Boolean decCoin(int val)
	{
		if ( coin < val )
			return false;
		else
		{
			coin-=val;
			save();
			return true;
		}
	}

	public static Credit create(String uId)
	{
		Credit credit = new Credit(uId);
		credit.save();
		return credit;
	}

	// -------- Queries
	public static Model.Finder<String,Credit> find = new Model.Finder<String,Credit>(String.class, Credit.class);

	public static Credit findByUserId(String uId)
	{
		return find.where().eq("userId", uId).findUnique();
	}
}