import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BooListComponent } from './boo-list.component';

describe('BooListComponent', () => {
  let component: BooListComponent;
  let fixture: ComponentFixture<BooListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BooListComponent]
    });
    fixture = TestBed.createComponent(BooListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
