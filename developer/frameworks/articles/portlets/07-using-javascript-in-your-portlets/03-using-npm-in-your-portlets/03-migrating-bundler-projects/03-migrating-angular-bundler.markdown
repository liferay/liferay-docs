---
header-id: migrating-an-angular-project-to-use-bundler-2-x
---

# Migrating an Angular Project to Use Bundler 2.x

[TOC levels=1-4]

After following the steps covered in the intro to this section, follow these 
remaining steps to migrate your Angular project to 2.x. While liferay-npm-bundler 1.x relied on 
Babel to perform some transformation steps, these transformations are now 
automatically applied in version 2.x. Therefore, you should remove Babel from 
your project:

1.  Open your `tsconfig.json` file and replace the `"module": "amd"` compiler 
    option with the configuration shown below to produce CommonJS modules:

    ```json
    {
      "compilerOptions": {
        ...
        "module": "commonjs",
        ...
      }
    }
    ```

2.  Delete the `.babelrc` file to remove the Babel configuration.

3.  Remove Babel from your `package.json` build process so it matches the 
    configuration below:

    ```json    
    {
      "scripts": {
        "build": "tsc && liferay-npm-bundler"
      },
      ...
    }
    ```

4.  Remove the following Babel dependencies from your `package.json` 
    *devDependencies*:

    ```json
    "babel-cli": "6.26.0",
    "babel-preset-liferay-amd": "1.2.2"
    ```

Great! Your project is migrated to use the new version of the 
liferay-npm-bundler. 

## Related Topics
        
- [Formatting Your npm Modules for AMD](/docs/7-2/frameworks/-/knowledge_base/f/formatting-your-npm-modules-for-amd)
- [Using the NPMResolver API in Your Portlets](/docs/7-2/frameworks/-/knowledge_base/f/using-the-npmresolver-api-in-your-portlets)
- [What Changed between liferay-npm-bundler 1.x and 2.x](/docs/7-2/reference/-/knowledge_base/r/what-changed-between-liferay-npm-bundler-1-x-and-2-x)
