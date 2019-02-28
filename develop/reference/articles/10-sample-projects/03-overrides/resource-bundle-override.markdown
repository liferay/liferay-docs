# Resource Bundle Override [](id=resource-bundle-override)

This example overrides the default
`javax.portlet.title.com_liferay_login_web_portlet_LoginPortlet` language key
for @product@'s default Login portlet. After deploying this sample to @product@,
the Login portlet's *Sign In* title is modified to display *Login Portlet
Override*.

![Figure 1: The customized Login portlet displays the new language key.](../../../images/hook-resourcebundle.png)

For reference, the Login portlet's language keys are stored in the
[liferay-portal](https://github.com/liferay/liferay-portal) Github repo's
`modules/apps/login/login-web/src/main/resources/content` folder.

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample leverages the
[`Provide-Capability`](https://bnd.bndtools.org/chapters/220-contracts.html)
OSGi manifest header.

## How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

This sample conveys the recommended approach to override a portlet's
language keys file for any module that is deployed to @product@'s OSGi runtime
(not applicable to @product@'s core language keys).

The steps to override a portlet's language keys are

- Provide the new language keys that will override the original ones.
- Prioritize the new module's resource bundle.

This sample's `src/main/resources/content` folder holds the language properties
file to override. Since this example's goal is to only override the English
keys, the `Language_en.properties` is added. You can add more language
properties files for additional language key locales you want to override (e.g.,
`Language_en.properties` for Spanish).

Once your language keys are in place, you must use OSGi manifest headers to
specify your custom language keys are for the target module. To compliment the
target module's resource bundle, you must aggregate your resource bundle with
the target module's resource bundle. This is done by ranking your module first
to prioritize its resource bundle over the target module resource bundle. See
this sample's `bnd.bnd` as an example for setting the `Provide-Capability` OSGi
header:

    Provide-Capability:\
        liferay.resource.bundle;\
            resource.bundle.base.name="content.Language",\
        liferay.resource.bundle;\
            bundle.symbolic.name=com.liferay.login.web;\
            resource.bundle.aggregate:String="(bundle.symbolic.name=com.liferay.blade.login.web.resource.bundle.override),(bundle.symbolic.name=com.liferay.login.web)";\
            resource.bundle.base.name="content.Language";\
            service.ranking:Long="2";\
            servlet.context.name=login-web

For more information on the `Provide-Capability` header and its parts, see the
[Prioritze Your Module's Resource Bundle](/develop/tutorials/-/knowledge_base/7-1/overriding-a-modules-language-keys#prioritize-your-modules-resource-bundle)
section.

This approach can be used to override any portlet's language keys (i.e.,
`language.properties` files that are inside a module deployed to @product@'s
OSGi runtime). If you need to override @product@'s core language keys, see the
[Overriding Global Language Keys](/develop/tutorials/-/knowledge_base/7-1/overriding-global-language-keys)
tutorial.

For more information on using a resource bundle to override a module's
language keys, see the
[Overriding a Module's Language Keys](/develop/tutorials/-/knowledge_base/7-1/overriding-a-modules-language-keys)
tutorial.

## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/overrides/login-web-resource-bundle-override)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/7.1/liferay-workspace/overrides/login-web-resource-bundle-override)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/7.1/maven/overrides/login-web-resource-bundle-override)
