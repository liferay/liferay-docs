---
header-id: escaping-fragment-configuration-text-values
---

# Escaping Fragment Configuration Text Values

[TOC levels=1-4]

When you define text configuration and other options for a Fragment, Fragment
developers can declare any text value they want. With this freedom comes risk;
malicious code could be inserted into the text field, wreaking havoc for other
users of the Fragment.

In this article, you'll learn how to escape Fragment text values so Fragment
authors are protected from XSS attacks.

### Escaping Values in HTML/FreeMarker

You must take special care when adding a text value in your Fragment's HTML. For
example, if a user includes malicious code within `<script>` tags, it runs when
the page is rendered. 

To solve this problem, a utility is available in the FreeMarker context via the
`htmlUtil` class.

For generic cases, an `escape` method is available:

```html
<div class="fragment_38816">
    ${htmlUtil.escape(configuration.text)}"
</div>
```

This escapes your Fragment configuration's `text` field, preventing malicious
code from affecting Fragment authors.

For more information on escaping methods, see the
[HtmlUtil](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/HtmlUtil.html)
class.

### Escaping Values in JavaScript

When including JavaScript in your Fragment, you must be aware of potential
attack vectors, like setting an attribute or appending HTML children. To prevent
these attacks, you should use the `Liferay.Util.escapeHTML` function. You can
call it from your Fragment's JavaScript like this:

```js
function (fragmentElement, configuration) {
    const escapedValue = Liferay.Util.escapeHTML(configuration.text)
}
```

This escapes your Fragment configuration's `text` field, preventing malicious
code in your Fragment's JavaScript code.
