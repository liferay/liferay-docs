# Displaying Category Pages [](id=displaying-category-pages)

Users have two options for displaying the contents of a category on a page. You
can manually create a category display page for each category, or you can create
a single page to display content dynamically depending on the category
a customer selects.

There are advantages to both approaches: creating pages individually permits
greater customization, but a single default page is significantly less labor
intensive. Fortunately, you don't have to choose: you can use a default page to
set certain standards for appearance and consistency, and then override the
default with a custom page for any categories on which you want to lavish more
attention.

Once your categories have pages, the Category Navigation widget can be used to
help buyers easily find the products they want.

## Creating a Default Category Page [](id=creating-a-default-category-page)

Follow these steps to create a single generic page to display category content:

1.  [Create a page](/discover/portal/-/knowledge_base/7-1/creating-and-managing-pages). 

2.  Go to *Site Menu* &rarr; *Build* &rarr; *Pages* and click the
    ![Options](../../images/icon-options.png) button next to the page you just
    created. Select *View*.

3.  Add a *Category Content* widget to the page.

The category content widget does two things. First, it identifies the page you
just created as the default category page. When a link in a *Commerce Category
Navigation* widget is clicked, the navigation widget finds the first page in the
site with a category content widget and opens that page. Second, the category
content widget displays the category's
[image](/web/liferay-emporio/documentation/-/knowledge_base/1-0/product-categories#images).

You can add other content and applications to the page: for instance,
a *Commerce Search Results* widget automatically displays products from the
appropriate category when the page is viewed. Related widgets, such as *Option
Facets* or *Specification Option Facets*, filter search results.

+$$$

**Note:** The commerce category navigation widget must be configured to link to
the category page. Once the widget is on a page, open its *Configuration*
dialog. From the *Vocabulary* drop-down, select the vocabulary that contains
your product category tree. See 
[Product Categories](/web/liferay-emporio/documentation/-/knowledge_base/1-0/product-categories)
for more detail.

$$$

## Creating an Individual Category Page [](id=creating-an-individual-category-page)

To create a unique page for a single category, follow these steps:

1.  [Create a page](/discover/portal/-/knowledge_base/7-1/creating-and-managing-pages).

2.  Go to *Site Menu* &rarr; *Categorization* &rarr; *Categories* and find the
    category you want to create a page for. Then click
    ![Option](../../images/icon-app-options.png) &rarr; *Edit* and select the
    *Category Display Page* tab.

3.  Click *Choose*, select the page you just created, and click *Done*.

4.  Change the *Friendly URL* to make the page easily navigable.

5.  Click *Save*.

You can put whatever content and applications you want on this page, but unlike
the generic page it does *not* need a category content widget. When a user
clicks on a category, the commerce category navigation widget first checks to
see if a unique page has been designated for the selected category. If no page
has been designated, it then checks for a category content widget.

An individual category page automatically overrides the generic page; no further
action is required other than to create the page and assign it to a category.
