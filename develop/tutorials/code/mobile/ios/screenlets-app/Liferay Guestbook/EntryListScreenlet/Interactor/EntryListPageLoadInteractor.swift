import UIKit
import LiferayScreens

class EntryListPageLoadInteractor: BaseListPageLoadInteractor {
    
    private let groupId: Int64
    private let guestbookId: Int64
    
    init(screenlet: BaseListScreenlet,
         page: Int,
         computeRowCount: Bool,
         groupId: Int64,
         guestbookId: Int64) {
        
        self.groupId = (groupId != 0) ? groupId : LiferayServerContext.groupId
        self.guestbookId = guestbookId
        
        super.init(screenlet: screenlet, page: page, computeRowCount: computeRowCount)
    }
    
    public override func createListPageConnector() -> PaginationLiferayConnector {
        let screenlet = self.screenlet as! BaseListScreenlet
        
        return EntryListPageLiferayConnector(
            startRow: screenlet.firstRowForPage(self.page),
            endRow: screenlet.firstRowForPage(self.page + 1),
            computeRowCount: self.computeRowCount,
            groupId: groupId,
            guestbookId: guestbookId)
    }
    
    override public func convertResult(_ serverResult: [String:AnyObject]) -> AnyObject {
        
        return EntryModel(attributes: serverResult)
    }
    
    override public func cacheKey(_ op: PaginationLiferayConnector) -> String {
        return "\(groupId)-\(guestbookId)"
    }
    
}
