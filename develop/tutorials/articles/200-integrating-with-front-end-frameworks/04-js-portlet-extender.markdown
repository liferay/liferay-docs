# JS Portlet Extender

## What is JS Portlet Extender?

It is an OSGi module that provides extra functionality to Liferay so that developers are able to deploy pure Javascript portlets that can be built with 100% Javascript tools.

Even though the deployed artifacts are built with pure Javascript tools, they still are OSGi bundles that are processed by the extender to make some automagic services and resources available.

In particular, the extender is able to instantiate an on-the-fly generated portlet whenever a specific type of OSGi bundle is deployed to Liferay. This is done without any need for the developer to provide any XML or Java class to make the portlet instantiate or work. He just needs to provide the Javacript entry point for the portlet.

![Extender lifecycle](extender-lifecycle.png)


## Developing an OSGi bundle to be handled by the extender

The only prerequisite to make an OSGi bundle be processed by the extender is the presence of a MANIFEST header:

```
Require-Capability: osgi.extender;filter:="(osgi.extender=liferay.frontend.js.portlet)"
```

Just by including that in your OSGi bundle's MANIFEST.MF file, the extender will kick in. Of course, it is very probable that you will also need a header like:

```
Web-ContextPath: /my-project
```

That will make your bundle instantiate a web context that is able to serve resources to the browser. But this would only be needed if your need to serve any non-Javascript resource, like images, CSSs, or static HTML from your bundle.

Once the extender activates, it will look for a `package.json` file inside the `META-INF/resources` folder of your OSGi bundle and will parse the `main` entry to find the initial Javascript module of your bundle.

Such initial module is expected to export a Javascript function with the following signature:

```javascript
function({portletNamespace, contextPath, portletElementId}) {}
```

Which, in turn, is expected to render some HTML to the portlet's area to interact with the user.

As you can see, the entry point function receives one parameter which is an object with three fields:

* **portletNamespace**: the unique namespace of the portlet as defined in the Portlet specification.
* **contextPath**: the URL path that can be used to retrieve bundle resources from the browser (it doesn't contain the protocol, host, or port parts, just the absolute path).
* **portletElementId**: the DOM identifier of the portlet's &lt;DIV> node, that can be used to render HTML inside.

That's enough to make your bundle be managed by the extender. Let's now see what else can be tweaked.

### Tweaking extender bundles

Given that the extender will generate a portlet on-the-fly for you, you won't be able to customize everything, as the portlet is automatic and you don't have any control over it. 

However, the extender lets you configure some values through the use of customized values in the `portlet` section of your `package.json` file. You can place any valid portlet property inside such section as you would do in the Java's `@Component` annotation of a portlet.

Thus, if you want to mimick this Java annotation:

```java
@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=category.sample",
        "com.liferay.portlet.instanceable=true",
        "javax.portlet.display-name=My Portlet",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)
public class MyPortlet extends MVCPortlet {
}
```

You must write the following in your `package.json`:

```json
{
    "portlet": {
        "com.liferay.portlet.display-category": "category.sample",
        "com.liferay.portlet.instanceable": "true",
        "javax.portlet.display-name": "My Portlet",
        "javax.portlet.security-role-ref": "power-user,user"
    }
}
```

### Bundling extender bundles

There are several ways to assemble an extender bundle (like `gradle`, the `jar` tool of the JDK, or even a ZIP compressor), but the intention of the extender is to be able to develop with 100% Javascript tools, using an standard workflow. So, to achieve that, we encourage the use of the [liferay-npm-bundler](https://github.com/liferay/liferay-npm-build-tools/wiki/How-to-use-liferay-npm-bundler) tool that comes with features that lets you create extender bundles easily. Find out in the next chapter how to use it.

## Using liferay-npm-bundler to create an extender bundle

> 👀 To ease project configuration, we have created a [Yeoman](http://yeoman.io/) generator to create projects from scratch that can be directly deployed to Liferay. Learn how to use it in [its wiki page](https://github.com/liferay/liferay-npm-build-tools/wiki/How-to-use-generator-liferay-bundle).


Creating an extender bundle with `liferay-npm-bundler` is easy. All you need to do is follow the standard process you are used to, but provide an extra option to the bundler to make it output the final JAR too. That option is called `create-jar`, goes in the `.npmbundlerrc` file and has the following structure:

```json
{
    "create-jar": {
        "auto-deploy-portlet": "true",
        "output-dir": "dist",
        "web-context-path": "/my-portlet"
    }
}
```

Valid options are:

* **auto-deploy-portlet**: tells the bundler to create a JAR file to be processed by the extender. Defaults to `true` and only needs to be set to `false` when you want to create a bundle with no portlet (for instance: if you want to provide shared npm packages to be used with [imports](https://community.liferay.com/blogs/-/blogs/why-we-need-a-new-liferay-npm-bundler-2-of-3-).
* **output-dir**: tells the bundle where to put the generated JAR file. Defaults to the standard output folder, but it is wise to change it so that the JAR and intermediate files are not mixed.
* **web-context-path**: specifies the URL path to be used to export static resources when the bundle is deployed to Liferay. Defaults to `<project name>-<project version>` but you can change it to anything you want so that you can reference static resource from your source code.

## Conclusion

With the advent of the JS Portlet Extender a new era in Liferay frontend development begins. The bundler opened the path to using standard Javascript workflows and the extender plus the Yeoman generator has closed the loop so that developers can now create and develop Liferay frontend projects using 100% Javascript tools, without any need to rely on Java any more.






