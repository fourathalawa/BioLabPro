import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { trigger, state, style, animate, transition, AnimationTriggerMetadata } from '@angular/animations';

@Component({
  selector: 'app-my-dialog-component',
  templateUrl: './my-dialog-component.component.html',
  styleUrls: ['./my-dialog-component.component.css'],
  animations: [
    trigger('dialogContainer', [
      state('start', style({ opacity: 1 })),
      transition('void => start', [style({ opacity: 0 }), animate('100ms ease-in-out')])
    ])
  ]
})
export class MyDialogComponentComponent implements OnInit {
  constructor(
    public dialogRef: MatDialogRef<MyDialogComponentComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  

  ngOnInit(): void {
  }

}
