import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {FlightService} from '../../../service/flight-information/flight.service';
import {randomString} from '../RandomString';

@Component({
  selector: 'app-search-flight-information',
  templateUrl: './search-flight-information.component.html',
  styleUrls: ['./search-flight-information.component.css']
})
export class SearchFlightInformationComponent implements OnInit {
  listLocation = ['Đà Nẵng (DAD)', 'TP Hồ Chí Minh (SGN)', 'Phú Quốc (PQC)', 'Nha Trang (CXR)', 'Cần Thơ (VCA)'];
  // Vé chiều đi chiều về
  ways = [{id: 1, name: 'Một chiều'}, {id: 2, name: 'Khứ hồi'}];
  // Tìm kiếm theo
  find = [{id: 'id', name: 'Đề xuất'}, {id: 'price', name: 'Giá (Thấp đến cao)'}, {
    id: 'departure_time',
    name: 'Thời gian khởi hành'
  },
    {id: 'airline', name: 'Hãng hàng không'}];
  // Form
  formSearch: FormGroup;
  listOneWay = '';
  listReturn = '';
  minDepartureDate: Date;
  minArrivalDate: Date;

  constructor(
    private formBuilder: FormBuilder,
    private flightServiceService: FlightService,
  ) {
  }

  ngOnInit() {
    this.formSearch = this.formBuilder.group({
      way: ['1'],
      departure: ['', [Validators.required]],
      arrival: ['', [Validators.required]],
      departureDate: ['', [Validators.required]],
      arrivalDate: ['', [Validators.required]],
      sort: ['id'],
      quantity: ['']
    }, {validators : this.validatorDepartureAndArrival});
    this.minDepartureDate = new Date();
    this.minArrivalDate = new Date();
    console.log(randomString(10));
  }

  private takeInvalidOneWay(form: FormGroup): boolean {
    const checkDeparture = form.controls.departure.invalid;
    const checkDepartureDate = form.controls.departureDate.invalid;
    const checkArrival = form.controls.arrival.invalid;
    if (checkDepartureDate || checkDeparture || checkArrival) {
      return true;
    } else {
      return false;
    }
  }

  validatorDepartureAndArrival(form: AbstractControl) {
    const departure = form.get('departure').value;
    const arrival = form.get('arrival').value;
    if (departure === arrival) {
      return {validator: true};
    } else {
      return null;
    }
  }

  private takeInvalidReturn(form: FormGroup): boolean {
    const checkDeparture = form.controls.departure.invalid;
    const checkDepartureDate = form.controls.departureDate.invalid;
    const checkArrival = form.controls.arrival.invalid;
    const checkArrivalDate = form.controls.arrivalDate.invalid;
    if (checkArrivalDate || checkDepartureDate || checkDeparture || checkArrival) {
      return true;
    } else {
      return false;
    }
  }

  private takeListFlightOneWay(form: FormGroup) {
    const departureDate = new Date(form.controls.departureDate.value).toLocaleDateString();
    // Lấy list Flight đi
    this.flightServiceService.search(0, form.controls.departure.value, form.controls.arrival.value,
      departureDate, form.controls.sort.value).subscribe(data => {
      this.listOneWay = data;
      console.log(data);
    });
  }

  private takeListFlightReturn(form: FormGroup) {
    const departureDate = new Date(form.controls.departureDate.value).toLocaleDateString();
    const arrivalDate = new Date(form.controls.arrivalDate.value).toLocaleDateString();
    // Lấy list Flight đi
    this.flightServiceService.search(0, form.controls.departure.value, form.controls.arrival.value,
      departureDate, form.controls.sort.value).subscribe(data => {
      this.listOneWay = data;
      // Để lấy ngày tháng năm về theo dạng dd/mm/yyyy
      this.flightServiceService.search(0, form.controls.arrival.value, form.controls.departure.value,
        arrivalDate, form.controls.sort.value).subscribe(data1 => {
        this.listReturn = data1;
      });
    });
  }

  search(flightTableHTML) {
    console.log(this.formSearch.value);
    this.listOneWay = '';
    this.listReturn = '';
    flightTableHTML.wayChildren = this.formSearch.controls.way.value;
    flightTableHTML.sortChildren = this.formSearch.controls.sort.value;
    flightTableHTML.arrival = this.formSearch.controls.arrival.value;
    flightTableHTML.departure = this.formSearch.controls.departure.value;
    flightTableHTML.dateDeparture = this.formSearch.controls.departureDate.value;
    flightTableHTML.quantityInChildrenCpn = this.formSearch.controls.quantity.value;
    if (this.formSearch.controls.way.value === '1' && !this.takeInvalidOneWay(this.formSearch)) {
      this.takeListFlightOneWay(this.formSearch);
    } else if (this.formSearch.controls.way.value === '2' && !this.takeInvalidReturn(this.formSearch)) {
      flightTableHTML.dateArrival = this.formSearch.controls.arrivalDate.value;
      this.takeListFlightReturn(this.formSearch);
    } else {
      return;
    }
  }
}
