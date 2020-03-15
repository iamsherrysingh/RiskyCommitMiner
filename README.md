# Risky Commit Miner
This is a tool I am developing as part of my school project. It interfaces with Apache.org issue tracker(JIRA), git source code repository and finds a list of JAVA classes that have changed as part of a bug fix. The classes obtained can be used to prioritize test cases.

### Instructions:
1. Clone git repository of the project you want to mine to you local machine.
```$xslt
git clone <project git URL>
```
2. Clone this project in a Java IDE(Eclipse, IntelliJ, etc.)
3. Add location of the local repo(from Step 1) to the src/config.properties file (just for your Operating System, ignore the other two OS properties)
4. Configure 'project' and 'version' properties in src/config.properties to pass inputs to the program.
5. Run com.sherry.App.App.main(String[] args)
6. Find Issue IDs and Classes that changed in the 'output' directory  


### Sample configuration(src/config.properties) for Windows:
```
    #RiskyCommitMiner INPUTS
project=MATH
version=3.0
localRepoLocationWindows=C:/commons-math/
issueIDs.fileOutput=output/issueIDs.txt
classes.fileOutput=output/classes.txt
```

### Sample configuration(src/config.properties) for Linux:
```
    #RiskyCommitMiner INPUTS
project=MATH
version=3.0
localRepoLocationLinux=/home/user/commons-math/
issueIDs.fileOutput=output/issueIDs.txt
classes.fileOutput=output/classes.txt
```

### Sample configuration(src/config.properties) for Mac(untested):
```
    #RiskyCommitMiner INPUTS
project=MATH
version=3.0
localRepoLocationMac=/home/user/commons-math/
issueIDs.fileOutput=output/issueIDs.txt
classes.fileOutput=output/classes.txt
```
