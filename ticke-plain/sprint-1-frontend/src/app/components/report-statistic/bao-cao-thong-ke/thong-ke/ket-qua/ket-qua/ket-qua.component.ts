import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ExcelService} from '../../../../service/excel.service';
// @ts-ignore
import Chart = require('chart.js');


// @ts-ignore
@Component({
  selector: 'app-ket-qua',
  templateUrl: './ket-qua.component.html',
  styleUrls: ['./ket-qua.component.css']
})

export class KetQuaComponent implements OnInit {

  constructor(
    public dialogStatistic: MatDialogRef<KetQuaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public excelService: ExcelService,
  ) {
  }

  ngOnInit(): void {
    console.log(this.data);
    if (this.data.data1.item === 'staff') {
      const jsChart = new Chart('JsChart', {
          type: this.data.data1.type,
          data: {
            // tslint:disable-next-line:max-line-length
            labels: ['Số lượng nhân viên', 'Tổng lượng vé nhân viên bán được ngày bắt đầu', 'Tổng lượng vé nhân viên bán được ngày kết thúc', 'Tổng thu nhập ngày bắt đầu (triệu VNĐ) ', 'Tổng thu nhập ngày kết thúc(triệu VNĐ)'],
            datasets: [{
              label: 'Thống kê nhân viên dựa trên số lượng và số vé bán được.',
              // tslint:disable-next-line:max-line-length
              data: [this.data.data1.numberItem, this.data.data1.numberTicketSellDateStart, this.data.data1.numberTicketSellDateEnd, this.data.data1.priceTicketSellDateStart / 1000000, this.data.data1.priceTicketSellDateEnd / 1000000],
              backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(255, 99, 132, 0.2)',
              ],
              borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(255, 99, 132, 0.2)',
              ],
              borderWidth: 1
            }]
          },
          options: {
            scales: {
              yAxes: [{
                ticks: {
                  beginAtZero: true
                }
              }]
            }
          }
        });
        // @ts-ignore
      if (this.data.data1.timeSelectionCompare !== '') {
          const jsChartCompare = new Chart('JsChart', {
            type: this.data.data1.type,
            data: {
              // tslint:disable-next-line:max-line-length
              labels: ['Số lượng nhân viên', 'Tổng vé bán ngày bắt đầu', 'Tổng vé bán ngày kết thúc', 'Thu nhập ngày bắt đầu (triệu VNĐ) ', 'Thu nhập ngày kết thúc(triệu VNĐ)', 'Tổng vé bán ngày bắt đầu (so sánh)', 'Tổng vé bán ngày kết thúc (so sánh)', 'Thu nhập ngày bắt đầu (triệu VNĐ)(so sánh) ', 'Thu nhập ngày kết thúc(triệu VNĐ)(so sánh)'],
              datasets: [{
                label: 'Thống kê nhân viên dựa trên số lượng và số vé bán được.',
                // tslint:disable-next-line:max-line-length
                data: [this.data.data1.numberItem, this.data.data1.numberTicketSellDateStart, this.data.data1.numberTicketSellDateEnd, this.data.data1.priceTicketSellDateStart / 1000000, this.data.data1.priceTicketSellDateEnd / 1000000, this.data.data1.numberItem, this.data.data1.numberTicketSellDateStartCompare, this.data.data1.numberTicketSellDateEndCompare, this.data.data1.priceTicketSellDateStartCompare / 1000000, this.data.data1.priceTicketSellDateEndCompare / 1000000],
                backgroundColor: [
                  'rgba(255, 99, 132, 0.2)',
                  'rgba(54, 162, 235, 0.2)',
                  'rgba(255, 206, 86, 0.2)',
                  'rgba(75, 192, 192, 0.2)',
                  'rgba(255, 99, 132, 0.2)',
                ],
                borderColor: [
                  'rgba(255, 99, 132, 1)',
                  'rgba(54, 162, 235, 1)',
                  'rgba(255, 206, 86, 1)',
                  'rgba(75, 192, 192, 1)',
                  'rgba(255, 99, 132, 0.2)',
                ],
                borderWidth: 1
              }]
            },
            options: {
              scales: {
                yAxes: [{
                  ticks: {
                    beginAtZero: true
                  }
                }]
              }
            }
          });
        }
    }
    if ((this.data.data1.item === 'money')) {
      const jsChart = new Chart('JsChart', {
        type: this.data.data1.type,
        data: {
          // tslint:disable-next-line:max-line-length
          labels: ['Tổng thu nhập (triệu VNĐ)', 'Tổng vé bán được', 'Thu nhập ngày bắt đầu (triệu VNĐ)', 'Tổng thu nhập ngày kết thúc (triệu VNĐ) '],
          datasets: [{
            label: 'Thống kê thu nhập dựa trên số vé bán được.',
            // tslint:disable-next-line:max-line-length
            data: [this.data.data1.numberItem / 1000000, this.data.data1.numberTicketSellDateStart, this.data.data1.priceTicketSellDateStart / 1000000, this.data.data1.priceTicketSellDateEnd / 1000000],
            backgroundColor: [
              'rgba(255, 99, 132, 0.2)',
              'rgba(54, 162, 235, 0.2)',
              'rgba(255, 206, 86, 0.2)',
              'rgba(75, 192, 192, 0.2)',
            ],
            borderColor: [
              'rgba(255, 99, 132, 1)',
              'rgba(54, 162, 235, 1)',
              'rgba(255, 206, 86, 1)',
              'rgba(75, 192, 192, 1)',
            ],
            borderWidth: 1
          }]
        },
        options: {
          scales: {
            yAxes: [{
              ticks: {
                beginAtZero: true
              }
            }]
          }
        }
      });
      if (this.data.data1.timeSelectionCompare !== '') {
        const jsChartCompare = new Chart('JsChart', {
          type: this.data.data1.type,
          data: {
            // tslint:disable-next-line:max-line-length
            labels: ['Tổng thu nhập (triệu VNĐ)', 'Tổng vé bán được', 'Thu nhập ngày bắt đầu (triệu VNĐ)', 'Tổng thu nhập ngày kết thúc (triệu VNĐ)', 'Tổng thu nhập(triệu VNĐ)(so sánh)', 'Tổng vé bán (so sánh)', 'Thu nhập ngày bắt đầu (triệu VNĐ)(so sánh)', 'Thu nhập ngày kết thúc (triệu VNĐ)(so sánh)'],
            datasets: [{
              label: 'Thống kê nhân viên dựa trên số lượng và số vé bán được.',
              // tslint:disable-next-line:max-line-length
              data: [this.data.data1.numberItem / 1000000, this.data.data1.numberTicketSellDateStart, this.data.data1.priceTicketSellDateStart / 1000000, this.data.data1.priceTicketSellDateEnd / 1000000, this.data.data1.numberItem / 1000000, this.data.data1.numberTicketSellDateStartCompare, this.data.data1.priceTicketSellDateStartCompare / 1000000, this.data.data1.priceTicketSellDateEndCompare / 1000000],
              backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
              ],
              borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
              ],
              borderWidth: 1
            }]
          },
          options: {
            scales: {
              yAxes: [{
                ticks: {
                  beginAtZero: true
                }
              }]
            }
          }
        });
      }
    }
    if ((this.data.data1.item === 'brand')) {
      const jsChart = new Chart('JsChart', {
        type: this.data.data1.type,
        data: {
          // tslint:disable-next-line:max-line-length
          labels: ['Tổng số chuyến bay', 'Số chuyến bay ngày đầu', 'Tổng giá ngày đầu (triệu VNĐ)', 'Số chuyến bay ngày kết thúc', 'Tổng giá ngày kết thúc (triệu VNĐ) '],
          datasets: [{
            label: 'Thống kê hãng bay Airline.',
            // tslint:disable-next-line:max-line-length
            data: [this.data.data1.numberItem, this.data.data1.numberTicketSellDateStart, this.data.data1.priceTicketSellDateStart / 1000000, this.data.data1.numberTicketSellDateEnd, this.data.data1.priceTicketSellDateEnd / 1000000],
            backgroundColor: [
              'rgba(255, 99, 132, 0.2)',
              'rgba(54, 162, 235, 0.2)',
              'rgba(255, 206, 86, 0.2)',
              'rgba(75, 192, 192, 0.2)',
              'rgba(255, 99, 132, 0.2)',
            ],
            borderColor: [
              'rgba(255, 99, 132, 1)',
              'rgba(54, 162, 235, 1)',
              'rgba(255, 206, 86, 1)',
              'rgba(75, 192, 192, 1)',
              'rgba(255, 99, 132, 0.2)',

            ],
            borderWidth: 1
          }]
        },
        options: {
          scales: {
            yAxes: [{
              ticks: {
                beginAtZero: true
              }
            }]
          }
        }
      });
      if (this.data.data1.timeSelectionCompare !== '') {
        const jsChartCompare = new Chart('JsChart', {
          type: this.data.data1.type,
          data: {
            // tslint:disable-next-line:max-line-length
            labels: ['Tổng số chuyến bay ', 'Số chuyến bay ngày đầu', 'Tổng giá ngày đầu (triệu VNĐ)', 'Số chuyến bay ngày kết thúc', 'Tổng giá ngày kết thúc (triệu VNĐ) ', 'Số chuyến bay ngày đầu (so sánh)', 'Tổng giá ngày đầu (triệu VNĐ)(so sánh)', 'Số chuyến bay ngày kết thúc(so sánh)', 'Tổng giá ngày kết thúc (triệu VNĐ)(so sánh)'],
            datasets: [{
              label: 'Thống kê hãng bay Airline.',
              // tslint:disable-next-line:max-line-length
              data: [this.data.data1.numberItem, this.data.data1.numberTicketSellDateStart, this.data.data1.priceTicketSellDateStart / 1000000, this.data.data1.numberTicketSellDateEnd, this.data.data1.priceTicketSellDateEnd / 1000000 , this.data.data1.numberTicketSellDateStartCompare, this.data.data1.priceTicketSellDateStartCompare / 1000000, this.data.data1.numberTicketSellDateEndCompare, this.data.data1.priceTicketSellDateEndCompare / 1000000],
              backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
              ],
              borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 0.2)',
              ],
              borderWidth: 1
            }]
          },
          options: {
            scales: {
              yAxes: [{
                ticks: {
                  beginAtZero: true
                }
              }]
            }
          }
        });
      }
    }
  }

  // tslint:disable-next-line:typedef
  printStatistic() {
    let dataExcel: any;
    if ((this.data.data1.item === 'staff')) {
      if (this.data.data1.timeSelectionCompare === '') {
        dataExcel = [
          {
            'Số lượng nhân viên': this.data.data1.numberItem,
          },
          {
            'Ngày: ': this.data.data1.dateStart,
            'Số vé bán ': this.data.data1.numberTicketSellDateStart,
            'Tổng thu nhập (triệu VNĐ)': this.data.data1.priceTicketSellDateStart / 1000000,
          },
          {
            'Ngày: ': this.data.data1.dateEnd,
            'Số vé bán ': this.data.data1.numberTicketSellDateEnd,
            'Tổng thu nhập (triệu VNĐ)': this.data.data1.priceTicketSellDateEnd / 1000000,
          }
        ];
      } else {
        dataExcel = [
          {
            'Số lượng nhân viên': this.data.data1.numberItem,
          },
          {
            'Ngày: ': this.data.data1.dateStart,
            'Số vé bán ': this.data.data1.numberTicketSellDateStart,
            'Tổng thu nhập (triệu VNĐ)': this.data.data1.priceTicketSellDateStart / 1000000,
          },
          {
            'Ngày: ': this.data.data1.dateEnd,
            'Số vé bán ': this.data.data1.numberTicketSellDateEnd,
            'Tổng thu nhập (triệu VNĐ)': this.data.data1.priceTicketSellDateEnd / 1000000,
          },
          {
            'Ngày: ': this.data.data1.compareReportDateStart,
            'Số vé bán ': this.data.data1.numberTicketSellDateStartCompare,
            'Tổng thu nhập (triệu VNĐ)': this.data.data1.priceTicketSellDateStartCompare / 1000000,
          },
          {
            'Ngày: ': this.data.data1.compareReportDateEnd,
            'Số vé bán ': this.data.data1.numberTicketSellDateEndCompare,
            'Tổng thu nhập (triệu VNĐ)': this.data.data1.priceTicketSellDateEndCompare / 1000000,
          },
        ];
      }
    }
    if (this.data.data1.item === 'money') {
      if (this.data.data1.timeSelectionCompare === '') {
        dataExcel = [
          {
            'Tổng thu nhập (triệu VNĐ)': this.data.data1.numberItem / 1000000,
          },
          {
            'Số vé bán được:': this.data.data1.numberTicketSellDateStart,
          },
          {
            'Ngày: ': this.data.data1.dateStart,
            'Tổng thu nhập (triệu VNĐ)': this.data.data1.priceTicketSellDateStart / 1000000,
          },
          {
            'Ngày: ': this.data.data1.dateEnd,
            'Tổng thu nhập (triệu VNĐ)': this.data.data1.priceTicketSellDateEnd / 1000000,
          }
        ];
      } else {
        dataExcel = [
          {
            'Tổng thu nhập (triệu VNĐ)': this.data.data1.numberItem / 1000000,
          },
          {
            'Số vé bán được:': this.data.data1.numberTicketSellDateStart,
          },
          {
            'Ngày: ': this.data.data1.dateStart,
            'Tổng thu nhập (triệu VNĐ)': this.data.data1.priceTicketSellDateStart / 1000000,
          },
          {
            'Ngày: ': this.data.data1.dateEnd,
            'Tổng thu nhập (triệu VNĐ)': this.data.data1.priceTicketSellDateEnd / 1000000,
          },
          {
            'Số vé bán được so sánh:': this.data.data1.numberTicketSellDateStartCompare / 1000000,
          },
          {
            'Ngày: ': this.data.data1.compareReportDateStart,
            'Tổng thu nhập (triệu VNĐ)': this.data.data1.priceTicketSellDateStartCompare / 1000000,
          },
          {
            'Ngày: ': this.data.data1.compareReportDateEnd,
            'Tổng thu nhập (triệu VNĐ)': this.data.data1.priceTicketSellDateEndCompare / 1000000,
          },
        ];
      }
    }
    if (this.data.data1.item === 'brand') {
      if (this.data.data1.timeSelectionCompare === '') {
        dataExcel = [
          {
            'Số chuyến bay': this.data.data1.numberItem,
          },
          {
            'Ngày: ': this.data.data1.dateStart,
            'Số chuyến ': this.data.data1.numberTicketSellDateStart,
            'Tổng giá bán (triệu VNĐ)': this.data.data1.priceTicketSellDateStart / 1000000,
          },
          {
            'Ngày: ': this.data.data1.dateEnd,
            'Số chuyến': this.data.data1.numberTicketSellDateEnd,
            'Tổng giá bán (triệu VNĐ)': this.data.data1.priceTicketSellDateEnd / 1000000,
          }
        ];
      } else {
        dataExcel = [
          {
            'Số chuyến bay': this.data.data1.numberItem,
          },
          {
            'Ngày: ': this.data.data1.dateStart,
            'Số chuyến ': this.data.data1.numberTicketSellDateStart,
            'Tổng giá bán (triệu VNĐ)': this.data.data1.priceTicketSellDateStart / 1000000,
          },
          {
            'Ngày: ': this.data.data1.dateEnd,
            'Số chuyến ': this.data.data1.numberTicketSellDateEnd,
            'Tổng giá bán (triệu VNĐ)': this.data.data1.priceTicketSellDateEnd / 1000000,
          },
          {
            'Ngày: ': this.data.data1.compareReportDateStart,
            'Số chuyến ': this.data.data1.numberTicketSellDateStartCompare,
            'Tổng giá bán (triệu VNĐ)': this.data.data1.priceTicketSellDateStartCompare / 1000000,
          },
          {
            'Ngày: ': this.data.data1.compareReportDateEnd,
            'Số chuyến ': this.data.data1.numberTicketSellDateEndCompare,
            'Tổng giá bán (triệu VNĐ)': this.data.data1.priceTicketSellDateEndCompare / 1000000,
          },
        ];
      }
    }
    this.excelService.exportAsExcelFile(dataExcel, 'Thống kê');
  }
}


