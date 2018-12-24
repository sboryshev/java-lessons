package com.issart.boryshev.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDelete() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }

}
