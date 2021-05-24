import {Component, ElementRef, Inject, OnChanges, OnInit, QueryList, SimpleChanges, ViewChildren} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {LuggageService} from '../../../service/luggage/luggage.service';
import {LoginService} from '../../../service/login.service';
import {Router} from '@angular/router';
import {LoginComponent} from '../../login/login.component';
import {FlightService} from '../../../service/flight-information/flight.service';
import {PassengerService} from '../../../service/passenger/passenger.service';
import {BookingService} from '../../../service/booking/booking.service';
import {randomString} from '../RandomString';
import {TicketService} from '../../../service/ticket/ticket.service';
import {SpinnerOverlayService} from '../../../service/animations/spinner-overlay.service';
import {MatInput} from '@angular/material';

@Component({
  selector: 'app-info-passenger-booking-ticket',
  templateUrl: './info-passenger-booking-ticket.component.html',
  styleUrls: ['./info-passenger-booking-ticket.component.css']
})

export class InfoPassengerBookingTicketComponent implements OnInit {
  isLinear = true;
  flightOne;
  flightTwo;
  quantityTicketOne;
  quantityTicketTwo;
  passengerFormOne: FormGroup;
  passengerFormTwo: FormGroup;
  listLuggage;
  maxBirthdayOfHuman: Date;
  minBirthdayOfChildren: Date;
  maxBirthdayOfChildren: Date;

  constructor(private dialogRef: MatDialogRef<InfoPassengerBookingTicketComponent>,
              @Inject(MAT_DIALOG_DATA) private data: any,
              private el: ElementRef,
              private formBuilder: FormBuilder,
              private luggageService: LuggageService,
              private loginService: LoginService,
              private router: Router,
              private dialog: MatDialog,
              private flightService: FlightService,
              private passengerService: PassengerService,
              private ticketService: TicketService,
              public spinnerOverlayService: SpinnerOverlayService) {
    this.maxBirthdayOfHuman = new Date(Date.parse(new Date().toString()) - 18 * 1000 * 3600 * 24 * 365);
    this.maxBirthdayOfChildren = new Date(Date.parse(new Date().toString()) - 2 * 1000 * 3600 * 24 * 365);
    this.minBirthdayOfChildren = new Date(Date.parse(new Date().toString()) - 12 * 1000 * 3600 * 24 * 365);
  }

  ngOnInit() {
    this.luggageService.getAll().subscribe(data => {
      this.listLuggage = data;
    });
    this.flightOne = this.data.flightDetailOne;
    this.flightTwo = this.data.flightDetailTwo;
    this.quantityTicketOne = this.data.quantityOne;
    this.quantityTicketTwo = this.data.quantityTwo;
    // Hiển thị bản nhập thông tin cho số lượng người lớn trong chuyến tàu đi
    this.passengerFormOne = this.formBuilder.group({
      passengerForm: this.formBuilder.array([])
    });
    for (let i = 0; i < this.quantityTicketOne; i++) {
      this.passengerForm(this.passengerFormOne).push(this.createPassenger());
    }
    // Hiển thị nhập thông tin tương ứng với số lượng người lớn cho chuyến tàu về
    if (this.flightTwo !== '') {
      this.passengerFormTwo = this.formBuilder.group({
        passengerForm: this.formBuilder.array([])
      });
      for (let i = 0; i < this.quantityTicketTwo; i++) {
        this.passengerForm(this.passengerFormTwo).push(this.createPassenger());
      }
    }
  }

  cancel() {
    this.dialogRef.close();
  }

  createPassenger(): FormGroup {
    return this.formBuilder.group({
      fullName: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(50),
        Validators.pattern('^([A-Z][a-z]*\\s?)+$')]],
      birthday: ['', [Validators.required]],
      address: [''],
      email: ['', [Validators.required, Validators.pattern('^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$')]],
      gender: ['true'],
      phoneNumber: ['', [Validators.required, Validators.pattern('^((\\+\\(84\\))|(0))9[0-9]{8}$')]],
      identityNumber: ['', [Validators.required, Validators.pattern('^[0-9]{9}$')]],
      luggage: [null, Validators.required],
      passengerList: this.formBuilder.array([])
    });
  }

  createPassengerChildren(): FormGroup {
    return this.formBuilder.group({
      fullName: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(50),
        Validators.pattern('^([A-Z][a-z]*\\s?)+$')]],
      birthday: ['', [Validators.required]],
      address: [''],
      email: ['', Validators.pattern('^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$')],
      gender: ['true'],
      phoneNumber: ['', Validators.pattern('^((\\+\\(84\\))|(0))9[0-9]{8}$')],
      identityNumber: ['', [Validators.pattern('^[0-9]{9}$')]],
      luggage: [null, Validators.required]
    });
  }

  passengerForm(form: FormGroup): FormArray {
    return form.get('passengerForm') as FormArray;
  }

  passengerFormIndex(form: FormGroup, index): FormGroup {
    return this.passengerForm(form).controls[index] as FormGroup;
  }

  passengerList(form: FormGroup, indexPassengerForm): FormArray {
    const formTemp = this.passengerForm(form).controls[indexPassengerForm];
    return formTemp.get('passengerList') as FormArray;
  }

  passengerListIndex(form: FormGroup, indexPassengerForm, index): FormGroup {
    return this.passengerList(form, indexPassengerForm).controls[index] as FormGroup;
  }

  addItem(form: FormGroup, indexPassengerForm): void {
    this.passengerList(form, indexPassengerForm).push(this.createPassengerChildren());
  }

  deleteItem(form: FormGroup, indexPassengerForm, indexPassengerList): void {
    this.passengerList(form, indexPassengerForm).removeAt(indexPassengerList);
  }


  pay() {
    if (this.loginService.currentUserValue === null) {
      const dialogRef = this.dialog.open(LoginComponent, {
        width: '80%',
        height: '100%',
        disableClose: true,
        hasBackdrop: true
      });
      dialogRef.afterClosed().subscribe();
    } else {
      this.spinnerOverlayService.show('Đang chuyển hướng đến lịch sử thanh toán');
      const daoBooking = {
        bookingCode: '',
        bookingDate: '',
        appUserId: 0,
      };
      const daoTicket = {
        ticketCode: [],
        passengerName: [],
        priceDeparture: [],
        priceArrival: [],
        flightInformation: [],
        daoBooking,
        countDeparture: 0,
        countArrival: 0
      };
      // Chiều đi
      for (let i = 0; i < this.passengerForm(this.passengerFormOne).length; i++) {
        daoTicket.passengerName.push(this.passengerFormIndex(this.passengerFormOne, i).value.fullName);
        daoTicket.priceDeparture.push(this.flightOne.price * (1 + (10 / 100)));
        daoTicket.priceArrival.push(null);
        daoTicket.countDeparture++;
        daoTicket.ticketCode.push(randomString(10));
        for (let k = 0; k < this.passengerList(this.passengerFormOne, i).length; k++) {
          daoTicket.passengerName.push(this.passengerListIndex(this.passengerFormOne, i, k).value.fullName);
          daoTicket.priceDeparture.push(this.flightOne.price * (75 / 100) * (1 + (10 / 100)));
          daoTicket.priceArrival.push(null);
          daoTicket.countDeparture++;
          daoTicket.ticketCode.push(randomString(10));
        }
        this.passengerService.addPassenger(this.passengerFormIndex(this.passengerFormOne, i).value).subscribe();
      }
      daoTicket.flightInformation.push(this.flightOne.id);
      if (this.flightTwo !== '') {
        for (let i = 0; i < this.passengerForm(this.passengerFormTwo).length; i++) {
          daoTicket.passengerName.push(this.passengerFormIndex(this.passengerFormTwo, i).value.fullName);
          daoTicket.priceArrival.push(this.flightOne.price * (1 + (10 / 100)));
          daoTicket.priceDeparture.push(null);
          daoTicket.countArrival++;
          daoTicket.ticketCode.push(randomString(10));
          for (let k = 0; k < this.passengerList(this.passengerFormTwo, i).length; k++) {
            daoTicket.passengerName.push(this.passengerListIndex(this.passengerFormTwo, i, k).value.fullName);
            daoTicket.priceArrival.push(this.flightOne.price * (75 / 100) * (1 + (10 / 100)));
            daoTicket.priceDeparture.push(null);
            daoTicket.countArrival++;
            daoTicket.ticketCode.push(randomString(10));
          }
          this.passengerService.addPassenger(this.passengerFormIndex(this.passengerFormTwo, i).value).subscribe();
        }
        daoTicket.flightInformation.push(this.flightTwo.id);
      }
      daoBooking.bookingCode = randomString(10);
      daoBooking.bookingDate = new Date().toLocaleDateString();
      daoBooking.appUserId = this.loginService.currentUserValue.id;
      daoTicket.daoBooking = daoBooking;
      this.passengerService.sentEmail(this.loginService.currentUserValue.id, daoBooking.bookingCode).subscribe();
      this.ticketService.addTicketAndBooking(daoTicket).subscribe(data => {
        }, error => {
        },
        () => {
          this.spinnerOverlayService.hide();
          this.dialogRef.close();
// @ts-ignore
          setTimeout( this.router.navigateByUrl('/informationUser/list-pending-ticket' ) , 1000);
        });
    }
  }

}
