# Providing Friendly URLs for a Custom Content Type

To provide a friendly URL for your custom content type, you must implement a
friendly URL resolver to retrieve the desired information. For the User content
type example, you'll want to retrieve profiles by a user's screen name.

We'll implement the getActualURL and
getLayoutFriendlyURLComposite methods of FriendlyURLResolver interface because
they are the methods that matter. We'll also use only the default display page
template for the User model.

But first we have to implement the method getInfoDisplayObjectProvider of the
UserInfoDisplayContributor


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



Now the friendly URL resolver

@Component(service = FriendlyURLResolver.class)
public class UserDisplayPageFriendlyURLResolver implements FriendlyURLResolver {

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

}



With this, if we deploy, and call the following URL, we'll get an empty page:
[host]/web/guest/user/[screenName]

Now we only have to return the values from the users that are mapped to the
display page. For that we implement the method getInfoDisplayFieldsValues from
the UserInfoDisplayContributor class.


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


