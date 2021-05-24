import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {TicketService} from '../../service/ticket/ticket.service';
import {DeleteTicketComponent} from '../delete-ticket/delete-ticket.component';
import {EditTicketComponent} from '../edit-ticket/edit-ticket.component';
import {Router} from '@angular/router';

@Component({
  selector: 'app-list-ticket',
  templateUrl: './list-ticket.component.html',
  styleUrls: ['./list-ticket.component.css']
})
export class ListTicketComponent implements OnInit {
  protected ticketList;
  protected nativeWindow;

  constructor(
    protected ticketService: TicketService,
    protected dialog: MatDialog,
    protected router: Router
  ) {
    this.nativeWindow = ticketService.openNewWindow();
  }

  ngOnInit() {
    this.ticketService.getAllTicketService().subscribe(
      (data) => {
        this.ticketList = data;
      },
      () => {
        const NOTICE = 'Không tìm thấy trang ';
        this.router.navigate(['notice-page', {message: NOTICE}]).then(r => {});
       },
      () => {
      });
  }

  /**
   * Chau start
   *
   */
  openDialogDelete(id: any): void {
    this.ticketService.findTicketByIDService(id).subscribe(
      ticket => {
      if (ticket != null) {
        const DIALOG_REF = this.dialog.open(DeleteTicketComponent, {
          width: '650px',
          height: '360px',
          data: {dataTicket: ticket},
          disableClose: true
        });

        DIALOG_REF.afterClosed().subscribe(result => {
          this.ngOnInit();
        });
      } else {
        const NOTICE = 'Không tìm thấy vé.';
        const URL = 'http://localhost:4200/list-ticket';
        this.router.navigate(['notice-page', {message: NOTICE, path: URL}]).then(r => {
        });
      }
    },
      () => {
        this.error();
      }
    );
  }

  openDialogEdit(id: any): void {
    this.ticketService.findTicketByIDService(id).subscribe(
      ticket => {
      if (ticket != null) {
        const DIALOG_REF = this.dialog.open(EditTicketComponent, {
          width: '750px',
          height: '450px',
          data: {dataTicket: ticket},
          disableClose: true
        });

        DIALOG_REF.afterClosed().subscribe(result => {
          this.ngOnInit();
        });
      } else {
        const NOTICE = 'Không tìm thấy vé.';
        const URL = 'http://localhost:4200/list-ticket';
        this.router.navigate(['notice-page', {message: NOTICE, path: URL}]).then(r => {
        });
      }
    },
      () => {
        this.error();
      }
    );
  }

  openWindowPrint(idTicket: number): void {
    const NEW_WINDOW = this.nativeWindow.open('print-ticket');
    NEW_WINDOW.location = 'print-ticket/' + idTicket;
  }

  private error() {
    const NOTICE = 'Lỗi hệ thống.';
    const URL = 'http://localhost:4200/list-ticket';
    this.router.navigate(['notice-page', {message: NOTICE, path: URL}]).then(r => {
    });
  }
  /**
   * Chau end
   *
   */
}
