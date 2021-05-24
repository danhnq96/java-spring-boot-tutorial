import { Component, OnDestroy, OnInit, ViewEncapsulation } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { Subject } from 'rxjs';
import { FuseConfigService } from '@fuse/services/config.service';
import { fuseAnimations } from '@fuse/animations';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material/core';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../commons/format-datepicker';
import { RegisterService } from './register.service';
import { RegisterDTO } from '../../../dto/Employee/RegisterEmployeeDTO';
import { formatDate } from "@angular/common";
import { Notification } from '../../../commons/Notification';

@Component({
    selector: 'register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss'],
    encapsulation: ViewEncapsulation.None,
    animations: fuseAnimations,
    providers: [
        { provide: DateAdapter, useClass: AppDateAdapter },
        { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
    ]
})
export class RegisterComponent implements OnInit {
    // define variable
    registerForm: FormGroup;
    _myDate: Date;
    empRgt: RegisterDTO;
    errStatus: number;
    birthday;
    // create list gender
    genders = [
        { value: 'Male', viewValue: 'Male' },
        { value: 'Female', viewValue: 'Female' },
        { value: 'Unknow', viewValue: 'Unknow' }
    ];

    // Private
    private _unsubscribeAll: Subject<any>;
    constructor(
        private _fuseConfigService: FuseConfigService,
        private _formBuilder: FormBuilder,
        private reg: RegisterService,
    ) {
        this.empRgt = new RegisterDTO();
        // Configure the layout
        this._fuseConfigService.config = {
            layout: {
                navbar: {
                    hidden: true
                },
                toolbar: {
                    hidden: true
                },
                footer: {
                    hidden: true
                },
                sidepanel: {
                    hidden: true
                }
            }
        };


        // Set the private defaults
        this._unsubscribeAll = new Subject();

    }
    // -----------------------------------------------------------------------------------------------------
    // @ Lifecycle hooks
    // -----------------------------------------------------------------------------------------------------

    /**
     * On init
     */
    ngOnInit(): void {
        this._myDate = new Date();
        this._myDate.setFullYear(this._myDate.getFullYear() - 18);
        this.registerForm = this._formBuilder.group({
            first_name: ['', [Validators.required, Validators.pattern(/^[\p{L}\s]{1,30}$/u)]],
            last_name: ['', [Validators.required, Validators.pattern(/^[\p{L}\s]{1,30}$/u)]],
            mid_name: ['', Validators.pattern(/^[\p{L}\s]{1,30}$/u)],
            phone: ['', [Validators.required, Validators.pattern('(\\+|0)(\\d){8,30}')]],
            address: ['', [Validators.required, Validators.pattern(/^[\p{L}\s\d.,@/\\]{1,190}$/u)]],
            birthday: ['', Validators.required],
            hire_date: ['', Validators.required],
            email: ['', [Validators.required, Validators.pattern(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)]],
            id_card: ['', [Validators.required, Validators.pattern(/^[\p{L}\s\d]{9,20}$/u)]],
            gender: ['', Validators.required]
            // password: ['', Validators.required],
            // passwordConfirm: ['', [Validators.required, confirmPasswordValidator]]
        });

        // Update the validity of the 'passwordConfirm' field
        // when the 'password' field changes
        // this.registerForm.get('password').valueChanges
        //     .pipe(takeUntil(this._unsubscribeAll))
        //     .subscribe(() => {
        //         this.registerForm.get('passwordConfirm').updateValueAndValidity();
        //     });
    }

    // set disabled date in calender
    dateFilter = (date: Date) => date <= this._myDate;

    // set default date
    setDefaultDate() {
        this.registerForm.get('birthday').setValue(this._myDate);
    }

    // event Enter Submit
    onEnter() {
        if (!this.registerForm.invalid) {
            this.onSubmit();
        }
    }

    // submit register
    onSubmit() {
        Notification.showWaiting();
        // format date
        if (this.empRgt.birthday && this.empRgt.hire_date) {
            this.empRgt.birthday = formatDate(this.registerForm.get('birthday').value, "yyyy-MM-dd", "en-US");
            this.empRgt.hire_date = formatDate(this.registerForm.get('hire_date').value, "yyyy-MM-dd", "en-US");
        }

        // convert to gender
        if (this.empRgt.gender) {
            if (this.empRgt.gender.toString() === "Male") {
                this.empRgt.gender = 1;
            } else if (this.empRgt.gender.toString() === "Female") {
                this.empRgt.gender = 0;
            } else {
                this.empRgt.gender = 2;
            }
        }

        // execute api
        this.reg.execute_register(this.empRgt).then(data => {
            Notification.showSuccess("Register successfully");
            // this.router.navigateByUrl('/register');
        }).catch(error => {
            Notification.showErrorStatus(error);
            console.log("ERROR: ", error);
        })
    }

    /**
     * On destroy
     */
    ngOnDestroy(): void {
        // Unsubscribe from all subscriptions
        this._unsubscribeAll.next();
        this._unsubscribeAll.complete();
    }
}

/**
//  * Confirm password validator
//  *
//  * @param {AbstractControl} control
//  * @returns {ValidationErrors | null}
//  */
// export const confirmPasswordValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {

//     if (!control.parent || !control) {
//         return null;
//     }

//     const password = control.parent.get('password');
//     const passwordConfirm = control.parent.get('passwordConfirm');

//     if (!password || !passwordConfirm) {
//         return null;
//     }

//     if (passwordConfirm.value === '') {
//         return null;
//     }

//     if (password.value === passwordConfirm.value) {
//         return null;
//     }

//     return { passwordsNotMatching: true };
// };
