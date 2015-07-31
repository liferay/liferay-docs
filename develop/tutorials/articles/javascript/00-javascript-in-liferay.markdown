# JavaScript in Liferay [](id=javascript-in-liferay)

As much as Java is used throughout Liferay's back-end data layer, JavaScript is
used throughout the presentation layer. Liferay uses JavaScript to implement
responsive control logic and provide powerful UI components.  And the scripts
leverage Liferay's component-rich JavaScript library
[AlloyUI](http://alloyui.com/). 

Liferay's JavaScript follows the Module Pattern to encapsulate data and
functionality. It makes available its modules via the `Liferay` global object.
The `Liferay` object is loaded automatically at runtime and available
throughout the portal, its theme, and all portal plugins. It includes a myriad
of sub-objects that are chock-full of useful methods. 
