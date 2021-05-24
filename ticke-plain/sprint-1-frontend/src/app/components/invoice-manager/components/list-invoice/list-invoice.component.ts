import { Component, OnInit } from '@angular/core';
import {InvoiceService} from '../../service/invoice.service';
import {Invoice} from '../../model/invoice.class';

@Component({
  selector: 'app-list-invoice',
  templateUrl: './list-invoice.component.html',
  styleUrls: ['./list-invoice.component.css']
})
export class ListInvoiceComponent implements OnInit {
  public listInvoice: Invoice[] = [];
  public p: number;
  constructor(private invoiceService: InvoiceService) { }

  ngOnInit(): void {
    this.invoiceService.getAllInvoice().subscribe( data => {
      this.listInvoice = data;
    });
  }
}
