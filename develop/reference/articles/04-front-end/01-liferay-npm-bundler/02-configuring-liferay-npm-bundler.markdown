# Configuring liferay-npm-bundler [](id=configuring-liferay-npm-bundler)

The liferay-npm-bundler is configured via a `.npmbundlerrc` file placed in 
the portlet project's root folder. You can create a complete configuration 
manually or extend a configuration preset (via Babel). 

This article explains the `.npmbundlerrc` file's structure. See the 
[default preset reference](/develop/reference/-/knowledge_base/7-1/how-the-default-preset-configures-the-liferay-npm-bundler) 
to learn how the default preset configures the liferay-npm-bundler. See the 
[Creating JavaScript Portlets with JavaScript Tooling](/develop/reference/-/knowledge_base/7-1/how-the-default-preset-configures-the-liferay-npm-bundler) 
tutorial to learn how to use the liferay-npm-bundler to create JavaScript 
portlets. 

## Understanding the .npmbundlerrc File's Structure [](id=understanding-the-npmbundlerrc-file-structure)

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

+$$$

**Note:** During this phase, Babel transforms package files (for example, to 
convert them to AMD format, if necessary), but doesn't transpile them. In 
theory, you could also transpile them by configuring the proper plugins. We 
recommend transpiling before running the bundler, to avoid mixing both unrelated 
processes.

$$$

**Post-Process:** Defined with the `post-plugins` property. An alternative to 
using the *pre-process* phase, this specifies plugins to run after the Babel 
phase has completed.

Here's an example of a `.npmbundlerrc` configuration:

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

+$$$

**Note:** Not all definition formats (`*`, `some-package-name`, and 
`some-package-name@version`) shown above are required. In most cases, the 
wildcard definition (`*`) is enough. The non-wildcard formats 
(`some-package-name` and `some-package-name@version`) are rare exceptions for 
packages that require a more specific configuration than the wildcard definition 
provides.

$$$

### Standard Configuration Options [](id=standard-configuration-options)

Below are the standard configuration options for the `.npmbundlerrc` file:

*config*: global configuration which is passed to all bundler and Babel plugins. 
Please refer to each plugin's documentation to find the available options for 
each specific plugin. 

*dump-report:* Sets whether to generate a debugging report. If `true`, a 
`liferay-npm-bundler-report.html` file is generated in the project directory 
with information such as what the liferay-npm-bundler is doing with each 
package. Note that you can also pass this as the build flag 
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
`liferay-npm-bundler-preset-standard` preset is used.

*verbose:* Sets whether to output detailed information about what the tool is 
doing to the console. The default value is `false`.

### Package Processing Options [](id=package-processing-options)

*"/"*: plugins' configuration for the project's package.

*"\"*: plugins' configuration for dependency packages.

*exclude:* defines glob expressions of files to exclude from bundling from all
or specific packages. Each list is an array identified by one of the following 
keys: `*` (any package), `{package name}` (any version of the package), or 
`{package name}@{version}` (a specific version of a package). Below is an 
example configuration:

    {
      "exclude": {
        "*": ["__tests__/**/*"],
        "is-object": ["test/**/*"],
        "is-array@1.0.1": ["test/**/*", "Makefile"]
      }
    }

*ignore:* skips processing the specified JavaScript files with Babel for the 
project. An example configuration is shown below:

    {
      "ignore": ["lib/legacy/**/*.js"]
    }

*include-dependencies:* defines packages to include in bundling, even if they 
are not listed under the `dependencies` section of `package.json`. These 
packages must be available in the `node_modules` folder (i.e. installed 
  manually, without saving them to `package.json`, or listed in the 
  `devDependencies` section).

*packages:* defines plugin configuration for npm packages, per package.

*process-serially:* Whether to process packages one by one, as opposed to 
parallel. The default value is `false` (parallel). Setting this option to `true` 
may prevent `EMFILE` errors (especially on Windows).

### OSGi Bundle Creation Options [](id=osgi-bundle-creation-options)

Since version 2.2.0, the liferay-npm-bundler can create portlet OSGi bundles for 
you. See the 
[Creating and Bundling JavaScript Portlets with JavaScript Tooling](/develop/tutorials/-/knowledge_base/7-1/creating-and-bundling-javascript-portlets-with-javascript-tooling) 
tutorial for complete instructions. The configuration options for OSGi bundle 
creation are shown below:

- **create-jar**: Creates an OSGi bundle when set to a truthy value. When 
set to `true`, all sub-options take default values. When an object is 
passed, as shown in the example above, each sub-option can be configured 
individually. Note that you can also pass this as a build flag: 
`$ liferay-npm-bundler --create-` or 
`$ liferay-npm-bundler -j`. The default value is `false`.

- **create-jar.auto-deploy-portlet**: **Note** that this option is 
deprecated. Use the `js-extender` option instead. 

- **create-jar.output-dir:** specifies where to place the final JAR

- **create-jar.features.js-extender:** controls whether to process the OSGi 
bundle with the JS Portlet Extender 
[CE App](https://web.liferay.com/marketplace/-/mp/application/115542926) 
|| 
[DXP App](https://web.liferay.com/marketplace/-/mp/application/115543020). 
You can also specify the minimum required version of the Extender to use for the 
bundle. This can be useful if you want to use advanced features in your bundle, 
but you want it to be deployable in older versions of the Extender. Pass the 
string `"any"` to let the bundle deploy in any version of the Extender. If 
`true`, the liferay-npm-bundler automatically determines the minimum version of 
the Extender required for the features used in the bundle. the default value is 
`true`. An example configuration is shown below:

    {
      "create-jar": {
        "features": {
          "js-extender": "1.1.0"
        }
      }
    }

- **create-jar.features.web-context:** specifies the context path to use for 
publishing bundle's static resources. The default value is 
`/{project name}-{project version}`.

- **create-jar.features.localization:** specifies the L10N file to be used by the 
bundle (see the 
[Creating JS Portlets with JS Tooling](Creating-OSGi-bundles#localization) 
tutorial for more information on using localization in your portlet. The default 
value is `features/localization/Language` if a properties file with that base 
name exists, otherwise the default is `undefined`.

- **create-jar.features.settings:** specifies the JSON file describing the 
configuration structure (see the 
[Creating JS Portlets with JS Tooling](Creating-OSGi-bundles#settings-configuration) 
tutorial for more information on the required settings configuration). The 
default value is `features/settings.json` if that file exists, otherwise the 
default is `undefined`.

+$$$

**Note:** Plugins' configuration specifies the options for configuring plugins 
in all the possible phases, as well as the `.babelrc` file to use when running 
Babel (see [Babel's documentation](https://babeljs.io/docs/usage/babelrc/) for 
more information on that file format). 

$$$

+$$$

**Note:** Prior to version 1.4.0 of the liferay-npm-bundler, package 
configurations were placed next to the tools options 
(`*`, `output`, `exclude`, etc.) To prevent package name collisions, package 
configurations are now namespaced and placed under the `packages` section. To 
maintain backwards compatibility, the liferay-npm-bundler falls back to the root 
section outside `packages` for package configuration, if no package 
configurations (`package-name@version`, `package-name`, or `*`) are found in the 
`packages` section.

$$$

Now you know the structure of the `.npmbundlerrc` file!
