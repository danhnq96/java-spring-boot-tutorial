export class RegisterDTO {
    fist_name: string;
    mid_name: string;
    last_name: string;
    phone: string;
    address: string;
    birthday: string;
    hire_date: string;
    gender: number;
    email: string;
    id_card: string;
    image: string;

    constructor(fist_name?: string, mid_name?: string, last_name?: string, phone?: string, address?: string, 
        birthday?: string, hire_date?: string, gender?: number, email?: string, idCard?: string, image?: string) {
        this.fist_name = fist_name;
        this.mid_name = mid_name;
        this.last_name = last_name;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
        this.hire_date = hire_date;
        this.gender = gender;
        this.email = email;
        this.id_card = idCard;
        this.image = image;
    }
}