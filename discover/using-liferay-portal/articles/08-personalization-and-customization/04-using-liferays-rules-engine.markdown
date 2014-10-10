# Using Liferay's rules engine [](id=using-liferays-rules-engine)

![EE Only Feature](../../images/ee-feature-web.png)

Liferay Portal Enterprise Edition provides an implementation of a JSR-94
compliant rules engine. This rules engine is provided as a Web Plugin and is
based on the popular open source Drools project. 

## Why use a rules engine? 

If you are not familiar with rules engines, you may be wondering why you would
want to use one. In most applications, complex rule processing often takes the
form of nested `if-else` blocks of code which can be very difficult to decipher
and to maintain. If rules change, a developer must work with a business user to
define the new rules. The developer must then read through the existing logic to
understand what is happening and make the necessary modifications. The changes
must then be recompiled, tested, and redeployed. A rules engine provides a means
to separate the rules or logic of an application from the remaining code.
Separating these rules provides several distinct advantages. 

- A rules engine allows for a more declarative style of programming where the
  rules define what is happening, without describing how it is happening. This
  makes it much easier to read than nested 'if-else' blocks of code. It's also
  easier to make changes without introducing bugs in your code. 

- The rules are written in a language that is easier for non-developers to
  understand. This makes it easier for business users to validate and even
  modify the rules without having to involve developers.  

- A rules engine allows for changes to be made to the rules without requiring
  that you recompile your application. If your code must pass through a strict
  deployment workflow, this can be a huge time saver and can also save a
  significant amount of money. 

After all this, you may be interested in using Liferay's rules engine, so let's
get started with it. 

## Installation 

The Drools web plugin is available to Liferay Enterprise Edition customers
through Liferay Marketplace. It's called the Drools EE app and you'll find it
categorized as a Utility app.

The Drools web plugin provides a rules engine implementation. By itself, it
doesn't provide any observable changes to the portal user interface or any
additional functionality. To see the rules engine in action, you can download
and install the Sample Drools portlet that contains two rule definitions that
illustrate how to leverage the rules engine in your custom code. The Sample
Drools portlet is available through the Customer Portal.

Let's examine the sample portlet to see how it works. 

### Configuring the Sample Drools Portlet 

Begin by downloading and installing the Sample Drools portlet. The Sample Drools
portlet is available to Liferay Enterprise Edition customers through the
customer portal. The name is `sample-drools-portlet`, and you'll find it in the
list of web plugins.

After installation is complete, log in as an administrator and add the portlet
to a page. Initially, the portlet indicates the name of the currently logged in
user and a message stating that there are no results. To see results in the
portlet, we need to create and tag assets in the site to which you added the
portlet. 

From the Dockbar, click *Admin* &rarr; *Content* and create a new web content
instance in your site. Before publishing the web content instance, tag the
article with *americas*. Then, navigate to *My Account* from the
Dockbar and click on the *Addresses* link on the right side of the screen. Enter
a Brazilian, Canadian, Mexican, or US based address and click *Save*. Navigate
back to the page with the Sample Drools portlet. The Sample Drools portlet
should now be displaying the web content instance that you created.

The default rule that's being evaluated displays a list of assets based on the
current user's address. For example, if the current user's country is set to
Brazil, Canada, Mexico, or the United States, the Sample Drools portlet displays
a list of assets that have been tagged with the *americas* tag.

![Figure 7.9: By default, the Sample Drools portlet returns personalized content based on the addresses set on the form for editing a user account. This form is accessible from the Dockbar via *\[User Name\]* &rarr; *My Account*. Users with addresses in Brazil, Canada, Mexico, or the United States will see a list of assets tagged with the "americas" tag in the Sample Drools portlet.](../../images/sample-drools-portlet.png)

<!-- | TODO: We need to point to what we're about to do before we do it;
otherwise this is hard to follow. | -->

<!--
TODO: The rules in the `sample-drools-portlet/WEB-INF/src/com/liferay/sampledrools/dependencies/rules_user_custom_attribute_content.drl` file need to be updated to be compatible with API changes. See the fix for LPS-40820 for an example of how this should be done. -Jesse, 10/1/2013
-->

<!--
The Sample Drools portlet plugin also contains a second rule that returns
personalized content based on the user's net worth set in the *My Account*
&rarr; *Custom Fields* section of the Control Panel. To see this rule in action,
add a second instance of the Sample Drools portlet to a page. Once added to the
page, select the *Options* icon (the gear) and then select *Configuration*. You
need to replace the rules defined in the *Rules* section of the Configuration
screen with contents of the *rules_user_custom_attribute_content.drl* file. The
rule file can be found in the deployed portlet at
`sample-drools-portlet/WEB-INF/src/com/liferay/sampledrools/dependencies/rules_user_custom_attribute_content.drl`.
In the same Configuration screen, add `networth` to the
`user-custom-attribute-names` field. Save your changes and close the pop-up
window. Navigate to the Control Panel and add a custom field to the User object
with the key `networth`. Navigate to *My Account* and click on the *Custom
Fields* link on the right side of the screen. Enter a net worth of 150000 and
save the record. Next, add a new web content instance to the default
site of your portal. Before publishing the web content, tag the instance
with *high net worth* and then save the entry. Now, navigate back to the
default site and the web content should be displayed in the second Sample Drools
portlet added to the page.
-->

Now that you've seen how the Sample Drools portlet works, let's take a look
closer at the rules themselves. 

### Rules Definitions 

Rule definitions can be written using Drools' declarative language. Rule files
are text files that often have a `.drl` extension. A rule file can contain
multiple rules. In addition to the standard Drools' declarative language, a
domain specific language (DSL) can be created for your specific problem domain.
<!-- | TODO: Explain. | --> Creating a DSL can make your rules even easier for
business users to create and maintain your applications rules but does require
some additional work up front. For additional information on creating a DSL for
your problem domain please refer to the Domain Specific Languages section of the
official Drools Documentation at
[http://docs.jboss.org/drools/release/5.2.0.Final/drools-expert-docs/html/ch05.html#d0e6217](http://docs.jboss.org/drools/release/5.2.0.Final/drools-expert-docs/html/ch05.html#d0e6217). 

To see examples of a rules definition file, access the following directory in
the Sample Drools portlet
`sample-drools-portlet/WEB-INF/src/com/liferay/sampledrools/dependencies`. To
see how rules work in action, we'll look at the rule defined in
`rules_user_address_content.drl`. 

At first glance, this `.drl` file looks a lot like a Java class file.  This
example starts with a comment describing the rule. Single line comments can
begin with either a `*#*` or `*//*` and multi-line comments begin with `_/*_`
and end with `_*/_`. 

    ##
    ## Rules
    ##
    ## This sample program will return personalized content based on the user's
    ## addresses set in the My Account section of the Control Panel.
    ##
    ## For example, suppose the current user has an address in the United States and
    ## is a member of the Liferay site. All assets within the Liferay site
    ## that are tagged with "West Coast Symposium" will be returned.
    ##

Following the comments is a `package` declaration. The package declaration is
optional in a Drools, but if it appears, it must be at the beginning of the
file. The package denotes a collection of rules. Unlike Java, the package name
does not represent a folder structure; it's only a unique namespace. The `;` at
the end of the package declaration and all other statements is optional. 

    package com.liferay.sampledrools.dependencies;

After the package declaration are a series of `import` statements. Import
statements in the rule file are used just like the import statements in Java
classes. Classes that are imported can be used in the rules. 

    import com.liferay.portal.kernel.search.BooleanClause;
    import com.liferay.portal.kernel.search.BooleanClauseOccur;
    import com.liferay.portal.kernel.search.BooleanClauseFactoryUtil;
    import com.liferay.portal.kernel.search.BooleanQuery;
    import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
    import com.liferay.portal.kernel.search.Document;
    import com.liferay.portal.kernel.search.FacetedSearcher;
    import com.liferay.portal.kernel.search.Field;
    import com.liferay.portal.kernel.search.Hits;
    import com.liferay.portal.kernel.search.Indexer;
    import com.liferay.portal.kernel.search.SearchContext;
    import com.liferay.portal.kernel.search.facet.AssetEntriesFacet;
    import com.liferay.portal.kernel.search.facet.Facet;
    import com.liferay.portal.kernel.search.facet.ScopeFacet;
    import com.liferay.portal.kernel.util.GetterUtil;
    import com.liferay.portal.kernel.util.KeyValuePair;
    import com.liferay.portal.kernel.util.LocaleUtil;
    import com.liferay.portal.kernel.util.StringUtil;
    import com.liferay.portal.kernel.util.Validator;
    import com.liferay.portal.model.Address;
    import com.liferay.portal.model.Group;
    import com.liferay.portal.model.Contact;
    import com.liferay.portal.model.User;
    import com.liferay.portal.service.AddressLocalServiceUtil;
    import com.liferay.portal.util.PortalUtil;
    import com.liferay.portlet.asset.model.AssetEntry;
    import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;
	
The next line declares the `dialect` for the package. In this case, we will be
using Java for any of the code expressions that we'll encounter. The other
possible value for the dialect is `MVEL`, which is a powerful expression
language for Java-based applications. See
[http://mvel.codehaus.org/](http://mvel.codehaus.org/) for details. If
necessary, the dialect can also be specified at the rule level to override the
package level dialect.

        dialect "java"
	
In this rule file, we have only a single function, which is listed next.
Functions allow you to insert Java code that can be evaluated at run time into
your rule file. Functions are commonly used as a as part of the consequence
clause of the rule statement. Functions are similar to Java methods but you use
the *function* keyword to define a function. The function's parameters and the
return type are declared as they would be in a Java method. In this example, the
`getAssetEntries` function returns a `java.util.List` object that contains
`AssetEntry` objects based on the `user`, `classNameId`s, and `assetTagName`
provided in the function call. 

    function List getAssetEntries(
        User user, long[] classNameIds, String assetTagName) {

        List<Group> groups = user.getMySites();

        long[] groupIds = new long[groups.size()];

        for (int i = 0; i < groups.size(); i++) {
            Group group = groups.get(i);

            groupIds[i] = group.getGroupId();
        }

        if ((classNameIds == null) || (classNameIds.length == 0) ||
            (groupIds.length == 0) || Validator.isNull(assetTagName)) {

            return Collections.emptyList();
        }

        SearchContext searchContext = new SearchContext();

        Facet assetEntriesFacet = new AssetEntriesFacet(searchContext);

        assetEntriesFacet.setStatic(true);

        searchContext.addFacet(assetEntriesFacet);

        Facet scopeFacet = new ScopeFacet(searchContext);

        scopeFacet.setStatic(true);

        searchContext.addFacet(scopeFacet);

        searchContext.setAttribute("paginationType", "regular");
        searchContext.setCompanyId(user.getCompanyId());
        searchContext.setGroupIds(groupIds);
        searchContext.setIncludeStagingGroups(false);
        searchContext.setScopeStrict(false);
        searchContext.setUserId(user.getUserId());

        BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create(searchContext);

        booleanQuery.addExactTerm(Field.ASSET_TAG_NAMES, assetTagName);

        BooleanClause booleanClause = BooleanClauseFactoryUtil.create(
            searchContext, booleanQuery, BooleanClauseOccur.MUST.getName());

        searchContext.setBooleanClauses(new BooleanClause[] {booleanClause});

        List<AssetEntry> assetEntries = new ArrayList<AssetEntry>();

        Indexer indexer = FacetedSearcher.getInstance();

        Hits hits = indexer.search(searchContext);

        for (int i = 0; i < hits.getDocs().length; i++) {
            Document document = hits.doc(i);

            String entryClassName = document.get(Field.ENTRY_CLASS_NAME);

            long entryClassPK = GetterUtil.getLong(
                document.get(Field.ENTRY_CLASS_PK));
            long rootEntryClassPK = GetterUtil.getLong(
                document.get(Field.ROOT_ENTRY_CLASS_PK));

            if (rootEntryClassPK > 0) {
                entryClassPK = rootEntryClassPK;
            }

            AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
                entryClassName, entryClassPK);

            if (assetEntry != null) {
                assetEntries.add(assetEntry);
            }
        }

        return assetEntries;
    }

Alternatively, this function could've been written in a helper class and then
imported using a *function import*. So if we had created a helper class called
`AddressContentHelper` the import would look like this: 

        import function
        com.liferay.sampledrools.dependencies.AddressContentHelper.getAsetEntries;

The last section of the rules file contains the actual rules. The syntax of a
rule is very straightforward. 

        rule "name" attribute when condition then consequence end

The rule name is required and it must be unique within the package as declared
above. Names with spaces must be enclosed in double quotes. It is considered a
best practice to always enclose your rule names in double quotes. 

        rule "Initialize Rules"
 
The attributes section is optional. In our first rule, we have a single
attribute called `salience`. The salience attribute is an integer value that
acts as a priority weighting. The number can be positive or negative and
defaults to the value of `0`. Rules with a higher salience value are given a
higher priority. It is considered a best practice to write attributes one to a
line. In our example, the first rule is one that should be evaluated before any
other so it is given a high salience value of 1000. None of our other rules have
a salience attribute set, so they all default to a value of `0`.

        salience 1000

The `when` keyword marks the conditional section of the rule. It is also
referred to as the Left Hand Side (LHS). The `when` keyword is followed by zero
or more condition elements that must be met before the consequence will be
called. If there are no condition elements, then the condition is always
evaluated as true. 

The most common type of conditional element is a *pattern*. Patterns match
against *facts*. Facts are statements that are known to be true. Facts can be
inserted by the Java code that invokes the rules engine or they can be declared
in the rule file itself. 

In the first rule of our rule file (`Initialize Rules`), the only condition is
that the rule must operate on a User object. 
		
	when user : User();

In more complex rules, the pattern may include constraints or may evaluate the
properties of Java objects.  For example, the second rule of this rule file is
called *Get European Content*. This rule includes the following pattern which
ensures that a user's address contains the country name France, Germany, or
Spain. 

	userAddress : Address(country.getName(Locale.US) in ("France", "Germany", "Spain"));

The consequence section of the rule follows the conditional section. It's also
known as the Right Hand Side (RHS) or action part of the rule. The consequence
section begins with the keyword `then` and it is intended to modify the working
memory data. Drools provides some convenience methods that make it easier to
modify the working memory. In this rule, we use the `insertLogical` method which
places a new object into the working memory and retracts it when there are no
more facts supporting the truth of the current rule. After the consequence
section of the rule, the rule is terminated with the keyword `end`. 

	then List<Address> userAddresses = AddressLocalServiceUtil.getAddresses(
            user.getCompanyId(), Contact.class.getName(), user.getContactId());

        for (Address userAddress : userAddresses) {
            insertLogical(userAddress); } end

Following the initial rule in our example, there are three additional rules that
will be evaluated. Each of these rules evaluates the `userAddress` that was
inserted into the working memory to determine what type of content should be
displayed to the end user. 

For additional documentation on the Drools rules language, please see the
official Drools documentation at
[http://docs.jboss.org/drools/release/5.2.0.Final/drools-expert-docs/html/](http://docs.jboss.org/drools/release/5.2.0.Final/drools-expert-docs/html/). 
