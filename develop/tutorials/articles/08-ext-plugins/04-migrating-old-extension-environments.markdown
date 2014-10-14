# Migrating old extension environments [](id=migrating-old-extension-environments)

Because Ext plugins are an evolution of the extension environment provided in
Liferay 5.2 and earlier, you might need to migrate your extension environment
into Ext plugins when upgrading Liferay. If you need to do this, we have good
news; migrating is automated and relatively easy. 

+$$$

**Tip:** When migrating an extension environment, first consider whether any of
the extension environment's features can be moved into other types of plugins.
Portlets and hooks are designed to meet specific needs and they're easier to
learn. Additionally, they're easier to maintain since they often require fewer
changes when upgrading to a new version of Liferay. 

$$$

To successfully migrate, execute an Ant target within the `ext` directory of the
Plugins SDK, pointing to the old extension environment and naming the new
plugin. Be sure to remove the line escape character `\` from the following
example: 

    ant upgrade-ext -Dext.dir=/projects/liferay/ext -Dext.name=my-ext\
    -Dext.display.name="My Ext"


Let's look at the three parameters we used above: 

- `ext.dir`: The location of the old extension environment. 
- `ext.name`: The name of the Ext plugin that you want to create. 
- `ext.display.name`: The display name. 

After executing the target, you should see the logs of several copy operations
that will take files from the extension environment and copy them into the
equivalent directory within the Ext plugin (see the section *Creating an Ext
plugin* for an explanation of the main directories within the plugin).

With the migration process finished, you can upgrade your code to the new
version of Liferay by completing a few additional tasks. Most commonly, you
should do the following: 

- Review the uses of Liferay's APIs and adapt them accordingly. 
- Review any changes to the new version of Liferay's JSPs. Merge your changes
into the JSPs of the new Liferay version. 
- Run `ant build-service` again, to use Service Builder. It's also recommended
to consider moving this code to a portlet plugin, because Service Builder is
deprecated in Ext, and plugins allow for greater modularity and maintainability.
- If you implemented any portlets in the old extension environment, migrate them
to portlet plugins; extension environment portlets have been deprecated since
Liferay Portal 6.0, and support isn't guaranteed in future Liferay Portal
releases. 

## Licensing and Contributing [](id=licensing-and-contributing)

Liferay Portal is Open Source software licensed under the LGPL 2.1 license
([http://www.gnu.org/licenses/lgpl-2.1.html](http://www.gnu.org/licenses/lgpl-2.1.html)).
If you reuse any code snippet and redistribute it, whether publicly or to a
specific customer, make sure your modifications are compliant with the license.
One common way is to make the source code of your modifications available to the
community under the same license. Make sure you read the license text yourself
to find the option that best fits your needs. 

If your goal in making changes is fixing a bug or improving Liferay, it could be
of interest to a broader audience. Consider contributing it back to the project.
That benefits all users of the product, including you since you won't have to
maintain the changes with each newly released version of Liferay. You can notify
Liferay of bugs or improvements in
[issues.liferay.com](http://issues.liferay.com). There is also a wiki page with
instructions on how to contribute to Liferay: 

[http://www.liferay.com/community/wiki/-/wiki/Main/Contributing](http://www.liferay.com/community/wiki/-/wiki/Main/Contributing)
