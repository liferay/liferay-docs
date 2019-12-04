---
header-id: migrating-your-project-to-use-the-new-mode
---

# Migrating Your Project to Use liferay-npm-bundler's New Mode

[TOC levels=1-4]

In the previous version of the liferay-npm-bundler, before the bundler ran, the 
build did some preprocessing, then the bundler modified the output from the 
preprocessed files, as shown in the example build script below:

```json
{
  "scripts":{
    "build": "babel --source-maps -d build src && liferay-npm-bundler"
  }
}
```

In the new mode, the liferay-npm-bundler runs the whole process, like webpack,
and is configured via a set of rules. The build script is condensed, as shown
below:

```json
{
  "scripts":{
    "build": "liferay-npm-bundler"
  }
}
```

Follow these steps to migrate your project to use the new configuration mode:

1.  Open the project's `package.json` file and update the `build` script to 
    use only the liferay-npm-bundler:

    ```json
    {
      "scripts":{
        "build": "liferay-npm-bundler"
      }
    }
    ```

2.  Define the rules for the bundler to use (e.g. running babel to transpile 
    files) in the project's `.npmbundlerrc` file. The example configuration 
    below defines rules for using the `babel-loader` to transpile JavaScript 
    files. See the [Default Loaders reference](/docs/7-2/reference/-/knowledge_base/r/default-liferay-npm-bundler-loaders) 
    for the full list of default loaders. Follow the steps in 
    [Creating Custom Loaders for the Bundler](/docs/7-2/frameworks/-/knowledge_base/f/creating-custom-loaders-for-the-liferay-npm-bundler) 
    to create a custom loader. The liferay-npm-bundler processes the `*.js` 
    files in  `/src/` with babel and writes the results in the default `/build/` 
    folder:

    ```json
    {
      "sources": ["src"],
      "rules": [
        {
          "test": "\\.js$",
          "exclude": "node_modules",
          "use": [
            {
              "loader": "babel-loader",
              "options": {
                "presets": ["env"]
              }
            }
          ]
        }
      ]
    }
    ```

    |**Note:** The new mode of the liferay-npm-bundler acts very much 
    | like webpack, but because webpack creates a single JS bundle file and 
    | liferay-npm-bundler targets AMD loader, they are not compatible.

## Related Topics

- [Default liferay-npm-bundler Loaders](/docs/7-2/reference/-/knowledge_base/r/default-liferay-npm-bundler-loaders)
- [Understanding liferay-npm-bundler's Loaders](/docs/7-2/reference/-/knowledge_base/r/understanding-liferay-npm-bundlers-loaders)
