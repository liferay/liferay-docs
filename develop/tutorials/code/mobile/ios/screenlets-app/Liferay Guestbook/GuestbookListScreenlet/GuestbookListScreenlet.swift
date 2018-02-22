import UIKit
import LiferayScreens

@objc public protocol GuestbookListScreenletDelegate : BaseScreenletDelegate {
    
    @objc optional func screenlet(screenlet: GuestbookListScreenlet,
                                  onGuestbookListResponse guestbooks: [GuestbookModel])
    
    @objc optional func screenlet(screenlet: GuestbookListScreenlet,
                                  onGuestbookListError error: NSError)
    
    @objc optional func screenlet(screenlet: GuestbookListScreenlet,
                                  onGuestbookSelected guestbook: GuestbookModel)
    
}

public class GuestbookListScreenlet: BaseListScreenlet {
    
    @IBInspectable public var groupId: Int64 = 0
    
    override public func createPageLoadInteractor(
        page: Int,
        computeRowCount: Bool) -> BaseListPageLoadInteractor {
        
        return GuestbookListPageLoadInteractor(screenlet: self,
                                               page: page,
                                               computeRowCount: computeRowCount,
                                               groupId: self.groupId)
    }
    
    public var guestbookListDelegate: GuestbookListScreenletDelegate? {
        return delegate as? GuestbookListScreenletDelegate
    }
    
    override public func onLoadPageResult(page: Int, rows: [AnyObject], rowCount: Int) {
        super.onLoadPageResult(page: page, rows: rows, rowCount: rowCount)
        
        guestbookListDelegate?.screenlet?(screenlet: self, onGuestbookListResponse: rows as! [GuestbookModel])
    }
    
    override public func onLoadPageError(page: Int, error: NSError) {
        super.onLoadPageError(page: page, error: error)
        
        guestbookListDelegate?.screenlet?(screenlet: self, onGuestbookListError: error)
    }
    
    override public func onSelectedRow(_ row: AnyObject) {
        guestbookListDelegate?.screenlet?(screenlet: self, onGuestbookSelected: row as! GuestbookModel)
    }

}
