# Configuring liferay-npm-bundler [](id=configuring-liferay-npm-bundler)

The liferay-npm-bundler is configured via a `.npmbundlerrc` file placed in the 
portlet project's root folder. You can create a complete configuration manually 
or extend a configuration preset (via Babel). 

This tutorial explains the `.npmbundlerrc` file's structure and shows how the 
default preset configures the liferay-npm-bundler.

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
    	}
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

+$$$

**Note:** Not all definition formats (`*`, `some-package-name`, and 
`some-package-name@version`) shown above are required. In most cases, the 
wildcard definition (`*`) is enough. The non-wildcard formats 
(`some-package-name` and `some-package-name@version`) are rare exceptions for 
packages that require a more specific configuration than the wildcard definition 
provides.

$$$

Below are the configuration options for the `.npmbundlerrc` file:

*exclude:* defines glob expressions of files to exclude from bundling from all
or specific packages.

*include-dependencies:* defines packages to include in bundling, even if they 
are not listed under the `dependencies` section of `package.json`. These 
packages must be available in the `node_modules` folder (i.e. installed 
manually, without saving them to `package.json`, or listed in the 
`devDependencies` section).

*output:* by default the bundler writes packages to the standard Gradle 
resources folder: `build/resources/main/META-INF/resources`. Set this value to 
override the default output folder.

*process-serially:* Process packages in parallel, leveraging Node.js 
asynchronous model, or one by one. The default value is `false`, (parallel), but 
if you get EMFILE errors, you can disable this.

*verbose:* Sets whether to output detailed information about what the tool is 
doing to the commandline.

*dump-report:* Sets whether to generate a debugging report. If `true`, an HTML 
file is generated in the project directory with information such as what the 
liferay-npm-bundler is doing with each package.

*config*: global configuration which is passed to all bundler and Babel plugins. 
Please refer to each plugin's documentation to find the available options for 
each specific plugin.

*"/"*: plugins' configuration for the project's package.

*"\"*: plugins' configuration for dependency packages.

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

Now that you know the structure of the `.npmbundlerrc` file, you can learn about 
the default configuration preset.

## How the Default Preset Configures the liferay-npm-bundler [](id=how-the-default-preset-configures-the-liferay-npm-bundler)

The liferay-npm-bundler comes with a default configuration preset: 
[`liferay-npm-bundler-preset-standard`](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-preset-standard)
---Note you may omit the `liferay-npm-bundler` prefix from the npm package name 
in your `.npmbundlerrc` file. This preset configures several plugins for the 
build process and is automatically used (even if the `.npmbundlerrc` is 
missing), unless you override it with one of your own. Running the 
liferay-npm-bundler with this preset applies the 
[config file](https://github.com/liferay/liferay-npm-build-tools/blob/master/packages/liferay-npm-bundler-preset-standard/config.json) 
from `liferay-npm-bundler-preset-standard`:

    {
    	"/": {
    		"plugins": ["resolve-linked-dependencies"],
    		".babelrc": {
    			"presets": ["liferay-standard"]
    		},
    		"post-plugins": ["namespace-packages", "inject-imports-dependencies"]
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
    	}
    }

+$$$

**Note:** You can override configuration preset values by adding your own 
configuration to your project's `.npmbundlerrc` file. For instance, using the 
configuration preset example above, you can define your own `.babelrc` value in 
`.npmbundlerrc` file to override the defined "liferay-standard" babelrc preset.

$$$

The 
[`liferay-standard` preset](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/babel-preset-liferay-standard) 
applies the following plugins to packages:

- [exclude-imports](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-plugin-exclude-imports):
  Exclude packages declared in the `imports` section from the build.

- [inject-imports-dependencies](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-plugin-inject-imports-dependencies):
  Inject dependencies declared in the `imports` section in the dependencies' 
  `package.json` files.

- [inject-peer-dependencies](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-plugin-inject-peer-dependencies):
  Inject declared peer dependencies (as they are resolved in the project's 
  `node_modules` folder) in the dependencies' `package.json` files.

- [namespace-packages](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-plugin-namespace-packages):
  Namespace package names based on the root project's package name to isolate 
  packages per project and avoid collisions. This prepends 
  `<project-package-name>$` to each package name appearance in `package.json` 
  files.

- [replace-browser-modules](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-plugin-replace-browser-modules):
  Replace modules listed under `browser`/`unpkg`/`jsdelivr` section of 
  `package.json` files.

- [resolve-linked-dependencies](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-plugin-resolve-linked-dependencies):
  Replace linked dependencies versions appearing in `package.json` files 
  (those obtained from local file system or GitHub, for example) by their real 
  version number, as resolved in the project's `node_modules` directory.

In addition, the bundler runs Babel with the 
[babel-preset-liferay-standard](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/babel-preset-liferay-standard) 
preset, that invokes the following plugins:

- [babel-plugin-name-amd-modules](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/babel-plugin-name-amd-modules):
  Name AMD modules based on package name, version, and module path.

- [babel-plugin-namespace-amd-define](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/babel-plugin-namespace-amd-define):
  Add a prefix to AMD `define()` calls (by default `Liferay.Loader.`).

- [babel-plugin-namespace-modules](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/babel-plugin-namespace-modules):
  Namespace modules based on the root project's package name, prepending 
  `<project-package-name>$`. Wrap modules inside an AMD `define()` module for 
  each module name appearance (in `define()` or `require()` calls) so that the 
  packages are localized per project and don't clash.

- [babel-plugin-normalize-requires](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/babel-plugin-normalize-requires):
  Normalize AMD `require()` calls.

- [babel-plugin-wrap-modules-amd](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/babel-plugin-wrap-modules-amd):
  Wrap modules inside an AMD `define()` module.

- [babel-plugin-transform-node-env-inline](https://github.com/babel/minify/tree/master/packages/babel-plugin-transform-node-env-inline):
  Inline the `NODE_ENV` environment variable, and if it's part of a binary 
  expression (eg. `process.env.NODE_ENV === "development"`), then statically 
  evaluate and replace it.

Now you know how to configure the liferay-npm-bundler!

## Related Topics [](id=related-topics)

[Adding liferay-npm-bundler to Your Portlet](/develop/tutorials/-/knowledge_base/7-0/adding-liferay-npm-bundler-to-your-portlet)

[The Structure of OSGi Bundles Containing NPM Packages](/develop/tutorials/-/knowledge_base/7-0/the-structure-of-osgi-bundles-containing-npm-packages)
