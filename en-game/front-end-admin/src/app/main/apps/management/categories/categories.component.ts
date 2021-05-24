import { Component, OnInit, ViewChild, ViewEncapsulation } from "@angular/core";
import { fuseAnimations } from "@fuse/animations";
import { ListCategoryDTO } from "app/main/dto/Category/ListCategoryDTO";
import { MatMultiSort, MatMultiSortTableDataSource, TableData } from "ngx-mat-multi-sort";
import { HelperParam } from "../../../commons/helper-param";
import { ManagementCategoriesService } from "./categories.service";
import { ExcelService } from "../../../commons/excel.service";
import { ActivatedRoute, Router } from "@angular/router";
import { Location } from "@angular/common";
import { Notification } from "../../../commons/Notification";
import { HelperRegex } from "app/main/commons/helper-regex";
import { CategoryExportExcelDTO } from "app/main/dto/Category/CategoryExportExcelDTO";

@Component({
  selector: 'management-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss'],
  animations: fuseAnimations,
  encapsulation: ViewEncapsulation.None,
})
export class ManagementCategoriesComponent implements OnInit {
  helperParam: HelperParam = new HelperParam();
  lastPageSize: number = 5;
  checkDataResponse: boolean = true;
  table: TableData<ListCategoryDTO>;
  @ViewChild(MatMultiSort) sort1: MatMultiSort;
  search: string = "";
  sort: string[] = [];
  pageSizeOptions: number = 0;
  arrPropertySortValid: string[] = [
    "id",
    "name",
    "active",
    "hasSubCategory",
  ];
  constructor(
    private categoryService: ManagementCategoriesService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location,
    private excelService: ExcelService,) {
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
          } else {
            this.sort = ["id,asc"];
          }
          setTimeout(() => {
            this.getParamSort();
          }, 0);
        } else {
          this.router.navigateByUrl("error/error-404");
        }
      } else {
        this.sort = ["id,asc"];
        this.renderPageInit();
      }
    });
  }

  // Setting For Table Init
  private initTable(): void {
    this.table = new TableData<ListCategoryDTO>(
      [
        { id: "no", name: "No." },
        { id: "name", name: "Name" },
        { id: "active", name: "Active" },
        { id: "hasSubCategory", name: "Sub Category" },
      ],
      { defaultSortParams: ["name"], defaultSortDirs: ["asc"] },
    );
    this.table.pageIndex = 0;
  }

  // Call API Get Data For Table
  private getData(): void {
    this.helperParam.updateQueryParamInURL(this.location, this.route, this.router, this.sort, this.search, this.table);
    Notification.showWaiting();
    this.categoryService
      .getListCategories(this.table.pageIndex, this.table.pageSize, this.search, this.sort)
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

  // Change Page Table When Change Pagination
  private changePaginationInPage(): void {
    if (this.lastPageSize !== this.table.pageSize) {
      this.table.pageIndex = 0;
    }
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

  // Search Category
  public searchCategory(): void {
    if (!HelperRegex.REG_SEARCH.test(this.search.trim())) {
      this.router.navigateByUrl("error/error-404");
    }
    this.table.pageIndex = 0;
    this.getData();
  }

  // Export List To Excel
  async exportListToExcel(): Promise<any> {
    const categories: CategoryExportExcelDTO[] = [];
    Notification.showWaiting();
    await this.categoryService
      .getListExportToExcel(this.search)
      .then((data: any) => {
        data.forEach((element, ind) => {
          categories.push(new CategoryExportExcelDTO(ind + 1, element.id, element.parentId, element.name, element.active, element.hasSubCategory));
        });
      })
      .catch((error) => {
        Notification.showErrorStatus(error);
      })
      .finally(() => {
        Notification.clearWaitNoMessage();
      });
    categories.forEach(e => {
      console.log("z: " + e);
    })
    this.excelService.exportAsExcelFile(categories, "List_Categories");
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
      arrSortParam.push("name");
    }
    if (arrSortDirs.length === 0) {
      arrSortDirs.push("asc");
    }
    this.table.sortDirs = arrSortDirs;
    this.table.sortParams = arrSortParam;
    this.table.updateColumNames([
      { id: "no", name: "No." },
      { id: "name", name: "Name" },
      { id: "active", name: "Active" },
      { id: "hasSubCategory", name: "Sub Category" },
    ]);
    this.getData();
  }
}
