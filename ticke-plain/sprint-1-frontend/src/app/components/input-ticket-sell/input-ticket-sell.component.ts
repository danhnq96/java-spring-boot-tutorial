import {Component, ElementRef, OnInit} from '@angular/core';
import {TicketService} from '../../service/ticket/ticket.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {randomString} from '../../utils/RandomUtils';
import {ActivatedRoute, Router} from '@angular/router';
import {LoginService} from '../../service/login.service';
import {FlightInformation} from '../../model/flightInformation';
import {SpinnerOverlayService} from '../../service/animations/spinner-overlay.service';
import {finalize} from 'rxjs/operators';

@Component({
  selector: 'app-input-ticket-sell',
  templateUrl: './input-ticket-sell.component.html',
  styleUrls: ['./input-ticket-sell.component.css']
})
export class InputTicketSellComponent implements OnInit {
  protected flightInformationDeparture = FlightInformation;
  protected flightInformationArrival = FlightInformation;
  protected idFlightDeparture = 0;
  protected idFlightArrival = 0;
  protected idEmployee;
  protected checkArrival = 'false';
  protected formCreate: FormGroup;
  protected totalPriceSell: number;
  protected nativeWindow;
  protected message = 'Nothing';
  protected messageSave: string;

  constructor(
    protected ticketService: TicketService,
    protected loginService: LoginService,
    protected formBuilder: FormBuilder,
    protected router: Router,
    private activedRouter: ActivatedRoute,
    private spinnerOverlayService: SpinnerOverlayService,
    private el: ElementRef
  ) {
    this.nativeWindow = ticketService.openNewWindow();
  }

  ngOnInit() {
    this.activedRouter.params.subscribe(data => {
      this.idFlightDeparture = data.idFlightDeparture;
      this.idFlightArrival = data.idFlightArrival;
    });
    if (this.idFlightDeparture != 0) {
      this.idEmployee = this.loginService.currentUserValue.id;
      this.ticketService.findFlightInformationByIDService(this.idFlightDeparture).subscribe(
        (data) => {
          if (data != null) {
            this.flightInformationDeparture = data;
          } else {
            this.error();
          }
        },
        () => {
          this.error();
        },
        () => {
          if (this.idFlightArrival != 0) {
            this.ticketService.findFlightInformationByIDService(this.idFlightArrival).subscribe(
              data => {
                if (data != null) {
                  this.flightInformationArrival = data;
                  this.checkArrival = 'true';
                } else {
                  this.error();
                }
              },
              () => {
                this.error();
              }
            );
          }
        }
      );
      const BOOKING_CODE = randomString(10);
      this.formCreate = this.formBuilder.group({
        id: [''],
        passengerName: ['',
          [Validators.required, Validators.maxLength(150),
            Validators.pattern('^([a-zA-Z]([ ]?[a-zA-Z])*)([,]([a-zA-Z]([ ]?[a-zA-Z])*)*)*$')]],
        adults: ['', [Validators.required, Validators.pattern('^([0-9]+)$'),
          Validators.min(1), Validators.maxLength(2)]],
        babies: ['', [Validators.required,
          Validators.pattern('^([0-9]+)$'),
          Validators.maxLength(2)]],
        priceDeparture: ['', [Validators.required, Validators.pattern('^([0-9]+([.][0-9]+)?)$')]],
        priceArrival: [0, [Validators.required, Validators.pattern('^([0-9]+([.][0-9]+)?)$')]],
        statusCheckin: [''],
        ticketCode: [''],
        booking: [BOOKING_CODE],
        employee: [this.idEmployee],
        flightInformation: [''],
        invoice: [''],
        statusPayment: [''],
        appUser: ['', {
          validators:
            [Validators.required,
              Validators.maxLength(40),
              Validators.pattern('^[a-zA-Z0-9]+[@]([a-zA-Z]{3,7})[.]([a-z]{2,3})$')],
          asyncValidators: [this.ticketService.validateEmailUser()],
          updateOn: 'blur'
        }]
      });
    } else {
      this.error();
    }
  }

  save() {
    this.formCreate.markAllAsTouched();
    if (this.formCreate.valid) {
      this.spinnerOverlayService.show();
      this.ticketService.saveTicketService(this.idFlightDeparture,
        this.idFlightArrival, this.formCreate.value)
        .pipe(finalize(() => this.spinnerOverlayService.hide()))
        .subscribe(
          (data) => {
            this.messageSave = data.message;
            if (this.messageSave === 'Save ticket and send mail succeed') {
              this.router.navigateByUrl('list-ticket').then(_ => {
              });
            } else if (this.messageSave === 'Send mail failed') {
              const NOTICE = 'Lưu vé thành công nhưng gởi mail không thành công. Đề nghị kiểm tra kết nối mạng.';
              const URL = 'http://localhost:4200/list-ticket';
              this.router.navigate(['notice-page', {message: NOTICE, path: URL}]).then(r => {
              });
            } else if (this.messageSave === 'Save ticket failed') {
              const NOTICE = 'Lưu vé không thành công. Đề nghị kiểm tra kết nối mạng.';
              const URL = 'http://localhost:4200/list-ticket';
              this.router.navigate(['notice-page', {message: NOTICE, path: URL}]).then(r => {
              });
            } else {
              this.error();
            }
          },
          () => {
            this.error();
          }
        );
    } else {
      for (const KEY of Object.keys(this.formCreate.controls)) {
        if (this.formCreate.controls[KEY].invalid) {
          const INVALID_CONTROL = this.el.nativeElement.querySelector('[formControlName="' + KEY + '"]');
          INVALID_CONTROL.focus();
          break;
        }
      }
    }
  }

  saveAndPrint() {
    this.formCreate.markAllAsTouched();
    if (this.formCreate.valid) {
      this.spinnerOverlayService.show();
      this.ticketService.saveTicketService(this.idFlightDeparture, this.idFlightArrival, this.formCreate.value)
        .pipe(finalize(() => this.spinnerOverlayService.hide()))
        .subscribe(
          (data) => {
            this.messageSave = data.message;
            if (this.messageSave === 'Save ticket and send mail succeed') {
              const NEW_WINDOW = this.nativeWindow.open('print-ticket-two-way');
              NEW_WINDOW.location = 'print-ticket-two-way/' + this.formCreate.value.booking + '/'
                + this.formCreate.value.passengerName + '/' + this.idFlightDeparture + '/'
                + this.idFlightArrival;
              this.router.navigateByUrl('list-ticket').then(r => {
              });
            } else if (this.messageSave === 'Send mail failed') {
              const NOTICE = 'Lưu vé thành công nhưng gởi mail không thành công. Đề nghị kiểm tra kết nối mạng.';
              const URL = 'http://localhost:4200/list-ticket';
              this.router.navigate(['notice-page', {message: NOTICE, path: URL}]).then(r => {
              });
            } else if (this.messageSave === 'Save ticket failed') {
              const NOTICE = 'Lưu vé không thành công. Đề nghị kiểm tra kết nối mạng.';
              const URL = 'http://localhost:4200/list-ticket';
              this.router.navigate(['notice-page', {message: NOTICE, path: URL}]).then(r => {
              });
            } else {
              this.error();
            }
          },
          () => {
            this.error();
          }
        );
    } else {
      for (const key of Object.keys(this.formCreate.controls)) {
        if (this.formCreate.controls[key].invalid) {
          const invalidControl = this.el.nativeElement.querySelector('[formControlName="' + key + '"]');
          invalidControl.focus();
          break;
        }
      }
    }
  }

  priceSell(priceDeparture: string, priceArrival: string) {
    if (priceArrival.length === 0) {
      // tslint:disable-next-line:radix
      this.totalPriceSell = Number.parseInt(priceDeparture);
    } else if (priceDeparture.length === 0) {
      // tslint:disable-next-line:radix
      this.totalPriceSell = Number.parseInt(priceArrival);
    } else {
      // tslint:disable-next-line:radix
      this.totalPriceSell = Number.parseInt(priceDeparture) + Number.parseInt(priceArrival);
    }
  }

  private error() {
    const NOTICE = 'Lỗi hệ thống.';
    const URL = 'http://localhost:4200/list-ticket';
    this.router.navigate(['notice-page', {message: NOTICE, path: URL}]).then(r => {
    });
  }

  keyDownFunction(event) {
    if (event.keyCode === 13) {
      this.saveAndPrint();
    }
  }
}
