# Finding Adapted Images [](id=finding-adapted-images)

+$$$

**Note:** This documentation is currently in beta. 

$$$

<!-- 
Is this required to display adapted images as described in the first tutorial? 
E.g., what's the purpose of getting adapted images? What do you do with them 
once you have them?
-->
Adaptive Media's Java API lets you write queries that get adapted images based 
on certain search criteria and filters. For example, you can get adapted images 
that match a file version or resolution, or are ordered by an attribute like 
image width. You can even get adapted images that match approximate attribute 
values (*fuzzy* attributes). 

This tutorial shows you how to call Adaptive Media's API to get adapted images 
in your app. First, you'll learn how to construct such API calls. 

## Calling Adaptive Media's API

The entry point to Adaptive Media's API is 
[the `AMImageFinder` interface](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-api/src/main/java/com/liferay/adaptive/media/image/finder/AMImageFinder.java). 
To use it, you must first inject the OSGi component in your class as follows: 

    @Reference
    private AMImageFinder _amImageFinder;

This makes an `AMImageFinder` instance available. `AMImageFinder` has one 
method, `getAdaptiveMediaStream`, that returns a stream of `AdaptiveMedia` 
objects. This method receives a `Function` that creates an `AMQuery` based on an 
`AMImageQueryBuilder`. The `AMImageQueryBuilder` class, which can search adapted 
images based on different attributes (e.g., width, height, order, etc.), creates 
the query that `AMImageFinder` uses to get the matching adapted images. 
`AMImageQueryBuilder` is designed to be used via chaining to improve the clarity 
when reading the source code. 

For example, here's a general `getAdaptiveMediaStream` call:

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.methodToCall(arg).done());

The argument to `getAdaptiveMediaStream` is a lambda expression that returns an 
`AMQuery` constructed via `AMImageQueryBuilder`. Note that `methodToCall(arg)` 
is a placeholder for the `AMImageQueryBuilder` method you want to call and its 
argument. The exact call depends on what criteria you want to use to select 
adapted images. The `done()` call that follows this, however, isn't a 
placeholder--it creates and returns the `AMQuery` regardless of which 
`AMImageQueryBuilder` methods you call. 

To better understand the possibilities for 
creating `AMQuery`, see 
[the Javadoc for `AMImageQueryBuilder`](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-api/src/main/java/com/liferay/adaptive/media/image/finder/AMImageQueryBuilder.java). 

Next, you'll see different examples of constructing such calls that get adapted 
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

Note that these calls only return the adapted images whose 
[image resolutions are enabled](/discover/portal/-/knowledge_base/7-0/managing-image-resolutions) 
in the Adaptive Media app. Adapted images of disabled resolutions aren't 
included in the stream. To retrieve every adapted image regardless of any image 
resolution's status, you must do the following: 

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(fileVersion)
                .withConfigurationStatus(AMImageQueryBuilder.ConfigurationStatus.ANY).done());

To get adapted images for a specific file version, when the image resolution is 
disabled, use the following: 

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(fileVersion)
                .withConfigurationStatus(AMImageQueryBuilder.ConfigurationStatus.DISABLED).done());

Once you have the stream of Adaptive Media, you can get the information that you 
need. For example, the following code prints the URIs for each of the adapted 
images: 

    adaptiveMediaStream.forEach(
        adaptiveMedia -> {
            System.out.println(adaptiveMedia.getURI());
        }
    );

You can also get the `InputStream` by invoking `adaptiveMedia.getInputStream()`, 
or the content length by invoking 
`adaptiveMedia.getValueOptional(AMAttribute.getContentLengthAMAttribute())`, or 
the image's height by invoking 
`adaptiveMedia.getValueOptional(AMImageAttribute.AM_IMAGE_ATTRIBUTE_HEIGHT)`. 

## Getting the Adapted Images for a Specific Image Resolution [](id=getting-the-adapted-images-for-a-specific-image-resolution)

There may be scenarios where you want only the adapted images generated for a 
specific image resolution. The `AMImageFinder` lets you get such images by 
providing that resolution's UUID. This UUID can be defined when 
[adding the resolution](/discover/portal/-/knowledge_base/7-0/adding-image-resolutions) 
in the Adaptive Media app. By default, it uses a *safe* version of the 
resolution's name.

The following example gets the adapted images for a specific image resolution. 
It returns the adapted images regardless of whether the resolution is enabled or 
disabled. Note that the compiler won't let you chain with the method 
`withConfigurationStatus`.

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(fileVersion)
                .forConfiguration("hd-resolution").done());

## Getting Adapted Images in a Specific Order [](id=getting-adapted-images-in-a-specific-order)

You can also get adapted images for a specific `fileEntry` or `fileVersion`, 
ordered by certain attributes. For example, you may want to get all the adapted 
images regardless of whether the image resolution is enabled, ordered by the 
image width: 

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinderImpl.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(_fileVersion)
                .withConfigurationStatus(AMImageQueryBuilder.ConfigurationStatus.ANY)
                .orderBy(AMImageAttribute.AM_IMAGE_ATTRIBUTE_WIDTH, AMImageQueryBuilder.SortOrder.ASC)
                .done());

## Getting Adapted Images with Fuzzy Attributes [](id=getting-adapted-images-with-fuzzy-attributes)

Adaptive Media also lets you get adapted images with *fuzzy attributes*. This 
means that you can ask for adapted images whose height is around 200px, or whose 
size is around 200kb, for example. Adaptive Media returns a stream whose 
elements are ordered based on how close they are to the specific attribute. The 
concept of *close* is different depending on the attributes. In the case of 
width, height, and length, a numeric comparison orders the adapted images. In 
the case of content type, file name, or UUID, the comparison is more tricky 
because they are strings, and thus delegated to 
[the Java `String.compareTo` method](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#compareTo(java.lang.String)). 

Here's an example of how to obtain a stream of adapted images whose height is 
similar to 400px: 

    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinderImpl.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.forFileVersion(_fileVersion)
                .with(AMImageAttribute.AM_IMAGE_ATTRIBUTE_HEIGHT, 400).done());

Imagine that there are four enabled image resolutions that have generated 
adapted images with the widths 150px, 350px, 600px, and 900px. Using this code 
returns the following order in the stream:  350px, 600px, 150px, 900px. 

Awesome! Now you know how to find adapted images. 

## Related Topics [](id=related-topics)

[Displaying Adapted Images in Your App](/develop/tutorials/-/knowledge_base/7-0/displaying-adapted-images-in-your-app)

[Changing Adaptive Media's Image Processing](/develop/tutorials/-/knowledge_base/7-0/changing-adaptive-medias-image-processing)

[Adapting Your Media Across Multiple Devices](/discover/portal/-/knowledge_base/7-0/adapting-your-media-across-multiple-devices)
