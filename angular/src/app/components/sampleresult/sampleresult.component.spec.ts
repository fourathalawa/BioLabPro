import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SampleresultComponent } from './sampleresult.component';

describe('SampleresultComponent', () => {
  let component: SampleresultComponent;
  let fixture: ComponentFixture<SampleresultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SampleresultComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SampleresultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
