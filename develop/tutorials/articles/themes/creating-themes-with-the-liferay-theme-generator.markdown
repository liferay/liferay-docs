# Creating Themes with the Liferay Theme Generator

The Liferay Theme Generator is an easy-to-use command-line wizard that
streamlines the theme creation process. It is independent of the plugins SDK, 
and works for Liferay versions 6.2 and up. This tutorial, however, focuses 
on creating themes for Liferay 7.0. In just a few steps, you'll have a
working theme for Liferay.

+$$$

**Note:** The Liferay theme generator is still in development and is not 
guaranteed to work on all platforms/environments.

**Usage on Windows**

Unfortunately, there can be some minor headaches when using the generator on
Windows. The main reason is because, by default, the generator uses node-sass,
which requires node-gyp to run. Furthermore, node-gyp requires Python and Visual
Studio to be installed. You can read more at the following link:

[node-gyp Installation](https://github.com/nodejs/node-gyp#installation)

<!-- The *Visual Studio Setup* link is for setting up VS 2010. The instructions
for installing node-gyp appear to require either VS 2013 or VS 2015. Should we
update the VS setup instruction to be compatible with what is required for
node-gyp? -Cody -->

Alternatively you can use the Ruby based version of Sass. In order to use that 
version of Sass, you'll need to install Ruby with the [Ruby Installer](http://rubyinstaller.org/), 
and install the Sass and Compass gems from the command line using the following 
command: 

    gem install sass compass

When the generator asks if you need Compass support, type *Y*. This bypasses
node-gyp completely, and uses the Ruby versions of Sass and Compass.

$$$

Now that you know a little bit about the generator, follow the steps below to
install it and its dependencies so you can start building themes.

## Installing the Generator and Dependencies

Follow these steps to install the generator and its dependencies:

1. Install [Node.JS](http://nodejs.org/), if you don't have it installed
   already.

    If you're unsure whether you have node.js installed, you can run the
    following command in your terminal or console window to check:

        node -v

    You should get an output similar to the following one:

        v4.2.2

    Note: It is recommended that you install the LTS(Long Term Support) version
    of Node.js to avoid any potential compatibility issues. At the time of this 
    writing, the LTS version is v4.2.2.

    Once you have Node.js installed you can move on to installing the generator.
    As part of your Node.js installation, you installed npm as well. NPM is a
    package manager that you will use to install the generator and its
    dependencies.
    
    +$$$

    **Note:** If you run into permissions issues with npm, you may need to
    configure a `.npmrc` file in your user's root directory.
    
    To configure npm, first create the `.npmrc` file in the user root directory, 
    and add the following line to it:
    
        prefix=/Users/[username]/.npm-packages
        
    This sets where globally installed npm packages will be installed.
    
    Next, add the corresponding node environment variables to your system:
    
        NPM_PACKAGES=/Users/[username]/.npm-packages/(same as prefix value)
        PATH=${PATH}:${NPM_PACKAGES}bin
        NODE_PATH=${NODE_PATH}:${NPM_PACKAGES}lib/node_modules
        
    You should no longer run into node related permissions issues now.
    
    $$$

2. Run `npm install -g yo gulp` to install the [Yeoman](http://yeoman.io/) 
   and [gulp](https://www.npmjs.com/package/gulp) global dependencies. 
   
    Once the dependencies finish installing, you can install the generator 
    next.

3. Still inside the terminal, run the following command to install 
   the generator:
   
        npm install -g generator-liferay-theme

Now that the generator and dependencies are installed, you can learn how to use
the generator next.

## Running the Theme Generator
<!--
When you installed the Liferay Theme Generator, you also installed two
sub-generators with it: a themelet creator, and a theme importer. For the
purposes of this tutorial, the focus will be on the default theme generator. 
To learn how to use the generator to create themelets and import themes, you 
can read the Extending your Theme Using Themelets tutorial and the Importing and 
Upgrading Themes tutorials.

Need to add back once the tutorials are live-->

Follow the steps below to create a 7.0 theme using the default theme generator:

1. Open your terminal window and navigate to the directory you want to create 
   the theme in.

2. Run the following command to start the default theme generator:

        yo liferay-theme
        
    If you run into permissions issues, make sure you have read/write access to
    all folders on your system.  

3. Enter a name and themeId for your theme, choose *7.0* for the version, and 
   choose a template language.
   
4. Enter *No* to decline Compass support, or *Yes* if you installed the Ruby 
   based version of Sass on Windows.

    Note that your theme is based off of the styled theme by default, and uses 
    lib-sass/bourbon instead of Compass. If, however, you installed the **Ruby
    version of Sass** at the beginning of this tutorial, your theme **will 
    require Compass support**.
    
5. Enter the path to your app server's directory. The information you
   provide is added to the `liferay-theme.json` in your theme's root directory.
   you can change the path in that file if your app server changes.
   
6. Finally, enter your app server's URL, or press *enter* to accept the default
   `localhost:8080`.

The generator creates a new theme in your current directory that inherits 
styles from [liferay-theme-styled](https://www.npmjs.com/package/liferay-theme-styled) 
theme. Note that the base theme you wish to use can be changed after the theme
has been created, using the `gulp extend` command.

There you have it! You now have a working theme. At the moment the theme is a 
bit bare bones, but you have everything you need to get started. The generated 
theme's structure differs a bit from a traditional SDK developed theme. You 
can read more about the differences next.

### Theme Anatomy

The theme anatomy is a bit different for themes that have been generated with 
the theme generator. In a traditional SDK developed theme, your changes are 
placed in a `_diffs` directory. Your changes, however, are placed in the `src` 
directory for generated themes. The other noticeable change is that all CSS 
files have been converted to Sass SCSS files. SCSS is the new main syntax known 
as Sassy CSS, which allows you to use the latest CSS3 styles and Sass syntax 
advantages, such as nesting and variables.

Now that your theme is created, and you understand your theme's anatomy more, 
you can take advantage of the gulp tasks that are available to you, to extend
your theme.

## Gulp Theme Tasks

When your theme was generated, a `gulpfile.js` file was included that gives you
access to multiple gulp tasks. To run the gulp tasks covered below, navigate to 
your theme's root directory.

Here's a list of gulp tasks available for the theme generator:

**build:** generates the base theme files, compiles sass into css, and zips all 
theme files into a `.war` file, ready to be deployed to a Liferay server. 
Invoked with `gulp build` in your theme's root directory.

**deploy:** initally runs the `build` task, and once the `.war` file has been
created, it deploys to the specified local app server. Invoked with 
`gulp deploy` in your theme's root directory.

If you want to deploy to a live site, use the `--live` flag to deploy to a
remote server. Invoke this using `gulp deploy --live` in your theme's root 
directory.

Note that the specified server must have the [server-manager-web](https://github.com/liferay/liferay-plugins/tree/master/webs/server-manager-web) 
plugin deployed. The `--live` flag will deploy to the remote server specified in 
the `init` task.

If you want to deploy to a different server without changing the default server 
specified in `init`, you may use the `--url` flag.

`gulp deploy --live --url http://some-host.com`

You may also specify your login credentials using the `-u`/`--username` and 
`-p`/`--password` flags.

`gulp deploy --live -u test@liferay.com -p test`

**extend:** allows you to specify what base theme to extend from. By default,
themes created with the [theme generator](https://github.com/liferay/generator-liferay-theme) 
are based off the [styled theme](https://www.npmjs.com/package/liferay-theme-styled).

You first are prompted if you want to extend a Base theme or Themelet, then
you're be prompted for where you would like to search for modules. `Globally
installed npm modules` will search npm modules that have been installed on your
computer with the `-g` flag. Selecting `npm registry` will search for published
modules on npm.

Once it gives you the options and you make your selection, it will add any
selected modules to your `package.json` under dependencies and run `npm
install`.

Invoked with `gulp extend` in your theme's root directory.

**init:** prompts user for local and remote appserver information used for
deployment purposes.

Invoked with `gulp init` in your theme's root directory.

**status:** displays what base theme/themelets your theme is extending. Invoked
with `gulp status` in your theme's root directory.

**watch:** allows you to see the changes you make to your theme without a 
full redeploy. Invoked with `gulp watch` in your theme's root directory.

After invoking the `watch` task, every time you save any changes to a file in
your theme, it compiles (if applicable) and copies it directly to your app
server.

+$$$

**Note:** In order for the `watch` task to work, you must have [Developer Mode](/develop/tutorials/-/knowledge_base/6-2/using-developer-mode-with-themes) 
enabled.
<!--Update link to 7.0 URL once it's updated-->
$$$

There you have it! Now go make some great designs.

<!--Readd links once tutorials are submitted
## Related Topics

[Extending Your Theme with Themelets](/develop/tutorials/-/knowledge_base/7-0/extending-your-theme-with-themelets)
 
[Importing and Upgrading Themes with the Theme Generator](/develop/tutorials/-/knowledge_base/7-0/importing-and-upgrading-themes-with-the-generator)
-->
