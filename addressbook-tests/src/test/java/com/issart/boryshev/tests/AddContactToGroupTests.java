package com.issart.boryshev.tests;

import com.issart.boryshev.model.ContactData;
import com.issart.boryshev.model.Contacts;
import com.issart.boryshev.model.GroupData;
import com.issart.boryshev.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                .withFirstName("test")
                .withLastName("test")
                .withTitle("test")
                .withAddress("test")
                .withMobilePhone("+798845137534")
                .withEmail1("test@mail.ru"));
        }

        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData()
                .withName("test")
                .withHeader("header")
                .withFooter("footer"));
        }
    }

    @Test
    public void testContactAddToGroup() {
        app.goTo().homePage();

        Groups groupsBefore = app.db().groups();
        Contacts contactsBefore = app.db().contacts();
        ContactData addingContact = contactsBefore.iterator().next();
        GroupData destinationGroup = groupsBefore.iterator().next();
        Groups groups = addingContact.getGroups();

        app.contact().addToGroup(addingContact, destinationGroup.getId());

        Groups groupsAfter = app.db().contacts().getById(addingContact.getId()).getGroups();

        assertThat(groupsAfter, equalTo(groups.withAdded(destinationGroup)));
    }
}
