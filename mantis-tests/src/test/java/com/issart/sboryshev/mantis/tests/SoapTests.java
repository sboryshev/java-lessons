package com.issart.sboryshev.mantis.tests;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;
import javax.xml.rpc.ServiceException;
import com.issart.sboryshev.mantis.model.Issue;
import com.issart.sboryshev.mantis.model.Project;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase{

    public SoapTests() throws MalformedURLException, ServiceException {
    }

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue()
            .withSummary("Test issue")
            .withDescription("Test issue description")
            .withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }
}
