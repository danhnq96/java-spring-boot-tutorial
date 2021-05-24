import * as dayjs from "dayjs";
import { Component, ElementRef, OnInit, ViewChild, ViewEncapsulation } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Notification } from "../../../commons/Notification";
import { finalize } from "rxjs/operators";
import { HelperParam } from "../../../commons/helper-param";
import { fuseAnimations } from "@fuse/animations";
import { ManagementEmployeeService } from "app/main/apps/management/employee/employee.service";
import { ActivatedRoute, ParamMap, Router } from "@angular/router";
import { EmployeeDTO } from "app/main/dto/Employee/EmployeeDTO";
import { formatDate } from "@angular/common";
import { DateAdapter, MAT_DATE_FORMATS } from "@angular/material/core";
import { AppDateAdapter, APP_DATE_FORMATS } from "../../../commons/format-datepicker";
import { AngularFireStorage, AngularFireStorageReference } from "@angular/fire/storage";
import { AngularFireDatabase } from "@angular/fire/database";
import { Helper } from "../../../commons/helper";
import { HelperRegex } from "app/main/commons/helper-regex";
import * as customParseFormat from "dayjs/plugin/customParseFormat";
import { ErrorValidateServer } from "app/main/models/ErrorValidateServer";
import { ErrorSqlServer } from "app/main/models/ErrorSqlServer";
import { EmployeeFailDTO } from "app/main/dto/Employee/EmployeeFailDTO";
dayjs.extend(customParseFormat);
import { ExcelService } from "../../../commons/excel.service";
import Notiflix from "notiflix";
import { TokenStorageService } from "app/main/auth/token-storage.service";
import { AccountDTO } from "app/main/dto/Employee/AccountDTO";
import { EmployeeChatDTO } from "app/main/dto/Chat/EmployeeChatDTO";
import { ChatContentDTO } from "app/main/dto/Chat/ChatContentDTO";
import { TypingActiveEmployeeChat } from "app/main/dto/Chat/TypingActiveEmployeeChat";
@Component({
  selector: "management-employee",
  templateUrl: "./employee.component.html",
  styleUrls: ["./employee.component.scss"],
  encapsulation: ViewEncapsulation.None,
  animations: fuseAnimations,
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS },
  ],
})
export class ManagementEmployeeComponent implements OnInit {
  private imgDefault =
    "https://firebasestorage.googleapis.com/v0/b/endgame-eproject.appspot.com/o/Employees%2Fimage_none.png?alt=media&token=c07b3f80-983e-4ec9-aca2-2eb7030e76b9";
  pageType: string;
  myDate: Date;
  employeeForm: FormGroup;
  employee: EmployeeDTO = new EmployeeDTO();
  helperParam: HelperParam = new HelperParam();
  helper: Helper = new Helper();
  imgChange: any = null;
  listNewEmployees: EmployeeDTO[];
  checkCurrentProfile: boolean;
  excelHeaders: string[] = [
    "no.",
    "firstName",
    "midName",
    "lastName",
    "address",
    "birthday",
    "startDate",
    "gender",
    "email",
    "idCard",
    "phone",
    "cause",
  ];
  templateToExcel: string[][] = [this.excelHeaders, []];
  @ViewChild("UploadFileInput") uploadFileInput: ElementRef;
  myFileName = "Select File";
  fileExcelExist = false;
  genders = [
    { value: 0, viewValue: "Male" },
    { value: 1, viewValue: "Female" },
    { value: 2, viewValue: "Unknow" },
  ];

  statusAccount = [
    { value: true, viewValue: "Active" },
    { value: false, viewValue: "Inactive" },
  ];

  /**
   * Constructor
   *
   * @param {EcommerceProductService} _ecommerceProductService
   * @param {FormBuilder} _formBuilder
   * @param {Location} _location
   * @param {MatSnackBar} _matSnackBar
   */
  constructor(
    private employeeService: ManagementEmployeeService,
    private formBuilder: FormBuilder,
    private storage: AngularFireStorage,
    private route: ActivatedRoute,
    private router: Router,
    private excelService: ExcelService,
    private tokenService: TokenStorageService,
    private firebaseDB: AngularFireDatabase,
  ) {
    this.employeeForm = this.createEmployeeForm();
  }

  // -----------------------------------------------------------------------------------------------------
  // @ Lifecycle hooks
  // -----------------------------------------------------------------------------------------------------

  /**
   * On init
   */
  ngOnInit(): void {
    Notification.showWaiting();
    this.myDate = new Date();
    this.myDate.setFullYear(this.myDate.getFullYear() - 18);
    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      const id = paramMap.get("id");
      if (this.helperParam.checkIdIsValid(id)) {
        this.pageType = id.trim() === "new" ? "new" : "edit";
        if (this.pageType === "edit") {
          this.employeeService
            .getEmployee(parseInt(id, 10))
            .then((data) => {
              this.employee = data;
              this.checkCurrentProfile = this.tokenService.getUsername() === this.employee.account.username;
            })
            .catch(() => {
              this.router.navigateByUrl("error/error-404");
            })
            .then(() => {
              this.employeeForm = this.createEmployeeForm();
            });
        } else {
          this.employee.image = this.imgDefault;
          this.employee.gender = 0;
          this.employee.active = true;
        }
      } else {
        this.router.navigateByUrl("error/error-404");
      }
    });
    Notification.clearWaitNoMessage();
  }

  // -----------------------------------------------------------------------------------------------------
  // @ Public methods
  // -----------------------------------------------------------------------------------------------------

  /**
   * Create product form
   *
   * @returns {FormGroup}
   */
  createEmployeeForm(): FormGroup {
    return this.formBuilder.group({
      firstName: [this.employee.firstName, [Validators.required, Validators.pattern(HelperRegex.REG_NAME)]],
      midName: [this.employee.midName, Validators.pattern(HelperRegex.REG_MID_NAME)],
      lastName: [this.employee.lastName, [Validators.required, Validators.pattern(HelperRegex.REG_NAME)]],
      address: [this.employee.address, [Validators.required, Validators.pattern(HelperRegex.REG_ADDRESS)]],
      birthday: [this.employee.birthday, Validators.required],
      active: [this.employee.active],
      startDate: [this.employee.startDate, Validators.required],
      gender: [this.employee.gender, Validators.required],
      email: [this.employee.email, Validators.pattern(HelperRegex.REG_EMAIL)],
      lastLogin: [this.employee.account.lastLogin],
      idCard: [this.employee.idCard, [Validators.required, Validators.pattern(HelperRegex.REG_ID_CARD)]],
      phone: [this.employee.phone, [Validators.required, Validators.pattern(HelperRegex.REG_PHONE)]],
      myFileName: [],
    });
  }

  // Event For Input
  public eventKeyUpEnterSubmitForm(): void {
    if (!this.employeeForm.invalid) {
      if (this.pageType === "edit") {
        this.saveEmployee();
      } else {
        this.addEmployee();
      }
    }
  }

  /**
   * Save Employee
   */
  public saveEmployee(): void {
    Notification.showWaiting();
    if (!this.employeeForm.invalid) {
      // format date
      if (this.employee.birthday && this.employee.startDate) {
        this.employee.birthday = formatDate(this.employeeForm.get("birthday").value, "yyyy-MM-dd", "en-US");
        this.employee.startDate = formatDate(this.employeeForm.get("startDate").value, "yyyy-MM-dd", "en-US");
      }
      const filePath = `Employees/${this.employee.account.username}`;
      const fileRef: AngularFireStorageReference = this.storage.ref(filePath);
      if (this.imgChange) {
        this.storage
          .upload(filePath, this.imgChange)
          .snapshotChanges()
          .pipe(
            finalize(() => {
              fileRef
                .getDownloadURL()
                .toPromise()
                .then((url) => {
                  this.employee.image = url;
                })
                .then(() => {
                  // post data to server
                  this.employeeService
                    .saveEmployee(this.employee)
                    .then(() => {
                      Notification.showSuccess("Saved!!!", "", "OK");
                    })
                    .catch((error) => {
                      if (error.error.name === "Duplicate!") {
                        Notification.showErrorMessage("Error", error.error.content);
                      } else {
                        Notification.showErrorMessage("Error", "Error SQL");
                      }
                    });
                })
                .catch((err) => {
                  Notification.showErrorStatus(err);
                });
            }),
          )
          .subscribe();
      } else {
        // post data to server
        this.employeeService
          .saveEmployee(this.employee)
          .then(() => {
            Notification.showSuccess("Saved!!!", "", "OK");
          })
          .catch((error) => {
            if (error.error.name === "Duplicate!") {
              Notification.showErrorMessage("Error", error.error.content);
            } else {
              Notification.showErrorMessage("Error", "Error SQL");
            }
          });
      }
    } else {
      Notification.showSuccess("Error Form Is Invalid!!!");
    }
  }

  /**
   * Add Employee
   */
  public addEmployee(): void {
    // format date
    Notification.showWaiting();
    if (!this.employeeForm.invalid) {
      this.employee.birthday = formatDate(this.employeeForm.get("birthday").value, "yyyy-MM-dd", "en-US");
      this.employee.startDate = formatDate(this.employeeForm.get("startDate").value, "yyyy-MM-dd", "en-US");
      this.employeeService
        .addEmployee(this.employee)
        .then((data) => {
          Notification.showSuccess("Added!!!", "", "OK");
          this.employee.account.username = data.content;
          this.addNewUserInFireBase(this.employee, null);
          this.employeeForm.reset();
          this.employeeForm.get("active").setValue(true);
        })
        .catch((error) => {
          Notification.clearWaitNoMessage();
          console.log(error);
          Notification.showErrorMessage(error.error, error.error);
        });
    }
  }

  // Add Employee By File
  async addListEmployeeByFile() {
    if (this.fileExcelExist) {
      if (this.listNewEmployees.length > 100) {
        Notification.showErrorMessage("Error!!!", "Length of list can not larger 100");
        this.fileExcelExist = false;
      } else {
        let checkFaild: boolean = true;
        let listEmployeeFail: EmployeeFailDTO[] = [];
        Notification.showWaiting();
        let errValidateServers: ErrorValidateServer[] = [];
        this.listNewEmployees.forEach((emp, i) => {
          const msg: string = this.checkValidListEmployeeAndGetMessageError(emp);
          const empFail: EmployeeFailDTO = new EmployeeFailDTO();
          Object.assign(empFail, emp);
          // empFail.birthday = dayjs(empFail.birthday, "YYYY-MM-ĐD").format("DD/MM/YYYY");
          // empFail.startDate = dayjs(empFail.startDate, "YYYY-MM-ĐD").format("DD/MM/YYYY");
          empFail.birthday = empFail.birthday.replace(/-/g, "/");
          empFail.startDate = empFail.startDate.replace(/-/g, "/");
          empFail.index = i + 1;
          if (msg) {
            checkFaild = false;
            empFail.cause = msg;
          } else {
            empFail.cause = "";
          }
          listEmployeeFail.push(empFail);
        });
        if (checkFaild) {
          listEmployeeFail = [];
          this.listNewEmployees = await this.formatListEmployee(this.listNewEmployees);
          this.employeeService
            .addListEmployee(this.listNewEmployees)
            .then((data) => {
              Notification.showSuccess(
                "Finish!",
                "Add " + this.listNewEmployees.length + " employees successfully!!!",
                "OK",
              );
              data.forEach(element => {
                const empChat: EmployeeChatDTO = element.employeeFireBaseDTO;
                this.addNewUserInFireBase(null, empChat);
              });
            })
            .catch((error) => {
              console.log(error);
              const err: string = error.error.message;
              if (err && err.includes("addListEmployee.emps[")) {
                const errs: string[] = err.split(";,");
                for (let i = 0; i < errs.length; i++) {
                  const errServer: ErrorValidateServer = new ErrorValidateServer();
                  errServer.no =
                    parseInt(errs[i].substring(errs[i].indexOf("[") + 1, errs[i].indexOf("]")).trim(), 10) + 1;
                  errServer.content = errs[i].substring(errs[i].indexOf(":") + 1).trim();
                  errValidateServers.push(errServer);
                }
              }
              // chưa xử lý lỗi thêm account lên firebase
              if (error.status === 900) {
                const listErrSql: ErrorSqlServer[] = error.error;
                errValidateServers = this.helper.addErrorSqlToListErrorServer(errValidateServers, listErrSql);
                console.log(errValidateServers);
              }
              if (error.status === 901) {
                Notification.clearWaitNoMessage();
                Notification.showErrorMessage(error.error[0].name, error.error[0].content);
                this.fileExcelExist = false;
              } else {
                if (errValidateServers) {
                  errValidateServers.forEach((element, ind) => {
                    const empFail: EmployeeFailDTO = new EmployeeFailDTO();
                    Object.assign(empFail, this.listNewEmployees[element.no - 1]);
                    empFail.birthday = dayjs(empFail.birthday, "YYYY-MM-DD").format("DD-MM-YYYY").replace(/-/g, "/");
                    empFail.startDate = dayjs(empFail.startDate, "YYYY-MM-DD").format("DD-MM-YYYY").replace(/-/g, "/");
                    empFail.cause = element.content;
                    empFail.index = ind + 1;
                    listEmployeeFail.push(empFail);
                  });
                }
                Notification.clearWaitNoMessage();
                this.showConfirmDownLoadExcel(listEmployeeFail, this.listNewEmployees.length - listEmployeeFail.length);
                this.fileExcelExist = false;
              }
            });
          this.fileExcelExist = false;
        } else {
          this.fileExcelExist = false;
          Notification.clearWaitNoMessage();
          this.showConfirmDownLoadExcel(listEmployeeFail, 0);
        }
        this.myFileName = "Select File";
      }
    }
  }

  // Set Disable Date In alender
  public dateFilter = (date: Date) => date <= this.myDate;

  // Set Default Date
  public setDefaultDate(name): void {
    if (!this.employeeForm.get(name).value || this.employeeForm.get(name).value === "") {
      if (name === "startDate") {
        this.employeeForm.get(name).setValue(new Date());
      } else {
        this.employeeForm.get(name).setValue(this.myDate);
      }
    }
  }

  // Get Image When Change Image
  public getImage(ev: any): void {
    if (this.helperParam.checkImage(ev)) {
      const reader: FileReader = new FileReader();
      reader.onload = (e: any) => (this.employee.image = e.target.result);
      reader.readAsDataURL(ev.target.files[0]);
      this.imgChange = ev.target.files[0];
    } else {
      Notification.showErrorMessage("Error File !", "Please choose file is image or file size < 2 MB");
    }
  }

  // Reset Form Before Added Employee
  private resetFormBeforeAddedEmployee(): void {
    this.employee = new EmployeeDTO();
    this.employee.image = this.imgDefault;
    this.employee.gender = 0;
    this.employee.active = true;
    this.employeeForm = this.createEmployeeForm();
  }

  // Upload File Excel
  public async fileChangeEvent(fileInput: any): Promise<void> {
    if (fileInput.target.files && fileInput.target.files[0]) {
      this.myFileName = fileInput.target.files[0].name;
      const reader: FileReader = new FileReader();
      reader.readAsDataURL(fileInput.target.files[0]);

      await this.excelService.readFileExcel(fileInput.target.files[0], fileInput).then((data) => {
        this.listNewEmployees = this.formatDateListEmloyees(data);
      });
      // Reset File Input to select Same file again
      this.uploadFileInput.nativeElement.value = "";
      this.fileExcelExist = true;
    } else {
      this.fileExcelExist = false;
      this.myFileName = "Select File";
    }
  }

  private checkValidListEmployeeAndGetMessageError(emp: EmployeeDTO): string {
    let msg: string = "";
    if (!emp.firstName || !HelperRegex.REG_NAME.test(emp.firstName)) {
      msg += "First name is required and less than 30 characters and no special characters. ";
    }
    if (!HelperRegex.REG_MID_NAME.test(emp.midName)) {
      msg += " Mid name can empty or less than 30 characters and no special characters. ";
    }
    if (!emp.lastName || !HelperRegex.REG_NAME.test(emp.lastName)) {
      msg += "Last name is required and less than 30 characters and no special characters. ";
    }
    if (!emp.address || !HelperRegex.REG_ADDRESS.test(emp.address)) {
      msg += "Address is required and less than 190 characters and no special characters. ";
    }
    if (!emp.idCard || !HelperRegex.REG_ID_CARD.test(emp.idCard)) {
      msg += "Id card is required and minimum 9 and maximum 20 characters no special characters. ";
    }
    if (!emp.phone) {
      msg += "Phone is required and starting with 0 or +, minimum 9 and maximum 30 characters. ";
    }
    if (!emp.email || !HelperRegex.REG_EMAIL.test(emp.email)) {
      msg += "Email is invalid. ";
    }
    if (!emp.birthday || !HelperRegex.REG_DATE.test(emp.birthday)) {
      msg += "Birthday is required and correct format dd/mm/yyyy. ";
    }
    if (HelperRegex.REG_DATE.test(emp.birthday)) {
      if (
        emp.birthday.substring(3, 5) === "02" &&
        (emp.birthday.substring(0, 2) === "31" || emp.birthday.substring(0, 2) === "30")
      ) {
        msg += "Birthday is invalid at month 02 day over 29. ";
      }
      if (
        dayjs(dayjs(new Date()), "DD-MM-YYYY").diff(dayjs(emp.birthday.replace(/\//g, "-"), "DD-MM-YYYY"), "day") < 6582
      ) {
        msg += "Birthday is invalid. Employees must be over 18 years old. ";
      }
    }
    if (!emp.startDate || !HelperRegex.REG_DATE.test(emp.startDate)) {
      msg += "Hire Date is required and correct format dd/mm/yyyy. ";
    }
    if (HelperRegex.REG_DATE.test(emp.startDate) && HelperRegex.REG_DATE.test(emp.birthday)) {
      if (
        emp.startDate &&
        dayjs(emp.startDate.replace(/\//g, "-"), "DD-MM-YYYY").diff(
          dayjs(emp.birthday.replace(/\//g, "-"), "DD-MM-YYYY"),
          "day",
        ) < 6582
      ) {
        msg += "Hire Date is invalid. Employees must be over 18 years old. ";
      }
      if (!emp.startDate || !HelperRegex.REG_DATE.test(emp.startDate)) {
        msg += "Hire Date is required and correct format dd/mm/yyyy. ";
      }
      if (
        emp.startDate.substring(3, 5) === "02" &&
        (emp.startDate.substring(0, 2) === "31" || emp.startDate.substring(0, 2) === "30")
      ) {
        msg += "Hire Date is invalid at month 02 day over 29. ";
      }
    }
    const gender = emp.gender.toLocaleString().toLowerCase();
    if (emp.gender !== 0 && emp.gender !== 1 && emp.gender !== 2) {
      if (gender !== "male" && gender !== "female" && gender !== "unknow") {
        msg += 'Gender is required and gender equal "Male/0" or "Female/1" or "Unknow/2". ';
      }
    }
    return msg;
  }

  private formatListEmployee(listEmps: EmployeeDTO[]): EmployeeDTO[] {
    for (let i: number = 0; i < listEmps.length; i++) {
      const emp: EmployeeDTO = listEmps[i];
      emp.birthday = dayjs(emp.birthday.replace(/\//g, "-"), "DD-MM-YYYY").format("YYYY-MM-DD");
      emp.startDate = dayjs(emp.startDate.replace(/\//g, "-"), "DD-MM-YYYY").format("YYYY-MM-DD");
      emp.image = this.imgDefault;
    }
    return listEmps;
  }

  private showConfirmDownLoadExcel(listEmployeeFail: EmployeeFailDTO[], sizeComplete: number): void {
    Notiflix.Confirm.Init({ titleColor: "#ff0000", messageFontSize: "15px" });
    Notiflix.Confirm.Show(
      "Employee is invalid!!!",
      "Add successfully " + sizeComplete + ". Do you download " + listEmployeeFail.length + " employees error?",
      "Yes",
      "No",
      () => {
        this.excelService.exportAsExcelFile(listEmployeeFail, "List_Employee_Invalid");
      },
      () => {
        // No button callback
      },
    );
  }
  // public writeFileExcelEmployeeFail(): void {
  //     const ws: XLSX.WorkSheet = XLSX.utils.aoa_to_sheet(this.templateToExcel);
  //     const wb: XLSX.WorkBook = XLSX.utils.book_new();
  //     XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
  //     XLSX.writeFile(wb, "List_Employee_Invalid" + ".xlsx");
  // }
  private formatDateListEmloyees(listEmps: EmployeeDTO[]): EmployeeDTO[] {
    listEmps.forEach((emp) => {
      if (typeof emp.birthday !== "string") {
        emp.birthday = dayjs(Math.round((Number(emp.birthday) - 25569) * 86400 * 1000)).format("DD-MM-YYYY");
      }
      if (typeof emp.startDate !== "string") {
        emp.startDate = dayjs(Math.round((Number(emp.startDate) - 25569) * 86400 * 1000)).format("DD-MM-YYYY");
      }
      if (!emp.midName) {
        emp.midName = "";
      }
      const gender: string = emp.gender.toString().toLocaleLowerCase();
      if (gender === "male") {
        emp.gender = 0;
      } else if (gender === "female") {
        emp.gender = 1;
      } else {
        emp.gender = 2;
      }
    });
    return listEmps;
  }

  // reset password
  public resetPassword(): void {
    Notiflix.Confirm.Show('Reset Password Confirm', 'Are you sure reset password?', 'Yes', 'No',
      () => {
        Notification.showWaiting("Sending password to the mail ...");
        const acc: AccountDTO = new AccountDTO();
        acc.username = this.employee.account.username;
        this.employeeService
          .resetPassword(acc)
          .then(() => {
            Notification.showSuccess("Reset Password Complete", "A new password has been sent to the mail " + this.employee.email, "OK");
          })
          .catch((error) => {
            console.log(error);
            Notification.showErrorMessage(error.error.name, error.error.content);
          });
      });
  }

  // add user to firebase
  async addNewUserInFireBase(emp: EmployeeDTO = null, empChatDTO: EmployeeChatDTO = null) {
    let empChat: EmployeeChatDTO = new EmployeeChatDTO();
    if (emp) {
      empChat.id = emp.account.username;
      empChat.avatar = emp.image;
      if (emp.midName !== "") {
        empChat.name = emp.lastName + " " + emp.midName + " " + emp.firstName;
      } else {
        empChat.name = emp.lastName + " " + emp.firstName;
      }
      empChat.status = "offline";
    } else {
      empChat = empChatDTO;
    }
    await this.firebaseDB.database.ref('users/').once('value', async snapshot => {
      if (snapshot.val()) {
        await this.firebaseDB.database.ref('users/' + empChat.id).set(empChat);
        this.addCountsAndChatsInFireBase(empChat);
      } else {
        this.employeeService
          .getInfoAdmin()
          .then((employeeFireBaseDTO) => {
            this.firebaseDB.database.ref('users/' + employeeFireBaseDTO.id).set(employeeFireBaseDTO);
            this.firebaseDB.database.ref('users/' + empChat.id).set(empChat);
          }).then(() => {
            this.addCountsAndChatsInFireBase(empChat);
          })
          .catch((error) => {
            Notification.showErrorMessage(error, error);
          });
      }
    })
    // this.firebaseDB.database.ref('users/length').set(empChat);
    // this.firebaseDB.database.ref('users/' + empChat.id).set(empChat);
  }

  private addCountsAndChatsInFireBase(empChat: EmployeeChatDTO): void {
    this.firebaseDB.database.ref('users/').once('value', resp => {
      const users = this.snapshotToArray(resp);
      this.firebaseDB.database.ref('counts/users').set(users.length);
      users.forEach(e => {
        if (e.id !== empChat.id) {
          const chatDTO: ChatContentDTO = new ChatContentDTO();
          chatDTO.message = "";
          chatDTO.time = "";
          chatDTO.who = "";
          chatDTO.status = "";
          chatDTO.id = "";
          this.firebaseDB.database.ref(`chats/${e.id}/${empChat.id}/default`).set(chatDTO);
          this.firebaseDB.database.ref(`counts/${e.id}/${empChat.id}`).set(new TypingActiveEmployeeChat());
          this.firebaseDB.database.ref(`chats/${empChat.id}/${e.id}/default`).set(chatDTO);
          this.firebaseDB.database.ref(`counts/${empChat.id}/${e.id}`).set(new TypingActiveEmployeeChat());
        }
      })
    });
  }

  private snapshotToArray = (snapshot: any) => {
    const returnArr = [];

    snapshot.forEach((childSnapshot: any) => {
      const item = childSnapshot.val();
      item.key = childSnapshot.key;
      returnArr.push(item);
    });

    return returnArr;
  };
}
