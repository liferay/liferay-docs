# Enabling Staging [](id=enabling-staging)

You have two different ways to set up staging:
[Local Live](/discover/portal/-/knowledge_base/7-1/enabling-local-live-staging)
and
[Remote Live](/discover/portal/-/knowledge_base/7-1/enabling-remote-live-staging).
Whether you enable Local Live or Remote Live staging, the interface for managing
and publishing staged pages is the same.

Local Live staging lets you publish site changes quickly, since the
staged and live environments are on the same server. It's also easier to switch
between the staged and live environments using Local Live staging. Since the
staged content, however, is stored in the same database as the production
content, your server must have more resources, and the content isn't as well
protected or backed up as with Remote Live staging. Also, you can't install new
versions of apps for testing purposes in a Local Live staging environment, since
only one version of an app can be installed at any given time on a single
Liferay server.

With Remote Live staging, your staging and live environments are hosted on
separate servers, so your data is separated. This lets you deploy new versions
of apps and content to your staging environment without interfering with your
live environment. However, publishing is slower with Remote Live staging since
data must be transferred over a network. Of course, you also need more
hardware to run a separate staging server.

Visit the staging environment article (Local or Remote) that most closely aligns
with your goal for staging content.
