package com.sherry.App;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.sherry.minedata.FindClassName;
import com.sherry.minedata.FindCommits;
import com.sherry.minedata.FindIssueID;
import com.sherry.rest.RestClient;

public class App {

	public static void main(String[] args) throws IOException {
		
		App app= new App();
		Properties properties= new Properties();
		RestClient restClient= new RestClient();
		FindIssueID findIssueID= new FindIssueID();

		properties.load(new FileInputStream("src/config.properties"));

		//get JSON result with JQL request
		String jsonOutput= restClient.getListOfIssues(properties.getProperty("project"), properties.getProperty("version"));
		System.out.println("JSON Output: "+jsonOutput);

		//filter JSON request to find all issue IDs
		List<String> issueIds= findIssueID.findIssueIds(jsonOutput);
		System.out.println(issueIds);
		System.out.println("Issue Ids found: "+issueIds.size());

		//scan local repo to find classes that changed
		System.out.println("Classes Found:");
		for(String issueId: issueIds) {
			app.getClassesWithIssueId(properties.getProperty("localRepoLocation"), issueId);
		}

	}
	
	public void getClassesWithIssueId(String repoLocation, String issueId) {
		FindClassName findClassName= new FindClassName();
		FindCommits findCommits= new FindCommits();
		RestClient restClient= new RestClient();
			
		List<String> classesToPrioritize=findClassName.getClassesToPrioritize(repoLocation, findCommits.findCommits(restClient.getCommentsForIssue(issueId)));
		
		for(String classX:classesToPrioritize) {
			System.out.println(classX);
		}
	}
}
