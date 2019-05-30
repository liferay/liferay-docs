---
header-id: advanced-page-set-options
---

# Advanced Page Set Options

[TOC levels=1-4]

There are some powerful options that should only be used by those with a firm
command of the technology, or they could have major unintended side effects. You
can find these options under the *Advanced* tab.

## Executing JavaScript in Site Pages

At the top of the *Advanced* tab is a JavaScript editor. Code entered here is
executed at the bottom of every page in the Site. If your Site's theme uses
JavaScript (as is usually the case), it's best to add custom JavaScript code to
the theme and *not* here. This way, all your Site's JavaScript code remains in
one place.

This may be useful if your Site's theme does *not* use JavaScript. In this
case, you can place *all* of your Site's JavaScript here.

## Merge Public Pages

If you have more than one Site on a specific Liferay instance, one of those Sites will be the *Default Site* where visitors will be directed if they access the instance's root URL. By default, visitors will only see the pages of that Site in the navigation. To have another Site's public pages appear in the primary navigation for the Default Site, check the box to *Merge public pages* for that site. Be careful as adding too many pages to the main navigation can make it become unwieldy very quickly.

## Rendering Pages for Mobile Devices

*Mobile Device Rules* lets you configure your page set to have specific
behaviors for specific mobile devices or types. Mobile device rules are
inherited from your Public Pages, but you can define specific rules per page.
You can edit the Look and Feel of specific pages for mobile devices, including
the theme. This is explained in 
[Mobile Device Rules](/docs/7-2/user/-/knowledge_base/u/mobile-device-rules).

## Robots

The *Robots* option lets you configure `robots.txt` rules for the domain: both
its public and private pages. The `robots.txt` file provides instructions to
search engines and other tools that are automatically crawling and indexing your
Site. Common entries here include defining some pages not to be indexed.

## Notifying Search Engines of Site Pages

The *Sitemap* option generates a sitemap you can send to some search engines so
they can crawl your Site. It uses the industry standard sitemap protocol. 

Select a search engine link to send the sitemap to it. It's only necessary to
do this once per Site.

If you're interested in seeing what is sent to the search engines, select the
*preview* link to see the generated XML.
