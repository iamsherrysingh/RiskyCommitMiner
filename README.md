# RiskyCommitMiner
This is a tool I am developing as part of my school project. It interfaces with Apache.org(or another website. Depends how you configure) JIRA repositories, git source code and finds a list of JAVA classes that have changed as part of a bug fix. Those classes can be used to prioritize test cases.

Instructions:
=============
1. Please congifure the config.properties to pass inputs to the program.
2. Clone git repository to you local machine, and enter the path in config.properties


Sample configuration(config.properties) for Windows:
----------------------------------------------------
```
    #RiskyCommitMiner INPUTS
project=MATH
version=3.0
localRepoLocation=C:/commons-math/
```

Sample configuration(config.properties) for Linux(Mac may work):
----------------------------------------------------------------
```
    #RiskyCommitMiner INPUTS
project=MATH
version=3.0
localRepoLocation=/home/user/commons-math/
```
