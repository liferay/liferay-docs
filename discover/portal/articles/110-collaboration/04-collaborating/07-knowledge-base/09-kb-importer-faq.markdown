# Knowledge Base Importer FAQs [](id=knowledge-base-importer-faqs)

-   **What happens when I import an existing article?** 

    The importer checks if the source file's leading header ID (e.g., 
    `# Some Heading  [](id=some-heading)`) matches the URL title of any existing 
    article in the Knowledge Base folder. If a match is found, the importer 
    replaces the article's content with the incoming content converted from the 
    source file. If no match is found, a new article is created. 

-   **Do I need to import all of a Knowledge Base folder's articles, even if I 
    only want to create a new article or update a subset of the folder's current
    articles?** 

    No. You can import as many or as few new or modified articles as you like. 

-   **Does the importer remove articles?** 

    No. The importer only creates and updates articles. It doesn't delete any 
    existing articles. To delete an article, you must manually do so via the 
    Knowledge Base app. 

-   **Can I update an article's priority?** 

    Yes. You can use the file/folder prefix convention and re-import the article 
    to update its priority. 

-   **If I change an article's title, should I also change its header ID?** 

    It depends on whether you've already published your article. If it hasn't 
    been published, then there are no public links to it, so it's fine to change 
    the header ID. If the article is already published, you must decide whether 
    it's worth breaking existing links to the article, and worth having search 
    engines rediscover and re-rank your article based on its new friendly URL. 
    The new friendly URL is based on the new header ID. 
