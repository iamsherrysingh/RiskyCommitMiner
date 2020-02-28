package com.sherry.minedata;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FindCommits {  //(",\"body\":\".+\",\"")

	public List<String> findCommits(String inputString) {

		 List<String> allMatches = new ArrayList<String>();
		 List<String> allCommits = new ArrayList<String>();
		 
		 String pattern=",\"body\":\"[a-zA-Z0-9$&+,:;=?@#|'<>.-^*()%!\\s]*\",";
		 inputString= "{\"startAt\":0,\"maxResults\":1048576,\"total\":3,\"comments\":[{\"self\":\"https://issues.apache.org/jira/rest/api/2/issue/12950877/comment/15198085\",\"id\":\"15198085\",\"author\":{\"self\":\"https://issues.apache.org/jira/rest/api/2/user?username=evanward1\",\"name\":\"evanward1\",\"key\":\"evanward1\",\"avatarUrls\":{\"48x48\":\"https://issues.apache.org/jira/secure/useravatar?avatarId=10452\",\"24x24\":\"https://issues.apache.org/jira/secure/useravatar?size=small&avatarId=10452\",\"16x16\":\"https://issues.apache.org/jira/secure/useravatar?size=xsmall&avatarId=10452\",\"32x32\":\"https://issues.apache.org/jira/secure/useravatar?size=medium&avatarId=10452\"},\"displayName\":\"Evan Ward\",\"active\":true,\"timeZone\":\"America/New_York\"},\"body\":\"fixed in 7a8dc00\",\"updateAuthor\":{\"self\":\"https://issues.apache.org/jira/rest/api/2/user?username=evanward1\",\"name\":\"evanward1\",\"key\":\"evanward1\",\"avatarUrls\":{\"48x48\":\"https://issues.apache.org/jira/secure/useravatar?avatarId=10452\",\"24x24\":\"https://issues.apache.org/jira/secure/useravatar?size=small&avatarId=10452\",\"16x16\":\"https://issues.apache.org/jira/secure/useravatar?size=xsmall&avatarId=10452\",\"32x32\":\"https://issues.apache.org/jira/secure/useravatar?size=medium&avatarId=10452\"},\"displayName\":\"Evan Ward\",\"active\":true,\"timeZone\":\"America/New_York\"},\"created\":\"2016-03-16T20:23:08.654+0000\",\"updated\":\"2016-03-16T20:23:08.654+0000\"},{\"self\":\"https://issues.apache.org/jira/rest/api/2/issue/12950877/comment/15199457\",\"id\":\"15199457\",\"author\":{\"self\":\"https://issues.apache.org/jira/rest/api/2/user?username=evanward1\",\"name\":\"evanward1\",\"key\":\"evanward1\",\"avatarUrls\":{\"48x48\":\"https://issues.apache.org/jira/secure/useravatar?avatarId=10452\",\"24x24\":\"https://issues.apache.org/jira/secure/useravatar?size=small&avatarId=10452\",\"16x16\":\"https://issues.apache.org/jira/secure/useravatar?size=xsmall&avatarId=10452\",\"32x32\":\"https://issues.apache.org/jira/secure/useravatar?size=medium&avatarId=10452\"},\"displayName\":\"Evan Ward\",\"active\":true,\"timeZone\":\"America/New_York\"},\"body\":\"fixed in 5238b00 on 3.X\",\"updateAuthor\":{\"self\":\"https://issues.apache.org/jira/rest/api/2/user?username=evanward1\",\"name\":\"evanward1\",\"key\":\"evanward1\",\"avatarUrls\":{\"48x48\":\"https://issues.apache.org/jira/secure/useravatar?avatarId=10452\",\"24x24\":\"https://issues.apache.org/jira/secure/useravatar?size=small&avatarId=10452\",\"16x16\":\"https://issues.apache.org/jira/secure/useravatar?size=xsmall&avatarId=10452\",\"32x32\":\"https://issues.apache.org/jira/secure/useravatar?size=medium&avatarId=10452\"},\"displayName\":\"Evan Ward\",\"active\":true,\"timeZone\":\"America/New_York\"},\"created\":\"2016-03-17T12:59:46.209+0000\",\"updated\":\"2016-03-17T12:59:46.209+0000\"},{\"self\":\"https://issues.apache.org/jira/rest/api/2/issue/12950877/comment/15254969\",\"id\":\"15254969\",\"author\":{\"self\":\"https://issues.apache.org/jira/rest/api/2/user?username=erans\",\"name\":\"erans\",\"key\":\"erans\",\"avatarUrls\":{\"48x48\":\"https://issues.apache.org/jira/secure/useravatar?avatarId=10452\",\"24x24\":\"https://issues.apache.org/jira/secure/useravatar?size=small&avatarId=10452\",\"16x16\":\"https://issues.apache.org/jira/secure/useravatar?size=xsmall&avatarId=10452\",\"32x32\":\"https://issues.apache.org/jira/secure/useravatar?size=medium&avatarId=10452\"},\"displayName\":\"Gilles Sadowski\",\"active\":true,\"timeZone\":\"Europe/Brussels\"},\"body\":\"Code was released in CM v3.6.1.\",\"updateAuthor\":{\"self\":\"https://issues.apache.org/jira/rest/api/2/user?username=erans\",\"name\":\"erans\",\"key\":\"erans\",\"avatarUrls\":{\"48x48\":\"https://issues.apache.org/jira/secure/useravatar?avatarId=10452\",\"24x24\":\"https://issues.apache.org/jira/secure/useravatar?size=small&avatarId=10452\",\"16x16\":\"https://issues.apache.org/jira/secure/useravatar?size=xsmall&avatarId=10452\",\"32x32\":\"https://issues.apache.org/jira/secure/useravatar?size=medium&avatarId=10452\"},\"displayName\":\"Gilles Sadowski\",\"active\":true,\"timeZone\":\"Europe/Brussels\"},\"created\":\"2016-04-23T00:32:32.907+0000\",\"updated\":\"2016-04-23T00:32:32.907+0000\"}]}\r\n" + 
		 		"";
		 
		 Matcher m = Pattern.compile(pattern)
		     .matcher(inputString);
		 while (m.find()) {
		   allMatches.add(m.group());
		 }
		 
		/*
		 * for(String s:allMatches) { System.out.println(s); }
		 */
		 
		 for(int i=0;i<allMatches.size();i++) {
			 String mydata= allMatches.get(i);
			 Pattern pattern1 = Pattern.compile("\\s+[0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][a-z0-9][0-9]"); //assumption. commit must trail a space
			 Matcher matcher = pattern1.matcher(mydata);
			 if (matcher.find())
			 {
			     allCommits.add(matcher.group().trim());
			 }
		 }
		 
//		 for(String s:allCommits) {
//			 System.out.println(s);
//		 }
		 
		 
		 return allCommits;
	}

}
