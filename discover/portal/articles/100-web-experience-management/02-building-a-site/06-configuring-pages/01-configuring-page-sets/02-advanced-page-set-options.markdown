# Advanced Page Set Options [](id=advanced-page-set-options)

There are some powerful options that should only be used by those with a firm
command of the technology, or they could have major unintended side effects. You
can find these options under the *Advanced* tab.

## Executing JavaScript in Site Pages [](id=executing-javascript-in-site-pages)

At the top of the *Advanced* tab is a JavaScript editor. Code entered here is
executed at the bottom of every page in the Site. If your Site's theme uses
JavaScript (as is usually the case), it's best to add custom JavaScript code to
the theme and *not* here. This way, all your Site's JavaScript code remains in
one place.

This may be useful if your Site's theme does *not* use JavaScript. In this
case, you can place *all* of your Site's JavaScript here.

## Rendering Pages for Mobile Devices [](id=rendering-pages-for-mobile-devices)

*Mobile Device Rules* lets you configure your page set to have specific
behaviors for specific mobile devices or types. Mobile device rules are
inherited from your Public Pages, but you can define specific rules per page.
You can edit the Look and Feel of specific pages for mobile devices, including
the theme. This is explained in 
[Mobile Device Rules](/discover/portal/-/knowledge_base/7-1/mobile-device-rules).

## Robots [](id=configuring-rules-for-virtual-hosting)

The *Robots* option lets you configure `robots.txt` rules for the domain: both
its public and private pages. The `robots.txt` file provides instructions to
search engines and other tools that are automatically crawling and indexing your
Site. Common entries here include defining some pages not to be indexed.

## Notifying Search Engines of Site Pages [](id=notifying-search-engines-of-site-pages)

The *Sitemap* option generates a sitemap you can send to some search engines so
they can crawl your Site. It uses the industry standard sitemap protocol. 

Select a search engine link to send the sitemap to it. It's only necessary to
do this once per Site.

If you're interested in seeing what is sent to the search engines, select the
*preview* link to see the generated XML.
