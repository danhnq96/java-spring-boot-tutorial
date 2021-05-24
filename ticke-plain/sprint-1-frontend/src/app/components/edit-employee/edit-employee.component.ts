import {Component, ElementRef, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {DatePipe} from '@angular/common';
import {EmployeeService} from '../../service/employee/employee.service';
import {Employee} from '../../model/employee/employee.class';

@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
  styleUrls: ['./edit-employee.component.css']
})
export class EditEmployeeComponent implements OnInit {
  private formEdit: FormGroup;
  private pipe: DatePipe;
  private employee: Employee;
  private idNeed;
  protected roleEdit;
  protected genderEdit;
  private maxDate = new Date(2012, 11, 23);
  private minDate = new Date(1970, 0, 1);

  constructor(
    private formBuilder: FormBuilder,
    private employeeService: EmployeeService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private el: ElementRef
  ) {
  }

  ngOnInit() {
    this.pipe = new DatePipe('en-US');
    this.formEdit = this.formBuilder.group({
      id: [''],
      employeeCode: [''],
      fullName: ['',
        [Validators.required, this.employeeService.validateWhiteSpace,
          this.employeeService.validateSpecialCharacter, Validators.maxLength(40)
        ]],
      birthday: ['', [Validators.required]],
      gender: ['', [Validators.required]],
      email: [''],
      phoneNumber: ['', [Validators.required, Validators.pattern('^((\\d+){10})$'),
        Validators.maxLength(12)]],
      role: ['']
    });
    this.activatedRoute.params.subscribe(data => {
      this.idNeed = data.id;
      this.employeeService.findEmployeeByIdService(this.idNeed).subscribe(dataEdit => {
        this.formEdit.patchValue(dataEdit);
        this.roleEdit = dataEdit.appAccount.appRole.name;
        if (dataEdit.gender === false) {
          this.genderEdit = 'Nu';
        } else {
          this.genderEdit = 'Nam';
        }
      });
    });
  }

  editEmployee() {
    this.formEdit.markAllAsTouched();
    if (this.formEdit.valid) {
      if (this.genderEdit === 'Nu') {
        this.formEdit.value.gender = '0';
      } else {
        this.formEdit.value.gender = '1';
      }
      if (this.formEdit.value.role == '') {
        this.formEdit.value.role = this.roleEdit;
      }
      this.employee = Object.assign({}, this.formEdit.value);
      this.employee.birthday = this.pipe.transform(this.employee.birthday, 'dd-MM-yyyy');
      this.employeeService.editEmployeeService(this.employee, this.employee.id).subscribe(data => {
            this.router.navigateByUrl('list-employee').then(_ => {
            });
          },
          () => {
            const NOTICE = 'Sửa không thành công';
            this.router.navigate(['message-notice-employee', {message: NOTICE}]).then(r => {
              setTimeout(() => {
                  this.router.navigateByUrl('list-employee');
                }, 1000
              );
            });
          });
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
      this.editEmployee();
    }
  }
}
