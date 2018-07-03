# Displaying Product Pages [](id=displaying-product-pages)

Once the catalog is populated, it makes sense to start thinking about how to
display its contents to buyers. In most cases, this means that each product will
need a page of its own.

There are two ways to do this: you can manually create a unique page for each
product, or you can create a generic default page that will be automatically
populated with the appropriate content when it is opened.

## Creating a Default Product Page [](id=creating-a-default-product-page)

Follow these steps to create a single generic page to display product details:

1.  [Create a page](/discover/portal/-/knowledge_base/7-1/creating-and-managing-pages).

2.  Deploy a *Commerce Product Details* widget to the page.

When a user clicks on a link to a product page, the product details widget
retrieves the appropriate content from the catalog and displays it on the page.
This content depends on the widget's configuration, but by default it includes
the product's images, description, and price, as well as UI elements to purchase
it.

+$$$

**Note:** if you place a product details widget on more than one page,
@commerce@ will use the page that was created first as the default product page.

$$$

You can add whatever other content or applications to the page that you want,
but keep in mind that it will appear on the detail pages for not just one but
all of your products. 

## Creating Product Pages Individually [](id=creating-product-pages-individually)

Even if you've already created a default page, you can still create individual
pages if you want to fine-tune the look for individual products. When a user
clicks on  a link to a product page, @commerce@ will first check to see if a 
unique display page has been assigned to the product. It will only display the
default page if no individual page exists.

Follow these steps to create an individual product page:

1.  [Create a page](/discover/portal/-/knowledge_base/7-1/creating-and-managing-pages).

2.  Navigate to the catalog and select the product which you want to assign to
    the page. Click the *Configuration* tab and then the *Display Page* sub-tab.
    Click *Choose,* select the page you just created, and click *Done*. Then
    click *Save*.

You can add whatever content and applications you want to this page as well. You
can also add a product details widget to the page if you want, but keep in mind
that the first page you place the widget on will be used as the default product
page.
