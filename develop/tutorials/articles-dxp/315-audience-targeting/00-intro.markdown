---
header-id: audience-targeting
---

# Audience Targeting

[TOC levels=1-4]

Liferay's Audience Targeting application uses defined criteria to display
content to users, who are organized into segments. You can target content to
those user segments and create campaigns to expose user segments to a certain
set of assets. Visit
[Targeting Content to your Audience](/docs/7-1/user/-/knowledge_base/u/targeting-content-to-your-audience)
for more information on Audience Targeting and how to use it.

Audience Targeting can be used to do all this without any customization, but it
also contains a framework to be extended by developers.

There are a set of extension points you can use to customize its functionality,
including

- Rule Types
- Report Types
- Report Metrics

Audience Targeting extensions are created using OSGi modules. There are
convenient Blade CLI templates for creating these projects, but you can create
the modules any way you want.

| **Important:** Not all Audience Targeting features function using the embedded
| HSQL database, so developers must use a Liferay-supported database to make full
| use of Audience Targeting features.

In these tutorials, you'll learn how to create these extension points for your
Audience Targeting application.
