package com.sherry.App;

import com.sherry.minedata.FindClassName;

public class FindDependencies {
    public static void main(String[] args) {
        FindClassName findClassName = new FindClassName();
        String dependencies= findClassName.getDependenciesForClass("/home/sherry/commons-math/", "src.main.java.org.apache.commons.math.ode.AbstractIntegrator.java");
        System.out.println(dependencies);
    }
}
