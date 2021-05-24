import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {TicketService} from '../../service/ticket/ticket.service';
import {Ticket} from '../../model/ticket';
import {Router} from '@angular/router';

@Component({
  selector: 'app-delete-ticket',
  templateUrl: './delete-ticket.component.html',
  styleUrls: ['./delete-ticket.component.css']
})
export class DeleteTicketComponent implements OnInit {
  protected ticket: Ticket;
  protected idDelete: number;

  constructor(
    protected dialogRef: MatDialogRef<DeleteTicketComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private ticketService: TicketService,
    protected router: Router
  ) {
  }

  ngOnInit() {
    this.ticket = this.data.dataTicket;
    this.idDelete = this.data.dataTicket.id;
  }

  delete() {
    this.ticketService.deleteTicketService(this.idDelete).subscribe(
      data => {
      if (data.message === 'Succeed') {
        this.dialogRef.close();
      } else {
        this.dialogRef.close();
        const NOTICE = 'Xóa vé không thành công';
        const URL = 'http://localhost:4200/list-ticket';
        this.router.navigate(['notice-page', {message: NOTICE, path: URL}]).then(r => {
        });
      }
    },
      () => {
        const NOTICE = 'Lỗi hệ thống';
        this.router.navigate(['notice-page', {message: NOTICE}]).then(r => {
        });
      }
    );
  }
}
