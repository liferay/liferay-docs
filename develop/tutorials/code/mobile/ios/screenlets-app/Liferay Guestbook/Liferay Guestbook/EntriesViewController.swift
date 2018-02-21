import UIKit
import LiferayScreens

class EntriesViewController: UIViewController, EntryListScreenletDelegate {
    
    @IBOutlet weak var screenlet: EntryListScreenlet!

    var selectedGuestbook: GuestbookModel?

    override func viewDidLoad() {
        super.viewDidLoad()

        self.screenlet.delegate = self
        self.screenlet.guestbookId = selectedGuestbook!.guestbookId
        self.navigationItem.title = selectedGuestbook!.name
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func screenlet(screenlet: EntryListScreenlet, onEntryListResponse entries: [EntryModel]) {
        
    }
    
    func screenlet(screenlet: EntryListScreenlet, onEntryListError error: NSError) {
        print("Failed to retrieve guestbook entries: \(error.localizedDescription)")
    }
    
    func screenlet(screenlet: EntryListScreenlet, onEntrySelected entry: EntryModel) {
        
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
