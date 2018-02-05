# Extending and Overwriting Liferay Theme Tasks [](id=extending-and-overwriting-liferay-theme-tasks)

Themes created with the Liferay Theme Generator have access to several default
gulp theme tasks that provide the standard features required to develop and
build your theme (build, deploy, watch, etc.). You may, however, want to run
additional processes--such as minifying your JavaScript files--on your theme's
files prior to deploying the theme to the server. The Liferay Theme Generator's
APIs expose a `hookFn` property that lets you hook into the default gulp theme
tasks to inject your own logic. 

Follow these steps to hook into the default Liferay theme tasks: 

1.  Identify the gulp task or sub task that you want to hook into or overwrite. 
		The tasks and their sub tasks are listed in their `[task-name].js` file in 
		the 
		[`tasks` folder](https://github.com/liferay/liferay-theme-tasks/tree/master/tasks)
		of the 
		[`liferay-theme-tasks`](https://github.com/liferay/liferay-theme-tasks)
		repo. For example, the gulp `build` task and sub tasks are defined in the 
		[`build.js` file](https://github.com/liferay/liferay-theme-tasks/blob/master/tasks/build.js#L38-L53): 

				'build:clean',
				'build:base',
				'build:src',
				'build:web-inf',
				'build:liferay-look-and-feel',
				'build:hook',
				'build:themelets',
				'build:rename-css-dir',
				'build:prep-css',
				'build:compile-css',
				'build:fix-url-functions',
				'build:move-compiled-css',
				'build:remove-old-css-dir',
				'build:fix-at-directives',
				'build:r2',
				'build:war',

2.  Open your theme's `gulpfile.js` file and locate the 
		`liferayThemeTasks.registerTasks()` method. This method registers the 
		default gulp theme tasks. Add the `hookFn` property to the `registerTasks()` 
		method's configuration object, making sure to pass in the `gulp` instance:

				liferayThemeTasks.registerTasks({
					gulp: gulp,
					hookFn: function(gulp) {
						
					}
				});
		
3.  Inside the `hookFn()` function, use the `gulp.hook()` method to specify the
    theme task or sub task that you want to hook into. You can inject your code
    before or after a task by prefixing it with the `before:` or `after:`
    keywords. Alternatively, you can use the `gulp.task()` method to overwrite
    a gulp task. Both methods have two parameters: the task or sub task you want
    to hook into and a callback function that invokes `done` or returns a stream
    with the logic that you want to inject. A few example configuration patterns
    are shown below:
		
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

The example below fires before the `build:war` sub-task and reads the JavaScript 
files in the theme's `build` folder, minifies them with the `gulp-uglify` 
module, places them back in the `./build/js` folder, and invokes `done`:

		'use strict';

		var gulp = require('gulp');
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
					});
			}
		});

+$$$

**Note:** The `hook` callback function must invoke the `done` argument or return 
a stream.

$$$

Now you know how to hook into and overwrite the default Liferay theme tasks!

## Related Topics [](id=related-topics)

[Creating Themes with the Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-1/creating-themes-with-the-liferay-theme-generator)

[Installing the Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-1/installing-the-theme-generator)

[Theme Builder](/develop/tutorials/-/knowledge_base/7-1/theme-builder)
