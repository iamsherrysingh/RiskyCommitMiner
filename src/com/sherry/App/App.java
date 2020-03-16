package com.sherry.App;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import com.sherry.minedata.FindClassName;
import com.sherry.minedata.FindIssueID;
import com.sherry.rest.RestClient;

public class App {

	public static void main(String[] args) throws IOException {
		FindClassName findClassName= new FindClassName();
		Properties properties= new Properties();
		RestClient restClient= new RestClient();
		FindIssueID findIssueID= new FindIssueID();

		//loading config.properties file
		properties.load(new FileInputStream("src/config.properties"));

		//Find host Operating System and use the appropriate property from the properties file.
		String hostOS= System.getProperty("os.name").split(" ")[0];
		String repoLocationProperty="";
		if(hostOS.equalsIgnoreCase("Windows")){
			repoLocationProperty="localRepoLocationWindows";
		}else if(hostOS.equalsIgnoreCase("Linux")){
			repoLocationProperty="localRepoLocationLinux";
		}else if(hostOS.equalsIgnoreCase("Mac") || hostOS.equalsIgnoreCase("Macintosh")) {
			repoLocationProperty="localRepoLocationMac";
		}else{
			System.out.println("ERROR: Local git repository not configured or host operating OS unknown.\n" +
								"Please manually over-ride App.repoLocationProperty");
			System.exit(-1);
		}

		//get JSON result using JQL request
		String jsonOutput= restClient.getListOfIssues(properties.getProperty("project"), properties.getProperty("version"));
		System.out.println("JSON Output: "+jsonOutput);

		//filter JSON request to find all issue IDs
		List<String> issueIds= findIssueID.findIssueIds(jsonOutput);
		System.out.println(issueIds);
		System.out.println("Issue Ids found: "+issueIds.size());
		FileWriter fileWriter= new FileWriter(properties.getProperty("issueIDs.fileOutput"));
		fileWriter.write("Total Issues: "+issueIds.size()+"\n"+issueIds.toString());
		fileWriter.close();

		//scan local repo to find classes that changed
		System.out.println("Classes Found:");
		StringBuffer buffer= new StringBuffer("Classes that have changed\n" +
				                              "=========================\n");
		for(String issueId: issueIds) {
			buffer.append(findClassName.getClassesWithIssueId(properties.getProperty(repoLocationProperty), issueId));
		}
		//TO-DO remove duplicates in classes list
		//TO-DO find classes that depend on these classes. and log them
		fileWriter= new FileWriter(properties.getProperty("classes.fileOutput"));

		fileWriter.write(buffer.toString().trim());
		fileWriter.close();

	}

}
