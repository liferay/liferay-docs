import { Component, OnInit } from '@angular/core';
import { GuestbookEntry} from '../guestbook-entry';
import { EntryService } from '../entry.service';

@Component({
  selector: 'app-view-guestbook',
  templateUrl: '/o/my-angular-guestbook/app/view-guestbook.component.html'
  //styleUrls: ['/o/my-angular-guestbook/app//view-guestbook/view-guestbook.component.css']
})
export class ViewGuestbookComponent implements OnInit {

  entries: GuestbookEntry[];

  constructor(private entryService: EntryService) { }

  ngOnInit() {
    this.getEntries();
  }

  getEntries(): void {
    this.entryService.getEntries()
        .subscribe(entries => this.entries = entries);
  }

}
