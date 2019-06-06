---
header-id: troubleshooting-deployments
---

# Troubleshooting Deployments

[TOC levels=1-4]

When coding on any platform, you can sometimes run into issues that have no
clear resolution. This can be particularly frustrating. If you have issues
building, deploying, or running apps and modules, you want to resolve them
fast. These frequently asked questions and answers help you troubleshoot and
correct problems. 

Click a question to view the answer.

<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">Why did the entity sort order change when I migrated to a new database type?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p><a href="/docs/7-2/deploy/-/knowledge_base/d/sort-order-changed-with-a-different-database">Your new database uses a different default query result order--you should be able to configure a different order</a>.</p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">How can I use files to configure components?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>See <a href="/docs/7-2/deploy/-/knowledge_base/d/using-files-to-configure-product-modules">Using Files to Configure Module Components</a>. </p>
  </div>
</div>

<br/>
<div class="ldn-faq-question">
  <span class="ldn-faq-toggle-button" data-show="false" style="font-weight: normal;">The application server and database started, but @product@ failed to connect to the database. What happened and how can I fix this?&nbsp;<span class="icon-caret-right" style="pointer-events:none;"></span></span>
  <div class="hide">  
    <p>@product@ initialization can fail while attempting to connect to a database server that isn't ready. <a href="/docs/7-2/deploy/-/knowledge_base/d/portal-failed-to-initialize-because-the-database-wasnt-ready">Configuring startup to retry JDBC connections</a> facilitates connecting @product@ to databases. </p>
  </div>
</div>
