import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatetrainingComponent } from './updatetraining.component';

describe('UpdatetrainingComponent', () => {
  let component: UpdatetrainingComponent;
  let fixture: ComponentFixture<UpdatetrainingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdatetrainingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdatetrainingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
