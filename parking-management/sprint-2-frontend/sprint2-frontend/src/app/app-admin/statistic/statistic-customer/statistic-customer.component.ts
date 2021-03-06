import {Component, ElementRef, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import * as moment from 'moment';
import {StatisticsService} from "../../../service/statistics.service";
import *as Highcharts from "highcharts";
import {MatDialog} from "@angular/material/dialog";
import {StatisticNotifyComponent} from "../statistic-notify/statistic-notify.component";

declare var require: any;
require('highcharts/modules/exporting')(Highcharts);
require('highcharts/modules/export-data.src')(Highcharts);

export const MY_FORMATS = {
  parse: {
    dateInput: 'LL',
  },
  display: {
    dateInput: 'DD-MM-YYYY',
    monthYearLabel: 'YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'YYYY',
  },
};

@Component({
  selector: 'app-statistic-customer',
  templateUrl: './statistic-customer.component.html',
  styleUrls: ['./statistic-customer.component.css']
})
export class StatisticCustomerComponent implements OnInit {
  formStatisticCustomerRegisterPeriod: FormGroup;
  toTalCustomerRegisterPeriods: any[];
  day;
  days;
  message;

  constructor(public formBuilder: FormBuilder,
              public statisticsService: StatisticsService,
              public dialog: MatDialog,
              public el: ElementRef) {
    this.formStatisticCustomerRegisterPeriod = this.formBuilder.group({
      fromDay: ['', Validators.required],
      toDay: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  openStatisticCustomerRegisterPeriod() {
    // get data day from form group
    this.day = {
      fromDay: this.formStatisticCustomerRegisterPeriod.controls.fromDay.value,
      toDay: this.formStatisticCustomerRegisterPeriod.controls.toDay.value,
    };
    // convert day to string by 'moment'
    this.days = {
      fromDay: moment(this.day.fromDay).format('YYYY-MM-DD'),
      toDay: moment(this.day.toDay).format('YYYY-MM-DD'),
    };
    if (this.formStatisticCustomerRegisterPeriod.valid) {
      // statistic total customer in period time
      this.statisticsService.getToTalCustomerRegisterPeriod(this.days).subscribe(dataToTalCustomerRegisterPeriod => {
        this.toTalCustomerRegisterPeriods = dataToTalCustomerRegisterPeriod;
        if (dataToTalCustomerRegisterPeriod != null) {
          this.createChartTotalCustomer();
        } else {
          this.dialog.open(StatisticNotifyComponent, {
            width: '500px',
            disableClose: true,
          });
        }
      });
    } else {
      for (const KEY of Object.keys(this.formStatisticCustomerRegisterPeriod.controls)) {
        if (this.formStatisticCustomerRegisterPeriod.controls[KEY].invalid) {
          const INVALID_CONTROL = this.el.nativeElement.querySelector('[formControlName="' + KEY + '"]');
          INVALID_CONTROL.focus();
          break;
        }
      }
    }
  }

  // create chart total customer
  createChartTotalCustomer() {
    // @ts-ignore
    Highcharts.chart('statistic-customer', {

      chart: {
        type: 'column',
        backgroundColor: 'none',
      },

      lang: {
        downloadCSV: 'T???i file CSV',
        downloadJPEG: 'T???i h??nh ???nh JPEG',
        downloadPDF: 'T???i file PDF',
        downloadPNG: 'T???i h??nh ???nh PNG',
        downloadSVG: 'T???i file SVG',
        downloadXLS: 'T???i file XLS',
        viewFullscreen: 'Hi???n th??? to??n m??n h??nh',
        printChart: 'In',
        viewData: 'Hi???n th??? d??? li???u c???a b???ng',
      },

      title: {
        text: 'Bi???u ????? s??? l?????ng kh??ch h??ng',
        style: {
          color: '#435d7d',
          font: 'bold 20px "Arial", Verdana, sans-serif'
        }
      },

      xAxis: {
        title: {
          text: 'Th???i gian',
          style: {
            fontSize: '15px',
            color: 'black',
          }
        },
        categories: this.toTalCustomerRegisterPeriods.map(x => x.date_register),
        lineColor: 'black',
        labels: {
          style: {
            fontSize: '15px',
            color: 'black'
          }
        }
      },

      yAxis: {
        title: {
          text: 'S??? l?????ng kh??ch h??ng'
        },
        labels: {
          style: {
            fontSize: '15px',
            color: 'black'
          }
        }
      },

      legend: {
        enabled: false
      },

      plotOptions: {
        series: {
          borderWidth: 1,
          dataLabels: {
            enabled: true,
          }
        }
      },

      responsive: {
        rules: [{
          condition: {
            maxWidth: 500,
          },
          chartOptions: {
            legend: {
              align: "center",
              verticalAlign: "bottom",
              layout: "horizontal",
            },
            yAxis: {
              labels: {
                align: "left",
                x: 0,
                y: -5,
              }
            }
          }
        }]
      },
      colors: [
        'rgb(255, 99, 132)',
        'rgb(84, 255, 159)',
        'rgb(255, 77, 0)',
        'rgb(255, 165, 0)',
        'rgb(75, 0, 130)',
        'rgb(0, 0, 255)',
        'rgb(255,0,0)',
        'rgb(0,255,0)',
        'rgb(255,255,0)',
        'rgb(128,0,0)',
        'rgb(128,128,128)',
        'rgb(255, 99, 132)',
        'rgb(84, 255, 159)',
        'rgb(255, 77, 0)',
        'rgb(255, 165, 0)',
        'rgb(75, 0, 130)',
        'rgb(0, 0, 255)',
        'rgb(255,0,0)',
      ],
      series: [
        {
          name: "S??? l?????ng kh??ch h??ng ",
          colorByPoint: true,
          data: this.toTalCustomerRegisterPeriods.map(x => x.total_customer),
        }
      ],
    });
  }
}
