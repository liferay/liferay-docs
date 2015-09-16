# Liferay Theme Generator

**Note: the Liferay theme generator is still in development and is not 
guaranteed to work on all platforms/environments.**

> Liferay theme generator allows you to generate new themes to be used with 
Liferay Portal, and supplies you with the necessary tools to deploy and make 
quick modifications to your theme.

## Dependencies

1. Install [Node.JS](http://nodejs.org/), if you don't have it yet.

Note: the Liferay theme generator requires Node version 0.10.x or 0.12.x, due to 
issues with [node-sass](https://www.npmjs.com/package/node-sass) the generator 
will not currently work with Node 0.11.x.

2. run `<sudo> npm install -g yo gulp` to install global dependencies.

## Generator use

1. Install generator: `<sudo> npm install -g generator-liferay-theme`

2. Run `yo liferay-theme` to start theme generator and follow prompts to name 
and configure your theme.

Note: the generator will create a new folder in your current directory that will 
house your theme.

## Generators

Available generators:

* [liferay-theme](#create)
* [liferay-theme:import](#import)
* [liferay-theme:import](#themelet)

### Create

```
yo liferay-theme
```

The default `liferay-theme` generator creates a new theme that inherits styles 
from [liferay-theme-styled](https://www.npmjs.com/package/liferay-theme-styled) 
or [liferay-theme-unstyled](https://www.npmjs.com/package/liferay-theme-unstyled).


### Import

```
yo liferay-theme:import
```

The `liferay-theme:import` generator enables you to import pre-existing Liferay 
themes from the SDK.

### Themelet

```
yo liferay-theme:themelet
```

The `liferay-theme:themelet` generator enables you to create theme fragments 
called `themelets`.

The advantage of themelets is that reused code/components that often exist in 
multiple themes can be abstracted and easily reused in all of your themes.


## Gulp tasks

Once the generator is done creating your theme, there are multiple gulp tasks 
available to expedite theme development. See [liferay-theme-tasks](https://github.com/Robert-Frampton/liferay-theme-tasks) 
for more detail.

# Liferay Theme Tasks

> The liferay-theme-tasks module is inteded for use with the yeoman generator 
for [Liferay themes](https://github.com/natecavanaugh/generator-liferay-theme).

## Available tasks:

* [build](#build)
* [deploy](#deploy)
* [extend](#extend)
* [status](#status)
* [watch](#watch)

### Build

```
gulp build
```
The `build` task generates the base theme files, compiles sass into css, and 
zips all theme files into a .war file, ready to be deployed to a Liferay server.

### Deploy

```
gulp deploy
```

The deploy initally runs the `build` task, and once the .war file has been 
created it deploys to the specified appserver.

### Extend

```
gulp extend
```

The `extend` task is what allows you to specify what base theme you are 
extending from. By default, themes created with the [theme generator](https://github.com/natecavanaugh/generator-liferay-theme) 
will base off the [styled theme](https://github.com/natecavanaugh/liferay-theme-styled).

You first are prompted if you want to extend a Base theme or Themelet, then you 
will be prompted for where you would like to search for modules. 
`Globally installed npm modules` will search npm modules that have been 
installed on your computer with the `-g` flag. Selecting `npm registry` will 
search for published modules on npm.

Once it gives you the options and you make your selection, it will add any 
selected modules to your package.json under dependencies and run `npm install`.

### Status

```
gulp status
```
Displays what base theme/themelets your theme is extending.

### Watch

```
gulp watch
```

The watch task allows you to see the changes you make to your theme without a 
full redeploy.

After invoking the watch task, every time you save any changes to a file in your 
theme it compiles (if applicable) and copies it directly to your appserver.

MIT
