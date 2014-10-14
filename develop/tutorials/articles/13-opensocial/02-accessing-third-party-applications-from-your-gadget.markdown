# Accessing third-party applications from your gadget [](id=accessing-third-party-applications-from-your-gadget)

Is there data on sites like Evernote, Facebook, Google, Netflix, Photobucket,
Twitter, or Yahoo you'd like to access in your OpenSocial gadgets? Perhaps you'd
like to provide a gadget for portal users to add movies to their Netflix queue
or for users to display their Photobucket pictures within gadgets in Liferay
Portal. You may be concerned that users would have to share their third-party
application credentials with Liferay portal in order to use the applications.
Good news! *OAuth* technology resolves the issue.

You can think of OAuth as a "handshake mechanism" where, instead of requiring
the exchange of personal information, your site redirects portal users directly
to the service provider (e.g. Netflix, Photobucket, etc). Users approve the
gadget's access to their resources on the external web applications. It's just
that easy!

Read the
[Gadget personalization](/discover/portal/-/knowledge_base/6-1/exporting-portal-applications-as-widgets-and-opensocial-gadgets)
section in *Using Liferay Portal 6.1* for instructions on configuring and using
OAuth enabled gadgets. To learn how to write OAuth enabled gadgets, see Google's
[Writing OAuth Gadgets](https://developers.google.com/gadgets/docs/oauth)
reference.

Let's now shift our focus to gadget/portlet communication with PubSub.
