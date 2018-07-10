# Catalog Rules [](id=catalog-rules)

Catalog Rules manage the catalog's visibility. They work with 
[user segments](/web/liferay-emporio/documentation/-/knowledge_base/1-0/user-segmentation)
to restrict specified buyers so they can only see a limited portion of the
catalog. This can help focus the attention of customers only authorized
to purchase products associated with a particular support plan, or who want the
convenience of only seeing parts and accessories for equipment they already
own.

By default, all users are blacklisted: if no catalog rules are in place, then no
users (except administrators) will be able to see the catalog at all.

Follow these steps:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Catalog Rules* and click on the
    ![Add](../../images/icon-add.png) button.

2.  Give the rule a *Name* and enable the *Active* toggle. To make the entire
    catalog visible, select *All products* from the *Type* drop-down box. To
    limit the catalog's visibility, select *Categories*.

3.  If you selected *Categories*, Click the *Select* button that appears and
    select the categories you want specified users to be able to see. Click
    *Done*.

4.  Click *Save*. A new tab, *User Segments*, appears at the top of the page.

5.  Click on *User Segments* and then click the
    ![Add](../../images/icon-add.png) button. Select the segment or segments
    that should be limited to the selected categories. Click *Add*.

+$$$

**Note:** In step 2 above, the only type of rule available that actually
restricts the catalog makes products visible by category. With the development
of additional code, you can add other custom types, allowing sections of the
catalog to be selected by individual product, by specification, or by other
criteria. 

$$$
