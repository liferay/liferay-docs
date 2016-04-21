# Importing a 6.2 Theme

Now that you have the lunar resort theme resources downloaded and themes 
generator installed, you can update the theme to use the new node build tools. 
To help automate this process, the themes generator has an import sub-generator. 

Follow the steps below to import the lunar resort theme.

1.  Create a new directory called *Theme*.

2.  Navigate to the *Theme* directory you just created and run the following 
    command from the command line:

        yo liferay-theme:import
        
    This starts the import sub-generator process.

3.  Enter a directory similar to the following to import the lunar resort theme:

        /Users/mike/Desktop/resources/lunar-resort-theme-migration-6.2/lunar-resort-ce-theme

+$$$

    **Note:** The themes generator does not support relative paths.
$$$

    The importer creates some configuration files and migrates the modified
    theme files over to a new `src` directory. Similar to the `_diffs` directory 
    in Plugins SDK developed themes, the `src` directory is where all of your
    theme modifications go.
        
4.  Enter the path to your app server directory. It should be similar to the one 
    below:

        /Users/mike/opt/bundles/tomcat-8.0.32
        
5.  Press *Enter* to accept the default *http://localhost:8080* for the
    development site URL.
    
Your settings are added to a `liferay-theme.json` file in the theme's root
directory:

    {
      "LiferayTheme": {
        "appServerPath": "/Users/mike/opt/bundles/tomcat-8.0.32",
        "deployPath": "/Users/mike/opt/bundles/deploy",
        "url": "http://localhost:8080",
        "appServerPathTheme": "/Users/mike/opt/Liferay/bundles/tomcat-8.0.30/webapps/lunar-resort-ce-theme",
        "deployed": false,
        "themeName": "lunar-resort-ce-theme"
      }
    }
    
To change these settings at any time, run `gulp init` from the theme's root 
directory.

Notice also that a `package.json` was added to the theme. This defines some
basic info for the theme:

    {
            "name": "lunar-resort-ce-theme",
            "version": "0.0.0",
            "keywords": [
                    "liferay-theme"
            ],
            "liferayTheme": {
                    "baseTheme": "styled",
                    "screenshot": "",
                    "rubySass": true,
                    "templateLanguage": "ftl",
                    "version": "6.2"
            },
            "devDependencies": {
                    "gulp": "^3.8.10",
                    "liferay-theme-tasks": "*",
                    "liferay-theme-deps-6.2": "*"
            },
            "publishConfig": {
                    "tag": "6_2_x"
            }
    }
    
As you may have noticed, the `version` is still `6.2`. The import process 
imports a plugins SDk theme to use the node build tools. You'll upgrade the
theme to 7.0 in a moment.
    
Your updated theme directory structure should reflect the one below after
import:

- `lunar-resort-theme/`
    - `gulpfile.js`
    - `liferay-theme.json`
    - `node_modules/`
        - (many directories)
    - `package.json`
    - `src/`
        - `css/`
            - `_aui_variables.scss`
            - `custom.css`
        - `images/`
            -   (many directories)
        - `js/`
            - `main.js`
        - `templates/`
            - `init_custom.ftl`
            - `navigation.ftl`
            - `portal_normal.ftl`
        - `WEB-INF/`
                - `lib/`
            - `liferay-look-and-feel.xml`
            - `liferay-plugin-package.properties`
            - `src/`
                - `resources-importer/`
                    - (Many directories)

The lunar resort theme is setup to use the node tools, but it's still a 6.2
theme at the moment. As part of the import process, a gulp file was added that
gives you access to several gulp theme tasks, one of which is `upgrade.` You'll
use the `gulp upgrade` task to help with the upgrade process.

Follow these steps to upgrade the lunar resort theme to Liferay 7.

1.  Navigate to the `lunar-resort-ce-theme` in your `theme` directory if you are
    not already there and run the following command:

        gulp upgrade

    The existing lunar resort theme is placed in a `backup` folder. This can be
    used to restore your theme back to its original state. Running 
    `gulp upgrade:revert` will restore the lunar resort theme back to its 
    original state, after the import. All the core code needed to generate the 
    proper base files for Liferay 7 themes has also been created.
    
    If you open `package.json` in the theme's root directory, you'll note that
    the `version` is now `7.0`.

2.  Press Enter for each file rename to update them to the `.scss` Sass file 
    extension. All sass files now have the `.scss` extension and all sass 
    partials have an underscore at the start of their name, i.e. `_custom.scss`.
    
    Once the renames are complete, the upgrade task upgrades the themes 
    Bootstrap from 2 to 3, taking care of some of the migration for you, and 
    prints suggestions for further updates.
    
Your directory structure should reflect the one below after you run the 
`gulp upgrade` task:

- `lunar-resort-theme/`
    - `gulpfile.js`
    - `liferay-theme.json`
    - `node_modules/`
        - (many directories)
    - `package.json`
    - `src/`
        - `css/`
            - `_aui_variables.scss`
            - `_custom.scss`
        - `images/`
            -   (many directories)
        - `js/`
            - `main.js`
        - `templates/`
            - `init_custom.ftl`
            - `navigation.ftl`
            - `portal_normal.ftl`
        - `WEB-INF/`
                - `lib/`
            - `liferay-look-and-feel.xml`
            - `liferay-plugin-package.properties`
            - `src/`
                - `resources-importer/`
                    - (Many directories)

In the next section you'll continue the upgrade process with the lunar resort
theme's CSS.
