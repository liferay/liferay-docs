# Folder Facet [](id=folder-facet)

The Folder Facet narrows search results down to those contained in a certain
Asset Folder. If you search for *space*, a Folder titled *Space Images* doesn't
necessarily show up here. The content inside the folder must match the keyword.
Only if its content matches the searched keyword does the Folder appear in the
Folder Facet.

Folders of these Types appear as Folder Facet terms: 

- Bookmarks Folder 
- Documents and Media Folder
- Web Content Folder

![Figure 1: Each Folder with matching content is a facet term.](../../../images/search-folder-facet.png)

The Folder Facet contains several configuration options:

![Figure 2: The Folder Facet is configurable.](../../../images/search-folder-facet-config.png)

**Folder Parameter Name**
: Set the URL parameter name for the Facet. The default is *folder*. Searching for
*lunar resort* and clicking on a Folder Facet produces the URL

    http://localhost:8080/web/guest/search?q=lunar resort&folder=38716

**Max Terms**
: Set the maximum number of facet terms to display, regardless of how
many matching terms are found for the facet.

**Frequency Threshold**
: Set the minimum frequency required for terms to appear in the result list. For
example, if the frequency threshold of a facet is set to `3`, a term with two
matching results doesn't appear in the term result list.

**Display Frequencies**
: Choose whether or not to display the term frequencies.

