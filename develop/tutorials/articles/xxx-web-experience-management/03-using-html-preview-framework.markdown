# Using HTML Preview Framework

Liferay's HTML Preview Framework provides the ability to generate previews of 
HTML content. There are two parts of the HTML Preview Framework that you can
utilize, the HTML Preview Generator Service and the HTML Preview Processor. The 
Preview Generator Service takes HTML as an input and returns an 
`HtmlPreviewEntry` which contains the preview of the rendered content. You can 
extend the `HtmlPreviewProcessor` interface can be extended to render the 
preview to different document types.

## Using HTML Preview Generator

The HTML Preview Generator is used by the Fragment editor to provide an on the 
fly preview for the HTML, CSS, and JavaScript that you enter. Since it is an 
OSGi module, any developer to use in the their own custom application. To use it
in your application:

1. Add the `com.liferay:com.liferay.html.preview.api` module as a project 
    dependency.
    
2. Inject the service as a `@Reference` in any `@Component` by adding the 
    following code:
    
        @Reference
        private HtmlPreviewEntryLocalService _htmlPreviewEntryLocalService;

3. Once that is taken care of, you can generate an HTML preview like this:

        public HtmlPreviewEntry addHtmlPreviewEntry(
           long userId, long groupId, long classNameId, long classPK,
           java.lang.String.content, java.lang.String mimeType,
           ServiceContext.serviceContext)
         throws PortalException;

    The `classNameId` and `classPK` fields are the class name and identifier of 
    the entity you want to generate a preview for. `content` could be any kind 
    of content, but if you're using the default implementation it will throw a 
    PortalException if it is not well formed XHTML content. The method returns 
    an `HtmlPreviewEntry` instance which could be used to get associated file 
    entry for the further processing if needed.

## Extending the HTML Preview Framework

By default, the HTML Preview Generator directly renders the previewed content 
as HTML to be displayed in the browser. However, support could be added to 
output a PDF or other format of the preview output. You will, of course, need 
to provide the requisite code and libraries to generate your desired output. 
Your new HTML preview processor should be an OSGI `@Compoenent` and implement 
`HtmlPreviewProcessor.` Your class might look something like this:

    public interface HtmlPreviewProcessor {

    /**
     * Generate a preview from an HTML content
     *
     * @param content HTML content to generate a preview for.
     * @return Generated preview file.
     * @throws Exception
    */

    public File generateHtmlPreview(String content) throws Exception;

    /**
     * HTML preview processor MIME type.
     *
     * @return MIME type string.
    */

    public String getMimeType();
    }


