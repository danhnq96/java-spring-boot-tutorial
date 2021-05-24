
export class EmployeeExportExcelDTO {
  index: number;
  firstName: string;
  midName: string;
  lastName: string;
  address: string;
  birthday: string;
  image: string;
  active: boolean;
  startDate: string;
  gender: string;
  email: string;
  idCard: string;
  phone: string;
  username: string;
  lastLogin: string;

  constructor(
    index: number, username: string, firstName: string, midName: string, lastName: string,
    address: string, birthday: string, image: string, active: boolean, startDate: string,
    gender: string, email: string, idCard: string, phone: string, lastLogin: string
  ) {
    this.index = index;
    this.username = username;
    this.firstName = firstName;
    this.midName = midName;
    this.lastName = lastName;
    this.address = address;
    this.birthday = birthday;
    this.image = image;
    this.active = active;
    this.startDate = startDate;
    this.gender = this.convertGender(parseInt(gender, 10));
    this.email = email;
    this.idCard = idCard;
    this.phone = phone;
    this.lastLogin = lastLogin;
  }

  private convertGender(gender: number): string {
    switch (gender) {
      case 0:
        return "Male";
      case 1:
        return "Female";
      default:
        return "Unknow"
    }
  }

}
