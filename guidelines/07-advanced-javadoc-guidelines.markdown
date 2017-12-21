# Advanced Javadoc Guidelines

## Table of Contents

1. [Class Descriptions](#class-descriptions)
2. [Class Javadoc Tags](#class-javadoc-tags)
    - [@author tags](#author-tags)
    - [@see tags](#see-tags)
    - [@since tags](#since-tags)
    - [@deprecated tags](#deprecated-tags)
3. [Method Descriptions](#method-descriptions)
    - [Initial Method Descriptions](#initial-method-descriptions)
    - [Detailed Method Descriptions](#detailed-method-descriptions)
    - [More on Method Descriptions](#more-on-method-descriptions)
4. [Method Javadoc Tags](#method-javadoc-tags)
    - [@param tags](#param-tags)
    - [@return tags](#return-tags)
    - [@throws tags](#throws-tags)
    - [@see tags for methods](#see-tags-for-methods)
    - [@since tags for methods](#since-tags-for-methods)
    - [@deprecated tags for methods](#deprecated-tags-for-methods)
5. [Javadoc Linking](#javadoc-linking)
6. [Formatting Tags](#formatting-tags)
7. [Examples](#examples)
    - [Class: Initial and detailed description](#class-initial-and-detailed-description)
    - [Constructor: with parameters](#constructor-with-parameters)
    - [Method: Get by primary key](#method-get-by-primary-key)
    - [Method: Deletes/Removes something](#method-deletesremoves-something)
    - [Method: Get/search/count matching a single field](#method-getsearchcount-matching-a-single-field)
    - [Method: Get/search/count matching multiple fields](#method-getsearchcount-matching-multiple-fields)
    - [Method: Get/search/count by field-to-keyword matching](#method-getsearchcount-by-field-to-keyword-matching)
    - [Method: Get/search/count by Combined Field Value and Field-to-Keyword Matching](#method-getsearchcount-by-combined-field-value-and-field-to-keyword-matching)
    - [Method: Returning a boolean](#method-returning-a-boolean)
    - [Method: Returning a count](#method-returning-a-count)
    - [Method: Returning a collection](#method-returning-a-collection)
    - [Method: Returning an ordered range of values](#method-returning-an-ordered-range-of-values)
    - [Method: Returning ordered values NOT as a range of values](#method-returning-ordered-values-not-as-a-range-of-values)
    - [Method: Returning a ranged collection](#method-returning-a-ranged-collection)
    - [Method: Describing overloaded methods](#method-describing-overloaded-methods)
    - [Method: Password Policy Methods](#method-password-policy-methods)
    - [Method: Uses a property](#method-uses-a-property)
    - [Parameter: Primary key](#parameter-primary-key)
    - [Parameter: Attribute/field](#parameter-attributefield)
    - [Parameter: Boolean](#parameter-boolean)
    - [Parameter: ClassNameId](#parameter-classnameid)
    - [Parameter: Entity (primary)](#parameter-entity-primary)
    - [Parameter: ServiceContext](#parameter-servicecontext)
    - [Parameter: And/or operator](#parameter-andor-operator)
    - [Parameter: Refer reader to more information](#parameter-refer-reader-to-more-information)
    - [Field](#field)
    - [Return: Ordered collection](#return-ordered-collection)
    - [Return: Significantly varying values](#return-significantly-varying-values)

## Class Descriptions

Here are some rules of thumb for initial class descriptions:

- **Start with a verb** - Whenever possible, start an initial class description
  with verb to describe the purpose of the class.

    Example ([Localization](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/util/Localization.html)
    interface): `Stores and retrieves localized strings from XML, and provides
    utility methods for updating localizations from JSON, portlet requests, and
    maps.`

- **Never begin with *This class*** - Never begin the description with *This
  class* or anything similar. However, it is acceptable to use this wording in
  later sentences/paragraphs.

- **For a model interface and its class** - If some explanation is necessary to
  distinguish an interface from its class, follow the format below (e.g.,
  `Model.java`):

    ```
    Provides the base model interface for the User service. Represents a row in
    the <code>User</code> database table, with each column mapped to a property
    of this class.
    ```

    Likewise, the description of the implementation could be as follows (e.g.
    `classModelImpl.java`):

    ```
    Provides the base model implementation for the User service. Represents
    a row in the <code>User</code> database table, with each column mapped to a
    property of this class.
    ```

- **For model classes (model entities)** - describe the methods and entities
  represented and manipulated in the class. Use the following format (taken from
  `classUserImpl.java`):

    ```
    Represents a portal user, providing access to the user's contact
    information, groups, organizations, teams, user groups, roles, locale,
    timezone, and more.
    ```

    In the above example:

    - State what entity the class represents (*Represents a portal user,*)
    - Describe what the entity provides (*providing access to the user's &lt;summary of methods>*).

- **For service implementations (ServiceImpls)**

    Use these patterns:

| Class Type | Initial Description Pattern
| :--------- | :--------------------------
| LocalServiceImpl | Provides the local service for *&lt;summary of methods using actions ending in "ing">&lt;entities (plural)>*.
| ServiceImpl | Provides the remote service for *&lt;summary of methods using actions ending in "ing">&lt;entities (plural)>*.

Example from `DLAppLocalServiceImpl`:

```
/**
 * Provides the local service for accessing, adding, deleting, moving,
 * subscription handling of, trash handling of, and updating document library
 * file entries, file ranks, and folders. All portlets should interact with
 * the document library through this class or through DLAppService, rather
 * than through the individual document library service classes.
 *
 * ...
```

In the above example:

- the **method summary** states the service is for "accessing, adding, ... and
  updating" its entities.
- the **entities** acted on are document library file entries, file ranks, and
  folders.

    The method summary consists of a list of "ing" verbs in alphabetical order.
    You can derive these verbs from your method names. Note, you can use
    "accessing" to cover all the getters, fetchers, and search methods. And
    "updating" is typically adequate for covering the setters. But feel free to
    use the verbs that match your methods best.

## Class Javadoc Tags

### @author tags

Use `@author` tags for each author of the class, from first to most recent.

### @see tags

Use `@see` tags to link other closely related classes whose Javadocs would give
the reader a clearer picture of the purpose of the class. This often includes
the parent class, any important ancestors, and the primary classes that use this
class. An example of an `@see` tag declaration is the following:

    @see com.liferay.bookmarks.model.impl.BookmarksEntryImpl

You cannot use the `@see` tag to refer to classes in a Liferay module (unless
the referred class is located in the same module). Therefore, to reference a
class in another module, make sure to refer to it in the class description.

### @since tags

The `@since` tag should always be used in cases where the class is a class that
replaces another class or where the class has been moved to a new package.
Deprecations due to moving a class or replacing a class necessitate respective
use of a @deprecated tag in the old class and a @since tag in the new class. For
specifying the version, see the
[Understanding Liferay's Releases](https://dev.liferay.com/discover/deployment/-/knowledge_base/6-2/understanding-liferays-releases)
section.

The `@since` tag can optionally be used when the class is newly introduced.

The preferred format for common `@since` messages are listed below.

| Reason | @since Description
| :----- | :-----------------
| Replaced by former class | @since *version*, replaced {@link *fully qualified class*}
| Moved to different package | @since *version*, moved from {@link *fully qualified class*}
| New class | @since *version*

For a working example, see
[StagedModelDataHandlerRegistryUtil](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/StagedModelDataHandlerRegistryUtil.html).

### @deprecated tags

The `@deprecated` tag should provide a short description that includes the
release/version of initial deprecation, why the class was deprecated, and a link
to what should be used in its place. For specifying the version, see the
[Understanding Liferay's Releases](https://dev.liferay.com/discover/deployment/-/knowledge_base/6-2/understanding-liferays-releases)
section.

Note, if a class deprecation was due to moving or replacing a class, then the
comments for the new class should include an `@since` tag referencing the old
class. See the former section for details.

The preferred format for common `@deprecated` messages are listed below.

| Deprecation Reason | @deprecated Description
| :----------------- | :----------------------
| Replaced | As of *version*, replaced by {@link *fully qualified class*}
| Moved to different package | As of *version*, moved to {@link *fully qualified class*}
| Some other reason | As of *version*, because *of some reason*
| Not replaced | As of *version*, with no direct replacement

**Important:** You cannot link to classes/methods in Liferay modules unless
you're providing a class/method link in the same module it resides in.
Therefore, if you need to refer to a class in your `@deprecated` tag, you'll
need to follow a slightly different pattern. See the
[Javadoc Linking](06-javadoc-guidelines.markdown#javadoc-linking) section for
more details.

## Method Descriptions

### Initial Method Descriptions

Always use a verb to describe the method. The preferred format for the
descriptions of several common methods are listed below.

| Method Type | Description
| :---------- | :----------
| constructor(value) | Constructs a new ... with the *value*.
| setSomething(value) | Sets the *something of this thing*.
| getSomething() | Returns the *something of this thing*.
| getSomethings() | Returns the *somethings of this thing*. (Note, do **not** refer to collection type; instead, refer to the something in plural form.)
| setSomething(boolean) | Sets whether *this thing is something*.
| isSomething() | Returns &lt;code>true&lt;/code> if *this thing is something*.
| deleteSomething() | Deletes the *something*.

There are two general rules for method descriptions:

1. When referring to the current instance, say "this something".

    Example: `Returns <code>true</code> if this person is an administrator.`

2. When referring to parameters, use "the" instead of "a".

    Example: `Returns the localized preferences value for the key.`

**Try to avoid simply restating the name of the method in the description.** In
some cases, such as with getters/setters, this is fine, but if it is not
immediately obvious what a method does, it needs more explanation.

For example, the following is **NOT** a good example of a method description:

```java
/**
 * Updates the localization.
 */
public String updateLocalization(String xml, String key, String value, String requestedLanguageId);
```

Write the following instead:

```java
/**
 * Updates the localized string for the language in the localizations XML.
 */
public String updateLocalization(String xml, String key, String value, String requestedLanguageId);
```

### Detailed Method Descriptions

If there is both a getter and setter for the same property/collection on the
object, place the detailed description on the setter/add method. The getter
method should have a one sentence description, and `@see` the method with more
detail.

If there are multiple overloaded methods of the same name, each method should
have a complete description of itself. Do not `@see` the other overloaded forms.

### More on Method Descriptions

#### Start the description with `Returns ...` for a method used primarily for the value it returns.

Getters should always follow this rule. Methods that involve returning a count
or a search result should typically follow this rule as well.

```java
/**
 * Returns the organization with the primary key.
 *
 * ...
 */
public Organization getOrganization(long organizationId)
```

- Distinguish between overloaded methods

    Distinguish between overloaded methods by uniquely describing each method in
    the first sentence of the method description. This helps developers spot out
    which of the overloaded methods they want to use when viewing the Javadoc
    summary of the methods for the class. Remember, only the first sentence of
    the method description shows in the method summary. The remaining sentences
    (including sentences of the same paragraph following the first sentence) are
    available in the full length method description.

    ```java
    /**
     * Returns all immediate subfolders of the parent folder.
     * ...
     */
     public List<Folder> getFolders(long repositoryId, long parentFolderId)
    ```

    and

    ```java
    /**
     * Returns all immediate subfolders of the parent folder, optionally
     * including mount folders for third-party repositories.
     * ....
     */
     public List<Folder> getFolders(long repositoryId, long parentFolderId, boolean includeMountFolders)
    ```

- When describing a specific parameter, refer to it with "the", not "a" or "given".

- By using "the" (e.g., "the parent folder") it is assumed the reader will
  understand we are referring to an entity related to a parameter (e.g. the
  folder associated with parameter `parentFolderId`).

    ```java
    /**
     * Returns all immediate subfolders of the parent folder.
     *
     * ...
     * @param  parentFolderId the primary key of the folder's parent folder
     * ...
     */
     ```

#### Multiple Sentences in Initial Method Description

Unless the details describing a method warrant a paragraph, simply add the
sentence(s) directly after the initial method description.

```java
/**
 * Returns the parent organizations in order by closest ancestor.  The list
 * starts with the organization itself.
 *
 * ...
 */
public List<Organization> getParentOrganizations(long organizationId)
```

**Important:** In the HTML generated from the Javadoc, only the initial method
description sentence will appear in the *method summary* table. But the
sentences that follow the initial method description will show in the method
details.


## Method Javadoc Tags

### @param tags

`@param` tags should provide a short description of what the parameter is or
what it is for, as well as any special requirements. If more detail is needed
than what can fit in one or two sentences, place it in the method description
instead. Never start the parameter description with a capital letter, and always
refer to the parameter with "the" rather than "a". Additionally, it is
unnecessary to explain what the parameter is for if this was already explained
in the method description.

The preferred format of several common parameter types are shown below. Be sure
to include the description text found in bold.

| Param case | Param name convention | @param Description
| :--------- | :-------------------- | :-----------------
| An entity's ID | *entity ID* | the primary key of the *entity*
| An entity's attribute or field | *paramName* | the *entity's attribute*
| An involved entity (possibly the method's subject) | *classname* (lowercase) | the (*refer to the entity in layman's terms in lower case. Do not refer to the uppercase classname.*)
| A boolean parameter | *paramName* | whether *to do something or some condition is true*

Bad example:

```java
/**
 * Returns the name of the role.
 *
 * @param roleId the primary key of the role **whose name to get**
 * @return the name of the role
 */
String getRoleName(long roleId)
```

Good example:

```java
/**
 * Returns the name of the role.
 *
 * @param roleId the primary key of the role
 * @return the name of the role
 */
String getRoleName(long roleId)
```

Sometimes, a parameter description may require more than one phrase. Any
additional content following the initial parameter phrase description must be
written in complete sentences, followed by a period. For example:

```
@param  trusted whether to bypass permission checks. In third-party
        repositories, this parameter may be ignored.
```

To show possession, use an apostrophe rather that ending a sentence with a
preposition. The only exception is that when referring to a primary key, always
write "the primary key of the user", not "the user's primary key". Example:

```java
/**
 * @param  creatorUserId the primary key of the user's creator
 * @param  companyId the primary key of the user's company
 * @param  name the user's name
...
 * @param  groupIds the primary keys of the user's groups
 */
public User addUser(long creatorUserId, long companyId, name, ... , long[] groupIds, ...)
```

### @return tags

The `@return` tag should provide a short description of what the method returns,
including any special values. If a method returns `null`, this should be
explicitly stated. Otherwise, the reader should assume the method will always
return its designated type.

As a rule of thumb, describe the return value in simple terms. Leave to method
and parameter descriptions details on what can cause subtle variations in values
returned.

The preferred format for the return descriptions of several common method types
are shown below.

| Method Type | @return Description
| :---------- | :------------------
| getSomething() | the *something of this thing*
| getSomethings() | the *somethings of this thing* (Note, do not refer to the collection type; instead, refer to the something in plural form)
| changeSomething(thing) | the *changed thing*, or &lt;code>null&lt;/code> if the *change failed*
| isSomething() | &lt;code>true&lt;/code> if *something*; &lt;code>false&lt;/code> otherwise

Note using the word "the" (not "a" or "an") in describing the returned item.

#### Abbreviating return description when extensive matching done

If a method uses more than two criteria items to match an entity or is awkward
to consolidate into a small sentence, just use the phrase "the matching
somethings" to start of the description of the returned entities.

```
/**
 * Returns an ordered range of all the file entries in the group starting at
 * the root folder that are stored within the Liferay repository.
 * ...
 * @return the range of matching file entries ordered by the comparator
 * ...
 */
 ```

#### Parameter Side Effects

Only include a message about the side effects of a parameter in the `@return`
description if the parameter can have a side effect apart from the main purpose
of the method. For instance, if the primary purpose of a parameter was to tell
the method to use an alternate data source, but a failure in using the
alternative data source caused the method to return `null` rather than throw an
exception, say in the `@param` the parameter can change the data source, and
then say in the `@return` that a failure in using the alternative data source
could cause `null` to be returned, and say in the `@throws` the exception
wouldn't be thrown under this circumstance.

### @throws tags

`@throws` should give a short description of when an exception will be thrown.
They should always be written in the past tense.

It is often difficult to describe exactly what will cause certain exceptions to
be thrown, particularly in the case of `SystemException`. For this exception,
simply use the following generic description:

```
@throws SystemException if a system exception occurred
```

If you do know what could cause an exception, briefly describe the
circumstances. For example:

```
@throws PortalException if a user with the primary key could not be found
```

If you describe what could cause an exception, but cannot provide an exhaustive
list of all of the possible causes, simply end your comment generalizing that
the type of exception could have occurred. For example:

```
@throws PortalException if a user with the primary key could not be
        found, if the XSD was not well-formed, or if a portal exception
        occurred
```

By definition, system exceptions are only thrown when something has gone wrong
with the system, such as a database server going offline, a file system becoming
corrupted, or any number of other impossible to predict things going wrong. This
is the reason the completely generic "if a system exception is thrown" message
is used for system exceptions. All the information that we can really give a
developer about the reasons for a system exception being thrown is available in
the documentation for `SystemException`, so there is no need to duplicate this
information in essentially every method in Liferay.

Descriptions of portal exceptions should include situations that can be
predicted and explained to some extent. For example, methods that take a primary
key will generally throw a PortalException when a record with that primary key
cannot be found. Other methods will throw a PortalExceptions on validation
errors.

- **Key Not Found**

    Pattern: `if a <entity1> [or entity2] with the primary key could not be found`

- **Invalid Information**

    Pattern: `if the <entity's> information was invalid`

- **Start New Reasons with *if*:** Start each new type of reason for the
  exception with the word if.

    Pattern: `@throws PortalException if <reason type1> or if <reason type2>`

```java
/**
 * ...
 * @throws PortalException if a creator or parent organization with the
 * primary key could not be found or if the organization's information was
 * invalid
 * ...
 */
public Organization addOrganization(long userId, long parentOrganizationId, … , ServiceContext serviceContext)
throws PortalException, ...
```

### @see tags for methods

Only use `@see` when there are more details about something that could be gained
by reading the docs for another method or class. An example of an `@see` tag
declaration is the following:

```
@see com.liferay.portal.service.persistence.BasePersistence#clearCache()
```

You cannot use the `@see` tag to refer to methods in a Liferay module (unless
the referred method is located in the same module). Therefore, to reference a
method in another module, make sure to refer to it in the method description.

### @since tags for methods

The `@since` tag should be used in cases where the method is a result of
renaming a former method. It can optionally be used when the method is newly
introduced to an existing class.

Note, deprecations due to renaming a method should include both the use of
`@deprecated` in the old method and `@since` in the new method.

The preferred format for common `@since` messages are listed below.

| Reason | @since Description
| :----- | :-----------------
| Renamed a former method | @since *version*, rename of {@link #*someMethod*()}
| Generalization | @since *version*, replaced {@link #*someMethod*()}
| New method | @since *version*

### @deprecated tags for methods

The `@deprecated` tag should provide a short description that includes the
release/version of initial deprecation, why the method was deprecated, and a
link to what should be used in its place. For specifying the version, see the
[Understanding Liferay's Releases](https://dev.liferay.com/discover/deployment/-/knowledge_base/6-2/understanding-liferays-releases)
section.

Note, if a method deprecation was due to moving or replacing a method, then the
comments for new method should include an `@since` tag referencing the old method.
See the previous section for details.

The preferred format for several common `@deprecated` messages are listed below.

| Deprecation Reason | @deprecated Description
| :----------------- | :----------------------
| Replaced | As of *version*, replaced by {@link *fully qualified path or #method name*}
| Renamed within same class | As of *version*, renamed to {@link #*someMethod*()}
| Generalized in another method within same class | As of *version*, replaced by the more general {@link #*someMethod*()}
| Moved to different package | As of *version*, moved to {@link *fully qualified path*}
| Some other reason | As of *version*, because *of some reason*

**Important:** Before deprecating a method, make sure no other methods in
`liferay-portal` call it. Using the Call Hierarchy feature from Eclipse is
helpful in making these verifications.

Note: In special cases, the method can be deprecated while still being called by
another method. In particular, when deprecating a service method, check callers
will often be calling it indirectly via its utility class.

**Important:** Test links to each deprecation replacement (e.g. links to
replacement method) to make sure the link works and that it is referring to the
intended target.

For a working example, see
[UserImpl.getDisplayURL(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-impl/com/liferay/portal/model/impl/UserImpl.html#getDisplayURL(java.lang.String,%20java.lang.String)).

**Important:** You cannot link to classes/methods in Liferay modules unless
you're providing a class/method link in the same module it resides in.
Therefore, if you need to refer to a method in your `@deprecated` tag, you'll need
to follow a slightly different pattern. See the
[Javadoc Linking](#javadoc-linking) section for more details.

## Javadoc Linking

Most Javadoc errors result from the incorrect usage of the `@see` and `@link`
tags. Make sure to follow the following rules when using links in your Javadocs:

- `@see` tags should only be used directly below the class/method description,
  not within.
- `@link` tags should be used within descriptions, and surrounded by `{}` (e.g.,
  `{@link DDMStructureServiceImpl}`.
- You **MUST** specify the class' full package path if the class you're
  referencing is not located in the same package as the class you're
  documenting.
- Same thing goes for classes in same package, but in different class.
- Do not include full package paths unless it is necessary.
- You can check if the full package path is needed by simply hovering your mouse
  over the class. If an info box appears, you're correctly specifying the link.
- There are cases where you've specified the incorrect params, but IDE still
  recognizes the link. Always double check, because although IDE recognizes the
  method-- Javadoc errors could still be present.
- `@link` examples:

    **Within same class:**

    ```
    {@link #updateStructure(long, long)}
    ```

    **Diff class, same package:**

    ```
    {@link DDMStructureLocalServiceImpl#updateStructure(long, long)}
    ```

    **Diff package:**

    ```
    {@link com.liferay.portlet.dynamicdatamapping.service.impl.DDMStructureLocalServiceImpl#updateStructure(long, long)}
    ```

    **Field example:**

    ```
    {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}
    ```

**Important:** You cannot link to classes/methods that reside outside of a
particular area. Areas in Liferay's source code are the sections that are JAR'd
up and deployed to the Liferay container. For instance, `portal-impl` and
`portal-kernel` are separate JARs, so classes/methods in these JARs can not link
to each other. The same rule goes for all modules; since each module is a
separate JAR, linking cannot be done outside a particular module. Because of the
inability to successfully link to other areas of Liferay's source code, you must
specify where the referenced code is located, and rely on readers to manually
find it.

In some cases, there are many different methods in a class with the same name
and different parameters. If this is the case, specify the parameters when
referencing the method. If the method name in the class is unique, there is no
need to list the method's parameters.

When specifying a module that a class/method resides in, be sure to refer to the
module by its bundle symbolic name found in its `bnd.bnd` file (e.g.,
`com.liferay.dynamic.data.mapping.api`, which is declared
[here](https://github.com/liferay/liferay-portal/blob/7.0.0-ga1/modules/apps/forms-and-workflow/dynamic-data-mapping/dynamic-data-mapping-api/bnd.bnd#L2)).
When referring to non-module areas where code resides, you should specify it in
the way it is displayed on [docs.liferay.com](https://docs.liferay.com/) (e.g.,
`portal-kernel`).

Below are examples of how to specify code outside the current class's area.

Suppose you want to specify the `DDMFormValues` class located in the
`com.liferay.dynamic.data.mapping.api` module from another class outside the
current module. You would specify it as the following:

```
See <code>DDMFormValues</code> in the <code>com.liferay.dynamic.data.mapping.api</code> module
```

To specify a method in another module:

```
See <code>BlogsEntryStagedModelDataHandler#getClassNames</code> in the <code>com.liferay.blogs.service</code> module
```

If you'd like to specify a class in another module mid-sentence, you could refer
to it like this:

```
<code>DDMFormValues</code> (in the <code>com.liferay.dynamic.data.mapping.api</code> module)
```

Referring to code that is in a different non-module area is very similar (e.g.,
`com.liferay.portal.impl`, `com.liferay.portal.kernel`):

```
See <code>DLFileVersionPolicyImpl#isKeepFileVersionLabel(long, long, boolean)</code> in <code>com.liferay.portal.impl</code>
```

Since deprecations typically use links to refer to replaced code, this format
for code outside the area is applied for `@deprecated tags`, as well:

```
@deprecated As of 7.0.0, replaced by <code>BlogsEntryStagedModelDataHandler#getClassNames(String, boolean)</code> in the <code>com.liferay.blogs.service</code> module
```

Notice that the `@see` and `@link` tag are never used. Configuring a link for a
class/method that cannot be found results in a Javadoc error during compilation
of the portal; therefore, refrain from using "linking" tags in these scenarios.

## Formatting Tags

- All HTML tags (except for `<b>`, `<i>`, `<code>`, etc.) should be on a line by
  themselves.
    - For an example of using table tags, see `com.liferay.portal.kernel.annotation.AnnotationLocatorTest`.
        - Note: When using table tags, you must insert a line return between
          `<table>` and the start of the content nested within. The same rule
          also applies before the ending `</table>` tag.
        - For an example of using list tags, both ordered and unordered, see the
          class comment example below.
- Unordered and ordered lists in comments should be represented using `<ul>` or
  `<ol>` respectively. The `<ul>` and `</ul>` or `<ol>` and `</ol>` tags should
  each be on a line of their own. List items should each be placed on their own
  line, and the `<li>` and `</li>` tags should be on their own line immediately
  before and after the item text.
- Unordered and ordered lists must not be nested within paragraph (`<p>``</p>`)
  tags.
- Tables (`<table>`...`</table>`) must be nested within paragraph (`<p>``</p>`)
  tags.
- Pre-formatted text (`<pre>`...`</pre>`) must be nested within paragraph
  (`<p>``</p>`) tags.
- Multi-line code blocks (see below bullet) must be nested within pre-format
  tags (`<pre>``</pre>`).
- Format all keywords, special constants (`true`, `false`, `null`), and file
  names (`portal-ext.properties`) as code using one of the two methods below:
    - HTML `<code>` tags (e.g., `<code>true</code>`)
    - Javadoc `@code` tag (e.g., `{@code true}`)
- With regards to class constants, be sure to reference their class either by
  including the full path of the constant (e.g.,
  `{@link com.liferay.portal.kernel.util.Constants#TYPE_ASSET}`) or wrapping the
  constant(s) in `<code></code>` tags followed by a reference to the class with
  its full path.
    - For example, `<code>TYPE_ASSET</code>` and `<code>TYPE_CREATOR</code>`
      defined in `{@link com.liferay.portlet.social.model.SocialActivityCounterConstants}`.

**Formatting tags example:**

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
 * <ul>
 * <li>
 * Example list, item 1
 * </li>
 * <li>
 * Item two
 * </li>
 * </ul>
 *
 * <p>
 * Another paragraph with more information in it. Example code should be placed
 * inside <pre><code> tags, as shown below.
 * </p>
 *
 * <p>
 * <pre>
 * <code>
 * Example myExample = new Example();
 * </code>
 * </pre>
 * </p>
 *
 * <p>
 * Notice the Javadoc tag values are lined up.
 * </p>
 *
 * <p>
 * Here is a table example:
 * </p>
 *
 * <p>
 * <table>
 * <tr>
 * <th>
 * Column Header 1
 * </th>
 * <th>
 * Column Header 2
 * </th>
 * <th>
 * Column Header 3
 * </th>
 * </tr>
 * <tr>
 * <td>
 * Data 1
 * </td>
 * <td>
 * Data 2
 * </td>
 * <td>
 * Data 3
 * </td>
 * </tr>
 * </table>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see	   BigExample
 */
public class Example {
	...
}
```

## Examples

This section provides examples of Javadocs for various types of situations. For
each example, an explanation is given, a pattern is identified, and a reference
to Javadoc in a Liferay class is given. Each example is listed in the Table of
Contents for quick lookup.

### Class: Initial and detailed description

The
[Localization](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/util/Localization.html)
interface demonstrates our guidelines for initial and detailed class
descriptions well.

```
Stores and retrieves localized strings from XML, and provides utility methods
for updating localizations from JSON, portlet requests, and maps. Used for
adding localization to strings, most often for model properties.

<p>
Localized values are cached in this class rather than in the value object since
value objects get flushed from cache fairly quickly. Though lookups performed on
a key based on an XML file are slower than lookups done at the value object
level in general, the value object will get flushed at a rate which works
against the performance gain. The cache is a soft hash map which prevents memory
leaks within the system while enabling the cache to live longer than in a weak
hash map.
</p>
```

Note the following from this example:

- The first sentence of the initial class description starts with a verb (two
  verbs, in fact):

    `Stores and retrieves ...`

- A followup sentence supports the initial class description's first sentence:

    `... Used for adding localization to strings, most often for model
    properties.`

- The second paragraph provides a helpful description of the classes details.

### Constructor: with parameters

**Constructor Description Pattern:** Constructs a new *&lt;something>* with ...
*&lt;list each parameter in layman's terms>*.

```java
/**
 * Constructs a DayAndPosition with the day of the week and day position.
 *
 * @param d the day of the week
 * @param p the day position
 */
public DayAndPosition(int d, int p)
```

For a working example, see
[StringParser.StringParser(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/util/StringParser.html#StringParser(java.lang.String))

### Method: Get by primary key

The primary key need not be mentioned in the method description, as the primary
key is part of the method signature and should have a parameter description.

**Method Description Pattern:** Returns the *&lt;something>* with the primary
key.

**Return Tag Pattern:** the *&lt;something of this thing>*

```java
/**
 * Returns the organization with the primary key.
 *
 * @param  organizationId the primary key of the organization
 * @return the organization
 */
public Organization getOrganization(long organizationId)
```

For a working example, see
[OrganizationService.getOrganization(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/service/OrganizationService.html#getOrganization(long)).

### Method: Deletes/Removes something

The primary key need not be mentioned in the method description, as the primary
key is part of the method signature and should have a parameter description.

**Method Description Pattern:** Deletes the *&lt;something>* **OR** Removes the
*&lt;something>*

**Return Tag Pattern:** the *&lt;something that was removed>*

### Method: Get/search/count matching a single field

Methods that involve straight matching of fields to simple values should use the
following pattern:

**Method Description Pattern:** Returns *&lt;something>* with the *&lt;field1>*... .

Example:

```java
/**
 * Returns the primary key of the user with the email address.
 *
 * ...
 * @return the user with the email address
 */
public long getUserIdByEmailAddress( ... , String emailAddress)
```

For a working example, see
[UserService.getUserByEmailAddress(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/service/UserService.html#getUserByEmailAddress(long,%20java.lang.String)).

### Method: Get/search/count matching multiple fields

At times, the method may have a whole set of parameters used to find/match an
entity instance. To simplify things, describe your method as "matching" these
parameters and list the parameters.

**Method Description Pattern:** Returns *&lt;something>* matching the *&lt;field1>
[,&lt;field2>, ... &lt;fieldN>]* and *&lt;fieldN+1>*.

Example:

```java
/**
 * Returns all the structures matching the group, name, and description.
 * ...
 * @return the matching structures
 */
public List<DDMStructure> getStructure(long groupId, String name, String description)
```

For working examples, see
[ResourceLocalServiceImpl.updateResources(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-impl/com/liferay/portal/service/impl/ResourceLocalServiceImpl.html#updateResources(long,%20long,%20java.lang.String,%20long,%20com.liferay.portal.kernel.service.permission.ModelPermissions)).

### Method: Get/search/count by field-to-keyword matching

Methods for which individual fields are matched with corresponding keyword
parameters, should follow the pattern below:

**Method Description Pattern:** Returns *&lt;somethings>* whose *&lt;fieldA>[,
&lt;fieldB>, ... or &lt;fieldZ>]* match the keywords specified for them.

### Method: Get/search/count by Combined Field Value and Field-to-Keyword Matching

Methods for which straight matching of fields to simple values is done, and for
which individual fields are matched with corresponding keyword parameters,
should follow the pattern below:

**Method Description Pattern:** Returns *&lt;somethings>* with the *&lt;field1>[,
&lt;field2>, ... and &lt;fieldn>]*, and whose *&lt;fieldA>[,
&lt;fieldB>, … and &lt;fieldZ>]* match the keywords specified for them.

```java
/**
 * Returns an ordered range of all the users with the status, and whose
 * first name, middle name, last name, screen name, or email address match
 * the keywords specified for them, ...
 * ...
 * @return the matching users
 */
public List<User> search(long companyId, String firstName, String middleName, String lastName, String screenName, String emailAddress, int status, ... )
```

For a working example, see
[UserLocalService.search(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/service/UserLocalService.html#search(long,%20java.lang.String,%20java.lang.String,%20java.lang.String,%20java.lang.String,%20java.lang.String,%20int,%20java.util.LinkedHashMap,%20boolean,%20int,%20int,%20com.liferay.portal.kernel.search.Sort)).

### Method: Returning a boolean

If a method returns a boolean, only the condition(s) for which the method
returns `true` need be included in the initial method description.

**Method Description Pattern:** Returns &lt;code>true&lt;/code> if
*&lt;satisfying condition(s)>*.

**Return Tag Pattern:** &lt;code>true&lt;/code> if *&lt;something>*;
&lt;code>false&lt;/code> otherwise

```java
/**
 * Returns <code>true</code> if the user is a member of the group.
 *
 * ...
 * @return <code>true</code> if the user is a member of the group;
 *         <code>false</code> otherwise
 */
public boolean hasGroupUser(long groupId, long userId)
```

For a working example, see
[UserService.hasGroupUser(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/service/UserService.html#hasGroupUser(long,%20long)).

### Method: Returning a count

If a method returns some sort of count, then use the following pattern:

**Method Description Pattern:** Returns the number of *&lt;something>* ... .

Hint, if a method name contains the word "Count" or the method returns an `int`,
it may qualify for this pattern.

For a working example, see
[OrganizationLocalService.searchCount(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/service/OrganizationLocalService.html#searchCount(long,%20long,%20java.lang.String,%20java.lang.String,%20java.lang.Long,%20java.lang.Long,%20java.util.LinkedHashMap)).

### Method: Returning a collection

Precede the reference to the entity instances with the term "all the".

**Method Description Pattern:** Returns ... all the *&lt;entities>* ... .

**Return Tag Pattern:** the *&lt;something>s* of *&lt;this thing>* (Note, do not
refer to the collection type; instead, refer to the something in plural form)

```java
/**
 * Returns all the organizations associated with the user.
 *
 * ...
 * @return the organizations associated with the user
 */
public List<Organization> getUserOrganizations(long userId)
```

For a working example, see
[OrganizationService.getUserOrganizations(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/service/OrganizationService.html#getUserOrganizations(long)).

### Method: Returning an ordered range of values

If the collection returned is ordered, mention that in the method description.

**Method Description Pattern:** Returns an ordered range of ... .

**Return Tag Pattern:** the *&lt;something>s* of *&lt;this thing>* (Note, do
not refer to the collection type; instead, refer to the something in plural
form)

```java
/**
 * Returns an ordered range of all the users with a social relation of the
 * type with the user.
 *
 * ...
 * @return the ordered range of users with a social relation of the type
 *         with the user
 */
public List<User> getSocialUsers(long userId, int type, int start, int end, OrderByComparator obc)
```

For a working example, see
[UserLocalService.getSocialUsers(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/service/UserLocalService.html#getSocialUsers(long,%20long,%20int,%20int,%20com.liferay.portal.kernel.util.OrderByComparator)).

### Method: Returning ordered values NOT as a range of values

Sometimes, you'll need to describe your method that returns the entire
collection of values, that are not a part of a paginated range, and are ordered.

Example:

```java
/**
 * Returns all the structures matching the class name ID ordered by the
 * comparator.
 * ...
 */
public List<DDMStructure> getClassStructures(long companyId, long classNameId, OrderByComparator orderByComparator)
```

### Method: Returning a ranged collection

If the collection returned is a subset of the total collection with a specified
start and end index, refer to the subset as a range.

**Method Description Pattern:** Returns an ordered range of ... .

**Return Tag Pattern:** the *&lt;something>s* of *&lt;this thing>* (Note, do not
refer to the collection type; instead, refer to the something in plural form)

```java
/**
 * Returns an ordered range of all the users with a social relation of the
 * type with the user.
 *
 * ...
 * @return the ordered range of users with a social relation of the type
 *         with the user
 */
public List<User> getSocialUsers(long userId, int type, int start, int end, OrderByComparator obc)
```

For a working example, see
[UserLocalService.getSocialUsers(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/service/UserLocalService.html#getSocialUsers(long,%20long,%20int,%20int,%20int,%20com.liferay.portal.kernel.util.OrderByComparator)).

### Method: Describing overloaded methods

In most cases, you'll find the overloaded methods calling one of the
others--we'll refer to this as the main method. Here are some rules of thumb:

If there are only 2 overloaded methods, you can simply describe the main method
as having additional parameters.

- Method1 (main):

    ```java
    /**
     * Adds an organization with additional parameters.
     */
    addOrganization(long parentOrganizationId, String name, String type, boolean recursable, long regionId, long countryId, int statusId, String comments, boolean site, List<Address> addresses, List<EmailAddress> emailAddresses, List<OrgLabor> orgLabors, List<Phone> phones, List<Website> websites, ServiceContext serviceContext)
    ```

- Method2:

    ```java
    /**
     * Adds an organization.
     */
    addOrganization(long parentOrganizationId, String name, String type, boolean recursable, long regionId, long countryId, int statusId, String comments, boolean site, ServiceContext serviceContext)
    ```

    For a working example, see the `addOrganization` methods in
    [OrganizationServiceImpl](https://docs.liferay.com/portal/7.0/javadocs/portal-impl/com/liferay/portal/service/impl/OrganizationServiceImpl.html).

- If there are 3 or more overloaded methods, focus on their distinguishing
  parameters.

    Example, 3 overloaded methods named `addStructure()` and their
    distinguishing method descriptions.

    - Method1 (main):

        ```java
        /**
         * Adds a structure referencing its parent structure.
        */
        addStructure(long userId, long groupId, long parentStructureId, long classNameId,
            String structureKey, Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
            String xsd, String storageType, int type, ServiceContext serviceContext)
        ```

    - Method2:

        ```java
        /**
         * Adds a structure referencing a default parent structure, using the portal
         * property <code>dynamic.data.lists.storage.type</code> storage type and
         * default structure type.
         */
        addStructure(long userId, long groupId, long classNameId, Map<Locale, String> nameMap,
            Map<Locale, String> descriptionMap, String xsd, ServiceContext serviceContext)
        ```

    - Method3:

        ```java
        /**
         * Adds a structure referencing a default parent structure if the parent
         * structure is not found.
         */
        addStructure(long userId, long groupId, String parentStructureKey, long classNameId,
            String structureKey, Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
            String xsd, String storageType, int type, ServiceContext serviceContext)
        ```

Note, you can still consider appending with additional parameters at the end of
the main method if it makes sense.

### Method: Password Policy Methods

The following represents different types of methods that involve relationship of
an entities to password policies.

- Password Policy - Add

    **Method Description Pattern:** Assigns the password policy to the
    *&lt;entity(s)>*, removing any other currently assigned password policies.

    For a working example, see
    [UserService.addPasswordPolicyUsers(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/service/UserService.html#addPasswordPolicyUsers(long,%20long[])).

- Password Policy - Has

    **Method Description Pattern:** Returns &lt;code>true&lt;/code> if the
    password policy has been assigned to the *&lt;entity>*.

    For a working example, see
    [UserLocalServiceImpl.hasPasswordPolicyUser(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-impl/com/liferay/portal/service/impl/UserLocalServiceImpl.html#hasPasswordPolicyUser(long,%20long)).

- Password Policy - Unset

    **Method Description Pattern:** Removes the *&lt;entity(s)>* from the
    password policy.

    For a working example, see
    [UserService.unsetPasswordPolicyUsers(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/service/UserService.html#unsetPasswordPolicyUsers(long,%20long[])).

### Method: Uses a property

If your parameter uses a property, be sure to mention the kind of property it is
(e.g. portal property, portlet property, Liferay plugin package property, etc.)
and mention the property name explicitly wrapped in code tags.

Here is an example from `DDMStructureLocalService`:

```java
/**
 * Adds a structure referencing a default parent structure, using the portal
 * property <code>dynamic.data.lists.storage.type</code> storage type and
 * default structure type.
 * ...
 */
addStructure(long userId, long groupId, long classNameId, Map<Locale, String> nameMap, Map<Locale, String> descriptionMap, String xsd, ServiceContext serviceContext)
```

### Parameter: Primary key

Refer to primary keys explicitly. Do not refer to them as IDs, even if the
parameter uses Id in its name (e.g. `organizationId`).

```java
/**
 * ...
 * @param  organizationId the primary key of the organization
 * ...
 */
public Organization getOrganization(long organizationId)
```

For a working example, see
[OrganizationService.getOrganization(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/service/OrganizationService.html#getOrganization(long)).

### Parameter: Attribute/field

When describing parameters that represent entity fields, use the possessive form
of the entity to show ownership of the field. This is often applicable in
describing parameters in add/update methods for an entity.

```java
/**
 * ...
 * @param name the organization's name
 * @param type the organization's type
 * ...
 */
public Organization addOrganization(..., String name, String type, …)
```

For a working example, see
[OrganizationService.addOrganization(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/service/OrganizationService.html#addOrganization(long,%20java.lang.String,%20java.lang.String,%20long,%20long,%20long,%20java.lang.String,%20boolean,%20com.liferay.portal.kernel.service.ServiceContext)).

### Parameter: Boolean

When describing boolean parameters, **always** start the description with the
word *whether*. Two common uses of a boolean parameter are the following:

- The parameter indicates some condition. Therefore, describe the condition in
  terms of it being `true`.
- The parameter indicates whether to do something. Therefore, describe the
  action to be performed when the parameter value is `true`.

Example,

```java
/**
 * ...
 * @param male whether the user is male
 * ...
 * @param sendEmail whether to send the user an email notification about
 *        their new account
 * ...
 */
public User addUser(
			..., boolean male,
			..., boolean sendEmail,
			...)
```

For a working example, see
[UserService.addUser(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/service/UserService.html#addUser(long,%20boolean,%20java.lang.String,%20java.lang.String,%20boolean,%20java.lang.String,%20java.lang.String,%20long,%20java.lang.String,%20java.util.Locale,%20java.lang.String,%20java.lang.String,%20java.lang.String,%20long,%20long,%20boolean,%20int,%20int,%20int,%20java.lang.String,%20long[],%20long[],%20long[],%20long[],%20java.util.List,%20java.util.List,%20java.util.List,%20java.util.List,%20java.util.List,%20boolean,%20com.liferay.portal.kernel.service.ServiceContext)).

### Parameter: ClassNameId

When describing the `classNameId` parameter, identify if the method is referring
to its own service model or to a related model. Then, use the appropriate
pattern below based on which class name is being used.

- Uses own service model's class name ID - True

    **Parameter Description Pattern:** the primary key of the *&lt;entity's>*
    class name

- Uses a related model's class name ID - True

    **Parameter Description Pattern:** the primary key of the class name for the
    *&lt;entity's>* related model

### Parameter: Entity (primary)

Use this pattern when an entity (typically of a class type) is either the
subject of the method or involved in the method's behavior.

```java
/**
 * ...
 * @param user the user
 * ...
 */
public boolean isPasswordExpired(User user)
```

For a working example, see
[UserLocalService.isPasswordExpired(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/service/UserLocalService.html#isPasswordExpired(com.liferay.portal.kernel.model.User)).

### Parameter: ServiceContext

For every method that accepts a `ServiceContext` param, the Javadoc for that
param should specify which fields within the `ServiceContext` are mandatory and
which other fields are taken into account if specified.

Here are the rules to follow:

1.  Provide a simple description of the service context. If it can optionally be
    `null`, include that information in this first sentence.

2.  In the next sentence, start with "Must set" and describe any service context
    fields which are mandatory.

3.  In the next sentence, start with "Can set" or "Can merge" and describe any
    service context fields which, if specified, can be used by the underlying
    implementation. If there are a combination of fields that can be *set* and
    fields that can be *merged*, mention the fields that can be set *before* the
    fields that can be merged.

4.  Any service context attribute specifically named should be wrapped in
    `<code></code>` tags (e.g., `a service context's
    <code>fieldEntryTypeId</code>`).

**Parameter Tag Pattern:**

@param serviceContext the service context to be applied [
(optionally &lt;code>null&lt;/code>) ] . [ Must set the *&lt;field1> [, &lt;field2>, ... and
&lt;fieldN> ]* for the *&lt;entity>*. ] [ Can *(set | merge)* the *&lt;fieldA> [, &lt;fieldB>, ...
and &lt;fieldZ> ]* for the *&lt;entity>*. ]

```java
/**
 * ...
 * @param  serviceContext the service context to be applied (optionally
 *         <code>null</code>). Can set asset category IDs and asset tag
 *         names for the organization, and merge expando bridge attributes
 *         for the organization.
 * ...
 */
public Organization updateOrganization( ... , ServiceContext serviceContext)
```

Note, in the above example there are no mandatory service context fields.
However, the method does apply *replacement* field values and new field values.

### Parameter: And/or operator

The following is an example of how an and/or operator parameter may be
described. Note, be sure to check the specific use of the operator in order to
describe it properly.

```java
/**
 * ...
 * @param  andSearch whether every field must match its keywords, or just
 *         one field. For example, &quot;users with the first name 'bob' and
 *         last name 'smith'&quot; vs &quot;users with the first name 'bob'
 *         or the last name 'smith'&quot;.
 * ...
 */
public List<User> search( ... , String firstName, String middleName, … , boolean andSearch, ... )
```

For a working example, see
[UserLocalService.search(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/service/UserLocalService.html#search(long,%20java.lang.String,%20java.lang.String,%20java.lang.String,%20java.lang.String,%20java.lang.String,%20int,%20java.util.LinkedHashMap,%20boolean,%20int,%20int,%20com.liferay.portal.kernel.search.Sort)).

### Parameter: Refer reader to more information

To direct a reader to another class or URL containing more information related
to the parameter, add a sentence with a link to that information.

**Parameter Tag Pattern:**

@param the parameter's description. For more information see {@link
*&lt;fully-qualified class name>*}.

**Example:**

```
 * @param  params the finder params. For more information, see {@link
 *         com.liferay.portal.service.persistence.OrganizationFinder}
```

This example is used in comments for several of the search methods in
[OrganizationLocalService](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/service/OrganizationLocalService.html).

### Field

A field can be documented if it helps developers understand its purpose in the
class. A field description typically begins with the word The followed by a
short explanation.

**Example:**

```java
/**
 * The default long array value is an empty long array.
 */
 public static final long[] DEFAULT_LONG_VALUES = new long[0];
```

Fields can also begin with the `@value` tag, which displays the field value in
the description.

**Example:**

```java
/**
 * {@value #DYNAMIC_DATA_LISTS_DISPLAY} is the Portlet ID for the Dynamic
 * Data Lists Display portlet.
 */
 public static final String DYNAMIC_DATA_LISTS_DISPLAY =
 	"com_liferay_dynamic_data_lists_web_portlet_DDLDisplayPortlet";
```

You can also start with the word The and use the `@value` tag, if appropriate.

**Example:**

```java
/**
 * The default long value is {@value #DEFAULT_LONG}.
 */
 public static final long DEFAULT_LONG = 0;
```

The `@value` tag only returns the field value for primitive types and Strings.
The `@value` tag will not render properly when set to a non-primitive field
value.

### Return: Ordered collection

For ordered collection returned, mention how or what determines the order

**Examples:**

```
@return the range of matching file entries ordered by the comparator
```

```
@return the range of matching file entries ordered by date modified
```

For working examples, see `DDMStructure.getStructures(long groupId, long
classNameId, int start, int end, OrderByComparator orderByComparator)` and
[DLAppServiceImpl.getGroupFileEntries(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-impl/com/liferay/portlet/documentlibrary/service/impl/DLAppServiceImpl.html#getGroupFileEntries(long,%20long,%20long,%20int,%20int)).

### Return: Significantly varying values

If a method can return values significantly different or special known values,
separate the descriptions of these values using commas – *even if there are only
two such differing values*. This convention helps to emphasize differences
between them.

```
@return the primary key of the organization, or <code>0</code> if the organization was not found
```

Note, the convention of separating two values with a comma is only used with
describing return values. This convention is not to be used with the other tags
(e.g. `@param`, `@throws`).

For a working example, see
[OrganizationLocalService.getOrganizationId(...)](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/service/OrganizationLocalService.html#getOrganizationId(long,%20java.lang.String)).
