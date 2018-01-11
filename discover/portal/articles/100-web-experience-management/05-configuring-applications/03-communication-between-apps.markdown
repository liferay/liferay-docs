# Communication Between Apps [](id=communication-between-apps)

@product@ implements several communication mechanisms across apps including
those specified by the JSR-286 standard: public render parameters and events.
Public render parameters are easy to use and can be quite powerful. Some
Liferay apps provide a configuration UI to help you get the most out of
this communication mechanism. To access this UI, open your application's
configuration window by clicking on the *Options* icon
(![Options](../../../images/icon-options.png)) and selecting *Configuration*.
Then click on the *Communication* tab.

![Figure 1: You can configure apps to communicate with each other using public render parameters.](../../../images/app-communication-tab.png)

The screenshot above is for the Wiki application, which has six public render
parameters: `categoryId`, `nodeId`, `nodeName`, `resetCur`, `tag`, and `title`.
For each of these parameters, you can configure the app to ignore the values
coming from other apps to read the value from another parameter.

Why might it be useful to ignore the values for certain parameters that come
from other apps? Consider a common use case for the Wiki application. The Wiki
app is often used along with the Tags Navigation app so that when a user clicks
on a tag of the latter, the Wiki app shows a list of pages with that tag. In
some cases, an administrator may want the Wiki app to always show the front page
independently of any tag navigation done through other apps. This can be
achieved by checking the *Ignore* checkbox so that the values of the parameter
coming from those other apps are ignored.

Reading the value of a parameter from another app is an advanced but very
powerful option that allows apps to communicate with each other even if
their developers didn't intend them to. For example, imagine that the Wiki
application is being used to publish information about certain countries. Imagine
further that there's another app that allows browsing countries for
administrative reasons. The second app has a public render parameter called
*country* with the name of the country. You'd like the Wiki to show the
information from the country that's selected in the administration app.
This can be achieved by setting the value of the title parameter of the Wiki
app to be read from the country parameter of the administration app.
Cool, isn't it?
