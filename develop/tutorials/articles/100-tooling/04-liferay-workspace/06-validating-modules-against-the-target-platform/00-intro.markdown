# Validating Modules Against the Target Platform

Once you've finished developing your custom module in Liferay Workspace, you're
ready to deploy it and see how it looks. Right before deploying your app for the
first time, you may feel a sense of nervousness. Will my app deploy
successfully? Will it appear on the screen? Will there be some sort of missing
requirement? If so, how will I diagnose the issue? These are all common worries
that can be frustrating.

Instead of deploying your app and waiting for @product@ to spit errors back at
you, you can validate your app before deployment. This is done by calling
Liferay Workspace's `resolve` task, which validates your modules against a
targeted platform. Continue on to learn how this works.