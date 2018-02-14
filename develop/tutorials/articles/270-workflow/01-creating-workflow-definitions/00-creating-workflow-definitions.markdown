# Crafting XML Workflow Definitions [](id=crafting-xml-workflow-definitions)

You don't need a fancy visual designer to build workflows. To be clear, Kaleo
Designer may make you a faster workflow designer through its graphical
interface. If you plan to build lots of workflow processes, a Digital Enterprise
subscription gets you access to Kaleo Designer. But with a little copy and paste
from existing workflows and a little handcrafted XML, you can build any workflow
and attain workflow wizard-hood in the process. Follow this set of tutorials to
learn what elements you can put into your definitions.

## Existing Workflow Definitions [](id=existing-workflow-definitions)

Only one workflow definition is installed by default: Single Approver. Several
more, however, are embedded in the source code of your @product@ installation.
If you're comfortable extracting the XML files from a JAR file embedded in an
LPKG file, you're welcome to follow the steps below to obtain the workflow
definitions. To obtain the files more conveniently, download a ZIP file
[here](https://dev.liferay.com/documents/10184/1530512/Workflow+Definitions+Zip).

To extract the definitions for yourself, navigate to 

    [Liferay Home]/osgi/marketplace

and open (using an archive manager) `Liferay CE Forms and Workflow.lpkg`. Open
the JAR file named

    com.liferay.portal.workflow.kaleo.runtime.impl-[version].jar
 
In the JAR file, navigate to 

    META-INF/definitions/

and extract the four XML workflow definition files. These definitions provide
good reference material for many of the workflow features and elements described
in these articles. In fact, most of the XML snippets you see here are lifted
directly from these definitions.

## Schema [](id=schema)

The XML structure of a workflow definition is defined in an XSD file:

    liferay-worklow-definition-7_0_0.xsd

Declare the schema at the top of the workflow definition file:

    <?xml version="1.0"?>
    <workflow-definition
        xmlns="urn:liferay.com:liferay-workflow_7.0.0"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
        xsi:schemaLocation="urn:liferay.com:liferay-workflow_7.0.0 
            http://www.liferay.com/dtd/liferay-workflow-definition_7_0_0.xsd">

To read 464 lines of beautifully formatted XML that defines how to write more
XML (it's practically poetic), check out the XSD
[here](https://www.liferay.com/dtd/liferay-workflow-definition_7_0_0.xsd).
Otherwise, move on to entering the definition's metadata.

## Metadata [](id=metadata)

Give the definition a name, description, and version:

    <name>Category Specific Approval</name>
    <description>A single approver can approve a workflow content.</description>
	<version>1</version>

Once the schema and metadata are in place, it's time to turn up the funky beats
and get into the flow (the workflow). Learn about workflow nodes in the next
article.
