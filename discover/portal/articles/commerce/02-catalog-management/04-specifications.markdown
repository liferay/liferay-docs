# Specifications

Specifications provide information about the product that--in contrast to
[options](/discover/portal/-/knowledgebasd/7-1/catalog-options)--is not customer
selectable. Like options, however, specifications are defined in multiple parts:
*groups*, *labels* and *values*.

![Figure 1: In this image, *Technical Details* is a group, *Voltage* is a label, and *14.4* is a value.](../../../../images/spec-group.png)

Of course, it would be simple to create a form with a field for each aspect of
a specification. But filling out all three fields for every product in your
catalog would be a time-consuming and error-prone process. To avoid this,
specification groups and labels are created at the catalog level. Rather than
creating them again for each product, users can quickly deploy groups and labels
to a product simply my making selections from a list. The following sections
describe the process.

## Specification Groups

To create a specification group, click on the *Specification Groups* tab in
*Site Menu* &rarr; *Commerce* &rarr; *Catalog*. Then click on the
![Add](../../../../images/icon-add.png) button and fill in the form.

**Title**: This is the text that will appear at the start of each group.

**Description**: A description may be helpful to site administrators, but it
will not be displayed on the product page by default.

**Priority**: This establishes the order in which the specification groups will
be listed. Smallest numbers go first.

**Key**: A programmatic identifier for this option category. This field
auto-fills and can be safely ignored by most users.

Once the form is complete, click *Save*. The next section explains how to assign
specifications to these categories.

+$$$

Unlike labels and values, groups are optional. You can simply ignore groups and
still use specifications to describe your products.

$$$

## Specification Labels

To create a Specification Label, click on the appropriate tab next to the
*Specification Groups* tab. Then click on the
![Add](../../../../images/icon-add.png) button to fill in the following fields:

**Title**: If you want to specify that a product is colored black, then enter
*Color* in this field.

**Description**: A description may be helpful to site administrators, but it
will not be displayed on the product page by default.

**Use in faceted navigation**: Check this box to make search results filterable
for this specification.

**Specification Group**: Pick the group to which this label belongs. This is
optional, and can be overridden at the product level where specification values
are defined.

After filling in the appropriate fields, click save. The label has been created,
but assigning a value to it must be done on the product level.

## Specification Values

To apply your specifications, go to your catalog and select a product. Then open
the *Specifications* tab, click on the ![Add](../../../../images/icon-add.png)
button and select any number of specification labels from the list, and click
*Add* to close the pop-up.

Now your specification labels have been deployed to the product. To assign
values to the labels, click on each individually, enter a value and choose
whether to override the specification group by making a selection from the
drop-down menu.
