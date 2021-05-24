import { Component, OnInit, ViewChild, ViewEncapsulation } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { ExcelService } from 'app/main/commons/excel.service';
import { HelperParam } from 'app/main/commons/helper-param';
import { ManagementMembersService } from './members.service';
import { MatMultiSort, MatMultiSortTableDataSource, TableData } from "ngx-mat-multi-sort";
import { fuseAnimations } from "@fuse/animations";
import { Location } from "@angular/common";
import { Notification } from "../../../commons/Notification";
import { ListMemberDTO } from "app/main/dto/Member/ListMemberDTO";
import { HelperRegex } from "app/main/commons/helper-regex";
import { MemberExcelDTO } from "app/main/dto/Member/MemberExcelDTO";
@Component({
  selector: 'management-members',
  templateUrl: './members.component.html',
  styleUrls: ['./members.component.scss'],
  animations: fuseAnimations,
  encapsulation: ViewEncapsulation.None,
})
export class ManagementMembersComponent implements OnInit {
  @ViewChild(MatMultiSort) sort1: MatMultiSort;
  helperParam: HelperParam = new HelperParam();
  lastPageSize: number = 5;
  checkDataResponse: boolean = true;
  table: TableData<ListMemberDTO>;
  search: string = "";
  sort: string[] = [];
  pageSizeOptions: number = 0;
  arrPropertySortValid: string[] = [
    "id",
    "email",
    "firstName",
    "midName",
    "lastName",
    "registerDate",
    "memberType.levelId",
    "memberType.type",
    "memberType.price",
    "active",
  ];
  constructor(
    private memberService: ManagementMembersService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location,
    private excelService: ExcelService,
  ) {
    this.helperParam.settingReloadSameURL(this.router);
  }

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
          }
          else {
            this.sort = ["email,asc"];
          }
          setTimeout(() => {
            this.getParamSort();
          }, 0);
        } else {
          this.router.navigateByUrl("error/error-404");
        }
      } else {
        this.sort = ["email,asc"];
        this.renderPageInit();
      }
    });
  }

  // Setting For Table Init
  private initTable(): void {
    this.table = new TableData<ListMemberDTO>(
      [
        { id: "no", name: "No." },
        { id: "email", name: "Email" },
        { id: "firstName", name: "First Name" },
        { id: "midName", name: "Mid Name" },
        { id: "lastName", name: "Last Name" },
        { id: "registerDate", name: "Register Date" },
        { id: "memberType.levelId", name: "Level Id" },
        { id: "memberType.type", name: "Type" },
        { id: "memberType.price", name: "Price" },
        { id: "active", name: "Active" },
      ],
      { defaultSortParams: ["email"], defaultSortDirs: ["asc"] },
    );
    this.table.pageIndex = 0;
  }

  // Call API Get Data For Table
  private getData(): void {
    this.updateQueryParamInURL();
    Notification.showWaiting();
    this.memberService
      .getListMembers(this.table.pageIndex, this.table.pageSize, this.search, this.sort)
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
    this.updateQueryParamInURL();
  }

  // Update Param In URL Not Reload Page
  private updateQueryParamInURL(): void {
    let url: string = "";
    if (this.sort.length > 0) {
      url = this.router
        .createUrlTree([], {
          relativeTo: this.route,
          queryParams: {
            page: this.table.pageIndex + 1,
            pageSize: this.table.pageSize,
            search: this.search,
            sort: this.sort,
          },
        })
        .toString();
    } else {
      url = this.router
        .createUrlTree([], {
          relativeTo: this.route,
          queryParams: { page: this.table.pageIndex + 1, pageSize: this.table.pageSize, search: this.search },
        })
        .toString();
    }
    this.location.go(url);
  }

  // Change Page Table When Change Pagination
  private changePaginationInPage(): void {
    if (this.lastPageSize !== this.table.pageSize) {
      this.table.pageIndex = 0;
    }
    this.getData();
  }

  // Search Employee
  public searchMember(): void {
    if (!HelperRegex.REG_SEARCH.test(this.search.trim())) {
      this.router.navigateByUrl("error/error-404");
    }
    this.table.pageIndex = 0;
    this.getData();
  }

  // Get Param Sort And Render Data Again When Change Sort
  async getParamSort(): Promise<void> {
    await this.initData(this.helperParam.getSortParams(this.sort), this.helperParam.getSortDirs(this.sort));
    this.updateSort();
  }

  // Reload Page With Init Data
  async renderPageInit(): Promise<void> {
    await this.initData();
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
      arrSortParam.push("email");
    }
    if (arrSortDirs.length === 0) {
      arrSortDirs.push("asc");
    }
    this.table.sortDirs = arrSortDirs;
    this.table.sortParams = arrSortParam;
    this.table.updateColumNames([
      { id: "email", name: "Email" },
      { id: "firstName", name: "First Name" },
      { id: "midName", name: "Mid Name" },
      { id: "lastName", name: "Last Name" },
      { id: "registerDate", name: "Register Date" },
      { id: "memberType.levelId", name: "Level Id" },
      { id: "memberType.type", name: "Type" },
      { id: "memberType.price", name: "Price" },
      { id: "active", name: "Active" },
    ]);
    this.getData();
  }

    // Export List To Excel
    async exportListToExcel(): Promise<any> {
      const members: MemberExcelDTO[] = [];
      Notification.showWaiting();
      await this.memberService
        .getListExportToExcel(this.search)
        .then((data: any) => {
          data.forEach((element, ind) => {
            const mem: MemberExcelDTO = element;
            mem.index = ind + 1;
            members.push(mem);
          });
        })
        .catch((error) => {
          Notification.showErrorStatus(error);
        })
        .finally(() => {
          Notification.clearWaitNoMessage();
        });
      members.forEach(e => {
        console.log("z: " + e);
      })
      this.excelService.exportAsExcelFile(members, "List_Members");
    }
}
