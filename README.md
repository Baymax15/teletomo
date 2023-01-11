# Readme

helpful when checking out projects without polluting .m2 folder
| Environment variable | Value | Description|
| --- | --- | --- |
| MAVEN_OPTS | -Dmaven.repo.local=repository | To set the folder "repository" in project root as the maven local repository in current shell |



1.  Perform an maven install on project root 
    ```
    mvnw.cmd clean install
    ```

2.  Executing the application

    - To run without packaging (spring-boot:run)
        ```
        mvnw.cmd spring-boot:run -pl <modulename>
        ```
    - To package the application for jar file
        ```
        mvnw.cmd clean package spring-boot:repackage -pl <modulename>
        ```

> :warning: Make sure to perform the install command if there's a change in build files