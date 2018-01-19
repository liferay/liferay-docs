# Taglib Description Guidelines [](id=taglib-description-guidelines)

We welcome you to contribute tag descriptions for Liferay Portal's taglibs.
Please follow these guidelines as you describe tags in Liferay Portal's `.tld`
files.

Please create an LRDOCS ticket for the description, with `API` issue type, and 
be sure to reference the ticket number in any commits you make.

You can commit your changes in a branch based on the master branch of
[https://github.com/liferay/liferay-portal](https://github.com/liferay/liferay-portal).
Then send a pull request to user liferay, making sure to mention Cody Hoag
(GitHub handle: `@codyhoag`) so he can review your changes. In the pull request
description, mention any other version of Liferay Portal you'd like the changes
to be ported to. 

You must generate the API docs locally to verify the syntax is correct, before 
sending a pull request. 

Scan the rule summaries below or click on them for details: 

- [Rules](#rules-) 
    - [Accompany every tag library with a description.](#accompany-every-tag-library-with-a-description-) 
    - [Start All &lt;tag> descriptions with a verb.](#start-all--descriptions-with-a-verb-) 
    - [Include a screenshot of the tag at the end of the tag's description for reference, if applicable.](#include-a-screenshot-of-the-tag-at-the-end-of-the-tags-description-for-reference-if-applicable-) 
    - [Punctuate every attribute description (sentence or fragment) with a period.](#punctuate-every-attribute-description-sentence-or-fragment-with-a-period-) 
    - [Use complete sentences for all text following a description's initial phrase/sentence.](#use-complete-sentences-for-all-text-following-a-descriptions-initial-phrasesentence-) 
    - [Preferably start &lt;attribute> descriptions using an indefinite article "A" or "An".](#preferably-start--descriptions-using-an-indefinite-article-a-or-an-) 
    - [Only start attribute descriptions with definite article "The", if it's natural and, otherwise, awkward to use "A" or "An".](#only-start-attribute-descriptions-with-definite-article-the-if-its-natural-and-otherwise-awkward-to-use-a-or-an-) 
    - [Alternatively start <attribute> descriptions with a noun.](#alternatively-start--descriptions-with-a-noun-) 
    - [Use XML-safe text.](#use-xml-safe-text-) 
    - [Mark up all code, parameter, tag, and variable references.](#mark-up-all-code-parameter-tag-and-variable-references-) 
- [Attribute Patterns](#attribute-patterns-) 
    - [The name Pattern](#the-name-pattern-) 
    - [The boolean Pattern](#the-boolean-pattern-) 
    - [The param Pattern](#the-param-pattern-) 
    - [The onClick Pattern](#the-onclick-pattern-) 
    - [The CssClass Pattern](#the-cssclass-pattern-) 
- [Tag Types](#tag-types-) 
    - [Tags that have a Boolean or Integer value must have a &lt;type> tag.](#tags-that-have-a-boolean-or-integer-value-must-have-a--tag-) 
    - [There are special cases where a &lt;type> tag is needed in addition to Booleans and Integers](#there-are-special-cases-where-a--tag-is-needed-in-addition-to-booleans-and-integers-) 
- [Deprecated Tags](#deprecated-tags-) 
    - [Deprecated tags with a replacement](#deprecated-tags-with-a-replacement-) 
    - [Deprecated tags with no direct replacement](#deprecated-tags-with-no-direct-replacement-) 
- [How to Generate Taglib API Docs Locally](#how-to-generate-taglib-api-docs-locally-) 
    - [Use the Ant taglibdoc task to build taglib docs for Liferay Portal core tags](#use-the-ant-taglibdoc-task-to-build-taglib-docs-for-liferay-portal-core-tags-)
    - [Use the Gradle tlddoc task to build taglib docs for modules](#use-the-gradle-tlddoc-task-to-build-taglib-docs-for-modules-) 
- [How to Generate Taglib Jars Locally](#how-to-generate-taglib-jars-locally-) 
    - [Use the Gradle jarTlddoc task to generate jar files for your modules](#use-the-gradle-jartlddoc-task-to-generate-jar-files-for-your-modules-) 
    
## Rules [](id=rules)

Write tag descriptions like a champ, by following these rules! 

### Accompany every tag library with a description [](id=accompany-every-tag-library-with-a-description)

Example: 

    <description>Provides the Liferay UI component tags, prefixed with 
    <![CDATA[<code>liferay-ui:</code>]]>.</description>

    <short-name>liferay-ui</short-name>

### Start All &lt;tag> descriptions with a verb [](id=start-all-tag-descriptions-with-a-verb)

Example:

    <description>Creates a flag icon that lets users report inappropriate 
    content.</description>

    <name>flags</name>
    
    ...

### Include a screenshot of the tag at the end of the tag's description for reference, if applicable [](id=include-a-screenshot-of-the-tag-at-the-end-of-the-tags-description-for-refe)

For tags inside of the `liferay-portal` repo place the screenshot in the 
`liferay-portal/util-taglib/src/META-INF/images/[taglib prefix]` folder. Create 
the folder if it does not currently exist. Include the image in CDATA tags, 
pointing to the relative path of the image. For tags inside of a module repo, 
place the screenshot in the 
`module-name/module-name-taglib/src/main/tlddoc/images` folder. You will need to 
create the `tlddoc` and `images` directories. The relative path would then be 
`../images/screenshot.png`. The size of the image should be just enough to 
render the UI element 

Liferay Portal Repo Example: 

    <description>Creates a tabbed UI of section dividers that each house their own 
    content.<![CDATA[<br /><br />Example:<br />
    <img src="../images/liferay-ui/tabs.png"/>]]>
    </description>

Module Example:

    <description>Hello World <![CDATA[<img src="../images/breadcrumb.png"]]>
    </description>

### Punctuate every attribute description (sentence or fragment) with a period [](id=punctuate-every-attribute-description-sentence-or-fragment-with-a-period)

Example:

    <attribute>

        <description>The Java class name for any categories added to the 
        entry.</description>

        <name>assetCategoryClassName</name>

        ...
        
### Use complete sentences for all text following a description's initial phrase/sentence [](id=use-complete-sentences-for-all-text-following-a-descriptions-initial-phrase)

Example: 

    <attribute>

        <description>Can hold miscellaneous data. This data is not shared with 
        the browser.</description>

        <name>data</name>

        ...
        
### Preferably start &lt;attribute> descriptions using an indefinite article "A" or "An" [](id=preferably-start-attribute-descriptions-using-an-indefinite-article-a-or-an)

Example:

    <attribute>

        <description>A name for the form.</description>

        <name>formName</name>

        ...
        
### Only start attribute descriptions with definite article "The", if it's natural and, otherwise, awkward to use "A" or "An" [](id=only-start-attribute-descriptions-with-definite-article-the-if-its-natural-)

Example 1:

    <attribute>

        <description>The flagged asset's Java class name.</description>

        <name>className</name>
        
        ...

Example 2:

    <attribute>

        <description>The total number of objects in the search container.</description>

        <name>total</name>

        ...
        
### Alternatively start &lt;attribute> descriptions with a noun [](id=alternatively-start-attribute-descriptions-with-a-noun)

Example:

    <attribute>

        <description>Text to display next to the flag icon. ...

        <name>message</name>

        ...
        
### Use XML-safe text [](id=use-xml-safe-text)

Example:

    <type>Map&lt;String, Object&gt;</type>

### Mark up all code, parameter, tag, and variable references [](id=mark-up-all-code-parameter-tag-and-variable-references)

All references to tag libraries, tags, attributes, and literal values should be 
marked up as code. You must wrap the markup in a CDATA tag.

Example:

    <![CDATA[<code>someValue</code>]]>

## Attribute Patterns [](id=attribute-patterns)

You've got tons of attributes, right? Document them, following these patterns.

### The name Pattern [](id=the-name-pattern)

Follow this pattern:

    <description>A name for the component. [Any additional details]</description>

Example:

    <description>A name for the component.</description>

    <name>name</name>
    
### The boolean Pattern [](id=the-boolean-pattern)

Follow this pattern:

    <description>Whether to (do something).</description>

    <name>disabled</name>

    ...

    <type>boolean</type>
            
Example:

    <description>Whether to disable the input field.</description>

    <name>disabled</name>

    ...

    <type>boolean</type>
            
### The param Pattern [](id=the-param-pattern)

Follow this pattern:

    <description>A variable name for the component. 
    [Any additional details]</description>

Example:

    <description>A variable name for the component.</description>

    <name>param</name>

### The onClick Pattern [](id=the-onclick-pattern)

Follow this pattern:

    <description>A function to be called on the user clicking the component. 
    [Any additional details]</description>

Example:

    <description>A function to be called on the user clicking the component.
    </description>

    <name>onClick</name>

### The CssClass Pattern [](id=the-cssclass-pattern)

Follow this pattern:

    <description>A CSS class for styling [whateverItStyles].<description>

Example:

    <description>A CSS class for styling the icon's URL.</description>

    <name>linkCssClass</name>

## Tag Types [](id=tag-types)

Curious about specifying attribute types? Follow these tag type guidelines and 
examples.

### Tags that have a Boolean or Integer value must have a &lt;type> tag [](id=tags-that-have-a-boolean-or-integer-value-must-have-a-type-tag)

Tags that have a String value **DO NOT NEED** a `<type>` tag.

Follow this pattern:

    <type>boolean</type>

    <type>int</type>

Example:

    <description>Whether to disable the input field.</description>

        <name>disabled</name>

        <required>false</required>

        <rtexprvalue>true</rtexprvalue>

        <type>boolean</type>
            
### There are special cases where a &lt;type> tag is needed in addition to Booleans and Integers [](id=there-are-special-cases-where-a-type-tag-is-needed-in-addition-to-booleans-)

Java Sets:

    <type>java.util.Set</type>

Java Maps:

    <type>java.util.Map</type>
    
Java Formats:

    <type>java.text.Format</type>
    
To be safe, don't specify a `<type>` tag if you're unsure what type to use.

## Deprecated Tags [](id=deprecated-tags)

Have you made a recent update and need to deprecate a tag? Follow these 
guidelines.

### Deprecated tags with a replacement [](id=deprecated-tags-with-a-replacement)

The deprecated tag should provide a short description that includes the 
release/version of the initial deprecation, and the name of the tag that has 
replaced it.

Example:

    <description>Deprecated as of 7.0.0, replaced by 
    <![CDATA[<code>liferay-aui:nav-item</code>]]></description>

### Deprecated tags with no direct replacement [](id=deprecated-tags-with-no-direct-replacement)

The deprecated tag should provide a short description that includes the 
release/version of the initial deprecation, and should state that it has no 
direct replacement.

Example:

    <description>Deprecated as of 7.0.0, with no direct replacement</description>

## How to Generate Taglib API Docs Locally [](id=how-to-generate-taglib-api-docs-locally)

Do you want to generate the taglib API docs locally? Follow these guidelines.

### Use the Ant taglibdoc task to build taglib docs for Liferay Portal core tags [](id=use-the-ant-taglibdoc-task-to-build-taglib-docs-for-liferay-portal-core-tag)

Navigate to the `liferay-portal/util-taglib` directory in the command line and 
run `ant taglibdoc`. The docs will be generated in your 
`liferay-portal/api/taglibs` directory. Open `index.html` to view the docs.

Generate taglib docs for Liferay Portal core tags:

    ant taglibdoc

### Use the Gradle tlddoc task to build taglib docs for modules [](id=use-the-gradle-tlddoc-task-to-build-taglib-docs-for-modules)

Navigate to the `liferay-portal/modules/apps/module-name/module-name-taglib` 
directory in the command line and run `../../../../gradlew tlddoc`. The docs 
will be generated in your `module-name-taglib/build/docs/tlddoc` directory. Open 
`index.html` to view the docs.

Generate taglib docs for modules:

    ../../../../gradlew tlddoc
    
## How to Generate Taglib Jars Locally [](id=how-to-generate-taglib-jars-locally)

So you're trying to jar up your API docs locally. Follow the guidelines below.

### Use the Gradle jarTlddoc task to generate jar files for your modules [](id=use-the-gradle-jartlddoc-task-to-generate-jar-files-for-your-modules)

Navigate to the `liferay-portal/modules/apps/module-name/module-name-taglib` 
directory in the command line and run `../../../../gradlew jarTlddoc`. The 
`com.liferay.modulename.taglib-version-taglibdoc.jar` file will be generated in 
your `liferay-portal/tools/sdk/dist` directory, containing the docs information, 
along with any images you included as well.

Generate jar files for module taglib docs:

    ../../../../gradlew jarTlddoc
