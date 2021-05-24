import {Component, ElementRef, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {DatePipe} from '@angular/common';
import {Employee} from '../../model/employee/employee.class';
import {EmployeeService} from '../../service/employee/employee.service';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {
  private formCreateNew: FormGroup;
  private pipe: DatePipe;
  private employee: Employee;
  private maxDate = new Date(2012, 11, 23);
  private minDate = new Date(1970, 0, 1);

  constructor(
    private employeeService: EmployeeService,
    private formBuilder: FormBuilder,
    private router: Router,
    private el: ElementRef
  ) {
  }

  ngOnInit() {
    this.pipe = new DatePipe('en-US');
    this.formCreateNew = this.formBuilder.group({
      employeeCode: ['',
        [Validators.required, Validators.pattern('^(NV-)[0-9]{4}$')],
        [this.employeeService.validateEmployeeCode()], {updateOn: 'blur'}
      ],
      fullName: ['',
        [Validators.required, this.employeeService.validateWhiteSpace,
          this.employeeService.validateSpecialCharacter, Validators.maxLength(40)
        ]],
      birthday: ['', [Validators.required]],
      gender: ['', [Validators.required]],
      email: ['', [Validators.required,
        Validators.pattern('^[a-zA-Z0-9]+[@]([a-zA-Z]{3,7})[.]([a-z]{2,3})$'),
        Validators.maxLength(40)], [this.employeeService.validateEmployeeEmail()], {updateOn: 'blur'}
      ],
      password: ['', [Validators.required, Validators.maxLength(20),
        this.employeeService.validateWhiteSpace]],
      phoneNumber: ['', [Validators.required, Validators.pattern('^((\\d+){10})$'),
        Validators.maxLength(12)]],
      role: ['', [Validators.required]]
    });
  }

  createNewEmployee() {
    this.formCreateNew.markAllAsTouched();
    if (this.formCreateNew.valid) {
    this.employee = Object.assign({}, this.formCreateNew.value);
    this.employee.birthday = this.pipe.transform(this.employee.birthday, 'dd-MM-yyyy');
    this.employeeService.createNewEmployeeService(this.employee).subscribe(data => {
        this.router.navigateByUrl('list-employee').then(_ => {
        });
      }, () => {
        const NOTICE = 'Thêm mới không thành công';
        this.router.navigate(['message-notice-employee', {message: NOTICE}]).then(r => {
          setTimeout(() => {
              this.router.navigateByUrl('list-employee');
            }, 1000
          );
        });
      });
    } else {
      for (const KEY of Object.keys(this.formCreateNew.controls)) {
        if (this.formCreateNew.controls[KEY].invalid) {
          const INVALID_CONTROL = this.el.nativeElement.querySelector('[formControlName="' + KEY + '"]');
          INVALID_CONTROL.focus();
          break;
        }
      }
    }
  }

  resetAll() {
    if (this.formCreateNew.valid) {
      this.formCreateNew.reset();
    }
    this.ngOnInit();
  }

  keyDownFunction(event) {
    if (event.keyCode === 13) {
      this.createNewEmployee();
    }
  }
}

