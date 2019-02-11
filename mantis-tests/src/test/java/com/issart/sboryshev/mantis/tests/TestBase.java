package com.issart.sboryshev.mantis.tests;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import com.issart.sboryshev.mantis.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    public static MantisConnectPortType mc;

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty(
        "browser", BrowserType.CHROME));

    public TestBase() throws MalformedURLException, ServiceException {
        mc = new MantisConnectLocator()
            .getMantisConnectPort(new URL(app.getProperty("web.mantisConnectPort")));
    }

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php",
            "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }

    public boolean isIssueOpen(BigInteger issueId) throws RemoteException {
        String status = mc.mc_issue_get(
            app.getProperty("web.adminLogin"),
            app.getProperty("web.adminPassword"), issueId).getStatus().getName();
        return status.equals("new") ;
    }

    public void skipIfNotFixed(BigInteger issueId) throws RemoteException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
