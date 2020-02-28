package com.sherry.App;

import java.util.List;

import com.sherry.minedata.FindClassName;
import com.sherry.minedata.FindCommits;
import com.sherry.minedata.FindIssueID;
import com.sherry.rest.RestClient;

public class App {

	public static void main(String[] args) {
		
//		new App().getClassesWithIssueId("/home/sherry/commons-math/", "MATH-1342");
		
		
		//Testing area below this line
		RestClient restClient= new RestClient();
		FindIssueID findIssueID= new FindIssueID();
		
		String jsonOutput= restClient.getListOfIssues("project", "affectedVersion");
		System.out.println("JSON Output");
		System.out.println(jsonOutput);
		
//		
//		System.out.println("\nIssue IDs:");
//		List<String> issueIds= findIssueID.findIssueIds(jsonOutput);
//		for(String issueId: issueIds) {
//			System.out.println(issueId);
//		}
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
