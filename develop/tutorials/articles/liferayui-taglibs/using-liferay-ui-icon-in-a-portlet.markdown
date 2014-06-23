# Using Icons with the liferay-ui Tag 

Icons help communicate to your users right away with a graphic that is easily 
recognizable. Using icons in your portal can enhance your overall design, 
wrapping it up in a user-friendly bow.

Now that you understand the value of icons, it's time to go icon crazy, as you 
explore how to use icons in your portlets.
 
## Using Liferay's Icons in A Portlet

While you can use your own custom icons, Liferay has a wide array of icons that 
are available to you.

---

 ![Note](../../images/tip-pen-paper.png) **Note:** You'll find the list of 
 available icons in your `/webapps/ROOT/html/themes/classic/images/common/` 
 directory.

--- 

Follow these steps and you'll be using icons in no time:

1.  Open the `view.jsp` of your portlet.

2.  Reference the liferay-ui and portlet taglibs by adding them to the top of 
your 'view.jsp'
    
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
    
Now that you have the proper taglibs referenced, you can use liferay-ui:icon in 
your portlet.
    
To use a icon, place the name of the icon you desire, dropping off the 
file extension, as the value of the image attribute of the liferay-ui:icon tag. 
For example, to use the add icon, your code would look like this:

    <liferay-ui:icon image="add"/>
    
And here is what the full code for the `view.jsp` may look like:

    ```
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

    <portlet:defineObjects />

    This is the <b>icon example</b> portlet.

    <liferay-ui:icon image="add"/>
    ```

Congrats! You have successfully added a icon to a portlet! There are a few more 
attributes for liferay-ui:icon that can take your design to the next level 
though. What if you wanted to link to a site when a user clicked on an icon? No 
problem. Add a `url` attribute to the liferay-ui:icon tag:

    <liferay-ui:icon image="add" url="http://www.google.com" />

If you hover your mouse over the icon, you will notice that two dialogs pop up
with the name of the icon. What if you wanted to change the text in the dialog?
piece of cake: Add a message attribute to the liferay-ui:icon tag with the text 
you wish to display:

    <liferay-ui:icon image="add" url="http://www.google.com" message="click here"/>

Now that you have an understanding of how to use Liferay's icons in your
portlet, it's time to use some of your own.

## Using Your Own Icons in A Portlet

The process for adding your own icons in a portlet is very similar to the
process of using Liferay's icons, with a few minor, but distinct changes.

One important thing that should be noted is that liferay-ui:icon uses sprites
that hold multiple icons. For instance, all of the navigation icons are in one
`_sprite.png` file. So, when you use one of the navigation icons in the
liferay-ui:icon tag, you are grabing the icon from a larger group of icons in a
sprite file. 

You may be thinking, "what is all this sprite talk? I'm not thirsty"; well, 
it's good that you're not thirsty because that's not what sprite refers to in 
this case. A sprite refers to a image sprite, which is a group of images that 
are combined into one single image. Each image that is loaded is a separate 
HTTP-request to the server. By combining multiple icons into one larger image, 
you can turn 12, 20, or even 100 separate HTTP-requests into 1. So, as you can 
imagine, it really decreases load times and increases efficiency.

Lucky for you, Liferay automatically generates sprites of your icons, as long as
they are placed in the proper folder. When it comes to portlets, sprites are 
only generated for .png files located in the `/docroot/icons/` directory.

Follow the same steps as you did for using Liferay's icons, but this time add a 
src attribute to the liferay-ui:icon tag that points to the location of your 
custom icon. For example:

    <liferay-ui:icon image="my-custom-icon" src="/name-of-your-portlet/icons/my-custom-icon.png" />
    
Here is what the full code for the `view.jsp` may look like:

    ```
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

    <portlet:defineObjects />

    This is the <b>icon example</b> portlet.

    <liferay-ui:icon image="my-custom-icon" src="/name-of-your-portlet/icons/my-custom-icon.png" />
    
    ```
    
Deploy your portlet and you'll see your new icon inside of it with the settings 
you gave. Congrats, you have learned how to use your own custom icons in a
portlet!

## Next Steps

<!--URl needs updated to correct one once it is added to LDN-->
<!--[Replacing the default theme icons](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/replacing-the-default-icons-in-a-theme)-->

