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

		//Find host Operating System and use the appropriate property from the properties file.
		String hostOS= System.getProperty("os.name").split(" ")[0];
		String repoLocationProperty="Local git repository not configured or host operating OS unknown.\n Please manually over-ride App.repoLocationProperty";
		if(hostOS.equalsIgnoreCase("windows")){
			repoLocationProperty="localRepoLocationWindows";
		}else if(hostOS.equalsIgnoreCase("linux")){
			repoLocationProperty="localRepoLocationLinux";
		}else if(hostOS.equalsIgnoreCase("windows")) {
			repoLocationProperty="localRepoLocationMac";
		}

		//scan local repo to find classes that changed
		System.out.println("Classes Found:");
		for(String issueId: issueIds) {
				app.getClassesWithIssueId(properties.getProperty(repoLocationProperty), issueId);
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
