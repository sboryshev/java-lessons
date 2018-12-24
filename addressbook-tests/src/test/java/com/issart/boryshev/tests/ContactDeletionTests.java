package com.issart.boryshev.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDelete() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().goToHomePage();
    }
}
