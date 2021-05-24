import {Component, OnInit} from '@angular/core';
import {InvoiceService} from '../../service/invoice.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Invoice} from '../../model/invoice.class';

@Component({
  selector: 'app-invoice-list-search',
  templateUrl: './invoice-list-search.component.html',
  styleUrls: ['./invoice-list-search.component.css']
})
export class InvoiceListSearchComponent implements OnInit {
  public number: number;
  public message = '';
  public message1 = '';
  public listInvoice: Invoice[] = [];
  public search: FormGroup;

  constructor(private invoiceService: InvoiceService,
              private form: FormBuilder
  ) {
  }

  ngOnInit(): void {
    this.message = '';
    this.message1 = '';
    this.search = this.form.group({
      invoiceCode: [''],
      // tslint:disable-next-line:triple-equals
      codeConfirm: ['', [Validators.required]],
    });
    this.invoiceService.getCapcha().subscribe(data => {
      this.number = data;
      console.log(this.number);
    });
  }

  searchByCode(key, capcha) {
    if (this.number + '' === capcha) {
      this.message = '';
      this.invoiceService.getInvoiceByInvoiceCode(key, capcha).subscribe(data => {
            this.listInvoice = data;
        }, error => {
        console.log('a');
        this.message1 = 'Không tìm thấy dữ liệu hợp lí';
        this.listInvoice = [];
        console.log(this.listInvoice.length);
      });
    } else {
      this.message = 'capcha sai';
    }
  }
}
