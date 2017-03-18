# Export/Import [](id=export-import)

<!-- This section needs to be tested against the new AlloyUI -->

Some Liferay portlets allow you to export or import portlet data. These include
many of Liferay's collaborative applications, such as the Blogs, Wiki, and
Message Boards portlets. To export or import portlet data, click on the gear
icon of your portlet and select *Export/Import*. Exporting portlet data produces
a `.lar` file that you can save and import into another portlet application of
the same type. To import portlet data, you must select a `.lar` file. Be careful
not to confuse portlet-specific `.lar` files with site-specific `.lar` files.
See the section on [Creating and Managing Pages](/discover/portal/-/knowledge_base/6-2/leveraging-liferays-multi-site-capabilities#creating-and-managing-pages) 
for a discussion of exporting and importing site page data. 

+$$$

**Note:** To prevent malicious code from being imported into your portal,
Liferay restricts external classes from being serialized/deserialized. If you
need a class serialized/deserialized during the import of a LAR, you must
whitelist that class. 

You can list your classes in `portal-ext.properties` or in the given plugin by
creating a `portal.properties` file with the proper settings and a
`liferay-hook.xml` that contains a `<portal-properties>` element to let the
deploy framework recognize and merge the property configuration with the default
ones.

First, to enable checking XStream class serialization security permissions, you
must set `staging.xstream.security.enabled=true`. Then you can list your fully
qualified class names allowed to be serialized/deserialized during export/import
and staging processes using the `staging.xstream.class.whitelist` property. This
list can be empty since the portal default entities are added already. The
following list are types allowed by default:

- Primitive types as a
`com.thoughtworks.xstream.security.PrimitiveTypePermission`. See the following
[class](https://github.com/x-stream/xstream/blob/XSTREAM_1_4_7/xstream/src/java/com/thoughtworks/xstream/core/util/Primitives.java)
for the full list of primitives.
- Allowed types as a
[`com.thoughtworks.xstream.security.ExplicitTypePermission`](https://github.com/x-stream/xstream/blob/XSTREAM_1_4_7/xstream/src/java/com/thoughtworks/xstream/security/ExplicitTypePermission.java):
    - `byte[]`
    - `java.util.Date`
    - `com.liferay.portlet.dynamicdatamapping.storage.Field`
    - `com.liferay.portlet.dynamicdatamapping.storage.Fields`
    - `java.io.InputStream`
    - `java.util.Locale`
    - `java.lang.String`
    - `java.sql.Time`
    - `java.sql.Timestamp`
 
Types defined in the `staging.xstream.class.whitelist` portal property are
passed as an `ExplicitTypePermission`.

- Allowed types as a
[`com.thoughtworks.xstream.security.WildcardTypePermission`](https://github.com/x-stream/xstream/blob/XSTREAM_1_4_7/xstream/src/java/com/thoughtworks/xstream/security/WildcardTypePermission.java):
    - `com.liferay.portal.model.*`
    - `com.liferay.portal.model.impl.*`
    - `com.thoughtworks.xstream.mapper.DynamicProxyMapper*`
- Allowed type hierarchies each defined as a
[`com.thoughtworks.xstream.security.TypeHierarchyPermission`](https://github.com/x-stream/xstream/blob/XSTREAM_1_4_7/xstream/src/java/com/thoughtworks/xstream/security/TypeHierarchyPermission.java):
    - `com.liferay.portlet.asset.model.AssetLink`
    - `com.liferay.portlet.asset.model.AssetTag`
    - `java.util.List`
    - `com.liferay.portal.model.Lock`
    - `java.util.Map`
    - `com.liferay.portal.model.OrgLabor`
    - `com.liferay.portlet.ratings.model.RatingsEntry`
    - `com.liferay.portal.model.StagedModel`

Any class that is not whitelisted either through the portal property or by
default generates a `com.thoughtworks.xstream.security.ForbiddenClassException`
during the import phase. Thus, if you encounter such an error, you need to add
the fully qualified class name to your `portal-ext.properties` file.

$$$

Let's explore the export process for portlets first.

![Figure 4.7: When exporting portlet data, you can choose what content to include.](../../images/portlet-export.png)

First, you can select a *Date Range* of content that you'd like to export.
Content that has been added to your portlet within your specified date range is
included in the `.lar` file. You also have options of choosing *All* dates or
just the most recently added content, or *Last*.

Next, by checking the *Content* box, you can choose specific content you'd like
to export. When you check the *Content* box, more options appear, allowing you
to choose specific kinds of metadata to include. For example, if you have a wiki
page with referenced content that you don't wish to include, you can simply
check the *Wiki Pages* checkbox and uncheck the *Referenced Content* checkbox.
Another option in the Content section of the Export/Import window is the
selection of content types. Two familiar content types in your portal is
*Comments* and *Ratings*. If you wish to include these entities in your `.lar`
file, select *Change* and select them from the checklist.

Lastly, you can choose whether to include permissions for your exported content.
The permissions assigned for the exported portlet window will be included if the
*Permissions* option is checked. When you check this box, a sub-box called
*Permissions Assigned to Roles* appears. If you wish, you can export your
portlet's permissions but not the permissions assigned to roles. After you've
exported your portlet's data, switch to the *Current and Previous* tab to view
ongoing export processes and the history of past exports.

![Figure 4.8: When importing portlet data, you can choose a LAR file using the file explorer or drag and drop the file between the dotted lines.](../../images/portlet-import.png)

To import portlet data, you can select the LAR using your file explorer or by
dragging and dropping the file between the dotted lines. After selecting the LAR
file, you're given a similar screen to what you'd be offered during export.
Select the appropriate content and permissions, and click *Continue*.

The next screen offers options split into two sections -- *Update Data* and
*Authorship of the Content*. Here's options and descriptions for each section:

**Update Data**

* *Mirror*: All data and content inside the imported LAR will be created as new
the first time while maintaining a reference to the source. Subsequent imports
from the same source will update entries instead of creating new entries.
* *Mirror with overwriting*: Same behavior as the mirror strategy, but if a
document or an image with the same name is found, it is overwritten.
* *Copy as New*: All data and content inside the imported LAR will be created as
new entries within the current site every time the LAR is imported.

**Authorship of the Content**

* *Use the Original Author*: Keep authorship of imported content whenever
possible. Use the current user as author if the original one is not found.
* *Use the Current User as Author*: Assign the current user as the author of all
imported content.

Next, let's discuss the concept of a portlet's scope.
