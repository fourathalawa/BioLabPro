import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingbackComponent } from './trainingback.component';

describe('TrainingbackComponent', () => {
  let component: TrainingbackComponent;
  let fixture: ComponentFixture<TrainingbackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrainingbackComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainingbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
