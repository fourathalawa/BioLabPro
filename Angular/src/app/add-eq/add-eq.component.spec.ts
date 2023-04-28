import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEqComponent } from './add-eq.component';

describe('AddEqComponent', () => {
  let component: AddEqComponent;
  let fixture: ComponentFixture<AddEqComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEqComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEqComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
