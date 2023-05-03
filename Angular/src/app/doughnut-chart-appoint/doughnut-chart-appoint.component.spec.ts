import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoughnutChartAppointComponent } from './doughnut-chart-appoint.component';

describe('DoughnutChartAppointComponent', () => {
  let component: DoughnutChartAppointComponent;
  let fixture: ComponentFixture<DoughnutChartAppointComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DoughnutChartAppointComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DoughnutChartAppointComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
