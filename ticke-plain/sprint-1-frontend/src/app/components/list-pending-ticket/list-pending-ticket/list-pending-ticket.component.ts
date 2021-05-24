import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {TicketService} from '../../../service/ticket/ticket.service';
import {MatDialog} from '@angular/material/dialog';
import {CancelPendingTicketComponent} from '../cancel-pending-ticket/cancel-pending-ticket.component';
import {SuccessfullyPaidPendingTicketComponent} from "../successfully-paid-pending-ticket/successfully-paid-pending-ticket.component";
import {LoginService} from "../../../service/login.service";

@Component({
	selector: 'app-list-pending-ticket',
	templateUrl: './list-pending-ticket.component.html',
	styleUrls: ['./list-pending-ticket.component.css']
})
export class ListPendingTicketComponent implements OnInit {
	public ticketList = [];
	public payTicketList = [];
	public amountMoney = 0;
	
	public isChecked: boolean;
	public ticketStatusPaymentDTO = {id: 1, statusPaymentName: ''};
	
	public pageNumber = 1;
	public pageSize = 2;
	
	public checked = [];
	
	public pageSizeArr = [
		2,5,10
	];
	
	constructor(
		private ticketService: TicketService,
		private dialog: MatDialog,
		private loginService: LoginService
	) {
	}
	
	@ViewChild('paypalRef', {static: true}) private paypalRef: ElementRef;
	ngOnInit(): void {
		// console.log('this.ticketStatusPaymentDTO');
		// console.log(this.ticketStatusPaymentDTO);
		this.listPendingTicket();
		paypal.Buttons(
			{
				style: {
					shape: 'rect',
					color: 'gold',
					layout: 'horizontal',
					label: 'paypal',
					tagline: true,
					height: 50
				},
				
				createOrder: (data, actions) => {
					// console.log('createOrder');
					// This function sets up the details of the transaction,
					// including the amount and line item details
					return actions.order.create({
						purchase_units: [
							{
								amount: {
									value: this.amountMoney,
									currency_code: 'USD'
								}
							}
						]
					});
				},
				onCancel: function (data) {
					console.log('onCancel');
					// @ts-ignore
					$("#refreshData").click();
				},
				onApprove: (data, actions) => {
					return actions.order.capture().then(details => {
						console.log('Transaction completed');
						// alert("Transaction completed");
						// @ts-ignore
						$("#paypalStatusPayment").click();
					});
				},
				onError: (data, actions) => {
					console.log('Transaction error');
					// @ts-ignore
					$("#refreshData").click();
				}
				
			}
		).render(this.paypalRef.nativeElement);
	}
	
	listPendingTicket() {
		this.ticketService.findAllPendingTicket().subscribe(value => {
			this.ticketList = value;
			console.log('this.ticketList');
			console.log(this.ticketList);
		});
	}
	
	onCheckboxChange($event: Event, ticket: any, index: any) {
		// console.log(' TicketCode: ' + ticketCode);
		// console.log($event.target.checked);
		
		// @ts-ignore
		this.isChecked = $event.target.checked;
		this.checked[index]=this.isChecked;
		console.log(this.checked);
		console.log(ticket.ticketCode + ' ' + this.isChecked);
		let ticketCode = ticket.ticketCode;
		console.log('ticketCode');
		console.log(ticketCode);
		console.log('index');
		console.log(index);
		if (this.isChecked) {
			this.payTicketList.push(ticket);
			
			this.amountMoney += ticket.flightInformation.price / 23000;
			console.log('this.amountMoney');
			console.log(this.amountMoney);
			console.log(this.payTicketList);
		} else {
			// console.log('ticket');
			// console.log(ticket);
			// console.log('ticket.id');
			// console.log(ticket.id);
			this.payTicketList = this.payTicketList.filter(value => value !== ticket);
			this.amountMoney -= ticket.flightInformation.price / 23000;
			// this.renderPaypalButton();
			console.log('this.amountMoney');
			console.log(this.amountMoney);
			console.log(this.payTicketList);
		}
	}
	
	setTicketStatusPaymentToPaid() {
		for (let i = 0; i < this.payTicketList.length; i++) {
			this.ticketStatusPaymentDTO.id = this.payTicketList[i].id;
			this.ticketStatusPaymentDTO.statusPaymentName = 'Paid';
			console.log('this.ticketStatusPaymentDTO');
			console.log(this.ticketStatusPaymentDTO);
			this.ticketService.setTicketStatusPayment(this.ticketStatusPaymentDTO.id, this.ticketStatusPaymentDTO).subscribe(value => {
				console.log('setToPaid ' + i);
			});
		}
		this.openSuccessfullyPaidDialogTicket(this.payTicketList);
	}
	
	openSuccessfullyPaidDialogTicket(payTicketList: any): void {
		this.ticketService.sentEmail(this.loginService.currentUserValue.id).subscribe();
		const dialogRef = this.dialog.open(SuccessfullyPaidPendingTicketComponent, {
			width: '500px',
			data: {dataSuccessfullyPaid: payTicketList},
			disableClose: true
		});
		
		dialogRef.afterClosed().subscribe(result => {
			this.refreshData();
		});
		
	}
	
	refreshData() {
		this.setPageNumberTo1();
		this.ticketList = [];
		this.payTicketList = [];
		this.amountMoney = 0;
		this.checked = [];
		this.listPendingTicket();
		console.log('refresh data');
		console.log(this.ticketList);
	}
	
	setPageNumberTo1() {
		this.pageNumber = 1;
	}
	
	openCancelDialogTicket(ticket: any): void {
		const dialogRef = this.dialog.open(CancelPendingTicketComponent, {
			width: '500px',
			data: {dataCancel: ticket},
			disableClose: true
		});
		
		dialogRef.afterClosed().subscribe(result => {
			this.refreshData();
		});
	}
	
}
