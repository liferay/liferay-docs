---
header-id: providing-friendly-urls-for-a-custom-content-type
---

# Providing Friendly URLs for a Custom Content Type

[TOC levels=1-4]

To provide a friendly URL for your custom content type, you must implement a
friendly URL resolver to retrieve the desired information. For the User content
type example, you'll want to retrieve profiles by a user's screen name.

To do this, follow the steps below.

1.  Open the `*InfoDisplayContributor` class you created previously (covered
    [here](/docs/7-2/frameworks/-/knowledge_base/f/mapping-a-content-type-to-a-page)).
    Implement the `getInfoDisplayObjectProvider` method. For the User example,
    it looks like this:

    ```java
    @Override
    public InfoDisplayObjectProvider<User> getInfoDisplayObjectProvider(
            long groupId, String urlTitle)
        throws PortalException {

        Group group = _groupLocalService.getGroup(groupId);

        User user = _userLocalService.getUserByScreenName(
            group.getCompanyId(), urlTitle);

        return new InfoDisplayObjectProvider<User>() {

            @Override
            public long getClassNameId() {
                return _classNameLocalService.getClassNameId(getClassName());
            }

            @Override
            public long getClassPK() {
                return user.getUserId();
            }

            @Override
            public long getClassTypeId() {
                return 0;
            }

            @Override
            public String getDescription(Locale locale) {
                return StringPool.BLANK;
            }

            @Override
            public User getDisplayObject() {
                return user;
            }

            @Override
            public long getGroupId() {
                return groupId;
            }

            @Override
            public String getKeywords(Locale locale) {
                return StringPool.BLANK;
            }

            @Override
            public String getTitle(Locale locale) {
                return user.getFullName();
            }

            @Override
            public String getURLTitle(Locale locale) {
                return user.getScreenName();
            }

        };
    }
    ```

    This method returns a new `InfoDisplayObjectProvider` for the User type.
    This is the specific model instance used to retrieve the mapped values and
    check for the display page. This is required by the friendly URL resolver.
    Now you'll implement the friendly URL resolver for the User content type.

2.  Inside your custom model project, add a class named
    `UserDisplayPageFriendlyURLResolver`.

3.  Implement the
    [`FriendlyURLResolver`](@platform-ref@//7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/FriendlyURLResolver.html)
    interface. Then add the `@Component` annotation:

    ```java
    @Component(service = FriendlyURLResolver.class)
    public class UserDisplayPageFriendlyURLResolver implements FriendlyURLResolver {

    }
    ```

    The `@Component` annotation declares the class as a friendly URL resolver in
    the OSGi service registry.

4.  Implement the methods. The User type's implementation looks like this:

    ```java
    @Override
    public String getActualURL(
        long companyId, long groupId, boolean privateLayout,
          String mainPath, String friendlyURL, Map<String, String[]> params,
        Map<String, Object> requestContext)
    throws PortalException {

        HttpServletRequest request = (HttpServletRequest)requestContext.get(
            "request");

        InfoDisplayContributor infoDisplayContributor =
            _getInfoDisplayContributor();

        List<String> paths = StringUtil.split(friendlyURL, CharPool.SLASH);

        InfoDisplayObjectProvider infoDisplayObjectProvider =
            infoDisplayContributor.getInfoDisplayObjectProvider(
                groupId, paths.get(1));

        request.setAttribute(
            AssetDisplayPageWebKeys.INFO_DISPLAY_OBJECT_PROVIDER,
                infoDisplayObjectProvider);

        request.setAttribute(
            InfoDisplayWebKeys.INFO_DISPLAY_CONTRIBUTOR,
                infoDisplayContributor);

        Layout layout = _getInfoDisplayObjectProviderLayout(
            infoDisplayObjectProvider);

        return _portal.getLayoutActualURL(layout, mainPath);
    }

    @Override
    public LayoutFriendlyURLComposite getLayoutFriendlyURLComposite(
        long companyId, long groupId, boolean privateLayout,
        String friendlyURL, Map<String, String[]> params,
        Map<String, Object> requestContext)
    throws PortalException {

        Layout layout = _getInfoDisplayObjectProviderLayout(
            _getInfoDisplayObjectProvider(groupId, friendlyURL));

        return new LayoutFriendlyURLComposite(layout, friendlyURL);
    }

    @Override
    public String getURLSeparator() {
        return "/user/";
    }

    private InfoDisplayContributor _getInfoDisplayContributor()
        throws PortalException {

        InfoDisplayContributor infoDisplayContributor =
            _infoDisplayContributorTracker.
                getInfoDisplayContributorByURLSeparator(getURLSeparator());

        if (infoDisplayContributor == null) {
            throw new PortalException(
                "Info display contributor is not available for " +
                getURLSeparator());
        }

        return infoDisplayContributor;
    }

    private InfoDisplayObjectProvider _getInfoDisplayObjectProvider(
            long groupId, String friendlyURL)
        throws PortalException {

        List<String> paths = StringUtil.split(friendlyURL, CharPool.SLASH);

        InfoDisplayContributor infoDisplayContributor =
            _infoDisplayContributorTracker.getInfoDisplayContributor(
                User.class.getName());

        return infoDisplayContributor.getInfoDisplayObjectProvider(
            groupId, paths.get(1));
    }

    private Layout _getInfoDisplayObjectProviderLayout(
        InfoDisplayObjectProvider infoDisplayObjectProvider) {

        LayoutPageTemplateEntry layoutPageTemplateEntry =
            _layoutPageTemplateEntryService.fetchDefaultLayoutPageTemplateEntry(
                infoDisplayObjectProvider.getGroupId(),
                infoDisplayObjectProvider.getClassNameId(),
                infoDisplayObjectProvider.getClassTypeId());

        if (layoutPageTemplateEntry != null) {
            return _layoutLocalService.fetchLayout(
                layoutPageTemplateEntry.getPlid());
        }

        return null;
    }

    @Reference
    private InfoDisplayContributorTracker _infoDisplayContributorTracker;

    @Reference
    private LayoutLocalService _layoutLocalService;

    @Reference
    private LayoutPageTemplateEntryService _layoutPageTemplateEntryService;

    @Reference
    private Portal _portal;
    ```

    Notice you're finding the `InfoDisplayObjectProvider` corresponding to the
    current user. This serves as the representation/descriptor of the mapped
    object.

    | **Note:** This `FriendlyURLResolver` implementation uses the default
    | display page template for the User model.

    When this implementation is deployed, you'll receive an empty page when
    calling the URL `[host]/web/guest/user/[screenName]`. You must return the
    values from the users that are mapped to the display page. You'll do this
    next.

5.  Implement the `getInfoDisplayFieldsValue` method in the previously created
    `*InfoDisplayContributor` class.

    ```java
    @Override
    public Map<String, Object> getInfoDisplayFieldsValues(
            User user, Locale locale)
        throws PortalException {

        Map<String, Object> infoDisplayFieldsValues = new HashMap<>();

        List<InfoDisplayContributorField> infoDisplayContributorFields =
            _infoDisplayContributorFieldTracker.getInfoDisplayContributorFields(
                getClassName());

        for (InfoDisplayContributorField infoDisplayContributorField :
            infoDisplayContributorFields) {

            Object fieldValue = infoDisplayContributorField.getValue(
                user, locale);

            infoDisplayFieldsValues.putIfAbsent(
                infoDisplayContributorField.getKey(), fieldValue);
        }

        return infoDisplayFieldsValues;
    }
    ```

Great! Now you have a friendly URL that maps to your display page template's
custom content type.
