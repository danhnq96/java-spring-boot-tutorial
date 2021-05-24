import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConsultantService {

  public consultantStatus = false;

  constructor() {
  }

  public setConsultantStatus(status: boolean) {
    this.consultantStatus = status;
    this.getConsultantStatus();
  }

  public getConsultantStatus() {
    return this.consultantStatus;
  }
}
