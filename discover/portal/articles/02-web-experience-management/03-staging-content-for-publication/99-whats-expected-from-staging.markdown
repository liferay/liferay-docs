# What's Expected from Staging?



## Web Content

The following table describes how entities that are attached/related to a web
content (WC) article are handled during the Staging process.

Updated Entity | Action performed | What should happen?     
:------------- | :--------------  | :------------------ 
Folder | WC article is moved into/out of folder | The article is marked as modified and will be published by default.
WC Structure | WC article is published | Structure (and its parent structure) and template are published.
             | Structure is modified | All articles using it are reindexed. The structure will be published.
             | Parent structure is modified  | All articles using the structure or child structure are reindexed. The structure will be published.
             | Structure is deleted | Structures with articles using it or other structures using it can not be deleted. There could be an issue on import deletion that cannot happen because there are still dependencies using this in live.
WC Template | WC article is published | Template is published and the template reference is validated. It will fail if the template can not be found in a parent site or the global site.
            | Template is modified | Template is marked to be published. WC articles are ignored.
            | Template is deleted | Templates with articles using it can not be deleted.
            | Linked structure is modified | Template is marked to be published. Template could break, so warning is issued.
Images and Image URLs contained in WC | WC article is published | Image(s) are published with article.
                                      | WC article is deleted | Image(s) in article are deleted.
                                      | Image is modified | The article will be published.
                                      | Image is deleted | The article will be published.
Embedded File contained in WC | WC article is published | Embedded file is published with article.
                              | WC article is deleted | No changes to embedded file.
                              | File entry is modified | The file entry is published. The article is not modified or published.
                              | File entry is deleted | The article will be published. Be aware that the missing file could create a link to a non-existing document, resulting in a broken link.
                              | File entry's permissions are modified | No changes to WC article.
Page link contained in WC | WC article is published | The page is imported; publishing won't fail if page is missing but users will get a warning message for a potential broken link.
                          | WC article is deleted | No changes.
                          | Page is deleted | A message displays warning that there may be a broken link.
                          | Page's friendly URL is modified | No changes.
Site link contained in WC | WC article is exported | If the page exists, export the reference to it.
                          | WC article is imported | Validate that the page exists.
                          | WC article is deleted | No changes.
                          | Page is deleted | The Web Content feature handles this action behind-the-scenes.
                          | Page's friendly URL is modified | The Web Content feature handles this action behind-the-scenes.















