import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoiceFrontComponent } from './invoice-front.component';

describe('InvoiceFrontComponent', () => {
  let component: InvoiceFrontComponent;
  let fixture: ComponentFixture<InvoiceFrontComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InvoiceFrontComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InvoiceFrontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
