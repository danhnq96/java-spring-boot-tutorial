import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {VerificationEmailComponent} from './components/verification-email/verification-email.component';
import {InformationAdminComponent} from './components/information-admin/information-admin.component';
import {AdminComponent} from './components/admin/admin.component';
import {EditUserComponent} from './components/edit-user/edit-user.component';
import {ChangePasswordUserComponent} from './components/change-password-user/change-password-user.component';
import {InformationUserComponent} from './components/information-user/information-user.component';
import {DetailUserComponent} from './components/detail-user/detail-user.component';
import {SearchPendingTicketComponent} from './components/search-pending-ticket/search-pending-ticket/search-pending-ticket.component';
import {ListPendingTicketComponent} from './components/list-pending-ticket/list-pending-ticket/list-pending-ticket.component';
import {ListEmployeeComponent} from './components/list-employee/list-employee.component';
import {CreateEmployeeComponent} from './components/create-employee/create-employee.component';
import {EditEmployeeComponent} from './components/edit-employee/edit-employee.component';
import {DeleteEmployeeComponent} from './components/delete-employee/delete-employee.component';
import {CommonModule, DatePipe} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MaterialModule} from './material.module';
import {MatDialogModule} from '@angular/material';
import {NgxPaginationModule} from 'ngx-pagination';
import {EmployeeComponent} from './components/employee/employee.component';
// tslint:disable-next-line:max-line-length
import {BaoCaoComponentComponent} from './components/report-statistic/bao-cao-thong-ke/bao-cao/bao-cao-component/bao-cao-component.component';
import {ListTicketComponent} from './components/list-ticket/list-ticket.component';
import {EditTicketComponent} from './components/edit-ticket/edit-ticket.component';
import {InputTicketSellComponent} from './components/input-ticket-sell/input-ticket-sell.component';
import {PrintTicketComponent} from './components/print-ticket/print-ticket.component';
import {MessageNoticeEmployeeComponent} from './components/message-notice-employee/message-notice-employee.component';
import {PrintTicketTwoWayComponent} from './components/print-ticket-two-way/print-ticket-two-way.component';
import {NoticePageComponent} from './components/notice-page/notice-page.component';
import {PromotionListForCustomerComponent} from './components/promotion/promotion-list-for-customer/promotion-list-for-customer.component';
import {InvoiceListComponent} from './components/invoice-list/invoice-list.component';
import {InvoiceDetailComponent} from './components/invoice-detail/invoice-detail.component';
import {InvoiceDownloadComponent} from './components/invoice-download/invoice-download.component';
import {SearchFlightInformationComponent} from './components/flight/search-flight-information/search-flight-information.component';
import {PaymentHistoryComponent} from './components/flight/payment-history/payment-history/payment-history.component';
import {DeleteTicketComponent} from './components/delete-ticket/delete-ticket.component';

import {MessUserComponent} from './components/mess-user/mess-user.component';
import {SendFeedbackComponent} from './components/send-feedback/send-feedback.component';
import {ConsultantComponent} from './components/consultant/consultant.component';
import {MessageConsultantComponent} from './components/consultant/message-consultant/message-consultant.component';
// KhánhPQ
import {CheckinOnlineComponent} from './components/checkin-online/checkin-online.component';
import {PromotionCodeComponent} from './components/promotion-code/promotion-code.component';
import {PassengerComponent} from './components/passenger/passenger.component';
import {CreatePassengerComponent} from './components/create-passenger/create-passenger.component';
import {DeletePassengerComponent} from './components/delete-passenger/delete-passenger.component';
import {EditPassengerComponent} from './components/edit-passenger/edit-passenger.component';
import {ListPassengerComponent} from './components/list-passenger/list-passenger.component';
import {MatRadioModule} from '@angular/material/radio';
import {EmployeeInfoComponent} from './components/employee-info/employee-info.component';
import {EmployeeDetailComponent} from './components/employee-detail/employee-detail.component';
import {ChangePasswordEmployeeComponent} from './components/change-password-employee/change-password-employee.component';
import {MatIconModule} from '@angular/material/icon';

import {ChangePasswordSuccessfullyComponent} from './components/change-password-successfully/change-password-successfully.component';
import {GetTokenEmailAdminComponent} from './components/get-token-email-admin/get-token-email-admin.component';
import {GetCheckPasswordAdminComponent} from './components/get-check-password-admin/get-check-password-admin.component';
import {DetailInvoiceComponent} from './components/invoice-manager/components/detail-invoice/detail-invoice.component';
import {ListInvoiceComponent} from './components/invoice-manager/components/list-invoice/list-invoice.component';
import {InvoiceListSearchComponent} from './components/invoice-manager/components/invoice-list-search/invoice-list-search.component';

const routes: Routes = [
  // Quân :
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'verification-email', component: VerificationEmailComponent},
  // Vinh
  {path: 'message', component: MessUserComponent},
  {path: 'send-feedback', component: SendFeedbackComponent},
  {
    path: 'consultant', component: ConsultantComponent,
    children: [
      {path: 'mess/:id', component: MessageConsultantComponent}
    ]
  },
  // Danh
  {
    path: 'admin', component: AdminComponent,
    children: [
      {path: 'information', component: InformationAdminComponent},
      {path: 'change-password-successfully', component: ChangePasswordSuccessfullyComponent},
      {path: 'get-token-email', component: GetTokenEmailAdminComponent},
      {path: 'get-check-password', component: GetCheckPasswordAdminComponent},
      // Nhật mới
      {path: 'report', component: BaoCaoComponentComponent}
    ]
  },
  // Đạt
  {path: 'detail-user', component: DetailUserComponent},
  {
    path: 'informationUser', component: InformationUserComponent,
    children: [
      {path: 'detailUser', component: DetailUserComponent},
      {path: 'editUser', component: EditUserComponent},
      {path: 'changPassword', component: ChangePasswordUserComponent},
      {path: 'list-pending-ticket', component: ListPendingTicketComponent},
      {path: 'search-pending-ticket', component: SearchPendingTicketComponent},
      {path: 'invoice-list', component: InvoiceListComponent},
      {path: 'history' , component: PaymentHistoryComponent},
    ]
  },
  // Quốc
  {
    path: 'passenger', component: PassengerComponent,
    children: [
      {path: 'list-Passenger', component: ListPassengerComponent},
      {path: 'list-Passenger/create-Passenger', component: CreatePassengerComponent},
      {path: 'list-Passenger/edit-Passenger/:id', component: EditPassengerComponent},
      {path: 'employee-Detail', component: EmployeeDetailComponent},
    ]
  },
  // Quốc
  {
    path: 'passenger', component: PassengerComponent,
    children: [
      {path: 'list-Passenger', component: ListPassengerComponent},
      {path: 'list-Passenger/create-Passenger', component: CreatePassengerComponent},
      {path: 'list-Passenger/edit-Passenger/:id', component: EditPassengerComponent},
      {path: 'employee-detail', component: EmployeeDetailComponent},
    ]
  },

  // Đăng:caca
  {path: 'list-pending-ticket', component: ListPendingTicketComponent},
  {path: 'search-pending-ticket', component: SearchPendingTicketComponent},
  // Ngân
  {path: 'promotion', component: PromotionListForCustomerComponent},
  // Mai :
  {path: 'list-employee', component: ListEmployeeComponent},
  {path: 'list-employee/create-employee', component: CreateEmployeeComponent},
  {path: 'list-employee/edit-employee/:id', component: EditEmployeeComponent},
  {path: 'delete-employee', component: DeleteEmployeeComponent},
  {path: 'message-notice-employee', component: MessageNoticeEmployeeComponent},

  // Châu :
  {path: 'list-ticket', component: ListTicketComponent},
  {path: 'print-ticket/:id', component: PrintTicketComponent},
  {path: 'input-ticket-sell', component: InputTicketSellComponent},
  {
    path: 'print-ticket-two-way/:bookingCode/:passengerList/:flightDeparture/:flightArrival',
    component: PrintTicketTwoWayComponent
  },
  {path: 'notice-page', component: NoticePageComponent},
  // PQ Khánh:
  {path: 'checkin-online', component: CheckinOnlineComponent},
  {path: 'promotion-code', component: PromotionCodeComponent},

  {path: 'deleteTicket', component: DeleteTicketComponent},
  {path: 'listTicket', component: ListTicketComponent},
  // khánh
  {path: 'invoice-detail/:id', component: InvoiceDetailComponent},
  {path: 'invoice-download', component: InvoiceDownloadComponent},
  // Đin
  {path: 'search', component: SearchFlightInformationComponent},

  // Hoàng
  {path: 'detail-invoice', component: DetailInvoiceComponent},
  {path: 'invoice-list', component: ListInvoiceComponent},
  {path: 'invoice-list-search', component: InvoiceListSearchComponent}
];

// @ts-ignore
@NgModule({
  imports: [RouterModule.forRoot(routes), CommonModule, ReactiveFormsModule, MaterialModule,
    MatDialogModule, FormsModule, NgxPaginationModule, MatRadioModule, MatIconModule],
  exports: [RouterModule],
  declarations: [ListEmployeeComponent, CreateEmployeeComponent, EditEmployeeComponent,
    DeleteEmployeeComponent, EmployeeComponent, EditTicketComponent, InputTicketSellComponent,
    PrintTicketTwoWayComponent, MessageNoticeEmployeeComponent, CheckinOnlineComponent, PromotionCodeComponent,
    NoticePageComponent, PassengerComponent, CreatePassengerComponent, DeletePassengerComponent, EditPassengerComponent,
    ListPassengerComponent, EmployeeInfoComponent, EmployeeDetailComponent, ChangePasswordEmployeeComponent,
  ],
  providers: [
    DatePipe
    //
  ]
})
export class AppRoutingModule {
}
