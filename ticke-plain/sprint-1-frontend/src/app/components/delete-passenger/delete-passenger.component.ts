import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {PassengerService} from '../../service/passenger/passenger.service';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-delete-passenger',
  templateUrl: './delete-passenger.component.html',
  styleUrls: ['./delete-passenger.component.css']
})
export class DeletePassengerComponent implements OnInit {
  public eleName;
  public eleId;

  constructor(public dialogRef: MatDialogRef<DeletePassengerComponent>,
              @Inject(MAT_DIALOG_DATA)
              public data: any,
              public passengerService: PassengerService,
              public toast: ToastrService,
              private router: Router) { }

  ngOnInit() {
    console.log(this.data.data1);
    this.eleName = this.data.data1.fullName;
    this.eleId = this.data.data1.id;
  }

  deleteOnClick() {
    this.passengerService.delete(this.eleId).subscribe(data => {
      console.log(data);

      if (data === 1) {
        this.router.navigate(['/passenger/list-Passenger'], {queryParams: {delete_msg: 'Delete successfully!', si: true}});
        this.toast.success('Thao Tác Thành Công' , 'Thông Báo');
        this.dialogRef.close();
      } else {
        this.toast.error('Thao Tác Thất Bại' , 'Thông Báo');
        alert('that bai');
      }
    });
  }

}
