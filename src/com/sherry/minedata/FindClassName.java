package com.sherry.minedata;

import com.sherry.rest.RestClient;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindClassName {
	public String getClassesUsingIssueId(String repoLocation, String issueId) throws IOException {
		FindCommits findCommits= new FindCommits();
		RestClient restClient= new RestClient();
		StringBuffer buffer= new StringBuffer("");

		List<String> commits= findCommits.findCommitsFromComments(restClient.getCommentsFromIssueID(issueId));
		Set<String> classesToPrioritize= getClassesFromCommits(repoLocation, commits);

		for(String classX:classesToPrioritize) {
			buffer.append(classX+"\n");
		}

		return buffer.toString();
	}

	public String getDependenciesForClass(String repoLocation, String fullyQualifiedClassName){
		if(fullyQualifiedClassName.trim().equalsIgnoreCase("")){
			return "";
		}

		//remove trailing .java
		fullyQualifiedClassName=fullyQualifiedClassName.substring(0,fullyQualifiedClassName.length() - 4 - 1);
		String[] splitClassName= fullyQualifiedClassName.trim().split("[.]");
		String className= splitClassName[splitClassName.length-1];  //getting the class name from fully qualified class name

		String commandToFindObjectCreation= "grep -r 'new "+className.trim()+"(' "+repoLocation+"*";
		String commandToFindImports="grep -r 'import .*."+className.trim()+";' "+repoLocation+"*";
		String commandToFindStaticCalls= "grep -r '"+className.trim()+"\\.' "+repoLocation+"*";

		String line="";
		Process p;
		String output="";
		try {    //Find imports
			String[] cmd = {"/bin/sh", "-c", commandToFindImports};
			Process proc = Runtime.getRuntime().exec(cmd);
			InputStream in= proc.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while ((line = br.readLine()) != null) {
				line=line.split(":")[0];                    // reading output of grep pre :
				line= line.replaceAll("/","."); //replace slash with .
				line= line.substring(repoLocation.length(), line.length());  //remove trailing repo location from file path
				if((line.substring(line.length()-5,line.length()).equalsIgnoreCase(".java"))){ //considering only .java files
					output+=line+"\n";}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {    //Find Object Creation
			String[] cmd = {"/bin/sh", "-c", commandToFindObjectCreation};
			Process proc = Runtime.getRuntime().exec(cmd);
			InputStream in= proc.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while ((line = br.readLine()) != null) {
				line=line.split(":")[0];                    // reading output of grep pre :
				line= line.replaceAll("/","."); //replace slash with .
				line= line.substring(repoLocation.length(), line.length());  //remove trailing repo location from file path
				if((line.substring(line.length()-5,line.length()).equalsIgnoreCase(".java"))){  //considering only .java files
					output+=line+"\n";}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {    //Find Static Calls
			String[] cmd = {"/bin/sh", "-c", commandToFindStaticCalls};
			Process proc = Runtime.getRuntime().exec(cmd);
			InputStream in= proc.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while ((line = br.readLine()) != null) {
				line=line.split(":")[0];                    // reading output of grep pre :
				line= line.replaceAll("/","."); //replace slash with .
				line= line.substring(repoLocation.length(), line.length());  //remove trailing repo location from file path
				if((line.substring(line.length()-5,line.length()).equalsIgnoreCase(".java"))){  //considering only .java files
					output+=line+"\n";}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return output.trim();
	}

	public Set<String> getDependenciesForClass2(String repoLocation, String fullyQualifiedClassName){
		if(fullyQualifiedClassName.trim().equalsIgnoreCase("")){
			return null;
		}

		//remove trailing .java
		fullyQualifiedClassName=fullyQualifiedClassName.substring(0,fullyQualifiedClassName.length() - 4 - 1);
		String[] splitClassName= fullyQualifiedClassName.trim().split("[.]");
		String className= splitClassName[splitClassName.length-1];  //getting the class name from fully qualified class name

		String commandToFindObjectCreation= "grep -r 'new "+className.trim()+"(' "+repoLocation+"*";
		String commandToFindImports="grep -r 'import .*."+className.trim()+";' "+repoLocation+"*";
		String commandToFindStaticCalls= "grep -r '"+className.trim()+"\\.' "+repoLocation+"*";

		String line="";
		Process p;
		String output="";
		Set<String> dependencySet= new HashSet<String>();
		try {    //Find imports
			String[] cmd = {"/bin/sh", "-c", commandToFindImports};
			Process proc = Runtime.getRuntime().exec(cmd);
			InputStream in= proc.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while ((line = br.readLine()) != null) {
				line=line.split(":")[0];                    // reading output of grep pre :
				line= line.replaceAll("/","."); //replace slash with .
				line= line.substring(repoLocation.length(), line.length());  //remove trailing repo location from file path

				try{
					if((line.substring(line.length()-5,line.length()).equalsIgnoreCase(".java"))){ //considering only .java files
//					output+=line+"\n";
						if(line.split("[.]")[1].equalsIgnoreCase("main")) //ignore test dependencies
						{dependencySet.add(line);}
					}
				}catch(Exception e){}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {    //Find Object Creation
			String[] cmd = {"/bin/sh", "-c", commandToFindObjectCreation};
			Process proc = Runtime.getRuntime().exec(cmd);
			InputStream in= proc.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while ((line = br.readLine()) != null) {
				line=line.split(":")[0];                    // reading output of grep pre :
				line= line.replaceAll("/","."); //replace slash with .
				line= line.substring(repoLocation.length(), line.length());  //remove trailing repo location from file path
				try{
					if((line.substring(line.length()-5,line.length()).equalsIgnoreCase(".java"))){ //considering only .java files
//					output+=line+"\n";
						if(line.split("[.]")[1].equalsIgnoreCase("main")) //ignore test dependencies
						{dependencySet.add(line);}
					}
				}catch(Exception e){}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {    //Find Static Calls
			String[] cmd = {"/bin/sh", "-c", commandToFindStaticCalls};
			Process proc = Runtime.getRuntime().exec(cmd);
			InputStream in= proc.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while ((line = br.readLine()) != null) {
				line=line.split(":")[0];                    // reading output of grep pre :
				line= line.replaceAll("/","."); //replace slash with .
				line= line.substring(repoLocation.length(), line.length());  //remove trailing repo location from file path
				try{
					if((line.substring(line.length()-5,line.length()).equalsIgnoreCase(".java"))){ //considering only .java files
//					output+=line+"\n";
						if(line.split("[.]")[1].equalsIgnoreCase("main")) //ignore test dependencies
						{dependencySet.add(line);}
					}
				}catch(Exception e){}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dependencySet;
	}
	
	private Set<String> getClassesFromCommits(String repoLocation, List<String> commits){
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
	
	private Set<String> parseOutput(String inputString){
		Set<String> classesToPrioritize=new HashSet<String>();
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
}