# Creating Get Entries Screenlet [](id=creating-get-entries-screenlet)

In this Learning Path's previous section, you created Get Guestbooks Screenlet. 
You still need a way to retrieve and display each guestbook's entries, though. 
You'll do this by creating another Screenlet: Get Entries Screenlet. This 
section in the Learning Path walks you through the steps required to create this 
Screenlet. 

Because you use a consistent development model to create Screenlets, similar 
Screenlets have similar code. As with guestbooks, it makes sense to display 
entries in a list using Android's 
[`ListView`](http://developer.android.com/reference/android/widget/ListView.html). 
This means you can reuse most of Get Guestbook Screenlet's code in Get Entries 
Screenlet. You'll therefore create Get Entries Screenlet using the same sequence 
of steps you used to create Get Guestbooks Screenlet:

1. Getting started: creating the Screenlet's package, and model class. 

2. Creating the Screenlet's UI (its View). 

3. Creating the Screenlet's server call (its Interactor). 

4. Creating the Screenlet class. 

Although this Learning Path section presents complete code snippets, it only 
discusses the code unique to Get Entries Screenlet. You can refer back to the 
previous section for detailed explanations of the code shared with Get 
Guestbooks Screenlet. If you get confused or stuck, refer to the finished app 
that contains the Screenlet code 
[here in GitHub](https://github.com/liferay/liferay-docs/develop/learning-paths/mobile/code/screenlets-app/LiferayGuestbook). 
