import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {BaoCaoService} from '../../../service/bao-cao.service';
import {KetQuaComponent} from '../../thong-ke/ket-qua/ket-qua/ket-qua.component';
import {ThongKeApiService} from '../../../service/thong-ke-api.service';
import {KetQuaTableComponent} from '../../thong-ke/ket-qua/ket-qua-table/ket-qua-table.component';
import {KetQuaSaiLogicComponent} from '../../thong-ke/ket-qua/ket-qua-sai-logic/ket-qua-sai-logic.component';
import {TrangChuThongKeComponent} from '../../thong-ke/demo/trang-chu-thong-ke.component';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-bao-cao-component',
  templateUrl: './bao-cao-component.component.html',
  styleUrls: ['./bao-cao-component.component.css']
})
export class BaoCaoComponentComponent implements OnInit {
  maxDate = new Date();
  minDateStart = new Date(2020, 0, 1);
  timesSelection = [
    {value: 'week', viewValue: 'Tuần'},
    {value: 'month', viewValue: 'Tháng'},
    {value: 'quarter', viewValue: 'Quí'},
    {value: 'year', viewValue: 'Năm'},
  ];
  typeReport = [
    {value: 'table', viewValue: 'Bảng'},
    {value: 'bar', viewValue: 'Biểu đồ cột'},
    {value: 'line', viewValue: 'Biểu đồ đường'},
  ];
  reportAbout = [
    {value: 'money', viewValue: 'Doanh thu'},
    {value: 'staff', viewValue: 'Nhân viên'},
    {value: 'brand', viewValue: 'Hãng'},
  ];
  compareReportTimeSelected = '';
  reportTimeSelected = '';
  compareReport = false;
  // @ts-ignore
  public reportForm: FormGroup;
  // @ts-ignore
  public statisticForm: FormGroup;

  constructor(
    public formBuilder: FormBuilder,
    private reportService: BaoCaoService,
    private thongKeApiService: ThongKeApiService,
    private diaLog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.reportForm = this.formBuilder.group(
      {
        typeReport: ['', Validators.required],
        itemReport: ['', Validators.required],
        timeSelection: ['', Validators.required],
        dateStart: ['', Validators.required],
        dateEnd: ['', Validators.required],
        compareReportTimeSelection: '',
        compareReportDateStart: new Date(2019, 0, 1),
        compareReportDateEnd: new Date(2019, 0, 1)
      }
    );
    this.statisticForm = this.formBuilder.group({
      numberItem: '',
      numberTicketSellDateStart: '',
      priceTicketSellDateStart: '',
      numberTicketSellDateEnd: '',
      priceTicketSellDateEnd: '',
      numberTicketSellDateStartCompare: '',
      priceTicketSellDateStartCompare: '',
      numberTicketSellDateEndCompare: '',
      priceTicketSellDateEndCompare: '',
      item: '',
      type: '',
      message: ''
      }
    );
  }
  // tslint:disable-next-line:typedef
  showCompareReportForm() {
    const checkCompareReport = !this.compareReport;
    this.compareReport = checkCompareReport;
    this.validateCompareReportForm();
  }
  // tslint:disable-next-line:typedef
  validateCompareReportForm() {
    const compareReportTimeSelection = this.reportForm.get('compareReportTimeSelection');
    const compareReportDateStart = this.reportForm.get('compareReportDateStart');
    const compareReportDateEnd = this.reportForm.get('compareReportDateEnd');
    // khi click vào check box sẽ chạy funtion này và validate compare report
    if (this.compareReport) {
      this.reportForm.controls.compareReportDateStart.setValue('');
      this.reportForm.controls.compareReportDateEnd.setValue('');
      // tslint:disable-next-line:no-unused-expression
      compareReportTimeSelection.setValidators(Validators.required);
      // tslint:disable-next-line:no-unused-expression
      compareReportDateStart.setValidators(Validators.required);
      compareReportDateEnd.setValidators(Validators.required);
    } else {
      // tslint:disable-next-line:no-unused-expression
      compareReportTimeSelection.setValidators(null);
      compareReportDateStart.setValidators(null);
      // tslint:disable-next-line:no-unused-expression
      compareReportDateEnd.setValidators(null);
      this.reportForm.controls.compareReportTimeSelection.setValue('');
      this.reportForm.controls.compareReportDateStart.setValue(new Date(2019, 0, 1));
      this.reportForm.controls.compareReportDateEnd.setValue(new Date(2019, 0, 1));
    }
  }
  // tslint:disable-next-line:typedef
  addNewReport() {
    // tslint:disable-next-line:prefer-const
    let idStatistic;
    if (this.reportForm.valid) {
      this.reportService.addNewReport(this.reportForm.value).subscribe(data => {
        this.reportForm.patchValue(data);
        // this.thongKeApiService.findStatisticByID(dat).subscribe(statistic => {
        //   this.statisticForm.patchValue(statistic);
        //   console.log(this.statisticForm);
        // });
        this.thongKeApiService.getAllStatistic().subscribe( listStatistic => {
          // @ts-ignore
          const statisticReply = listStatistic[listStatistic.length - 1];
          if (statisticReply.type === '') {
            const dialogStatistic = this.diaLog.open(KetQuaSaiLogicComponent, {
              width: '600px',
            });
          } else if (statisticReply.type !== 'table') {
            const dialogStatistic = this.diaLog.open(KetQuaComponent, {
              width: '600px',
              data: {data1: statisticReply},
            });
          } else {
            const dialogStatistic = this.diaLog.open(KetQuaTableComponent, {
              width: '600px',
              data: {data1: statisticReply},
            });
          }
        });
      });
    }
  }
  // tslint:disable-next-line:typedef
  openDemoDialog() {
    const demoDialog = this.diaLog.open(TrangChuThongKeComponent, {
      width: '550px', height: '150%'
    });
    demoDialog.afterClosed().subscribe(result => {
      this.reportForm.patchValue(this.reportForm.value);
    });
  }
}
