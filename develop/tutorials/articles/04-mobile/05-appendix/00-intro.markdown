# Using the Mobile SDK Instead of Screenlets [](id=using-the-mobile-sdk-instead-of-screenlets)

In this Learning Path's previous sections, you developed an app that retrieved 
and displayed guestbooks and entries from the Guestbooks portlet developed in 
[the Liferay MVC Learning Path](/develop/learning-paths/mvc). You did this by 
creating two new Screenlets: Get Guestbooks Screenlet and Get Entries Screenlet. 
Creating these Screenlets, however, isn't required. You can accomplish the same 
thing by calling the Mobile SDK directly in your app's activity or fragment 
code, and skip the extra time it takes to write the Screenlet components. So why 
bother creating Screenlets? 

Creating Screenlets for your custom portlets is a best practice for several 
reasons. One is that you use a consistent, repeatable development model to 
create Screenlets. This means that similar Screenlets have similar code, as is 
the case between the Get Guestbooks and Get Entries Screenlets. You can 
therefore reuse parts of an existing Screenlet's code when creating a similar 
Screenlet. Another benefit of creating your own Screenlets is that you can 
package and distribute them for reuse in other apps. Reusing Screenlets saves 
time if your organization has several apps that need to display similar data. 
It also saves time if your organization rebrands or adopts different artwork, 
since Screenlet UIs are fully pluggable. 

Consider the case of the hypothetical automotive parts business *Jalopy, Inc*. 
To keep track of its inventory, Jalopy has a Liferay instance with a custom 
parts inventory portlet. Everyone in Jalopy's warehouse, even though they work 
in different departments, depends on this portlet. And they need access to it 
from their mobile devices while they're on the warehouse floor. By developing 
and packaging a Screenlet for viewing this portlet's contents, Jalopy's mobile 
developers can use the Screenlet in different apps that are tailored to each 
department's requirements. This saves them from reinventing the wheel each time 
they develop an app that displays the inventory portlet's contents. And when 
Jalopy decides to rebrand (let's face it, the name *Jalopy* doesn't inspire 
confidence), they can change the Screenlet's UI and this change will be 
reflected in all the apps that use the Screenlet. There's no need to make the 
change separately in each app. 

But what if you only need to create a single app? What if no one else needs to 
use your Screenlets? We still recommend creating Screenlets. In this case, you 
should create any Screenlets you need in their own packages inside your Android 
project, separate from the package containing the rest of your app's code. This 
is what you did when you created the Get Guestbooks and Get Entries Screenlets. 
This separation of concerns makes maintaining your app easier. Even though it 
takes longer to develop Screenlets than to use the Mobile SDK directly in your 
app's activity or fragment code, you often end up saving time over the long run 
due Screenlets' lower maintenance requirements. 

Note, however, that there may be situations where you don't need or want to 
create Screenlets. For example, creating a Screenlet doesn't make sense if you 
don't need a UI to send data to or receive data from your portal. Screenlets 
must have UIs. Also, despite the benefits of Screenlets, you may simply prefer 
using only the Mobile SDK. There's nothing wrong with that; the Mobile SDK is a 
fine product! So what would this Learning Path's app look like if you developed 
it with the Mobile SDK instead of the Get Guestbooks and Get Entries Screenlets? 
Although this app's UI would be the same, its code would be very different. The 
rest of the articles in this appendix walk you through the steps necessary to 
create this app. 
