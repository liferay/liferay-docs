# Maintaining @product@ [](id=maintaining-liferay)

Once you have a @product@ installation, there are some things you must do to
keep it running smoothly. Backing up your installation in case of a hardware
failure protects your data and helps you get your system back in working order
quickly. And if you're a DXP customer, patching your system regularly brings the
latest bug fixes to your running instance.

+$$$

Upgrading your @product-ver@ to a new GA version can require
[data upgrade](/discover/deployment/-/knowledge_base/7-1/upgrading-to-liferay-71). 
Until you perform all required data upgrades (if any), @product@ startup fails
with messages like these: 

    2018-11-05 17:22:35.025 INFO  [main][StartupHelper:72] There are no patches installed
    You must first upgrade to @product@ 7101
    2018-11-05 17:22:35.098 ERROR [main][MainServlet:277] java.lang.RuntimeException: You must first upgrade to @product@ 7101

$$$

Read on to learn about how to keep your system running well. 
