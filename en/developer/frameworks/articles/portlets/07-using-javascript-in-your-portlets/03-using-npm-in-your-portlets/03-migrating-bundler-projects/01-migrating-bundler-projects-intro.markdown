---
header-id: migrating-a-liferay-npm-bundler-project-from-1-x-to-2-x
---

# Migrating a liferay-npm-bundler Project from 1.x to 2.x

[TOC levels=1-4]

You should use the latest 2.x version of the liferay-npm-bundler. It 
[offers more stability and includes more features out-of-the-box](/docs/7-2/reference/-/knowledge_base/r/what-changed-between-liferay-npm-bundler-1-x-and-2-x). 
If you already created a project using the 1.x version, don't worry. Follow 
these steps to migrate your project to 2.x:

1.  Update the `liferay-npm-bundler` dependency in your `package.json` to 
    version 2.x:

    ```json
    {
      "devDependencies": {
        ...
        "liferay-npm-bundler": "^2.0.0",
        ...
      },
      ...
    }
    ```

2.  Remove all `liferay-npm-bundler-preset-*` dependencies from your 
    `package.json` because liferay-npm-bundler 2.x includes these by default.

3.  Remove any bundler presets you configured in your `.npmbundlerrc` file. 
    liferay-npm-bundler 2.x includes one smart preset that handles all 
    frameworks automatically.

These are the standard requirements that all projects have in common. The 
remaining steps depend on your project's framework. Follow the instructions in 
the corresponding section to finish migrating your project. 
