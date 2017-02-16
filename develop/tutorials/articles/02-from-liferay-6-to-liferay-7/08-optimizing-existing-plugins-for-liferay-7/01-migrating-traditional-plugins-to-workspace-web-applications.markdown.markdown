# Migrating Traditional Plugins to Workspace Web Applications

After you've [adapted your traditional plugin](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
to @product@'s API, you can continue maintaining it in the Plugins SDK. The
Plugins SDK, however, is deprecated as of @product-ver@. Maintaining plugins in 
the SDK will become increasingly difficult. [Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace) 
[replaces the SDK](/develop/tutorials/-/knowledge_base/7-0/improved-developer-tooling-liferay-workspace-maven-plugins-and-more#from-the-plugins-sdk-to-liferay-workspace),
providing a comprehensive Gradle infrastructure and more. In addition to
developing modules in Workspace, you can develop plugins (such as portlets) as
web applications. 

Plugins SDK portlets are web applications. They can be migrated to Workspace as
standard web applications. In Workspace, you can build and deploy them to
@product-ver@ as a Web ARchives (WARs). 

## Running the Migration Command

[Blade CLI's](/develop/tutorials/-/knowledge_base/7-0/blade-cli) `migrateWar`
command moves Plugins SDK plugins into Workspace's `wars` folder. The plugin
files are re-organized to follow the standard web application folder structure. 

**Standard Web Application Anatomy:**

- `<project root>`
    - `src`
        - `main`
            - `webapp`
                - `WEB-INF`
                    - `classes`
                    - `lib` &rarr; Libraries
                    - Descriptor files
            - `css` &rarr; CSS files
            - `js` &rarr; JavaScript files
            - `icons`
            - JSP files
        - `java` &rarr; Java source
    - Build files

In a terminal, navigate to the Liferay Workspace root folder. Then, pass your
Plugins SDK project's name to Blade's `migrateWar` command:

    blade migrateWar <pluginProjectName>

Blade extracts the plugin from the Plugins SDK and reorganizes it in a standard
web application project in Workspace's `wars` folder. 

The image below shows the plugin files before and after they're migrated to
Workspace. 

![Figure x: The migration command moves Java source files to `src/main/java` and all other files/folders to `src/main/webapp`.](../../../images/migrate-war-compare-folder-structure.png)

The following table maps traditional plugin source file types to standard web
application folder structure Workspace uses. 

**Plugins SDK to web application folder map:**

  Files       | Plugins SDK folder (old) | Web app folder (new)      
:------------ | :----------------------- | :------------------------ 
  Java        | `docroot/WEB-INF/src`    | `src/main/java`           
  JSPs        | `docroot`                | `src/main/webapp`         
  icons       | `docroot`                | `src/main/webapp`        
  CSS         | `docroot/css`            | `src/main/webapp/css`    
  JS          | `docroot/js`             | `src/main/webapp/js`     
  descriptors | `docroot/WEB-INF`        | `src/main/webapp/WEB-INF`
  libraries   | `docroot/WEB-INF/lib`    | `src/main/webapp/lib`    

From your plugin's new location, you can [deploy](/develop/tutorials/-/knowledge_base/7-0/development-lifecycle-for-a-liferay-workspace#building-modules)
it to @product-ver@ and maintain it using
[Workspace Gradle tasks](/develop/tutorials/-/knowledge_base/7-0/improved-developer-tooling-liferay-workspace-maven-plugins-and-more#plugins-sdk-to-workspace-task-map).
Welcome to your plugin's new home in Workspace! 

## Related Topics

[Workspace Development lifecycle](/develop/tutorials/-/knowledge_base/7-0/development-lifecycle-for-a-liferay-workspace#building-modules)

[Workspace Gradle Tasks](/develop/tutorials/-/knowledge_base/7-0/improved-developer-tooling-liferay-workspace-maven-plugins-and-more#plugins-sdk-to-workspace-task-map)
