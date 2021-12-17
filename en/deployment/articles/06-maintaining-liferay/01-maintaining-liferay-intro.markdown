---
header-id: maintaining-liferay
---

# Maintaining @product@

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/maintaining-a-liferay-installation.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

Once you have a @product@ installation, there are some things you must do to
keep it running smoothly. Backing up your installation in case of a hardware
failure protects your data and helps you get your system back in working order
quickly. And if you're a DXP customer, patching your system regularly brings the
latest bug fixes to your running instance.

| Upgrading @product-ver@ to a new GA version can require
| [data upgrade](/docs/7-2/deploy/-/knowledge_base/d/upgrading-to-product-ver).
| Until you perform all required data upgrades (if any), @product@ startup fails
| with messages like these:
| 
| ```bash
| 2019-03-06 17:22:35.025 INFO  [main][StartupHelper:72] There are no patches installed
| You must first upgrade to @product@ 7210
| 2019-03-06 17:22:35.098 ERROR [main][MainServlet:277] java.lang.RuntimeException: You must first upgrade to @product@ 7201
| ```

Read on to learn about how to keep your system running well. 
