import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {jsPDF} from 'jspdf';
import html2canvas from 'html2canvas';
import {InvoiceService} from '../../service/invoice/invoice.service';
import {ActivatedRoute} from '@angular/router';
import {AppUserService} from '../../service/app-user.service';
import {TicketService} from '../../service/ticket/ticket.service';
import {LoginService} from '../../service/login.service';

@Component({
  selector: 'app-invoice-detail',
  templateUrl: './invoice-detail.component.html',
  styleUrls: ['./invoice-detail.component.css']
})
export class InvoiceDetailComponent implements OnInit {
  @ViewChild('content', {static: true}) content: ElementRef;
  private invoice;
  private invoiceId;
  private appUser;
  private ticketList;
  private appUserId;

  constructor(private invoiceService: InvoiceService, private active: ActivatedRoute, private appUserService: AppUserService,
              private ticketService: TicketService, private loginService: LoginService) {
    this.appUserId = this.loginService.currentUserValue.id;
  }

  ngOnInit() {
    this.active.params.subscribe(data => {
      this.invoiceId = data.id;
      console.log(this.invoiceId);
    });
    this.ticketService.findAllTicketByInvoiceId(this.invoiceId).subscribe(dataTicket => {
      this.ticketList = dataTicket;
    });
    this.invoiceService.findInvoiceById(this.invoiceId).subscribe(dataInvoice => {
      this.invoice = dataInvoice;
      console.log(this.invoice);
    });
    this.appUserService.findAppUserById(this.appUserId).subscribe(dataUser => {
      this.appUser = dataUser;
    });

  }

  public generatePDF(): void {
    const data = document.getElementById('content');
    html2canvas(data, {scrollY: -window.scrollY}).then(canvas => {
      const imgWidth = 208;
      const imgHeight = canvas.height * imgWidth / canvas.width;
      const contentDataURL = canvas.toDataURL('image/png');
      const pdf = new jsPDF('p', 'mm', 'a4');
      const position = 0;
      pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight);
      if (this.ticketList.length !== 0) {
        pdf.save(this.invoice.formCode);
      }
    });
  }
}
