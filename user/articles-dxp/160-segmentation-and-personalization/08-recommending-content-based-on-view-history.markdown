# Recommending Content Based on User Behavior

[TOC levels=1-4]

When visiting a site, users inevitably click on content that they're interested
in. For example, if someone visits a sporting goods store's site and clicks on
several hunting promotional ads, you can deduce that they're interested in
hunting products and can promote this type of content to them when they visit
the site again.

Accomplishing this with
[Segment based personalization](/docs/7-2/user/-/knowledge_base/u/creating-user-segments)
is possible, but that method is really targeted for vertical specific messaging
or content with a preconceived target audience. You may have to create hundreds
of Segments to target all combinations of customer use cases. Instead, you need
an infrastructure that tracks user viewership and displays the appropriate
content based on their behavior. You can accomplish this with *Content
Recommendation*.

This is done by adding metatags to content and monitoring the users who visit
that content. When a user views a specific content type, its metatags are
attached to that user as *interests*. When the user visits other pages, content
that matches their interests are viewed to them. The monitoring process is
facilitated by
[Analytics Cloud](https://help.liferay.com/hc/en-us/articles/360006608732-Generating-New-Business-Using-Analytics),
so you must have your DXP instance synced with it. If you haven't done this
yet, start by
[adding your DXP instance as a data source](https://help.liferay.com/hc/en-us/articles/360006653472-Adding-a-Liferay-DXP-Data-Source).

Once your DXP instance is synced to Analytics Cloud and you're leveraging
Content Recommendation, a user's interests are viewable by navigating to the
left menu &rarr; *Individuals* &rarr; *Interests*.

![Figure 1: A user's interests are stored and accessible from Analytics Cloud.](../../images-dxp/content-interests.png)

You can learn more about Analytics Cloud's individual analytics
[here](https://help.liferay.com/hc/en-us/articles/360006946171-Profiling-Individuals).

There are two ways to attach metatags to your content:

- Adding keywords to Content Sets and displaying them via Display Page
  Templates.
- Editing a Page's SEO configuration by adding keywords.

You'll step through the process for both strategies.

## Using Content Sets to Recommend Content

To recommend content to users using Content Sets, you must create a Display Page
Template and map the Content Set (containing tags) to it. An example is outlined
below:

1.  Navigate to the Site Administration &rarr; *Content & Data* &rarr; *Content
    Sets*.

2.  Select the *Add* button (![Add](../../images-dxp/icon-add.png)) and click
    *Dynamic Selection*. Assign a name and click *Save*.

3.  Select *Web Content Article* for the Asset Entry Type.

4.  Under the Content Recommendation tab, enable Content Recommendation. Then
    save the Content Set.

    ![Figure 2: Enable Content Recommendation for your Content Set..](../../images-dxp/enable-content-recommendation.png)

    For more information on Content Sets, visit the
    [Creating Content Sets](/docs/7-2/user/-/knowledge_base/u/creating-content-sets)
    article.

5.  Go to Site Administration &rarr; *Site Builder* &rarr; *Pages* &rarr;
    *Display Page Templates*. Select the *Add* button
    (![Add](../../images-dxp/icon-add.png)), give it a name, and click *Save*.

6.  In the right menu, select *Mapping* (![Mapping](../../images-dxp/icon-mapping.png)),
    choose the *Web Content Article* content type and choose *Basic Web Content*
    for the subtype. Then click *Save*.

7.  Add a Fragment to the Display Page and map its field to *Basic Web Content*.
    For example, click *Section Builder* &rarr; *Basic Components* and drag the
    *Paragraph* Fragment to the page. Then click the *Map* button and select
    *Basic Web Content* for its Source and Field.

8.  Publish the Display Page Template.

9.  [Begin creating a Basic Web Content](/docs/7-2/user/-/knowledge_base/u/creating-web-content).
    Before publishing the content, navigate to the *Display Page Template*
    section and select *Specific Display Page Template* from the dropdown. Then
    select the Display Page Template your created previously.

10. Go to the *Metadata* section in the right menu. Assign tags that
    characterize the content. These are the metatags that will be referenced as
    interests when a user views the content. Then click *Publish*.

11. Add an Asset Publisher widget to a Page. Navigate to its *Options*
    (![Mapping](../../images-dxp/icon-app-options.png)) &rarr; *Configuration*
    menu and select the *Content Set* asset selection.

12. Select the Content Set you want to display. Then click *Save*.

In a realistic scenario, the Content Set would have many assets with
differing metatags. That way, content similar to a user's interests is
displayed over other content.

Great! Now when users have accumulated interests based on viewership, the Asset
Publisher will only show content based on their interests.

## Modifying a Page's SEO Configuration to Recommend Content



