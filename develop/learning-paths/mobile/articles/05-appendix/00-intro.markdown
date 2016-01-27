# Using the Mobile SDK Instead of Screenlets

In this Learning Path's previous sections, you developed an app that retrieved 
and displayed guestbooks and entries from the Guestbooks portlet developed in 
[the Liferay MVC Learning Path](/develop/learning-paths/mvc). You did this by 
creating two new Screenlets: Get Guestbooks Screenlet and Get Entries Screenlet. 
Creating these Screenlets, however, isn't required. You could accomplish the 
same thing by calling the Mobile SDK directly in your app's activity or fragment 
code, and skip the extra time needed to write the Screenlet components. So why 
bother creating Screenlets? 

Creating Screenlets for your custom portlets is a best practice for several 
reasons. One is that you create them by using a consistent, repeatable 
development model. This means that similar Screenlets have similar code, as is 
the case between the Get Guestbooks and Get Entries Screenlets. You can 
therefore reuse parts of an existing Screenlet's code when creating another, 
similar Screenlet. Another benefit of creating your own Screenlets is that you 
can package and distribute Screenlets for reuse in other apps. Consider the case 
of a hypothetical automotive parts business called *Jalopy Enterprises*. To keep 
track of its inventory, Jalopy has a Liferay instance with a custom parts 
inventory portlet. Everyone in Jalopy, from sales to finance, even though they 
have very different job roles, depends on this portlet. Giving them access to it 
from their mobile devices is crucial. Sales and finance, however, need very 
different mobile apps. 