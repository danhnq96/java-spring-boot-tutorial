import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {Router} from '@angular/router';
import {EmployeeService} from '../../service/employee/employee.service';

@Component({
  selector: 'app-delete-employee',
  templateUrl: './delete-employee.component.html',
  styleUrls: ['./delete-employee.component.css']
})
export class DeleteEmployeeComponent implements OnInit {
  protected employee;
  protected idDelete: any;

  constructor(
    protected dialogRef: MatDialogRef<DeleteEmployeeComponent>,
    protected router: Router,
    protected employeeService: EmployeeService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit() {
    this.employee = this.data.dataNeed;
    this.idDelete = this.data.dataNeed.id;
  }

  deleteEmployee() {
    this.employeeService.deleteEmployeeService(this.idDelete).subscribe(data => {
      if (data == null) {
        this.dialogRef.close();
      }
    },
      () => {
        const NOTICE = 'Xóa không thành công';
        this.router.navigate(['message-notice-employee', {message: NOTICE}]).then(r => {
          setTimeout(() => {
            this.router.navigateByUrl('list-employee');
          }, 1000
          );
        });
      });
  }
}
