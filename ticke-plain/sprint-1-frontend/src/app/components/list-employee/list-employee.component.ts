import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material';
import {EmployeeService} from '../../service/employee/employee.service';
import {DeleteEmployeeComponent} from '../delete-employee/delete-employee.component';
import {LoginService} from '../../service/login.service';

@Component({
  selector: 'app-list-employee',
  templateUrl: './list-employee.component.html',
  styleUrls: ['./list-employee.component.css']
})
export class ListEmployeeComponent implements OnInit {
  private list = [];
  private checkList = 'true';
  keywordSearch: '';
  p: number;
  private accountId;
  private role;

  constructor(
    private employeeService: EmployeeService,
    private dialog: MatDialog,
    private router: Router,
    private loginService: LoginService
  ) {
  }

  ngOnInit() {
    this.accountId = this.loginService.currentUserValue.id;
    this.employeeService.getRole(this.accountId).subscribe(data => {
      this.role = data.name;
    });
    this.employeeService.getAllEmployeeService().subscribe(data => {
      this.list = data;
    }, () => {
        const NOTICE = 'Không tìm thấy trang ';
        this.router.navigate(['message-notice-employee', {message: NOTICE}]).then(r => {});
      },
      () => {
      });
  }

  openDialogDelete(id: any): void {
    this.employeeService.findEmployeeByIdService(id).subscribe(varialble => {
      const dialogRef = this.dialog.open(DeleteEmployeeComponent, {
        width: '750px',
        data: {dataNeed: varialble},
        disableClose: true
      });

      dialogRef.afterClosed().subscribe(result => {
        this.ngOnInit();
      });
    });
  }

  searchEmployee(attribute: any) {
    if (attribute === 'fullName' && this.keywordSearch != '') {
      this.employeeService.searchEmployeeFullNameService(this.keywordSearch).subscribe(data => {
        this.list = data;
        if (this.list.length === 0) {
          this.checkList = 'false';
        }
      });
    } else if (attribute === 'email' && this.keywordSearch != '') {
      this.employeeService.searchEmployeeEmailService(this.keywordSearch).subscribe(data => {
        this.list = data;
        if (this.list.length === 0) {
          this.checkList = 'false';
        }
      });
    } else if (attribute === 'phoneNumber' && this.keywordSearch != '') {
      this.employeeService.searchEmployeePhoneNumberService(this.keywordSearch).subscribe(data => {
        this.list = data;
        if (this.list.length === 0) {
          this.checkList = 'false';
        }
      });
    } else if (attribute == 'choose' && this.keywordSearch != '') {
      alert('Vui lòng chọn thuộc tính cần tìm!');
      this.employeeService.getAllEmployeeService().subscribe(data => {
        this.ngOnInit();
        if (this.list.length === 0) {
          this.checkList = 'false';
        }
      });
    } else if (attribute != 'choose' && this.keywordSearch == '') {
      alert('Vui lòng nhập từ khóa cần tìm!');
      this.employeeService.getAllEmployeeService().subscribe(data => {
        this.ngOnInit();
      });
    } else {
      alert('Vui lòng chọn thuộc tính và nhập từ khóa tìm kiếm');
    }
  }

  resetSearch() {
    this.keywordSearch = '';
    this.checkList = 'true';
    this.ngOnInit();
  }
}
