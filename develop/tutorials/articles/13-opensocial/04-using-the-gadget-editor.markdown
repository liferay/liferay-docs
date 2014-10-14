# Using the gadget editor [](id=using-the-gadget-editor)

As part of Liferay's OpenSocial integration, the *OpenSocial Gadget Editor* is
included with Liferay Portal. The gadget editor is a complete development
environment for gadgets, providing syntax highlighting, preview functionality,
undo/redo options, and built in tabs for working on multiple gadgets at the same
time. You can also organize and manage gadgets easily using the editor's file
manager. All of this gives you the convenience of creating and improving your
gadgets right from within Liferay Portal.

Within the editor, each gadget's XML file has a wrench icon allowing you to
close, rename, delete, or publish the gadget, or to simply show the gadget's
URL. The *Publish* button directs you to a screen, similar to the *OpenSocial
Gadget Publisher*, allowing you to publish your gadget. Gadgets published
through the editor are stored in the site's Document and Media Library. The
*Show URL* button gives you the URL so that the gadget may be shared with other
sites. These options offer a user-friendly and easy to use testing station for
enhancing the gadgets on your sites.

![Figure 13.7: The wrench icon can be easily found to the right of your XML file.](../../images/opensocial-39.png)

**Note:** When you publish a new gadget,
remember that your Liferay Portal installation is the new host when specifying
the gadget's URL.

For a brief exercise, we will improve the *Google Address* gadget using the
gadget editor. As we referenced earlier, the *Google Address* gadget
automatically displays the address *1400 Montefino Avenue, Diamond Bar, CA
91765* in its text window. By using the OpenSocial Gadget Editor, you can edit
the XML file and specify a customized address or remove the default address
entirely from the gadget's UI. For our example, we will remove the default
address entirely from our gadget's text window.

1.  Copy the gadget XML contents into the gadget editor. Navigate to *OpenSocial
    Gadget Publisher* from under the *Portal* heading and select the URL for
    *Google Address*.

2.  Copy the XML content onto your clipboard.

3.  Navigate to the *OpenSocial Gadget Editor* from the Control Panel and paste
    your clipboard contents into the gadget editor.

4.  Click the floppy disk button to save your new gadget XML, naming your gadget
    *GoogleMapsPublisher.xml*. Press the green check button to save the file.

    ![Figure 13.8: It is easy to insert gadget content into Liferay's *OpenSocial Gadget Editor* and save it as an OpenSocial gadget.](../../images/opensocial-31.png)

    **Note:** Liferay will not allow you to publish your new gadget without
    attaching `.xml` to the end of your file name. 

5.  Select the *Preview* tab from the toolbar and a preview of your gadget
    appears.

    ![Figure 13.9: The *Preview* tab displays what your gadget would look like if it was added to a Liferay page.](../../images/opensocial-32.png)

6.  Locate the following element in the gadget XML:

        <input type="text" id="address" name="address" size="40" value="1400
        Montefino Avenue, Diamond Bar, CA 91765">

    Then, remove the `value="1400 Montefino Avenue, Diamond Bar, CA 91765"`
    setting and save the file.

7.  Select the editor's *Preview* mode and click the refresh button.

    ![Figure 13.10: The refresh button is easy to find in the upper right corner of the editor's *Preview* pane.](../../images/opensocial-34.png)

    As you would expect, the gadget's default address is now blank.

    ![Figure 13.11: Your updated gadget now has a blank text window, thanks to the editing you've done in Liferay's gadget editor.](../../images/opensocial-33.png)

8.  Publish your gadget for portal-wide use by selecting the wrench icon next to
    the *GoogleMapsPublisher.xml* file and clicking *Publish*.

    ![Figure 13.12: You can publish your OpenSocial gadgets directly from Liferay's *OpenSocial Gadget Editor*.](../../images/opensocial-35.png)

    A *Publish Gadget* screen opens up, showing your gadget's URL and categories
    to select for your gadget.

    ![Figure 13.13: A *Publish Gadget* window displays your gadget's URL and a host of categories for you to consider for your gadget.](../../images/opensocial-36.png)

9.  Choose a category and click *Save*.

Your new gadget is now available for portal-wide use!

The OpenSocial Gadget Editor allows you to create and improve gadgets within
the comfort of your own Liferay Portal instance.

You can facilitate the social interactions on your sites and increase your
portal content's popularity across your social network by leveraging the power
of OpenSocial gadgets.
