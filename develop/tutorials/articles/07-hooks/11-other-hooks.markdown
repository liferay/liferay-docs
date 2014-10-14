# Other hooks [](id=other-hooks)

Since hooks are the preferred plugin type for customizing Liferay's core
features, the Liferay team is happy to keep providing you new hooks. This
section is a placeholder for hooks that are available in Liferay Portal 6.1,
but aren't yet fully documented. 

**Servlet filter hook:** Servlet filters allow you to pre-process requests going
*to* a servlet and post-process responses coming *from* a servlet. As server
requests are received that match URL patterns or match servlet names specified
in your servlet filter mappings, your specified servlet filters are applied.
Hook elements `servlet-filter` and `servlet-filter-mapping` have been added to
`liferay-hook.xml` so you can configure your servlet filters. For a working
example, see the
[sample-servlet-filter-hook](https://github.com/liferay/liferay-plugins/tree/master/hooks/sample-servlet-filter-hook)
in the Plugin SDK. 
