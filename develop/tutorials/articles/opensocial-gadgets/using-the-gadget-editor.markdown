# Using the Gadget Editor [](id=using-the-gadget-editor-lp-6-2-develop-tutorial)

As part of Liferay's OpenSocial integration, the *OpenSocial Gadget Editor* is
included with Liferay Portal. The gadget editor is a complete development
environment for gadgets, providing syntax highlighting, preview functionality,
undo/redo options, and built in tabs for working on multiple gadgets at the same
time. You can also organize and manage gadgets easily using the editor's file
manager. All of this gives you the convenience of creating and improving your
gadgets right from within Liferay Portal.

Within the editor, each gadget's XML file has a drop-down menu allowing you to
close, rename, delete, publish, or unpublish the gadget, or to simply show the
gadget's URL. The *Publish* button directs you to a screen, similar to the
*OpenSocial Gadget Publisher*, allowing you to publish your gadget. Gadgets
published through the editor are stored in the site's Document and Media
Library. The *Show URL* button gives you the URL so that the gadget may be
shared with other sites. These options offer a user-friendly and easy to use
testing station for enhancing the gadgets on your sites.

![Figure 1: The drop-down menu can be easily found to the right of your XML file.](../../images/opensocial-39.png)

**Note:** When you publish a new gadget, remember that your Liferay Portal
installation is the new host when specifying the gadget's URL.

As a brief tutorial, we'll demonstrate creating a new gadget and publishing the
gadget to Liferay using the gadget editor. Let's get started!
 
## Editing OpenSocial Gadgets [](id=editing-opensocial-gadgets-lp-6-2-develop-tutorial)

For this tutorial, we'll edit an OpenSocial gadget and publish the gadget to a
Liferay page. We'll accomplish all of this using Liferay's OpenSocial Gadget
Editor. Let's get started!

1. Navigate to *Site Administration* &rarr; *Content* and select *OpenSocial
   Gadget Editor*.

2. In the editor, create a gadget from scratch or copy in a gadget's XML and
   edit the code as desired.

3. Click the floppy disk button to save your new gadget XML. Once you've typed
   your gadget's desired name, click the check mark. Make sure to attach the
   `.xml` suffix to the end of your gadget's name; otherwise, the gadget will
   not save.

    ![Figure 2: It is easy to insert gadget content into Liferay's *OpenSocial Gadget Editor* and save it as an OpenSocial gadget.](../../images/opensocial-31.png)

4. Select the *Preview* tab from the toolbar and a preview of your gadget
   appears.

    ![Figure 3: The *Preview* tab displays what your gadget would look like if it was added to a Liferay page.](../../images/opensocial-32.png)

5. Publish your gadget for portal-wide use by selecting the drop-down menu next
   to the gadget's name and clicking *Publish*. A *Publish Gadget* screen opens
   up, showing your gadget's URL and categories to select for your gadget.

    ![Figure 4: A *Publish Gadget* window displays your gadget's URL and a host of categories for you to consider for your gadget.](../../images/opensocial-36.png)

9.  Choose a category and click *Save*.

Your new gadget is now available for portal-wide use!

The OpenSocial Gadget Editor allows you to create and improve gadgets within the
comfort of your own Liferay Portal instance. You can facilitate the social
interactions on your sites and increase your portal content's popularity across
your social network by leveraging the power of OpenSocial gadgets and the gadget
editor.

## Next Steps 

[Sending PubSub Messages Between Gadgets and Portlets](/tutorials/-/knowledge_base/sending-pubsub-messages-between-gadgets-and-portlet-lp-6-2-develop-tutorial)
