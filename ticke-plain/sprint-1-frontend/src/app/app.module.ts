import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {MaterialModule} from './material.module';
import {DeleteTicketComponent} from './components/delete-ticket/delete-ticket.component';
import {PrintTicketComponent} from './components/print-ticket/print-ticket.component';
import {ListTicketComponent} from './components/list-ticket/list-ticket.component';
import {ListPendingTicketComponent} from './components/list-pending-ticket/list-pending-ticket/list-pending-ticket.component';
import {CancelPendingTicketComponent} from './components/list-pending-ticket/cancel-pending-ticket/cancel-pending-ticket.component';
import {MatDialogModule} from '@angular/material/dialog';
import {SearchPendingTicketComponent} from './components/search-pending-ticket/search-pending-ticket/search-pending-ticket.component';
import {VerificationEmailComponent} from './components/verification-email/verification-email.component';
import {AuthInterceptor} from './service/AuthInterceptor';
import {SpinnerComponent} from './components/spinner/spinner.component';
import {SpinnerOverlayComponent} from './components/spinner-overlay/spinner-overlay.component';
import {NgxLoadingModule} from 'ngx-loading';
import {NgxPaginationModule} from 'ngx-pagination';
import {Ng2SearchPipeModule} from 'ng2-search-filter';
import {AdminComponent} from './components/admin/admin.component';
import {InformationAdminComponent} from './components/information-admin/information-admin.component';
import {ChangePasswordAdminComponent} from './components/change-password-admin/change-password-admin.component';
import {DetailUserComponent} from './components/detail-user/detail-user.component';
import {EditUserComponent} from './components/edit-user/edit-user.component';
import {ChangePasswordUserComponent} from './components/change-password-user/change-password-user.component';
import {InformationUserComponent} from './components/information-user/information-user.component';
import {TrangChinhComponent} from './components/report-statistic/bao-cao-thong-ke/trang-chinh/trang-chinh.component';
// tslint:disable-next-line:max-line-length
import {BaoCaoComponentComponent} from './components/report-statistic/bao-cao-thong-ke/bao-cao/bao-cao-component/bao-cao-component.component';
import {TrangChuThongKeComponent} from './components/report-statistic/bao-cao-thong-ke/thong-ke/demo/trang-chu-thong-ke.component';
import {KetQuaComponent} from './components/report-statistic/bao-cao-thong-ke/thong-ke/ket-qua/ket-qua/ket-qua.component';
import {KetQuaTableComponent} from './components/report-statistic/bao-cao-thong-ke/thong-ke/ket-qua/ket-qua-table/ket-qua-table.component';
// tslint:disable-next-line:max-line-length
import {KetQuaSaiLogicComponent} from './components/report-statistic/bao-cao-thong-ke/thong-ke/ket-qua/ket-qua-sai-logic/ket-qua-sai-logic.component';
import {ChartsModule} from 'ng2-charts';
import {AuthServiceConfig, GoogleLoginProvider, SocialLoginModule} from 'angularx-social-login';
import {UserDialogComponent} from './components/user-dialog/user-dialog.component';
import {MessageUserComponent} from './components/message-user/message-user.component';

import {SendFeedbackComponent} from './components/send-feedback/send-feedback.component';
import {MessUserComponent} from './components/mess-user/mess-user.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {ConsultantComponent} from './components/consultant/consultant.component';
import {FeedbackService} from './service/feedback.service';
import {MessageConsultantComponent} from './components/consultant/message-consultant/message-consultant.component';
import {DatePipe} from '@angular/common';
import {PickerModule} from '@ctrl/ngx-emoji-mart';
import {EmojiModule} from '@ctrl/ngx-emoji-mart/ngx-emoji';
import {NavBarComponent} from './components/nav-bar/nav-bar.component';
import {FooterComponent} from './components/footer/footer.component';
import {PromotionModule} from './components/promotion/promotion.module';
import {ExcelService} from './components/report-statistic/service/excel.service';
import {DeletePassengerComponent} from './components/delete-passenger/delete-passenger.component';
import {ToastrModule} from 'ngx-toastr';
import {InvoiceManagerModule} from './components/invoice-manager/invoice-manager.module';

import {ChangePasswordSuccessfullyComponent} from './components/change-password-successfully/change-password-successfully.component';
import {GetTokenEmailAdminComponent} from './components/get-token-email-admin/get-token-email-admin.component';
import {GetCheckPasswordAdminComponent} from './components/get-check-password-admin/get-check-password-admin.component';

import {InvoiceListComponent} from './components/invoice-list/invoice-list.component';
import {InvoiceDetailComponent} from './components/invoice-detail/invoice-detail.component';
import {InvoiceDownloadComponent} from './components/invoice-download/invoice-download.component';
import {PDFExportModule} from '@progress/kendo-angular-pdf-export';
import {SearchFlightInformationComponent} from './components/flight/search-flight-information/search-flight-information.component';
import {FlightTableComponent} from './components/flight/flight-table/flight-table.component';
import {FlightTicketComponent} from './components/flight/flight-ticket/flight-ticket.component';
import {WeekdaysPipe} from './components/flight/flight-table/weekdays.pipe';
import {FlightDetailComponent} from './components/flight/flight-detail/flight-detail.component';
import {InfoPassengerBookingTicketComponent} from './components/flight/info-passenger-booking-ticket/info-passenger-booking-ticket.component';
import {ChooseTicketComponent} from './components/flight/alertError/choose-ticket/choose-ticket.component';
import {PaymentHistoryComponent} from './components/flight/payment-history/payment-history/payment-history.component';
import {SuccessfullyPaidPendingTicketComponent} from './components/list-pending-ticket/successfully-paid-pending-ticket/successfully-paid-pending-ticket.component';
import {FeedbackDialogComponent} from './components/send-feedback/feedback-dialog/feedback-dialog.component';
import {MAT_DATE_LOCALE} from '@angular/material';
import {MAT_MOMENT_DATE_ADAPTER_OPTIONS} from '@angular/material-moment-adapter';

@NgModule({
  declarations: [
    AppComponent,
    // Quân
    HomeComponent, LoginComponent, RegisterComponent, NavBarComponent, FooterComponent, VerificationEmailComponent,
      // Đăngdcvsdacdasc
    ListPendingTicketComponent, CancelPendingTicketComponent, SearchPendingTicketComponent, SpinnerComponent, SpinnerOverlayComponent,
    SpinnerComponent, SpinnerOverlayComponent,
    //  Đăng update
    SuccessfullyPaidPendingTicketComponent,
    // Danh
    AdminComponent, InformationAdminComponent, ChangePasswordAdminComponent, ChangePasswordSuccessfullyComponent,
    GetTokenEmailAdminComponent, GetCheckPasswordAdminComponent,

    // Đạt
    DetailUserComponent, EditUserComponent, ChangePasswordUserComponent, InformationUserComponent, UserDialogComponent,
    MessageUserComponent,
    // Khánh
    InvoiceListComponent, InvoiceDetailComponent, InvoiceDownloadComponent,


    // Nhật mới
    TrangChinhComponent, BaoCaoComponentComponent, TrangChuThongKeComponent,
    KetQuaComponent, KetQuaTableComponent, KetQuaSaiLogicComponent,

    // Châu
    DeleteTicketComponent, PrintTicketComponent, ListTicketComponent,
    // Vinh
    SendFeedbackComponent,
    MessUserComponent,
    ConsultantComponent,
    MessageConsultantComponent,
    // Đin
    SearchFlightInformationComponent,
    FlightTableComponent,
    WeekdaysPipe,
    FlightDetailComponent,
    FlightTicketComponent,
    InfoPassengerBookingTicketComponent,
    ChooseTicketComponent,
    PaymentHistoryComponent,
    FeedbackDialogComponent
  ],
  imports: [BrowserModule, AppRoutingModule, MaterialModule, HttpClientModule, NgxPaginationModule, Ng2SearchPipeModule,
    FormsModule, MatDialogModule, ReactiveFormsModule, NgxLoadingModule.forRoot({}), PickerModule, EmojiModule,
    ChartsModule, SocialLoginModule, PromotionModule, PDFExportModule,
    InvoiceManagerModule, ToastrModule.forRoot({
      positionClass: 'toast-top-center',
      preventDuplicates: true,
    } )

  ],
  // tslint:disable-next-line:max-line-length
  providers: [DatePipe, FeedbackService
    , {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  }, {
    provide: AuthServiceConfig,
    useFactory: getAuthServiceConfigs
  },
    // { provide: LOCALE_ID, useValue: 'vi-VN' },
    ExcelService,
    {provide: MAT_DATE_LOCALE, useValue: 'fr-FR'},
    {provide: MAT_MOMENT_DATE_ADAPTER_OPTIONS, useValue: {useUtc: true}},
],
  bootstrap: [AppComponent],
  entryComponents: [SpinnerOverlayComponent, TrangChinhComponent, BaoCaoComponentComponent, TrangChuThongKeComponent,
    KetQuaComponent, KetQuaTableComponent, KetQuaSaiLogicComponent, FeedbackDialogComponent,
    //  Đăng update
    SuccessfullyPaidPendingTicketComponent, DeletePassengerComponent
  ]
})
export class AppModule {
}

export function getAuthServiceConfigs() {
  const config = new AuthServiceConfig(
    [
      // {
      //   id: FacebookLoginProvider.PROVIDER_ID,
      //   provider: new FacebookLoginProvider('927298648089577')
      // },
      {
        id: GoogleLoginProvider.PROVIDER_ID,
        provider: new GoogleLoginProvider('462543400761-9dlslrfotn225t12crirhc7hpcmdemcu.apps.googleusercontent.com')
      },
    ]
  );
  return config;
}
