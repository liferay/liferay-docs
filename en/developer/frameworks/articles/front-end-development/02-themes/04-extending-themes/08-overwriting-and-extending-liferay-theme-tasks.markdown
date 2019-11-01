---
header-id: overwriting-and-extending-liferay-theme-tasks
---

# Overwriting and Extending Liferay Theme Tasks

[TOC levels=1-4]

Themes created with the Liferay Theme Generator have access to several default 
gulp theme tasks that provide the standard features required to develop and 
build your theme (build, deploy, watch, etc.). You may, however, want to run 
additional processes on your theme's files prior to deploying the theme to the 
server---such as minifying your JavaScript files. The Liferay Theme Generator's 
APIs expose a `hookFn` property that lets you hook into the default gulp theme 
tasks to inject your own logic. 

Follow these steps to hook into the default Liferay theme tasks: 

1.  Identify the gulp task or sub task that you want to hook into or overwrite. 
    The tasks and their sub tasks are listed in their `[task-name].js` file in 
    the 
    [`tasks` folder](https://github.com/liferay/liferay-js-themes-toolkit/tree/master/packages/liferay-theme-tasks/tasks) 
    of the 
    [`liferay-theme-tasks`](https://github.com/liferay/liferay-js-themes-toolkit/tree/master/packages/liferay-theme-tasks) 
    package. For example, the gulp `build` task and sub tasks are defined in the 
    [`build.js` file](https://github.com/liferay/liferay-js-themes-toolkit/blob/master/packages/liferay-theme-tasks/tasks/build.js#L73-L92): 

    ```javascript
    gulp.task('build', function(cb) {
    	runSequence(
    		'build:clean',
    		'build:base',
    		'build:src',
    		'build:web-inf',
    		'build:liferay-look-and-feel',
    		'build:hook',
    		'build:themelets',
    		'build:rename-css-dir',
    		'build:compile-css',
    		'build:fix-url-functions',
    		'build:move-compiled-css',
    		'build:remove-old-css-dir',
    		'build:fix-at-directives',
    		'build:r2',
    		'build:war',
    		cb
    	);
    });
    ```

2.  Open your theme's `gulpfile.js` file and locate the 
    `liferayThemeTasks.registerTasks()` method. This method registers the 
    default gulp theme tasks. Add the `hookFn` property to the `registerTasks()` 
    method's configuration object, making sure to pass in the `gulp` instance:

    ```javascript
    liferayThemeTasks.registerTasks({
      gulp: gulp,
      hookFn: function(gulp) {
        
      }
    });
    ```

3.  Inside the `hookFn()` function, use the `gulp.hook()` method to specify the 
    theme task or sub task that you want to hook into. You can inject your code 
    before or after a task by prefixing it with the `before:` or `after:` 
    keywords. Alternatively, you can use the `gulp.task()` method to overwrite 
    a gulp task. Both methods have two parameters: the task or sub task you want 
    to hook into and a callback function that invokes `done` or returns a stream 
    with the logic that you want to inject. A few example configuration patterns 
    are shown below:

    ```javascript
    liferayThemeTasks.registerTasks({
      gulp: gulp,
      hookFn: function(gulp) {
        gulp.hook('before:build:src', function(done) {
          // Fires before build:src task
        });

        gulp.hook('after:build', function(done) {
          // Fires after build task
        });

        gulp.task('build:base', function(done) {
          // Overwrites build:base task
        });
      }
    });
    ```

The example below fires before the `build:war` sub-task and reads the JavaScript 
files in the theme's `build` folder, minifies them with the `gulp-uglify` 
module, places them back in the `./build/js` folder, invokes `done`, and finally 
logs that the JavaScript was minified. To follow along, replace your theme's 
`gulpfile.js` with the contents shown below, install the 
[gulp-uglify](https://www.npmjs.com/package/gulp-uglify) 
module and the 
[fancy-log](https://www.npmjs.com/package/fancy-log) 
module, and run `gulp deploy`:

```javascript
'use strict';

var gulp = require('gulp');
var log = require('fancy-log');
var uglify = require('gulp-uglify');
var liferayThemeTasks = require('liferay-theme-tasks');

liferayThemeTasks.registerTasks({
   gulp: gulp,
   hookFn: function(gulp) {
     gulp.hook('before:build:war', function(done) {
      // Fires before build `war` task
      gulp.src('./build/js/*.js')
      .pipe(uglify())
      .pipe(gulp.dest('./build/js'))
      .on('end', done);
      log('Your JS is now minified...');
      });
   }
});
```

You should see something similar to the output shown below:

```bash
[15:58:07] Finished 'build:r2' after 198 ms
[15:58:07] Starting 'build:war'...
[15:58:07] Your JS is now minified...
[15:58:07] Starting 'plugin:version'...
[15:58:07] Finished 'plugin:version' after 2.52 ms
```

| **Note:** The `hook` callback function must invoke the `done` argument or 
| return a stream.

Now you know how to hook into and overwrite the default Liferay theme tasks! 

## Related Topics

- [Installing the Theme Generator and Creating a Theme](/docs/7-2/reference/-/knowledge_base/r/installing-the-theme-generator-and-creating-a-theme)
- [Generating Themelets](/docs/7-2/reference/-/knowledge_base/r/creating-themelets-with-the-themes-generator)
- [Using Developer Mode with Themes](/docs/7-2/frameworks/-/knowledge_base/f/using-developer-mode-with-themes)
