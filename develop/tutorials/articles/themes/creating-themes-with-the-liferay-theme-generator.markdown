# Creating Themes with the Liferay Theme Generator [](id=creating-themes-with-the-liferay-theme-generator)

The Liferay Theme Generator is an easy-to-use command-line wizard that
streamlines the theme creation process. It is independent of the Liferay Plugins SDK, 
and works for Liferay versions 6.2 and up. This tutorial focuses 
on creating themes for Liferay 7.0. In just a few steps, you'll have a
working Liferay theme. The first step is to install the theme generator. 

+$$$

**Note:** The Liferay Theme Generator is still in development and is not 
guaranteed to work on all platforms and environments.

$$$

## Installing the Theme Generator

The theme generator has several dependencies. You'll learn how to install these
dependencies and then install the theme generator. 

+$$$

**Prerequisites on Windows**

By default, the generator uses node-sass which requires node-gyp. Furthermore, node-gyp requires Python and Visual
Studio. The [node-gyp Installation](https://github.com/nodejs/node-gyp#installation) instructions
explain how you can set this up.

Alternatively you can use the Ruby based version of Sass. In order to use that 
version of Sass, you must install Ruby with the [Ruby Installer](http://rubyinstaller.org/), 
and then install the Sass and Compass gems using the following 
command: 

    gem install sass compass
    
These are the initial steps you will need to run for Windows. Once the theme is
generated you will have to make a few more changes to the configuration files,
which is covered below.

$$$

Follow these steps to install the generator's dependencies:

1. Install [Node.JS](http://nodejs.org/), if you don't have it installed
   already.

    To test your Node.JS installation, execute the following command:

        node -v

    The resulting output should look similar to this:

        v4.2.2

    Note: To avoid any potential compatibility issues, we recommend installing the Long Term Support (LTS) version
    of Node.js . At the time of this 
    writing, the LTS version is v4.2.2.

    Node Package Manager (NPM) is installed along with Node.js. 

2. Setup your npm environment. 

    First, create an `.npmrc` file in your user's home directory. This helps you
    bypass npm permission-related issues. 

    In the `.npmrc` file, specify a `prefix` property like this one:

        prefix=/Users/[username]/.npm-packages

    Set the `prefix` value based on your user's home directory. Globally
    installed npm packages will be installed to the `prefix` location. 

    Next, set node system environment variables like these:

        NPM_PACKAGES=/Users/[username]/.npm-packages (same as prefix value)
        NODE_PATH=${NODE_PATH}:${NPM_PACKAGES}/lib/node_modules

    As a last step, since npm installs Yeoman and gulp executables to
    `${NPM_PACKAGES}/bin` on UNIX and to `%NPM_PACKAGES%` on Windows, make sure
    to add the appropriate directory to your system path. For example, on UNIX
    you'd set this:

        PATH=${PATH}:${NPM_PACKAGES}/bin

3. Install [Yeoman](http://yeoman.io/) 
   and [gulp](https://www.npmjs.com/package/gulp) global using the
   following command:

        npm install -g yo gulp

4. Install the generator:

        npm install -g generator-liferay-theme

Now that the theme generator and its dependencies are installed, you can
generate a theme. 

## Running the Theme Generator [](id=running-the-theme-generator)

<!--
When you installed the Liferay Theme Generator, you also installed two
sub-generators with it: a themelet creator, and a theme importer. For the
purposes of this tutorial, the focus will be on the default theme generator. 
To learn how to use the generator to create themelets and import themes, you 
can read the Extending your Theme Using Themelets tutorial and the Importing and 
Upgrading Themes tutorials.

Need to add back once the tutorials are live-->

Follow the steps below to create a 7.0 theme using the default theme generator:

1. Open a terminal window to the directory you want to create 
   the theme in.

2. Run the following command to start the default theme generator:

        yo liferay-theme
        
    If you run into permissions issues, make sure you have read/write access to
    all folders on your system.  

3. Enter a name for your theme.

4. Enter a theme ID, or press *enter* to accept the default. 

5. Select the version of your Liferay instance (e.g., *7.0*).

6. Choose a template language.

    Based on the inputs up to this point, the default generator starts
    installing additional required software and creating your theme project. 

4. When prompted, enter your app server's path. The information you
   provide is added to the `liferay-theme.json` in your theme's root directory.
   you can change the path in that file if you change app servers.
   
5. Finally, enter your app server's URL, or press *enter* to accept the default
   `localhost:8080`.

The generator creates a new theme project in your current directory. The theme inherits 
styles from the [liferay-theme-styled](https://www.npmjs.com/package/liferay-theme-styled) 
theme. Note that you can change the base theme
using the `gulp extend` command.

+$$$

**Note**: By default, your theme is based off of the styled theme and uses 
lib-sass/bourbon, instead of Compass. If, however, you are on Windows and 
are using the Ruby version of Sass, you must configure the theme to support
Compass. 

1. Open the `package.json` file found in the root folder of your theme, and
   locate the `supportCompass` property and change it from `false` to `true`.
   
    Now that your theme is set to support Compass, you will need to install the
    Ruby Sass middleware and save it as a dependency for your theme.
    
2. Run the following command to install the Ruby Sass middleware:

        npm i --save gulp-ruby-sass
        
    The `--save` flag adds Ruby Sass to the list of dependencies in your theme's
    `package.json` file. Your theme is ready to use.
 
$$$

There you have it! You now have a working theme. At the moment, the theme is a 
bit bare bones, but you have everything you need to get started. The generated 
theme's structure differs slightly from a theme created in the Liferay Plugins SDK. 

In a theme generated in the Liferay Plugins SDK, you put 
changes in a `_diffs` directory. In a theme generated by the Liferay Theme Generator, you put changes in
the `src` directory.

Another noticeable difference in the generator created theme is that 
all CSS files have been converted to Sass SCSS files. Sassy CSS (SCSS) is the new main 
syntax which allows you to use the latest CSS3 styles and leverage
Sass syntax advantages, such as nesting and variables.

Now that your theme is created, and you understand your theme's anatomy, 
you can take use the theme project's gulp tasks to extend and deploy
your theme.

## Gulp Theme Tasks [](id=gulp-theme-tasks)

Your theme's `gulpfile.js` file has several tasks you can execute in your
theme project's root folder. 

Here are the gulp commands you can execute:

`gulp build`: generates the base theme files, compiles Sass into CSS, and zips all 
theme files into a `.war` file that you can deploy to a Liferay server. 

`gulp deploy`: runs the `build` task and
deploys the `.war` file to the configured local app server. 

If you want to deploy to a live remote server, specify the `--live` flag along
with the `deploy` task (e.g., `gulp deploy --live`).

Note that the [server-manager-web](https://github.com/liferay/liferay-plugins/tree/master/webs/server-manager-web) 
plugin must deployed on the server you specify. The `--live` flag will deploy to the remote server specified in 
the `init` task--the `init` task is explained later in this section.

If you want to deploy to a different server without changing the default server 
specified in `init`, you may use the `--url` flag.

    gulp deploy --live --url http://some-host.com

You may also specify your login credentials using flags `-u` or `--username` and 
flags `-p` or `--password`.

    gulp deploy --live -u test@liferay.com -p test

`gupl extend`: allows you to specify a base theme to extend. By default,
themes created with the [theme generator](https://github.com/liferay/generator-liferay-theme) 
are based off of the [styled theme](https://www.npmjs.com/package/liferay-theme-styled).

You first are prompted if you want to extend a Base theme or Themelet, then
you're be prompted for where you would like to search for modules. Selecting *Globally
installed npm modules* will search globally accessible npm modules on your
computer. Selecting *npm registry* will search for published
modules on npm.

After you've selected modules from the options it provides
the modules are added to your `package.json` file as dependencies. Run `npm
install` to install them.

`gulp init`: prompts you for local and remote app server information to use in
theme deployment.

`gulp status`: displays the name of the base theme/themelets your theme extends. 

`gulp watch`: allows you to see the changes you make to your theme without requiring a 
full redeploy. 

After invoking the `watch` task, every time you save any changes to a file in
your theme, applicable changes are compiled and they're copied directly to your app
server.

+$$$

**Note:** In order for the `watch` task to work, you must have [Developer Mode](/develop/tutorials/-/knowledge_base/6-2/using-developer-mode-with-themes) 
enabled.
<!--Update link to 7.0 URL once it's updated-->

$$$

There you have it! You're ready to design terrific themes!

## Related Topics [](id=related-topics)

[Importing Resources with Your Themes](/develop/tutorials/-/knowledge_base/7-0/importing-resources-with-your-themes)
