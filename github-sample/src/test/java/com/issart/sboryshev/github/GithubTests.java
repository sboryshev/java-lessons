package com.issart.sboryshev.github;

import java.io.IOException;
import com.google.common.collect.ImmutableMap;
import com.jcabi.github.Coordinates;
import com.jcabi.github.Github;
import com.jcabi.github.RepoCommit;
import com.jcabi.github.RepoCommits;
import com.jcabi.github.RtGithub;
import org.testng.annotations.Test;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("ec2da7ea46d93353c13d8bcddc21a00006ce84f1");
        RepoCommits commits = github.repos()
            .get(new Coordinates.Simple("sboryshev", "java-lessons")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
