import UIKit
import LiferayScreens

class EntryListPageLiferayConnector: PaginationLiferayConnector {
    
    public let groupId: Int64
    public let guestbookId: Int64
    
    //MARK: Initializer
    
    public init(startRow: Int, endRow: Int, computeRowCount: Bool, groupId: Int64,
                guestbookId: Int64) {
        
        self.groupId = groupId
        self.guestbookId = guestbookId
        
        super.init(startRow: startRow, endRow: endRow, computeRowCount: computeRowCount)
    }
    
    //MARK: PaginationLiferayConnector
    
    public override func doAddPageRowsServiceCall(session: LRBatchSession, startRow: Int, endRow: Int,
                                                  obc: LRJSONObjectWrapper?) {
        
        let service = LREntryService_v7(session: session)
        
        do {
            try service!.getEntriesWithGroupId(groupId, guestbookId: guestbookId,
                                               start: Int32(startRow), end: Int32(endRow))
        }
        catch {
            // the service method returns nil because the request is sent later, in batch
        }
        
    }
    
    override public func doAddRowCountServiceCall(session: LRBatchSession) {
        let service = LREntryService_v7(session: session)
        
        do {
            try service!.getEntriesCount(withGroupId: groupId, guestbookId: guestbookId)
        }
        catch {
            // the service method returns nil because the request is sent later, in batch
        }
    }
    
}
