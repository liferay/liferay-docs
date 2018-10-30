# Troubleshooting Front-End Development Issues [](id=troubleshooting-front-end-development-issues)

Front-end development involves many moving parts. Sometimes it's hard to tell 
what may be causing the issues you run into along the way. This can be 
particularly frustrating. These frequently asked questions and answers help you 
troubleshoot and correct problems arising during front-end development.

Here are the troubleshooting sections:

- [CSS](#css)
- [Modules](#modules)
- [Portlets](#portlets)
- [Templates](#templates)
- [Themes](#themes)

Click a question to view the answer.

## CSS [](id=css)

- [Why are my CSS templates not applied in my Angular app?](#broken-css-angular-app)
- [Why is Liferay Portal's CSS broken in Internet Explorer?](#portal-css-broken-ie)

<div class="ldn-faq-question" id="broken-css-angular-app">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why are my CSS templates not applied in my Angular app?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>A known <a href="https://github.com/angular/angular/issues/4974">bug</a> with Angular causes absolute URLs for CSS files not to be recognized.</p>
    <p>Due to the nature of portals, a relative URL is not an option either because the app can be placed on any page.</p>
    <p>To fix this, you can either provide the CSS with a theme or themelet, or you can specify the path to the CSS file with the <code>com.liferay.portlet.header-portlet-css</code> property in the portlet containing your Angular code.</p>
  </div>
</div>

<br/>
<div class="ldn-faq-question" id="portal-css-broken-ie">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why is Liferay Portal's CSS broken in Internet Explorer?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>By default CSS files are minified in the browser. This can cause issues in Internet Explorer. You can disable this behavior by including <code>theme.css.fast.load=false</code> and <code>minifier.enabled=false</code> in your <code>portal-ext.properties</code> file. </p>
  </div>
</div>

## Modules [](id=modules)

- [Why does my JQuery module throw an anonymous module error when I try to load it?](#jquery-anonymous-module-error)
- [Why are my source maps not showing for my Angular or Typescript module?](#source-maps-not-showing)
- [I'm using the liferay-npm-bundler for multiple projects. How can I disable analytics tracking for the entire tool across all my projects?](#disable-bundler-analytics)

<div class="ldn-faq-question" id="jquery-anonymous-module-error">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why does my JQuery module throw an anonymous module error when I try to load it?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>If you're using an external library that you host, you must disable the <i>Expose Global</i> option as described in the <a href="https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-1/using-external-javascript-libraries#using-libraries-that-you-host">Using External JavaScript Libraries</a> tutorial.</p>
  </div>
</div>

<br/>
<div class="ldn-faq-question" id="source-maps-not-showing">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why are my source maps not showing for my Angular or Typescript module?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>This is due to <a href="https://issues.liferay.com/browse/LPS-83052">LPS-83052</a>.</p>
    <p>To solve this, activate the <a href="https://www.typescriptlang.org/docs/handbook/compiler-options.html"><code>inlineSources</code> compiler option</a> via argument or your <code>tsconfig.json</code> file.</p>
  </div>
</div>

<br/>
<div class="ldn-faq-question" id="disable-bundler-analytics">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">I'm using the liferay-npm-bundler for multiple projects. How can I disable analytics tracking for the liferay-npm-bundler in my projects?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>There are a couple options you can use to disable reporting:</p>
    <ul>
      <li><p>Use the <code>--no-tracking</code> flag in your <code>package.json</code>'s build script to disable reporting:</p>
      <p><pre><code>liferay-npm-bundler --no-tracking</code></pre></p>
      </li>
      <li>
      <p>Create a <code>.liferay-npm-bundler-no-tracking</code> file in your project's root folder, or any of its ancestors, to disable reporting.</p>
      <p>This equates to answering <code>No</code> to the <code>May liferay-npm-bundler anonymously report usage statistics to improve the tool over time?</code> question.</p>
      </li>
    </ul>
  </div>
</div>

## Portlets [](id=portlets)

- [I want to use a custom router in my Angular/React/Vue portlet. How can I disable the default Senna JS SPA engine in my portlet?](#angular-react-vue-portlet-disable-spa)

<div class="ldn-faq-question" id="angular-react-vue-portlet-disable-spa">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">I want to use a custom router in my Angular/React/Vue portlet. How can I disable the default Senna JS SPA engine in my portlet?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>By default, the <a href="https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-1/automatic-single-page-applications#what-is-sennajs">Senna JS SPA engine</a> is enabled in your portlets and sites. This disables full page reloads during portlet navigation.</p>
    <p>If you want to use a custom router in your portlet instead, follow the <a href="https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-1/automatic-single-page-applications#disabling-spa">instructions in the SPA documentation</a> to blacklist your portlet from SPA.</p>
  </div>
</div>

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
