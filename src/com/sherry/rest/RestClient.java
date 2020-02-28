package com.sherry.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient {
	
    public String getCommentsForIssue(String issueID) {
    	String output="";
        try {
        	String out;

            URL url = new URL("https://issues.apache.org/jira/rest/api/2/issue/"+issueID+"/comment");//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            while ((out = br.readLine()) != null) {
               output+=out;
            }
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in getCommentsForIssue:- " + e);
        }
        
        return output;
    }
    
    public String getListOfIssues(String project, String affectedVersion) {
    	project="MATH";
    	affectedVersion="3.6";
    	String output="";
        try {

            URL url = new URL("https://issues.apache.org/jira/rest/api/2/search?jql=project="+project+"+AND+type=bug+and+affectedVersion="+affectedVersion);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
//            while ((output = br.readLine()) != null) {
//                System.out.println(output);
//            }
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in getCommentsForIssue:- " + e);
        }
        
        return output;
    }
}