# Kaleo Designer [](id=kaleo-designer)

With the proper permissions, users can publish assets. Even if your enterprise
has the greatest employees in the world, many of the items they want to publish
must still be reviewed, for a variety of reasons. The Kaleo Designer lets you
design workflow definitions so your assets go through a review process before
publication.

With Kaleo Designer, you develop workflow definitions using a convenient drag
and drop user interface. You don't need to be familiar with writing XML
definitions by hand. Some of the features can be enhanced, however, if you're
familiar with Groovy, a supported Java-based scripting language. All that is to
say, don't be scared off when you come to a block of code in these articles.
Just decide if you need the feature and find someone familiar with Java or
Groovy to help you out.

+$$$

**Note:** By default, only one workflow definition is installed: the Single
Approver Workflow definition. What you might not know is that you have access to
several others too. Look in `[Liferay_Home]/osgi/portal/` and find the
`com.liferay.portal.workflow.kaleo.runtime.impl-[version].jar`. Open it up and
then look in `META-INF/definitions`. You'll see the following workflow
definitions:

    category-specific-definition.xml
    legal-marketing-definition.xml
    single-approver-definition.xml
    single-approver-definition-scripted-assignment.xml

To work with any of these definitions in Kaleo Designer, extract them from the
JAR file first. Once you have the XML files locally, 

1.  Add a new workflow. Go to Control Panel &rarr; Configuration &rarr;
    Workflow, and click the Add button (![Add](../../../images/icon-add.png)).

2.  Go to the Source tab.

3.  Click _import a file_ and upload the XML file.

4.  Name the definition appropriately, and click either *Save* (to save it as a
    draft) or *Publish* (see below for more information on saving and
    publishing). 

Now you can begin exploring or modifying the definition. 

$$$

It's time to start exploring the Kaleo Designer and its features.
