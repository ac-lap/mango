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
		return coin;
	}

	public Boolean decCoin(int val)
	{
		if ( coin < val )
			return false;
		else
		{
			coin-=val;
			return true;
		}
	}

	public static Credit create(String uId)
	{
		Credit credit = new Credit(uId);
		credit.save();
		return credit;
	}
}