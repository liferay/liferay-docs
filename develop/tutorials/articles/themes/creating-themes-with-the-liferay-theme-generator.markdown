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
Studio to be installed. You can read more at the following links:

[node-gyp Installation](https://github.com/nodejs/node-gyp#installation) 

[Visual Studio Setup](https://github.com/nodejs/node-gyp/wiki/Visual-Studio-2010-Setup)

<!-- The *Visual Studio Setup* link is for setting up VS 2010. The instructions
for installing node-gyp appear to require either VS 2013 or VS 2015. Should we
update the VS setup instruction to be compatible with what is required for
node-gyp? -Cody -->

Alternatively you can use the Ruby based version of Sass. In order to use that 
version of Sass, you'll need to install Ruby with the Ruby Installer, and 
install the Sass and Compass gems from the command line using the following 
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

2. Still inside the terminal, run the following command to install 
   the generator:

        npm install -g generator-liferay-theme

    Once the generator finishes installing, you can install the dependencies 
    next.

3. Run `npm install -g yo gulp` to install the [Yeoman](http://yeoman.io/) 
   and [gulp](https://www.npmjs.com/package/gulp) global dependencies.

Now that the generator and dependencies are installed, you can learn how to use
the generator next.

## Running the Theme Generator

When you installed the Liferay Theme Generator, you also installed two
sub-generators with it: a themelet creator, and a theme importer. For the
purposes of this tutorial, the focus will be on the default theme generator. To
learn how to use the generator to create themelets and import themes, you can
read the Extending your Theme Using Themelets tutorial and the Importing and 
Upgrading Themes tutorials.

Follow the steps below to create a 7.0 theme using the default theme generator:

1. Open your terminal window and navigate to the directory you want to create 
   the theme in.

2. Run the following command to start the default theme generator:

        yo liferay-theme

3. Enter a name and themeId for your theme, choose *7.0* for the version, choose
   a template language, and enter *No* for Compass support.

    Note that you don't need the Compass support because the theme is based off
    of the styled theme. The base styled theme uses lib-sass/bourbon instead.
    Therefore, make sure you select *No* when asked if you would like Compass
    support.

<!-- In the note at the beginning of this tutorial (for Windows usage), the
following was stated:

    "When the generator asks you if you need Compass support, type "Y". This
    will bypass node-gyp completely, and use the Ruby versions of Sass and
    Compass."

Step 3 says that we should say "No" because we're basing our theme off the
styled theme. What if we want to use Compass support? 

For the record, indicating both "Y" and "N" worked for me (on Windows). I
configured both ways (node-gyp and Compass) -Cody -->

<!-- I also had to enter the path to my app server directory and deploy
directory. Maybe this should be added as a step 4? -Cody -->

The generator will create a new theme in your current directory that inherits 
styles from [liferay-theme-styled](https://www.npmjs.com/package/liferay-theme-styled) 
theme. Note that the base theme you wish to use can be changed after the theme
has been created using the `gulp extend` command.

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
access to multiple gulp tasks.

Here's a list of gulp tasks available for the theme generator:

<!-- It may be useful to mention that users should navigate to their new theme
before running the gulp tasks below. This seems obvious, but I remember feedback
from the old Maven tutorials I wrote, and the #1 problem I noticed on the forums
was that developers were not navigating to their newly created project before
running the highlighted commands. :-) -Cody -->

**build:** generates the base theme files, compiles sass into css, and zips all 
theme files into a `.war` file, ready to be deployed to a Liferay server. Run
using `gulp build` in your theme's root directory.

**Deploy:** initally runs the `build` task, and once the `.war` file has been
created, it deploys to the specified app server. Invoke this using `gulp deploy`
in your theme's root directory.

**Extend:** allows you to specify what base theme to extend from. By default,
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

**Status:** Displays what base theme/themelets your theme is extending. Invoked
with `gulp status` in your theme's root directory.

**Watch:** allows you to see the changes you make to your theme without a 
full redeploy. Invoked with `gulp watch` in your theme's root directory.

After invoking the `watch` task, every time you save any changes to a file in
your theme, it compiles (if applicable) and copies it directly to your app
server.

There you have it! Now go make some great designs.

## Related Topics

[Extending Your Theme with Themelets](/develop/tutorials/-/knowledge_base/7-0/extending-your-theme-with-themelets)
 
[Importing and Upgrading Themes with the Theme Generator](/develop/tutorials/-/knowledge_base/7-0/importing-and-upgrading-themes-with-the-generator)
