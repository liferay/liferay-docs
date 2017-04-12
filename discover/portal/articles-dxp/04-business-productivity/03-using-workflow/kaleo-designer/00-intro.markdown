# Kaleo Designer [](id=kaleo-designer)

With the proper permissions, users can publish assets. Even if your enterprise
has the greatest employees in the world, many of the items they'll wish to
publish must still be reviewed, for a variety of reasons. The Kaleo Designer in
@product@ lets you design workflow definitions so your assets go through a
review process before publication.

Kaleo Designer lets you develop workflow definitions using a convenient drag
and drop user interface, so you don't need to be familiar with any scripting
languages or with writing XML by hand. However, some of the features can be
enhanced if you're familiar with one of the supported scripting languages. All
that is to say, don't be scared off when you come to a block of code in these
articles. Just decide if you need the feature and find someone familiar with
one of the supported scripting languages to help you out.

Workflow definitions support scripting in Beanshell, DRL, Groovy, JavaScript,
Python, or Ruby. 

+$$$

**Note:** By default, there's only one workflow definition that's installed in
@product@: the Single Approver Workflow definition. What you might not know is
that you have access to several others too. If you have a local @product@
installation, look in `[Liferay_Home]/osgi/marketplace/Liferay Forms and
Workflow.lpkg` 
and find the `com.liferay.portal.workflow.kaleo.runtime.impl-[version].jar`.
Open it up and then look in `META-INF/definitions`. You'll see the following
workflow definitions:

    category-specific-definition.xml
    legal-marketing-definition.xml
    single-approver-definition.xml
    single-approver-definition-scripted-assignment.xml

To work with any of these definitions in Kaleo Designer, extract them from the
JAR file first. Once you have the XML files locally, add a new workflow (see
the below instructions) in the designer, then open the *Source (Kaleo XML)* tab.
Replace the entire contents with the contents of the XML definition file. Name
the definition appropriately, and click either *Save as Draft* or Publish* (see
below for more information on saving and publishing). Now you can begin
exploring or modifying the definition as you'd like.

$$$

It's time to start exploring the Kaleo Designer and its features.
