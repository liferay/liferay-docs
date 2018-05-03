# Assigning Options to Products [](id=assigning-options-to-products)

**An option** is a product feature that can be selected or specified by the
buyer. To create an option and apply it to a product, you must first create an
[option template](/web/liferay-emporio/documentation/-/knowledge_base/7-1/options).
Then you can assign that template to products in your catalog.

Remember, option templates are always scoped to the catalog at creation. This
means that they can be assigned to any product in the catalog. Once you've
assigned the template to a product, however, you can make product-scoped changes
to it---changes that only affect that one product.

## Assigning an Option Template to a Product [](id=assigning-an-option-template-to-a-product)

To assign an option template to a product, follow these steps:

1.  Go to the catalog, open a product, and click on the *Options* tab.

2.  Click on the ![Add](../../../images/icon-add.png) button and select option
    templates from the pop-up. Click *Add* to close the pop-up.

## Editing an Option Template at the Product Scope [](id=editing-an-option-template-at-the-product-scope)

Once an option template has been assigned to a product, you can make changes to
it the template that only affect that product. Follow these steps:

1.  Go to the catalog, select the product in question, and open the *Options*
    tab. If you just finished assigning a template to the product in question,
    then you are already on this page.

2.  On the left side of the page is a list of the option templates this product
    deploys. Click on the one you want to edit.

3.  Make changes to the *Option Detail* form on the right side of the page and
    then hit *Save*. The form contains most of the information that was entered
    when the template was created, but includes a *Priority* field. Priority
    indicates where on a page the option is displayed---lower numbers come
    first.

## Creating Option Values at the Product Scope [](id=creating-option-values-at-the-product-scope)

Regardless of whether any catalog-scoped option values exist for a given option,
you can also create values that apply only to a single product. Follow these
steps:

1.  Go to the catalog and select the desired product. Click on the *Options*
    tab, find the template for which you want to create values and click on the
    *values* button next to its title.

    This displays a list of option values, including catalog-scoped values and
    any previously-created product-scoped values. These can be edited or
    deleted, if necessary, without impacting any other products. Just click on
    a value and make changes to the *Option Value Detail* form on the right.

3.  Click on the ![Add](../../../images/icon-add.png) button and fill in the
    *Option Value Detail* form as described above.

4.  Click *Save*. Repeat steps 3 and 4 to create additional values.

