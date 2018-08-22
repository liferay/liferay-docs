# Using Staging and Export/Import [](id=using-staging-and-export-import)

Your Guestbook application creates guestbooks and entries that are immediately
published when they're saved. Sites constantly change, however, so it's crucial
that you have an area where updates can be planned and tested before publishing
to your audience. Staging lets you change your site behind the scenes without
affecting the live site, and then you can publish all the changes in one fell
swoop. You'll need to implement Staging support in your Guestbook app so its
entries can be tracked during the Staging phase of your publishing process.

![Figure 1: Once Staging is implemented in your Guestbook app, you can have its data tracked by the Staging framework.](../../../images/guestbook-staging.png)

![Figure 2: A Staging-enabled Guestbook app can be modified on the staged site first without any users seeing it on the live site.](../../../images/guestbook-staging-page.png)

Keep in mind that Staging leverages the Export/Import feature, which adds
another dimension to your application by letting you produce reusable content
and import content from other places. The Export/Import framework is
programmatically similar to Staging, so you can easily implement it with
Staging.

Ready to support Staging in your Guestbook app?

<a class="go-link btn btn-primary" href="/develop/tutorials/-/knowledge_base/7-0/creating-staged-models">Let's Go!<span class="icon-circle-arrow-right"></span></a>
