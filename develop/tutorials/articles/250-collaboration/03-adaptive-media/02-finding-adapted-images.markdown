# Finding Adapted Images [](id=finding-adapted-images)

In most cases, you can rely on the Adaptive Media taglib to 
[display adapted images in your app](/develop/tutorials/-/knowledge_base/7-1/displaying-adapted-images-in-your-app). 
This taglib uses the file version you give it to query Adaptive Media's finder 
API and display the adapted image appropriate for the device making the request. 
If you need more control, however, you can write your own query with the API 
instead of using the taglib. For example, if you have an app that needs a 
specific image in a specific dimension, it's best to query Adaptive Media's 
finder API directly. You can then display the image however you like (e.g., with 
an HTML `<img>` tag). 

Adaptive Media's finder API lets you write queries that get adapted images based 
on certain search criteria and filters. For example, you can get adapted images 
that match a file version or resolution or are ordered by an attribute like 
image width. You can even get adapted images that match approximate attribute 
values (*fuzzy* attributes). 

This tutorial shows you how to call Adaptive Media's API to get adapted images 
in your app. First, you'll learn how to construct such API calls. 

## Calling Adaptive Media's API [](id=calling-adaptive-medias-api)

The entry point to Adaptive Media's API is 
[the `AMImageFinder` interface](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-api/src/main/java/com/liferay/adaptive/media/image/finder/AMImageFinder.java). 
To use it, you must first inject the OSGi component in your class, which must 
also be an OSGi component, as follows: 

    @Reference
    private AMImageFinder _amImageFinder;

This makes an `AMImageFinder` instance available. It has one method, 
`getAdaptiveMediaStream`, that returns a stream of `AdaptiveMedia` objects. This 
method takes a `Function` that creates an `AMQuery` (the query for adapted 
images) via `AMImageQueryBuilder`, which can search adapted images based on 
different attributes (e.g., width, height, order, etc.). The 
`AMImageQueryBuilder` methods you call depend on the exact query you want to 
construct. 

For example, here's a general `getAdaptiveMediaStream` call:

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.methodToCall(arg).done());

The argument to `getAdaptiveMediaStream` is a lambda expression that returns an 
`AMQuery` constructed via `AMImageQueryBuilder`. Note that `methodToCall(arg)` 
is a placeholder for the `AMImageQueryBuilder` method you want to call and its 
argument. The exact call depends on the criteria you want to use to select 
adapted images. The `done()` call that follows this, however, isn't a 
placeholder--it creates and returns the `AMQuery` regardless of which 
`AMImageQueryBuilder` methods you call. 

For more information on creating `AMQuery` instances, see 
[the Javadoc for `AMImageQueryBuilder`](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-api/src/main/java/com/liferay/adaptive/media/image/finder/AMImageQueryBuilder.java). 

Next, you'll see specific examples of constructing calls that get adapted 
images. 

## Getting Adapted Images for a Specific File Version [](id=getting-the-adapted-images-for-a-specific-file-version)

To get adapted images for a specific file version, you must call the 
`AMImageQueryBuilder` method `forFileVersion` with a 
[`FileVersion` object](https://docs.liferay.com/portal/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/repository/model/FileVersion.html) 
as an argument: 

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(fileVersion).done());

To get the adapted images for the latest approved file version, use the 
`forFileEntry` method with a 
[`FileEntry` object](https://docs.liferay.com/portal/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/repository/model/FileVersion.html): 

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileEntry(fileEntry).done());

Note that these calls only return the adapted images for 
[enabled image resolutions](/discover/portal/-/knowledge_base/7-0/managing-image-resolutions). 
Adapted images for disabled resolutions aren't included in the stream. To 
retrieve all adapted images regardless of any image resolution's status, you 
must also call the `withConfigurationStatus` method with the constant 
`AMImageQueryBuilder.ConfigurationStatus.ANY`: 

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(fileVersion)
                .withConfigurationStatus(AMImageQueryBuilder.ConfigurationStatus.ANY).done());

To get adapted images for a specific file version when the image resolution is 
disabled, make the same call but instead use the constant 
`AMImageQueryBuilder.ConfigurationStatus.DISABLED`: 

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(fileVersion)
                .withConfigurationStatus(AMImageQueryBuilder.ConfigurationStatus.DISABLED).done());

Next, you'll learn how to get adapted images for a specific image resolution. 

## Getting the Adapted Images for a Specific Image Resolution [](id=getting-the-adapted-images-for-a-specific-image-resolution)

By providing an image resolution's UUID to `AMImageFinder`, you can get that 
resolution's adapted images. This UUID is defined when 
[adding the resolution](/discover/portal/-/knowledge_base/7-0/adding-image-resolutions) 
in the Adaptive Media app. To get a resolution's adapted images, you must pass 
that resolution's UUID to the `forConfiguration` method. 

For example, this code gets the adapted images that match a file version, and 
belong to an image resolution with the UUID `hd-resolution`. It returns the 
adapted images regardless of whether the resolution is enabled or disabled: 

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(fileVersion)
                .forConfiguration("hd-resolution").done());

Next, you'll learn how to return adapted images in a specific order. 

## Getting Adapted Images in a Specific Order [](id=getting-adapted-images-in-a-specific-order)

It's also possible to define the order in which `getAdaptiveMediaStream` returns 
adapted images. To do this, call the `orderBy` method with your sort criteria 
just before calling the `done()` method. The `orderBy` method takes two 
arguments: the first specifies the image attribute to sort by (e.g., 
width/height), while the second specifies the sort order (e.g., 
ascending/descending). 

For example, this code gets all the adapted images regardless of whether the 
image resolution is enabled, and puts them in ascending order by the image 
width: 

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinderImpl.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(_fileVersion)
                .withConfigurationStatus(AMImageQueryBuilder.ConfigurationStatus.ANY)
                .orderBy(AMImageAttribute.AM_IMAGE_ATTRIBUTE_WIDTH, AMImageQueryBuilder.SortOrder.ASC)
                .done());

The `orderBy` arguments `AMImageAttribute.AM_IMAGE_ATTRIBUTE_WIDTH` and 
`AMImageQueryBuilder.SortOrder.ASC` specify the image width and ascending sort, 
respectively. You can alternatively use 
`AMImageAttribute.AM_IMAGE_ATTRIBUTE_HEIGHT` to sort by image height, and 
`AMImageQueryBuilder.SortOrder.DESC` to perform a descending sort. 

Next, you'll learn how to specify approximate attribute values when getting 
adapted images. 

## Getting Adapted Images with Fuzzy Attributes [](id=getting-adapted-images-with-fuzzy-attributes)

Adaptive Media also lets you get adapted images that match *fuzzy attributes* 
(approximate attribute values). For example, fuzzy attributes let you ask for 
adapted images whose height is around 200px, or whose size is around 100kb. The 
API returns a stream with elements ordered by how close they are to the 
specified attribute. For example, imagine that there are four image resolutions 
that have adapted images with the heights 150px, 350px, 600px, and 900px. 
Searching for adapted images whose height is approximately 400px returns this 
order in the stream:  350px, 600px, 150px, 900px. 

So how close, exactly, is *close*? It depends on the attribute. In the case of 
width, height, and length, a numeric comparison orders the images. In the case 
of content type, file name, or UUID, the comparison is more tricky because these 
attributes are strings and thus delegated to 
[the Java `String.compareTo` method](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#compareTo-java.lang.String-). 

To specify a fuzzy attribute, call the `with` method with your search criteria 
just before calling the `done()` method. The `with` method takes two arguments: 
the image attribute, and that attribute's approximate value. For example, this 
code gets adapted images whose height 
(`AMImageAttribute.AM_IMAGE_ATTRIBUTE_HEIGHT`) is approximately 400px:

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinderImpl.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(_fileVersion)
                .with(AMImageAttribute.AM_IMAGE_ATTRIBUTE_HEIGHT, 400).done());

To search for image width instead, use 
`AMImageAttribute.AM_IMAGE_ATTRIBUTE_WIDTH` as the first argument to the `width` 
method. 

## Using the Adaptive Media Stream [](id=using-the-adaptive-media-stream)

Once you have the `AdaptiveMedia` stream, you can get the information you need 
from it. For example, this code prints the URI for each adapted image: 

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

Awesome! Now you know how to find and use adapted images. 

## Related Topics [](id=related-topics)

[Displaying Adapted Images in Your App](/develop/tutorials/-/knowledge_base/7-1/displaying-adapted-images-in-your-app)

[Changing Adaptive Media's Image Scaling](/develop/tutorials/-/knowledge_base/7-1/changing-adaptive-medias-image-scaling)

[Adapting Your Media Across Multiple Devices](/discover/portal/-/knowledge_base/7-1/adapting-your-media-across-multiple-devices)
