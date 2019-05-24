# Editing Multiple Change Lists in Parallel

There are times when multiple people must edit content at the same time. With
each of them maintaining their own change list, problems can arise where people
could attempt to override each other's content changes based on when they're
published. The Change Lists framework can help resolve these issues by detecting
content collisions.

Collision detection is possible with the following assets:

- Web content structure
- Web content template

As an example, assume there are two content editors managing their own separate
change lists with both change lists based on the same production environment.
Consider this scenario:

1.  There is an existing production template in Site A (v1.0).
2.  User 1 creates a change list *CC1*.
3.  User 2 creates a change list *CC2*.
4.  User 1 edits the template in *CC1* (v1.1).
5.  User 2 edits the template in *CC2* (v1.2).
6.  User 2 publishes *CC2* (production now has v1.2, *CC1* has v1.1)

When User 1 goes to publish their change list *CC1*, the production environment
will be different than the one it was built from. When this happens, the Change
List framework cannot decipher what takes precedent, so a collision is flagged.

![Figure 1: Your Active Change List section displays a collision notification.](../../../images/collision-detection.png)

When a collision with the production environment is detected, a notification is
present in the Active Change List section. You can also find the specific
content causing the collision in the Latest Changes section at the bottom of the
Overview page.

The Change Lists framework does not offer auto-merging, so you must decide the
best course of action. If you know your change list should override the work
completed by someone else, you can enable the *Ignore Collision* selector on the
Publish Change List screen. When collisions are ignored and you publish your
change list, the production environment your change list was based on is
reestablished and your changes are published on top of that.

Note that web content articles were not listed as a supported content type for
collision detection management. When a scenario similar to the one listed
previously occurs for a web content article, an error is thrown.

![Figure 2: You cannot ignore collisions or leverage its pre-detection features.](../../../images/error-for-multi-user-edits.png)

When a collision is detected for web content articles, your only option is to
copy the latest article's content in the production environment to your active
change list and then apply your changes to it.

Great! Now you know how to manage collisions for your change list!
