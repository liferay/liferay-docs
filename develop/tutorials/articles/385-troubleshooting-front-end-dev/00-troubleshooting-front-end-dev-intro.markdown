# Troubleshooting Front-end Development Issues

Front-end development involves many moving parts. Sometimes, it's hard to tell 
what may be causing the issues you run into along the way. This can be 
particularly frustrating. These frequently asked questions and answers help you 
troubleshoot and correct problems that arise during front-end development.

Here are the troubleshooting sections:

-   [Templates](#templates)
-   [Themes](#themes)

Click a question to view the answer.

## Templates

<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why does my portlet's UI break when I refresh the page?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>Some tags, such as the <code>liferay-map</code> tag, have limitations when used in a cacheable template (e.g., FreeMarker and Velocity). For instance, when the <code>liferay-map</code> tag is used in a cacheable template and the user refreshes the page, the map does not show. </p>
    <p>The following tags have limitations in cacheable templates:</p>
    <ul>
        <li>tag one</li>
        <li>tag two</li>
        <li>tag three</li>
        <li>tag four</li>
    </ul>
    <p>To resolve this, disable cache for the template by editing it and unchecking the cacheable option. Alternatively, you can disable cache for all templates by navigating to <code>System Settings</code>&rarr;<code>Template Engines</code> and setting <code>Resource Modification Check</code> to <code>0</code>. </p>
  </div>
</div>

## Themes

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I use the Classic theme as my base theme?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>The Classic theme is already an implementation of an existing base theme and is therefore not meant to be extended. You can use the <a href="/develop/tutorials/-/knowledge_base/7-1/copying-an-existing-themes-files">Gulp kickstart</a> task to copy files from the Classic theme into your theme if you wish. We recommend that you extend the Styled theme instead, if you want to start off with some base styling.</p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I include OSGi package dependencies in my theme?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>To include OSGi dependencies in your theme, specify them with the <code>Import-Package</code> header in your theme's <code>liferay-plugin-package.properties</code> file. Any headers placed in this file are automatically included in your MANIFEST and included in the OSGi bundle.</p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why is Liferay Portal's CSS broken in Internet Explorer?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>By default CSS files are minified in the browser. This can cause issues in Internet Explorer. You can disable this behavior by including <code>theme.css.fast.load=false</code> and <code>minifier.enabled=false</code> in your <code>portal-ext.properties</code> file. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why aren't my changes showing up after I redeploy my theme?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>By default CSS, JS, and theme template files are cached in the browser. During development, you can enable <a href="/develop/tutorials/-/knowledge_base/7-1/using-developer-mode-with-themes">Devloper Mode</a> to prevent your theme's files from caching. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why is my theme not loading? It returns the default theme instead.&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>If you receive the "No theme found for specified theme id..." warning, you may be referencing an outdated theme ID in your site. Verify that the theme ID in your theme's <code>liferay-look-and-feel.xml</code> matches the theme ID in the warning message: "mytheme_WAR_mytheme". If the theme IDs match, there may be pages that are using the outdated theme instead of using the Site theme. You can verify this by checking the pages manually or searching the database for layouts with values for <code>themeId -</code>. </p>
  </div>
</div>
