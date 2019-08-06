---
header-id: automatic-single-page-applications
---

# Automatic Single Page Applications

[TOC levels=1-4]

A good user experience is the measure of a well-designed site. A user's time is 
highly valuable. The last thing you want is for someone to grow frustrated with 
your site because of constant page reloads. A Single Page Application (SPA) 
avoids this issue. Single Page Applications drastically cut down on load times 
by loading only a single HTML page that's dynamically updated as the user 
interacts and navigates through the site. This provides a more seamless app 
experience by eliminating page reloads. **SPA is enabled by default in your apps 
and sites and requires no changes to your workflow or code!** If Spa is 
disabled, ensure that the `com.liferay.frontend.js.spa.web-[version]` module is 
deployed and active. 

## The Benefits of SPAs

Let's say you're surfing the web and you find a really rad site that happens to 
be SPA enabled. All right! Page load times are blazin' fast. You're deep into 
the site, scrolling along, when you find this great post that just speaks to 
you. You copy the URL from the address bar and email it to all of your friends 
with the subject: 'Your Life Will Change Forever.' They must experience this 
awe-inspiring work! 

You get a response back almost immediately. "This is a rad site, but what post 
are you talking about?" it reads. 

"What!? Do my eyes deceive me?" you exclaim. You were in so much of a hurry to 
share this life-changing content that you neglected to notice that the URL never 
updated when you clicked the post. You click the back button, hoping to get 
back to the post, but it takes you to the site you were on before you ever 
visited this one. The page history didn't update as you navigated through the 
app; Only the main app URL was saved. 

What a bummer! "Why? Why have you failed me, site?" you cry. 

If only there was a way to have a Single Page Application, but also be able to 
link to the content you want. Well, don't despair my friend. You can have your 
cake and eat it too, thanks to SennaJS. 

## What is SennaJS?

SennaJS is @product@'s SPA engine. SennaJS handles the client-side data, and 
AJAX loads the page's content dynamically. While there are other JavaScript 
frameworks out there that may provide some of the same features, Senna's only 
focus is SPA, ensuring that your site provides the best user experience 
possible. 

SennaJS provides the following key enhancements to SPA:

**SEO & Bookmarkability**: Sharing or bookmarking a link displays the same 
content you are viewing. Search engines are able to index this content. 

**Hybrid rendering**: Ajax + server-side rendering lets you disable `pushState` 
at any time, allowing progressive enhancement. You can use your preferred method 
to render the server side (e.g. HTML fragments or template views). 

**State retention**: Scrolling, reloading, or navigating through the history of 
the page takes you back to where you were. SennaJS exposes lifecycle events that 
represent state changes in the application. See [Available SPA Lifecycle Events](available-spa-lifecycle-events) 
for more information. 

**UI feedback**: The UI indicates to the user when some content is requested. 

**Pending navigations**: UI rendering is blocked until data is loaded, and the 
content is displayed all at once. 

**Timeout detection**: If the request takes too long to load or the user tries 
to navigate to a different link while another request is pending, the request 
times out. 

**History navigation**: The browser history is manipulated via the 
[History API](https://developer.mozilla.org/en-US/docs/Web/API/History), 
so you can use the back and forward history buttons to navigate through the 
history of the page. 

**Cacheable screens**: Once a surface is loaded, the content is cached in memory 
and is retrieved later without any additional request, speeding up your 
application. 

**Page resources management**: Scripts and stylesheets are evaluated from 
dynamically loaded resources. Additional content can be appended to the DOM 
using `XMLHttpRequest`. For security reasons, some browsers won't evaluate 
`<script>` tags from content fragments. Therefore, SennaJS extracts scripts from 
the content and parses them to ensure that they meet the browser loading 
requirements. 

You can see examples and read more about SennaJS at its [website](http://sennajs.com/). 

This section covers these topics:

- Configuring SPA System Settings
- Disabling SPA
- Specifying how resources are loaded during SPA navigation
- Detaching Global Listeners
