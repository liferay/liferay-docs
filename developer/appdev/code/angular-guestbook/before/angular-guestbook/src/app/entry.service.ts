import { Injectable } from '@angular/core';
import { GuestbookEntry } from './guestbook-entry';
//import { ENTRIES } from './mock-guestbook-entries';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class EntryService {

private entriesUrl = 'api/entries';  // URL to web api

  constructor(
  private http: HttpClient) { }
  
  /** GET heroes from the server */
  getEntries (): Observable<GuestbookEntry[]> {
    return this.http.get<GuestbookEntry[]>(this.entriesUrl)
    .pipe(
      tap(_ => this.log('fetched entries')),
      catchError(this.handleError('getEntries', []))
    );
  }

  /** POST: add a new hero to the server */
  addEntry (entry: GuestbookEntry): Observable<GuestbookEntry> {
    //console.log(`added entry w/ id=${entry.id}, name=${entry.name} and message=${entry.message}`);
    return this.http.post<GuestbookEntry>(this.entriesUrl, entry, httpOptions).pipe(
      tap((entry: GuestbookEntry) => this.log(`added entry w/ id=${entry.id}, name=${entry.name} and message=${entry.message}`)),
      catchError(this.handleError<GuestbookEntry>('addEntry'))
    )
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    //this.messageService.add(`HeroService: ${message}`);
    console.log(`EntryService: ${message}`);
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
  
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
  
      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);
  
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
