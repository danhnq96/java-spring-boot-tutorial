import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../service/login.service';
import {AppUserService} from '../../service/app-user.service';
import {InvoiceService} from '../../service/invoice/invoice.service';

@Component({
  selector: 'app-invoice-list',
  templateUrl: './invoice-list.component.html',
  styleUrls: ['./invoice-list.component.css']
})
export class InvoiceListComponent implements OnInit {
  private invoiceList;
  private appUser;
  private readonly appUserId;

  constructor(public loginService: LoginService, private appUserService: AppUserService, private invoiceService: InvoiceService) {
    this.appUserId = this.loginService.currentUserValue.id;
  }

  ngOnInit() {
    this.appUserService.findAppUserById(this.appUserId).subscribe(dataAppUser => {
      console.log(dataAppUser);
      this.appUser = dataAppUser;
    });
    this.invoiceService.findInvoiceListByIdUser(this.appUserId).subscribe(dataInvoice => {
      this.invoiceList = dataInvoice;
      console.log(dataInvoice);
    });
  }

}
