# Built-in Mobile Support

Out of the box, you have several features that help make your pages look just 
as good and have the same functionality on mobile devices as they do on a 
desktop:

-  Liferay Widgets and custom widgets which use Liferay's UI frameworks will
    automatically scale to fit the screen size.

![Figure X: A demonstration of how a widget adjusts its size.](../../../../../images/widget-adjustment.png)

-  UI elements like the navigation and Product Menu will automatically adjust to
    remain usable on smaller screens.

![Figure X: A demonstration of how the main navigation adjusts its size.](../../../../../images/navigation-adjustment.png)

-  When the screen width is low, Liferay will combine columns so that all 
    content remains legible.

![Figure X: A demonstration of how columns combine.](../../../../../images/columns-adjustment.png)    
    
-  For web developers, Liferay's theme tools provide a number of tools to help
    ensure optimum mobile performance.

For most business users, this means that all you need to do to display pages on 
Mobile device is to create a page. However, you also have tools available to 
verify that everything is going to display as intended. The Device Simulator (![Simulation](../../../../../images/icon-simulation.png)) in 
particular is a powerful tool that shows you how pages will look on different 
devices. 

## Using the Device Simulator

When creating a page or reviewing a page before it is published, one of your 
most important tools is the Device Simulator found in the top right corner of 
every page. The simulator lets you view the current page in a number of 
resolutions based on different display types. There are three predefined 
options:

-  **Desktop** fixes the width to display the page at full size.

-  **Tablet** puts your page in a box as if it is being displayed on a tablet. 
    It also activates some of Liferay's built it mobile features.

-  **Mobile** puts your page in an even smaller box to demonstrate how the page 
    will look to your average smartphone user.

![Figure X: The Simulation panel.](../../../../../images/device-simulation.png)    

There are also two options available to display 

-  **Autosize** provides another way to view the default behavior where the page
    will shrink and grow based on the width of the browser window.
    
-  **Custom** lets you enter a specific size for testing and fixes the height 
    and width of the display.

Because modern mobile browsers are built on the same technology as desktop 
browsers, you can be sure that the behavior you see in the simulator will match 
the experience of users on mobile devices. In addition to making sure the basic 
layout looks good and that all functionality remains, it's also important to 
make sure that automatic features---like how columns are combined at lower 
resolutions---doesn't have any unintended effects.

## Designing Mobile Friendly Pages

@product@ provides the tools you need, but building pages that provide a good 
experience across all kinds of devices still means working across all levels of 
web development and publishing. Theme developers need to create themes which 
use Liferay's frameworks to scale content well across all kinds of displays. 
Designers need to have multiple screen sizes in mind when designing pages. And 
before anything it published it must be thoroughly reviewed to make sure that 
it will provide the best experience to all of your users.

Now that you've learned about Liferay's tools for making your website mobile 
friendly, let's look at your options for adapting to different types of mobile 
devices.