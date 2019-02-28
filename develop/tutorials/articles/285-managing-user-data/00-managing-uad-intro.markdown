# Managing user-associated data stored by custom applications [](id=managing-user-associated-data-stored-by-custom-applications)

@product-ver@ makes it possible for administrators to [delete or
anonymize](/discover/portal/-/knowledge_base/7-1/managing-user-data)
User Associated Data (UAD), providing a useful tool for compliance with the EU's
General Data Protection Regulation (GDPR). Out of the box, this tool only supports
Liferay applications (blogs, web content, etc.). This tutorial explains how to
anonymize data stored by your custom apps as well.

If your app was created using Service Builder, anonymization is easy.
Follow these steps:

1.  Include dependencies on `com.liferay.petra.string` and the
    `com.liferay.portal.kernel` in your Service Module's build script.

2.  Identify the fields you want to be able to anonymize in the service module's
    `service.xml` file.

3.  Run Service Builder. Provide a build script for the `-uad` module that is
    generated.

4.  Provide your application's name to the anonymization UI. If you skip this
    step, your app will be labeled using the `Bundle-SymbolicName` from the
    `-uad` module's `bnd.bnd` file.

Anonymization of apps not created using Service Builder will be covered in
a separate tutorial.

## Include dependencies [](id=include-dependencies)

In order to compile the code that Service Builder generates, you need
dependencies on Petra and 3.23.0 or later of Liferay `kernel` in your service
module's `build.gradle`:

    dependencies {
        compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "3.23.0"
        compileOnly group: "com.liferay", name: "com.liferay.petra.string", version: "2.0.0"
        ...
    }

## Choose fields to anonymize [](id=choose-fields-to-anonymize)

Next you must identify fields to anonymize by attaching anonymization attributes to
elements in the `-service` module's `service.xml` file. There are two ways to do
this.

- The `uad-anonymize-field-name` attribute indicates a field whose value is
replaced by that of the anonymous user in the UAD deletion process.

- The `uad-nonanonymizable` attribute indicates data that cannot be anonymized
automatically and must be reviewed by an administrator.

For example, in the blogs application, `uad-anonymize-field-name="fullName"` is
appended to the `userName` column in `service.xml`:

    <column name="userName" type="String" uad-anonymize-field-name="fullName" />

This indicates that the user name of a blog entry's author should be replaced by
the anonymous user's `fullName`.

The content of a blog post, in contrast, cannot be anonymized automatically:

    <column name="content" type="String" uad-nonanonymizable="true" />

The `uad-nonanonymizable` value of `true` indicates that the `content` field
will have to be reviewed by an administrator in order to remove a blog author's
UAD.

## Run Service Builder! [](id=run-service-builder)

At this point, you're ready to run Service Builder. This will generate a new
`-uad` module based on the values you added to `service.xml`. The new module is
generated without a build script, so you must provide a `build.gradle`. It
should include dependencies on `osgi.service.component.annotations`, `kernel`,
`Petra`, the `-api` module of the UAD application, as well as your own
application's `-api` module. The build script should look something like this:

    dependencies {
      compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "3.23.0"
      compileOnly group: "com.liferay", name: "com.liferay.user.associated.data.api", version: "3.0.2"
      compileOnly group: "com.liferay", name: "com.liferay.petra.string", version: "1.0.0"
      compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
      compileOnly project(":modules:custom:custom-api")
      ...
      }

At this point you can compile your application. Before you deploy it, however,
you should to make sure the UAD application recognizes it in a way that makes
sense to administrators.

+$$$

**Note:** Depending on how you created your project---for instance, if you used
Blade's Service Builder template rather than Liferay Dev Studio's---you may need
to include the new `-uad` module in your `settings.gradle` file before you can
compile:

    include "myapp-api", "myapp-service", "myapp-uad"

$$$

## Provide your app's name to the UI [](id=provide-your-apps-name-to-the-ui)

The simplest way to provide your app's name to the anonymization UI is to
include a language key in your `Language.properties` file:
`application.name.[Bundle-SymbolicName]=` where the bracketed text is the
`Bundle-SymbolicName` from your `-uad` module's `bnd.bnd` file. For example:
`application.name.com.liferay.docs.custom.portlet=Custom App`.

That's the recommended approach for custom apps, but if you look at the source
code for @product@ itself, you see that it isn't used. Why not? Because it has
the downside of creating multiple language keys to label a single application,
which can be confusing. To avoid multiplying language keys, Liferay applications
use the `com.liferay.lang.merger.` plugin. Here's what it looks like:

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

This is from the `-uad` module's `build.gradle` file in @product@'s blogs
application. The `setting` property identifies the location of the
`Language.properties` file (by Gradle convention, the `sourceDirs` property must
match `setting`). `transformKey` passes in first the language key for the
application's name, and then the `Bundle-SymbolicName` from the `-uad` module's
`bnd.bnd` file. The plugin takes the value of the first parameter and assigns it
to the second parameter. The end result is that a key from `Language.properties`
provides the name of the application to the anonymization UI---but no additional
language keys need to be created or maintained.

That's it! You can now delete or anonymize User Associated Data stored by your
app.
