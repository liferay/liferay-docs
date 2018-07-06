# Modified Facet [](id=modified-facet)

The Modified Facet narrows search results down to those that match the searched
keyword and that were created or modified during a certain time period.

![Figure 1: Each time period with matching content is a facet term.](../../../images/search-modified-facet.png)

In addition to selecting a pre-configured time period, Users can select a Custom
Range, specifying a From and To date using a date picker:

![Figure 2: Users can include a Custom Range in the Modified Facet.](../../../images/search-modified-facet-custom.png)

The Modified Facet supports configuration actions:

- Modify existing time ranges
- Delete existing time ranges
- Create new time ranges

Edit or create time ranges using a time range alias.

The available time range aliases include:

    past-hour
    past-24-hours
    past-week
    past-month
    past-year

![Figure 3: The User Facet is configurable.](../../../images/search-user-facet-config.png)

Each Range has an alias and a Label.

By default, all the default ranges end in `*`, which evaluates to *now*. For
example, the past-week range is

    [past-week TO *]

You're not limited to ending Ranges. Instead of the `*`, specify another
time range alias as the ending point.

To set up a range from 12 months ago to one month ago, 

1.  Click the plus button in one of the existing ranges.

2.  Give it the label **1-12 Months Ago**.

Give it a Range value of 

    [past-year to past-month]

This gives you lots of flexibility in using alternative time ranges as Modified
Facet terms.
