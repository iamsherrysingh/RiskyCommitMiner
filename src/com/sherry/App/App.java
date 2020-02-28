package com.sherry.App;

import java.util.List;

import com.sherry.minedata.FindClassName;
import com.sherry.minedata.FindCommits;
import com.sherry.rest.RestClient;

public class App {

	public static void main(String[] args) {
		new App().getClassesWithIssueId("/home/sherry/commons-math/", "issueID");
	}
	
	public void getClassesWithIssueId(String repoLocation, String issueId) {
		repoLocation="/home/sherry/commons-math/";
		issueId="DUMMY ISSUE ID";

		FindClassName findClassName= new FindClassName();
		FindCommits commits= new FindCommits();
		RestClient restClient= new RestClient();
		
		
		List<String> classesToPrioritize=findClassName.getClassesToPrioritize(repoLocation, commits.findCommits(restClient.getCommentsForIssue(issueId)));
		
		for(String classX:classesToPrioritize) {
			System.out.println(classX);
		}
	}

}
