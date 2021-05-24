import { Component, OnInit } from '@angular/core';
import {FormGroup, FormBuilder, Validators, FormControl} from '@angular/forms';
import {PromotionService} from '../../promotion.service';
import {Router} from '@angular/router';
import {min} from 'rxjs/operators';


@Component({
  selector: 'app-add-new',
  templateUrl: './add-new.component.html',
  styleUrls: ['./add-new.component.css']
})
export class AddNewComponent implements OnInit {
  public formAddNew: FormGroup;
  private listPromotion: any;
  min = '7:00';
  constructor(public formBuilder: FormBuilder,
              public promotionService: PromotionService,
              public router: Router) { }

  ngOnInit() {
    // @ts-ignore
    this.formAddNew = this.formBuilder.group({
      flight: ['', [Validators.required, Validators.pattern('^\\D{3}-\\D{3}$')]],
      airline: ['', [Validators.required]],
      flightNumber: ['', [Validators.required, Validators.pattern('^VJ \\d{3}|QH \\d{3}|VN \\d{3}$')]],
      departureDate: ['', [Validators.required]],
      departurePlace: ['', [Validators.required]],
      departureTime: ['', [Validators.required]],
      arrivalPlace: ['', [Validators.required]],
      arrivalTime: ['', [Validators.required]],
      price: ['', [Validators.required, Validators.pattern('^[1-9]\\d{5,6}$')]]
    });
  }
  addNewPromo() {
    console.log(this.formAddNew.value);
    this.promotionService.addNewPromo(this.formAddNew.value).subscribe(data => {
      this.router.navigateByUrl('promotion-list');
    });
  }
}
