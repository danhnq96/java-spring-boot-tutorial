import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import html2canvas from 'html2canvas';
import {jsPDF} from 'jspdf';
import {InvoiceService} from '../../service/invoice.service';
import {ActivatedRoute} from '@angular/router';
@Component({
  selector: 'app-detail-invoice',
  templateUrl: './detail-invoice.component.html',
  styleUrls: ['./detail-invoice.component.css']
})
export class DetailInvoiceComponent implements OnInit {
  @ViewChild('content', {static: true}) content: ElementRef;
  private invoice;
  private invoiceId;
  private appUser;
  private ticketList;
  private appUserId;
  private list: any = [];
  constructor(private invoiceService: InvoiceService,  public activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activateRoute.params.subscribe(data => {
      this.invoiceId = data.id;
      // tslint:disable-next-line:no-shadowed-variable
      this.invoiceService.getListInvoiceById(this.invoiceId).subscribe(data => {
        this.list = data;
      });
    });
  }

  generatePDF() {
    const data = document.getElementById('contentToConvert');
    html2canvas(data, {scrollY: -window.scrollY}).then(canvas => {
      const imgWidth = 208;
      const imgHeight = canvas.height * imgWidth / canvas.width;
      const contentDataURL = canvas.toDataURL('image/png');
      const pdf = new jsPDF('p', 'mm', 'a4');
      const position = 0;
      pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight);
      pdf.save('newPDF.pdf');
    });
  }
}
