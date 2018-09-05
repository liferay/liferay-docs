# Built-in Mobile Support [](id=built-in-mobile-support)

Out of the box, there are several features that help make your pages look just
as good and have the same functionality on mobile devices as they do on
a desktop:

-  Liferay Widgets and custom widgets that use Liferay's UI frameworks 
   automatically scale to fit the screen size.

    ![Figure 1: A widget adjusts its size.](../../../../../images/widget-adjustment.png)

-  UI elements like the navigation and Product Menu automatically adjust to
   remain usable on smaller screens.

    ![Figure 2: The main navigation adjusts its size.](../../../../../images/navigation-adjustment.png)

-  When the screen width is low, Liferay combines columns so that all 
   content remains legible.

    ![Figure 3: Columns combine.](../../../../../images/columns-adjustment.png) 
 
-  For web developers, Liferay's theme tools provide a number of tools to help
   ensure optimum mobile performance.

For most business users, this means that all you need to do to display pages on 
Mobile device is to create a page. However, you also have tools available to
verify that everything displays as intended. The Device Simulator
(![Simulation](../../../../../images/icon-simulation.png)) is
a powerful tool that shows you how pages look on different devices. 

## Using the Device Simulator [](id=using-the-device-simulator)

When creating a page or reviewing a page before it is published, one of your 
most important tools is the Device Simulator found in the top right corner of 
every page. The simulator lets you view the current page in a number of 
resolutions based on different display types. There are three predefined 
options:

**Desktop:** Fixes the width to display the page at full size.

**Tablet:** Puts your page in a box as if it is being displayed on a tablet. It
also activates some of Liferay's built-in mobile features.

**Mobile:** Puts your page in an even smaller box to demonstrate how the page
looks to your average smartphone user.

![Figure 4: The Simulation panel defines multiple screen sizes.](../../../../../images/device-simulation.png) 

There are also two options available to display 

**Autosize:** Provides another way to view the default behavior where the page
shrinks and grows based on the width of the browser window.
 
**Custom:** Lets you enter a specific size for testing and fixes the height and
width of the display.

Because modern mobile browsers are built on the same technology as desktop
browsers, the behavior you see in the simulator should match the experience of
users on mobile devices. In addition to making sure the basic layout looks good
and that all functionality remains, it's also important to make sure that
automatic features---like how columns are combined at lower resolutions---don't
have unintended effects.

## Designing Mobile Friendly Pages [](id=designing-mobile-friendly-pages)

@product@ provides the tools you need, but building pages that provide a good 
experience across all kinds of devices still means working across all levels of
web development and publishing. Theme developers must create themes that use
Liferay's frameworks to scale content well across all kinds of displays.
Designers must have multiple screen sizes in mind when designing pages. And
before anything it published it must be thoroughly reviewed to make sure that it
provides the best experience to all of your users.

Now that you've learned about Liferay's tools for making your website mobile 
friendly, let's look at your options for adapting to different types of mobile 
devices.
