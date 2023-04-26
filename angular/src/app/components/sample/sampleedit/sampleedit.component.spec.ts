import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SampleeditComponent } from './sampleedit.component';

describe('SampleeditComponent', () => {
  let component: SampleeditComponent;
  let fixture: ComponentFixture<SampleeditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SampleeditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SampleeditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
