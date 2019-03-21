---
header-id: adaptive-media
---

# Adaptive Media

The 
[Adaptive Media](/discover/portal/-/knowledge_base/7-2/adapting-your-media-across-multiple-devices) 
app tailors the size and quality of images to the device displaying them. Here, 
you'll learn about these things:

-   [The Adaptive Media Taglib](#the-adaptive-media-taglib)
-   [Adaptive Media's Finder API](#adaptive-medias-finder-api)
-   [Image Scaling in Adaptive Media](#image-scaling-in-adaptive-media)

## The Adaptive Media Taglib

To display adapted images in your apps, Adaptive Media offers a convenient tag 
library in the module 
[`com.liferay.adaptive.media.image.taglib`](https://github.com/liferay/com-liferay-adaptive-media/tree/master/adaptive-media-image-taglib). 
The only mandatory attribute for the taglib is `fileVersion`. It indicates the 
file version of the adapted image to display. The taglib uses this file version 
to query Adaptive Media's finder API and display the adapted image appropriate 
for the device making the request. You can also add as many attributes as 
needed, such as `class`, `style`, `data-sample`, and so on. Any attributes you 
add are then added to the adapted images in the markup the taglib renders. 

For step-by-step instructions on using this taglib, see 
[Displaying Adapted Images in Your App](/developer/frameworks/-/knowledge_base/7-2/displaying-adapted-images-in-your-app). 

## Adaptive Media's Finder API

If you need more control than the taglib offers for finding adapted images, you 
can query Adaptive Media's finder API directly. For example, if you have an app 
that needs a specific image in a specific dimension, it's best to query Adaptive 
Media's finder API directly. You can then display the image however you like 
(e.g., with an HTML `<img>` tag). 

Adaptive Media's finder API lets you write queries that get adapted images based 
on certain search criteria and filters. For example, you can get adapted images 
that match a file version or resolution, or are ordered by an attribute like 
image width. You can even get adapted images that match approximate attribute 
values. 

### Calling the API

The entry point to Adaptive Media's API is 
[`AMImageFinder`](@app-ref@/adaptive-media/latest/javadocs/com/liferay/adaptive/media/image/finder/AMImageFinder.html). 
To use it, you must first inject the OSGi component in your class (which must 
also be an OSGi component) as follows: 

    ```java
    @Reference
    private AMImageFinder _amImageFinder;
    ```

This makes an `AMImageFinder` instance available. It has one method, 
`getAdaptiveMediaStream`, that returns a stream of 
[`AdaptiveMedia`](@app-ref@/adaptive-media/latest/javadocs/com/liferay/adaptive/media/AdaptiveMedia.html) 
objects. This method takes a `Function` that creates an 
[`AMQuery`](@app-ref@/adaptive-media/latest/javadocs/com/liferay/adaptive/media/finder/AMQuery.html) 
(the query for adapted images) via 
[`AMImageQueryBuilder`](@app-ref@/adaptive-media/latest/javadocs/com/liferay/adaptive/media/image/finder/AMImageQueryBuilder.html), 
which can search adapted images based on different attributes (e.g., width, 
height, order, etc.). The `AMImageQueryBuilder` methods you call depend on the 
exact query you want to construct. 

For example, here's a general `getAdaptiveMediaStream` call:

    ```java
    Stream<AdaptiveMedia<AMImageProcessor>> adaptiveMediaStream =
        _amImageFinder.getAdaptiveMediaStream(
            amImageQueryBuilder -> amImageQueryBuilder.methodToCall(arg).done());
    ```

The argument to `getAdaptiveMediaStream` is a lambda expression that returns an 
`AMQuery` constructed via `AMImageQueryBuilder`. Note that `methodToCall(arg)` 
is a placeholder for the `AMImageQueryBuilder` method you want to call and its 
argument. The exact call depends on the criteria you want to use to select 
adapted images. The `done()` call that follows this, however, isn't a 
placeholder--it creates and returns the `AMQuery` regardless of which 
`AMImageQueryBuilder` methods you call. 

For more information on creating `AMQuery` instances, see the 
`AMImageQueryBuilder` 
[Javadoc](@app-ref@/adaptive-media/latest/javadocs/com/liferay/adaptive/media/image/finder/AMImageQueryBuilder.html). 

For step-by-step instructions on calling Adaptive Media's API, see 
[Finding Adapted Images](/developer/frameworks/-/knowledge_base/7-2/finding-adapted-images). 

### Adaptive Media API Constants

When calling the Adaptive Media API, there are some constants you can use for 
specifying common attributes: 

-   `AMImageAttribute.AM_IMAGE_ATTRIBUTE_WIDTH`: image width
-   `AMImageAttribute.AM_IMAGE_ATTRIBUTE_HEIGHT`: image height
-   `AMImageQueryBuilder.SortOrder.ASC`: ascending sort
-   `AMImageQueryBuilder.SortOrder.DESC`: descending sort

### Approximate Attributes

Adaptive Media also lets you get adapted images that match approximate attribute 
values. For example, you can ask for adapted images whose height is around 
200px, or whose size is around 100kb. The API returns a stream with elements 
ordered by how close they are to the specified attribute. For example, imagine 
that there are four image resolutions that have adapted images with the heights 
150px, 350px, 600px, and 900px. Searching for adapted images whose height is 
approximately 400px returns this order in the stream: 350px, 600px, 150px, 
900px. 

So how close, exactly, is *close*? It depends on the attribute. In the case of 
width, height, and length, a numeric comparison orders the images. In the case 
of content type, file name, or UUID, the comparison is more tricky because these 
attributes are strings and thus delegated to Java's 
[`String.compareTo`](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#compareTo-java.lang.String-) 
method. 

## Image Scaling in Adaptive Media

As described in 
[Adaptive Media's user guide](/discover/portal/-/knowledge_base/7-2/adapting-your-media-across-multiple-devices), 
Adaptive Media scales images to match the image resolutions defined by the 
@product@ administrator. The default scaling is usually suitable, but Adaptive 
Media contains an extension point that lets you replace the way it scales 
images. The 
[`AMImageScaler`](@app-ref@/adaptive-media/latest/javadocs/com/liferay/adaptive/media/image/scaler/AMImageScaler.html) 
interface defines Adaptive Media's image scaling logic. Out of the box, Adaptive 
Media provides two implementations of this interface: 

[`AMDefaultImageScaler`](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-impl/src/main/java/com/liferay/adaptive/media/image/internal/scaler/AMDefaultImageScaler.java): 
The default image scaler. It's always enabled and uses `java.awt` for its image 
processing and scaling. 

[`AMGIFImageScaler`](https://github.com/liferay/com-liferay-adaptive-media/blob/master/adaptive-media-image-impl/src/main/java/com/liferay/adaptive/media/image/internal/scaler/AMGIFImageScaler.java): 
A scaler that works only with GIF images. It depends on the installation of the 
external tool 
[gifsicle](https://www.lcdf.org/gifsicle/) 
in the @product@ instance. This scaler is disabled by default. Administrators 
can enable it in *Control Panel* &rarr; *System Settings*. 

You must register image scalers in @product@'s OSGi container using the 
`AMImageScaler` interface. Each scaler must also set the `mime.type` property to 
the MIME type it handles. For example, if you set a scaler's MIME type to 
`image/jpeg`, then that scaler can only handle `image/jpeg` images. If you 
specify the special MIME type `*`, the scaler can process any image. Note that 
`AMDefaultImageScaler` is registered using `mime.type=*`, while 
`AMGIFImageScaler` is registered using `mime.type=image/gif`. Both scalers, like 
all scalers, implement `AMImageScaler`. 

You can add as many image scalers as you need, even for the same MIME type. 
However, Adaptive Media uses only one scaler per image, using this process to 
determine the best one: 

1.  Select only the image scalers registered with the same MIME type as the 
    image. 

2.  Select the enabled scalers from those selected in the first step 
    (the `AMImageScaler` method `isEnabled()` returns `true` for enabled 
    scalers). 

3.  Of the scalers selected in the second step, select the one with the highest 
    `service.ranking`. 

If these steps return no results, they're repeated with the special MIME type 
`*`. Also note that if an image scaler is registered for specific MIME types and 
has a higher `service.ranking`, it's more likely to be chosen than if it's 
registered for the special MIME type `*` or has a lower `service.ranking`. 

For step-by-step instructions on creating your own image scaler, see 
[Creating an Image Scaler](/developer/frameworks/-/knowledge_base/7-2/creating-an-image-scaler). 
