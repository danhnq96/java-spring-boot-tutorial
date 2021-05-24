import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {PromotionService} from '../../promotion.service';
import {Router, ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  public formEdit: FormGroup;
  private promotionId: any;
  constructor(public formBuilder: FormBuilder,
              public promotionService: PromotionService,
              public router: Router,
              public activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.formEdit = this.formBuilder.group({
      id:  ['', [Validators.required]],
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
    this.activatedRoute.params.subscribe(data => {
      this.promotionId = data.id;
      // tslint:disable-next-line:no-shadowed-variable
      this.promotionService.getPromoById(this.promotionId).subscribe(data => {
        this.formEdit.patchValue(data);
      });
    });
  }
  updatePromo() {
    console.log(this.formEdit.value);
    this.promotionService.updatePromo(this.formEdit.value, this.promotionId).subscribe(data => {
      this.router.navigateByUrl('promotion-list');
    });
  }
  }
