---
header-id: liferay-front-end-cards
---

# Liferay Front-end Cards

[TOC levels=1-4]

If you have data you want to compare that's heavy on image usage, cards are the
component for the job. Cards visually represent data in a minimal and compact
format. Use them for images, document libraries, user profiles, and more. There
are four main types of Cards:

- Horizontal Cards
- Icon Cards
- Vertical Cards
- User Cards

Examples of each card are shown below.

## Horizontal Card

Horizontal cards are used primarily to display documents, such as files and 
folders. An example configuration is shown below:

```html
<liferay-frontend:horizontal-card
	text="Documents"
  url="https://dev.liferay.com/en/developer/frameworks/-/knowledge_base/7-2/clay-icons"
>
	<liferay-frontend:horizontal-card-col>
				<liferay-frontend:horizontal-card-icon
					icon="folder"
				/>
	</liferay-frontend:horizontal-card-col>
</liferay-frontend:horizontal-card>
```

![Figure 1: Horizontal cards are perfect to display files and documents.](../../../../images/liferay-frontend-taglib-cards-horizontal.png)

The `<liferay-frontend:horizontal-card-icon>` tag uses 
[Clay Icons](/docs/7-2/reference/-/knowledge_base/r/clay-icons) for its `icon` 
attribute. 

## Icon Vertical Card

Icon vertical cards, as the name suggests, are cards that display information in
a vertical format that emphasizes an icon. These cards show content that doesn't
have an associated image. Instead, an icon representing the type of content is
displayed. The example snippet below displays information for a web content
article:

```html
<liferay-frontend:icon-vertical-card
  cssClass="article-preview-content"
  icon="web-content"
  title="<%= title %>"
>
  <liferay-frontend:vertical-card-sticker-bottom>
    <liferay-ui:user-portrait
      cssClass="sticker sticker-bottom"
      userId="<%= assetRenderer.getUserId() %>"
    />
  </liferay-frontend:vertical-card-sticker-bottom>

  <liferay-frontend:vertical-card-footer>
    <aui:workflow-status 
      markupView="lexicon" 
      showIcon="<%= false %>" 
      showLabel="<%= false %>" 
      status="<%= article.getStatus() %>" 
    />
  </liferay-frontend:vertical-card-footer>
</liferay-frontend:icon-vertical-card>
```

![Figure 2: Vertical icon cards are perfect to display an entity selection, such as a web content article.](../../../../images/liferay-frontend-taglib-cards-icon-vertical.png)

## Vertical Card

Vertical cards display information in a vertical card format, as opposed to
a horizontal format. If the content has an associated image (like a blog header
image) you can use a vertical card to display the image. If there is no
associated image, you can use an icon vertical card to represent the content's
type instead (e.g. a PDF file). The example below displays a vertical card for
a web content article when an image preview is available:

```html
<liferay-frontend:vertical-card
  cssClass="article-preview-content"
  imageUrl="<%= articleImageURL %>"
  title="<%= title %>"
>
  <liferay-frontend:vertical-card-sticker-bottom>
    <liferay-ui:user-portrait
      cssClass="sticker sticker-bottom"
      userId="<%= assetRenderer.getUserId() %>"
    />
  </liferay-frontend:vertical-card-sticker-bottom>

  <liferay-frontend:vertical-card-footer>
    <aui:workflow-status 
      markupView="lexicon" 
      showIcon="<%= false %>" 
      showLabel="<%= false %>" 
      status="<%= article.getStatus() %>" 
    />
  </liferay-frontend:vertical-card-footer>
</liferay-frontend:vertical-card>
```

![Figure 3: Vertical cards are perfect to display files and documents.](../../../../images/liferay-frontend-taglib-cards-vertical.png)

## HTML Vertical Card

The HTML Vertical card lets you display custom HTML in the header of the 
vertical card. The example below embeds a video:

```html
<liferay-util:buffer var = "customThumbnailHtml">
	<div class="embed-responsive embed-responsive-16by9">
	  <iframe class="embed-responsive-item" 
    src="https://www.youtube.com/embed/8Bg9jPJpGOM?rel=0" 
    allowfullscreen></iframe>
	</div>
</liferay-util:buffer>

<div class="container">
  <div class="row">
    <div class="col-md-4">
      <liferay-frontend:html-vertical-card
      	html="<%= customThumbnailHtml %>"
      	title="My Video"
      >
      </liferay-frontend:html-vertical-card>
    </div>
  </div>
</div>
```

![Figure 4: Html vertical cards let you display custom HTML in the card's header.](../../../../images/liferay-frontend-taglib-cards-html-vertical.png)

## User Vertical Card

The User Vertical card displays user profile selections in the icon view of the
Management Bar. Below is an example snippet from the User Admin portlet:

```html
<liferay-frontend:user-vertical-card
  actionJsp="/membership_request_action.jsp"
  actionJspServletContext="<%= application %>"
  resultRow="<%= row %>"
  subtitle="<%= membershipRequestUser.getEmailAddress() %>"
  title="<%= HtmlUtil.escape(membershipRequestUser.getFullName()) %>"
  userId="<%= membershipRequest.getUserId() %>"
>
  <liferay-frontend:vertical-card-header>
    <liferay-ui:message 
      arguments="<%= LanguageUtil.getTimeDescription(
      request, 
      System.currentTimeMillis() - membershipRequest.getCreateDate().getTime(), 
      true) %>" 
      key="x-ago" 
      translateArguments="<%= false %>" 
    />
  </liferay-frontend:vertical-card-header>
</liferay-frontend:user-vertical-card>
```

![Figure 5: User vertical cards are perfect to display files and documents.](../../../../images/liferay-frontend-taglib-cards-user-vertical.png)

## Related Topics

- [Liferay Front-end Add Menu](/docs/7-2/reference/-/knowledge_base/r/liferay-frontend-add-menu)
- [Liferay Front-end Info Bar](/docs/7-2/reference/-/knowledge_base/r/liferay-frontend-info-bar)
- [Liferay Front-end Management Bar](/docs/7-2/reference/-/knowledge_base/r/liferay-frontend-management-bar)
