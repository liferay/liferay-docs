# Using Icons with the *liferay-ui* Taglib 

By using meaningful graphics as icons, you can draw your users' attention 
towards important parts of your portlet. Using either Liferay's icons or your 
own custom icons also enhances your portlet's overall design. Think of it as 
wrapping your portlet up in a nice, user-friendly bow. You can use icons in your 
portlets by leveraging the `liferay-ui:icon` taglib. See the figure below for an 
example of icons being used in a portlet.

![Figure 1: Here's an example of a portlet that uses an icon.](../../images/liferay-ui-icon-01.png)

Now it's time to go icon crazy! Get started by learning how to use Liferay's 
icons in your portlets. 

## Using Liferay's Icons in Your Portlet 

Liferay has a wide variety of icons that you can use in your portlets. They are 
located in your `/webapps/ROOT/html/themes/classic/images/common/` directory. 
Follow these steps and you'll be using them in no time:

1. Open the `view.jsp` of your portlet.

2. Reference the liferay-ui and portlet taglibs by adding the following lines 
   of code to the top of your `view.jsp` file:
    
    ```
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
    ```
    
Now that you have the proper taglibs referenced, you can use `liferay-ui:icon` 
in your `view.jsp` to add icons to your portlet.

To use an icon, give its name without its file extension as the value of the 
image attribute of `liferay-ui:icon`. For example, use the following to use 
Liferay's add icon in a portlet: 

    <liferay-ui:icon image="add"/>

The `view.jsp` for using Liferay's add icon near the top of a portlet could look 
something like this: 

    ```
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

    <portlet:defineObjects />

    This is the <b>icon example</b> portlet.

    <liferay-ui:icon image="add"/>
    ```
The figure below shows what the add icon could look like in a portlet:

![Figure 2: Here's what the add icon looks like in a portlet.](../../images/liferay-ui-icon-02.png)

Congrats! You have successfully added a icon to a portlet! Note that the icon in 
the above example appears near the top of the portlet. The position of the icon 
in the portlet is dictated by the position of `liferay-ui:icon` relative to 
other items in `view.jsp`.

There are a few more attributes for `liferay-ui:icon` that can take your design 
to the next level. What if you want to link an icon to a site? No problem! Just 
add a `url` attribute with the site url to `liferay-ui:icon`. For example, this 
code takes the user to the Liferay home page when they click the add icon: 

    <liferay-ui:icon image="add" url="http://www.liferay.com" />

You can also change the tooltip that appears when you hover your mouse over the 
icon. By default, the tooltip is just the name of the icon. This can be useful, 
but sometimes it isn't sufficiently descriptive. For example, you might want to 
use the tooltip to suggest the user take a specific action. Implementing this is 
a piece of cake! Just add a `message` attribute to `liferay-ui:icon` with the 
text you wish to display. The next bit of code tells your user to click the 
icon. Doing so takes them to the Liferay home page.

    <liferay-ui:icon image="add" url="http://www.liferay.com" message="click here"/>

Take a look at the figure below to see what that may look like:

![Figure 3: Here's what the add icon looks like with the changes above when hovered over.](../../images/liferay-ui-icon-03.png)
    
Now that you have an understanding of how to use Liferay's icons in your
portlet, it's time to use some of your own!

## Using Your Own Icons in a Portlet

The process for adding your own icons in a portlet is very similar to the
process of using Liferay's icons. There are just a few minor but important 
differences.

You should note that `liferay-ui:icon` uses sprites that hold multiple icons. 
For instance, all of the navigation icons are in one `_sprite.png` file. When 
you use one of the navigation icons, you are grabing the icon from a larger 
group of icons in a single sprite file. 

You may be thinking, "What is all this sprite talk? I'm not thirsty!". In this 
case, a sprite refers to an image sprite. An image sprite is a group of images 
that are combined into one single image. Each image that is loaded is a separate 
HTTP request to the server. By combining multiple icons into one larger image, 
you can turn 12, 20, or even 100 separate HTTP requests into just 1. Thus, using 
sprites greatly decreases load times and increases efficiency.

Lucky for you, Liferay automatically generates sprites of your icons as long as
they are placed in the proper folder. When it comes to portlets, sprites are 
only generated for `png` files located in the `/docroot/icons/` directory.

To put your own icons in your portlet, just add a `src` attribute to 
`liferay-ui:icon` that points to the location of your custom icon. For example, 
this bit of code puts `/name-of-your-portlet/icons/my-custom-icon.png` in your 
portlet:

    <liferay-ui:icon image="my-custom-icon" src="/name-of-your-portlet/icons/my-custom-icon.png" />
    
The `view.jsp` could look something like this: 

    ```
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

    <portlet:defineObjects />

    This is the <b>icon example</b> portlet.

    <liferay-ui:icon image="my-custom-icon" src="/name-of-your-portlet/icons/my-custom-icon.png" />
    
    ```
    
Deploy your portlet and your new icon appears inside of it. Congrats, you have 
learned how to use your own custom icons in a portlet!

## Next Steps 

[Liferay UI Taglibs](/tutorials/-/knowledge_base/liferay-ui-taglibs-lp-6-2-develop-tutorial)

<!--URl needs updated to correct one once it is added to LDN-->
<!--[Replacing the default theme icons](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/replacing-the-default-icons-in-a-theme)-->

