import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Chart from 'chart.js/auto';
import { AppointementService } from 'src/app/services/appointement.service';
@Component({
  selector: 'app-doughnut-chart-appoint',
  templateUrl: './doughnut-chart-appoint.component.html',
  styleUrls: ['./doughnut-chart-appoint.component.css']
})
export class DoughnutChartAppointComponent implements OnInit {
  public chart: any;
  constructor(private ac:ActivatedRoute,private ps:AppointementService , private router:Router) { }
  
  public NotReached:number=0;
  public numvalidated:any;
  public numnotvalidated:any;
  public totalnumber: any;

  ngOnInit(): void {
  
    
    
    this.ps.allappoints().subscribe(
      (numberOfAppointments: number) => {
        console.log(`Response received: ${numberOfAppointments}`);
          this.totalnumber = numberOfAppointments;
       
      },
      (error: any) => {
        console.error(error);
      }
    );
    
  
    //validated
    this.ps.allvalidated().subscribe(
      (numberOfAppointments: number) => {
        //console.log(`Response received: ${numberOfAppointments}`);
        this.numvalidated = numberOfAppointments;
      
      },
      (error: any) => {
        console.error(error);
      }
    );
  
     //notvalidated
   
  
    //notreached
    this.ps.allnotyetreached().subscribe(
      (numberOfAppointments: number) => {
        //console.log(`Response received: ${numberOfAppointments}`);
        this.NotReached = numberOfAppointments;
       
      },
      (error: any) => {
        console.error(error);
      }
    );

    this.ps.allnotvalidated().subscribe(
      (numberOfAppointments: number) => {
        console.log(`Response received: ${numberOfAppointments}`);
        this.numnotvalidated = numberOfAppointments;
        this.createChart( this.numnotvalidated,this.numvalidated, this.NotReached)
      },
      (error: any) => {
        console.error(error);
      }
    );

    console.log(`okok ${this.numnotvalidated}`);
    console.log(`Response received: ${this.NotReached}`);
    console.log(`Response received: ${this.numvalidated}`);
   
  }
  
  private createChart(n:number,v:number,r:number): void {
    

    this.chart = new Chart("MyChart", {
      type: 'pie', //this denotes tha type of chart
  
      data: {// values on X-Axis
        labels: ['Not validated','Validated','Not Reached', ],
        datasets: [{
          label: 'My First Dataset',
          data: [n , v , r ],
          backgroundColor: [
            'red',
            'green',
            'grey',
          ],
          hoverOffset: 4
        }],
      },
      options: {
        aspectRatio:2.5
      }
    });
  }
  
  
  


}
