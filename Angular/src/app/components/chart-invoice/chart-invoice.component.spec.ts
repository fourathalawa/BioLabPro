import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChartInvoiceComponent } from './chart-invoice.component';

describe('ChartInvoiceComponent', () => {
  let component: ChartInvoiceComponent;
  let fixture: ComponentFixture<ChartInvoiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChartInvoiceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChartInvoiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
