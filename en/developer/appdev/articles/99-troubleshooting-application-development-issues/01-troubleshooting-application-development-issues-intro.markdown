---
header-id: troubleshooting-application-development-issues
---

# Troubleshooting Application Development Issues

[TOC levels=1-4]

When coding on any platform, you can sometimes run into issues that have no
clear resolution. This can be particularly frustrating. If you have issues
building, deploying, or running apps and modules, you want to resolve them
fast. These frequently asked questions and answers help you troubleshoot and
correct problems.

Here are the troubleshooting sections:

-   [Modules](#modules)
-   [Services and Components](#services-and-components)
-   [Front-end](/docs/7-2/appdev/-/knowledge_base/a/troubleshooting-front-end-development-issues)

Click a question to view the answer.

## Modules

<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I configure dependencies on Liferay artifacts?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>See <a href="/docs/7-2/customization/-/knowledge_base/c/configuring-dependencies">Configuring Dependencies</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">What are optional package imports and how can I specify them?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>When developing modules, you can declare <em>optional</em> package imports. An optional package import is one your module can use if it's available, but can still function without it. <a href="/docs/7-2/appdev/-/knowledge_base/a/declaring-optional-import-package-requirements">Specifying optional package imports</a> is straightforward. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I connect to a JNDI data source from my module?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>Connecting to an application server's JNDI data sources from Liferay's OSGi environment is almost the same as connecting to them from the Java EE environment. In an OSGi environment, the only difference is that you must <a href="/docs/7-2/appdev/-/knowledge_base/a/connecting-to-data-sources-using-jndi">use @product@'s class loader to load the application server's JNDI classes</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">My module has an unresolved requirement. What can I do?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>If one of your bundles imports a package that no other bundle in the Liferay OSGi runtime exports, @product@ reports an unresolved requirement:</p>
    <pre><code>! could not resolve the bundles: ...
    Unresolved requirement: Import-Package: ...
    ...
    Unresolved requirement: Require-Capability ...
    </code></pre>
    <p>To satisfy the requirement, <a href="/docs/7-2/appdev/-/knowledge_base/a/resolving-bundle-requirements">find a module that provides the capability, add it to your build file's dependencies, and deploy it</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">An IllegalContextNameException reports that my bundle's context name does not follow Bundle-SymbolicName syntax. How can I fix the context name?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p><a href="/docs/7-2/appdev/-/knowledge_base/a/resolving-bundle-symbolicname-syntax-issues">Adjust the <code>Bundle-SymbolicName</code> to adhere to the syntax</a>. </p>
  </div>
</div>

<!-- https://github.com/liferay/liferay-docs/blob/7.1.x/en/develop/tutorials/articles/380-troubleshooting/10-js-css-changes.markdown needs to be ported to 7.2+ jhinkey
<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why aren't my module's JavaScript and CSS changes showing?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p><a href="/docs/7-2/appdev/-/knowledge_base/a/why-arent-my-modules-javascript-and-css-changes-showing">Incorrect component properties or stale browser cache can prevent JavaScript and CSS changes from showing</a>. </p>
  </div>
</div>
-->

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I adjust my module's logging?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>See <a href="/docs/7-2/appdev/-/knowledge_base/a/adjusting-module-logging">Adjusting Module Logging</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I implement logging in my module or plugin?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p><a href="/docs/7-2/appdev/-/knowledge_base/a/implementing-logging">Use Simple Logging Facade for Java (SLF4J) to log messages</a>.</p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">After creating a relational mapping between Service Builder entities, my portlet is using too much memory. What can I do?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p><a href="/docs/7-2/appdev/-/knowledge_base/a/disabling-cache-for-table-mapper-tables">Disabling the cache related to the entity mapping lowers memory usage.</a>.</p>
</div>
</div>

## Services and Components

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I see what's happening in the OSGi container?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p><a href="/docs/7-2/appdev/-/knowledge_base/a/system-check">Run a System Check.</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I detect unresolved OSGi components?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>module components that use Service Builder use Dependency Manager (DM) and most other module components use Declarative Services (DS). <a href="/docs/7-2/appdev/-/knowledge_base/a/detecting-unresolved-osgi-components">Gogo shell commands and tools help you find and inspect unsatisfied component references for DM and DS components</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">What is the safest way to call OSGi services from non-OSGi code?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>See <a href="/docs/7-2/frameworks/-/knowledge_base/f/using-a-service-tracker
">Calling Non-OSGi Code that Uses OSGi Services</a>. </p>
  </div>
</div>
