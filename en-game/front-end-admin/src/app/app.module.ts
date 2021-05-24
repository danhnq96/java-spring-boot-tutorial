import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { HttpClientModule } from "@angular/common/http";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { RouterModule, Routes } from "@angular/router";
// material
import { MatMomentDateModule } from "@angular/material-moment-adapter";
import { MatOptionModule } from "@angular/material/core";
import { MatButtonModule } from "@angular/material/button";
import { MatIconModule } from "@angular/material/icon";
import { MatSelectModule } from "@angular/material/select";
import { FormsModule } from "@angular/forms";
import { MatFormFieldModule } from "@angular/material/form-field";
import { environment } from "../environments/environment";
import { MatMenuModule } from '@angular/material/menu';
import { MatGridListModule } from '@angular/material/grid-list';
// Fire Base
import { AngularFireModule } from "@angular/fire";
import { AngularFireStorageModule } from "@angular/fire/storage";
import { AngularFireDatabaseModule } from "@angular/fire/database";

import { TranslateModule } from "@ngx-translate/core";
import "hammerjs";
import { FuseModule } from "@fuse/fuse.module";
import { FuseSharedModule } from "@fuse/shared.module";
import { FuseProgressBarModule, FuseSidebarModule, FuseThemeOptionsModule } from "@fuse/components";
import { fuseConfig } from "app/fuse-config";
import { AppComponent } from "app/app.component";
import { LayoutModule } from "app/layout/layout.module";
import { AuthGuard } from "./main/auth/auth.guard";

import { StoreModule } from "@ngrx/store";
import { reducer } from "./store/reducers/employeeReducer";
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';  
  
import { ColorPickerModule } from 'ngx-color-picker';
const appRoutes: Routes = [
  {
    path: "apps",
    loadChildren: () => import("./main/apps/apps.module").then((m) => m.AppsModule),
  },
  {
    path: "",
    loadChildren: () => import("./main/login/login.module").then((m) => m.LoginModule),
  },
  {
    path: "login",
    loadChildren: () => import("./main/login/login.module").then((m) => m.LoginModule),
  },
  {
    path: "home",
    loadChildren: () => import("./main/home/home.module").then((m) => m.HomeModule),
  },
  {
    path: "**",
    redirectTo: "apps/error/error-404",
  },
  // {
  //     path: 'register',
  //     loadChildren: () => import('./main/register/register.module').then(m => m.RegisterModule)
  // }
];

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    StoreModule.forRoot({
      employeeBasicInfo: reducer,
    }),
    TranslateModule.forRoot(),

    // Material moment date module
    MatMomentDateModule,

    // Material
    MatButtonModule,
    MatIconModule,
    FormsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatOptionModule,
    MatMenuModule,
    MatGridListModule,
    // Fuse modules
    FuseModule.forRoot(fuseConfig),
    FuseProgressBarModule,
    FuseSharedModule,
    FuseSidebarModule,
    FuseThemeOptionsModule,
    // App modules
    LayoutModule,

    // firebase
    AngularFireModule.initializeApp(environment.firebaseConfig),
    AngularFireStorageModule,
    AngularFireDatabaseModule,

    CKEditorModule,

    ColorPickerModule,

  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent],
})
export class AppModule { }
