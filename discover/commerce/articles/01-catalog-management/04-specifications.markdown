# Specifications [](id=specifications)

A specification stores a single piece of product information. A product's
dimensions, color, weight, capacity, or almost any other attribute can be
recorded as a specification. Specifications make it easy to present product
information and simplify the process of comparing similar products.

![Figure 1: Specifications are most visible on product display pages.](../../images/specifications.png)

To organize specifications and make them easy to display and maintain, the data
are structured into three components: groups, labels and values.

**A Specification Group** is a container for specifications. Several
specifications with common characteristics can be organized into a group. In
the image above, *Hardware* is a group.

**A Specification Label** names a specification. In the image above, *Display*
is a label.

**A Specification Value** provides product-specific information. In the image
above, 1920x1080 is a value.

![Figure 2: Specification groups and labels exist independently of products, but values do not.](../../images/specification-diagram.png)

## Specification Groups [](id=specification-groups)

Follow these steps to create a specification group:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Catalog*, open the
    *Specifications* tab and then the *Specification Groups* sub-tab. 

2.  Then click the ![Add](../../images/icon-add.png) button and fill in
    the following fields:

**Title**: Give the group a name. Typically a group's title will indicate what
its labels have in common.

**Description**: If necessary, explain the group more fully.

**Priority**: Priority establishes the order to display the specification
groups. Smallest numbers go first.

**Key**: A unique identifier for referring to this group programmatically. As
this field is filled automatically, most users can safely leave it alone.

Once the form is complete, click *Save*.

## Specification Labels [](id=specification-labels)

Follow these steps to create a specification label:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Catalog*, open the
    *Specifications* tab and then the *Specification Labels* sub-tab.

2.  Click on the ![Add](../../images/icon-add.png) button and fill in
    the following fields:

**Title**: Give the label a name that describes the values that may be assigned
to it.

**Description**: If necessary, explain the label more fully. 

**Use in faceted navigation**: Check this box to make search results filterable
for this specification.

**Specification Group**: Pick the group to which this label belongs. This is
optional and can be overridden at the product level where specification values
are defined (see below for details).

Click *Save*. The label has been created, but assigning a value to it must
be done on the product level.

## Specification Values [](id=specification-values)

Specification values are more limited in scope than groups and labels. Groups
and labels are scoped to the catalog, which means that once they are created,
they can be assigned to any product in the catalog. Values, however, are scoped
to the product, and have no impact anywhere but on the product to which they
are assigned.

To create a specification label, you must first assign a label to a product.

1.  Go to *Site Menu* &rarr; *Catalog*, select a product, and click on the
    *Specifications* tab.

2.  Click on the ![Add](../../images/icon-add.png) button and select from the
    list of option labels that is displayed. Click *Add* to close the pop-up.
    This assigns the selected labels to the product.

Next, edit the label to give it a value.

1.  Click on the ![Options](../../images/icon-options.png) button next to the
    new label and hit *Edit*.

2.  Enter a value. For example, if your label is *Material*, then the value
    should describe what the product is made of: *polymer*, *ceramic*, or
    *stainless steel*. 
 
3.  Set the priority and hit *Save*.

If you want, you can also override the specification group that was selected
when the label was created. When editing the label to assign a value, you can
choose any group from the *Group* drop-down box. This change applies only to the
current product---it has no effect on any other products which use the same
label.
