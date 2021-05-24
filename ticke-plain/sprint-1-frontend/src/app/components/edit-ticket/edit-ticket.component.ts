import {Component, ElementRef, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TicketService} from '../../service/ticket/ticket.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-edit-ticket',
  templateUrl: './edit-ticket.component.html',
  styleUrls: ['./edit-ticket.component.css']
})
export class EditTicketComponent implements OnInit {
  protected formEdit: FormGroup;
  protected bookingCodeDisplay;
  protected passengerEdit;
  protected appUserEdit;
  protected flightInformationDisplay;

  constructor(
    protected dialogRef: MatDialogRef<EditTicketComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    protected formBuilder: FormBuilder,
    private ticketService: TicketService,
    protected router: Router,
    private el: ElementRef
  ) {
  }

  ngOnInit() {
    this.bookingCodeDisplay = this.data.dataTicket.booking;
    this.flightInformationDisplay = this.data.dataTicket.flightInformation;
    this.formEdit = this.formBuilder.group({
      id: [this.data.dataTicket.id],
      priceArrival: [this.data.dataTicket.priceArrival],
      priceDeparture: [this.data.dataTicket.priceDeparture],
      statusCheckin: [this.data.dataTicket.statusCheckin],
      ticketCode: [this.data.dataTicket.ticketCode],
      booking: [this.data.dataTicket.booking.bookingCode],
      employee: [this.data.dataTicket.employee.id],
      flightInformation: [this.data.dataTicket.flightInformation.id],
      invoice: [this.data.dataTicket.invoice.id],
      passengerName: [this.data.dataTicket.passenger,
        [Validators.required, Validators.minLength(10), Validators.maxLength(50),
          Validators.pattern('^([a-zA-Z]([ ]?[a-zA-Z])*)$')]],
      statusPayment: [this.data.dataTicket.statusPayment.name],
      appUser: ['', {
        validators:
          [Validators.required, Validators.maxLength(50),
            Validators.pattern('^[a-zA-Z0-9]+[@]([a-zA-Z]{3,7})[.]([a-z]{2,3})$')],
        asyncValidators: [this.ticketService.validateEmailUser()],
        updateOn: 'blur'
      }],
    });
  }

  edit() {
    this.formEdit.markAllAsTouched();
    if (this.formEdit.valid) {
      this.passengerEdit = this.formEdit.value.passengerName;
      this.appUserEdit = this.formEdit.value.appUser;
      this.ticketService.editTicketService(this.passengerEdit, this.appUserEdit, this.formEdit.value)
        .subscribe(
          data => {
          if (data.message === 'Succeed') {
            this.dialogRef.close();
          } else {
            this.dialogRef.close();
            const NOTICE = 'Sửa vé không thành công';
            const URL = 'http://localhost:4200/list-ticket';
            this.router.navigate(['notice-page', {message: NOTICE, path: URL}]).then(r => {
            });
          }
        },
          () => {
            const NOTICE = 'Lỗi hệ thống';
            this.router.navigate(['notice-page', {message: NOTICE}]).then(r => {
            });
          }
        );
    } else {
      for (const KEY of Object.keys(this.formEdit.controls)) {
        if (this.formEdit.controls[KEY].invalid) {
          const INVALID_CONTROL = this.el.nativeElement.querySelector('[formControlName="' + KEY + '"]');
          INVALID_CONTROL.focus();
          break;
        }
      }
    }
  }

  keyDownFunction(event) {
    if (event.keyCode === 13) {
      this.edit();
    }
  }
}
