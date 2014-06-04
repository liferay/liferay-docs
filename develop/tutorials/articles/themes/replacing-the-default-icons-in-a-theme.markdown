# Replacing the Default Theme Icons With Your Own Using Sprites

While Liferay's default icons are great, there may come a time when you wish to
introduce a little more of your own flair and style in your theme. Custom icons
can be the icing on the cake: the last layer of design that kicks it up a notch, 
BAM!

You may be asking yourself "What is a sprite? and why would I use them?" No, I'm
not talking about the delicious, cold, refreshing beverage that advertisements 
try to pressure you into buying at the cinema. A sprite in this case refers to 
an image sprite: a group of images that are combined into one single image.

While loading one image separately will get the job done, it slows down your
load time and decreases the overall efficiency of your site. Each image that 
gets loaded is a separate HTTP-request; if you're using one or two icons, this 
may not be an issue; however, when you start talking about all the icons for a 
site, or even just the icons for navigation, the HTTP-request can pile up fast, 
leaving your images stuck in a 5 O'clock traffic jam.

Now that your interest is peaked, it's time to learn how to make it happen.

## Using Sprites in Your Theme

Typically, if you wanted to use sprites, you would have to use CSS styles to
tell your theme where each image is located on the larger image of the sprite.
Luckily, Liferay takes care of the heavy lifting for you and saves the proper
coordinates to a _sprite.properties file that is generated upon deployment;
furthermore, a _sprite.png file is generated for you.

Follow these steps, and you'll be a sprite master in no time:

1.  Navigate to your themes root directory.
2.  *Open* the `/docroot/images` folder.
3.  Note the names of the png files you wish to change and the folder they are in.
4.  Navigate to your `/docroot/_diffs/` directory.
5.  Recreate the file structure for the images you wish to change.

    For example if you wanted to change the en_US.png icon in the language 
    folder of the `/docroot/images/` directory of your theme, you would recreate 
    the language folder in the `/docroot/_diffs/images/` directory of your theme. 
    So, your file structure would look like this:

    `/docroot/_diffs/images/language/en_US.png`
    
    **It is important that your new icon has the exact same name as the icon you
    wish to replace.**

    To make changes to other images, follow the same steps, making sure to 
    recreate the file structure that you wish to alter.

6.  Deploy/re-deploy your theme.

---

 ![Note](../../images/tip-pen-paper.png) **Note:** if your changes are not 
 showing up after deployment, delete the existing _sprite.png and 
 _sprite.properties files in your 
 /tomcat-xxx/work/Catalina/localhost/theme-name/sprite/images/subdirectory/ you 
 changed, redeploy, and you should be good to go. If you find that your changes 
 still are not showing up, refresh the cache.

---

If you navigate to your /tomcat-xxx/work/Catalina/localhost/theme-name/sprite/
images/subdirectory-of-your-new-icon/ you can observe the changes you made in
the newly updated _sprite.png file.

Now that you have mastered the art of sprite making, go out there and kick it up
a notch, BAM!
