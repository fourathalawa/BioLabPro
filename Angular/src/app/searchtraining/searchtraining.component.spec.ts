import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchtrainingComponent } from './searchtraining.component';

describe('SearchtrainingComponent', () => {
  let component: SearchtrainingComponent;
  let fixture: ComponentFixture<SearchtrainingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchtrainingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchtrainingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
