# GDPR: Right to be Forgotten and Data Portability [](id=gdpr-right-to-be-forgotten-and-data-portability)

Enforcement of the General Data Protection Regulation (GDPR) began May 25,
2018. Companies processing the personal data of EU residents must adopt
appropriate measures to protect their user data. A wealth of available
resources discuss the implications of the regulation and the steps
companies must take to ensure compliance.

The goal of this article is to help you tackle two of GDPR's technically
challenging requirements, the 
[right to data portability](https://gdpr-info.eu/art-20-gdpr/) 
and the 
[right to be forgotten](https://gdpr-info.eu/art-17-gdpr/) 
on Liferay Portal 6.2. For the upcoming release of @product@ 7.1, this 
[blog post](https://web.liferay.com/web/dennis.ju/blog/-/blogs/upcoming-gdpr-focused-features-for-liferay-d-1)
describes new features to more easily tackle these requirements. These same
features are planned for @product@ version 7.0 in a future service pack release
following the release of @product@ version 7.1. Until then, the principles and
code described here equally apply to versions 6.2 and 7.0. Version 7.1 will have
better native support for complying with GDPR. Consider upgrading to leverage
those features (and more, of course).

The code supplied here demonstrates an approach you can use when developing two
particularly difficult requirements of your full GDPR solution. It's ultimately
your responsibility to assess your own organization's usage of personal data and
the work required to comply fully with GDPR. 

The code referenced here could be deployed via the scripting console or
compiled in a web plugin. It's up to you. A sample web plugin containing this
code can be referenced
[here](https://github.com/liferay/liferay-plugins/tree/6.2.x/webs/sample-personal-data-web).

## Right to Data Portability [](id=right-to-data-portability)

The 
[right to data portability](https://gdpr-info.eu/art-20-gdpr/) 
requires organizations to provide a machine-readable export of a user's personal
data upon request.

There's no easy way through the UI to export a given user's personal data on
Liferay Portal 6.2. One straightforward way to fulfill this requirement
programmatically for applications built using Service Builder is to leverage
[Actionable Dynamic Queries](/develop/tutorials/-/knowledge_base/7-0/dynamic-query).

This example exports all of a user's blogs created using Liferay's
[Blogs](/discover/portal/-/knowledge_base/7-0/publishing-blogs)
app to a JSON-formatted file. It starts by creating an instance of
`BlogsEntryActionableDynamicQuery` and overriding the `addCriteria` method to
grab all `BlogEntries` that match the user by `userId` or `statusByUserId`. This
returns all blogs where the user is the original author or the last editor.

    ActionableDynamicQuery actionableDynamicQuery = new BlogsEntryActionableDynamicQuery() {

        @Override
        protected void addCriteria(DynamicQuery dynamicQuery) {
            Criterion criterion = RestrictionsFactoryUtil.or(
                RestrictionsFactoryUtil.eq("userId", userId),
                RestrictionsFactoryUtil.eq("statusByUserId", userId));

                dynamicQuery.add(criterion);
        }

        @Override
        protected void performAction(Object object) throws PortalException, SystemException {
            // do something
        }
    };

Next it overrides `performAction` to write all matching `BlogEntries` to a JSON
array. It uses the [FasterXML Jackson](https://github.com/FasterXML/jackson)
library to JSONify output, as it provides powerful customization features.

    // the path of our export file
    Path path = Files.createTempFile("export_blogsEntries", ".json");

    JsonFactory jsonFactory = new JsonFactory();

    final JsonGenerator jsonGenerator = jsonFactory.createGenerator(path.toFile(), JsonEncoding.UTF8);

    // begin the JSON array
    jsonGenerator.writeStartObject();
    jsonGenerator.writeFieldName(BlogsEntry.class.getName());
    jsonGenerator.writeStartArray();

    final ObjectMapper objectMapper = new ObjectMapper();

    // avoid exception when Jackson can't serialize a bean
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    ActionableDynamicQuery actionableDynamicQuery = new BlogsEntryActionableDynamicQuery() {
        @Override
        protected void addCriteria(DynamicQuery dynamicQuery) {
            Criterion criterion = RestrictionsFactoryUtil.or(
                RestrictionsFactoryUtil.eq("userId", userId),
                RestrictionsFactoryUtil.eq("statusByUserId", userId));

            dynamicQuery.add(criterion);
        }

        @Override
        protected void performAction(Object object) throws PortalException, SystemException {
            try {
                objectMapper.writeValue(jsonGenerator, object);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    actionableDynamicQuery.performActions();

    // end the JSON array
    jsonGenerator.writeEndArray();
    jsonGenerator.writeEndObject();
    jsonGenerator.close();

This naive implementation serializes all fields for `BlogsEntries` including
system data like `groupId`, `allowPingbacks`, `status`, etc. The is formatted
like this:

    {
        "com.liferay.portlet.blogs.model.BlogsEntry": [
            {
                "visible": true,
                "inTrashExplicitly": false,
                "smallImageType": null,
                "content": "<p>Hello everyone! ...</p>",
                "cachedModel": false,
                "description": "",
                "new": false,
                ...
            }    
        ]
    }

Since a user's personal data is all that must be exported, we leverage Jackson's
filters to specify the right fields:

    // specify the field names we want to export
    Set<String> exportFields = new HashSet<String>(
        Arrays.asList("entryId", "title", "subtitle", "description", "content"));

    filterProvider.addFilter(
        "blogsEntriesFilter", new SimpleBeanPropertyFilter.FilterExceptFilter(exportFields));

    JacksonAnnotationIntrospector jacksonAnnotationIntrospector =
        new JacksonAnnotationIntrospector() {

        @Override
        public Object findFilterId(Annotated annotated) {
            return "blogsEntriesFilter";
        }
    };

    objectMapper.setAnnotationIntrospector(jacksonAnnotationIntrospector);

    final ObjectWriter objectWriter = objectMapper.writer(filterProvider);

    ActionableDynamicQuery actionableDynamicQuery = new BlogsEntryActionableDynamicQuery() {
        ...

        @Override
        protected void performAction(Object object) throws PortalException, SystemException {
            try {
                objectWriter.writeValue(jsonGenerator, object);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

Now the exported data is cleaner and only contains user-inputted personal data.

    {
        "com.liferay.portlet.blogs.model.BlogsEntry": [
            {
                "content": "<p>Hello everyone! ...</p>",
                "description": "",
                "title": "Introducing Dennis",
                "entryId": 11217
            }
        ]
    }

As this code is essentially boilerplate for all of Liferay's service generated
classes, you can generalize this solution using reflection. 

## Right to be Forgotten [](id=right-to-be-forgotten)

The 
[right to erasure](https://gdpr-info.eu/art-17-gdpr/) 
(also known as the "right to be forgotten") requires organizations to erase a
user's personal data upon request (excluding data the
organization has a legitimate reason to retain like financial records, public
interest data, etc).

### Erasing the data [](id=erasing-the-data)

Unfortunately, simply deleting the `User` object by calling
`UserLocalServiceUtil.deleteUser(User user)` does not sufficiently fulfill this
requirement as content created by the user is understandably not deleted.
Fortunately, we can leverage the same logic in the Data Portability code example
to locate and delete a user's personal data. Continuing with the `BlogsEntries`
example, you can simply delete any blog where the `userId` or `statusByUserId`
matches.

    ActionableDynamicQuery actionableDynamicQuery = new BlogsEntryActionableDynamicQuery() {
        @Override
        protected void addCriteria(DynamicQuery dynamicQuery) {
            Criterion criterion = RestrictionsFactoryUtil.or(
                RestrictionsFactoryUtil.eq("userId", userId),
                RestrictionsFactoryUtil.eq("statusByUserId", userId));

            dynamicQuery.add(criterion);
        }

        @Override
        protected void performAction(Object object) throws PortalException, SystemException {
            BlogsEntry blogsEntry = (BlogsEntry)object;
            
            BlogsEntryLocalServiceUtil.deleteEntry(blogsEntry);
        }
    };

    actionableDynamicQuery.performActions();

### Anonymizing the data [](id=anonymizing-the-data)

But what if blogs contain valuable data we want to keep? Perhaps a community
member wrote a blog post on DE 7.0 upgrade tips but now requests to be
forgotten. In many cases, businesses want to anonymize personal data rather than
delete it. Anonymization requires data to be scrubbed such that it "does not
relate to an identified or identifiable natural person" or in "such a manner
that the data subject is not or no longer identifiable" ([recital
26](https://gdpr-info.eu/recitals/no-26/)).

This means at the very least you must alter the `userName` field so the user no
longer appears as the author or editor of the blog. But proper anonymization
requires more than just replacing names, email addresses, and other personally
identifiable data. The blog's content must be reviewed and sanitized. For
example, a blog post on DE 7.0 upgrade tips might begin with an anecdotal
introduction like, "I've seen some horror stories in my ten years as the IT
director of Gamora Inc ..." Even though this sentence doesn't include any
contact details, it's easy to identify the author via a quick social media
search. Unfortunately, this means proper anonymization can potentially be an
extremely cumbersome process. Imagine reviewing the thousands of forum posts an
active member may have posted. Ultimately, whether and to what extent a business
must judiciously review and edit every word, image, and detail during
anonymization must be evaluated by each business. The scale, sensitivity,
likelihood of identification and so on must be weighed, reasoned, and
documented.

For our example, say you want to review the data first. One approach is to
export the personal data first using the code from the "data portability"
section and review the blog title, subtitle, content, etc. Various strategies
can be used to search for certain contact details (like user alias, email
addresses, phone numbers, etc). The content can then be updated either
programmatically or through Liferay's web interface.

After the content has been satisfactorily sanitized, you can replace the
`userId` and `userName` using the same `ActionableDynamicQuery` logic above.
Before doing so, you should create an actual `User` object populated with
anonymous details. This makes it easier to identify all data in the future
that's been anonymized.

    // assume this user has already been created
    final User anonymousUser = UserLocalServiceUtil.getUser(anonymousUserId);

    final String anonymousUserName = anonymousUser.getFullName();

    ActionableDynamicQuery actionableDynamicQuery = new BlogsEntryActionableDynamicQuery() {
        @Override
        protected void addCriteria(DynamicQuery dynamicQuery) {
            Criterion criterion = RestrictionsFactoryUtil.or(
                RestrictionsFactoryUtil.eq("userId", userId),
                RestrictionsFactoryUtil.eq("statusByUserId", userId));

            dynamicQuery.add(criterion);
        }

        @Override
        protected void performAction(Object object) throws PortalException, SystemException {
            BlogsEntry blogsEntry = (BlogsEntry)object;

            if (blogsEntry.getUserId() == userId) {
                blogsEntry.setUserId(anonymousUserId);
                blogsEntry.setUserName(anonymousUserName);
            }
            
            if (blogsEntry.getStatusByUserId() == userId) {
                blogsEntry.setStatusByUserId(anonymousUserId);
                blogsEntry.setStatusByUserName(anonymousUserName);
            }

            BlogsEntryLocalServiceUtil.updateBlogsEntry(blogsEntry);
        }
    };

    actionableDynamicQuery.performActions();

Again, this solution is mostly boilerplate code and can be generalized to data
built using 
[Service Builder](/develop/tutorials/-/knowledge_base/6-2/service-builder).

# Conclusion [](id=conclusion)

These examples demonstrate how to leverage Liferay's existing APIs to address
GDPR's new standard for personal data protection. Ultimately, true compliance
involves much more than technical measures; it requires re-thinking,
re-evaluating, and raising the standards of data protection for most modern
organizations. 
