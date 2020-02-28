package com.sherry.minedata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindIssueID {
	
	public List<String> findIssueIds(String jsonResultAffectedVersions) {
		
		//Potential Regex ,"key":"[A-Z]-[0-9]",
		//","key":"MATH-1347","
		//Regex:   ","key":"MATH-[0-9]+","
		
		Set<String> allMatches= new HashSet<String>();
		List<String> allIssueIds= new ArrayList<String>();
		
		String pattern="\",\"key\":\"MATH-[0-9]+\",\"";
		
		Matcher m= Pattern.compile(pattern)
						  .matcher(jsonResultAffectedVersions);
		while(m.find()) {
			allMatches.add(m.group());
		}
		
		
		for(String mydata: allMatches) {
			Pattern pattern1= Pattern.compile("");
			Matcher matcher= pattern1.matcher(mydata);
			if(matcher.find()) {
				allIssueIds.add(matcher.group().trim());
			}
		}
		
	return allIssueIds;
	}

}
