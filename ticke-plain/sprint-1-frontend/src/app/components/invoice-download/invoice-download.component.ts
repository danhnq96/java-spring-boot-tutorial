import {Component, OnInit} from '@angular/core';
import {Invoice} from '../../model/Invoice';
import {forkJoin, Observable} from 'rxjs';
import {InvoiceService} from '../../service/invoice/invoice.service';
import {AppUserService} from '../../service/app-user.service';
import {LoginService} from '../../service/login.service';
import {TicketService} from '../../service/ticket/ticket.service';

declare var $: any;

@Component({
  selector: 'app-invoice-download',
  templateUrl: './invoice-download.component.html',
  styleUrls: ['./invoice-download.component.css']
})
export class InvoiceDownloadComponent implements OnInit {
  private invoice: Invoice;
  private listInvoice;
  private appUser;
  private readonly userId;
  private ticketList;
  private checkboxValues: Array<string> = [];

  constructor(private invoiceService: InvoiceService, private appUserService: AppUserService,
              private   loginService: LoginService, private ticketService: TicketService) {
    this.userId = this.loginService.currentUserValue.id;
  }

  ngOnInit() {
    this.invoiceService.findInvoiceListByIdUser(this.userId).subscribe(dataInvoice =>{
      this.listInvoice = dataInvoice;
    });
    this.appUserService.findAppUserById(this.userId).subscribe(dataUser => {
      this.appUser = dataUser;
    });
  }

  getCheckBoxValue(id: any) {
    if (!this.checkboxValues.includes(id.target.value)) {
      this.checkboxValues.push(id.target.value);
      console.log(this.checkboxValues);
    } else {
      this.checkboxValues.splice(this.checkboxValues.indexOf(id), 1);

    }
  }

  preparePdf() {
    this.invoiceService.findInvoiceById($('input[name=\'IdInvoice\']:checked').val()).subscribe(data => {
      this.invoice = data;
      this.ticketService.findAllTicketByInvoiceId(this.invoice.id).subscribe(dataTicket => {
        this.ticketList = dataTicket;
      });
    });
  }

  // printInvoice() {
  //   // tslint:disable-next-line:prefer-for-of
  //   for (let i = 0; i < this.checkboxValues.length; i++) {
  //     this.invoiceService.findInvoiceById((Number(this.checkboxValues[i]))).subscribe(data => {
  //       this.invoice = data;
  //       $('#print').click();
  //     });
  //   }
  //
  // }
  printInvoice() {
    // tslint:disable-next-line:prefer-for-of
    for (const idInvoice of this.checkboxValues) {
      this.invoiceService.findInvoiceById(Number(idInvoice)).toPromise().then(data => {
        this.invoice = data;
        this.ticketService.findAllTicketByInvoiceId(idInvoice).toPromise().then(dataTicket => {
          this.ticketList = dataTicket;
        });
      }).then(  $('#print').click());
    }
  }
}
