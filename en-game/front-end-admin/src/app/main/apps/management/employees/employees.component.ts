import { Component, OnInit, ViewChild, ViewEncapsulation } from "@angular/core";
import { Notification } from "../../../commons/Notification";
import { fuseAnimations } from "@fuse/animations";
import { ListEmployeeDTO } from "../../../dto/Employee/ListEmployeeDTO";
import { EmployeeExportExcelDTO } from "../../../dto/Employee/EmployeeExportExcelDTO";
import { ManagementEmployeesService } from "app/main/apps/management/employees/employees.service";
import { ActivatedRoute, Router } from "@angular/router";
import { Location } from "@angular/common";
import { HelperParam } from "../../../commons/helper-param";
import { MatMultiSort, MatMultiSortTableDataSource, TableData } from "ngx-mat-multi-sort";
import { HelperRegex } from "app/main/commons/helper-regex";
import { ExcelService } from "../../../commons/excel.service";
@Component({
  selector: "management-employees",
  templateUrl: "./employees.component.html",
  styleUrls: ["./employees.component.scss"],
  animations: fuseAnimations,
  encapsulation: ViewEncapsulation.None,
})
export class ManagementEmployeesComponent implements OnInit {
  lastPageSize: number = 5;
  table: TableData<ListEmployeeDTO>;
  @ViewChild(MatMultiSort) sort1: MatMultiSort;
  checkDataResponse: boolean = true;
  helperParam: HelperParam = new HelperParam();
  search: string = "";
  sort: string[] = [];
  pageSizeOptions: number = 0;
  arrPropertySortValid: string[] = [
    "id",
    "firstName",
    "midName",
    "lastName",
    "address",
    "birthday",
    "image",
    "active",
    "account.username",
  ];
  constructor(
    private employeesService: ManagementEmployeesService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location,
    private excelService: ExcelService,
  ) {
    this.helperParam.settingReloadSameURL(this.router);
  }

  // -----------------------------------------------------------------------------------------------------
  // @ Lifecycle hooks
  // -----------------------------------------------------------------------------------------------------

  /**
   * On init
   */
  async ngOnInit(): Promise<void> {
    // set event table
    await this.initTable();
    this.table.nextObservable.subscribe(() => {
      this.getData();
    });
    this.table.sortObservable.subscribe(() => {
      this.sortData();
    });
    this.table.previousObservable.subscribe(() => {
      this.getData();
    });
    this.table.sizeObservable.subscribe(() => {
      this.changePaginationInPage();
    });

    this.route.queryParams.subscribe((params) => {
      // eslint-disable-next-line dot-notation
      const paramURL: string = decodeURIComponent(this.route.snapshot["_routerState"].url);
      if (paramURL.includes("?")) {
        if (this.helperParam.checkParamPage(paramURL, this.arrPropertySortValid)) {
          if (params.page) {
            this.table.pageIndex = params.page - 1;
          }
          if (params.search) {
            this.search = params.search;
          }
          if (params.pageSize) {
            const pageSizeChange: number = params.pageSize;
            if (this.table.pageSize !== pageSizeChange) {
              this.table.pageIndex = 0;
            }
            this.table.pageSize = pageSizeChange;
            this.lastPageSize = pageSizeChange;
          }
          if (params.sort) {
            this.sort = params.sort;
          } else {
            this.sort = ["account.username,asc"];
          }
          setTimeout(() => {
            this.getParamSort();
          }, 0);
        } else {
          this.router.navigateByUrl("error/error-404");
        }
      } else {
        this.sort = ["account.username,asc"];
        this.renderPageInit();
      }
    });
  }

  // Setting For Table Init
  private initTable(): void {
    this.table = new TableData<ListEmployeeDTO>(
      [
        { id: "no", name: "No." },
        { id: "account.username", name: "Username" },
        { id: "firstName", name: "First Name" },
        { id: "midName", name: "Mid Name" },
        { id: "lastName", name: "Last Name" },
        { id: "birthday", name: "Birthday" },
        { id: "phone", name: "Phone" },
        { id: "active", name: "Active" },
      ],
      { defaultSortParams: ["account.username"], defaultSortDirs: ["asc"] },
    );
    this.table.pageIndex = 0;
  }

  // Reload Page With Init Data
  async renderPageInit(): Promise<void> {
    await this.initData();
    this.updateSort();
  }

  // Get Param Sort And Render Data Again When Change Sort
  async getParamSort(): Promise<void> {
    await this.initData(this.helperParam.getSortParams(this.sort), this.helperParam.getSortDirs(this.sort));
    this.updateSort();
  }

  // Init Data
  private initData(arrSortParam: string[] = [], arrSortDirs: string[] = []): void {
    // hack code layout paginator at combobox page size
    const result = document.getElementsByClassName("mat-paginator-page-size ng-star-inserted")[0];
    result.setAttribute("style", "width: 200px");
    result.getElementsByClassName("mat-paginator-page-size-label")[0].setAttribute("style", "width: inherit");
    // end hack code
    this.table.dataSource = new MatMultiSortTableDataSource(this.sort1);
    if (arrSortParam.length === 0) {
      arrSortParam.push("account.username");
    }
    if (arrSortDirs.length === 0) {
      arrSortDirs.push("asc");
    }
    this.table.sortDirs = arrSortDirs;
    this.table.sortParams = arrSortParam;
    this.table.updateColumNames([
      { id: "account.username", name: "Username" },
      { id: "firstName", name: "First Name" },
      { id: "midName", name: "Mid Name" },
      { id: "lastName", name: "Last Name" },
      { id: "birthday", name: "Birthday" },
      { id: "phone", name: "Phone" },
      { id: "active", name: "Active" },
    ]);
    this.getData();
  }

  // Call API Get Data For Table
  private getData(): void {
    this.helperParam.updateQueryParamInURL(this.location, this.route, this.router, this.sort, this.search, this.table);
    Notification.showWaiting();
    this.employeesService
      .getListEmployee(this.table.pageIndex, this.table.pageSize, this.search, this.sort)
      .then((data: any) => {
        this.table.totalElements = data.totalElements;
        this.table.data = data.content;
        this.checkDataResponse = data.content.length !== 0;
      })
      .catch((error) => {
        Notification.showErrorStatus(error);
      })
      .then(() => {
        Notification.clearWaitNoMessage();
      });
  }

  // Sort Data
  private sortData(): void {
    this.updateSort();
    this.getData();
  }

  // Update Sort When Change Param Sort
  private updateSort(): void {
    this.sort = [];
    for (let i = 0; i < this.table.sortParams.length; i++) {
      if (this.table.sortParams[0]) {
        this.sort.push(this.table.sortParams[i] + "," + this.table.sortDirs[i]);
      }
    }
    this.helperParam.updateQueryParamInURL(this.location, this.route, this.router, this.sort, this.search, this.table);
  }

  // Update Param In URL Not Reload Page
  // private updateQueryParamInURL(): void {
  //   let url: string = "";
  //   if (this.sort.length > 0) {
  //     url = this.router
  //       .createUrlTree([], {
  //         relativeTo: this.route,
  //         queryParams: {
  //           page: this.table.pageIndex + 1,
  //           pageSize: this.table.pageSize,
  //           search: this.search,
  //           sort: this.sort,
  //         },
  //       })
  //       .toString();
  //   } else {
  //     url = this.router
  //       .createUrlTree([], {
  //         relativeTo: this.route,
  //         queryParams: { page: this.table.pageIndex + 1, pageSize: this.table.pageSize, search: this.search },
  //       })
  //       .toString();
  //   }
  //   this.location.go(url);
  // }

  // Change Page Table When Change Pagination
  private changePaginationInPage(): void {
    if (this.lastPageSize !== this.table.pageSize) {
      this.table.pageIndex = 0;
    }
    this.getData();
  }

  // Search Employee
  public searchEmployee(): void {
    if (!HelperRegex.REG_SEARCH.test(this.search.trim())) {
      this.router.navigateByUrl("error/error-404");
    }
    this.table.pageIndex = 0;
    this.getData();
  }

  // Export List To Excel
  async exportListToExcel(): Promise<any> {
    const employees: EmployeeExportExcelDTO[] = [];
    Notification.showWaiting();
    await this.employeesService
      .getListExportToExcel(this.search)
      .then((data: any) => {
        data.forEach((element, ind) => {
          employees.push(new EmployeeExportExcelDTO(
            ind + 1, element.account.username, element.firstName, element.midName, element.lastName,
            element.address, element.birthday, element.image, element.active, element.startDate,
            element.gender, element.email, element.idCard, element.phone, element.account.lastLogin
          ));
        });
      })
      .catch((error) => {
        Notification.showErrorStatus(error);
      })
      .finally(() => {
        Notification.clearWaitNoMessage();
      });
    this.excelService.exportAsExcelFile(employees, "List_Employees");
  }
}
