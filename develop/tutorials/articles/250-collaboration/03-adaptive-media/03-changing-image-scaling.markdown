# Changing Adaptive Media's Image Scaling [](id=changing-adaptive-medias-image-scaling)

As described in 
[the Adaptive Media user guide](/discover/portal/-/knowledge_base/7-1/adapting-your-media-across-multiple-devices), 
Adaptive Media scales images to match the image resolutions defined by the 
@product@ administrator. The default scaling is usually suitable, but you can 
also customize it to your needs. Before doing so, however, you should understand 
how this scaling works. 

## Understanding Image Scaling in Adaptive Media [](id=understanding-image-scaling-in-adaptive-media)

Adaptive Media contains an extension point that lets you replace the way it 
scales images. 
[The `AMImageScaler` interface](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-api/src/main/java/com/liferay/adaptive/media/image/scaler/AMImageScaler.java) 
defines Adaptive Media's image scaling logic. Out of the box, Adaptive Media 
provides two implementations of this interface: 

-   [`AMDefaultImageScaler`](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-impl/src/main/java/com/liferay/adaptive/media/image/internal/scaler/AMDefaultImageScaler.java): 
    The default image scaler. It's always enabled and uses `java.awt` for its 
    image processing and scaling. 

-   [`AMGIFImageScaler`](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-impl/src/main/java/com/liferay/adaptive/media/image/internal/scaler/AMGIFImageScaler.java): 
    A scaler that works only with GIF images. It depends on the installation of 
    the external tool 
    [gifsicle](https://www.lcdf.org/gifsicle/) 
    in the @product@ instance. This scaler must be enabled in *Control Panel* 
    &rarr; *System Settings*. 

You must register image scalers in @product@'s OSGi container using the 
`AMImageScaler` interface. Each scaler must also set the `mime.type` property to 
the MIME type it handles. For example, if you set a scaler's MIME type to 
`image/jpeg`, then that scaler can only handle `image/jpeg` images. If you 
specify the special MIME type `*`, the scaler can process any image. Note that 
[the `AMDefaultImageScaler`](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-impl/src/main/java/com/liferay/adaptive/media/image/internal/scaler/AMDefaultImageScaler.java) 
is registered using `mime.type=*`, while 
[the `AMGIFImageScaler`](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-impl/src/main/java/com/liferay/adaptive/media/image/internal/scaler/AMGIFImageScaler.java) 
is registered using `mime.type=image/gif`. Both scalers, like all scalers, 
implement `AMImageScaler`. 

You can add as many image scalers as you need, even for the same MIME type. Even 
so, Adaptive Media uses only one scaler per image, using this process to 
determine the best one: 

1.  Select only the image scalers registered with the same MIME type as the 
    image. 

2.  Select the enabled scalers from those selected in the first step 
    ([the `AMImageScaler`](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-api/src/main/java/com/liferay/adaptive/media/image/scaler/AMImageScaler.java) 
    method `isEnabled()` returns `true` for enabled scalers). 

3.  Of the scalers selected in the second step, select the scaler with the 
    highest `service.ranking`. 

If these steps return no results, they're repeated, but the first step uses the 
special MIME type `*`. Also note that if an image scaler is registered for 
specific MIME types and has a higher `service.ranking`, it's more likely to be 
chosen than if it's registered for the special MIME type `*` or has a lower 
`service.ranking`. 

## Creating an Image Scaler [](id=creating-an-image-scaler)

Now that you know how Adaptive Media scales images, you'll learn how to 
customize this scaling. As an example, you'll see a sample image scaler that 
customizes the scaling of PNG images. 

Follow these steps to create a custom image scaler: 

1.  Create your scaler class to implement `AMImageScaler`. You must also 
    annotate your scaler class with `@Component`, setting `mime.type` properties 
    for each of the scaler's MIME types, and registering an `AMImageScaler` 
    service. If there's more than one scaler for the same MIME type, you must 
    also set the `@Component` annotation's `service.ranking` property. For your 
    scaler to take precedence over other scalers of the same MIME type, its 
    service ranking property must be higher than that of the other scalers. If 
    `service.ranking` isn't set, it defaults to `0`. 

    +$$$

    **Note:** The `service.ranking` property isn't set for the image scalers 
    included with Adaptive Media (`AMDefaultImageScaler` and 
    `AMGIFImageScaler`). Their service ranking therefore defaults to `0`. To 
    replace either scaler, you must set your scaler to the same MIME type and 
    give it a service ranking higher than `0`. 

    $$$

    For example, this sample image scaler scales PNG and x-PNG images, and has a 
    service ranking of `100`: 

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
    simply return `true` in this method. This is the case with the example 
    `SampleAMPNGImageScaler`: 

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
    business logic, and must return an `AMImageScaledImage` instance. For 
    example, the example `scaleImage` implementation in `SampleAMPNGImageScaler` 
    uses `AMImageConfigurationEntry` to get the maximum height and width values 
    for the scaled image, and `FileVersion` to get the image to scale. The 
    scaling is done with the help of a private inner class, assuming that the 
    methods `_scalePNG`, `_getScalePNGHeight`, `_getScalePNGWidth`, and 
    `_getScalePNGSize` implement the actual scaling: 

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

Great! Now you know how to write your own image scalers. 

## Related Topics [](id=related-topics)

[Displaying Adapted Images in Your App](/develop/tutorials/-/knowledge_base/7-1/displaying-adapted-images-in-your-app)

[Finding Adapted Images](/develop/tutorials/-/knowledge_base/7-1/finding-adapted-images)

[Adapting Your Media Across Multiple Devices](/discover/portal/-/knowledge_base/7-1/adapting-your-media-across-multiple-devices)
