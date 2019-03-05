---
header-id: creating-an-image-scaler
---

# Creating an Image Scaler

Adaptive Media scales images to match the image resolutions defined by the 
@product@ administrator. The default scaling is usually suitable, but you can 
customize it by creating an image scaler. The steps here show you how. For 
detailed information on these steps, see 
[Image Scaling in Adaptive Media](/develop/tutorials/-/knowledge_base/7-2/adaptive-media#image-scaling-in-adaptive-media). 

Follow these steps to create a custom image scaler. The example scaler in these 
steps customizes the scaling of PNG images: 

1.  Create your scaler class to implement `AMImageScaler`. You must also 
    annotate your scaler class with `@Component`, setting `mime.type` properties 
    for each of the scaler's MIME types, and registering an `AMImageScaler` 
    service. If there's more than one scaler for the same MIME type, you must 
    also set the `@Component` annotation's `service.ranking` property. For your 
    scaler to take precedence over other scalers of the same MIME type, its 
    service ranking property must be higher than that of the other scalers. If 
    `service.ranking` isn't set, it defaults to `0`. 

    | **Note:** The `service.ranking` property isn't set for the image scalers 
    | included with Adaptive Media (`AMDefaultImageScaler` and 
    | `AMGIFImageScaler`). Their service ranking therefore defaults to `0`. To 
    | replace either scaler, you must set your scaler to the same MIME type and 
    | give it a service ranking higher than `0`. 

    This example image scaler scales PNG and x-PNG images and has a service 
    ranking of `100`: 

        @Component(
            immediate = true,
            property = {"mime.type=image/png", "mime.type=image/x-png", "service.ranking:Integer=100"},
            service = {AMImageScaler.class}
        )
        public class SampleAMPNGImageScaler implements AMImageScaler {...

    This requires these imports: 

        import com.liferay.adaptive.media.image.scaler.AMImageScaler;
        import org.osgi.service.component.annotations.Component;

2.  Implement the `isEnabled()` method to return `true` when you want to enable 
    the scaler. In many cases, you always want the scaler enabled, so you can 
    simply return `true` in this method. This is the case with the image scaler 
    in this example: 

        @Override
        public boolean isEnabled() {
            return true;
        }

    This method gets more interesting when the scaler depends on other tools or 
    features. For example, the `isEnabled()` method in `AMGIFImageScaler` 
    determines whether gifsicle is enabled. This scaler must only be enabled 
    when the tool it depends on, gifsicle, is also enabled: 

        @Override
        public boolean isEnabled() {
            return _amImageConfiguration.gifsicleEnabled();
        }

3.  Implement the `scaleImage` method. This method contains the scaler's 
    business logic and must return an `AMImageScaledImage` instance. For 
    example, the `scaleImage` implementation in this example uses 
    `AMImageConfigurationEntry` to get the maximum height and width values for 
    the scaled image, and `FileVersion` to get the image to scale. The scaling 
    is done via a private inner class, assuming that the methods `_scalePNG`, 
    `_getScalePNGHeight`, `_getScalePNGWidth`, and `_getScalePNGSize` implement 
    the actual scaling: 

        @Override
        public AMImageScaledImage scaleImage(FileVersion fileVersion,
            AMImageConfigurationEntry amImageConfigurationEntry) {

            Map<String, String> properties = amImageConfigurationEntry.getProperties();

            int maxHeight = GetterUtil.getInteger(properties.get("max-height"));
            int maxWidth = GetterUtil.getInteger(properties.get("max-width"));

            try {
                InputStream inputStream = 
                    _scalePNG(fileVersion.getContentStream(false), maxHeight, maxWidth);

                int height = _getScalePNGHeight();
                int width = _getScalePNGWidth();
                long size = _getScalePNGSize();

                return new AMImageScaledImageImpl(inputStream, height, width, size);
            }
            catch (PortalException pe) {
                throw new AMRuntimeException.IOException(pe);
            }
        }

        private class AMImageScaledImageImpl implements AMImageScaledImage {

            @Override
            public int getHeight() {
                return _height;
            }

            @Override
            public InputStream getInputStream() {
                return _inputStream;
            }

            @Override
            public long getSize() {
                return _size;
            }

            @Override
            public int getWidth() {
                return _width;
            }

            private AMImageScaledImageImpl(InputStream inputStream, int height, 
                int width, long size) {

                _inputStream = inputStream;
                _height = height;
                _width = width;
                _size = size;
            }

            private final int _height;
            private final InputStream _inputStream;
            private final long _size;
            private final int _width;

        }

    This requires these imports: 

        import com.liferay.adaptive.media.exception.AMRuntimeException;
        import com.liferay.adaptive.media.image.configuration.AMImageConfigurationEntry;
        import com.liferay.adaptive.media.image.scaler.AMImageScaledImage;
        import com.liferay.portal.kernel.exception.PortalException;
        import com.liferay.portal.kernel.repository.model.FileVersion;
        import com.liferay.portal.kernel.util.GetterUtil;
        import java.io.InputStream;
        import java.util.Map;

## Related Topics

[Adaptive Media](/develop/tutorials/-/knowledge_base/7-2/adaptive-media)

[Displaying Adapted Images in Your App](/develop/tutorials/-/knowledge_base/7-2/displaying-adapted-images-in-your-app)

[Finding Adapted Images](/develop/tutorials/-/knowledge_base/7-2/finding-adapted-images)
