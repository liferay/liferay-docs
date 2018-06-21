# Advanced Page Set Options

There are some options which can be very powerful, but should also only be 
configured by someone who has a firm command of the technology or they could 
have major unintended side effects. You can find these options under the *Advanced* tab.

## Executing JavaScript in Site Pages [](id=executing-javascript-in-site-pages)

At the top of the *Advanced* tab, you have a JavaScript editor. Code entered 
here is executed at the bottom of every page in the site. If your site's theme 
uses JavaScript (as is usually the case), it's best to add custom JavaScript 
code to the theme and *not* here. This way, all of your site's JavaScript code 
remains in one place.

This may be useful if your site's theme does *not* use JavaScript. In this
case, you can place *all* of your site's JavaScript here.

## Rendering Pages for Mobile Devices [](id=rendering-pages-for-mobile-devices)

*Mobile Device Rules* lets you configuration your page set to have specific
behaviors for specific mobile devices or types. Mobile device rules are 
inherited from your Public Pages, but you can define specific rules per page. 
You can edit the Look and Feel of specific pages for mobile devices, including 
the theme. The set up for this option is explained in the 
[Displaying Site Pages to Mobile Devices](/discover/portal/-/knowledge_base/7-1/displaying-pages-on-mobile-devices)
section.

## Robots [](id=configuring-rules-for-virtual-hosting)

Selecting the *Robots* option lets you configure `robots.txt` rules for the 
domain: both its public and private pages. The `robots.txt` is intended to 
provide instructions to search engines and other tools that are automatically 
"crawling" and indexing your site. Common entries here include requesting that 
certain pages are not indexed or made available for search engines.

## Notifying Search Engines of Site Pages [](id=notifying-search-engines-of-site-pages)

If you select the *Sitemap* option from the *Advanced* tab for a page set, you
can send a sitemap to some search engines so they can crawl your site. It uses
the industry standard sitemap protocol. 

Select a search engine link to send the sitemap to it. It's only necessary to
do this once per site.

If you're interested in seeing what is sent to the search engines, select the
*preview* link to see the generated XML.
