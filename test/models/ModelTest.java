package models;

import models.*;
import org.junit.*;
import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;

public class ModelTest extends WithApplication {
    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }
    
    @Test
    public void createAndRetrieveUser() 
    {
    	User.create("bob@gmail.com", "secret");
        //User bob = User.find.where().eq("emailId", "bob@gmail.com").findUnique();
        //assertNotNull(bob);
        //assertEquals("secret", bob.accDetails.password);
        AccountDetails temp = AccountDetails.find.where().eq("emailId", "bob@gmail.com").findUnique();
        assertNotNull(temp);
        assertEquals(temp.password, "secret");
        
    }
}