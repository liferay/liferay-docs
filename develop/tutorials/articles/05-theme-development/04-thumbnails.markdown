# Thumbnails [](id=thumbnails)

Now that your theme is available in Liferay, it's time to dress it up for a
stylistic appeal. Currently in the *Look and Feel* settings, your theme's
thumbnail is a broken image. To remedy this, create a 1080 pixels wide by 864
pixels high image to use as your theme's thumbnail. You may want to take a
snapshot of your theme and resize it to these dimension. It is very important to
abide by these *exact* dimensions or your image will not display properly as a
thumbnail. Save the image as a `.png` file named `screenshot.png` and place it
in your theme's `docroot/_diffs/images` directory (create this directory if it
doesn't already exist). On redeployment, your `screenshot.png` file
automatically displays as your theme's thumbnail.

Now go to the *Look and Feel* settings. Your theme's thumbnail should appear
there, along with the *Classic* theme's thumbnail. 

+$$$

**Tip:** To achieve the 1080x864 resolution in a new browser window, use the
button provided in the Resize Browser web content article displayed on the
Welcome page.

$$$
 
<!-- This tip comes verbatim from the ADDING A SCREENSHOT/THUMBNAIL slide in the
Themes course's 02-building-a-theme.fodp slideshow -->
 
Let's talk about Liferay's JavaScript library next. 
