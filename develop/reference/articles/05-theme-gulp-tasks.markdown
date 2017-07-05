# Theme Gulp Tasks [](id=theme-gulp-tasks)

Theme projects created using the [Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)
have a `gulpfile.js` file that provides several [gulp](https://www.npmjs.com/package/gulp)
tasks you can execute to manage and deploy your theme.

Here are the gulp tasks you can execute:

*  `build`: generates the base theme files, compiles Sass into CSS, and
    zips all theme files into a WAR file that you can deploy to a Liferay
    server. 

*  `deploy`: runs the `build` task and deploys the WAR file to the
    configured local app server.

    +$$$

    **Note:** If you're running the [Felix Gogo shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell), 
    you can also deploy your theme using the `gulp deploy:gogo` command. **This 
    task will NOT work for 6.2 themes.**

    $$$

*  `extend`: allows you to specify a base theme to extend. By default, themes
    created with the [Liferay Theme Generator](https://github.com/liferay/generator-liferay-theme)
    are based off of the [styled theme](https://www.npmjs.com/package/liferay-theme-styled).

    You first are prompted if you want to extend a Base theme or Themelet, then
    you're be prompted for where you would like to search for modules. Selecting
    *Globally installed npm modules* searches globally accessible npm modules
    on your computer. Selecting *npm registry* searches for published modules
    on npm.

    After you've selected modules from the options it provides, the modules are
    added to your `package.json` file as dependencies. Run `npm install` to
    install them.

*  `kickstart`: allows you to copy the css, images, js, and templates from 
    another theme into the `src` directory of your own. While this is similar to 
    the `extend` task, kickstarting from another theme is a one time inheritance, 
    whereas extending from another theme is a dynamic inheritance that applies 
    your src files on top of the base theme on every build.

*  `init`: prompts you for local and remote app server information to use in
    theme deployment.

*  `status`: displays the name of the base theme/themelets your theme extends. 

*  `watch`: allows you to see the changes you make to your theme without
    requiring a full redeploy. After invoking the `watch` task, every time you
    save any changes to a file in your theme, applicable changes are compiled
    and they're copied directly to your app server. **Note:** In order for the
    `watch` task to work, you must have [Developer
    Mode](/develop/tutorials/-/knowledge_base/7-0/using-developer-mode-with-themes)
    enabled.

**Related Topics**

[Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)
