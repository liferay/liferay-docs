# Themes Generator [](id=themes-generator)

The Liferay Themes Generator is an easy-to-use command-line wizard that
streamlines the theme creation process. It is independent of the Liferay Plugins
SDK, and works for Liferay versions 6.2 and up. This tutorial focuses on using
the Themes Generator to create themes. In just a few steps, you'll have a
working Liferay theme.

+$$$

**Note:** The [Liferay Themes Generator](https://github.com/liferay/generator-liferay-theme)
is still in development and is not guaranteed to work on all platforms and
environments. 

$$$

This tutorial demonstrates how to:

- Install the Themes Generator

- Generate a theme

The first step is to install the Themes Generator.

## Installing the Theme Generator [](id=installing-the-theme-generator)

The Themes Generator has several dependencies. Follow these steps to install
them:

1.  Install [Node.JS](http://nodejs.org/), if it's not already installed.

    To test your Node.JS installation, execute the following command:

        node -v

    The resulting output should look similar to this:

        v4.2.2

    Note: To avoid any potential compatibility issues, we recommend installing
    the Long Term Support (LTS) version of Node.js--at the time of this writing,
    the LTS version is v4.2.2.

    Node Package Manager (NPM) is installed along with Node.js. 

2.  Setup your npm environment. 

    First, create an `.npmrc` file in your user's home directory. This helps
    you bypass npm permission-related issues. 

    In the `.npmrc` file, specify a `prefix` property like this one:

        prefix=/Users/[username]/.npm-packages

    Set the `prefix` value based on your user's home directory. The location you
    specify is where global npm packages are to be installed.

    Next, set Node.js `NPM_PACKAGES` and `NODE_PATH` system environment 
    variables with values like these based on the `prefix` value you just 
    specified.

        NPM_PACKAGES=/Users/[username]/.npm-packages (same as prefix value)
        NODE_PATH=${NODE_PATH}:${NPM_PACKAGES}/lib/node_modules

    As a last npm configuration, since npm installs Yeoman and gulp executables
    to `${NPM_PACKAGES}/bin` on UNIX and to `%NPM_PACKAGES%` on Windows, make
    sure to add the appropriate directory to your system path. For example, on
    UNIX you'd set this:

        PATH=${PATH}:${NPM_PACKAGES}/bin

3.  Install [Yeoman](http://yeoman.io/) and [gulp](https://www.npmjs.com/package/gulp)
    globally by executing the following command:

        npm install -g yo gulp

You've completed installing the Themes Generator's dependencies.

Now you're ready to install the Themes Generator. Install it by executing this
command:

    npm install -g generator-liferay-theme

If you are on Windows, you must do additional setup for generated themes to use
Sass. 

### Installing Sass on Windows [](id=installing-sass-on-windows)

On Windows, you must use either Sass from node-sass or Sass from Ruby. By
default, the generator creates theme projects to use node-sass; but you can
reconfigure them to use Ruby based Sass and Compass. Since node-sass indirectly
requires Visual Studio, developers who are not already using Visual Studio may
opt to use Ruby based Sass and Compass instead of node-sass. This section
explains both Sass installations. 

#### Installing Sass from node-sass [](id=installing-sass-from-node-sass)

By default, the generator uses Sass from node-sass. node-sass requires node-gyp,
which in turn requires Python and Visual Studio. The 
[node-gyp installation instructions](https://github.com/nodejs/node-gyp#installation)
explain how to set up node-gyp, Python, and Visual Studio. Since Visual Studio 
is a particularly large dependency, if you aren't already using Visual Studio 
you might want to consider using Ruby Sass instead of node-sass. 

#### Installing Ruby Sass and Compass [](id=installing-ruby-sass-and-compass)

As an alternative to using Sass from node-sass, you can use Sass from Ruby.
Liferay themes require using Compass along with Ruby based Sass. In order to
install and use Sass and Compass, you must install Ruby via the [Ruby installer](http://rubyinstaller.org/).
The following command installs the Sass and Compass gems.

    gem install sass compass

After creating a theme project in the next section, you'll learn how to
configure a project to use Ruby based Sass and Compass.

Now that you've installed the Themes Generator and theme dependencies, you can
generate a theme.

## Running the Themes Generator [](id=running-the-themes-generator)

When you installed the Liferay Theme Generator, you also installed two
sub-generators with it: a themelet creator, and a theme importer. For the
purposes of this tutorial, the focus will be on the default Themes Generator. 
To learn how to use the generator to create themelets and import themes, you 
can read the [Themelets](/develop/tutorials/-/knowledge_base/7-0/themelets)
tutorial. <!-- and the Importing and Upgrading Themes tutorials. -->

From a directory in which you want to create a 7.0 theme, run the following
command:

    yo liferay-theme

+$$$

Note: If you run into permissions issues during theme generation, make sure you
have read/write access to all folders on your system. 

$$$

The Themes Generator prompts you for the following things:

1. Enter a name for your theme.

2. Enter a theme ID, or press *enter* to accept the default. 

3. Select the version of your Liferay instance (e.g., *7.0*).

4. Choose a template language.

    Based on the inputs up to this point, the default generator starts
    installing additional required software and creating your theme project. 

5.  When prompted, enter your app server's path. The information you
    provide is added to the `liferay-theme.json` in your theme's root directory.
    you can change the path in that file if you change app servers.

6.  Finally, enter your Liferay instance's URL, or press *enter* to accept the default
    `localhost:8080`.

The generator creates a new theme project in your current directory. The theme
inherits styles from the [liferay-theme-styled](https://www.npmjs.com/package/liferay-theme-styled)
theme. Note that you can switch to using a different base theme by executing the
[`gulp extend`](/develop/reference/-/knowledge_base/7-0/theme-gulp-tasks)
command. 

**Important**: By default, your theme is based off of the styled theme and uses
lib-sass/bourbon, instead of Compass. If, however, you are on Windows and are
using the Ruby version of Sass, you must configure the theme to support Compass.
To do so, follow these steps:

1.  Open the `package.json` file found in the root folder of your theme, and
    locate the `rubySass` property and change it from `false` to `true`.

    Now that your theme is set to support Compass, you must install the Ruby
    Sass middleware and save it as a dependency for your theme.
    
2.  Run the following command to install the Ruby Sass middleware:

        npm i --save gulp-ruby-sass

    The `--save` flag adds Ruby Sass to the list of dependencies in your theme's
    `package.json` file. Your theme is ready to use.

3. Run the `gulp build` task to generate the base files for your theme. Open the
`build` directory of your theme to view the base files.

There you have it! You now have a working theme. At the moment, the theme is a
bit bare bones, but you have everything you need to develop it.

The generated theme's structure differs slightly from a theme created in the
Liferay Plugins SDK. In a theme generated in the Liferay Plugins SDK, you put
changes in a `_diffs` directory. In a theme generated by the Liferay Theme
Generator, you put changes in the `src` directory.

To develop your theme, copy the build files into your `src` directory. For
instance to make a change to the `portal_normal.ftl` theme template, you would
create a `templates` folder in your `src` directory, and copy the 
`portal_normal.ftl` file from the `build/templates` directory into the 
`src/templates` directory. This gives you the base template to build on. It is
important that you mirror the directory structure in order for the changes to be
applied.

Another noticeable difference in the generator created theme is that all CSS
files have been converted to Sass SCSS files. Sassy CSS (SCSS) is the new main
syntax which allows you to use the latest CSS3 styles and leverage Sass syntax
advantages, such as nesting and variables.

To deploy your theme to your configured Liferay instance, execute this command:

    gulp deploy

+$$$

**Note:** By default theme images are cached by the browser. If you need to 
update images in the theme, it is best practice to use versioning in the image 
URL. For example, `background-image:url("../images/image.jpg?v=1")` . You can
then just update the version each time you update the image, which will remove
the potential for any caching issues.

$$$
    
Now that you've created a theme and deployed it, you can use the theme project's
gulp tasks to further develop and manage your theme. Refer to the [Theme Gulp Tasks](/develop/reference/-/knowledge_base/7-0/theme-gulp-tasks)
reference document to learn the Gulp tasks available to you.

There you have it! You're ready to design a terrific theme!

## Related Topics [](id=related-topics)

[Theme Gulp Tasks](/develop/reference/-/knowledge_base/7-0/theme-gulp-tasks)

[Themelets](/develop/tutorials/-/knowledge_base/7-0/themelets)

[Importing Resources with a Theme](develop/tutorials/-/knowledge_base/7-0/importing-resources-with-a-theme)
