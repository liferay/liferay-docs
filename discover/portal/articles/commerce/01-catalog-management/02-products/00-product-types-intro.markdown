# Product Types

The catalog supports three product types: simple, grouped and virtual.

**Simple Products:** A simple product is any physical product for which inventory is tracked. Almost
any physical item can be a simple product.

**Grouped Products:** A grouped product consists of two or more products from the catalog,
packaged together to be sold as a single item.  Grouped products are not tracked
in inventory--instead, the catalog tracks all of the products contained in the
group individually. A grouped product can contain simple products, other grouped
products, or virtual products.

**Virtual Products:** A virtual product is any non-physical product, for which
there is typically no inventory tracking at all. This may mean that the product
is downloaded rather than shipped, as in the case of a music file or an
electronic publication. Services, warranties and other goods that might be
neither shipped nor downloaded would also be virtual products.

## Creating a Product

To create a product:

1.  From the catalog's *Products* tab, click on the
    ![Add](../../../../images/icon-add.png) button at the top-right of the
    screen and then select the type of product you wish to create: simple,
    grouped or virtual.

2.  Next, you'll be prompted to enter basic product information into the *Add
    Product* form. Fill in the following fields if applicable:

**Title**: Name your product. This is the only required field.

**Short Description**: Enter an abstract of the product's full description for
use where brevity is desired. Text entered here could be displayed where the
product appears in thumbnail form or in search results--anywhere space is
limited.

**Full Description**: This text would typically be displayed to customers on the
product page. The integrated text editor provides a number of formatting options
that appear when text is selected, and clicking on the *Add* button that appears
when the editor is in focus provides additional options. For more flexibility,
click on the *Code View* (</>) button on the right to edit the html directly.

**Friendly URL**: This is the URL for the product page. It auto-fills but can be
changed.

**Meta Title, Meta Keywords, Meta Description**: descriptors entered in these
fields will help search engines locate your products. 

**Schedule**: Products can be displayed to customers for a preset window of
time. If you don't want to bother with scheduling, make sure the *Never
Expire* box is checked. Unchecking the *Published* box will remove the product
from customers' view.

    Once the *Add Product* form is complete to your satisfaction, click the
    *Publish* button to add the product to the catalog. The product tab bar appears
    at the top of the screen (and can be reached again at any time by navigating to
    the catalog and selecting a product). The first tab, *Details*, is populated
    with your responses from the *Add Product* form.

3.  Enter additional information specific to the product type you selected in
    step 1.

    **For a Simple Product**, there is no additional information to enter. When
    you create simple product, the standard product tab bar is displayed,
    starting with a *Details* tab where you can edit entries from the *Add
    Product* form.

    **When a Grouped Product** is created, it is necessary to specify the
    products that it will contain. A grouped product has an extra
    tab--*Grouped*--in its product tab bar. From the *Grouped* tab, click on the
    ![Add](../../../../images/icon-add.png) button in the upper-right to select
    any number of products. Once products have been added to the group, they can
    be edited individually (![options](../../../../images/icon-options.png)
    &rarr; *Edit*) to set a quantity and a priority (priority indicates the
    order in which items will be displayed--lower numbers come first).

    ![Figure 1: The features that make a grouped product unique can be conveniently found in the *Grouped* tab.](../../../../images/grouped-product.png)

    **A Virtual Product** also has an extra tab--*Virtual*--in its product tab
    bar. The *Virtual* tab contains the following fields which should be filled
    in if applicable:

**URL/Select File**: If your virtual product is stored at a web address,
enter the URL here. Alternatively, click *Select* to upload a local file
or select one that was uploaded previously.

**Activation Status**: Select the stage in the order process at which the
product becomes available for download.

**Duration**: Enter the number of days after purchase in which customers
will have access to the file. A value of 0 sets no limit.

**Max Usages**: Set the number of times a customer can download the file.
A value of 0 sets no limit.

**Sample**: Upload or enter a URL for a sample file that will be
available to prospective buyers. The *Use Sample* box must be checked
before customers will see the sample.

**Terms of Use**: Either enter a Terms of Use document directly into the
integrated editor, or select a document previously uploaded to Web
Content.
