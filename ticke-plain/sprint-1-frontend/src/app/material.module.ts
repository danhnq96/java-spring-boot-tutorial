import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {ChangePasswordAdminComponent} from './components/change-password-admin/change-password-admin.component';
import {MatDialogModule} from '@angular/material';
import {EditUserComponent} from './components/edit-user/edit-user.component';
import {ChangePasswordUserComponent} from './components/change-password-user/change-password-user.component';
import {CancelPendingTicketComponent} from './components/list-pending-ticket/cancel-pending-ticket/cancel-pending-ticket.component';
import {DeleteTicketComponent} from './components/delete-ticket/delete-ticket.component';
import {EditTicketComponent} from './components/edit-ticket/edit-ticket.component';
import {UserDialogComponent} from './components/user-dialog/user-dialog.component';
import {MessageUserComponent} from './components/message-user/message-user.component';
import {MatRadioModule} from "@angular/material/radio";
import {MatIconModule} from "@angular/material/icon";
import {MatStepperModule} from "@angular/material/stepper";
import {MatTabsModule} from "@angular/material/tabs";
import {MAT_DIALOG_DEFAULT_OPTIONS} from "@angular/material/dialog";
import {FlightDetailComponent} from "./components/flight/flight-detail/flight-detail.component";
import {FlightTicketComponent} from "./components/flight/flight-ticket/flight-ticket.component";
import {InfoPassengerBookingTicketComponent} from "./components/flight/info-passenger-booking-ticket/info-passenger-booking-ticket.component";
import {LoginComponent} from "./components/login/login.component";
import {ChooseTicketComponent} from "./components/flight/alertError/choose-ticket/choose-ticket.component";
import {MatMomentDateModule} from '@angular/material-moment-adapter';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatDividerModule,
    MatButtonModule,
    MatDialogModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatIconModule,
    MatStepperModule,
    MatTabsModule,
    MatMomentDateModule,
  ],
  exports: [
    CommonModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatDividerModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    MatDialogModule,
    MatRadioModule,
    MatIconModule,
    MatStepperModule,
    MatTabsModule,
    MatMomentDateModule,

  ],
  providers: [
    MatDatepickerModule, {provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}}
  ],
  entryComponents: [
    ChangePasswordAdminComponent,
    EditUserComponent, ChangePasswordUserComponent,
    CancelPendingTicketComponent, DeleteTicketComponent, EditTicketComponent,
    UserDialogComponent, MessageUserComponent,
    FlightDetailComponent, FlightTicketComponent, InfoPassengerBookingTicketComponent, LoginComponent, ChooseTicketComponent
],
  bootstrap: [InfoPassengerBookingTicketComponent]
})
export class MaterialModule { }
