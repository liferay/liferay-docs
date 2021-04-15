---
header-id: using-liferays-script-engine
---

# Using Liferay's Script Engine

[TOC levels=1-4]

@product@ provides a robust script engine for executing [Groovy](http://groovy-lang.org/) scripts to maintain your @product@ instance. You can execute scripts to perform maintenance tasks involving data cleanup, user maintenance operations, bulk Liferay API invocations, or even system level operations.

The tutorials in this section explain how to use Liferay's script engine and
script console. They cover the following topics: 

- Invoking Liferay's API from a script

- Running scripts from the script console

- Using the script engine with workflow

- Leveraging custom Java tools in the script engine

Invoking Liferay's API is probably the most common task for which you'll want to
run a script. If you have any familiarity with Liferay's API, this will be very
easy for you. To access Liferay's scripting console, navigate to the Control
Panel, click on *Server Administration* under the System heading, then click on
the *Script* tab.