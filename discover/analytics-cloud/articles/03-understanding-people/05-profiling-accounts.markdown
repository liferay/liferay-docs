# Profiling Accounts [](id=profiling-accounts)

If you have a Salesforce data source, Analytics Cloud can import and analyze 
Salesforce account data. By combining Salesforce account data with data from 
other sources, Analytics Cloud presents a holistic view of the account and those 
in it. 

To view account analytics, select *Accounts* from the left navigation panel. The 
accounts are displayed in a searchable table that lists the following data for 
each account: 

-   Account Type
-   Individuals
-   Total Activities
-   30-Day Engagement Score

![Figure 1: The Accounts tab lists the Salesforce accounts imported in Analytics Cloud.](../../images/accounts.png)

Click an account to view its information, which is organized into these tabs: 

-   Overview
-   Activities
-   Interests
-   Segments
-   Individuals
-   Details

The sections that follow describe each. 

## Overview [](id=overview)

The Overview tab presents summaries of the account's data. It displays these 
summaries via these panes: 

-   Account Activities
-   Account Firmographics
-   Contact Information
-   Account Interest Topics
-   Known Individuals
-   Associated Segments

If more information is available for each, you can access it via its link. For 
example, a *View All Activities* link is at the bottom of the Account Activities 
pane. Clicking this link takes you to the Activities tab, which shows more 
detailed information about activities. 

![Figure 2: The Overview tab displays summaries of the account's data.](../../images/accounts-overview.png)

## Activities [](id=activities)

The Activities tab shows the activities of the individuals in the account. A 
histogram shows the number of activities by date and the average engagement 
score for the account's individuals. A searchable table below the histogram 
shows more detail about the activities. 

![Figure 3: The Activities tab displays data on activities of the account's individuals.](../../images/accounts-activities.png)

## Interests [](id=interests)

The Interests tab shows the topics of interest for the account's individuals. 
Analytics Cloud identifies these topics via the same methodology it uses for 
individuals. For more information, see 
[Customer Insights](https://help.liferay.com/hc/en-us/articles/360006947951-Customer-Insights). 

## Segments [](id=segments)

The Segments tab shows the segments for the account's individuals. These 
segments function the same as segments in general. The only difference is that 
the segments here apply to the individuals in the account. For more information 
on segments, see the documentation on 
[creating](https://help.liferay.com/hc/en-us/articles/360006947671-Creating-Segments) 
and 
[profiling](https://help.liferay.com/hc/en-us/articles/360006947851-Profiling-Segments) 
segments. 

## Individuals [](id=individuals)

The Individuals tab shows information about the individuals in the account. For 
more information on individuals in Analytics Cloud, see 
[Profiling Individuals](https://help.liferay.com/hc/en-us/articles/360006946171-Profiling-Individuals).

## Details [](id=details)

The Details tab shows information about the account's properties. The properties 
appear in a searchable table that shows each account property in Analytics Cloud 
and its value. The table also has columns for these values: 

**Source Name:** The corresponding property name in the Salesforce data source. 
For example, the Analytics Cloud `accountId` property is `id` in Salesforce. 
Therefore, `id` appears in the Source Name column of the `accountId` row. 

**Data Source:** The data source the property's value originated from. 

**Last Modified:** The date the property's value was most recently changed. 

![Figure 4: The Details tab contains a searchable table that shows the account's properties and their values.](../../images/salesforce-accounts-details.png)
