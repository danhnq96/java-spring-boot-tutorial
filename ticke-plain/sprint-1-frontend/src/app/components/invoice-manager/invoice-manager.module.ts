import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListInvoiceComponent } from './components/list-invoice/list-invoice.component';
import {RouterModule} from '@angular/router';
import {routesManager} from './invoice-manager.router';
import { InvoiceListSearchComponent } from './components/invoice-list-search/invoice-list-search.component';
import {ReactiveFormsModule} from '@angular/forms';
import { DetailInvoiceComponent } from './components/detail-invoice/detail-invoice.component';
import {NgxPaginationModule} from 'ngx-pagination';
@NgModule({
  declarations: [ListInvoiceComponent, InvoiceListSearchComponent, DetailInvoiceComponent],
    imports: [
        CommonModule,
        RouterModule.forChild(routesManager),
        ReactiveFormsModule,
      NgxPaginationModule
    ],
  exports: [
  ]
})
export class InvoiceManagerModule { }
