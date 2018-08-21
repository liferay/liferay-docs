# Configuring XStream

Configuring [XStream](http://x-stream.github.io/index.html) for your Guestbook
app provides an easy way to serialize entities to XML and back again. This can
be used to enhance the Guestbook's staging implementation; however, it's not
required. There are three ways to leverage Liferay's offering of XStream via the
[XStreamConfigurator](@app-ref@/foundation/latest/javadocs/com/liferay/xstream/configurator/XStreamConfigurator.html):

- *Allowed Types:* whitelists entities so everything is forbidden except a
  certain set of items. All staged models are allowed by default; this would be
  in addition to the default functionality.
- *Aliases:* helps with the readability and char length of
  [LAR files](/develop/tutorials/-/knowledge_base/7-0/understanding-data-handlers#liferay-archive-lar-file)
  by creating an alias for an otherwise longwinded entity name.
- *Converters:* converts configured objects to and from XML.

In the Guestbook, you'll leverage XStream by creating an alias, which will
modify the LAR file produced by your app during the staging and export
processes.

For example, your generated LAR, by default, would contain something like this:

    <com.liferay.docs.guestbook.model.impl.GuestBookImpl>
        <field1>...</field1>
        ...
    </com.liferay.docs.guestbook.model.impl.GuestBookImpl>

With an XStream alias configured in your app, that same LAR content would appear
as this:

    <GuestBook>
        <field1>...</field1>
        ...
    </GuestBook>

Follow the instructions below to create an XStream alias:

1.  In the `guestbook-service` module, create a package named
    `com.liferay.docs.guestbook.xstream.configurator`. In that package, create
    a class named `GuestbookXStreamConfigurator`.

2.  Modify the class to implement the
    [`XStreamConfigurator`](@app-ref@/foundation/latest/javadocs/com/liferay/xstream/configurator/XStreamConfigurator.html)
    interface and create an `@Component` annotation declaring that same
    interface as the implementation service:

        @Component(
            immediate = true, 
            service = XStreamConfigurator.class
        )
        public class GuestbookXStreamConfigurator implements XStreamConfigurator {

3.  Since the Guestbook won't leverage the *Allowed Types* and *Converters*
    XStream options, override those methods and have them return `null`:

        @Override
        public List<XStreamType> getAllowedXStreamTypes() {

            return null;
        }

        @Override
        public List<XStreamConverter> getXStreamConverters() {

            return null;
        }

4.  Override the `getXStreamAliases()` method to return a list of aliases you
    want to define. Also, define the list field.

        @Override
        public List<XStreamAlias> getXStreamAliases() {
            return ListUtil.toList(_xStreamAliases);
        }

        private XStreamAlias[] _xStreamAliases;

    Next, you'll define the list.

5.  Create an `activate()` method that defines your aliases to use for your
    Guestbook's generated LAR file. You'll define an alias for the
    `GuestbookImpl` and `EntryImpl` classes. They will be converted from their
    full package names to simple entity names:

        @Activate
        protected void activate() {

            _xStreamAliases = new XStreamAlias[] {
                new XStreamAlias(GuestbookImpl.class, "Guestbook"),
                new XStreamAlias(EntryImpl.class, "Entry"),
            };
        }

6.  Organize your imports (*[CTRL]+[SHIFT]+O*), and save the file.

Awesome! You've implemented an XStream Configurator for the Guestbook and have
created an alias for your guestbook and entry entity declarations.
