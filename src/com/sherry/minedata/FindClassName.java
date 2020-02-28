package com.sherry.minedata;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
//	            System.out.println ("exit: " + p.exitValue());
	            p.destroy();
	        } catch (Exception e) {};
			
//			System.out.println(output);
//			System.out.println("=========================");
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
    
	
}