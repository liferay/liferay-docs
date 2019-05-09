import { InMemoryDbService } from 'angular-in-memory-web-api';
import { GuestbookEntry } from './guestbook-entry';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const entries = [
      { id: 1, name: 'Joe Bloggs', message: 'Had an awesome Time!' },
      { id: 2, name: 'Jane Bloggs', message: 'Great event!' },
      { id: 3, name: 'Bill Bloggs', message: 'Had a good time.' },
      { id: 4, name: 'Bob Nosester', message: 'Great atmosphere!' },
      { id: 5, name: 'Martha Nosester', message: 'Lovely aromas.' }
    ];
    return {entries};
  }

  // Overrides the genId method to ensure that a hero always has an id.
  // If the heroes array is empty,
  // the method below returns the initial number (11).
  // if the heroes array is not empty, the method below returns the highest
  // hero id + 1.
  genId(entries: GuestbookEntry[]): number {
    return entries.length > 0 ? Math.max(...entries.map(entry => entry.id)) + 1 : 1;
  }
}