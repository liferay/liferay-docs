import UIKit
import LiferayScreens

@objc public protocol EntryListScreenletDelegate : BaseScreenletDelegate {
    
    @objc optional func screenlet(screenlet: EntryListScreenlet,
                                  onEntryListResponse entries: [EntryModel])
    
    @objc optional func screenlet(screenlet: EntryListScreenlet,
                                  onEntryListError error: NSError)
    
    @objc optional func screenlet(screenlet: EntryListScreenlet,
                                  onEntrySelected entry: EntryModel)
    
}

public class EntryListScreenlet: BaseListScreenlet {
    
    @IBInspectable public var groupId: Int64 = 0
    @IBInspectable public var guestbookId: Int64 = 0
    
    override public func createPageLoadInteractor(
        page: Int,
        computeRowCount: Bool) -> BaseListPageLoadInteractor {
        
        return EntryListPageLoadInteractor(screenlet: self,
                                           page: page,
                                           computeRowCount: computeRowCount,
                                           groupId: self.groupId,
                                           guestbookId: self.guestbookId)
    }
    
    public var entryListDelegate: EntryListScreenletDelegate? {
        return delegate as? EntryListScreenletDelegate
    }
    
    override public func onLoadPageResult(page: Int, rows: [AnyObject], rowCount: Int) {
        super.onLoadPageResult(page: page, rows: rows, rowCount: rowCount)
        
        entryListDelegate?.screenlet?(screenlet: self, onEntryListResponse: rows as! [EntryModel])
    }
    
    override public func onLoadPageError(page: Int, error: NSError) {
        super.onLoadPageError(page: page, error: error)
        
        entryListDelegate?.screenlet?(screenlet: self, onEntryListError: error)
    }
    
    override public func onSelectedRow(_ row: AnyObject) {
        entryListDelegate?.screenlet?(screenlet: self, onEntrySelected: row as! EntryModel)
    }

}
