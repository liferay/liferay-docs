import UIKit
import LiferayScreens

class EntryListView_default: BaseListTableView {
    
    override public func doFillLoadedCell(row: Int, cell: UITableViewCell, object: AnyObject) {
        let entry = object as! EntryModel
        
        cell.textLabel?.text = entry.message
        cell.detailTextLabel?.text = entry.name
    }
    
    override open func doCreateCell(_ cellId: String) -> UITableViewCell {
        return UITableViewCell(style: .subtitle, reuseIdentifier: cellId)
    }
    
    override public func doFillInProgressCell(row: Int, cell: UITableViewCell) {
        cell.textLabel?.text = "Loading..."
    }
    
}
