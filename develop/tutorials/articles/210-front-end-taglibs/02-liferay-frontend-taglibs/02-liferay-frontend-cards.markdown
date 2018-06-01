# Liferay Front-end Cards [](id=liferay-frontend-cards)

If you have data you want to compare that's heavy on image usage, cards are the
component for the job. Cards visually represent data in a minimal and compact
format. Use them for images, document libraries, user profiles, and more. There
are four main types of Cards covered in this tutorial:

- Horizontal Cards
- Icon Cards
- Vertical Cards
- User Cards

Examples of each card are shown below.

## Horizontal Card [](id=horizontal-card)

Horizontal cards are used primarily to display documents, such as files and 
folders. An example configuration is shown below:

    <liferay-frontend:horizontal-card
    	text="Documents"
      url="https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-1/clay-icons"
    >
    	<liferay-frontend:horizontal-card-col>
    				<liferay-frontend:horizontal-card-icon
    					icon="folder"
    				/>
    	</liferay-frontend:horizontal-card-col>
    </liferay-frontend:horizontal-card>

![Figure 1: Horizontal cards are perfect to display files and documents.](../../../images/liferay-frontend-taglib-cards-horizontal.png)

The `<liferay-frontend:horizontal-card-icon>` tag uses 
[Clay Icons](/develop/tutorials/-/knowledge_base/7-1/clay-icons) for its `icon` 
attribute. 

## Icon Vertical Card [](id=icon-vertical-card)

Icon vertical cards, as the name suggests, are cards that display information in
a vertical format that emphasizes an icon. These cards show content that doesn't
have an associated image. Instead, an icon representing the type of content is
displayed. The example snippet below displays information for a web content
article:

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

![Figure 2: Vertical icon cards are perfect to display an entity selection, such as a web content article.](../../../images/liferay-frontend-taglib-cards-icon-vertical.png)

## Vertical Card [](id=vertical-card)

Vertical cards display information in a vertical card format, as opposed to
a horizontal format. If the content has an associated image (like a blog header
image) you can use a vertical card to display the image. If there is no
associated image, you can use an icon vertical card to represent the content's
type instead (e.g. a PDF file). The example below displays a vertical card for
a web content article when an image preview is available:

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

![Figure 3: Vertical cards are perfect to display files and documents.](../../../images/liferay-frontend-taglib-cards-vertical.png)

## User Vertical Card [](id=user-vertical-card)

The User Vertical card displays user profile selections in the icon view of the
Management Bar. Below is an example snippet from the User Admin portlet:

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

![Figure 4: User vertical cards are perfect to display files and documents.](../../../images/liferay-frontend-taglib-cards-user-vertical.png)

## Related Topics [](id=related-topics)

[Liferay Front-end Add Menu](/develop/tutorials/-/knowledge_base/7-1/liferay-frontend-add-menu)

[Liferay Front-end Info Bar](/develop/tutorials/-/knowledge_base/7-1/liferay-frontend-info-bar)

[Liferay Front-end Management Bar](/develop/tutorials/-/knowledge_base/7-1/liferay-frontend-management-bar)
