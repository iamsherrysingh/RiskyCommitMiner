package com.sherry.App;

import java.util.List;

import com.sherry.minedata.FindClassName;
import com.sherry.minedata.FindCommits;
import com.sherry.rest.RestClient;

public class App {

	public static void main(String[] args) {
		
		new App().getClassesWithIssueId("/home/sherry/commons-math/", "MATH-1342");
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
