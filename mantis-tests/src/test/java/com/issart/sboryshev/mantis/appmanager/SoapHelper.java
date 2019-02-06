package com.issart.sboryshev.mantis.appmanager;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import javax.xml.rpc.ServiceException;
import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import com.issart.sboryshev.mantis.model.Issue;
import com.issart.sboryshev.mantis.model.Project;

public class SoapHelper {

    private final ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
            .getMantisConnectPort(new URL("http://localhost/mantisbt-1.2.19/api/soap/mantisconnect.php"));
    }

    public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
        return Arrays.asList(projects).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
            .collect(Collectors.toSet());
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories("administrator", "root",
            BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()),
            issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add("administrator", "root", issueData);
        IssueData createdIssueData = mc.mc_issue_get("administrator", "root", issueId);
        return new Issue()
            .withId(createdIssueData.getId().intValue())
            .withSummary(createdIssueData.getSummary())
            .withDescription(createdIssueData.getDescription())
            .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                .withName(createdIssueData.getProject().getName()));
    }

    public Project addProject(Project project) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData projectData = new ProjectData();
        projectData.setName(project.getName());
        BigInteger projectId = mc.mc_project_add("administrator", "root", projectData);
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
        ProjectData createdProject = projects[0];
        return new Project()
            .withId(projectId.intValue())
            .withName(createdProject.getName());
    }
}
