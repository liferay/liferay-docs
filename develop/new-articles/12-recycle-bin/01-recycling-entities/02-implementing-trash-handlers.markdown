# Implement a Trash Handler for the Guestbook

Like other Liferay frameworks--such as assets and
indexing frameworks which you just enabled--you must implement a handler class 
for Recycle Bin. A Recycle Bin handler class manages moving entries to the 
Recycle Bin, viewing them in the Recycle Bin, restoring them, and permanently 
deleting them. You must implement the [`TrashHandler`](http://docs.liferay.com/portal/7.0/javadocs/com/liferay/portal/kernel/trash/TrashHandler.html)
interface for each trash-enabled entity. As a convenience, Liferay provides the
extensible abstract class [`BaseTrashHandler`](http://docs.liferay.com/portal/7.0/javadocs/com/liferay/portal/kernel/trash/BaseTrashHandler.html).

First create the necessary files and classes:

1. Right click on the `guestbook-service` module and select *New* &rarr; 
    *Package*
2. Name the package `com.liferay.docs.guestbook.trash` and click 
    *Finish*
3. Now right click on the new package and click *New* &rarr; *Class*.
4. Name the first class `GuestbookBaseTrashHandler`.
5. Click *Browse* next to the *Supeclass* field and enter `BaseTrashHandler`.
6. Click *OK* and then *Finish*.
7. Create a second class in the same package named `GuestbookTrashHandler`.

GuestbbokTrashHandler:

	package com.liferay.docs.guestbook.trash;

	import javax.portlet.PortletRequest;

	import org.osgi.service.component.annotations.Component;
	import org.osgi.service.component.annotations.Reference;

	import com.liferay.docs.guestbook.model.Guestbook;
	import com.liferay.docs.guestbook.service.GuestbookLocalService;
	import com.liferay.docs.guestbook.service.permission.GuestbookModelPermission;
	import com.liferay.docs.guestbook.service.persistence.GuestbookUtil;
	import com.liferay.portal.kernel.exception.PortalException;
	import com.liferay.portal.kernel.model.ContainerModel;
	import com.liferay.portal.kernel.security.permission.ActionKeys;
	import com.liferay.portal.kernel.security.permission.PermissionChecker;
	import com.liferay.portal.kernel.service.ServiceContext;
	import com.liferay.portal.kernel.trash.TrashActionKeys;
	import com.liferay.portal.kernel.trash.TrashHandler;
	import com.liferay.portal.kernel.trash.TrashRenderer;
	import com.liferay.trash.kernel.model.TrashEntry;

	@Component(
			property = {"model.class.name=com.liferay.bookmarks.model.Guestbook"},
			service = TrashHandler.class
	)
	public class GuestbookTrashHandler extends GuestbookBaseTrashHandler {

				@Override
				public void deleteTrashEntry(long classPK) throws PortalException {
					_GuestbookLocalService.deleteGuestbook(classPK, false);
				}

				@Override
				public String getClassName() {
					return Guestbook.class.getName();
				}

				@Override
				public String getDeleteMessage() {
					return "found-in-deleted-folder-x";
				}

				@Override
				public ContainerModel getParentContainerModel(long classPK)
					throws PortalException {

					Guestbook folder = getGuestbook(classPK);

					long parentFolderId = folder.getParentFolderId();

					if (parentFolderId <= 0) {
						return null;
					}

					return getContainerModel(parentFolderId);
				}

				@Override
				public String getRestoreContainedModelLink(
						PortletRequest portletRequest, long classPK)
					throws PortalException {

					Guestbook folder = getGuestbook(classPK);

					return GuestbookUtil.getControlPanelLink(
						portletRequest, folder.getFolderId());
				}

				@Override
				public String getRestoreContainerModelLink(
						PortletRequest portletRequest, long classPK)
					throws PortalException {

					Guestbook folder = getGuestbook(classPK);

					return GuestbookUtil.getControlPanelLink(
						portletRequest, folder.getParentFolderId());
				}

				@Override
				public String getRestoreMessage(PortletRequest portletRequest, long classPK)
					throws PortalException {

					Guestbook folder = getGuestbook(classPK);

					return GuestbookUtil.getAbsolutePath(
						portletRequest, folder.getParentFolderId());
				}

				@Override
				public TrashEntry getTrashEntry(long classPK) throws PortalException {
					Guestbook folder = getGuestbook(classPK);

					return folder.getTrashEntry();
				}

				@Override
				public TrashRenderer getTrashRenderer(long classPK) throws PortalException {
					Guestbook folder = getGuestbook(classPK);

					return new GuestbookAssetRenderer(folder);
				}

				@Override
				public boolean hasTrashPermission(
						PermissionChecker permissionChecker, long groupId, long classPK,
						String trashActionId)
					throws PortalException {

					if (trashActionId.equals(TrashActionKeys.MOVE)) {
						return GuestbookPermissionChecker.contains(
							permissionChecker, groupId, classPK, ActionKeys.ADD_FOLDER);
					}

					return super.hasTrashPermission(
						permissionChecker, groupId, classPK, trashActionId);
				}

				@Override
				public boolean isContainerModel() {
					return true;
				}

				@Override
				public boolean isInTrash(long classPK) throws PortalException {
					Guestbook folder = getGuestbook(classPK);

					return folder.isInTrash();
				}

				@Override
				public boolean isInTrashContainer(long classPK) throws PortalException {
					Guestbook folder = getGuestbook(classPK);

					return folder.isInTrashContainer();
				}

				@Override
				public boolean isRestorable(long classPK) throws PortalException {
					Guestbook folder = getGuestbook(classPK);

					if ((folder.getParentFolderId() > 0) &&
						(_GuestbookLocalService.fetchGuestbook(
							folder.getParentFolderId()) == null)) {

						return false;
					}

					return !folder.isInTrashContainer();
				}

				@Override
				public void moveEntry(
						long userId, long classPK, long containerModelId,
						ServiceContext serviceContext)
					throws PortalException {

					_GuestbookLocalService.moveFolder(classPK, containerModelId);
				}

				@Override
				public void moveTrashEntry(
						long userId, long classPK, long containerId,
						ServiceContext serviceContext)
					throws PortalException {

					_GuestbookLocalService.moveFolderFromTrash(
						userId, classPK, containerId);
				}

				@Override
				public void restoreTrashEntry(long userId, long classPK)
					throws PortalException {

					_GuestbookLocalService.restoreFolderFromTrash(userId, classPK);
				}

				protected Guestbook getGuestbook(long classPK)
					throws PortalException {

					return _GuestbookLocalService.getFolder(classPK);
				}

				@Override
				protected long getGroupId(long classPK) throws PortalException {
					Guestbook folder = getGuestbook(classPK);

					return folder.getGroupId();
				}

				@Override
				protected boolean hasPermission(
						PermissionChecker permissionChecker, long classPK, String actionId)
					throws PortalException {

					Guestbook folder = getGuestbook(classPK);

					return GuestbookModelPermission.contains(
						permissionChecker, folder, actionId);
				}

				@Reference(unbind = "-")
				protected void setGuestbookLocalService(
					GuestbookLocalService bookmarksFolderLocalService) {

					_GuestbookLocalService = bookmarksFolderLocalService;
				}

				private GuestbookLocalService _GuestbookLocalService;

			}


After you've implemented trash handlers for your trash-enabled entities, 
specify the handlers in your app's `liferay-portlet.xml` file. For example, the
Jukebox's Song portlet specifies the song trash handler like this:

    <trash-handler>org.liferay.jukebox.trash.SongTrashHandler</trash-handler>

Great! You have trash handlers ready to manage your trash entries, but you still
need a way to get the entries to the Recycle Bin. You must create a service
method for moving them there.
