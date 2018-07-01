# Using Taglibs in Templates [](id=using-taglibs-in-templates)

Liferay's taglibs are also accessible to web content administrators developing
in FreeMarker. There is no need to instantiate these taglibs within your
FreeMarker template; they're already provided for you automatically. You can
access these taglibs by indicating the TLD's file name with underscores.

When you're using @product@'s template editor, you can find variables on the 
left side of the template editor. To place one of the variables onto
the template editor,

1.  Position your cursor where you want the variable placed.

2.  Click the variable name.

If the variable name doesn't give you sufficient information on the variable's 
functionality, you can hover your pointer over it for a more detailed 
description.

![Figure 1: You can hover your pointer over a variable for a more detailed description.](../../../../../images/web-content-templates-create.png)

The interactive template editor is available for the FreeMarker, Velocity, and
XSL languages. Depending on which language you select, the variable content
changes so you're always adding content in the language you've chosen. Another
cool feature for the template editor is the autocomplete feature. It can be
invoked by typing *${* which opens a drop-down menu of available variables. By
clicking one of the variables, the editor inserts the variable into the template
editor.

+$$$

**Note:** The `utilLocator`, `objectUtil`, and `staticUtil` variables for
FreeMarker and the `utilLocator` variable for Velocity are disabled by default.
These variables are vulnerable to remote code execution and privilege
escalation, and should be used with caution, if enabled.

$$$

After you've saved your template, @product@ provides a WebDAV URL and static 
URL. These values access the XML source of your structure. You can find these 
URLs by returning to your template after it's been saved and expanding the 
*Details* section. For more information on WebDAV and the uses of the WebDAV 
URL, reference the [WebDAV Access](/discover/portal/-/knowledge_base/7-0/publishing-files#desktop-access-to-documents-and-media)
section.

Now that you've created a handsome template and know how to use the template
editor, it's time to decide who the lucky people are that get to use your new
template.
