# RiskyCommitMiner
This is a tool I am developing as part of my school project. It interfaces with Apache.org(or another website. Depends how you configure) JIRA repositories, git source code and finds a list of JAVA classes that have changed as part of a bug fix. Those classes can be used to prioritize test cases.

### Instructions:
1. Clone git repository to you local machine.
```$xslt
git clone <project git URL>
```
2. Add location of the local repo to the src/config.properties file (just for your Operating System, ignore the other two OS properties)
3. Configure 'project' and 'version' properties in src/config.properties to pass inputs to the program.
4. Run com.sherry.App.App.main(String[] args)
5. Find Issue IDs and Classes that changed in 'output' directory  


### Sample configuration(src/config.properties) for Windows:
```
    #RiskyCommitMiner INPUTS
project=MATH
version=3.0
localRepoLocationWindows=C:/commons-math/
```

### Sample configuration(src/config.properties) for Linux:
```
    #RiskyCommitMiner INPUTS
project=MATH
version=3.0
localRepoLocationLinux=/home/user/commons-math/
```

### Sample configuration(src/config.properties) for Mac(untested):
```
    #RiskyCommitMiner INPUTS
project=MATH
version=3.0
localRepoLocationMac=/home/user/commons-math/
```