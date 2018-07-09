# Type Facet [](id=type-facet)

The Type Facet narrows search results down to those associated with a certain
Asset Type. Each Type with content matching the searched keyword appears as a
facet term.

![Figure 1: Each Asset Type with matching content is a Type Facet term.](../../../images/search-type-facet.png)

By default, all out of the box Asset Types are included as facet terms:

- Wiki Page
- Document
- User
- Bookmarks Folder
- Blogs Entry
- Form Record
- Documents Folder
- Dynamic Data Lists Record
- Bookmarks Entry
- Web Content Article
- Message Boards Message
- Calendar Event
- Knowledge Base Article

The Type Facet contains several configuration options:

![Figure 2: The Type Facet is configurable.](../../../images/search-type-facet-config.png)

**Type Parameter Name**
: Set the URL parameter name for the Facet. The default is *type*. Searching for
*lunar resort* and clicking on a site facet produces the URL

    http://localhost:8080/web/guest/search?q=lunar resort&type=com.liferay.blogs.model.BlogsEntry

**Frequency Threshold**
: Set the maximum number of facet terms to display, regardless of how
many matching terms are found for the facet.

**Display Frequencies**
: Choose whether or not to display the term frequencies.

**Current and Available**
: Add or remove Asset Types from the facet. To remove types, select from the
Current section by clicking and highlighting. Click the right arrow and move the
Asset Type from *Current* to *Available*. Add Asset Types by moving them to the
Current section.

