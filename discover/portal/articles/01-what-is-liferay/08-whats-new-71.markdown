# What's New in @product-ver@ [](id=whats-new-in-71)

@product-ver@ launches new features for efficient, beautiful page design, along
with an extensive upgrade to forms. The user experience has also been
streamlined throughout the platform to help save time while managing sites.
There are new tools to support data protection in accordance with the recent
GDPR requirements. Finally, there are several significant technology updates
that help you develop new competitive advantages with modern development tools
for mobile and web.

Evolve your digital presence with new features to create stunning, personalized
experiences for every audience.

## Page Creation [](id=page-creation)

@product-ver@ introduces a powerful new way to design websites. From carefully
designed page fragments to full control over page menus, @product@ frees web
developers and designers to execute web experiences exactly as they envision.
This is an enhancement of the content management system that you can adopt as
you see fit. To differentiate the two, the new pages are called Content Pages,
while the existing system uses Widget Pages. 

### Content Pages [](id=content-pages)

Users can now easily create and add unstructured content directly to pages. This
is useful for site pages that don't need to leverage structured web content,
such as one-off landing pages for marketing campaigns.

When creating a new page, users can choose either the new content pages or
widget pages, which use the traditional method of creating pages through adding
and configuring applications.

![Figure 1: Users can now choose to create Content Pages.](../../images/01-creating-pages.jpg)

### Fragments [](id=fragments)

Fragments are a new way of creating and implementing content page designs. Web
developers can now save page sections as Fragments and reuse them across a Site.
Developers can now create a library of designed components for others to
build pages quickly without having to touch code. Fragments are organized in
collections and leverage familiar asset management features such as drafts,
thumbnail previews, search, and permissions.

![Figure 2: Non-technical users can use Fragments as building blocks for pages.](../../images/01-page-fragments.jpg)

### Fragment Editor [](id=fragment-editor)

Web developers can use the Fragment Editor in the browser to create or edit
their Fragments. Alternatively, developers can create Fragments with their
preferred tools and import the fragments into Liferay.

![Figure 3: The Fragment editor has a three-pane view for HTML, CSS, and JavaScript, with a preview pane rendering the final result.](../../images/01-fragment-editor.png)

### Page Editor [](id=page-editor)

The introduction of Fragments opens up new ways to create pages and templates
through a visual page editor. 

The new Page Editor is for laying out page designs visually and saving them as
reusable templates. Users can search through collections and easily add, remove
and position fragments on the page. Marketers can then customize text with
in-line editing, swapping in new images and other elements. Fragments can
display Liferay's out-of-the-box applications, and you can configure them within
the page editor.

![Figure 4: The Page Editor makes it easy to build pages out of Fragments.](../../images/01-page-editor.jpg)

### Display Pages [](id=display-pages)

Display Pages have been improved to make it easier to create standard templates
for web content that must have a consistent look and feel, such as press
releases. Use page Fragments to implement designs and map content sections
without touching code. When web content is published with a display page
template, it automatically gets its own page with a unique URL, replacing the
default content in the template with the newly added web content.

### Menus [](id=menus)

Menus are now decoupled from page navigation. Now you have the freedom to create
custom menus for sections of the site or remove marketing landing pages from
menus. You can easily manage menu hierarchies and save different menu sets with
a new drag-and-drop interface.

![Figure 5: Menus are now decoupled from page navigation, and can be edited
independently.](../../images/01-menus.jpg)

### Forms [](id=forms)

Forms have extensive new functionality, including a set of conditional rules
that make forms dynamic: if something happens in a field, an action elsewhere on
the form can be triggered. This lets you create calculations, offer follow-up
questions based on responses, and much more. Forms are now localized: they can
be translated into any language. There are new fields and properties, form
fragments, auto-saving of forms, and so much more it had to be 
[described separately](/discover/portal/-/knowledge_base/7-1/whats-new-with-liferay-forms).

![Figure 6: Forms have many improvements.](../../images/01-forms.jpg)

### User Experience [](id=user-experience)

@product-ver@ rolls out several user experience refinements for externally
facing content such as blogs, as well as omni-channel support for how media is
displayed and delivered on different devices. These integrated improvements help
you deliver a better experience to your users out of the box.

### Adaptive Media [](id=adaptive-media)

Adaptive Media dynamically adjusts images to best fit the screen size of the
device being used. It also offers deep control over how images are loaded and
displayed, which helps you address performance issues across a wide variety of
devices and varying network speeds between users and countries. Adaptive Media
does most of the work in the background automatically, but developers can edit
image resolutions and define devices that trigger various resolutions. This
level of control enables consistent experiences that avoid poor page layouts and
slow load times.

### Blogs and Message Boards [](id=blogs-and-message-boards)

Blog refinements make it easier to deliver experiences that are tailored to your
blog audience. These include support for creating friendly URLs, displaying
estimated reading times, unsubscribing from email notifications, a new cards
design, and support for videos from external services. Message Boards now
supports drag and drop for uploading attachments, section renaming, category and
thread grouping, notification management, and a new design for comments.

![Figure 7: The new cards design for blogs displays entries in a visual grid.](../../images/01-blogs.jpg)

## Administration Improvements [](id=administration-improvements)

In addition to its new features, @product-ver@ is easier to administer.
Streamlined administration tools mean less time managing sites, while still
offering granular control. 

### OAuth 2.0 [](id=oauth-2-0)

OAuth 2.0 has become a de-facto standard that allows users to authorize access
to parts of their accounts without giving up authentication credentials. With
@product-ver@, now users can "Sign in with [insert your site here]," granting
mobile and web applications secure, token-based access to user profile
information they have complete control over and can revoke at any time. 

### Data Protection [](id=data-protection)

@product-ver@ introduces new data protection tools to help companies address
GDPR regulations and maintain control over how their platform manages user data.

You can now erase a user's personal data and export a user's personal data in
a machine-readable format upon request. For data erasure, administrators can
review content that potentially contains personal information and edit or delete
as needed through a simple interface. Both tools include APIs for third-party
apps to implement this feature or override the default behavior for
out-of-the-box apps.

### Search [](id=search)

@product-ver@ improves search administration and uses Elasticsearch 6 as the
default search engine, giving users more options for implementing and managing
enterprise search for their sites.

A new Control Panel makes it easier to take care of all administration tasks
with the click of a button. Users can configure the search engine, start and
monitor re-indexes, and much more.

![Figure 8: Search administration is now separated from server administration.](../../images/01-search.jpg)

### Page Management [](id=page-management)

A new interface for visualizing and managing complex page hierarchies makes page
management easier. Page templates and display pages are now integrated nicely,
bringing page management into one central location.

![Figure 9: The new page management interface puts all page functions in one place.](../../images/01-new-page-management.jpg)

### Workflow Management [](id=workflow-management)

Workflow management 
[has received](/discover/portal/-/knowledge_base/7-1/workflow#whats-new-with-workflow) 
a complete UI overhaul, with all configuration consolidated under one area in
the Control Panel. Existing workflows can now be duplicated, definitions are
versioned, and you can save drafts and restore previous versions.

## Developer Improvements [](id=developer-improvements)

@product-ver@ includes an updated collection of tools to facilitate the support
and development of Liferay projects.

### Targeting a Liferay Platform [](id=targeting-a-liferay-platform)

Liferay Workspace helps target a specific release of @product@, so dependencies
get resolved properly. This makes upgrading your applications easy: specify your
target platform, and Workspace points to the new version. All your dependencies
are updated to the latest ones provided in the targeted release. 

### Resolving Modules Before Deployment [](id=resolving-modules-before-deployment)

Avoid the painful process of deploying modules only to be met with console
errors or mysterious problems by resolving modules before deployment. This can
be done by calling the new *resolve* Gradle task provided by Liferay Workspace. 

### 7.1 Code Upgrade Tool [](id=7-1-code-upgrade-tool)

The Code Upgrade Tool in Liferay Developer Studio helps you upgrade your legacy
application code to @product@: 

- Identifies code affected by the API changes
- Describes each API change related to the code
- Suggests how to adapt the code
- Provides options, in some cases, to adapt code automatically. 

### IntelliJ Support [](id=intellij-support)

Liferay development is now officially supported on IntelliJ IDEA which offers
wizards to 

- Create a Liferay Workspace
- Create projects leveraging Liferay's project templates
- Create a Liferay server runtime for project deployment and debugging

### Maven Support for Blade CLI [](id=maven-support-for-blade-cli)

Create Maven projects and Maven Liferay Workspaces using Blade CLI. 

### Hybrid Mobile App Development [](id=hybrid-mobile-app-development)

Liferay Screens 3.0 enables software developers to use Apache Cordova or
Xamarin to build cross-platform applications from one codebase designed
for the web and embed that content into a Screens app for mobile use. Sites and
applications designed for PC can be rendered in screenlets with no additional
code. The resulting apps allow native mobile capabilities and navigation to be
mixed with HTML content seamlessly.

### Modern JavaScript Frameworks Compatibility [](id=modern-javascript-frameworks-compatibility)

@product@ leverages its own npm bundler so developers can manage dependencies
between applications. @product-ver@ provides support for popular JavaScript
frameworks such as Angular, Vue.js, React and modern JavaScript workflows, so
that npm modules can be deployed inside of @product@.

### Modularity Update [](id=modularity-update)

New search applications such as Search Results, Search Bar and Category
Facets allow for greater flexibility in page construction. These
applications come from the previous Search application, but have been
divided into more useful components.

The message boards services have also been modularized and extracted out
of the core, making it easier to manage and update Message Boards
independently.

