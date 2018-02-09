import UIKit

@objc public class GuestbookModel: NSObject {
    
    public let attributes: [String:AnyObject]
    
    public var guestbookId: Int64 {
        return attributes["guestbookId"]?.int64Value ?? 0
    }
    
    public var groupId: Int64 {
        return attributes["groupId"]?.int64Value ?? 0
    }
    
    public var companyId: Int64 {
        return attributes["companyId"]?.int64Value ?? 0
    }
    
    public var userId: Int64 {
        return attributes["userId"]?.int64Value ?? 0
    }
    
    public var userName: String {
        return attributes["userName"] as? String ?? ""
    }
    
    public var createDate: Int64 {
        return attributes["createDate"]?.int64Value ?? 0
    }
    
    public var modifiedDate: Int64 {
        return attributes["modifiedDate"]?.int64Value ?? 0
    }
    
    public var name: String {
        return attributes["name"] as? String ?? ""
    }
    
    //MARK: Initializer
    
    public init(attributes: [String:AnyObject]) {
        self.attributes = attributes
    }
    
}
