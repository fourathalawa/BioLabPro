import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DaysOffEditComponent } from './days-off-edit.component';

describe('DaysOffEditComponent', () => {
  let component: DaysOffEditComponent;
  let fixture: ComponentFixture<DaysOffEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DaysOffEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DaysOffEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
