# Configuring liferay-npm-bundler [](id=configuring-liferay-npm-bundler)

The liferay-npm-bundler is configured via a `.npmbundlerrc` file placed in the 
portlet project's root folder. You can create the configuration manually or use 
a configuration preset. Use the configuration presets described here as a guide 
to create your own custom configurations.

This tutorial explains the `.npmbundlerrc` file's structure and shows how to use 
the available presets to configure the liferay-npm-bundler.

## Understanding the .npmbundlerrc File's Structure [](id=understanding-the-npmbundlerrc-file-structure)

The `.npmbundlerrc` file has three possible phase definitions: *pre-process*,
*post-process*, and *babel*. These phase definitions are explained in more 
detail below:

**Pre-Process:** Defined with the `plugins` property. Specifies plugins to run 
before the Babel phase is run.

**Babel:** Defined with the `.babelrc` definition. Specifies the `.babelrc` file 
to use when running Babel through the package's `.js` files. 

+$$$

**Note:** During this phase, Babel transforms package files (for example, to
convert them to AMD format, if necessary) but doesn't transpile them. In theory, 
you could also transpile them by configuring the proper plugins.

$$$

**Post-Process:** Defined with the `post-plugins` property. An alternative to 
using the *pre-process* phase, this specifies plugins to run after the Babel 
phase has completed. 

Here's an example `.npmbundlerrc` configuration:

    {
      "exclude":{
        "*":[
            (list of glob expressions excluding files)
        ],
        "some-package-name":[
            (same as wildcard format (`*`) contents below)
        ],
        "another-package-name@1.0.10":[
            (same as wildcard format (`*`) contents below)
        ]
      },
      "output":(relative path to output directory),
      "process-serially":(true|false),
      "*" : {
        "plugins": [
          <list of plugins>
        ],
        ".babelrc": {
          <a standard .babelrc file>
        },
        "post-plugins": [
          <list of plugins>
        ]
      },
      "some-package-name" : {
        (same as wildcard format (`*`) contents above)
      },
      "some-package-name@1.1.10" : {
        (same as wildcard format (`*`) contents above)
      }
      ...
    }

+$$$

**Note:** Not all definition formats (`*`, `some-package-name`, and 
`some-package-name@version`) shown above are required. In most cases, the 
wildcard definition (`*`) is enough. The non-wildcard formats 
(`some-package-name` and `some-package-name@version`) are rare exceptions for 
packages that require a more specific configuration than the wildcard definition 
provides.

$$$

Below are the options possible in the `.npmbundlerrc` file:

*exclude:* defines files to exclude from bundling from all or specific packages.

*output:* by default the bundler writes packages to the standard Gradle 
resources folder: `build/resources/main/META-INF/resources`. Set this value to 
override the default output folder.

*process-serially:* Process packages in parallel, leveraging Node.js 
asynchronous model, or one by one. The default value is `false`, (parallel), but 
if you get EMFILE errors, you can disable this.

*list of plugins:* a comma separated call list of liferay-npm-bundler plugins to 
call.

*standard .babelrc file:* a `.babelrc` file as defined in 
[Babel's documentation](https://babeljs.io/docs/usage/babelrc/) that 
liferay-npm-bundler passes to Babel when called.

*package name:* The npm package's name. The configuration under its scope is 
only applied to packages with this name and *any* version.

*version:* The npm package's version. The configuration under its 
`some-package-name@version` scope is only applied to packages with the specified 
*package name* and *version*.

Now that you know the structure of the `.npmbundlerrc` file, you can learn 
about the available configuration presets.

## liferay-npm-bundler Configuration Presets [](id=liferay-npm-bundler-configuration-presets)

The liferay-npm-bundler comes with default configuration presets that bundle 
several plugin types. You can use these presets to quickly configure your 
`.npmbundlerrc` file: 

-  [Liferay-npm-bundler-preset-amd](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-preset-amd)

-  [Liferay-npm-bundler-preset-angular](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-preset-angular)

-  [Liferay-npm-bundler-preset-isomorphic](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-preset-isomorphic)

-  [Liferay-npm-bundler-preset-react](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-preset-react)

-  [Liferay-npm-bundler-preset-standard](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-preset-standard)

-  [Liferay-npm-bundler-preset-vue](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-preset-vue)

Below is an example `.npmbundlerrc` file that uses the *standard* configuration 
preset:

    {
        "preset": "liferay-npm-bundler-preset-standard"
    }

+$$$

**Note:** the `liferay-npm-bundler` prefix may be omitted from the npm package 
name in your `.npmbundlerrc` file.

$$$

If you run liferay-npm-bundler with this file, it applies the 
[config file](https://github.com/liferay/liferay-npm-build-tools/blob/master/packages/liferay-npm-bundler-preset-standard/config.json) 
from `liferay-npm-bundler-preset-standard`:

    {
      "*": {
        "plugins": [
          "replace-browser-modules"
        ],
        ".babelrc": {
          "presets": ["liferay-standard"]
        }
      }
    }

The 
[`replace-browser-modules` plugin ](https://github.com/liferay/liferay-npm-build-tools/blob/master/packages/liferay-npm-bundler-plugin-replace-browser-modules/README.md) 
runs during the pre-process phase and replaces the server side files for each 
dependency that has a browser section in its `package.json` with its browser 
version counterpart.

+$$$

**Note:** You can override configuration preset values by adding your own 
configuration to your project's `.npmbundlerrc` file. For instance, using 
configuration preset example above, you can define your own `.babelrc` value in 
`.npmbundlerrc` file to override the defined "liferay-standard" babelrc preset. 

$$$

The 
[`liferay-standard` preset] (https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/babel-preset-liferay-standard) 
applies the following plugins:

- [babel-plugin-normalize-requires](https://github.com/izaera/liferay-npm-build-tools/tree/master/packages/babel-plugin-normalize-requires)

- [babel-plugin-transform-node-env-inline](https://www.npmjs.com/package/babel-plugin-transform-node-env-inline)

- [babel-plugin-wrap-modules-amd](https://github.com/izaera/liferay-npm-build-tools/tree/master/packages/babel-plugin-wrap-modules-amd)

- [babel-plugin-name-amd-modules](https://github.com/izaera/liferay-npm-build-tools/tree/master/packages/babel-plugin-name-amd-modules)

- [babel-plugin-namespace-amd-define](https://github.com/izaera/liferay-npm-build-tools/tree/master/packages/babel-plugin-namespace-amd-define)

Babel uses the plugins above to perform these tasks: 

- Remove trailing JavaScript strings from `require()` calls in the npm packages.

- Replace occurrences of `process.env.NODE_ENV` with their literal values.

- Wrap modules with an `AMD define()` call.

- Give a canonical name to each AMD module based on its package and relative 
  path inside it.

- Prefix `define()` calls with `Liferay.Loader`.

Now you know the available configuration presets for `.npmbundlerrc` and 
how they work. Next you can learn how to configure your `.npmbundlerrc` file to 
use a configuration preset.

## Using liferay-npm-bundler Configuration Presets [](id=using-liferay-npm-bundler-configuration-presets)

Follow these steps to use a liferay-npm-bundler configuration preset in your 
`.npmbundlerrc` file:

1.  Create a `.npmbundlerrc` file in your project’s root folder, if it doesn't 
    already exist.

2.  Install the liferay-npm-bundler configuration preset. For example, you can 
    use the following command to install the standard configuration:

        npm install --save-dev liferay-npm-bundler-preset-standard

3.  Finally, define which configuration preset to use. The example below uses 
    the standard configuration preset:

        {
            "preset": "liferay-npm-bundler-preset-standard"
        }

Now you know how to configure the liferay-npm-bundler!

## Related Topics [](id=related-topics)

[Adding liferay-npm-bundler to Your Portlet](/develop/tutorials/-/knowledge_base/7-1/adding-liferay-npm-bundler-to-your-portlet)

[The Structure of OSGi Bundles Containing NPM Packages](/develop/tutorials/-/knowledge_base/7-1/the-structure-of-osgi-bundles-containing-npm-packages)
