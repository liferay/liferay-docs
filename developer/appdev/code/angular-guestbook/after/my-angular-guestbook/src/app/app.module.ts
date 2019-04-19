import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { APP_BASE_HREF } from '@angular/common'; // newly added for migration to portal

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddEntryComponent } from './add-entry/add-entry.component';
import { ViewGuestbookComponent } from './view-guestbook/view-guestbook.component';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here

//import { HttpClientModule }    from '@angular/common/http'; //disabled for portal migration
//import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api'; //disabled for portal migration
//import { InMemoryDataService }  from './in-memory-data.service'; //disabled for portal migration

@NgModule({
  declarations: [
    AppComponent,
    routingComponents, //added for portal migration
    AddEntryComponent,
    ViewGuestbookComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
    //HttpClientModule //disabled for portal migration

// The HttpClientInMemoryWebApiModule module intercepts HTTP requests
// and returns simulated server responses.
// Remove it when a real server is ready to receive requests.
/*HttpClientInMemoryWebApiModule.forRoot(
  InMemoryDataService, { dataEncapsulation: false }
)*/ //disabled for portal migration
  ],
  entryComponents: [AppComponent], //added for portal migration
  providers: [{provide: APP_BASE_HREF, useValue: '/'}],//newly added for portal migration
  bootstrap: []// removed AppComponent for portal migration
})
export class AppModule {
	// Avoid bootstraping any component statically because we need to attach to
	// the portlet's DOM, which is different for each portlet instance and,
	// thus, cannot be determined until the page is rendered (during runtime).
	ngDoBootstrap() {}
}