# Adding a SharePoint Documents and Media Repository to @product@ [](id=adding-a-sharepoint-documents-and-media-repository-to-liferay)

Follow these steps to add your SharePoint Library to Documents and Media in 
@product@:

1.  Add the *Documents and Media* application to a page, if you haven't 
    already.

2.  Inside the Documents and Media application, click the *Add* icon 
    (![Add](../../../../images-dxp/icon-portlet-add-control.png)) 
    and select *Repository*. The New Repository screen appears. 

3.  Select the repository type for the SharePoint OAuth2 configuration you 
    created. For example, if your configuration is named `Foo`, the repository 
    type is listed as `SharePoint (Foo)`. 

4.  Specify values for these fields: 

    - **Site Absolute URL:** Resolves relative URLs. For SharePoint Online, the 
    value follows this pattern: `https://[your-site-name].sharepoint.com`.

    - **Library Path:** A relative path from the *Site Absolute URL* that points 
    to the SharePoint Document Library you want to mount in Documents and Media 
    (an example path could be `Shared Documents`).
    
    ![Figure 1: The Repository Configuration form is where you specify access to the SharePoint Library you want to use.](../../../../images-dxp/sharepoint-repo-configuration-form.png)

5.  Click *Save*.

@product@'s Documents and Media Library now lists your SharePoint repository. 

+$$$

**Note:** The first time you access a mounted SharePoint repository, you must 
provide login credentials and also grant permission for @product@ to access the 
remote SharePoint repository. 

$$$

Sweet! Now that you've added a SharePoint Repository to @product@'s Documents 
and Media, you can access and modify SharePoint Library files in your @product@ 
instance. 
