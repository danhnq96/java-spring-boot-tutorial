import {Routes} from '@angular/router';
import {ListInvoiceComponent} from './components/list-invoice/list-invoice.component';
import {InvoiceListSearchComponent} from './components/invoice-list-search/invoice-list-search.component';
import {DetailInvoiceComponent} from './components/detail-invoice/detail-invoice.component';

export const routesManager: Routes = [
  {path: 'detail-invoice', component: DetailInvoiceComponent},
  {path: 'invoice-list', component: ListInvoiceComponent},
  {path: 'invoice-list-search', component: InvoiceListSearchComponent}
];

