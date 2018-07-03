# Displaying Product Pages [](id=displaying-product-pages)

Once the catalog is populated, it makes sense to start thinking about how to
display its contents to buyers. In most cases, this means that each product must
have a page of its own.

There are two ways to do this: you can manually create a unique page for each
product, or you can create a generic default page that's automatically populated
with the appropriate content when it is opened.

## Creating a Default Product Page [](id=creating-a-default-product-page)

Follow these steps to create a single generic page to display product details:

1.  [Create a page](/discover/portal/-/knowledge_base/7-1/creating-and-managing-pages).

2.  Deploy a *Commerce Product Details* widget to the page.

When a user clicks on a link to a product page, the product details widget
retrieves the appropriate content from the catalog and displays it on the page.
This content depends on the widget's configuration, but by default it includes
the product's images, description, and price, and a way to purchase it.

+$$$

**Note:** if you place a product details widget on more than one page,
@commerce@ uses the page that was created first as the default product page.

$$$

Keep in mind that whatever other widgets you add to this page appears on the
detail pages for all your products. 

## Creating Product Pages Individually [](id=creating-product-pages-individually)

You can have a default page and individual pages if you want to fine-tune the
look for individual products. When a user clicks a link to a product page,
@commerce@ checks to see if a unique display page has been assigned to the
product. It only displays the default page if no individual page exists.

Follow these steps to create an individual product page:

1.  [Create a page](/discover/portal/-/knowledge_base/7-1/creating-and-managing-pages).

2.  Navigate to the catalog and select the product you want to assign to the
    page. Click the *Configuration* tab and then the *Display Page* sub-tab.
    Click *Choose,* select the page you just created, and click *Done*. Then
    click *Save*.

You can add whatever content and applications you want to this page as well. 
