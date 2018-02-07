# Troubleshooting FAQ [](id=troubleshooting)

When coding on any platform, you can sometimes run into issues that have no
clear resolution. This can be particularly frustrating. If you have issues
building, deploying, or running modules, you want to resolve them fast. These
frequently asked questions and answers help you troubleshoot problems that arise
based on the underlying OSGi platform, and then correct them. 

Here are the troubleshooting sections:

-   [Modules](#troubleshooting-modules)
-   [Services and Components](#troubleshooting-services-and-components)

Click a question to view the answer.

## Modules [](id=troubleshooting-modules)

<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I configure dependencies on Liferay Portal artifacts?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>See <a href="/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies">Configuring Dependencies</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">What are optional package imports and how can I specify them?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>When developing Liferay Portal modules, you can declare <em>optional</em> package imports. An optional package import is one your module can use if it’s available, but can still function without it. <a href="/develop/tutorials/-/knowledge_base/7-0/declaring-optional-import-package-requirements">Specifying optional package imports</a> is straightforward. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I connect to a JNDI data source from my module?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>Connecting to an application server’s JNDI data sources from Liferay Portal’s OSGi environment is almost the same as connecting to them from the Java EE environment. In an OSGi environment, the only difference is that you must <a href="/develop/tutorials/-/knowledge_base/7-0/connecting-to-data-sources-using-jndi">use Liferay Portal’s class loader to load the application server’s JNDI classes</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I make sure my module works?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/develop/tutorials/-/knowledge_base/7-0/testing">The Testing tutorials demonstrate several ways to test Liferay Portal modules</a>:</p>
    <ul> <li>Unit testing</li> <li>Integration testing</li> <li>Functional testing</li> </ul>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">My module has an unresolved requirement. What can I do?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>If one of your bundles imports a package that no other bundle in the Liferay OSGi runtime exports, Liferay Portal reports an unresolved requirement:</p>
    <pre><code>! could not resolve the bundles: ...
    Unresolved requirement: Import-Package: ...
    ...
    Unresolved requirement: Require-Capability ...
    </code></pre>
    <p>To satisfy the requirement, <a href="/develop/tutorials/-/knowledge_base/7-0/resolving-bundle-requirements">find a module that provides the capability, add it to your build file’s dependencies, and deploy it</a>. </p>
  </div>
</div> 

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">An IllegalContextNameException reports that my bundle's context name does not follow Bundle-SymbolicName syntax. How can I fix the context name?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/develop/tutorials/-/knowledge_base/7-0/resolving-bundle-symbolicname-syntax-issues">Adjust the <code>Bundle-SymbolicName</code> to adhere to the syntax</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why aren't my module's JavaScript and CSS changes showing?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/develop/tutorials/-/knowledge_base/7-0/why-arent-my-modules-javascript-and-css-changes-showing">Incorrect component properties or stale browser cache can prevent JavaScript and CSS changes from showing</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why aren't my fragment's JSP overrides showing?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/develop/tutorials/-/knowledge_base/7-0/why-arent-jsp-overrides-i-made-using-fragments-showing">Make sure your <code>Fragment-Host</code>’s bundle version is compatible with the host’s bundle version</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">The application server and database started, but @product@ failed to connect to the database. What happened and how can I fix this?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>Liferay Portal initialization can fail while attempting to connect to a database server that isn’t ready. <a href="/develop/tutorials/-/knowledge_base/7-0/portal-failed-to-initialize-because-the-database-wasnt-ready">Configuring Liferay Portal startup to retry JDBC connections</a> facilitates connecting Liferay Portal to databases. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I adjust my module's logging?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>See <a href="/develop/tutorials/-/knowledge_base/7-0/adjusting-module-logging">Adjusting Module Logging</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I implement logging in my module or plugin?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/develop/tutorials/-/knowledge_base/7-0/implementing-logging">Use Simple Logging Facade for Java (SLF4J) to log messages</a>.</p>
  </div>
</div>

## Services and Components [](id=troubleshooting-services-and-components)

<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I detect unresolved OSGi components?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>Liferay Portal module components that use Service Builder use Dependency Manager (DM) and most other Liferay Portal module components use Declarative Services (DS). <a href="/develop/tutorials/-/knowledge_base/7-0/detecting-unresolved-osgi-components">Gogo shell commands and tools help you find and inspect unsatisfied component references for DM and DS components</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">What is the safest way to call non-OSGi code that uses OSGi services?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>See <a href="/develop/tutorials/-/knowledge_base/7-0/calling-non-osgi-code-that-uses-osgi-services">Calling Non-OSGi Code that Uses OSGi Services</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I use files to configure components?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>See <a href="/develop/tutorials/-/knowledge_base/7-0/using-files-to-configure-product-modules">Using Files to Configure Module Components</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I use OSGi services from Ext Plugins?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/develop/tutorials/-/knowledge_base/7-0/using-osgi-services-from-ext-plugins">The registry API lets Ext Plugins use OSGi services </a>. </p>
  </div>
</div> 
