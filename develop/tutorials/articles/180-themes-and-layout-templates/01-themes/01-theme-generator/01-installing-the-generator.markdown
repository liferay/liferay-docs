# Installing the Theme Generator [](id=installing-the-theme-generator)

The Liferay Theme Generator has several dependencies. Installation varies 
slightly between Windows and Unix-based systems. This tutorial covers how to 
install the Liferay Theme Generator and its dependencies on both Windows and 
Unix-based systems. 

+$$$

**Note:** The Liferay Theme Generator does not currently support 7.1 theme 
creation.

$$$

Start by installing the Theme Generator and its dependencies. 

## Installing the Generator and Dependencies [](id=installing-the-generator-and-dependencies)

Follow these steps to install the Liferay Theme Generator:

1.  Install [Node.js](http://nodejs.org/), if it's not already installed. To 
    test your Node.js installation, execute the following command:

        node -v

    The resulting output should look similar to this:

        v8.9.4

    +$$$

    Note: To avoid any potential compatibility issues, we recommend installing
    the Long Term Support (LTS) version of Node.js--at the time of this writing,
    the LTS version is v8.9.4.
    
    $$$

2.  Set up your Node Package Manager (npm) environment (npm was installed along 
    with Node.js). You'll use npm to install the generator's dependencies.

    a.  Create an `.npmrc` file in your user's home directory. This helps bypass 
        npm permission-related issues. 

    b.  In the `.npmrc` file, add a `prefix` property, based on your user's 
        home directory, that specifies where to install global npm packages:

                prefix=/Users/[username]/.npm-packages

    c.  set the `NPM_PACKAGES` system environment variable to the `prefix` 
        value you just specified:

                NPM_PACKAGES=/Users/[username]/.npm-packages (same as prefix value)

    d.  As a last npm configuration, since npm installs Yeoman and gulp 
        executables to `${NPM_PACKAGES}/bin` on UNIX and to `%NPM_PACKAGES%` on 
        Windows, make sure to add the appropriate directory to your system path. 
        For example, on UNIX you'd set this:

                PATH=${PATH}:${NPM_PACKAGES}/bin

3.  Install 
    [Yeoman](http://yeoman.io/) 
    and 
    [gulp](https://www.npmjs.com/package/gulp) 
    globally:

        npm install -g yo gulp

4.  With its dependencies installed, execute the following command to install 
    the Liferay Theme Generator globally:

        npm install -g generator-liferay-theme

The Liferay Theme Generator uses Sass, which requires some additional setup on 
Windows. If you're not on Windows, you can jump to 
[running the Liferay Theme Generator](#running-the-themes-generator).

## Installing Sass on Windows [](id=installing-sass-on-windows)

To use Sass on Windows, you must use either Sass from node-sass or Sass from
Ruby. By default, the generator creates theme projects to use node-sass; but you
can reconfigure them to use Ruby based Sass and Compass. Since node-sass
indirectly requires Visual Studio, developers who are not already using Visual
Studio may opt to use Ruby based Sass and Compass instead of node-sass. 

This section explains both Sass installations. 

### Installing Sass from node-sass [](id=installing-sass-from-node-sass)

By default, the generator uses Sass from node-sass. node-sass requires node-gyp,
which in turn requires Python and Visual Studio. The 
[node-gyp installation instructions](https://github.com/nodejs/node-gyp#installation)
explain how to set up node-gyp, Python, and Visual Studio. Since Visual Studio 
is a particularly large dependency, if you aren't already using Visual Studio 
you might want to consider using Ruby Sass instead of node-sass. 

### Installing Ruby Sass and Compass [](id=installing-ruby-sass-and-compass)

As an alternative to using Sass from node-sass, you can use Sass from Ruby. 
Follow these steps to set it up:

1.  Install Ruby via the 
    [Ruby installer](http://rubyinstaller.org/).
    
2.  @product@ themes require Compass to be used with Ruby based Sass. Install 
    the Compass and Sass Ruby gems. Note that Sass version 3.5 has compatibility 
    issues with Compass. We recommend that you install Sass version 3.4.0, as 
    shown in the command below:
    
        gem install sass -v "=3.4.0"

        gem install compass

Now that you've installed the Liferay Theme Generator and theme dependencies, 
you can generate a theme.

## Related Topics [](id=related-topics)

[Using Developer Mode with Themes](/develop/tutorials/-/knowledge_base/7-1/using-developer-mode-with-themes)

[Themelets](/develop/tutorials/-/knowledge_base/7-1/themelets)

[Creating Themes with the Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-1/creating-themes-with-the-liferay-theme-generator)
