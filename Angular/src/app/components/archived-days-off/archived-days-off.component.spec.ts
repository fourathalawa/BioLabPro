import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArchivedDaysOffComponent } from './archived-days-off.component';

describe('ArchivedDaysOffComponent', () => {
  let component: ArchivedDaysOffComponent;
  let fixture: ComponentFixture<ArchivedDaysOffComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArchivedDaysOffComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ArchivedDaysOffComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
