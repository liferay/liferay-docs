import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewGuestbookComponent } from './view-guestbook.component';

describe('ViewGuestbookComponent', () => {
  let component: ViewGuestbookComponent;
  let fixture: ComponentFixture<ViewGuestbookComponent>;//changed type from viewComponent to ViewGuestbookComponent for portal migration

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewGuestbookComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewGuestbookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
