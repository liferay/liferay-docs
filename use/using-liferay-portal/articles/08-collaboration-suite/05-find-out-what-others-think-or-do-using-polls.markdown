# Find out what others think or do using Polls [](id=find-out-what-others-think-or-do-using-polls-lp-6-2-use-useportal)

How well do you know your users? Do you ever wonder what they're thinking? Is
using your site easy for them? How do they feel about the hot-button issues of
the day? Do they prefer dogs over cats? What about the new policy that
management wants to implement? What's their favorite ice cream flavor? When you
use Liferay's Polls feature you can find out the answer to these and other
questions that should help you better understand your users. 

There are two portlets involved in making and displaying a poll: the *Polls*
portlet, which is accessed through the Site Administration page, and the *Polls
Display* portlet, which can be added to any page in the portal.

The Polls portlet helps you set up the poll question and the possible answers
users can select. The Polls Display portlet is an instanceable portlet that
lets you select which poll to display, and is the portlet you put on the page
so users can vote.

The Polls portlet allows users and administrators to create multiple choice
polls that keep track of the votes and display results on the page. Many
separate polls can be managed; a separate portlet called Polls Display can be
configured to display a specific poll's questions and results.

The Polls Display Portlet allows users to vote for a specific poll's questions
and see the results. Questions must be created from the Polls portlet on the
Site Administration page. You can display one question at a time or you can
combine several questions inside a nested portlet to create a survey.

We'll begin by creating a poll.

## Creating a Poll 

On your site's Site Administration page, navigate to the *Polls* link under
Content. Click the *Add Question* button. A form appears that allows you to fill
out all the information for your poll.

![Figure 8.26: Besides the Title and the Polls Question, you must enter data for each of the Choices fields when creating a new poll.](../../images/polls-add-new-question.png)

**Title:** Enter the name of the poll question. 

**Polls Question:** Enter the text of the poll question. 

**Expiration Date:** Enter the date and time you want the poll to expire. 

**Choices:** Enter at least two answer options for the poll question. 

**Add Choice:** Enter additional answer options for the poll question. 

**Permissions:** Manage who can view and edit the poll. 

When you have finished creating your poll, click *Save*, and it is added to the
Polls portlet. 

As more polls are created in the Control Panel, they become accessible through
the Polls Display portlet until they are either deleted or they expire. You can
set an expiration date for a poll by selecting the day and time in the Add Poll
form or in the New Question form. The default is set to *Never Expire*. 

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

## Adding a Poll to a Page 

Now that you have created your poll question, it's time to present it to your
users. Navigate to your site and add the Polls Display portlet to a page. It is
available from the *Add* &rarr; *Applications* &rarr; *Content Management* menu.

The Polls Display portlet may look strange when it first appears on your page.
That's because it's not configured. Before visitors to your site can use the
poll, they must be able to access it. Click on the link labeled *Please
configure this portlet to make it visible to all users, and a dialog box like
the one below appears.

![Figure 8.27: In the initial configuration of the Polls Display portlet, the Question field will remain blank until you select the appropriate poll question. ](../../images/polls-display-config.png)

Under the Setup tab is a menu option labeled *Question*. Selecting this option
displays the name of the poll you created. Choose it, click *Save*, and it is
displayed on the page. That, in a nutshell, is how you create a poll, but there
is another way to add a question to the Polls Display portlet.

Start by navigating to your site and placing the Polls Display portlet on a
page. Using the icons in the lower left of the portlet, choose the *Add
Question* button. A new form appears that lets you create another question.
When you are done filling out the form, click *Save* and you new poll appears
on the page.

Once the poll question has been successfully placed on the page, you can
perform other tasks by using the icons in the lower left corner of the portlet.
Besides adding questions, you can also edit the currently selected question or
select existing questions.

![Figure 8.28: These three buttons, highlighted in red, allow you to manage the configuration of the poll. Notice this poll has expired.](../../images/polls-config-buttons.png)

**Edit Question:** Displays a similar dialog box to the one used to create the
poll. 

**Select Question:** Displays the same dialog box as Configuration, allowing
you to choose different questions from the drop-down menu. 

**Add Question:** Allows you to create a new question. 

You can also manage the Polls Display portlet by clicking the gear symbol in
the upper right corner of the portlet's title bar. Now let's see the poll
results.

## Viewing the Poll Results 

When you create a poll question, it appears in a list in the *Content* section
of Site Administration. After users vote in the poll, the data is collected
here. If you select it, the name and the question, as well as a breakdown of the
poll results appears, including percentages and total number of votes per answer
and the total number of votes cast.

![Figure 8.29: Selecting a poll in the Polls portlet allows you to see all the information related to the poll results.](../../images/polls-results.png)

Below this is an item called *Charts*. This option shows the poll results
represented in various graphs. The graphs are *Area*, *Horizontal Bar*, *Line*,
*Pie*, and *Vertical Bar*.

![Figure 8.30: This is what the vertical bar graph for the Lunar Resort poll results looks like.](../../images/polls-results-vertical-bar.png)

There is also a listing of the users who voted in your poll, how they voted,
and a time/date stamp of when their votes were cast. Registered users are
represented by their screen name while Guest users are represented by a number.

With Liferay Polls you can do many things. You can ask users very specific
questions or you can use Polls to create a little fun for your community. As
with most things in Liferay, you are only limited by your imagination. Next,
let's learn how to use Liferay's Announcements portlet.
