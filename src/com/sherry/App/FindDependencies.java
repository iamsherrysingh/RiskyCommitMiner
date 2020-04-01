package com.sherry.App;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.sherry.minedata.FindClassName;

public class FindDependencies {
    public static void main(String[] args) throws IOException {
        FindClassName findClassName = new FindClassName();
        String classList=new String ( Files.readAllBytes( Paths.get("src/classesList.txt") ) );
//        System.out.println(classList);
        String classArray[]=classList.split(",");
        Set<String> dependencies= new HashSet<>();
        for(String eachClass: classArray) {
             dependencies.addAll(findClassName.getDependenciesForClass2("/home/sherry/commons-math/", eachClass.trim()));
        }
        for (String s : dependencies) {
            System.out.println(s);
        }



    }
}
