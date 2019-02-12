package com.issart.sboryshev.rest;

import java.util.Set;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SkipIssueTest extends TestBase {

    private int id;

    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured
            .basic("28accbe43ea112d9feb328d2c00b3eed", "");
    }

    @BeforeMethod
    public void setUp() {
        if (getIssues().size() == 0) {
            Issue newIssue = new Issue()
                .withSubject("Test subject")
                .withDescription("Test description");
            id = сreateIssue(newIssue);
        }
    }

    @Test
    public void testCreateIssue() {
        skipIfNotFixed(id);
        String json = RestAssured.given()
            .param("description", "new description")
            .put("http://demo.bugify.com/api/issues/" + id + ".json").asString();
        String newDescription = new JsonParser()
            .parse(json).getAsJsonObject().get("description").getAsString();
        assertEquals(newDescription, "new description");
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
