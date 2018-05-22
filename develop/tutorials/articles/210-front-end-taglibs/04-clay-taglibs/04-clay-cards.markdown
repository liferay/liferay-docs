# Clay Cards [](id=clay-cards)

Cards visually represent data. Use them for images, document libraries, user
profiles and more. There are four main types of Cards:

- Image Cards
- File Cards
- User Cards
- Horizontal Cards

Each of these types is covered in this tutorial. 

## Image Cards [](id=image-cards)

Image Cards are used for image/document galleries. 

Image Card:

    <clay:image-card
    	actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    	href="#1"
    	imageAlt="thumbnail"
    	imageSrc="https://images.unsplash.com/photo-1506976773555-b3da30a63b57"
    	subtitle="Author Action"
    	title="Madrid"
    />
 
![Figure 1: Image Cards display images and documents.](../../../images/clay-taglib-image-card.png)

Image Card with icon:

    <clay:image-card
    	actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    	icon="camera"
    	subtitle="Author Action"
    	title="<%= SVG_FILE_TITLE %>"
    />

![Figure 2: Image Cards can also display icons instead of images.](../../../images/clay-taglib-image-card-icon.png)

Image Card empty:

    <clay:image-card 
      actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    	subtitle="Author Action"
    	title="<%= SVG_FILE_TITLE %>"
    />

![Figure 3: Cards can also display nothing.](../../../images/clay-taglib-image-card-empty.png)

Cards can also contain file types. Specify the file type with the `filetype` 
attribute:

    <clay:image-card
    	actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    	fileType="JPG"
    	fileTypeStyle="danger"
    	href="#1"
    	imageAlt="thumbnail"
    	imageSrc="https://images.unsplash.com/photo-1499310226026-b9d598980b90"
    	subtitle="Author Action"
    	title="California"
    />

![Figure 4: Cards can also contain file types.](../../../images/clay-taglib-image-card-file-type.png)

Include the `labels` attribute to add a label to a Card:

    <clay:image-card
    	actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    	fileType="JPG"
    	fileTypeStyle="danger"
    	href="#1"
    	imageAlt="thumbnail"
    	imageSrc="https://images.unsplash.com/photo-1503703294279-c83bdf7b4bf4"
    	labels="<%= cardsDisplayContext.getLabels() %>"
    	subtitle="Author Action"
    	title="Beetle"
    />

![Figure 5: You can include labels in Cards.](../../../images/clay-taglib-image-card-icon-label.png)

Include the `selectable` attribute to make cards selectable (include a checkbox):

    <clay:image-card
    	actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    	fileType="JPG"
    	fileTypeStyle="danger"
    	href="#1"
    	imageAlt="thumbnail"
    	imageSrc="https://images.unsplash.com/photo-1506020647804-b04ee956dc04"
    	labels="<%= cardsDisplayContext.getLabels() %>"
    	selectable="<%= true %>"
    	selected="<%= true %>"
    	subtitle="Author Action"
    	title="Beetle"
    />

![Figure 6: Cards can be selectable.](../../../images/clay-taglib-image-card-icon-selectable.png)

## File Cards [](id=file-cards)

File Cards display an icon of the file's type. They represent file types other
than image files (i.e. PDF, MP3, DOC, etc.).

    <clay:file-card
    	actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    	fileType="MP3"
    	fileTypeStyle="warning"
    	labels="<%= cardsDisplayContext.getLabels() %>"
    	labelStylesMap="<%= cardsDisplayContext.getLabelStylesMap() %>"
    	selectable="<%= true %>"
    	selected="<%= true %>"
    	subtitle="Jimi Hendrix"
    	title="<%= MP3_FILE_TITLE %>"
    />

![Figure 7: File Cards display file type icons.](../../../images/clay-taglib-file-card.png)

You can optionally use the `labelStylesMap` attribute to pass a `HashMap` of 
multiple labels, as shown above.

The example below specifies a list `icon` instead of the default file icon: 

    <clay:file-card
    	actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    	fileType="DOC"
    	fileTypeStyle="info"
    	icon="list"
    	labels="<%= cardsDisplayContext.getLabels() %>"
    	selectable="<%= true %>"
    	selected="<%= true %>"
    	subtitle="Paco de Lucia"
    	title="<%= DOC_FILE_TITLE %>"
    />

+$$$

**Note:** The full list of available Liferay icons can be found on the 
[Clay CSS website](https://claycss.com/docs/components/icons-lexicon.html#clay-lexicon-icons).

$$$

## User Cards [](id=user-cards)

User Cards display user profile images or the initials of the user's name or 
name+surname.

User Card with initials:

		<clay:user-card
			actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
			initials="HS"
			name="User Name"
			subtitle="Latest Action"
			userColor="danger"
		/>

![Figure 8: User Cards can display a user's initials.](../../../images/clay-taglib-user-card-initial.png)

User Card with profile image:

		<clay:user-card
			actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
			disabled="<%= true %>"
			imageAlt="thumbnail"
			imageSrc="https://images.unsplash.com/photo-1502290822284-9538ef1f1291"
			name="User name"
			selectable="<%= true %>"
			selected="<%= true %>"
			subtitle="Latest Action"
		/>

![Figure 9: A User Card can also display a profile image.](../../../images/clay-taglib-user-card-profile-image.png)

## Horizontal Cards [](id=horizontal-cards)

Horizontal Cards represent folders and can have the same amount of information
as other Cards. The key difference is that horizontal Cards let you remove the
image portion of the Card, since only the folder icon is required.

    <clay:horizontal-card
    	actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    	selectable="<%= true %>"
    	selected="<%= true %>"
    	title="ReallySuperInsanelyJustIncrediblyLongAndTotallyNotPossibleWordButWeAreReallyTryingToCoverAllOurBasesHereJustInCaseSomeoneIsNutsAsPerUsual"
    />
 
![Figure 10: Horizontal Cards are good for displaying folders.](../../../images/clay-taglib-horizontal-card.png)

Now you know how to use Cards in your UI to display information in your apps.

## Related Topics [](id=related-topics)

[Clay Badges](/develop/tutorials/-/knowledge_base/7-1/clay-badges)

[Clay Labels and Links](/develop/tutorials/-/knowledge_base/7-1/clay-labels-and-links)

[Clay Stickers](/develop/tutorials/-/knowledge_base/7-1/clay-stickers)
