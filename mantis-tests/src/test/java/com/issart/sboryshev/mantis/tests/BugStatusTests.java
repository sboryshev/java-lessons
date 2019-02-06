package com.issart.sboryshev.mantis.tests;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.Set;
import javax.xml.rpc.ServiceException;
import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import com.issart.sboryshev.mantis.model.Issue;
import com.issart.sboryshev.mantis.model.Project;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BugStatusTests extends TestBase {

    private static String admin = "administrator";
    private static String password = "root";
    private static Issue createdIssue;
    private static String category = "";
    private static BigInteger issueId;
    private static ObjectRef projectRef = new ObjectRef();

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
//
//        issue.setProject(projectRef);
//        issue.setSummary("test bug status");
//        issue.setDescription("test description for bug");
//        issue.setCategory(category);
//        issueId = mc.mc_issue_add(admin, password, issue);
//        issue.setId(issueId);
    }

    @BeforeMethod
    public void setUp() throws RemoteException {
        if (Math.random() < 0.5) {
            issue.setStatus(new ObjectRef(issueId, "resolved"));
        }
        skipIfNotFixed(BigInteger.valueOf(createdIssue.getId()));
    }

    @Test
    public void testBugStatus() throws RemoteException {
        System.out.println(mc.mc_issue_get(admin, password, issueId).getDescription());

    }
}