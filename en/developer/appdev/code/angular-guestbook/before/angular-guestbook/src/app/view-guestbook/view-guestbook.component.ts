import { Component, OnInit } from '@angular/core';
import { GuestbookEntry} from '../guestbook-entry';
import { EntryService } from '../entry.service';

@Component({
  selector: 'app-view-guestbook',
  templateUrl: './view-guestbook.component.html',
  styleUrls: ['./view-guestbook.component.css']
})
export class ViewGuestbookComponent implements OnInit {

  selectedEntry: GuestbookEntry;
  onSelect(entry: GuestbookEntry): void {
    this.selectedEntry = entry;
  }

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
