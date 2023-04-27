import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListDaysOffComponent } from './list-days-off.component';

describe('ListDaysOffComponent', () => {
  let component: ListDaysOffComponent;
  let fixture: ComponentFixture<ListDaysOffComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListDaysOffComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListDaysOffComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
