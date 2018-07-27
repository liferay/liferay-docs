# Find out what others think or do using Polls [](id=find-out-what-others-think-or-do-using-polls)

How well do you know your users? Do you ever wonder what they're thinking? Is
using your site easy for them? How do they feel about the hot-button issues of
the day? Do they prefer dogs over cats? What about the new policy that
management wants to implement? What's their favorite ice cream flavor? When you
use the Polls feature you can find out the answer to these and other questions
that should help you better understand your users. 

There are two portlets involved in making and displaying a poll: the *Polls*
portlet, which is accessed through the Site Administration page, and the *Polls
Display* widget, which can be added to any page.

The Polls portlet helps you set up the poll question and the possible answers
users can select. The Polls Display widget is an instanceable portlet that lets
you select which poll to display, and is the widget you put on the page so users
can vote.

The Polls portlet allows users and administrators to create multiple-choice
polls that keep track of the votes and display results on the page. Many
separate polls can be managed at once.

The Polls Display widget allows users to vote for a specific poll's questions
and see the results. Questions must be created from the Polls portlet on the
Site Administration page. You can display one question at a time or you can
combine several questions inside a nested portlet to create a survey. The Polls
Display widget can also be configured to display a specific poll's questions and
results.

We'll begin by creating a poll.

## Creating a Poll [](id=creating-a-poll)

On your site's Site Administration page, navigate to the *Polls* link under
Content. Click the ![Add](../images/icon-add.png) button. A form appears that
allows you to fill out all the information for your poll.

![Figure 1: Besides the Title and the Polls Question, you must enter data for each of the Choices fields when creating a new poll.](../images/polls-add-new-question.png)

**Title:** Enter the name of the poll question. 

**Polls Question:** Enter the text of the poll question. 

**Expiration Date:** Enter the date and time you want the poll to expire. 

**Choices:** Enter at least two answer options for the poll question. 

**Add Choice:** Enter additional answer options for the poll question. 

**Permissions:** Manage who can view and edit the poll. 

When you have finished creating your poll, click *Save*, and it is added to the
Polls portlet. 

Once a poll is created, it is accessible through the Polls Display portlet until
it expires or is deleted. You can set an expiration date for a poll by selecting
the day and time in the Add Poll form. The default is set to *Never Expire*. 

When a poll expires, users can't enter votes any more, but if a Polls Display
portlet is still publishing it, the poll results are displayed on the page. To
remove an expired poll from a page, remove the Poll Display portlet or
configure it to show another poll question. See the section below for more
details about the Polls Display portlet. 

*Permissions* can be set on individual polls as they are set elsewhere in
Liferay Portal. Permissions can be used, for example, to allow some privileged
users to vote on a certain poll question, while others can only view it. For
further information about permissions, please see chapters 15 and 16. 

As you can see, creating a poll is fairly straightforward. Next, let's complete
the two-step process and put your poll on a page.

## Adding a Poll to a Page [](id=adding-a-poll-to-a-page)

Now that you have created your poll question, it's time to present it to your
users. Follow these steps.

1.  Go to the page where you want to display poll results and add a Polls
    Display widget from *Add* &rarr; *Widgets* &rarr; *Content Management*.

2.  Click *Please configure this portlet to make it visible to all users.*

3.  In the dialog box that appears, select the title of the poll you want to
    display. Click *Save*.

Once the poll question has been successfully placed on the page,you can perform
other tasks using the icons at the bottom of the portlet.

![Figure 2: These buttons provide shortcuts to the widgets configuration, as well
as to some of the Polls Portlet's functionality.](../images/poll-buttons.png)

**Edit Question:** Displays a similar dialog box to the one used to create the
poll. 

**Select Poll:** Displays the same dialog box as Configuration, allowing you to
choose different questions from the drop-down menu. 

**Add:** Allows you to create a new poll. 

## Viewing the Poll Results [](id=viewing-the-poll-results)

All the polls you create are listed in the Polls portlet in *Site
Administration* &rarr; *Content* &rarr; *Polls*. When users vote in the poll,
the data is collected here. Click on a poll see a breakdown of the results.

![Figure 3: Selecting a poll in the Polls portlet puts the data at your fingertips.](../images/polls-results.png)

If you click on one of the listed *Charts*, the portlet will generate an
appropriate visualization of the data.


Below this is an item called *Charts*. This option shows the poll results
represented in various graphs. The graphs are *Area*, *Horizontal Bar*, *Line*,
*Pie*, and *Vertical Bar*.

![Figure 4: This is what the vertical bar graph for the Lunar Resort poll results looks like.](../images/polls-results-vertical-bar.png)

There is also a listing of the users who voted in your poll, how they voted,
and a time/date stamp of when their votes were cast. Registered users are
represented by name while Guest users are represented by a number.
