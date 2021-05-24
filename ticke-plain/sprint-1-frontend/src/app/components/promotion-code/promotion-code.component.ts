import {Component, ElementRef, OnInit} from '@angular/core';
import {PromotionCodeService} from '../../service/promotion_code/promotion-code.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';



@Component({
  selector: 'app-promotion-code',
  templateUrl: './promotion-code.component.html',
  styleUrls: ['./promotion-code.component.css']
})
export class PromotionCodeComponent implements OnInit {
  public messageSuccess = '';
  public promotionCodeResult: any;
  public formSendEmail: FormGroup;
  public p: any;

  constructor(
    public formBuilder: FormBuilder,
    public promotionCodeService: PromotionCodeService,
    public router: Router,
    private el: ElementRef
  ) {
  }

  ngOnInit() {
    this.promotionCodeService.findAllPromotionCode().subscribe(data => {
      this.promotionCodeResult = data;
      console.log(this.promotionCodeResult);
    });
    // validate email frontend
    this.formSendEmail = this.formBuilder.group({
      emailPassgenger: ['', [Validators.required, Validators.email]],
    });
  }

  // loading , gửi email, thông báo nhận email thành công hoặc thất bại
  sendEmail() {
    if (this.formSendEmail.valid) {
      // this.spinnerOverlayService.show();
      this.promotionCodeService.sendEmail(this.formSendEmail.value.emailPassgenger)
        // .pipe(finalize(() => this.spinnerOverlayService.hide()))
        .subscribe(data => {
        console.log(data);
        this.messageSuccess = 'Bạn đã đăng ký email ' + this.formSendEmail.value.emailPassgenger + ' thành công.';
        setTimeout(() => {
          this.messageSuccess = '';
        }, 5000);
        this.formSendEmail.value.emailPassgenger = null;
        this.ngOnInit();
      });
    } else {
      this.messageSuccess = 'Bạn nhập chưa đúng định dạng email';
      setTimeout(() => {
        this.messageSuccess = '';
      }, 5000);
    }

  }
}
