# Finding Adapted Images [](id=finding-adapted-images)

If you need more control than the 
[Adaptive Media taglib](liferay.com) 
offers for finding adapted images to display in your app, you can query Adaptive 
Media's finder API directly. The steps here show you how. For detailed 
information on these steps, see the 
[Adaptive Media introduction](liferay.com). 

## Getting Adapted Images for File Versions

Follow these steps to get adapted images for file versions. Note that the method 
calls here only return adapted images for 
[enabled image resolutions](/discover/portal/-/knowledge_base/7-2/managing-image-resolutions): 

1.  Get an `AMImageFinder` reference: 

        @Reference
        private AMImageFinder _amImageFinder;

2.  To get adapted images for a specific file version, call the 
    [`AMImageQueryBuilder`] method `forFileVersion` with a 
    [`FileVersion` object](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/repository/model/FileVersion.html) 
    as an argument: 

        Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
            _amImageFinder.getAdaptiveMediaStream(
                amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(fileVersion).done());

3.  To get the adapted images for the latest approved file version, use the 
    `forFileEntry` method with a 
    [`FileEntry` object](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/repository/model/FileEntry.html): 

        Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
            _amImageFinder.getAdaptiveMediaStream(
                amImageQueryBuilder -> amImageQueryBuilder.forFileEntry(fileEntry).done());

To get adapted images regardless of status (enabled/disabled image resolutions), 
invoke the `withConfigurationStatus` method with the constant 
`AMImageQueryBuilder.ConfigurationStatus.ANY`: 

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(fileVersion)
                .withConfigurationStatus(AMImageQueryBuilder.ConfigurationStatus.ANY).done());

Use the constant `AMImageQueryBuilder.ConfigurationStatus.DISABLED` to get 
adapted images for only disabled image resolutions. 

## Getting the Adapted Images for a Specific Image Resolution [](id=getting-the-adapted-images-for-a-specific-image-resolution)

By providing an image resolution's UUID to `AMImageFinder`, you can get that 
resolution's adapted images. This UUID is defined when 
[adding the resolution](/discover/portal/-/knowledge_base/7-2/adding-image-resolutions) 
in the Adaptive Media app. To get a resolution's adapted images, you must pass 
that resolution's UUID to the `forConfiguration` method. 

Follow these steps to get adapted images for an image resolution: 

1.  Get an `AMImageFinder` reference: 

        @Reference
        private AMImageFinder _amImageFinder;

2.  Call the 
    [`AMImageQueryBuilder.ConfigurationStep`](@app-ref@/adaptive-media/latest/javadocs/com/liferay/adaptive/media/image/finder/AMImageQueryBuilder.ConfigurationStep.html) 
    method `forConfiguration` with the image resolution's UUID. For example, 
    this code gets the adapted images that match a file version, and belong to 
    an image resolution with the UUID `hd-resolution`. It returns the adapted 
    images regardless of whether the resolution is enabled or disabled: 

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(fileVersion)
                .forConfiguration("hd-resolution").done());

## Getting Adapted Images in a Specific Order [](id=getting-adapted-images-in-a-specific-order)

It's also possible to define the order in which `getAdaptiveMediaStream` returns 
adapted images. Follow these steps to do so: 

1.  Get an `AMImageFinder` reference: 

        @Reference
        private AMImageFinder _amImageFinder;

2.  Call the `orderBy` method with your sort criteria just before calling the 
    `done()` method. The `orderBy` method takes two arguments: the first 
    specifies the image attribute to sort by (e.g., width/height), while the 
    second specifies the sort order (e.g., ascending/descending). The Adaptive 
    Media API provides 
    [constants](liferay.com) 
    that you can use for these arguments. 

    For example, this code gets all the adapted images regardless of whether the 
    image resolution is enabled, and puts them in ascending order by the image 
    width: 

        Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
            _amImageFinder.getAdaptiveMediaStream(
                amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(_fileVersion)
                    .withConfigurationStatus(AMImageQueryBuilder.ConfigurationStatus.ANY)
                    .orderBy(AMImageAttribute.AM_IMAGE_ATTRIBUTE_WIDTH, AMImageQueryBuilder.SortOrder.ASC)
                    .done());

## Using Approximate Attributes

Adaptive Media also lets you get adapted images that match approximate attribute 
values. Follow these steps to do so: 

1.  Get an `AMImageFinder` reference: 

        @Reference
        private AMImageFinder _amImageFinder;

2.  Call the `with` method with your search criteria just before calling the 
    `done()` method. The `with` method takes two arguments: the image attribute, 
    and that attribute's approximate value. For example, this code gets adapted 
    images whose height is approximately 400px: 

        Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
            _amImageFinder.getAdaptiveMediaStream(
                amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(_fileVersion)
                    .with(AMImageAttribute.AM_IMAGE_ATTRIBUTE_HEIGHT, 400).done());

## Using the Adaptive Media Stream [](id=using-the-adaptive-media-stream)

Once you have the 
[`AdaptiveMedia`](@app-ref@/adaptive-media/latest/javadocs/com/liferay/adaptive/media/AdaptiveMedia.html) 
stream, you can get the information you need from it. For example, this code 
prints the URI for each adapted image: 

    adaptiveMediaStream.forEach(
        adaptiveMedia -> {
            System.out.println(adaptiveMedia.getURI());
        }
    );

You can also get other values and attributes from the `AdaptiveMedia` stream. 
Here are a few examples: 

    // Get the InputStream 
    adaptiveMedia.getInputStream()

    // Get the content length
    adaptiveMedia.getValueOptional(AMAttribute.getContentLengthAMAttribute())

    // Get the image height
    adaptiveMedia.getValueOptional(AMImageAttribute.AM_IMAGE_ATTRIBUTE_HEIGHT)
