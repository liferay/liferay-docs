# Creating a Theme Thumbnail [](id=creating-a-theme-thumbnail-lp-6-2-develop-tutorial)

Now that your theme is available in Liferay, it's time to dress it up for a
stylistic appeal. Currently in the *Look and Feel* settings, your theme's
thumbnail is nonexistent. To remedy this, follow the steps below.

## Step 1. Create a Thumbnail Image

Create a 150 pixels wide by 120 pixels high image to use as your theme's 
thumbnail. You may want to take a snapshot of your theme and re-size it to these 
dimensions. It is very important to abide by these *exact* dimensions or your 
image will not display properly as a thumbnail. Onward to step 2!

## Step 2. Save the image As a PNG

Save it as a `.png` file named `thumbnail.png` and place it in your theme's 
`docroot/_diffs/images` directory (create this directory if it doesn't already 
exist). On redeployment, your `thumbnail.png` file automatically displays as 
your theme's thumbnail.

Now go to the *Look and Feel* settings. Your theme's thumbnail should appear
there, along with the *Classic* theme's thumbnail.

Congrats, now you know how to create a thumbnail for your theme!

## Next Steps

 <!-- URL will probably need updated when added to the new devsite-->
 [Using Developer Mode with Themes](/tutorials/-/knowledge_base/using-developer-mode)
 
 [Making Themes Configurable with Settings](/tutorials/-/knowledge_base/making-themes-configurable)

 [Specifying Color Schemes for Your Themes](/tutorials/-/knowledge_base/specifying-color-schemes-for-your-theme)
