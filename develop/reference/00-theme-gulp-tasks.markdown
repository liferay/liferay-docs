# Theme Gulp Tasks [](id=theme-gulp-tasks)

Your theme's `gulpfile.js` file has several tasks you can execute in your
theme project's root folder. 

Here are the gulp tasks you can execute:

*  `build`: generates the base theme files, compiles Sass into CSS, and
    zips all theme files into a `.war` file that you can deploy to a Liferay
    server. 

*  `deploy`: runs the `build` task and deploys the `.war` file to the
    configured local app server. 

    If you want to deploy to a live remote server, specify the `--live` flag
    along with the `deploy` task (e.g., `gulp deploy --live`).

    Note that the
    [server-manager-web](https://github.com/liferay/liferay-plugins/tree/master/webs/server-manager-web)
    plugin must deployed on the server you specify. The `--live` flag will
    deploy to the remote server specified in the `init` task--the `init` task is
    explained later in this section.

    If you want to deploy to a different server without changing the default
    server specified in `init`, you may use the `--url` flag.

        gulp deploy --live --url http://some-host.com

    You may also specify your login credentials using flags `-u` or `--username`
    and flags `-p` or `--password`.

        gulp deploy --live -u test@liferay.com -p test

*  `extend`: allows you to specify a base theme to extend. By default, themes
    created with the [theme 
    generator](https://github.com/liferay/generator-liferay-theme) are based off
    of the [styled theme](https://www.npmjs.com/package/liferay-theme-styled).

    You first are prompted if you want to extend a Base theme or Themelet, then
    you're be prompted for where you would like to search for modules. Selecting
    *Globally installed npm modules* will search globally accessible npm modules
    on your computer. Selecting *npm registry* will search for published modules
    on npm.

    After you've selected modules from the options it provides the modules are
    added to your `package.json` file as dependencies. Run `npm install` to
    install them.

*  `init`: prompts you for local and remote app server information to use in
    theme deployment.

*  `status`: displays the name of the base theme/themelets your theme extends. 

*  `watch`: allows you to see the changes you make to your theme without
    requiring a full redeploy. After invoking the `watch` task, every time you
    save any changes to a file in your theme, applicable changes are compiled
    and they're copied directly to your app server. **Note:** In order for the
    `watch` task to work, you must have [Developer
    Mode](/develop/tutorials/-/knowledge_base/6-2/using-developer-mode-with-themes)
    enabled. <!--Update link to 7.0 URL once it's updated-->