import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ChartConfiguration, ChartItem, registerables } from 'node_modules/chart.js';
import { Chart } from 'node_modules/chart.js';
import { InvoiceService } from 'src/app/services/invoice.service';

@Component({
  selector: 'app-chart-invoice',
  templateUrl: './chart-invoice.component.html',
  styleUrls: ['./chart-invoice.component.css']
})
export class ChartInvoiceComponent implements OnInit, AfterViewInit {
  @ViewChild('myChart') myChart!: ElementRef<HTMLCanvasElement>;
  Unpayed_AmmountMonth3: number = 0;
  Unpayed_AmmountMonth4: number = 0;
  Unpayed_AmmountMonth5: number = 0;
  Unpayed_AmmountMonth6: number = 0;
  Unpayed_AmmountMonth7: number = 0;
  constructor(private ps: InvoiceService) { }
  ngAfterViewInit(): void {
    throw new Error('Method not implemented.');
  }

  ngOnInit(): void {
    this.createChart()
  }


  createChart(): void {
    this.ps.Unpayed_AmmountPerMonthYear(3, 2023).subscribe(
      (Unpayed_AmmountMonth3: number) => {
        this.Unpayed_AmmountMonth3 = Unpayed_AmmountMonth3;
        this.ps.Unpayed_AmmountPerMonthYear(4, 2023).subscribe(
          (Unpayed_AmmountMonth4: number) => {
            this.Unpayed_AmmountMonth4 = Unpayed_AmmountMonth4;
            this.ps.Unpayed_AmmountPerMonthYear(5, 2023).subscribe(
              (Unpayed_AmmountMonth5: number) => {
                this.Unpayed_AmmountMonth5 = Unpayed_AmmountMonth5;
                this.ps.Unpayed_AmmountPerMonthYear(6, 2023).subscribe(
                  (Unpayed_AmmountMonth6: number) => {
                    this.Unpayed_AmmountMonth6 = Unpayed_AmmountMonth6;
                    this.ps.Unpayed_AmmountPerMonthYear(7, 2023).subscribe(
                      (Unpayed_AmmountMonth7: number) => {
                        this.Unpayed_AmmountMonth7 = Unpayed_AmmountMonth7;
                        // Create the chart with the updated data
                        Chart.register(...registerables);
                        const data = {
                          labels: ['March', 'April', 'May', 'Juin', 'July'],
                          datasets: [{
                            label: 'Total Loses Per Month (TND)',
                            backgroundColor: 'rgb(255, 99, 132)',
                            borderColor: 'rgb(255, 99, 132)',
                            data: [this.Unpayed_AmmountMonth3, this.Unpayed_AmmountMonth4, this.Unpayed_AmmountMonth5, this.Unpayed_AmmountMonth6, this.Unpayed_AmmountMonth7],
                          }]
                        };
                        const options = {
                          scales: {
                            y: {
                              beginAtZero: true,
                              display: false
                            }
                          }
                        };
                        const config: ChartConfiguration = {
                          type: 'line',
                          data: data,
                          options: options
                        }; (error: any) => {
                          console.error(error);
                        }
                        const chartItem: ChartItem = document.getElementById('my-chart') as ChartItem;
                        new Chart(chartItem, config);
                      },
                      (error: any) => {
                        console.error(error);
                      }
                    );
                  },
                  (error: any) => {
                    console.error(error);
                  }
                );
              },
              (error: any) => {
                console.error(error);
              }
            );
          });
        },
        (error: any) => {
          console.error(error);
        }
      );}

      }