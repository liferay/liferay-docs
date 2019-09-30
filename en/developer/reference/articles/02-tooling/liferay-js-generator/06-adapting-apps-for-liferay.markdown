# Adapting Existing Projects for @product@

Since [#347](https://github.com/liferay/liferay-js-toolkit/issues/347), you can 
create projects with your favorites tools, such as [create-react-app](https://facebook.github.io/create-react-app/), 
and adapt them to use the Liferay JS Toolkit and run on @product@. This lets you 
use [JavaScript portlet features](creating-and-bundling-javascript-widgets-with-javascript-tooling) 

| **Note:** At the moment, only [`create-react-app`](https://facebook.github.io/create-react-app/) 
| projects are supported. More projects will be added in the near future.

Follow these steps to use the Liferay JS Generator to adapt your existing 
project:

1.  Open the command line and navigate to your project's folder.

2.  Run the Liferay JS Generator's `adapt` subtarget:

    ```bash
    yo liferay-js:adapt
    ```
    
3.  Answer the prompts. An example configuration is shown below:

    ```bash
    ? Under which category should your widget be listed? category.sample
    ? Do you have a local installation of Liferay for development? Yes
    ? Where is your local installation of Liferay placed? /home/user/liferay
    ```
    
    Your project is adapted to use the Liferay JS Toolkit and run on @product@! 
    
4.  The adapt process automatically added a few npm scripts to the project's 
    `package.json` so you can build and deploy your project to @product@. Run 
    the scripts below to build or deploy your project. Note that you can swap 
    `npm` for `yarn` below if you prefer to use yarn instead:

    ``bash
    npm run build:liferay
    ```

    ``bash
    npm run deploy:liferay
    ```