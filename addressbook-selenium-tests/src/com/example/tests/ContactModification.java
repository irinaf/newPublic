package com.example.tests;

import static com.example.tests.ContactDataGenerator.loadContactsFromXmlFile;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.utils.SortedListOf;


public class ContactModification extends TestBase{

	

	@DataProvider
	public Iterator<Object[]>contactsFromFile() throws IOException{
		return wrapContactsForDataProvider(loadContactsFromXmlFile (new File("contact.xml"))).iterator();
	}
	
	//@Test(dataProvider="randomValidContactGenerator")
	@Test(dataProvider="contactsFromFile")	
	public void modifyContact(ContactData contacts){
	

	 // save old
	 SortedListOf<ContactData> oldList=app.getContactHelper().getContacts();
    Random rnd =new Random();
    int index= rnd.nextInt(oldList.size()-1);
	
	app.getContactHelper().modifySomeContact(index,contacts);
    
    /*app.getContactHelper()
	 .initContactModification(index)   
	 .fillContactForm(contacts,MODIFICATION)
	 .submitContactModification()
	 .gotoHomePage();  */
		
	
	/*ContactData contacts=new ContactData();
    contacts.f_name="���";
    contacts.l_name="Ivanova";
    contacts.b_month="January";*/
	
   
	
    // save new
	 SortedListOf<ContactData> newList=app.getContactHelper().getContacts(); 
    
    //compare
	    
    /*  oldList.remove(index);	 
      oldList.add(contacts);
      Collections.sort(oldList);
      assertEquals(newList,oldList);  */
    
	 
	 assertThat(newList,equalTo(oldList.without(index).withAdded(contacts)));
   }
}	
