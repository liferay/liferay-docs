---
header-id: creating-custom-loaders-for-the-liferay-npm-bundler
---

# Creating Custom Loaders for the liferay-npm-bundler

[TOC levels=1-4]

Since webpack creates JavaScript bundles and the liferay-npm-bundler targets AMD
loader, webpack's loaders aren't compatible with the liferay-npm-bundler. So, if
you want to use a loader that isn't 
[available by default](/docs/7-2/reference/-/knowledge_base/r/default-liferay-npm-bundler-loaders),
you must create a custom loader. 

A loader, in terms of the liferay-npm-bundler, is defined as an npm package that 
has a main module which exports a default function with this signature:

```javascript
function(context, options){
}
```

The arguments are defined as follows:

`context`: an object containing these fields: 

> `content`: a string with the contents of the processed file (the main input 
> of the loader)

> `filepath`: the project-relative path to the file to process with the loader

> `extraArtifacts`: an object with project-relative paths as keys and strings 
> as values of properties that may be used to output extra files along with 
> the one being processed (for example, you can use it to generate source 
> maps).

> `log`: a logger that writes execution information to the bundler's report 
> file (see the [`PluginLogger` class](https://github.com/liferay/liferay-js-toolkit/blob/master/packages/liferay-npm-build-tools-common/src/plugin-logger.js) 
> for information on its structure and API).

`options`: an object taken from the `options` field of the loader's 
configuration (See [Understanding liferay-npm-bundler's loaders and rules](/docs/7-2/reference/-/knowledge_base/r/understanding-liferay-npm-bundlers-loaders) 
for more information).

| **Note:** the function may return nothing or modified content. If something is 
| returned, it is copied on top of the `context.content` field and used to feed 
| the next loader or write the output file. This is the equivalent to 
| `context.content = 'something'`. If your loader does not return a file, but 
| instead it only filters files to prevent them from being generated, you 
| must explicitly set `context.content = 'undefined'`. 

Follow these steps to write a new loader. These steps use the Babel loader as an 
example:

1.  If your loader requires configuration, like Babel, you may define a rule 
    configuration like the one shown below so you can specify options for the 
    loader:

    ```json
    {
      "rules": [
        {
          "test": "\\.js$",
          "exclude": "node_modules",
          "use": [
            {
              "loader": "babel-loader",
              "options": {
                "presets": ["env", "react"]
              }
            }
          ]
        }
      ]
    }
    ``` 

2.  Create an `index.js` file and write a function that takes the input content, 
    passes it through the loader, and writes the result and the source map file 
    to the output folder. The loader function below takes the passed content 
    (JS files), run it through babel, and writes the result and source map to 
    the default `/build/` output folder:

    ```javascript
    export default function(context, options) {
      // Get input parameters
      const { content, filePath, log, sourceMap } = context;

      // Run babel on content
      const result = babel.transform(content, options);

      // Create an extra .map file with source map next to source .js file
      context.extraArtifacts[`${filePath}.map`] = JSON.stringify(result.map);

      // Tell the user what we have done
      log.info("babel-loader", "Transpiled file");

      // Return the modified content
      return result.code;
    }
    ```

3.  Place the `index.js` file in an npm package and publish it.

4.  Include the npm package you just created as a `devDependency` in the project's 
    `package.json`:

    ```json
    "devDependencies": {
      "liferay-npm-bundler": "2.12.0",
      "liferay-npm-build-support": "2.12.0",
      "liferay-npm-bundler-loader-babel-loader": "2.12.0",
      ...
    }
    ```

5.  Configure the loader's name in the `rules` section of the project's 
    `.npmbundlerrc` file:
    
    ```json
    {
      "sources": ["src"],
      ...
      "rules": [
        {
          "test": "\\.js$",
          "exclude": "node_modules",
          "use": [
            {
              "loader": "babel-loader",
              "options": {
                "presets": ["env", "react"]
              }
            }
          ]
        }
      ],
      ...
    }
    ```

## Related Topics

- [Default liferay-npm-bundler Loaders](/docs/7-2/reference/-/knowledge_base/r/default-liferay-npm-bundler-loaders)
- [Understanding liferay-npm-bundler's Loaders](/docs/7-2/reference/-/knowledge_base/r/understanding-liferay-npm-bundlers-loaders)
