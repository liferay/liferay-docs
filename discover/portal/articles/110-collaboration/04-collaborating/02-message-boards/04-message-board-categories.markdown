# Message Board Categories [](id=message-board-categories)

Message Board categories organize threads by topic. This makes it easier to find
the right topic for discussion, and can also help discussions stay on topic. For
example, a tropical fishkeeping message board may have separate categories for
freshwater and saltwater topics. 

This article shows you how to create and manage message board categories.

## Adding Categories [](id=adding-categories)

Follow these steps to create a message board category:

1.  Open the Message Boards app in your scope's Site Administration menu, as 
    described in 
    [the article on creating message boards](/discover/portal/-/knowledge_base/7-1/creating-message-boards).

2.  Click the *Add* icon
    (![Add](../../../../images/icon-add.png)) and select *Category*. This opens 
    the Add Category form. 

    ![Figure 1: You have several options to create a message board category for your needs.](../../../../images/message-boards-add-category.png)

3.  Enter a name and description for the category. 

4.  Select the category's *Display Style*. This controls how threads in the 
    category appear. By default, you can choose these display styles: 

    -   **Default:** Classic display style for general purpose discussions.
    -   **Question:** Threads appear in a question and answer style. 

    You can create custom display styles and make them available for selection
    in this form. You must set the available display styles via the [portal
    property](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Message%20Boards%20Portlet)
    `message.boards.category.display.styles`. Similarly, you can set the default
    display style in `message.boards.category.display.styles.default`. 

5.  Open the *Mailing List* section of the form and set the mailing list options 
    you want. To enable a mailing list for the category, set the *Active* toggle 
    to *YES*. To enable anonymous emails in the list, set the *Allow Anonymous 
    Emails* toggle to *YES*. The default for both toggles is *NO*. For an 
    explanation of these features, see 
    [the documentation on mailing lists for Message Boards](/discover/portal/-/knowledge_base/7-1/user-subscriptions-and-mailing-lists#mailing-lists). 

6.  Open the *Permissions* section and set the category's permissions. The
    *Viewable by* selector lets you pick who can view the category: 

    -   Anyone (Guest Role)
    -   Site Members
    -   Owner

    To show more permissions options, click *More Options*. A table appears with 
    the rest of the category's permissions, which you can assign to the Guest 
    and Site Member roles: 

    **Lock Thread:** Stop any further additions or modifications to a 
    thread's messages. 

    **Add Subcategory:** Add a new category within this category. 

    **Update:** Edit the category. 

    **Subscribe:** Receive notifications on new and modified posts. 

    **Reply to Message:** Respond to existing messages. 

    **Add File:** Attach a file to any of your messages. 

    **Permissions:** View and modify permissions. 

    **Delete:** Remove the category. 

    **Add Message:** Post a new thread. 

    **Update Thread Priority:** Modify a thread's priority. 

    **Move Thread:** Move a thread to a different category or subcategory. 

    Note that after creating a category, you can revisit its permission options 
    by clicking the category's *Actions* icon
    (![Actions](../../../../images/icon-actions.png)) and selecting *Permissions*. 

7.  Click *Save* when you're finished. Your category now appears in the table. 

As you add categories to a message board, they appear on the message board's
home screen. The list displays the category names and the numbers of
subcategories, threads, and posts in each one. 

![Figure 2: Categories help you organize threads so users can find topical threads that interest them.](../../../../images/message-boards-home.png)

## Adding Subcategories [](id=adding-subcategories)

Categories can contain as many subcategories as you like. If, however, you nest
categories too deep, users can have trouble finding them. 

Follow these steps to add a subcategory to a category:

1.  Click the category's name in the list, then click the *Add* icon 
    (![Add](../../../../images/icon-add.png)) and select *Subcategory*. The Add 
    Subcategory form appears. 

2.  Fill out the Add Subcategory form with the values and settings you want to 
    use for the subcategory. This form is identical to the Add Category form, 
    and is populated with the parent category's properties by default. 

3.  Click *Save* when you're finished. Your subcategory now appears in the 
    table. 

## Moving and Merging Categories [](id=moving-and-merging-categories)

Each category can have any number of threads, and you can add as many categories
and subcategories as you wish. You can also move and merge categories. 

Follow these steps to move a category or merge it with another: 

1.  Click the category's *Actions* icon
    (![Actions](../../../../images/icon-actions.png)) and select *Move*. 
    This brings up the Move Category form. 

2.  Select a new parent category via the *Select* button under the *Parent 
    Category* field. Note that this field is empty for top-level categories. 

3.  If you want to merge the category with the selected parent category, select 
    *Merge with Parent Category*. 

4.  Click *Move*. 

![Figure 3: The Move Category form lets you move and merge categories.](../../../../images/mb-move-merge.png)
