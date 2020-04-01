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
        String classArray[]=classList.split(",");
        Set<String> dependencies= new HashSet<>();
        for(String eachClass: classArray) {
            if(eachClass.trim().length()>0)
                dependencies.addAll(findClassName.getDependenciesForClass2("/home/sherry/commons-math/", eachClass.trim()));
        }
        for (String s : dependencies) {
            System.out.println(s);
        }



    }
}
