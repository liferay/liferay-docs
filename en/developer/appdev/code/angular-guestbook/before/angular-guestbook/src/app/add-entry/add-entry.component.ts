import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { GuestbookEntry} from '../guestbook-entry';
import { EntryService } from '../entry.service';

@Component({
  selector: 'app-add-entry',
  templateUrl: './add-entry.component.html',
  styleUrls: ['./add-entry.component.css']
})
export class AddEntryComponent implements OnInit {

  entries: GuestbookEntry[];

  entriesLength: number;

  constructor(
    private entryService: EntryService,
    private route: ActivatedRoute,
    private location: Location
  ) { }

  ngOnInit() {
    this.getEntries();
    this.getEntriesLength();
  }

  getEntries(): void {
    this.entryService.getEntries()
        .subscribe(entries => this.entries = entries);
  }

  getEntriesLength(): void {
    this.entryService.getEntries()
        .subscribe(entries => this.entriesLength = entries.length + 1);
  }

  goBack(): void {
    this.location.back();
  }
   
   add(name: string, message: string): void { 
     var id: number;
     id = this.entriesLength;
     name = name.trim();
     message = message.trim();
     if (!name || !message) { 
       alert("You must fill in all fields."); 
     }
     else {
       this.entryService.addEntry({ id, name, message } as GuestbookEntry)
       .subscribe(entry => {
         this.entries.push(entry);
       });
       //this.entries.push({id: id, name: name, message: message});
       this.goBack();
     }
   }

}
