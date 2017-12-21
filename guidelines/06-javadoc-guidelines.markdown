# Javadoc Guidelines

We welcome your API documentation contributions to Liferay's Java source code.
Following these guidelines helps to ensure that Liferay classes are documented
sufficiently and consistently. The guidelines also explain how to leverage our
Javadoc tools and submit your contributions.

The sections [Module Comments](#module-comments) and
[Package Comments](#package-comments) provide you with instructions on how to
provide a general overview of modules and packages. The sections
[Class Comments](#class-comments) and [Method Comments](#method-comments)
provide you with simple instructions on how to describe Java classes and
methods. Subsequent sections guide you in describing the
[most important tips](#top-tips) for writing Javadoc,
[formatting and building Javadoc](#formatting-and-building-javadoc), and
[submitting your contributions](#submitting-javadoc). This article covers the
main points and most important rules to follow. If you're interested in an
extensive amount of examples and more advanced information, visit the
[Advanced Javadoc Guidelines](07-advanced-javadoc-guidelines.markdown).

## Module Comments

You can provide documentation for a module by writing HTML in an `overview.html`
file and inserting it in the module's `/src/main/java` directory. When the
module's Javadoc is generated for its JAR file, the module's description will be
available when viewing the module's HTML.

## Package Comments

You can provide documentation for Java packages by writing HTML in a
`package.html` file and inserting it in the package you'd like to describe. When
Liferay's documentation is generated on
[docs.liferay.com](https://docs.liferay.com/), the package description will be
available when viewing the package. For example, view the
[com.liferay.counter.kernel.model](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/counter/kernel/model/package-summary.html)
package's description.

## Class Comments

The following information should be present in the following order in the class
comment:

- Initial class description (paragraph/sentence)
    - **First sentence** - Should describe the class clearly and concisely.
		(**required**)
    - **Followup sentences** - Support the first sentence with important points
	  about the class. Note, these sentences show up in the description for the
	  class but do not show up in the summary table for the class. (optional)
- Detailed class description (additional paragraph(s))
    - Provide more information on the class's purpose, abilities, and general
		 role. For some classes (simple utility classes for instance) this
		 additional information is not necessary if the initial paragraph provides
		 an adequate description.
    - Usage examples or `@link` tags to where the class can be seen in use.
- [@author tags](07-advanced-javadoc-guidelines.markdown#author-tags) for each
  author of the class, from first to most recent. (**required**)
- [@see tags](07-advanced-javadoc-guidelines.markdown#see-tags) to other closely
  related classes whose Javadocs gives the reader a clearer picture of the
  purpose of this class.
- [@since tags](07-advanced-javadoc-guidelines.markdown#since-tags). (as
  applicable)
- [@deprecated tags](07-advanced-javadoc-guidelines.markdown#deprecated-tags).
  (as applicable)

For more detailed information and examples for writing class descriptions, see
the
[Class Descriptions](07-advanced-javadoc-guidelines.markdown#class-descriptions)
section. A simple example class comment is provided below:

Example (class comment):

```java
/**
 * Represents an example class. If a basic description requires more than one
 * sentence, include it in the first paragraph.
 *
 * <p>
 * Example of a second paragraph. Note the blank line between the first <p> tag
 * and the end of the first paragraph.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see	   BigExample
 */
public class Example {
    ...
}
```

## Method Comments

The following information should be present in the Javadoc comment on each
method:

- A short, one sentence description of the method.
- Additional sentences and/or paragraphs providing more information on the
  method's purpose and function. Any information the user of the method would
	find useful should be included here, including special requirements,
	circumstances where it should/should not be used, etc. For many methods, this
	additional explanation is not necessary, particularly for getters and setters.
- Usage examples if necessary and possible. Trivial methods don't need examples,
  and if an example would be extremely large, simply *@link* to a place the
	method is used.
- If the method is only used in one or two places, *@link* to the methods it is
  called from. This helps later developers to understand its role in Liferay.
- No need to mention matching a company ID parameter. It should be understood
  and is not worth cluttering the description.

The following information should always be present in the following order in the Javadoc tags for the method:

- [@param](07-advanced-javadoc-guidelines.markdown#param-tags) - The method
  parameters, in order, with descriptions.
- [@return](07-advanced-javadoc-guidelines.markdown#return-tags) - All possible
  return values, including `null`. If the method is void, do not include this.
- [@throws](07-advanced-javadoc-guidelines.markdown#throws-tags) - The
  exceptions the method can throw, in order, with explanations of what would
  trigger them.
- [@see](07-advanced-javadoc-guidelines.markdown#see-tags-for-methods) - (as
  applicable)
- [@since](07-advanced-javadoc-guidelines.markdown#since-tags-for-methods) - (as
	applicable)
- [@deprecated](07-advanced-javadoc-guidelines.markdown#deprecated-tags-for-methods) -
  (as applicable)

For more detailed information and examples for writing method descriptions, see
the
[Method Descriptions](07-advanced-javadoc-guidelines.markdown#method-descriptions)
section. A simple class description is provided below:

Example (method comments):

```java
/**
 * Returns the localized preferences values for the key, optionally using the
 * default language if the no localization exists for the requested
 * language.
 *
 * @param  preferences the preferences container
 * @param  key the preferences key
 * @param  languageId the primary key of the language
 * @param  useDefault whether to use the default language if no localization
 *         exists for the requested language
 * @return the localized preferences values. If <code>useDefault</code> is
 *         <code>false</code> and no localization exists for the requested
 *         language, an empty array will be returned.
 * @throws PortalException if a portal exception occurred
 */
public String[] getPreferencesValues(
        PortletPreferences preferences, String key, String languageId,
        boolean useDefault);
    throws PortalException;
```

### Method Comments for Interfaces and Sub-Classes

- **Interface method Javadoc** is *always* required.
- **Sub-class Method Javadoc** is required for methods of sub-classes that have
  differences from their parent class(es) that should be noted. Some examples
	are sub-class methods that have different behavior, different side-effects,
	and/or different requirements of their parameters. In most cases, the Javadoc
	must be manually transferred from the interface or super-class to the
	sub-class if you think the descriptions should stay the same. If the interface
	or super-class is in the same module as the sub-class, the Javadoc is
	transferred automatically.

## Top Tips

1.  If you're new to Javadoc, familiarize yourself with the
    [official Javadoc style guide](http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html).
2.  Starting with an *action word*, describe what each [class](#class-comments)
    and [method](#method-comments) does.
3.  Avoid just restating the class or method name (e.g., Avoid
    *updateLocalization(...)* &rarr; *Updates the localization* **OR** *@param
    key the key*).
4.  Describe the most important details in the first sentence, because it is the
    only one shown in the class/method summary.
5.  Include all relevant tags
    ([@param](07-advanced-javadoc-guidelines.markdown#param-tags),
    [@return](07-advanced-javadoc-guidelines.markdown#return-tags), etc.) for
    each method; without them, the method Javadoc is incomplete.
6.  Start a method description with *Returns*, if the method returns a value.
7.  Don’t explicitly refer to collections in descriptions (e.g.,
    *list of articles*). Use, instead, the plural (e.g., *the articles*) or *all
    the* (e.g., *all the articles*).
8.  When referring to another class, try to either link using *{@link entity}*
    (e.g., *{@link com.liferay.osb.lcs.model.LCSClusterNode}*) or refer to the
    class in common terms (e.g., *LCS cluster node*). The first alternative
    provides direct access to the class's Javadoc, the second alternative makes
    for smooth reading.
9.  Avoid referring explicitly to parameters by name; instead, refer to them in
    general terms (e.g., *class name ID* instead of *<code>classNameId</code>*).
10. Refer to primary keys explicitly, even when the parameter is an ID. For
    example, use *the primary key of the something* instead of *the ID of the
    something*.
11. Begin
    [boolean parameter](07-advanced-javadoc-guidelines.markdown#parameter-boolean)
    descriptions with *whether*.
12. Punctuate every class and method description (sentence or phrase) with a
    period.
13. Only punctuate an initial description (sentence or phrase) for a method tag
    [@param](07-advanced-javadoc-guidelines.markdown#param-tags),
    [@return](07-advanced-javadoc-guidelines.markdown#return-tags), or
    [@throws](07-advanced-javadoc-guidelines.markdown#throws-tags) if it's
    followed by a sentence(s). Write followup text in complete sentences.
14. Describe exceptions (e.g.,
	  [@throws](07-advanced-javadoc-guidelines.markdown#throws-tags)) in past
    tense.
15. Only include a class's package in an *@see* and *@link* tag if the class is
    in another package. Refer to the
    [Javadoc Linking](07-advanced-javadoc-guidelines.markdown#javadoc-linking)
    subsection for comprehensive rules and examples on linking to other
    methods/classes.
16. Don't wrap the first paragraph of a Javadoc comment with paragraph tags. Do
    wrap subsequent paragraphs with paragraph tags (e.g.,
    `<p>second paragraph</p>`).
17. Conform Javadoc to 80 columns, if possible.

Terrific! You're off to a great start to writing Javadoc. The following sections
describe the most important aspects of writing class comments and method
comments, using the Javadoc Formatter, and submitting your contributions.

## Formatting and Building Javadoc

Before committing any new or modified Javadocs, run `ant format-javadoc` on your
code first! This will automatically wrap your comments to the proper width,
format html tags, and line up Javadoc tags. The formatter will also propagate
your Javadoc changes to the JSON interface for the class.

1. Compile the code.

    To compile the Portal Core, do this:

    ```
    cd liferay-portal
    ant compile
    cd portal-impl
    ant compile compile-test
    ```

2. Run the Javadoc Formatter.

    To format Javadoc in a module under `liferay-portal/modules/`, execute this
    in the module's root folder:

        gradlew formatJavadoc

    The optional arguments you can pass are:

    - `-DformatJavadoc.limit="SomeClassName1,SomeClassName2,com.liferay.portal.**` -
    Runs the formatter on more than one class/package. Limits must be wrapped in
    double quotes. This can be combined with the following two options, or
    completely removed (which runs the formatter on the entire code base).
    - `-DformatJavadoc.init=true` - Inserts place holders for all comment
		elements.
    - `-DformatJavadoc.update=true` - Inserts place holders for comment elements
    that are not up to date with the current method signatures. For example, the
    formatter will add comment placeholders for parameters that are in method
    signatures but not present in the Javadoc comments for those methods.
    - `-DformatJavadoc.generate.xml` - Generates an XML document of the Javadoc
		that shows for a module's web services API page.

    To format Javadoc in a class you've edited in a Liferay Portal Core module
    (e.g., in `portal-kernel`, `portal-impl`, etc), run:

		    ant format-javadoc -Dlimit=SomeClassName

    There are several other alternative options for invoking basic Javadoc
    formatting and updates.

    - `-Dlimit="SomeClassName1,SomeClassName2,com.liferay.portal.**"` - Runs the
    formatter on more than one class/package. Limits must be wrapped in double
	  quotes. This can be combined with the following two options, or completely
	  removed (which runs the formatter on the entire code base).
    - `-Dinit=true` - Inserts place holders for all comment elements.
    - `-Dupdate=true`- Inserts place holders for comment elements that are not
    up to date with the current method signatures. For example, the formatter
    will add comment placeholders for parameters that are in method signatures
    but not present in the Javadoc comments for those methods.

3. Building Javadoc (optional)

    To optionally build a module's Javadoc HTML to the module's `build/docs/`
    folder to see what it looks like, execute this:

        gradlew javadoc

    Javadoc HTML is only generated for modules that export their packages. This
    is because modules that do not export their packages are not considered
    public API, so there's no need to generate them. To check if your module
    exports packages, open its `bnd.bnd` file in the root folder and verify it
    has the following syntax:

        Export-Package:\

    If it doesn't but you'd still like to generate HTML to see what it looks
    like, add the above syntax followed by the packages you want to generate.

## Submitting Javadoc

For more information on submitting Javadoc, see the
[Javadoc Submission Process](05-javadoc-submission-process.markdown) article.

To request **re-adding** Javadoc or comments that have been removed from a file,
please open an [LRDOCS](https://issues.liferay.com/browse/LRDOCS) JIRA ticket:

- **Issue Type:** API
- **Summary:** Re-add Javadoc for .... (name or prefix of class/file)
- **Component:** Area the Javadoc pertains to
		files
- **Affected Release:** 7.0.x, 6.2.x, 6.1.x
- **Description:** Include the *commit number* of the original Javadoc commit
  and/or the Javadoc removal commit

For more extensive examples and comprehensive guidelines, visit the
[Advanced Javadoc Guidelines](07-advanced-javadoc-guidelines.markdown) article.