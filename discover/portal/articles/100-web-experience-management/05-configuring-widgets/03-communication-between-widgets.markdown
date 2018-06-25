# Communication Between Widgets [](id=communication-between-widgets)

Liferay provides options for communication between widgets, including public 
render parameters and events. Public render parameters are easy to use and can 
be quite powerful. Some Liferay widgets provide a configuration UI to help you 
get the most out of this communication mechanism. To access this UI, open your 
widget's configuration window by clicking on the *Options* icon
(![Options](../../../images/icon-options.png)) and selecting *Configuration*.
Then click on the *Communication* tab.

![Figure 1: You can configure widgets to communicate with each other using public render parameters.](../../../images/app-communication-tab.png)

The screenshot above is for the Wiki, which has six public render
parameters: `categoryId`, `nodeId`, `nodeName`, `resetCur`, `tag`, and `title`.
For each of these parameters, you can configure the widget to ignore the values
coming from other widgets to read the value from another parameter.

Why might it be useful to ignore the values for certain parameters that come
from other widgets? Consider a common use case for the Wiki application. The 
Wiki widget is often used along with the Tags Navigation widget so that when a 
user clicks on a tag of the latter, the Wiki shows a list of pages with that 
tag. An administrator may want the Wiki to always show the front page 
independently of any tag navigation done through other widgets. This is done by 
checking the *Ignore* checkbox so that the values of the parameter
coming from those other widgets are ignored.

Reading the value of a parameter from another widget is an advanced but very
powerful option that allows widgets to communicate with each other even if
their developers didn't intend them to. For example, imagine that the Wiki
is being used to publish information about certain countries. Imagine further 
that there's another widget that allows browsing countries for administrative 
reasons. The second widget has a public render parameter called *country* with 
the name of the country. You'd like the Wiki to show the information from the 
country that's selected in the administration widget. This can be achieved by 
setting the value of the title parameter of the Wiki widget to be read from the 
country parameter of the administration widget. Cool, isn't it?
