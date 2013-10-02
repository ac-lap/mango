package models;

import models.*;
import org.junit.*;
import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;

import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import java.util.*;

public class ModelTest extends WithApplication {
    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }
    
    @Test
    public void createAndRetrieveUser() 
    {
    	Ebean.save((List) Yaml.load("test-data.yml"));
        
        AccountDetails temp = AccountDetails.find.where().eq("emailId", "bob@gmail.com").findUnique();
        assertNotNull(temp);
        assertEquals(temp.password, "secret");
        
    }
}