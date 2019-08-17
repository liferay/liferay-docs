# Developing a Fragment Renderer

[TOC levels=1-4]

When creating fragments through @product@'s provided UI, you're given three
frontend languages to leverage: CSS, HTML, and JavaScript. Although you can
harness a lot of power with these languages alone, they do not provide an easy
way to retrieve and process information from the database or third party
systems. A common solution for this issue is creating a full-fledged portlet to
complete common backend necessities, but this is sometimes overkill for what you
need.

For a light weight alternative, you can develop a *Fragment Renderer* to use
Liferay's provided Java APIs for backend tasks related to your fragment.