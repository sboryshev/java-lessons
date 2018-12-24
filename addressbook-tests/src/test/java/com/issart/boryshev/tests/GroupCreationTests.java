package com.issart.boryshev.tests;

import com.issart.boryshev.model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreate() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("testgroup2", "group1", "group footer1"));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
    }

}
