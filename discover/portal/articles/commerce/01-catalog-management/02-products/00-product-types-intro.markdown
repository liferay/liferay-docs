# Product Types

The catalog supports three product types: simple, grouped and virtual. These
types are distinguished by the way the catalog tracks each in inventory.

## Simple Products

A simple product is any product for which inventory is tracked. In practice,
this means that almost any physical item can be a simple product. 

## Grouped Products

A grouped product consists of two or more products from the catalog,
packaged together to be sold as a single item.  Grouped products are not tracked
in inventory--instead, the catalog tracks all of the products contained in the
group individually. A grouped product can contain simple products, other grouped
products, or virtual products.

## Virtual Products

A virtual product is a product for which there is no inventory tracking at all.
This may mean that the product is downloaded rather than shipped, as in the case
of a music file or an electronic publication. It is also useful for selling
services, warranties and other goods that might be neither shipped nor
downloaded.

## Creating a Product

To create a product:

1. Click on the ![Add](../../../../images/icon-add.png) button at the top-right
   of the screen and then select the type of product you wish to create: simple,
   grouped or virtual.

2. Enter [Basic Product Information](discover/portal/-/knowledge_base/7_1/basic-product-information)
   into the *Add Product* form.

3. Enter information specific to the product type you selected.

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
   &rarr; *Edit*) to set a quantity and a priority (priority indicates the order
   in which items will be displayed--lower numbers come first).

   ![Figure 1: The features that make a grouped product unique can be conveniently found in the *Grouped* tab.](../../../../images/grouped-product.png)

   **A Virtual Product** also has an extra tab--*Virtual*--in its
   product tab bar. The *Virtual* tab contains the following fields:

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

+$$$

The *Terms of Use* editor offers simple formatting options via the GUI, as well
as complete html functionality via the *Code View* (</>) button.

$$$
