package com.issart.sboryshev.mantis.tests;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;
import javax.xml.rpc.ServiceException;
import com.issart.sboryshev.mantis.model.Issue;
import com.issart.sboryshev.mantis.model.Project;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BugStatusTests extends TestBase {

    private static Issue createdIssue;

    public BugStatusTests() throws MalformedURLException, ServiceException {
    }

    @BeforeClass
    public static void createBug() throws RemoteException, MalformedURLException, ServiceException {
        Set<Project> projects = app.soap().getProjects();

        if (projects == null) {
            Project newProject = new Project().withName("Test project");
            projects.add(app.soap().addProject(newProject));
        }
        Issue issue = new Issue()
            .withSummary("Test issue")
            .withDescription("Test issue description")
            .withProject(projects.iterator().next());
        createdIssue = app.soap().addIssue(issue);
    }

    @BeforeMethod
    public void setUp() throws RemoteException {
        if (Math.random() > 0.5) {
            try {
                app.soap().changeStatus(createdIssue, "resolved");
            } catch (MalformedURLException | ServiceException e) {
                e.printStackTrace();
            }
        } else {
            try {
                app.soap().changeStatus(createdIssue, "new");
            } catch (MalformedURLException | ServiceException e) {
                e.printStackTrace();
            }
        }
    }

    @Test(invocationCount = 10)
    public void testBugStatus() throws RemoteException {
        skipIfNotFixed(BigInteger.valueOf(createdIssue.getId()));

        System.out.println(mc.mc_issue_get(
            app.getProperty("web.adminLogin"),
            app.getProperty("web.adminPassword"),
            BigInteger.valueOf(createdIssue.getId())).getStatus().getName());
    }
}