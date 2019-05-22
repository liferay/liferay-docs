# Enabling Change Lists

Enabling the Change Lists feature lets you track asset creation/modifications
system wide. Note, Change Lists and Staging cannot be used at the same time.

To enable the Change Lists feature, follow these steps:

1.  Navigate to the Control Panel &rarr; *Change Lists* &rarr; *Settings*.

2.  Click the *Yes* selector button.

    ![Figure 1: You can enable Change Lists from the Control Panel.](../../../images/enabling-change-lists.png)

3.  Save your selection to enable the Change Lists functionality.

    The *Save* button saves your selection. Choosing the *Save and Go to
    Overview* option saves your selection and also navigates you to the Change
    Lists overview page.

Initially, all changes are still published to your live production environment.
You must create a new change list to begin tracking 
[supported content types](/docs/7-2/user/-/knowledge_base/user/change-lists) and
have the content hidden from your audience. You can learn more about creating
change lists in
[this article](/docs/7-2/user/-/knowledge_base/user/creating-change-lists).

| **Important:** Pages are not tracked by change lists in @product@ 7.2 GA1, but
| this functionality is in development for a future release.

The content types that are not supported are published directly to the
production environment when they're created/edited. If you do add content that
is not supported by change lists while the Change Lists feature is enabled, the
system displays a reminder to let you know your addition will be published
straight to your production environment.

<!-- Although some content types are not tracked by change lists, their widget
display can be tracked on a page. For example, change lists do not support
wiki content, but it does track the wiki widget's configuration on a page.

Add when pages are tracked by change lists. -Cody -->

To learn about managing change lists, continue to the next article.
