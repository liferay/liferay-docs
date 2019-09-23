---
header-id: recommending-content-based-on-user-behavior
---

# Recommending Content Based on User Behavior

[TOC levels=1-4]

| **Note:** A/B Testing is available for Liferay DXP 7.2 SP1+.

A site's content generates clicks from users. For example, if someone visits
a sporting goods store's site and clicks on several hunting promotional ads, you
can deduce an interest in hunting products and can promote this type
of content when this user visits the site again.

Accomplishing this with
[Segment-based personalization](/docs/7-2/user/-/knowledge_base/u/creating-user-segments)
is possible, but that method is really targeted for vertical specific messaging
or content with a preconceived audience. You may have to create hundreds of
Segments to target all combinations of customer use cases. Instead, you need an
infrastructure that tracks user views and displays the appropriate content based
on behavior. You can accomplish this with *Content Recommendation*.

This is done by adding tags to content/Pages and monitoring the users who visit
them. When a user views a specific content type or Page, its tags are attached
to that user as *interests*. When the user visits other pages, content that
matches their interests is displayed to them. The monitoring process is
facilitated by
[Analytics Cloud](https://help.liferay.com/hc/en-us/articles/360006608732-Generating-New-Business-Using-Analytics),
so you must have your DXP instance synced with it. If you haven't done this
yet, start by
[adding your DXP instance as a data source](https://help.liferay.com/hc/en-us/articles/360006653472-Adding-a-Liferay-DXP-Data-Source).

Once your DXP instance is synced with Analytics Cloud and you're leveraging
Content Recommendation, a user's interests are viewable by navigating to the
left menu &rarr; *Individuals* &rarr; *Interests*.

![Figure 1: A user's interests are stored and accessible from Analytics Cloud.](../../images-dxp/content-interests.png)

You can learn more about Analytics Cloud's individual analytics
[here](https://help.liferay.com/hc/en-us/articles/360006946171-Profiling-Individuals).

To begin recommending content to users, you must

- Add tags to content and/or Pages.
- Display content based on user behavior.

You'll step through these processes next.

## Adding Tags to Track User Behavior

To track user behavior and accumulate their interests, you must add tags to the
content and Pages they visit. First, you'll add tags to web content and
configure it to be viewable using a Display Page Template.

1.  Go to Site Administration &rarr; *Site Builder* &rarr; *Pages* &rarr;
    *Display Page Templates*. Select the *Add* button
    (![Add](../../images-dxp/icon-add.png)), give it a name, and click *Save*.

2.  In the right menu, select *Mapping*
    (![Mapping](../../images-dxp/icon-mapping.png)), choose the *Web Content
    Article* content type and choose *Basic Web Content* for the subtype. Then
    click *Save*.

3.  Add a Fragment to the Display Page and map its field to *Basic Web Content*.
    For example, click *Section Builder* &rarr; *Basic Components* and drag the
    *Paragraph* Fragment to the page. Then click the *Map* button and select
    *Basic Web Content* for its Source and Field.

4.  Publish the Display Page Template.

5.  [Begin creating Basic Web Content](/docs/7-2/user/-/knowledge_base/u/creating-web-content).
    Before publishing the content, navigate to the *Display Page Template*
    section and select *Specific Display Page Template* from the selector. Then
    select the Display Page Template your created previously.

6.  Go to the *Metadata* section in the right menu. Assign tags that
    characterize the content. These are the tags that are referenced as
    interests when a user views the content. Then click *Publish*.

Now your web content is mapped to a Display Page, which allows the assigned
tags to be tracked as interests when the web content is clicked. You can alter
this process based on the asset types you want to recommend.

You can also assign tags to a Page's SEO configuration, which would then be
assigned to users as interests when they visit the Page. Here's how to do this:

1.  Navigate to Site Administration &rarr; *Site Builder* &rarr; *Pages*.

2.  Click the *Actions* button (![Actions](../../images-dxp/icon-actions.png))
    &rarr; *Configure* for a Page you want to add tags to.

3.  Select *SEO* &rarr; *Categorization* and add relevant tags to the page. Then
    click *Save*.

Awesome! Now your content and Pages have tags that are assigned to users as
interests when they visit them. These interests are assessed when recommending
content, which you'll learn how to leverage next.

## Displaying Content Based on User Behavior

Now that your Site's users' have their interests tracked using tags, you'll want
to set up an Asset Publisher to display the content based on their behavior.

1.  Navigate to the Site Administration &rarr; *Content & Data* &rarr; *Content
    Sets*.

2.  Select the *Add* button (![Add](../../images-dxp/icon-add.png)) and click
    *Dynamic Selection*. Assign a name and click *Save*.

3.  Under the Content Recommendation tab, enable Content Recommendation. Then
    save the Content Set.

    ![Figure 2: Enable Content Recommendation for your Content Set.](../../images-dxp/enable-content-recommendation.png)

    For more information on Content Sets, see
    [Creating Content Sets](/docs/7-2/user/-/knowledge_base/u/creating-content-sets).

4.  Add an Asset Publisher widget to a Page. Navigate to its *Options*
    (![Options](../../images-dxp/icon-app-options.png)) &rarr; *Configuration*
    menu and select the *Content Set* asset selection.

5.  Select the Content Set you want to display. Then click *Save*.

In a realistic scenario, Content Sets have many assets with differing tags. That
way, content similar to a user's interests is displayed over other content.

Great! Now when users have accumulated interests based on views, the Asset
Publisher only shows content based on their interests.
