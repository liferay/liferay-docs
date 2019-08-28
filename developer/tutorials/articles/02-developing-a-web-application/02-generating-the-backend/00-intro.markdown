---
header-id: generating-the-back-end
---

# Generating the Back-end

[TOC levels=1-4]

You can start writing an application in either the front-end or the back-end. If
you start with the front-end, you design the screens and forms first, using mock
data. If you start with the back-end, you create your data store up front, and
then you can create your front-end later. This is what you'll do with the
Guestbook application. 

A *persistence* layer and a *service* layer make up the bottom layers of your
back-end. You'll persist guestbooks and their entries to a database. Your
service layer calls your persistence layer, providing a buffer in case you wish
to swap out your persistence technology later. 

![Figure 1: Service Builder generates the shaded layers of your application.](../../../images/application-layers.png)

*Service Builder* is Liferay's code generation tool for defining object models
and mapping those models to SQL databases. By defining your model in a single
XML file, you can generate your object model (the M in MVC), your service layer,
and your persistence layer all in one shot. At the same time, you can  support
every database @product@ supports. 

Ready to begin? 

<a class="go-link btn btn-primary" href="/docs/7-2/tutorials/-/knowledge_base/t/what-is-service-builder-0">Let's Go!<span class="icon-circle-arrow-right"></span></a>

