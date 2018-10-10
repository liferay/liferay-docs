# Troubleshooting Front-End Development Issues [](id=troubleshooting-front-end-development-issues)

Front-end development involves many moving parts. Sometimes it's hard to tell 
what may be causing the issues you run into along the way. This can be 
particularly frustrating. These frequently asked questions and answers help you 
troubleshoot and correct problems arising during front-end development.

Here are the troubleshooting sections:

- [Templates](#templates)
- [Themes](#themes)

Click a question to view the answer.

## Templates [](id=templates)

- [Why does my web content break when I refresh the page?](#cacheable-web-content-taglibs)

<div class="ldn-faq-question" id="cacheable-web-content-taglibs">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why does my web content break when I refresh the page?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>Some taglibs, such as the <code>liferay-map</code> taglib, have limitations when used in a cacheable template (e.g., FreeMarker and Velocity). For instance, when the <code>liferay-map</code> taglib is used in a cacheable template and the user refreshes the page, the map does not show. </p>
    <p>One possible workaround is to disable cache for the template by editing it and unchecking the cacheable option. Alternatively, you can disable cache for all templates by navigating to <code>System Settings</code>&rarr;<code>Template Engines</code> and setting <code>Resource Modification Check</code> to <code>0</code>. </p>
    <p>As best practice, however, we recommend that you don't use taglibs in cacheable web content. </p>
  </div>
</div>

## Themes [](id=themes)

- [How can I use the Classic theme as my base theme?](#classic-base-theme)
- [How can I include OSGi headers in my theme?](#osgi-headers-in-themes)
- [Why is Liferay Portal's CSS broken in Internet Explorer?](#portal-css-broken-ie)
- [Why aren't my changes showing up after I redeploy my theme?](#developer-mode)
- [Why is my theme not loading? It returns the default theme instead.](#default-theme-returned)

<div class="ldn-faq-question" id="classic-base-theme">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I use the Classic theme as my base theme?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>The Classic theme is already an implementation of an existing base theme and should not be extended. You can use the <a href="/develop/tutorials/-/knowledge_base/7-1/copying-an-existing-themes-files">Gulp kickstart</a> task to copy files from the Classic theme into your theme if you wish. If you want to start off with some base styling, start with the Styled theme instead.</p>
  </div>
</div>

<br/>
<div class="ldn-faq-question" id="osgi-headers-in-themes">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I include OSGi headers in my theme?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>Specify the headers you want to use in your theme's <code>liferay-plugin-package.properties</code> file. Any headers placed in this file are included automatically in your MANIFEST and the OSGi bundle.</p>
    <p>For example, you can add OSGi dependencies in your theme by importing the exported package with the <code>Import-Package</code> header:</p>
    <pre><code>Import-Package:com.liferay.docs.portlet</code></pre>
  </div>
</div>

<br/>
<div class="ldn-faq-question" id="portal-css-broken-ie">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why is Liferay Portal's CSS broken in Internet Explorer?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>By default CSS files are minified in the browser. This can cause issues in Internet Explorer. You can disable this behavior by including <code>theme.css.fast.load=false</code> and <code>minifier.enabled=false</code> in your <code>portal-ext.properties</code> file. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question" id="developer-mode">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why aren't my changes showing up after I redeploy my theme?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>By default CSS, JS, and theme template files are cached in the browser. During development, you can enable <a href="/develop/tutorials/-/knowledge_base/7-1/using-developer-mode-with-themes">Devloper Mode</a> to prevent your theme's files from caching. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question" id="default-theme-returned">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why is my theme not loading? It returns the default theme instead.&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>If you receive the warning "No theme found for specified theme id...", you may be referencing an outdated theme ID in your Site. Verify that the theme ID in your theme's <code>liferay-look-and-feel.xml</code> matches the theme ID in the warning message: "mytheme_WAR_mytheme". If the theme IDs match, there may be pages using the outdated theme instead of the Site theme. You can verify this by checking the pages manually or searching the database for layouts with values for <code>themeId -</code>. </p>
  </div>
</div>
