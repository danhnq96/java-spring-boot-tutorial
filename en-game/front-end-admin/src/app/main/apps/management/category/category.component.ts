import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { CategoryDTO } from 'app/main/dto/Category/CategoryDTO';
import { HelperParam } from "../../../commons/helper-param";
import { ManagementCategoryService } from './category.service';
import { ActivatedRoute, ParamMap, Router } from "@angular/router";
import { HelperRegex } from 'app/main/commons/helper-regex';
import { Notification } from "../../../commons/Notification";
import { fuseAnimations } from "@fuse/animations";

@Component({
  selector: 'management-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss'],
  encapsulation: ViewEncapsulation.None,
  animations: fuseAnimations,
})
export class ManagementCategoryComponent implements OnInit {
  pageType: string;
  categoryForm: FormGroup;
  category: CategoryDTO = new CategoryDTO();
  helperParam: HelperParam = new HelperParam();
  statusCategory = [
    { value: true, viewValue: "Active" },
    { value: false, viewValue: "Inactive" },
  ];

  constructor(
    private categoryService: ManagementCategoryService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.categoryForm = this.createCategoryForm();
  }

  ngOnInit(): void {
    Notification.showWaiting();
    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      const id = paramMap.get("id");
      if (this.helperParam.checkIdIsValid(id)) {
        this.pageType = id.trim() === "new" ? "new" : "edit";
        if (this.pageType === "edit") {
          this.categoryService
            .getCategory(parseInt(id, 10))
            .then((data) => {
              this.category = data;
            })
            .catch(() => {
              this.router.navigateByUrl("error/error-404");
            })
            .then(() => {
              this.categoryForm = this.createCategoryForm();
            });
        } else {
          this.category.name = "";
        }
      } else {
        this.router.navigateByUrl("error/error-404");
      }
    });
    Notification.clearWaitNoMessage();
  }


  createCategoryForm(): FormGroup {
    this.category.active = true;
    this.category.hasSubCategory = true;
    return this.formBuilder.group({
      name: [this.category.name, [Validators.required, Validators.pattern(HelperRegex.REG_NAME)]],
      parentId: [this.category.parentId, [Validators.required, Validators.pattern(HelperRegex.REG_NUMBER)]],
      active: [this.category.active],
      hasSubCategory: [this.category.hasSubCategory],
    });
  }

  // Event For Input
  public eventKeyUpEnterSubmitForm(): void {
    if (!this.categoryForm.invalid) {
      if (this.pageType === "edit") {
        this.saveCategory();
      } else {
        this.addCategory();
      }
    }
  }

  /**
  * Save Category
  */
  public saveCategory(): void {
    Notification.showWaiting();
    if (!this.categoryForm.invalid) {
      // post data to server
      this.categoryService
        .saveCategory(this.category)
        .then(() => {
          Notification.showSuccess("Saved!!!", "", "OK");
          this.router.navigate(['/apps/management/categories'])
        })
        .catch((error) => {
          Notification.clearWaitNoMessage();
          console.log(error);
          if (error.error.name === "Duplicate!") {
            Notification.showErrorMessage("Error", error.error.content);
          } else {
            Notification.showErrorMessage("Error", "Error SQL");
          }
        });
    } else {
      Notification.showSuccess("Error Form Is Invalid!!!");
    }
  }

  /**
  * Add Category
  */
  public addCategory(): void {
    // format date
    Notification.showWaiting();
    if (!this.categoryForm.invalid) {
      console.log(this.category);
      this.categoryService
        .saveCategory(this.category)
        .then(() => {
          Notification.showSuccess("Added!!!", "", "OK");
          this.categoryForm.reset();
          this.categoryForm.get("active").setValue(true);
          this.categoryForm.get("hasSubCategory").setValue(true);
          this.router.navigate(['/apps/management/categories'])
        })
        .catch((error) => {
          Notification.clearWaitNoMessage();
          console.log(error);
          if (error.error.name === "Duplicate!") {
            Notification.showErrorMessage("Error", error.error.content);
          } else {
            Notification.showErrorMessage("Error", "Error SQL");
          }
        });
    }
  }
}
