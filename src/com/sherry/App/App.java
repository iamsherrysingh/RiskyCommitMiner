package com.sherry.App;

import java.io.FileInputStream;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

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
		Set<String> issueIdsList= findIssueID.findIssueIds(jsonOutput);
		System.out.println(issueIdsList);
		System.out.println("Issue Ids found: "+issueIdsList.size());
		writeToFile(issueIdsList, "issueIDs.fileOutput");

		//scan local repo to find classes that changed
		Set<String> classesList= new HashSet<>();
		for(String issueId: issueIdsList) {
			String classFound=findClassName.getClassesWithIssueId(properties.getProperty(repoLocationProperty), issueId);
			if(!classFound.equalsIgnoreCase("")){classFound.substring(14); classesList.add(classFound);}
		}
		writeToFile(classesList, "classes.fileOutput");

		//find dependencies for classes found above
		Set<String> dependenciesList= new HashSet<>();
		for(String clas: classesList) {
			String dependencyFound=findClassName.getDependenciesForClass(properties.getProperty(repoLocationProperty), clas );
			if(!dependencyFound.equalsIgnoreCase("")){dependenciesList.add(dependencyFound);}
		}
		writeToFile(dependenciesList, "dependencies.fileOutput");

		System.out.println("==========");
		String x= findClassName.getDependenciesForClass(properties.getProperty(repoLocationProperty), "org.apache.commons.math3.analysis.SumSincFunction.java");
		System.out.println(x);
	}

	private static boolean writeToFile(List<String> data, String fileLocationInProperties) throws IOException {
		Properties properties= new Properties();
		properties.load(new FileInputStream("src/config.properties"));
		try {
			FileWriter fileWriter = new FileWriter(properties.getProperty(fileLocationInProperties));
			for(String s: data){fileWriter.write(s+"\n");}
			fileWriter.close();
		}catch(Exception e){
			throw e;
		}
		return true;
	}

	private static boolean writeToFile(Set<String> data, String fileLocationInProperties) throws IOException {
		Properties properties= new Properties();
		properties.load(new FileInputStream("src/config.properties"));
		try {
			FileWriter fileWriter = new FileWriter(properties.getProperty(fileLocationInProperties));
			for(String s: data){fileWriter.write(s+"\n");}
			fileWriter.close();
		}catch(Exception e){
			throw e;
		}
		return true;
	}

}
