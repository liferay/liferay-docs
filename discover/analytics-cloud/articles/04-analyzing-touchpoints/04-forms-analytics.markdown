# Form Analytics

[Forms](/discover/portal/-/knowledge_base/7-1/forms) 
are important direct data gathering tools for enterprises, but they're often
maligned by end users who find filling out online forms to be a displeasing
exercise.

Are your web forms providing you invaluable information or turning users away?
Analytics Cloud gives you important insights.

In Analytics Cloud, find the Operations section of the menu and click *Assets*.
There are four tabs, each displaying a paginated list of the Asset Type
indicated by the tab title. The first tab, and the default, is for Forms.

SCREENSHOT

View the summary metrics for an Asset directly in the list, and click on an
Asset to view its detail page.

## Visitor Behavior

As with all Asset types, the Visitors Behavior chart is at the top of its page
and provides a line graph with several data presentations. Choose from four
important metrics:

- Submissions
- Views
- Abandonment
- Completion Time

SCREENSHOT

### Submissions

Submissions counts the number of times the Submit button was clicked on a Form.
It's the gold standard metric for a form, because that''s why the form was
created in the first place: to collect data entered into the form. If the Submit
button isn't clicked, you don't get the data you were after.

The Submissions trend line shows the number of times the Submit button was
clicked each day over the selected time period. 

### Views

Views is a common metric among all Assets (and Pages).

Useful with the time period filter, Views is the number of views for a Form in a
given period of time. It's not the same as the number of visitors, because it
doesn't try to count only unique IP addresses. So over the last 30 days, one
visitor (IP address) could come back to the Page 100 times. That means there are
100 Page Views, but only one visitor (assuming they used the same device to
access the Page each time.  However, a unique view won't be logged for a single
user unless at least 30 minutes of inactivity <!-- need info on whether 30
minutes is accurate and that this is how views are calculated--> on the Page
passes before the user interacts with the Page again.

SCREENSHOT

### Abandonment

Abandonment is the daily percentage of users that interacted with the form but
stopped short of submitting an entry over the selected time period.

### Completion Time

Completion time is a daily average of the time it took for form users to go from
their first interaction with the form until they hit the Submit button.

The time series metrics displayed in the Visitors Behavior chart are paramount
to understanding Asset performance over time. But there's more to Asset
Analytics.

## Views by Segment

Scroll down from the Visitor Behavior chart and you'll see the Views by Segment
bar graph, which is present for all Asset types and Pages.

The Views by Segment bar graph shows how many times the Asset was viewed by up
to the top seven Segments over the selected time period. If more than seven
Segments viewed the Asset, the top six are displayed, and views by the remaining
Segments are aggregated in the seventh bar on the graph, labeled _X More
Segments_.

## Submissions by Location

View a bubble chart to compare the views by country over the selected period. Up
to the top five countries are displayed, and the sixth bubble is an aggregation
of the remaining countries.

## Submissions by Technology

View a stacked bar graph of the Page's views by operating system (grouped by
device type) in the default tab.

SCREENSHOT

Click *Web Browser* to see a donut chart displaying up to the top eight web
browsers over the selected time period. If applicable, remaining web borwsers
are aggregated in the ninth <!-- (ninth what? can't view the test server right
now)-->.

## Field Analysis

<!-- If we remove the common explanations  like the by location and by
technology and by segment charts, to the intro, we can better go
into detail on field analysis. looks like there's a lot to this -->

The Field Analysis chart is unique to Forms. 

Field analysis is displayed as a bar graph. The height of the first bar shows
the total number of people (visitors/views?) who have interacted with the form
in any way, even just scrolling past it on the page. The rest of the bars show
the percentage of those people that who interacted with a specific field. The
gross numbers on the right y-axis are the number of people who interacted with
the field (not so, they don't match the number of interactions displayed for
each field).

Field Analysis gives you these metrics on each form field:

- Number of *Interactions* with the field

- Percentage of users who abandoned the form at this field.

- The time spent interacting with the field (includes abandonments)

- The refill percent for the field. Out of the total number that filled out the
    field, this answers the question, "What percent had to edit or enter new
    information into the field after moving on in the form. 

SCREENSHOT

Field Analysis helps you get down to the field level in diagnosing potential
issues with your forms. For example, in your Newsletter Sign-Up form, if you
included a required field that asked for users to provide their first child's
full name, you'll undoubtedly see a lot of form abandonment on that field, and
not only because some form users don't have children. If you make that field not
required, you'll get less abandonment on that field, and if you remove that
field from the form entirely, you'll eliminate all abandonment from offended
parents. 

## Asset Appears On

The Asset Appears On table includes a Page Name and URL for a Page that's been
pulled in to Analytics Cloud from your Data Source. It's important ot note that
the link doesn't take you to the actual Page with the Asset on it. Instead, it
leads to the Page Analytics view of the Page. From there you can click the URL
to the actual Page.

