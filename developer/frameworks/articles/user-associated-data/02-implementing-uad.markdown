---
header-id: adding-the-uad-framework-to-a-service-builder-application
---

# Adding the UAD Framework to a Service Builder Application

[TOC levels=1-4]

You'll touch two modules of a Service Builder application to implement the UAD
features: the `-service` module and a brand new module that Service Builder
generates for you, the `-uad` module.

## Update the Service Module

Before you specify your model entity's fields to manage with the UAD framework,
make sure you have the right dependencies.

### Include Dependencies

To compile the code that Service Builder generates, you need dependencies on
`com.liferay.petra.String` and `com.liferay.portal kernel`. Make sure your
service module's `build.gradle` includes both:

```groovy
dependencies {
    compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "4.4.0"
    compileOnly group: "com.liferay", name: "com.liferay.petra.string", version: "3.0.0"
    ...
}
```

### Choose the Fields to Anonymize

In the `service.xml` file, choose the fields to be handled by the framework by
adding a `uad-anonymize-field-name="[fieldName]"` or `uad-nonanonymizable=true`. 

For example, to replace the `userName` field of your custom entity with the
`fullName` of the anonymous user,

```xml
<column name="userName" type="String" uad-anonymize-field-name="fullName" />
```

This declaration specifies that the `content` field cannot be auto-anonymized by
the framework but should be reviewed manually.

```xml
<column name="content" type="String" uad-nonanonymizable="true" />
```

Run Service Builder. A new module is generated alongside your other modules,
called `my-app-uad`. It requires a little massaging.

## Update the UAD Module

First, include your dependencies, and then provide your application's name to
the user interface. 

### Include Dependencies

The new module is generated without a build script, so you must provide one. It
should include dependencies on `osgi.service.component.annotations`,
`com.liferay.portal.kernel`, `com.liferay.petra.string`, the
`com.liferay.user.associated.data.api`, and your own application's `-api`
module:

```groovy
dependencies {
  compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "4.4.0"
  compileOnly group: "com.liferay", name: "com.liferay.user.associated.data.api", version: "4.1.1"
  compileOnly group: "com.liferay", name: "com.liferay.petra.string", version: "3.0.0"
  compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
  compileOnly project(":modules:custom:custom-api")
  }
```

### Provide Your App's Name to the UI

The simplest way to provide your app's name to the anonymization UI is to
include a language key in your `Language.properties` file:

```properties
application.name.[Bundle-SymbolicName]=Custom App
```

The bracketed text is the `Bundle-SymbolicName` from your `-uad` module's
`bnd.bnd` file. 

While this approach is recommended, it has one downside: multiple language keys
are used to label a single application. @product@ applications use the
`com.liferay.lang.merger` plugin to avoid this. Here's the relevant part of the
Blogs application's `blogs-uad/build.gradle`:

```groovy
apply plugin: "com.liferay.lang.merger"

dependencies {

...

}

mergeLang {
    setting("../blogs-web/src/main/resources/content") {
        transformKey "javax.portlet.title.com_liferay_blogs_web_portlet_BlogsPortlet", "application.name.com.liferay.blogs.uad"
    }

    sourceDirs = ["../blogs-web/src/main/resources/content"]
}
```
