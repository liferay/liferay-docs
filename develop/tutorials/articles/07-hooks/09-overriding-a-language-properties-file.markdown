# Overriding a Language.properties File [](id=overriding-a-language-properties-file)

Hooks let you change any of the messages displayed by Liferay, to suit your
needs. To do so, create a *Language* file for the locale of the messages you
want to customize, then refer to your file from your `liferay-hook.xml` file.
For example, to override the Spanish and French message translations of portal's
`Language.properties` file, create *Language* files for them in the same
directory, and refer to these language files in your `liferay-hook.xml` file,
like so:

    <hook>
        ...
        <language-properties>content/Language_es.properties</language-properties>
        <language-properties>content/Language_fr.properties</language-properties>
        ...
    </hook>

+$$$

**Tip:** Check the DTD of each Liferay XML file you modify for the elements and
attributes that can be included in the XML and the specified order for those
elements. 

$$$

Great! You now know how to customize language keys. Next, let's discuss
extending your Indexer Post Processor.
