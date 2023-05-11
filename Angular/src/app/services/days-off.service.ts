import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { DaysOff } from '../models/days-off';
import { MatDialog } from '@angular/material/dialog';
import { MyDialogComponentComponent } from '../components/my-dialog-component/my-dialog-component.component';
import { Router } from '@angular/router';



  
@Injectable({
  providedIn: 'root'
})
export class DaysOffService {

  daysOffUrl="http://localhost:8089/BioLabPro/DaysOff/all-DaysOff";
  daysOffEdit="http://localhost:8089/BioLabPro/DaysOff/getDaysOff";
  daysOffUpdate="http://localhost:8089/BioLabPro/DaysOff/updateDaysOff";
  private baseUrl = 'http://localhost:8080/api/daysoffbyid';

  
  httpOptions = { 
    headers: new HttpHeaders({ 
    'Content-Type': 'application/json' 
    }) 
    } 
    


  constructor(private _http:HttpClient, private dialog: MatDialog,private router:Router) { }
  getDaysOff() : Observable<any> {
    return this._http.get<string[]>(this.daysOffUrl);
  }

  deleteDaysOffById(id:number){
      return this._http.delete("http://localhost:8089/BioLabPro/DaysOff/deleteDaysOff/"+id);
  }

    getDaysById(id: number): Observable<DaysOff> { 
      return this._http.get<DaysOff>(this.daysOffEdit +'/'+ id); }
      
      updateDaysOff(id: number, daysOff: DaysOff): Observable<DaysOff> {
        return this._http.put<DaysOff>(this.daysOffUpdate+'/'+ id, daysOff,this.httpOptions)
          .pipe(
            tap((updatedDaysOff: DaysOff) => {
              const dialogRef = this.dialog.open(MyDialogComponentComponent, {
                data: {message: 'Days off updated successfully!'}
              });
              dialogRef.afterClosed().subscribe(() => {
                this.router.navigate([this.daysOffUrl]);
              });
            })
          );
      }
      addDaysOff(daysOff:DaysOff){
       return this._http.post<DaysOff>("http://localhost:8089/BioLabPro/DaysOff/RequestDaysOff",daysOff);

      }
        archiveEntity(id: number) {
          return this._http.put("http://localhost:8089/BioLabPro/DaysOff/archive/"+ id,[]);
        }
        
        genererPdf(daysOff:DaysOff) {
          console.log("test")
          this._http.post("http://localhost:8089/BioLabPro/DaysOff/test/", daysOff, { responseType: 'blob' })
            .subscribe((response: Blob) => {
              // Create a new blob object that contains the PDF file
              const file = new Blob([response], { type: 'application/pdf' });
        
              // Generate a URL for the PDF file
              const fileURL = URL.createObjectURL(file);
        
              // Open the PDF file in a new tab
              window.open(fileURL);
            }, (error: any) => {
              console.log(error);
            });
        }
      public getDaysOffByUserId(userId: string): Observable<DaysOff[]> {
  return this._http.get<DaysOff[]>(this.baseUrl + '/daysoffbyid/' + userId);
}
        
}

