---
header-id: theme-gulp-tasks
---

# Theme Gulp Tasks

[TOC levels=1-4]

Theme projects that use the [liferay JS Theme Toolkit](https://github.com/liferay/liferay-themes-sdk/tree/master/packages), 
such as those created with the [Liferay Theme Generator](/docs/7-0/tutorials/-/knowledge_base/t/themes-generator) 
have access to several [gulp](https://www.npmjs.com/package/gulp) tasks that you 
can execute to manage and deploy your theme. 

| **Note:** Gulp is included as a local dependency in generated themes, so you
| are not required to install it. It can be accessed by running
| `node_modules\.bin\gulp` followed by the Gulp task from a generated theme's
| root folder.

Here are the gulp tasks you can execute:

*  `build`: generates the base theme files, compiles Sass into CSS, and
    zips all theme files into a WAR file that you can deploy to a Liferay
    server. 

*  `deploy`: runs the `build` task and deploys the WAR file to the
    configured local app server.

    | **Note:** If you're running the [Felix Gogo shell](/docs/7-0/reference/-/knowledge_base/r/using-the-felix-gogo-shell),
    | you can also deploy your theme using the `gulp deploy:gogo` command. **This
    | task will NOT work for 6.2 themes.**

*  `extend`: allows you to specify a base theme or themelet to extend. By 
    default, themes created with the [Liferay Theme Generator](https://github.com/liferay/generator-liferay-theme)
    are based off of the [styled theme](https://www.npmjs.com/package/liferay-theme-styled).

    You first are prompted if you want to extend a Base theme or Themelet, then
    you're prompted for where you would like to search for modules. Selecting
    *Globally installed npm modules* searches globally accessible npm modules
    on your computer. Selecting *npm registry* searches for published modules
    on npm. If you have v8.x.x of the [Liferay JS Theme Toolkit](https://github.com/liferay/liferay-themes-sdk/tree/master/packages) 
    installed, you can also *Specify a package URL* to locate a themelet.

    | **Note:** You can retrieve the URL for a package by running 
    | `npm show package-name dist.tarball`. 

    After you've selected modules from the options it provides, the modules are
    added to your `package.json` file as dependencies. Run `npm install` to
    install them.

    | **Note:** The Classic theme is an implementation of an existing base theme 
    | and is therefore not meant to be extended. Extending Liferay's Classic 
    | theme is strongly discouraged.

*  `kickstart`: allows you to copy the CSS, images, JS, and templates from 
    another theme into the `src` directory of your own. While this is similar to 
    the `extend` task, kickstarting from another theme is a one time inheritance, 
    whereas extending from another theme is a dynamic inheritance that applies 
    your src files on top of the base theme on every build.

*  `init`: prompts you for local and remote app server information to use in
    theme deployment.

*  `status`: displays the name of the base theme/themelets your theme extends. 

*  `watch`: allows you to preview the changes you make to your theme without
    requiring a full redeploy. After invoking the `watch` task, every time you
    save any changes to a file in your theme, applicable changes are compiled
    and they're copied directly to a proxy port (e.g. `9080`) for you to preview 
    live. **Note:** you must have [Developer Mode](/docs/7-0/tutorials/-/knowledge_base/t/using-developer-mode-with-themes) enabled to use the `watch` task. Once you're happy with the live 
    preview, deploy your theme to apply the changes.

**Related Topics**

[Liferay Theme Generator](/docs/7-0/tutorials/-/knowledge_base/t/themes-generator)
