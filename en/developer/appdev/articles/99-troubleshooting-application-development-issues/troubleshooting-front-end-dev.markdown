---
header-id: troubleshooting-front-end-development-issues
---

# Troubleshooting Front-End Development Issues

[TOC levels=1-4]

Front-end development involves many moving parts. Sometimes it's hard to tell 
what may be causing the issues you run into along the way. This can be 
particularly frustrating. These frequently asked questions and answers help you 
troubleshoot and correct problems arising during front-end development.

Here are the troubleshooting sections:

- [CSS](#css)
- [Modules](#modules)
- [Portlets](#portlets)

Click a question to view the answer.

## CSS

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

## Modules

<div class="ldn-faq-question" id="jquery-anonymous-module-error">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why does my JQuery module throw an anonymous module error when I try to load it?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>If you're using an external library that you host, you must disable the <i>Expose Global</i> option as described in the <a href="/docs/7-2/frameworks/-/knowledge_base/f/using-external-javascript-libraries#using-libraries-that-you-host">Using External JavaScript Libraries</a> tutorial.</p>
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

## Portlets

<div class="ldn-faq-question" id="angular-react-vue-portlet-disable-spa">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">I want to use a custom router in my Angular/React/Vue portlet. How can I disable the default Senna JS SPA engine in my portlet?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>By default, the <a href="/docs/7-2/frameworks/-/knowledge_base/f/automatic-single-page-applications#what-is-sennajs">Senna JS SPA engine</a> is enabled in your portlets and sites. This disables full page reloads during portlet navigation.</p>
    <p>If you want to use a custom router in your portlet instead, follow the <a href="/docs/7-2/frameworks/-/knowledge_base/f/automatic-single-page-applications#disabling-spa">instructions in the SPA documentation</a> to blacklist your portlet from SPA.</p>
  </div>
</div>
