package com.issart.sboryshev.rest;

import java.util.Set;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests extends TestBase {

    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured
            .basic("28accbe43ea112d9feb328d2c00b3eed", "");
    }

    @Test
    public void testCreateIssue() {
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue()
            .withSubject("Test subject")
            .withDescription("Test description");
        int issueId = сreateIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }

    private Set<Issue> getIssues() {
        String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson()
            .fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
    }

    private int сreateIssue(Issue newIssue) {
        String json = RestAssured.given()
            .param("subject", newIssue.getSubject())
            .param("description", newIssue.getDescription())
            .post("http://demo.bugify.com/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
}
