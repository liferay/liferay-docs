# Product Relations [](id=product-relations)

Product relations can be used to connect products irrespective of category,
typically for the purpose of displaying links to one product on the detail page
of another.

Here's how it works: Every related product must be assigned to a *Product
Relationship Type*. These products can then be displayed by a product publisher
widget configured to source data from the appropriate type.

By placing more than one product publisher widget on a product detail page, you
can list multiple sets of related products. For instance, one widget might
display a set of products that are alternatives to the product featured on the
page, while another might display products that are accessories to the featured
product.

Follow these steps:

1.  Add product relationship types.

2.  Create product relationships.

3.  Configure your product detail page(s) to display relationships.

+$$$

**Note:** If your site was built from an accelerator, then a number of product
relationship types are already in place:

**Up-sell:** Display products that are comparable to the featured product
but have a higher sale value.

**Spare:** Display products that are spare parts or components for the featured
product.

**Related:** A catch-all set.

**Accessories:** Display add-on products for use in conjunction with the featured products.

**Cross-sell:** Display products complementary to the featured product.

If you built your site with an accelerator and these options meet your needs,
skip the next step.

$$$

## Adding Product Relationship Types [](id=adding-product-relationship-types)

Since the only function of a product relationship type is to distinguish product
relationships from those of other types, the only unique property it needs is
a name. Follow these steps to create and name a new product relationship type:

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*. Find
    the *Commerce* section, click *Catalog* and go to the *Product Relations*
    tab.

2.  Click *Add* to create a new type, or click the
    ![Options](../../../images/icon-options.png) button to edit or delete an
    existing one.

3.  Enter a name and click *Save*.

## Creating Product Relations [](id=creating-product-relations)

Follow these steps:

1.  Go to the catalog, select a product, and click the *Product Relations*
    tab.

2.  Select a relationship type from the tabs on the left.

    ![Figure 1: The row of tabs at the left allows you to create multiple sets of related products.](../../../images/product-relations.png)

3.  Click the ![Add](../../../images/icon-add.png) button and then check the
    box next to each product you want to set as a product relation of this type.
    Click *Add*.

## Displaying Product Relations [](id=displaying-product-relations)

You can display product relations on a product display page by configuring
a product publisher widget. You must have a 
[product display page](web/commerce/documentation/-/knowledge_base/1-0/displaying-product-pages)
in place first.

Follow these steps:

1.  Go to your product display page and place a *Product Publisher* widget on the
    page.

2.  Go to the widget's ![Options](../../../images/icon-app-options.png) menu and
    click *Configuration*.

3.  Set *Product-Selection* to *dataSource*. Then go to the *Data-Source*
    drop-down  menu and choose a product relationship type to be displayed in
    the widget.

4.  Click *Save* and close the dialog box.

That's it! Your product page should now display the related products you
specified.
