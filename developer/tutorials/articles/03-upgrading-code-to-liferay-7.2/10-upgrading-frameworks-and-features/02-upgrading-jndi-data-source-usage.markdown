# Upgrading JNDI Data Source Usage

<div class="learn-path-step">
    <p>Upgrading Frameworks and Features<br>Step 1 of 4</p>
</div>

In @product@'s OSGi environment, you must use the portal's class loader to load
the application server's JNDI classes. An OSGi bundle's attempt to connect to a
JNDI data source without using @product@'s class loader results in a
`java.lang.ClassNotFoundException`.

For more information on how to do this, see the
[Connecting to JNDI Data Sources](/docs/customization/7-2/-/knowledge_base/c/connecting-to-jndi-data-sources)
article.
