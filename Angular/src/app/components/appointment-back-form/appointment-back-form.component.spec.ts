import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentBackFormComponent } from './appointment-back-form.component';

describe('AppointmentBackFormComponent', () => {
  let component: AppointmentBackFormComponent;
  let fixture: ComponentFixture<AppointmentBackFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppointmentBackFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppointmentBackFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
