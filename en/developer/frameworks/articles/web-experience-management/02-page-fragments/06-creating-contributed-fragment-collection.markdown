---
header-id: creating-contributed-fragment-collection
---

# Creating a Contributed Fragment Collection

[TOC levels=1-4]

To create a Contributed Fragment Collection, a developer must,

1.  Create a module which will contain the necessary logic and the fragments.

2.  Extend the class `BaseFragmentCollectionContributor` with all the logic for
    reading the contributed fragments.

3.  Add fragments as resources in the module.

Once you deploy the module, any fragments contained in it will be available for 
use. 

To better understand Contributed Fragment Collections, create one called
`DemoFragmentCollectionContributor`.

## Create a Module

First you must create the module in your development environment. Follow the
instructions in
[Creating a Project](/docs/7-2/reference/-/knowledge_base/r/creating-a-project).

## Create the Java Class

Next, you must create the Java package and class to handle the logic for the 
contributed collection:

1.  Create a package in your module named
    `com.liferay.fragment.collection.contributor.demo`

2.  Inside of that package, create a Java class named
    `DemoFragmentCollectionContributor` that extends
    `BaseFragmentCollectionContributor`.

3.  Above the class declaration, add the `@Component` annotation to set the
    service class:

    ```java
    @Component(service = FragmentCollectionContributor.class)
    ```

4.  Create the variable for the servlet context:

    ```java
    private ServletContext _servletContext;
    ```

5.  Define the `getFragmentCollectionKey()` and `getServletContext()` methods:

    ```java
    @Override
    public String getFragmentCollectionKey() {
        return "DEMO";
    }

    @Override
    public ServletContext getServletContext() {
        return _servletContext;
    }
    ```

5.  Below that use the `@Reference` annotation to define your modules symbolic
    name:

    ```java
    @Reference(
        target = "(osgi.web.symbolicname=com.liferay.fragment.collection.contributor.demo)"
    )
    ```

6.  Organize your imports and save.

## Create the Resources

Next you need to include the fragments that you want to contribute in your 
module:

1.  In your module's `resources/` folder, create the folder structure
    `/com/liferay/fragments/collection/contributor/demo/dependencies`.

2.  Copy the Fragments you want to distribute into the folder. You can learn 
    how to create a Fragment in the
    [Creating Fragments section](/docs/7-2/frameworks/-/knowledge_base/f/creating-fragments).

3.  Create a file named `collections.json` in the same folder with this format:

    ```json
    {
        "fragments": [
            "[fragment-1]",
            "[fragment-2]",
            "[fragment-3]",
                ...
        ],
        "name": "[collection-name]"
    }
    ```

    If a fragment is not listed in `collections.json`, it will not be available
    in the Contributed Collection, even if the files are included in the module.

Next, you'll dive into providing thumbnail images and language
keys/translations.

## Providing Thumbnail Images

You can also provide thumbnail images for reference for your fragments:

1.  Under `resources/META-INF/resources` create a folder named `thumbnails`.

2.  Copy thumbnail images into the folder with the format `[fragment-name].png`
    for each fragment.

| **Note:** All fragments added through a Contributed Fragment Collection
| will be available globally to all Sites.

## Providing Language Keys

Providing language keys in your Fragment gives you the option for translating
the text you display. Here's how to do it:

1.  You must define your language keys in the Fragment's collection folder.
    Create the `[COLLECTION]/src/main/resources/content/Language.properties`
    file.

2.  Add your language keys. For example,

    ```properties
    applied-style=Applied Style
    this-is-the-style-that-will-be-applied=This is the style that will be applied.
    dark=Dark
    light=Light
    ```

You can learn more about providing translations in the
[Localizing Your Application](/docs/7-2/frameworks/-/knowledge_base/f/localizing-your-application)
article.

## Deploy the Contributed Fragment Collection

Now that you have created the necessary pieces of the module, you can build it
and [deploy it](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project) to
@product@. After it's deployed, the Fragments will be available for use. This
can also be done by using the
[Fragments Toolkit](/docs/7-2/frameworks/-/knowledge_base/f/page-fragments-desktop-tools#importing-and-exporting-fragments).
Contributed Fragments cannot be edited with Liferay, and can only be updated by
editing the fragments in your module and the building and redeploying them.
