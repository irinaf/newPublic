package com.example.fw;

public class ContactHelper extends HelperBase {

	public ContactHelper(ApplicationManager applicationManager) {
		super(applicationManager);
	}

	public void createContact(Contact contact) {
		/*manager.getAutoItHelper()
		.winWaitAndActivate("AddressBook Portable", "", 5000)
		.click("Add").winWaitAndActivate("Add Contact", "", 5000)
		.send("",contact.firstname)
		.send("",contact.lastname)
		.click("Save")
		.winWaitAndActivate("AddressBook Portable", "", 5000); */
		
		
		initCintactCreation();
		fillContactForm(contact);
		confirmContactCreation(); 
		
		
		
		
		
	}

	

	private void initCintactCreation() {
		manager.getAutoItHelper()
		.winWaitAndActivate("AddressBook Portable", "", 5000)
		.click("Add").winWaitAndActivate("Add Contact", "", 5000);
	}
	
	private void fillContactForm(Contact contact) {
		manager.getAutoItHelper()
		.send("TDBEdit12",contact.firstname)
		.send("TDBEdit11",contact.lastname);
	}
	
	private void confirmContactCreation() {
		manager.getAutoItHelper()
		.click("Save")
		.winWaitAndActivate("AddressBook Portable", "", 5000);
	}

	public Contact getFirstContact() {
	
		manager.getAutoItHelper()
		.winWaitAndActivate("AddressBook Portable", "", 5000)
		.click("TListView1")
		.send("{DOWN} {SPACE}")
		.click("Edit")
		.winWaitAndActivate("Update Contact", "", 5000);
		Contact contact=new Contact()
		.setFirstName(manager.getAutoItHelper().getText("TDBEdit12"))
		.setLastName(manager.getAutoItHelper().getText("TDBEdit11"));
		manager.getAutoItHelper().click("Cancel")
		.winWaitAndActivate("AddressBook Portable", "", 5000);
		return contact;
	}

	public void deleteContact() throws InterruptedException {
		checkContact();
		confirmContactDeletion(); 
		
	}

	private void confirmContactDeletion()  {
		manager.getAutoItHelper()
		.winWaitAndActivate("Confirm", "", 5000)
		.click("TButton2");
		manager.getAutoItHelper().winWaitAndActivate("AddressBook Portable", "", 5000);
		
		
	}

	private void checkContact() {
		manager.getAutoItHelper()
		.winWaitAndActivate("AddressBook Portable", "", 5000)
		.click("TListView1")
		.send("{DOWN} {ARROW}")
		.click("Delete");
		
			
	}

	public void exitForm() {
		manager.getAutoItHelper().click("Exit");
		
	}

	public int checkContactDeletion(Contact contact)  {
			
	
		manager.getAutoItHelper()
		.send("TEdit1",contact.lastname)
		.click("TRbButton10");
				
		boolean b=manager.getAutoItHelper().windowExist("Information");
		if (b){
			 manager.getAutoItHelper().click("TButton1"); 
			 return 1;
			 }
		else return 0;
	
		
	}

	public void exitFormDeletion() {
		manager.getAutoItHelper()
		.winWaitAndActivate("AddressBook Portable", "", 5000)
		.click("Exit");
		
	}

	
}
