---
header-id: understanding-the-npmbundlerrcs-structure
---

# Understanding the `.npmbundlerrc`'s Structure

[TOC levels=1-4]

The liferay-npm-bundler is configured via a `.npmbundlerrc` file placed in the 
widget project's root folder. You can create a complete configuration manually 
or extend a configuration preset (via Babel). 

This article explains the `.npmbundlerrc` file's structure. See the 
[default preset reference](/docs/7-2/reference/-/knowledge_base/r/how-the-default-preset-configures-the-liferay-npm-bundler) 
to learn how the default preset configures the liferay-npm-bundler. See 
[Creating JavaScript Widgets with JavaScript Tooling](/docs/7-2/frameworks/-/knowledge_base/f/creating-javascript-widgets-with-javascript-tooling) 
to learn how to use the liferay-npm-bundler along with the Liferay Bundle 
Generator to create JavaScript widgets. 

## The Structure

The `.npmbundlerrc` file has four possible phase definitions: *copy-process*, 
*pre-process*, *post-process*, and *babel*. These phase definitions are 
explained in more detail below:

**Copy-Process:** Defined with the `copy-plugins` property (only available for 
dependency packages). Specifies which files should be copied or excluded from 
each given package.

**Pre-Process:** Defined with the `plugins` property. Specifies plugins to run 
before the Babel phase is run.

**Babel:** Defined with the `.babelrc` definition. Specifies the `.babelrc` file 
to use when running Babel through the package's `.js` files. 

| **Note:** During this phase, Babel transforms package files (for example, to
| convert them to AMD format, if necessary), but doesn't transpile them. In
| theory, you could also transpile them by configuring the proper plugins. We
| recommend transpiling before running the bundler, to avoid mixing both unrelated
| processes.

**Post-Process:** Defined with the `post-plugins` property. An alternative to 
using the *pre-process* phase, this specifies plugins to run after the Babel 
phase has completed.

Here's an example of a `.npmbundlerrc` configuration:

```json
{
    "exclude": {
        "*": [
            "test/**/*"
        ],
        "some-package-name": [
            "test/**/*",
            "bin/**/*"
        ],
        "another-package-name@1.0.10": [
            "test/**/*",
            "bin/**/*",
            "lib/extras-1.0.10.js"
        ]
    },
    "include-dependencies": [
        "isobject", "isarray"
    ],
    "output": "build",
    "process-serially": false,
    "verbose": false,
    "dump-report": true,
    "config": {
        "imports": {
            "npm-angular5-provider": {
                "@angular/common": "^5.0.0",
        		"@angular/core": "^5.0.0"
            }
        }
    },
    "/": {
		"plugins": ["resolve-linked-dependencies"],
		".babelrc": {
			"presets": ["liferay-standard"]
		},
		"post-plugins": [
            "namespace-packages",
            "inject-imports-dependencies"
        ]
  	},
  	"*": {
  		"copy-plugins": ["exclude-imports"],
  		"plugins": ["replace-browser-modules"],
  		".babelrc": {
  			"presets": ["liferay-standard"]
  		},
  		"post-plugins": [
  			"namespace-packages",
  			"inject-imports-dependencies",
  			"inject-peer-dependencies"
  		]
  	},
    "packages": {
        "a-package-name": [
        "copy-plugins": ["exclude-imports"],
    		"plugins": ["replace-browser-modules"],
    		".babelrc": {
    			"presets": ["liferay-standard"]
    		},
    		"post-plugins": [
    			"namespace-packages",
    			"inject-imports-dependencies",
    			"inject-peer-dependencies"
    		]
        ],
        "other-package-name@1.0.10": [
          "copy-plugins": ["exclude-imports"],
      		"plugins": ["replace-browser-modules"],
      		".babelrc": {
      			"presets": ["liferay-standard"]
      		},
      		"post-plugins": [
      			"namespace-packages",
      			"inject-imports-dependencies",
      			"inject-peer-dependencies"
      		]
        ]
    }
}
```

| **Note:** Not all definition formats (`*`, `some-package-name`, and 
| `some-package-name@version`) shown above are required. In most cases, the 
| wildcard definition (`*`) is enough. The non-wildcard formats 
| (`some-package-name` and `some-package-name@version`) are rare exceptions for 
| packages that require a more specific configuration than the wildcard 
| definition provides.

### Standard Configuration Options

Below are the standard configuration options for the `.npmbundlerrc` file:

*config*: Defines the global configuration that is made available to all 
liferay-npm-bundler and Babel plugins. Please refer to each plugin's 
documentation to find the available options for each specific plugin. 

```json
{
  "config": {
    "imports": {
      "vuejs-provider": {
        "vue": "^2.0.0"
      }
    }
  }
}
```

*dump-report:* Sets whether to generate a debugging report. If `true`, a 
`liferay-npm-bundler-report.html` file is generated in the project directory 
that describes all actions and decisions taken when processing project and npm 
modules. Note that you can also pass this as the build flag 
`$ liferay-npm-bundler --dump-report` or `$ liferay-npm-bundler -r`. The default 
value is `false`.

*no-tracking:* whether to send usage analytics to our servers. Note that you can 
also pass this as a build flag with the CLI argument 
`$ liferay-npm-bundler --no-tracking`, or by 
creating a marker file called `.liferay-npm-bundler-no-tracking` in the 
project's root folder or any of its ancestors, or by setting the environment 
variable `LIFERAY_NPM_BUNDLER_NO_TRACKING=''`. The default value is `false`.

*output:* by default the bundler writes packages to the standard Gradle 
resources folder: `build/resources/main/META-INF/resources`. Set this value to 
override the default output folder. Note that the dependency npm packages are 
placed in a `node_modules` folder inside the build folder. Note if `create-jar` 
is set, the default output folder is `build`.

*preset:* specifies the `liferay-npm-bundler` preset to use as a base 
configuration. Note that if a `.npmbundlerrc` file is not provided, the default 
`liferay-npm-bundler-preset-standard` preset is used. All settings provided by 
the preset are inherited, but they can be overridden.

*verbose:* Sets whether to output detailed information about what the tool is 
doing to the console. The default value is `false`.

### Package Processing Options [](id=package-processing-options)

*"/"*: plugins' configuration for the project's package.

*"\"*: plugins' configuration for dependency packages.

*(asterisk)*: Defines the default plugin configuration for all npm packages. It 
contains four values identified by a corresponding key. Keys `copy-plugins`

*exclude:* defines glob expressions of files to exclude from bundling from all
or specific packages. Each list is an array identified by one of the following 
keys: `*` (any package), `{package name}` (any version of the package), or 
`{package name}@{version}` (a specific version of a package). Below is an 
example configuration:

```json
{
  "exclude": {
    "*": ["__tests__/**/*"],
    "is-object": ["test/**/*"],
    "is-array@1.0.1": ["test/**/*", "Makefile"]
  }
}
```

*ignore:* skips processing the specified JavaScript files with Babel for the 
project. An example configuration is shown below:

```json
{
  "ignore": ["lib/legacy/**/*.js"]
}
```

*include-dependencies:* defines packages to include in bundling, even if they 
are not listed under the `dependencies` section of `package.json`. These 
packages must be available in the `node_modules` folder (i.e. installed 
  manually, without saving them to `package.json`, or listed in the 
  `devDependencies` section).

*packages:* defines plugin configuration for npm packages, per package.

*max-parallel-files:* Defines the maximum number of files to process in parallel 
to avoid EMFILE errors (especially on Windows). The default value is `128`. 

*process-serially:* **Note**: removed since v 2.7.0. Replaced with 
`max-parallel-files`. 

### OSGi Bundle Creation Options

Since version 2.2.0, the liferay-npm-bundler can create widget OSGi bundles for 
you. See 
[Creating and Bundling JavaScript Widgets with JavaScript Tooling](/docs/7-2/frameworks/-/knowledge_base/f/creating-and-bundling-javascript-widgets-with-javascript-tooling) 
for complete instructions. The configuration options for OSGi bundle 
creation are shown below:

- **create-jar**: Creates an OSGi bundle when set to a truthy value. When set to 
`true`, all sub-options take default values. When an object is passed, each 
sub-option can be configured individually. Note that you can also pass this as a 
build flag: `$ liferay-npm-bundler --create-` or `$ liferay-npm-bundler -j`. The 
default value is `false`.

```json
{
  "create-jar": true
}
```

- **create-jar.auto-deploy-portlet**: **Note** that this option is deprecated. 
Use the `create-jar.features.js-extender` option instead. 

- **create-jar.features.configuration**: specifies the file describing the 
system (OSGi) and widget instance (widget preferences, as defined in the 
Portlet spec) configuration to use. (see 
[Configuring System Settings and Instance Settings for Your JavaScript Widgets](/docs/7-2/frameworks/-/knowledge_base/f/configuring-system-settings-and-instance-settings-for-your-javascript-widget) 
for more information on the required settings configuration). The 
default value is `features/configuration.json` if that file exists, otherwise 
the default is `undefined`.

```json
{
  "create-jar": {
    "features": {
      "configuration": "features/configuration.json"
    }
  }
}
```

- **create-jar.output-dir:** specifies where to place the final JAR

```json
{
  "create-jar": {
    "output-dir": "dist"
  }
}
```

- **create-jar.features.js-extender:** controls whether to process the OSGi 
bundle with the JS Portlet Extender. You can also specify the minimum required 
version of the Extender to use for the bundle. This can be useful if you want to 
use advanced features in your bundle, but you want it to be deployable in older 
versions of the Extender. Pass the string `"any"` to let the bundle deploy in 
any version of the Extender. If `true`, the liferay-npm-bundler automatically 
determines the minimum version of the Extender required for the features used in 
the bundle. the default value is `true`. An example configuration is shown 
below:

```json
{
  "create-jar": {
    "features": {
      "js-extender": "1.1.0"
    }
  }
}
```

- **create-jar.features.web-context:** specifies the context path to use for 
publishing bundle's static resources. The default value is 
`/{project name}-{project version}`.

```json
{
  "create-jar": {
    "features": {
      "web-context": "/my-project"
    }
  }
}
```

- **create-jar.features.localization:** specifies the L10N file to use for the 
bundle (see 
[Providing Localization in Your JavaScript Widgets](/docs/7-2/frameworks/-/knowledge_base/f/configuring-localization-in-your-javascript-widgets) 
for more information on using localization in your widget. The default value is 
`features/localization/Language` if a properties file with that base name 
exists, otherwise the default is `undefined`.

```json
{
  "create-jar": {
    "features": {
      "localization": "features/localization/Language"
    }
  }
}
```

- **create-jar.features.settings:** **Note** that this option is deprecated. Use 
the `create-jar.features.configuration` option instead. 

| **Note:** Plugins' configuration specifies the options for configuring plugins 
| in all the possible phases, as well as the `.babelrc` file to use when running 
| Babel (see [Babel's documentation](https://babeljs.io/docs/usage/babelrc/) for 
| more information on that file format). 

| **Note:** Prior to version 1.4.0 of the liferay-npm-bundler, package 
| configurations were placed next to the tools options 
| (`*`, `output`, `exclude`, etc.) To prevent package name collisions, package 
| configurations are now namespaced and placed under the `packages` section. To 
| maintain backwards compatibility, the liferay-npm-bundler falls back to the 
| root section outside `packages` for package configuration, if no package 
| configurations (`package-name@version`, `package-name`, or `*`) are found in 
| the `packages` section.

Now you know the structure of the `.npmbundlerrc` file!
