---
header-id: validating-modules-against-the-target-platform
---

# Validating Modules Against the Target Platform

[TOC levels=1-4]

| **Important:** Validating modules with the `resolve` task is deprecated. It
| only functions as it's documented here in versions prior to Liferay Workspace
| (Gradle only) version 2.0.3. It is being redesigned for workspace versions
| 2.0.3+ and is still in development at this time.

After you write a module in Liferay Workspace, you can validate it before
deployment to make sure of several things: 

- Will my app deploy successfully? 
- Will there be some sort of missing requirement? 
- If there's an issue, how do I diagnose it? 

These are all common worries that can be frustrating.

Instead of deploying your app and checking for errors in the log, you can
validate your app before deployment. This is done by calling Liferay Workspace's
`resolve` task, which validates your modules against a targeted platform.
Continue on to learn how this works.
