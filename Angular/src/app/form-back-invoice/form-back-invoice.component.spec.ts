import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormBackInvoiceComponent } from './form-back-invoice.component';

describe('FormBackInvoiceComponent', () => {
  let component: FormBackInvoiceComponent;
  let fixture: ComponentFixture<FormBackInvoiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormBackInvoiceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FormBackInvoiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
