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
