import UIKit
import LiferayScreens

class GuestbooksViewController: UIViewController, GuestbookListScreenletDelegate {

    @IBOutlet weak var screenlet: GuestbookListScreenlet!

    override func viewDidLoad() {
        super.viewDidLoad()

        self.screenlet.delegate = self
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func screenlet(screenlet: GuestbookListScreenlet, onGuestbookListResponse guestbooks: [GuestbookModel]) {
        
    }
    
    func screenlet(screenlet: GuestbookListScreenlet, onGuestbookListError error: NSError) {
        print("Failed to retrieve guestbooks: \(error.localizedDescription)")
    }
    
    func screenlet(screenlet: GuestbookListScreenlet, onGuestbookSelected guestbook: GuestbookModel) {
        
        performSegue(withIdentifier: "entriessegue", sender: guestbook)
    }
    

    // MARK: - Navigation

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        
        guard segue.identifier ==  "entriessegue",
            let entriesViewController = segue.destination as? EntriesViewController,
            let selectedGuestbook = sender as? GuestbookModel
            else {return}
        
        entriesViewController.selectedGuestbook = selectedGuestbook
    }

}
