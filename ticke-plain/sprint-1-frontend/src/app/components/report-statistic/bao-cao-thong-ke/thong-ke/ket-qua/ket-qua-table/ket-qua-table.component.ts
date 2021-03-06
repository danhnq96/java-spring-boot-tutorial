import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ExcelService} from '../../../../service/excel.service';

@Component({
  selector: 'app-ket-qua-table',
  templateUrl: './ket-qua-table.component.html',
  styleUrls: ['./ket-qua-table.component.css']
})
export class KetQuaTableComponent implements OnInit {
  tableData: any;

  constructor(
    public dialogStatistic: MatDialogRef<KetQuaTableComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public excelService: ExcelService,
  ) { }

  ngOnInit(): void {
    if (this.data.data1.item === 'staff'){
      this.tableData = [
        this.data.data1.numberItem, this.data.data1.numberTicketSellDateStart,
        this.data.data1.priceTicketSellDateStart, this.data.data1.numberTicketSellDateEnd,
        this.data.data1.priceTicketSellDateEnd
      ];
      if (this.data.data1.timeSelectionCompare !== ''){
        this.tableData.push(this.data.data1.numberTicketSellDateStartCompare);
        this.tableData.push(this.data.data1.priceTicketSellDateStartCompare);
        this.tableData.push(this.data.data1.numberTicketSellDateEndCompare);
        this.tableData.push(this.data.data1.priceTicketSellDateEndCompare);
      }
    }
    if (this.data.data1.item === 'money'){
      this.tableData = [
        this.data.data1.numberItem, this.data.data1.numberTicketSellDateStart,
        this.data.data1.priceTicketSellDateStart, this.data.data1.priceTicketSellDateEnd
      ];
      if (this.data.data1.timeSelectionCompare !== ''){
        this.tableData.push(this.data.data1.numberTicketSellDateStartCompare);
        this.tableData.push(this.data.data1.priceTicketSellDateStartCompare);
        this.tableData.push(this.data.data1.priceTicketSellDateEndCompare);
      }
    }
    if (this.data.data1.item === 'brand'){
      this.tableData = [
        this.data.data1.numberItem, this.data.data1.numberTicketSellDateStart,
        this.data.data1.priceTicketSellDateStart, this.data.data1.numberTicketSellDateEnd,
        this.data.data1.priceTicketSellDateEnd
      ];
      if (this.data.data1.timeSelectionCompare !== ''){
        this.tableData.push(this.data.data1.numberTicketSellDateStartCompare);
        this.tableData.push(this.data.data1.priceTicketSellDateStartCompare);
        this.tableData.push(this.data.data1.numberTicketSellDateEndCompare);
        this.tableData.push(this.data.data1.priceTicketSellDateEndCompare);
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
            'S??? l?????ng nh??n vi??n': this.data.data1.numberItem,
          },
          {
            'Ng??y: ': this.data.data1.dateStart,
            'S??? v?? b??n ': this.data.data1.numberTicketSellDateStart,
            'T???ng thu nh???p (tri???u VN??)': this.data.data1.priceTicketSellDateStart / 1000000,
          },
          {
            'Ng??y: ': this.data.data1.dateEnd,
            'S??? v?? b??n ': this.data.data1.numberTicketSellDateEnd,
            'T???ng thu nh???p (tri???u VN??)': this.data.data1.priceTicketSellDateEnd / 1000000,
          }
        ];
      } else {
        dataExcel = [
          {
            'S??? l?????ng nh??n vi??n': this.data.data1.numberItem,
          },
          {
            'Ng??y: ': this.data.data1.dateStart,
            'S??? v?? b??n ': this.data.data1.numberTicketSellDateStart,
            'T???ng thu nh???p (tri???u VN??)': this.data.data1.priceTicketSellDateStart / 1000000,
          },
          {
            'Ng??y: ': this.data.data1.dateEnd,
            'S??? v?? b??n ': this.data.data1.numberTicketSellDateEnd,
            'T???ng thu nh???p (tri???u VN??)': this.data.data1.priceTicketSellDateEnd / 1000000,
          },
          {
            'Ng??y: ': this.data.data1.compareReportDateStart,
            'S??? v?? b??n ': this.data.data1.numberTicketSellDateStartCompare,
            'T???ng thu nh???p (tri???u VN??)': this.data.data1.priceTicketSellDateStartCompare / 1000000,
          },
          {
            'Ng??y: ': this.data.data1.compareReportDateEnd,
            'S??? v?? b??n ': this.data.data1.numberTicketSellDateEndCompare,
            'T???ng thu nh???p (tri???u VN??)': this.data.data1.priceTicketSellDateEndCompare / 1000000,
          },
        ];
      }
    }
    if (this.data.data1.item === 'money') {
      if (this.data.data1.timeSelectionCompare === '') {
        dataExcel = [
          {
            'T???ng thu nh???p (tri???u VN??)': this.data.data1.numberItem / 1000000,
          },
          {
            'S??? v?? b??n ???????c:': this.data.data1.numberTicketSellDateStart,
          },
          {
            'Ng??y: ': this.data.data1.dateStart,
            'T???ng thu nh???p (tri???u VN??)': this.data.data1.priceTicketSellDateStart / 1000000,
          },
          {
            'Ng??y: ': this.data.data1.dateEnd,
            'T???ng thu nh???p (tri???u VN??)': this.data.data1.priceTicketSellDateEnd / 1000000,
          }
        ];
      } else {
        dataExcel = [
          {
            'T???ng thu nh???p (tri???u VN??)': this.data.data1.numberItem / 1000000,
          },
          {
            'S??? v?? b??n ???????c:': this.data.data1.numberTicketSellDateStart,
          },
          {
            'Ng??y: ': this.data.data1.dateStart,
            'T???ng thu nh???p (tri???u VN??)': this.data.data1.priceTicketSellDateStart / 1000000,
          },
          {
            'Ng??y: ': this.data.data1.dateEnd,
            'T???ng thu nh???p (tri???u VN??)': this.data.data1.priceTicketSellDateEnd / 1000000,
          },
          {
            'S??? v?? b??n ???????c so s??nh:': this.data.data1.numberTicketSellDateStartCompare / 1000000,
          },
          {
            'Ng??y: ': this.data.data1.compareReportDateStart,
            'T???ng thu nh???p (tri???u VN??)': this.data.data1.priceTicketSellDateStartCompare / 1000000,
          },
          {
            'Ng??y: ': this.data.data1.compareReportDateEnd,
            'T???ng thu nh???p (tri???u VN??)': this.data.data1.priceTicketSellDateEndCompare / 1000000,
          },
        ];
      }
    }
    if (this.data.data1.item === 'brand') {
      if (this.data.data1.timeSelectionCompare === '') {
        dataExcel = [
          {
            'S??? chuy???n bay': this.data.data1.numberItem,
          },
          {
            'Ng??y: ': this.data.data1.dateStart,
            'S??? chuy???n ': this.data.data1.numberTicketSellDateStart,
            'T???ng gi?? b??n (tri???u VN??)': this.data.data1.priceTicketSellDateStart / 1000000,
          },
          {
            'Ng??y: ': this.data.data1.dateEnd,
            'S??? chuy???n': this.data.data1.numberTicketSellDateEnd,
            'T???ng gi?? b??n (tri???u VN??)': this.data.data1.priceTicketSellDateEnd / 1000000,
          }
        ];
      } else {
        dataExcel = [
          {
            'S??? chuy???n bay': this.data.data1.numberItem,
          },
          {
            'Ng??y: ': this.data.data1.dateStart,
            'S??? chuy???n ': this.data.data1.numberTicketSellDateStart,
            'T???ng gi?? b??n (tri???u VN??)': this.data.data1.priceTicketSellDateStart / 1000000,
          },
          {
            'Ng??y: ': this.data.data1.dateEnd,
            'S??? chuy???n ': this.data.data1.numberTicketSellDateEnd,
            'T???ng gi?? b??n (tri???u VN??)': this.data.data1.priceTicketSellDateEnd / 1000000,
          },
          {
            'Ng??y: ': this.data.data1.compareReportDateStart,
            'S??? chuy???n ': this.data.data1.numberTicketSellDateStartCompare,
            'T???ng gi?? b??n (tri???u VN??)': this.data.data1.priceTicketSellDateStartCompare / 1000000,
          },
          {
            'Ng??y: ': this.data.data1.compareReportDateEnd,
            'S??? chuy???n ': this.data.data1.numberTicketSellDateEndCompare,
            'T???ng gi?? b??n (tri???u VN??)': this.data.data1.priceTicketSellDateEndCompare / 1000000,
          },
        ];
      }
    }
    this.excelService.exportAsExcelFile(dataExcel, 'Th???ng k??');
  }
}
