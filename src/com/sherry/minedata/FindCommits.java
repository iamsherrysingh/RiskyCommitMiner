package com.sherry.minedata;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FindCommits {
	public List<String> findCommitsFromComments(String jsonResultComments) {
		 List<String> allMatches = new ArrayList<String>();
		 List<String> allCommits = new ArrayList<String>();
		 
		 String pattern=",\"body\":\"[a-zA-Z0-9$&+,:;=?@#|'<>.-^*()%!\\s]*\",";

		 Matcher m = Pattern.compile(pattern)
		     .matcher(jsonResultComments);
		 while (m.find()) {
		   allMatches.add(m.group());
		 }

		 for(int i=0;i<allMatches.size();i++) {
			 String mydata= allMatches.get(i);
			 Pattern pattern1 = Pattern.compile("[\\s\\n]*[0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9]"); //assumption. commit must trail a space
			 Matcher matcher = pattern1.matcher(mydata);
			 if (matcher.find()) {
			     allCommits.add(matcher.group().trim());
			 }
		 }

		for(int i=0;i<allMatches.size();i++) {
			String mydata= allMatches.get(i);
			Pattern pattern2 = Pattern.compile("[\\s\\n]*[0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9]"); //assumption. commit must trail a space
			Matcher matcher = pattern2.matcher(mydata);
			if (matcher.find()) {
				allCommits.add(matcher.group().trim());
			}
		}

		return allCommits;
	}

}
