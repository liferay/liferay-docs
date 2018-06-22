# Migrating Traditional Plugins to Workspace Web Applications [](id=migrating-traditional-plugins-to-workspace-web-applications)

After you've
[adapted your traditional plugin](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-1/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
to @product@'s API, you can continue maintaining it in the Plugins SDK. The
Plugins SDK, however, is deprecated as of 7.0. Maintaining plugins in
the SDK will become increasingly difficult.
[Liferay Workspace replaces the Plugins SDK](/develop/tutorials/-/knowledge_base/7-1/improved-developer-tooling-liferay-workspace-maven-plugins-and-more#from-the-plugins-sdk-to-liferay-workspace), 
providing a comprehensive Gradle development environment and more. A simple
command migrates traditional plugins (such as portlets) to Gradle-based web
application projects. From there you can build and deploy them to @product@ as
Web ARchives (WARs). 

## Running the Migration Command [](id=running-the-migration-command)

[Blade CLI's](/develop/tutorials/-/knowledge_base/7-1/blade-cli)
`convert` command migrates Plugins SDK plugins to web application projects in
Workspace's `wars` folder. Plugin files are re-organized to follow the standard
web application folder structure. 

**Standard Web Application Anatomy:**

- `[project root]`
    - `src`
        - `main`
            - `webapp`
                - `WEB-INF`
                    - `classes`
                    - `lib` &rarr; Libraries
                    - descriptor files
            - `css` &rarr; CSS files
            - `js` &rarr; JavaScript files
            - `icons`
            - JSP files
        - `java` &rarr; Java source
    - build files

From the command line, navigate to the Liferay Workspace root folder. Then pass
your Plugins SDK project's name to Blade's `convert` command:

    blade convert [PLUGIN_PROJECT_NAME]

Blade extracts the plugin from the Plugins SDK and reorganizes it in a standard
web application project in Workspace's `wars` folder. 

**Note:** Executing `blade convert -l` lists Plugins SDK projects that can be
migrated to Workspace web apps. Run `blade convert --all` to migrate all the
plugin projects.

The image below shows the plugin files before and after they're migrated to
Workspace web apps. 

![Figure 1: The `convert` command migrates a Plugins SDK project to a Workspace web application project. It moves Java source files to `src/main/java` and all other files/folders to `src/main/webapp`.](../../../images/migrate-war-compare-folder-structure.png)

The following table maps traditional plugin source files to the standard web
application folder structure Workspace uses. 

**Plugins SDK folders to web application folders:**

  Files       | Plugins SDK folder (old) | Web app folder (new)      
:------------ | :----------------------- | :------------------------ 
  Java        | `docroot/WEB-INF/src`    | `src/main/java`           
  JSPs        | `docroot`                | `src/main/webapp`         
  icons       | `docroot`                | `src/main/webapp`        
  CSS         | `docroot/css`            | `src/main/webapp/css`    
  JS          | `docroot/js`             | `src/main/webapp/js`     
  descriptors | `docroot/WEB-INF`        | `src/main/webapp/WEB-INF`
  libraries   | `docroot/WEB-INF/lib`    | `src/main/webapp/lib`    

From your plugin's new location, you can invoke 
[Workspace Gradle tasks](/develop/tutorials/-/knowledge_base/7-1/improved-developer-tooling-liferay-workspace-maven-plugins-and-more#plugins-sdk-to-workspace-task-map)
on it and build its `.war` file.

  blade gw war 

To deploy the `.war`, copy it from the plugin's `build/libs` folder to the
`[LIFERAY_HOME]/deploy` folder. 

Welcome to your plugin's new home in Workspace! 

## Related Topics [](id=related-topics)

[Workspace Development lifecycle](/develop/tutorials/-/knowledge_base/7-1/development-lifecycle-for-a-liferay-workspace#building-modules)

[Workspace Gradle Tasks](/develop/tutorials/-/knowledge_base/7-1/improved-developer-tooling-liferay-workspace-maven-plugins-and-more#plugins-sdk-to-workspace-task-map)
