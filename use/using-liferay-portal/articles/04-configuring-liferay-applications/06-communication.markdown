# Communication [](id=communication)

Liferay implements several communication mechanisms across portlets including
those specified by the JSR-286 standard: public render parameters and events.
Public render parameters are easy to use and can be quite powerful. Some
Liferay portlets provide a configuration UI to help you get the most out of
this communication mechanism. To access this UI, open your portlet's
configuration window by clicking on the gear icon and selecting
*Configuration*. Then click on the *Communication* tab. 

![Figure 4.10: You can configure portlets to communicate with each other using public render parameters.](../../images/portlet-communication-tab.png)

The screenshot above is for the Wiki portlet, which has six public render
parameters: categoryId, nodeId, nodeName, resetCur, tag, title. For each of
these parameters, you can configure the portlet to ignore the values coming
from other portlets to read the value from another parameter.

Why might it be useful to ignore the values for certain parameters that come
from other portlets? Consider a common use case for the Wiki portlet. The Wiki
portlet is often used along with the Tags Navigation portlet so that when a
user clicks on a tag of the latter, the Wiki portlet shows a list of pages with
that tag. In some cases, an administrator may want the Wiki portlet to always
show the front page independently of any tag navigation done through other
portlets. This can be achieved by checking the *Ignore* checkbox so that the
values of the parameter coming from those other portlets are ignored.

Reading the value of a parameter from another portlet is an advanced but very
powerful option that allows portlets to communicate with each other even if
their developers didn't intend them to. For example, imagine that the Wiki
portlet is being used to publish information about certain countries. Imagine
further that there's another portlet that allows browsing countries for
administrative reasons. The second portlet has a public render parameter called
*country* with the name of the country. We'd like the Wiki to show the
information from the country that's selected in the administration portlet.
This can be achieved by setting the value of the title parameter of the Wiki
portlet to be read from the country parameter of the administration portlet.
Cool, isn't it?
