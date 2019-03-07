# npm Samples [](id=npm-samples)

This section focuses on Liferay npm sample portlets built with Gradle. You can
view these samples by visiting the
[gradle/apps/npm](https://github.com/liferay/liferay-blade-samples/tree/7.0/gradle/apps/npm)
folder in the `liferay-blade-samples` Github repository.

<!-- Readd the below snippet when npm samples are available for all build tools.
-Cody -->

<!-- `apps/npm` folder corresponding to your preferred
build tool:

- [Gradle sample apps](https://github.com/liferay/liferay-blade-samples/tree/7.0/gradle/apps/npm)
- [Liferay Workspace sample apps](https://github.com/liferay/liferay-blade-samples/tree/7.0/liferay-workspace/apps/npm)
- [Maven sample apps](https://github.com/liferay/liferay-blade-samples/tree/7.0/maven/apps/npm)

-->

The following npm samples are documented:

- [Angular npm Portlet](angular-npm-portlet)
- [Billboard.js npm Portlet](billboard-js-npm-portlet)
- [Isomorphic npm Portlet](isomorphic-npm-portlet)
- [jQuery npm Portlet](jquery-npm-portlet)
- [Metal.js npm Portlet](metal-js-npm-portlet)
- [React npm Portlet](react-npm-portlet)
- [Simple npm Portlet](simple-npm-portlet)
- [Vue.js npm Portlet](vue-js-npm-portlet)

+$$$

**Note:** The minifier fails on Liferay DXP 7.0 when JSDoc is present in a 
portlet. To resolve this, use 
[Grunt](https://gruntjs.com/getting-started) 
[uglify](https://www.npmjs.com/package/grunt-contrib-uglify) 
to remove the JSDoc comments. This process may take a long time, depending on 
the number of files that require an update. 

$$$

Visit a particular sample page to learn more!
