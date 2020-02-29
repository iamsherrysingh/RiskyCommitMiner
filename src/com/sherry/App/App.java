package com.sherry.App;

import java.util.List;

import com.sherry.minedata.FindClassName;
import com.sherry.minedata.FindCommits;
import com.sherry.minedata.FindIssueID;
import com.sherry.rest.RestClient;

public class App {

	public static void main(String[] args) {
		
		App app= new App();

		//Testing area below this line
		RestClient restClient= new RestClient();
		FindIssueID findIssueID= new FindIssueID();
		
		String jsonOutput= restClient.getListOfIssues("MATH", "3.0");
		System.out.println("JSON Output: "+jsonOutput);

		List<String> issueIds= findIssueID.findIssueIds(jsonOutput);
		System.out.println("Issue Ids found: "+issueIds.size());

		System.out.println("Classes Found:");
		for(String issueId: issueIds) {
			app.getClassesWithIssueId("/home/sherry/commons-math/", issueId);
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
