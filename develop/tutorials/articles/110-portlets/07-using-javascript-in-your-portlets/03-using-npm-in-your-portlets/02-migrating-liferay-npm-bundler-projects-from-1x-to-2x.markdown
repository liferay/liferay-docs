# Migrating a liferay-npm-bundler Project from 1.x to 2.x [](id=migrating-a-liferay-npm-bundler-project-from-1-x-to-2-x)

We recommend that you use the latest 2.x version of the liferay-npm-bundler. It 
[offers more stability and includes more features out-of-the-box](/develop/reference/-/knowledge_base/7-1/changes-between-liferay-npm-bundler-1x-and-2x). 
If you already created a project using the 1.x version, not to worry. You can 
follow these steps to migrate your project to 2.x:

1.  Update the `liferay-npm-bundler` dependency in your `package.json` to 
    version 2.x:

        {
          "devDependencies": {
            ...
            "liferay-npm-bundler": "^2.0.0",
            ...
          },
          ...
        }

2.  Remove all `liferay-npm-bundler-preset-*` dependencies from your 
    `package.json`. liferay-npm-bundler 2.x includes these by default.
    
3.  Remove any bundler presets you configured in your `.npmbundlerrc` file. 
    liferay-npm-bundler 2.x includes one smart preset that handles all 
    frameworks automagically.
    
These are the standard requirements that all projects have in common. The 
remaining steps depend on your project's framework. Follow the instructions in 
the corresponding section to finish migrating your project. 

## Migrating a Plain JS, Billboard JS, JQuery, Metal JS, React, or Vue JS Project [](id=migrating-a-plain-js-billboard-js-jquery-metal-js-react-or-vue-js-project)

After following the steps covered in the beginning, follow these remaining steps 
to migrate the framework projects shown below to 2.x:

- plain JS project
- Billboard.js project
- JQuery project
- Metal.js project
- React project
- Vue.js project

While Babel is required to transpile your source files, you must remove any 
Babel preset used for transformations from your project that bundler 1.x 
imposed. liferay-npm-bundler 2.x handles these transformations by default:

1.  Remove the *liferay-project* preset from your project's `.babelrc` file. All 
    that should remain is the `es2015` preset shown below:

        {
          "presets": ["es2015"]
        }
        
    If your project uses React, make sure the `react` preset remains as well:
    
        {
          "presets": ["es2015", "react"]
        }
        
2.  Remove the `babel-preset-liferay-project` dependency from your 
    `package.json`.
    
If you're migrating an Angular project, follow the steps in the next section. 

## Migrating an Angular Project [](id=migrating-an-angular-project)

After following the steps covered in the beginning, follow these remaining steps 
to migrate your Angular project to 2.x. While liferay-npm-bundler 1.x relied on 
Babel to perform some transformation steps, these transformations are now 
automagically applied in version 2.x. Therefore, you should remove Babel from 
your project:

1.  Open your `tsconfig.json` file and replace the `"module": "amd"` compiler 
    option with the configuration shown below to produce CommonJS modules:
    
        {
          "compilerOptions": {
            ...
            "module": "commonjs",
            ...
          }
        }
        
2.  Delete the `.babelrc` file to remove the Babel configuration.

3.  Remove Babel from your `package.json` build process so it matches the 
    configuration below:
    
        {
          "scripts": {
            "build": "tsc && liferay-npm-bundler"
          },
          ...
        }
        
4.  Remove the following Babel dependencies from your `package.json` 
    *devDependencies*:
    
        "babel-cli": "6.26.0",
        "babel-preset-liferay-amd": "1.2.2"

## Related Topics [](id=related-topics)

[Formatting Your npm Modules for AMD](/develop/tutorials/-/knowledge_base/7-1/formatting-your-npm-modules-for-amd)

[Using the NPMResolver API in Your Portlets](/develop/tutorials/-/knowledge_base/7-1/using-the-npmresolver-api-in-your-portlets)

[What Changed between liferay-npm-bundler 1.x and 2.x](/develop/reference/-/knowledge_base/7-1/changes-between-liferay-npm-bundler-1x-and-2x)
