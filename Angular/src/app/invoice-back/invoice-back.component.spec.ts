import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoiceBackComponent } from './invoice-back.component';

describe('InvoiceBackComponent', () => {
  let component: InvoiceBackComponent;
  let fixture: ComponentFixture<InvoiceBackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InvoiceBackComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InvoiceBackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
