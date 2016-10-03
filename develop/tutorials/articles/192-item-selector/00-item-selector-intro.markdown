# Item Selector [](id=item-selector)

People's tasks often involve making choices: which shirt to wear, what to eat
for breakfast, or which picture to use in an online profile. The power of choice
is a terrific privilege, but the means by which a person makes his/her selection
can make an experience good or bad. Here are some factors that influence the
experience: 

- Item organization
- Consistency
- UI intuitiveness

@product@'s Item Selector provides all of these things. It is a UI component
that enables users to select different entities in a consistent easy-to-use
manner. Many Liferay portlets, such as Documents and Media, Web Content, Blogs
and more, use Item Selectors to allow users to select things such as images,
videos, audio files, documents, and pages.

![Figure 1: Item Selectors let users browse and select different kinds of entities.](../../images/item-selector-dialog-02.png)

The Item Selector API provides a framework for developers to use Item Selectors
and extend them as desired. They can add Item Selectors to their apps, customize
@product@'s Item Selectors, and create Item Selectors to select any kind of
entity in a consistent, easy-to-use manner.

*Selection views* are key component of the framework. In an Item Selector,
selection views show entities of particular types from different sources.

For example, an Item Selector configured to show images might show selection
views that correlate to the following sources:

- Documents and Media
- Third-party image provider
- Drag-and-drop interface

The Item Selector API empowers developers to add Item Selectors to their apps to
help users get things done.

Here are some different Item Selector use cases:

1.  Adding to a third-party app, the ability to select @product@ entities such
    as sites, pages, or documents from Documents and Media, that are already
    integrated with the Item Selector framework.

2.  Customizing a selection experience by adding a new selection view for an
    entity (e.g., a view of images from an external image repository). 

3.  Creating new selectable entities for third-party applications to use in
    their Item Selectors. 

This group of tutorials demonstrates how to satisfy these use cases. 
