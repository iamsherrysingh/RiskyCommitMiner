package com.sherry.App;

import java.util.List;

import com.sherry.minedata.FindClassName;
import com.sherry.minedata.FindCommits;

public class App {

	public static void main(String[] args) {
		new App().test();
		
	}
	
	public void test() {
		// TODO Auto-generated method stub

		FindClassName findClassName= new FindClassName();
		FindCommits commits= new FindCommits();
		
		List<String> classesToPrioritize=findClassName.getClassesToPrioritize("/home/sherry/commons-math/", commits.findCommits(""));
		
		for(String classX:classesToPrioritize) {
			System.out.println(classX);
		}
	}

}
