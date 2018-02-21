import UIKit
import LiferayScreens

class GuestbookListPageLiferayConnector: PaginationLiferayConnector {
    
    public let groupId: Int64

    public init(startRow: Int, endRow: Int, computeRowCount: Bool, groupId: Int64) {
        self.groupId = groupId
        
        super.init(startRow: startRow, endRow: endRow, computeRowCount: computeRowCount)
    }
    
    public override func doAddPageRowsServiceCall(session: LRBatchSession, startRow: Int, endRow: Int,
                                                  obc: LRJSONObjectWrapper?) {
        let service = LRGuestbookService_v7(session: session)
        
        do {
            try service!.getGuestbooksWithGroupId(groupId, start: Int32(startRow), end: Int32(endRow))
        }
        catch {
            // ignore error: the service method returns nil because
            // the request is sent later, in batch
        }
        
    }
    
    override public func doAddRowCountServiceCall(session: LRBatchSession) {
        let service = LRGuestbookService_v7(session: session)
        
        do {
            try service!.getGuestbooksCount(withGroupId: groupId)
        }
        catch {
            // ignore error: the service method returns nil because
            // the request is sent later, in batch
        }
    }

}
