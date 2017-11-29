# Changing Adaptive Media's Image Processing [](id=changing-adaptive-medias-image-processing)

+$$$

**Note:** This documentation is currently in beta. 

$$$

As described in 
[the Adaptive Media user guide](/discover/portal/-/knowledge_base/7-0/adapting-your-media-across-multiple-devices), 
Adaptive Media generates adapted images that match the image resolutions defined 
by the @product@ administrator. To do this, Adaptive Media scales the original 
images to the image resolutions' specifications, thereby generating the adapted 
images while leaving the original images intact. 

This scaling is done with settings and configurations that are suitable for most 
situations. But what if your situation isn't most situations? What if you need 
to customize this scaling to your exact requirements? No problem! You can tune 
and customize Adaptive Media's image scaling to an infinite number of 
possibilities. Adaptive Media contains an extension point that lets third-party 
developers easily replace the way the images are scaled. 

[The `AMImageScaler` interface](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-api/src/main/java/com/liferay/adaptive/media/image/scaler/AMImageScaler.java) 
defines the logic of Adaptive Media's image scaling. Out of the box, Adaptive 
Media provides two implementations of this interface. 

-   [`AMDefaultImageScaler`](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-impl/src/main/java/com/liferay/adaptive/media/image/internal/scaler/AMDefaultImageScaler.java): 
    The default image scaler. It's always enabled and uses `java.awt` to do the 
    image processing and scaling. 

-   [`AMGIFImageScaler`](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-impl/src/main/java/com/liferay/adaptive/media/image/internal/scaler/AMGIFImageScaler.java): 
    A scaler that works only with GIF images. It depends on the installation of 
    the external tool 
    ([gifsicle](https://www.lcdf.org/gifsicle/)) 
    in the @product@ instance. This scaler must be enabled in *Control Panel* 
    &rarr; *System Settings*. 

You must register image scalers in @product@'s OSGi container using the 
`AMImageScaler` interface. Each scaler must also set the `mime.type` property to 
specify the MIME type it can handle. For example, if you set a specific MIME 
type like `image/jpeg` for a scaler, then that scaler can only handle images of 
the `image/jpeg` MIME type. If you use the special MIME type `*`, the scaler can 
process any kind of image. As another example, 
[the scaler `AMDefaultImageScaler`](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-impl/src/main/java/com/liferay/adaptive/media/image/internal/scaler/AMDefaultImageScaler.java) 
is registered using `mime.type=*`, while 
[the scaler `AMGIFImageScaler`](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-impl/src/main/java/com/liferay/adaptive/media/image/internal/scaler/AMGIFImageScaler.java) 
is registered using `mime.type=image/gif`. 

Third party developers can add as many image scalers as they need. Even though 
there can be multiple scalers, even for the same MIME type, Adaptive Media only 
uses one to process and scale the image. Adaptive Media follows this process to 
determine the best scaler for each image: 

1.  Selects only the image scalers registered with the same MIME type of the 
    image that is going to be processed. 

2.  Of the scalers selected in the first step, the enabled scalers are selected 
    (enabled scalers return `true` for 
    [the `AMImageScaler` method `isEnabled()`](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-api/src/main/java/com/liferay/adaptive/media/image/scaler/AMImageScaler.java#L55)). 

3.  Of the scalers selected in the second step, the scaler with the highest 
    `service.ranking` property value is selected. 

You should keep a few things in mind about this process. If these steps return 
no results, they are repeated with the special MIME type `*` to make the first 
selection. Also note that if the image scaler is registered for specific MIME 
types and has a higher `service.ranking`, it's more likely to be chosen than if 
it's registered for the special MIME type `*` or has a lower `service.ranking`. 

## Changing Adaptive Media's Image Scaling for a Particular MIME Type [](id=changing-adaptive-medias-image-scaling-for-a-particular-mime-type)

Now that you know how Adaptive Media scales images, you'll learn how to 
customize that scaling. As an example, you'll learn how to alter the scaling of 
PNG images via another library. To accomplish this, you'll add a new 
`AMImageScaler` and register it using the property `mime.type=image/png`. You'll 
then have a Java class named `SampleAMPNGImageScaler` that you must annotate 
with `@Component` and register with `mime.type=image/png` for the interface 
`AMImageScaler`. You don't need a `service.ranking` property because you don't 
have any other scalers registered for PNGs (but you should add it if @product@ 
already has such scaler). 

Here's the example `SampleAMPNGImageScaler`: 

    package com.sample.adaptive.media.image.internal.scaler;

    import com.liferay.adaptive.media.image.configuration.AMImageConfigurationEntry;
    import com.liferay.adaptive.media.image.scaler.AMImageScaledImage;
    import com.liferay.adaptive.media.image.scaler.AMImageScaler;
    import com.liferay.portal.kernel.repository.model.FileVersion;
    import com.liferay.portal.kernel.util.GetterUtil;

    import java.util.Map;

    import org.osgi.service.component.annotations.Component;

    @Component(
        immediate = true, property = {"mime.type=image/png"},
        service = {AMImageScaler.class}
    )
    public class SampleAMPNGImageScaler implements AMImageScaler {

        @Override
        public boolean isEnabled() {
            return true;
        }

        @Override
        public AMImageScaledImage scaleImage(FileVersion fileVersion,
            AMImageConfigurationEntry amImageConfigurationEntry) {

            Map<String, String> properties = amImageConfigurationEntry.getProperties();

            int maxHeight = GetterUtil.getInteger(properties.get("max-height"));
            int maxWidth = GetterUtil.getInteger(properties.get("max-width"));

            // Custom code here
    
            return null;
        }

    }

The `isEnabled` method is helpful when the scaler depends on other tools or 
features. This method should return `true` only when those tools are enabled. To 
simplify things, the `isEnabled` method in this example always returns `true`. 
The `scaleImage` method contains the scaler's business logic. 
`AMImageConfigurationEntry` provides the `maxWidth` and `maxHeight` values for 
the scaled image, and `FileVersion` provides the image that needs to be scaled. 

The `scaleImage` method needs to return an `AMImageScaledImage` instance, so the 
following code is a more detailed example of how to achieve that using a private 
inner class and assuming that the methods `_scalePNG`, `_getScalePNGHeight`, 
`_getScalePNGWidth`, and `_getScalePNGSize` implement the scaling feature. 

    package com.liferay.adaptive.media.image.internal.scaler;

    import com.liferay.adaptive.media.exception.AMRuntimeException;
    import com.liferay.adaptive.media.image.configuration.AMImageConfigurationEntry;
    import com.liferay.adaptive.media.image.scaler.AMImageScaledImage;
    import com.liferay.adaptive.media.image.scaler.AMImageScaler;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.repository.model.FileVersion;
    import com.liferay.portal.kernel.util.GetterUtil;

    import java.io.InputStream;

    import java.util.Map;

    import org.osgi.service.component.annotations.Component;

    @Component(
        immediate = true, property = {"mime.type=image/png"},
        service = {AMImageScaler.class}
    )
    public class SampleAMPNGImageScaler implements AMImageScaler {

        @Override
        public boolean isEnabled() {
            return true;
        }

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

    }

Image Scalers can be registered to more than one MIME type. For example, in the 
previous example, you may want to register `image/x-png` as well as `image/png`. 
You don't need to create another component to do this. You can register both 
properties in the same component as follows: 

    @Component(
        immediate = true, property = {"mime.type=image/png", "mime.type=image/x-png"},
        service = {AMImageScaler.class}
    )

## Replace Adaptive Media's GIF Image Scaling [](id=replace-adaptive-medias-gif-image-scaling)

Adaptive Media's default image scaler for GIF images is based on 
[gifsicle](https://www.lcdf.org/gifsicle/). 
This ensures that GIFs are scaled safely without losing the animation. You can, 
however, replace this scaler. The example here replaces it with a scaler that 
uses a Java library to scale the GIFs. You'll follow the same steps as before, 
but use the MIME type `image/gif`. You must also include a service ranking to 
ensure that the new image scaler overrides the default one. 

Here's an example: 

    package com.sample.adaptive.media.image.internal.scaler;

    import com.liferay.adaptive.media.exception.AMRuntimeException;
    import com.liferay.adaptive.media.image.configuration.AMImageConfigurationEntry;
    import com.liferay.adaptive.media.image.scaler.AMImageScaledImage;
    import com.liferay.adaptive.media.image.scaler.AMImageScaler;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.repository.model.FileVersion;
    import com.liferay.portal.kernel.util.GetterUtil;

    import java.io.InputStream;

    import java.util.Map;

    import org.osgi.service.component.annotations.Component;

    @Component(
        immediate = true, property = {"mime.type=image/gif", "service.ranking:Integer=100"},
        service = {AMImageScaler.class}
    )
    public class SampleAMGIFImageScaler implements AMImageScaler {

        @Override
        public boolean isEnabled() {
            return true;
        }

        @Override
        public AMImageScaledImage scaleImage(FileVersion fileVersion, 
            AMImageConfigurationEntry amImageConfigurationEntry) {

            //...Same as before...

        }

    }

This new `SampleAMGIFImageScaler` component replaces the default 
`AMGIFImageScaler` because it's registered with the same MIME type and has a 
higher service ranking (`SampleAMGIFImageScaler`'s service ranking is `100`, and 
`AMGIFImageScaler`'s is `0`, which is the default value if non specified). 

## Replacing Adaptive Media's Default Image Scaler [](id=replacing-adaptive-medias-default-image-scaler)

You can also replace Adaptive Media's default image scaler, 
`AMDefaultImageScaler`. This default scaler is used for an image as long as 
there's no other scaler registered for that image's MIME type. As an example, 
imagine that you want to customize the default scaler's logic. To do this, you 
must add a new `AMImageScaler`, register it with the special MIME type `*`, and 
set its service ranking property higher than `0`. 

Here's an example of such a class: 

    package com.sample.adaptive.media.image.internal.scaler;

    import com.liferay.adaptive.media.exception.AMRuntimeException;
    import com.liferay.adaptive.media.image.configuration.AMImageConfigurationEntry;
    import com.liferay.adaptive.media.image.scaler.AMImageScaledImage;
    import com.liferay.adaptive.media.image.scaler.AMImageScaler;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.repository.model.FileVersion;
    import com.liferay.portal.kernel.util.GetterUtil;

    import java.io.InputStream;

    import java.util.Map;

    import org.osgi.service.component.annotations.Component;

    @Component(
        immediate = true, property = {"mime.type=*", "service.ranking:Integer=100"},
        service = {AMImageScaler.class}
    )
    public class SampleAMDefaultImageScaler implements AMImageScaler {

        @Override
        public boolean isEnabled() {
            return true;
        }

        @Override
        public AMImageScaledImage scaleImage(FileVersion fileVersion,
            AMImageConfigurationEntry amImageConfigurationEntry) {

            //...Same as before...

        }

    }

Great! Now you know how to customize Adaptive Media's image scaling. 

## Related Topics [](id=related-topics)

[Displaying Adapted Images in Your App](/develop/tutorials/-/knowledge_base/7-0/displaying-adapted-images-in-your-app)

[Finding Adapted Images](/develop/tutorials/-/knowledge_base/7-0/finding-adapted-images)

[Adapting Your Media Across Multiple Devices](/discover/portal/-/knowledge_base/7-0/adapting-your-media-across-multiple-devices)
