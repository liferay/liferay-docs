# Item Selector [](id=item-selector)

People's tasks often involve making choices: which shirt to wear, what to eat
for breakfast, or what profile picture to use. The power of choice is a terrific
privilege, but the means by which a person makes his/her selection can make an
experience good or bad. Here are some factors that influence the experience: 

- Item organization
- Consistency
- UI intuitiveness

@product@'s Item Selector provides all these things. It is a UI component
that enables users to select entities in a consistent easy-to-use manner. Many
Liferay portlets, such as Documents and Media, Web Content, Blogs and more, use
Item Selectors for selecting things such as images, videos, audio files,
documents, and pages.

![Figure 1: Item Selectors let users browse and select different kinds of entities.](../../images/item-selector-dialog-02.png)

The Item Selector API provides a framework for developers to use and extend Item
Selectors. They can add Item Selectors to their apps, customize @product@'s Item
Selectors, and create Item Selectors to select any kind of entity. 

*Selection views* are the framework's key components. In an Item Selector,
selection views show entities of particular types from different sources.

For example, an Item Selector configured to show images might show selection
views from the following sources:

- Documents and Media
- Third-party image provider
- Drag-and-drop interface

Here are some different Item Selector use cases:

1.  Enabling your application to select @product@ entities such as sites, pages,
    or documents from Documents and Media. 

2.  Customizing a selection experience by adding a new selection view for an
    entity (e.g., a view of images from an external image repository). 

3.  Creating new selectable entities for other applications to use in their Item
    Selectors. 

This group of tutorials demonstrates how to satisfy these use cases. 
