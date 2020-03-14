package com.sherry.minedata;

import com.sherry.rest.RestClient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindClassName {
	
	public List<String> getClassesToPrioritize(String repoLocation, List<String> commits){
		String output="";
		List<String> classesToPrioritize=new ArrayList<String>();
		
		for(String commit:commits) {
			String command="git --git-dir "+repoLocation+".git show "+commit;
			
			
			String s;
	        Process p;
	        try {
	            p = Runtime.getRuntime().exec(command);
	            BufferedReader br = new BufferedReader(
	                new InputStreamReader(p.getInputStream()));
	            while ((s = br.readLine()) != null) {
	                output+=s;
	                output+="\n";
	            }
	            p.waitFor();
	            p.destroy();
	        } catch (Exception e) {};
		}

		return parseOutput(output);
	}
	
	private List<String> parseOutput(String inputString){
		List<String> classesToPrioritize=new ArrayList<String>();
		String pattern ="[-+][-+][-+].+\\.java";
		
		Matcher m = Pattern.compile(pattern)
			     .matcher(inputString);
			 while (m.find()) {
				 String extractedLine= m.group();
				 extractedLine=extractedLine.substring(6, extractedLine.length());
				 extractedLine= extractedLine.replaceAll("/", ".");
				 classesToPrioritize.add(extractedLine);
			 }
		
		return classesToPrioritize;
	}

	public String getClassesWithIssueId(String repoLocation, String issueId) throws IOException {
		FindCommits findCommits= new FindCommits();
		RestClient restClient= new RestClient();
		StringBuffer buffer= new StringBuffer("");

		List<String> classesToPrioritize=getClassesToPrioritize(repoLocation, findCommits.findCommits(restClient.getCommentsForIssue(issueId)));

		for(String classX:classesToPrioritize) {
			System.out.println(classX);
			buffer.append(classX+"\n");
		}

		return buffer.toString();
	}
    
	
}