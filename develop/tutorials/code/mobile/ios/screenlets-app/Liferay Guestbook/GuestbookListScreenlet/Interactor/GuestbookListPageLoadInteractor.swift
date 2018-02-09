import UIKit
import LiferayScreens

class GuestbookListPageLoadInteractor: BaseListPageLoadInteractor {
    
    private let groupId: Int64
    
    init(screenlet: BaseListScreenlet,
         page: Int,
         computeRowCount: Bool,
         groupId: Int64) {
        
        self.groupId = (groupId != 0) ? groupId : LiferayServerContext.groupId
        
        super.init(screenlet: screenlet, page: page, computeRowCount: computeRowCount)
    }
    
    public override func createListPageConnector() -> PaginationLiferayConnector {
        let screenlet = self.screenlet as! BaseListScreenlet
        
        return GuestbookListPageLiferayConnector(
            startRow: screenlet.firstRowForPage(self.page),
            endRow: screenlet.firstRowForPage(self.page + 1),
            computeRowCount: self.computeRowCount,
            groupId: groupId)
    }
    
    override public func convertResult(_ serverResult: [String:AnyObject]) -> AnyObject {
        
        return GuestbookModel(attributes: serverResult)
    }
    
    override public func cacheKey(_ op: PaginationLiferayConnector) -> String {
        return "\(groupId)"
    }

}
