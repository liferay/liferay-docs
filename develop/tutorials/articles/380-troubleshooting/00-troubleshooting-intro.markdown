# Troubleshooting OSGi Bundles [](id=troubleshooting-OSGi-Bundles)

When coding on any platform, you can sometimes run into issues that have no
clear resolution. This can be particularly frustrating. If you have issues
building, deploying, or running modules, you want to resolve them fast. These
frequently asked questions and answers help you troubleshoot and correct
problems that arise based on the underlying OSGi platform.

Here are the troubleshooting sections:

-   [Modules](#troubleshooting-modules)
-   [Services and Components](#troubleshooting-services-and-components)

Click a question to view the answer.

## Modules [](id=troubleshooting-modules)

<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I configure dependencies on Liferay artifacts?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>See <a href="/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies">Configuring Dependencies</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">What are optional package imports and how can I specify them?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>When developing modules, you can declare <em>optional</em> package imports. An optional package import is one your module can use if it's available, but can still function without it. <a href="/develop/tutorials/-/knowledge_base/7-1/declaring-optional-import-package-requirements">Specifying optional package imports</a> is straightforward. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I connect to a JNDI data source from my module?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>Connecting to an application server's JNDI data sources from Liferay's OSGi environment is almost the same as connecting to them from the Java EE environment. In an OSGi environment, the only difference is that you must <a href="/develop/tutorials/-/knowledge_base/7-1/connecting-to-data-sources-using-jndi">use @product@'s class loader to load the application server's JNDI classes</a>. </p>
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
    <p>To satisfy the requirement, <a href="/develop/tutorials/-/knowledge_base/7-1/resolving-bundle-requirements">find a module that provides the capability, add it to your build file's dependencies, and deploy it</a>. </p>
  </div>
</div> 

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">An IllegalContextNameException reports that my bundle's context name does not follow Bundle-SymbolicName syntax. How can I fix the context name?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/develop/tutorials/-/knowledge_base/7-1/resolving-bundle-symbolicname-syntax-issues">Adjust the <code>Bundle-SymbolicName</code> to adhere to the syntax</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">The application server and database started, but @product@ failed to connect to the database. What happened and how can I fix this?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>@product@ initialization can fail while attempting to connect to a database server that isn't ready. <a href="/develop/tutorials/-/knowledge_base/7-1/portal-failed-to-initialize-because-the-database-wasnt-ready">Configuring startup to retry JDBC connections</a> facilitates connecting @product@ to databases. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I adjust my module's logging?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>See <a href="/develop/tutorials/-/knowledge_base/7-1/adjusting-module-logging">Adjusting Module Logging</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I implement logging in my module or plugin?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/develop/tutorials/-/knowledge_base/7-1/implementing-logging">Use Simple Logging Facade for Java (SLF4J) to log messages</a>.</p>
  </div>
</div>

### Why did the entity sort order change when I migrated to a new database type? [](id=why-did-the-entity-sort-order-change-when-i-migrated-to-a-new-database-type)

[Your new database uses a different default query result order--you should be able to configure a different order](/develop/tutorials/-/knowledge_base/7-1/sort-order-changed-with-a-different-database).

## Services and Components [](id=troubleshooting-services-and-components)

<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I detect unresolved OSGi components?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>module components that use Service Builder use Dependency Manager (DM) and most other module components use Declarative Services (DS). <a href="/develop/tutorials/-/knowledge_base/7-1/detecting-unresolved-osgi-components">Gogo shell commands and tools help you find and inspect unsatisfied component references for DM and DS components</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">What is the safest way to call OSGi services from non-OSGi code?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>See <a href="/develop/tutorials/-/knowledge_base/7-1/service-trackers">Calling Non-OSGi Code that Uses OSGi Services</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I use files to configure components?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>See <a href="/develop/tutorials/-/knowledge_base/7-1/using-files-to-configure-product-modules">Using Files to Configure Module Components</a>. </p>
  </div>
</div>
