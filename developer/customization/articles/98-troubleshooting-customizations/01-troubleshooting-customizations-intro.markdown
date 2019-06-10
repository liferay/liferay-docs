---
header-id: troubleshooting-faq
---

# Troubleshooting Customizations

[TOC levels=1-4]

When coding on any platform, you can sometimes run into issues that have no
clear resolution. This can be particularly frustrating. If you have issues
building, deploying, or running apps and modules, you want to resolve them
fast. These frequently asked questions and answers help you troubleshoot and
correct problems. 

Click a question to view the answer.

<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why aren't my fragment's JSP overrides showing?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/docs/7-2/customization/-/knowledge_base/c/why-arent-jsp-overrides-i-made-using-fragments-showing">Make sure your <code>Fragment-Host</code>'s bundle version is compatible with the host's bundle version</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why doesn't the package I use from the fragment host resolve?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/docs/7-2/customization/-/knowledge_base/c/why-is-a-package-i-use-from-the-fragment-host-unresolved">Refrain from importing (<code>Import-Package: ...</code>) host packages that the host doesn't export</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question" id="cacheable-web-content-taglibs">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why does my web content break when I refresh the page?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">
    <p>Some taglibs, such as the <code>liferay-map</code> taglib, have limitations when used in a cacheable template (e.g., FreeMarker and Velocity). For instance, when the <code>liferay-map</code> taglib is used in a cacheable template and the user refreshes the page, the map does not show. </p>
    <p>One possible workaround is to disable cache for the template by editing it and unchecking the cacheable option. Alternatively, you can disable cache for all templates by navigating to <code>System Settings</code>&rarr;<code>Template Engines</code> and setting <code>Resource Modification Check</code> to <code>0</code>. </p>
    <p>As best practice, however, we recommend that you don't use taglibs in cacheable web content. </p>
  </div>
</div>
