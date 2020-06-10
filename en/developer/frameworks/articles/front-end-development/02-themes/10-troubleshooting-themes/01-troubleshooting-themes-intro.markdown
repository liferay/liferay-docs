---
header-id: troubleshooting-themes
---

# Troubleshooting Themes

[TOC levels=1-4]

These frequently asked questions and answers help you troubleshoot and correct
problems in theme development.

Click a question to view the answer.

- [How can I include OSGi headers in my theme?](#osgi-headers-in-themes)
- [Why aren't my changes showing up after I redeploy my theme?](#developer-mode)
- [Why is my theme not loading? It returns the default theme instead.](#default-theme-returned)
- [How can I prevent specific CSS rules from transforming for RTL Languages?](#rtl-no-flip)

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
    <p>By default CSS, JS, and theme template files are cached in the browser. During development, you can enable <a href="/docs/7-2/frameworks/-/knowledge_base/f/using-developer-mode-with-themes">Devloper Mode</a> to prevent your theme's files from caching. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question" id="default-theme-returned">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why is my theme not loading? It returns the default theme instead.&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>If you receive the warning "No theme found for specified theme id...", you may be referencing an outdated theme ID in your Site. Verify that the theme ID in your theme's <code>liferay-look-and-feel.xml</code> matches the theme ID in the warning message: "mytheme_WAR_mytheme". If the theme IDs match, there may be pages using the outdated theme instead of the Site theme. You can verify this by checking the pages manually or searching the database for layouts with values for <code>themeId -</code>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question" id="rtl-no-flip">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I prevent specific CSS rules from transforming for RTL Languages?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>You can prevent specific CSS rules from transforming (flipping) with the <code>/* @noflip */</code> decoration. Place the decoration to the left of the CSS rule to apply it. For example, this rule gives a left margin of <code>20em</code> to the <code>body</code> no matter if the selected language is LTR or RTL:</p>
      <pre><code>
      /* @noflip */ body {
       margin-left: 20em;
      }
      </pre></code>
    <p>You can also use the <code>.rtl</code> CSS selector for rules that exclusively apply to RTL languages.</p>
  </div>
</div>
