import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { HelperParam } from "../../../commons/helper-param";
import { ManagementProductService } from './product.service';
import { ActivatedRoute, ParamMap, Router } from "@angular/router";
import { HelperRegex } from 'app/main/commons/helper-regex';
import { Notification } from "../../../commons/Notification";
import { fuseAnimations } from "@fuse/animations";
import { ProductDTO } from 'app/main/dto/Product/ProductDTO';
import { DateAdapter, MAT_DATE_FORMATS } from "@angular/material/core";
import { AppDateAdapter, APP_DATE_FORMATS } from "../../../commons/format-datepicker";
import Notiflix from "notiflix";
import { AngularFireStorage, AngularFireStorageReference } from "@angular/fire/storage";
import { finalize } from 'rxjs/operators';
import { ImageDTO } from 'app/main/dto/image/imageDTO';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { UploadImageMainDTO } from 'app/main/dto/image/UploadImageMainDTO';
import { ColorDTO } from 'app/main/dto/Color/colorDTO';

@Component({
  selector: 'management-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss'],
  encapsulation: ViewEncapsulation.None,
  animations: fuseAnimations,
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS },
  ],
})

export class ManagementProductComponent implements OnInit {
  public Editor = ClassicEditor;
  private imgDefault =
    "https://firebasestorage.googleapis.com/v0/b/endgame-eproject.appspot.com/o/Employees%2Fimage_none.png?alt=media&token=c07b3f80-983e-4ec9-aca2-2eb7030e76b9";
  pageType: string;
  productForm: FormGroup;
  product: ProductDTO = new ProductDTO();
  helperParam: HelperParam = new HelperParam();
  statusProduct = [
    { value: true, viewValue: "Active" },
    { value: false, viewValue: "Inactive" },
  ];
  pathImageMain: string = "";
  checkUpFire: boolean = true;
  categories = [];
  imageMain: ImageDTO = new ImageDTO();
  currentPath: string = "";
  listNewImage: string[] = [];
  listUploadFireBase: string[] = [];
  listUrlFireBase: string[] = [];
  checkDeletedImageProduct: boolean = false;
  listImageDelete: ImageDTO[] = [];
  colors: ColorDTO[] = [new ColorDTO(), new ColorDTO(), new ColorDTO(), new ColorDTO()];
  constructor(
    private productService: ManagementProductService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private storage: AngularFireStorage,
  ) {
    this.productForm = this.createProductForm();
    this.imageMain.small = this.imgDefault;
  }

  ngOnInit(): void {

    Notification.showWaiting();
    this.productService
      .getListCategories()
      .then((data) => {
        if (data.length > 0) {
          data.forEach(element => {
            this.categories.push(
              {
                value: element.id,
                viewValue: element.name
              }
            )
          });
        }
      })
      .catch(() => {
        console.log("err");
        this.router.navigateByUrl("error/error-404");
      }).then(() => {
        this.route.paramMap.subscribe((paramMap: ParamMap) => {
          const id = paramMap.get("id");
          if (this.helperParam.checkIdIsValid(id)) {
            this.pageType = id.trim() === "new" ? "new" : "edit";
            if (this.pageType === "edit") {
              this.productService
                .getProduct(parseInt(id, 10))
                .then((data) => {
                  this.product = data;
                  if (this.product.imageMain) {
                    this.imageMain = this.product.imageMain;
                    this.currentPath = this.product.imageMain.small;
                  }
                  console.log(this.product.colors);
                  if (this.product.colors && this.product.colors.length > 0) {
                    this.colors.forEach((e, index) => {
                      if (this.product.colors[index] && this.product.colors[index].name) {
                        this.colors[index] = this.product.colors[index];
                      }
                    })
                    console.log(this.colors);
                  }
                  // this.product.createdDate = formatDate(this.product.createdDate, "dd-MM-yyyy", "en-US");
                })
                .catch((err) => {
                  console.log(err);
                  console.log("err");
                  this.router.navigateByUrl("error/error-404");

                })
                .then(() => {
                  this.productForm = this.createProductForm();
                });
            } else {
              this.product.name = "";
              this.product.active = true;
            }
          } else {
            console.log("err");
            this.router.navigateByUrl("error/error-404");
          }
        });
        Notification.clearWaitNoMessage();
      });
  }

  private createProductForm(): FormGroup {
    return this.formBuilder.group({
      name: [this.product.name, [Validators.required, Validators.pattern(HelperRegex.REG_NAME_PRODUCT)]],
      category: [this.product.categoryId],
      newPrice: [this.product.newPrice, [Validators.required, Validators.pattern(HelperRegex.REG_NUMBER_FLOAT_LARGER_OR_EQUAL_ZERO)]],
      oldPrice: [this.product.oldPrice, [Validators.required, Validators.pattern(HelperRegex.REG_NUMBER_FLOAT_LARGER_OR_EQUAL_ZERO)]],
      discount: [this.product.discount, [Validators.required, Validators.pattern(HelperRegex.REG_NUMBER_FLOAT_LARGER_OR_EQUAL_ZERO), Validators.max(99), Validators.min(0)]],
      weight: [this.product.weight, [Validators.required, Validators.pattern(HelperRegex.REG_NUMBER_FLOAT_LARGER_OR_EQUAL_ZERO)]],
      ratingCount: [this.product.ratingCount, [Validators.required, Validators.pattern(HelperRegex.REG_NUMBER_FLOAT_LARGER_OR_EQUAL_ZERO)]],
      ratingValue: [this.product.ratingValue, [Validators.required, Validators.pattern(HelperRegex.REG_NUMBER_FLOAT_LARGER_OR_EQUAL_ZERO)]],
      availibilityCount: [this.product.availibilityCount, [Validators.required, Validators.pattern(HelperRegex.REG_NUMBER)]],
      cartCount: [this.product.cartCount, [Validators.required, Validators.pattern(HelperRegex.REG_NUMBER)]],
      active: [this.product.active],
      description: [this.product.description],
      size: [this.product.size, Validators.maxLength(10)],
      // createdDate: [this.product.createdDate, Validators.required],
    });
  }

  // Event For Input
  public eventKeyUpEnterSubmitForm(): void {
    if (!this.productForm.invalid) {
      this.saveProduct();
    }
  }
  /**
   * Save Product
   */
  public saveProduct(): void {
    // Notification.showWaiting();
    if (!this.productForm.invalid) {
      // post data to server
      Notification.showWaiting();
      if (this.checkDeletedImageProduct) {
        Notiflix.Confirm.Init({ titleColor: "#ff0000", messageFontSize: "15px" });
        Notiflix.Confirm.Show(
          "Confirm Save Product!",
          "You deleted image product. Are you sure save product ?",
          "Yes",
          "No",
          () => {
            this.product.imagesDeleted = this.listImageDelete;
            this.removeAllImageProductInFireBase(this.listImageDelete);
            this.uploadAndSave();
          },
          () => {
            Notification.clearWaitNoMessage();
          },
        );
      } else {
        this.uploadAndSave();
      }
    } else {
      Notification.showErrorMessage("Error", "Error Form Is Invalid!!!");
    }
  }


  async uploadAndSave() {
    let checkErr: boolean = false;
    if (this.listUploadFireBase.length > 0) {
      this.listUrlFireBase = await this.uploadFire();
      if (this.listUrlFireBase && this.listUrlFireBase.length !== this.listUploadFireBase.length) {
        this.removeAllImageInFireBase(this.listUrlFireBase);

        Notification.showErrorMessage("Error Upload Firebase !", "Please try again");
        checkErr = true;
      } else {
        this.product.imagesAdd = [];
        if (this.listUrlFireBase.length > 0) {
          this.listUrlFireBase.forEach(e => {
            const imageDTO: ImageDTO = new ImageDTO();
            imageDTO.small = e;
            imageDTO.mainImage = false;
            this.product.imagesAdd.push(imageDTO);
          })
        }

      }
    }
    if (!checkErr) {
      this.saveToDB();
    }
  }

  private saveToDB() {
    this.product.colors = this.colors;
    this.productService
      .saveProduct(this.product)
      .then(() => {
        Notification.showSuccess("Saved!!!", "", "OK");
        this.router.navigate(['/apps/management/products'])
      })
      .catch((error) => {
        Notification.clearWaitNoMessage();
        this.removeAllImageInFireBase(this.listUrlFireBase);

        this.listUrlFireBase = [];
        if (error.error && error.error.name && error.error.name === "Duplicate!") {
          Notification.showErrorMessage("Error", error.error.content);
        } else {
          Notification.showErrorMessage("Error", "Error SQL");
        }
      });
  }

  /**
    * Add Product
    */
  public addProduct(): void {
    // format date
    Notification.showWaiting();
    if (!this.productForm.invalid) {
      this.productService
        .saveProduct(this.product)
        .then(() => {
          Notification.showSuccess("Added!!!", "", "OK");
          this.productForm.reset();
          this.productForm.get("active").setValue(true);
        })
        .catch((error) => {
          Notification.clearWaitNoMessage();
          if (error.error.name === "Duplicate!") {
            Notification.showErrorMessage("Error", error.error.content);
          } else {
            Notification.showErrorMessage("Error", "Error SQL");
          }
        });
    }
  }

  // Set Default Date
  public setDefaultDate(name): void {
    if (!this.productForm.get(name).value || this.productForm.get(name).value === "") {
      if (name === "createdDate") {
        this.productForm.get(name).setValue(new Date());
      }
    }
  }

  public deleteImage(i): void {
    this.listNewImage.splice(i, 1);
    this.listUploadFireBase.splice(i, 1);
  }

  // Get Image When Change Image
  public getImage(ev: any): void {
    if (ev.target.files && ev.target.files[0]) {
      if (this.helperParam.checkImage(ev)) {
        const filesAmount = ev.target.files.length;
        for (let i = 0; i < filesAmount; i++) {
          const reader: FileReader = new FileReader();
          reader.onload = (e: any) => { this.listNewImage.push(e.target.result) };
          reader.readAsDataURL(ev.target.files[i]);
          this.listUploadFireBase.push(ev.target.files[i]);
        }
      } else {
        Notification.showErrorMessage("Error File !", "Please choose file is image or file size < 2 MB");
      }
    }
  }

  // Get Image When Change Image
  public getImageMainChange(ev: any): void {
    Notification.showWaiting();

    if (ev.target.files && ev.target.files[0]) {
      if (this.helperParam.checkImage(ev)) {
        const reader: FileReader = new FileReader();
        // reader.onload = (e: any) => (this.imageMain.pathImage = e.target.result);
        reader.readAsDataURL(ev.target.files[0]);
        this.uploadFireBaseImageMain(ev.target.files[0]);
      } else {
        Notification.showErrorMessage("Error File !", "Please choose file is image or file size < 2 MB");
      }
    }
  }

  private uploadFireBaseImageMain(imgChange) {
    console.log(this.product.imageMain);
    const filePath = `Products/${window.performance.now()}`;
    const fileRef: AngularFireStorageReference = this.storage.ref(filePath);
    if (imgChange) {
      this.storage
        .upload(filePath, imgChange)
        .snapshotChanges()
        .pipe(
          finalize(() => {
            fileRef
              .getDownloadURL()
              .toPromise()
              .then((url) => {
                this.pathImageMain = url;
              })
              .then(() => {
                const uploadImageMain: UploadImageMainDTO = new UploadImageMainDTO();
                // set image new
                const imageNew: ImageDTO = new ImageDTO();
                imageNew.mainImage = true;
                imageNew.small = this.pathImageMain;
                imageNew.productId = this.product.id;

                // set image old
                let imageOld: ImageDTO = null;
                if (this.product.imageMain) {
                  imageOld = new ImageDTO();
                  imageOld = this.product.imageMain;
                  imageOld.small = this.currentPath;
                }

                uploadImageMain.imageMainNew = imageNew;
                uploadImageMain.imageMainOld = imageOld;

                // post data to server
                this.productService
                  .uploadImageMain(uploadImageMain)
                  .then((data) => {
                    this.product.imageMain = data;
                    this.imageMain = data;
                    if (uploadImageMain.imageMainOld && uploadImageMain.imageMainOld.small) {
                      this.storage.storage.refFromURL(uploadImageMain.imageMainOld.small).delete();
                    }
                    this.currentPath = data.pathImage;
                    Notification.showSuccess("Upload image successfully!!!", "", "OK");

                  })
                  .catch((error) => {
                    if (this.product.imageMain && this.product.imageMain.small !== this.imgDefault) {
                      this.imageMain.small = this.product.imageMain.small;
                    } else {
                      this.imageMain.small = this.imgDefault;
                    }
                    console.log("err");
                    this.storage.storage.refFromURL(this.pathImageMain).delete();
                    if (error.error && error.error.name && error.error.name === "Duplicate!") {
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
      Notification.showErrorMessage("Error", "Can not upload image.");
    }
  }

  public removeAllImage(): void {
    Notiflix.Confirm.Init({ titleColor: "#ff0000", messageFontSize: "15px" });
    Notiflix.Confirm.Show(
      "Confirm Delete!",
      "Are you sure delete all image ?",
      "Yes",
      "No",
      () => {
        this.listNewImage = [];
        this.listUploadFireBase = [];
      },
      () => {
        Notification.clearWaitNoMessage();
      },
    );
  }



  async saveFileImageInFireBase() {
    Notification.showWaiting();
    if (!this.productForm.invalid) {
      this.listUrlFireBase = await this.uploadFire();
      if (this.listUrlFireBase && this.listUrlFireBase.length !== this.listUploadFireBase.length) {
        this.removeAllImageInFireBase(this.listUrlFireBase);
        Notification.showErrorMessage("Error Upload Firebase !", "Please try again");
      }
    }
  }

  private uploadFire() {
    if (this.listUploadFireBase.length > 0) {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      return new Promise<string[]>((resolve, reject) => {
        const urls: string[] = [];
        if (this.listUploadFireBase.length > 0) {
          this.listUploadFireBase.forEach(element => {
            const filePath = `Products/${window.performance.now()}`;
            const ref: AngularFireStorageReference = this.storage.ref(filePath);
            const task = this.storage.upload(filePath, element);
            console.log(element);
            task.snapshotChanges().pipe(
              finalize(() => {
                ref.getDownloadURL().subscribe(url => {
                  urls.push(url);
                  if (this.listUploadFireBase.length === urls.length) {
                    resolve(urls);
                  }
                });
              })
            )
              .subscribe();
          });
        }
      });
    }
    return null;
  }

  private removeAllImageInFireBase(list: string[]) {
    if (list && list.length > 0) {
      list.forEach(element => {
        this.storage.storage.refFromURL(element).delete();
      });
    }
    this.product.imagesAdd = [];
  }

  private removeAllImageProductInFireBase(list: ImageDTO[]) {
    if (list && list.length > 0) {
      list.forEach(element => {
        this.storage.storage.refFromURL(element.small).delete();
      });
    }
  }

  public removeAllImageProduct(): void {
    Notiflix.Confirm.Init({ titleColor: "#ff0000", messageFontSize: "15px" });
    Notiflix.Confirm.Show(
      "Confirm Delete!",
      "Are you sure delete all image ?",
      "Yes",
      "No",
      () => {
        this.checkDeletedImageProduct = true;
        if (this.product.images) {
          this.product.images.forEach(element => {
            this.listImageDelete.push(element);
          });
        }
        this.product.images = [];
      },
      () => {
        // No button callback
      },
    );
  }

  public deleteImageProduct(id): void {
    const ind = this.product.images.findIndex(e => e.id === id);
    this.listImageDelete.push(this.product.images[ind]);
    this.product.images.splice(ind, 1);
    this.checkDeletedImageProduct = true;
  }

  public deleteColor(id): void {
    Notiflix.Confirm.Init({ titleColor: "#ff0000", messageFontSize: "15px" });
    Notiflix.Confirm.Show(
      "Confirm Delete!",
      "Are you sure delete color ?",
      "Yes",
      "No",
      () => {
        
        Notification.showWaiting();
        const ind = this.colors.findIndex(e => e.id === id);
        if (ind !== -1) {
          this.productService
            .deleteColor(this.colors[ind])
            .then(() => {
              Notification.showSuccess("Deleted Color!!!", "", "OK");
              this.colors[this.colors.findIndex(e => e.id === id)] = new ColorDTO();
              const indProduct = this.product.colors.findIndex(e => e.id === id);
              if (indProduct !== -1) {
                this.product.colors.splice(indProduct, 1);
              }
            })
            .catch((error) => {
              Notification.clearWaitNoMessage();
              if (error.error.name === "Duplicate!") {
                Notification.showErrorMessage("Error", error.error.content);
              } else {
                Notification.showErrorMessage("Error", "Error SQL");
              }
            });
        }
        Notification.clearWaitNoMessage();
      },
      () => {
        // No button callback
        Notification.clearWaitNoMessage();
      },
    );
  }
}

