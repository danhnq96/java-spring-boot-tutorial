import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatChipsModule } from '@angular/material/chips';
import { MatRippleModule } from '@angular/material/core';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatOptionModule } from '@angular/material/core'
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatTabsModule } from '@angular/material/tabs';
import { NgxChartsModule } from '@swimlane/ngx-charts';


import { NgxPaginationModule } from 'ngx-pagination';
import { PaginatorDirective } from '../../commons/pagination.directive';
import { FuseSharedModule } from '@fuse/shared.module';
import { FuseWidgetModule } from '@fuse/components/widget/widget.module';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { AuthGuard } from '../../auth/auth.guard';
// material
import { MatMomentDateModule } from '@angular/material-moment-adapter';
// multi sort
import { MatMenuModule } from '@angular/material/menu';
import { MatListModule } from '@angular/material/list';
import { MatDividerModule } from '@angular/material/divider';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { MatMultiSortModule } from 'ngx-mat-multi-sort';
import { FormsModule } from '@angular/forms';

// Component
import { ManagementCategoriesComponent } from './categories/categories.component';
import { ManagementCategoryComponent } from './category/category.component';
import { ManagementEmployeesComponent } from 'app/main/apps/management/employees/employees.component';
import { ManagementEmployeeComponent } from 'app/main/apps/management/employee/employee.component';
import { ManagementChangePasswordComponent } from './change-password/change-password.component';
import { ManagementMembersComponent } from './members/members.component';
import { ManagementProductsComponent } from './products/products.component';
import { ManagementProductComponent } from './product/product.component';
// Service
import { ManagementCategoriesService } from './categories/categories.service';
import { ManagementCategoryService } from './category/category.service';
import { ManagementMembersService } from './members/members.service';
import { ManagementEmployeesService } from 'app/main/apps/management/employees/employees.service';
import { ManagementEmployeeService } from 'app/main/apps/management/employee/employee.service';
import { ManagementProductsService } from 'app/main/apps/management/products/products.service';
import { ManagementProductService } from 'app/main/apps/management/product/product.service';


import { ExcelService } from '../../commons/excel.service';
import { ManagementChangePasswordService } from './change-password/change-password.service';

import { CKEditorModule } from '@ckeditor/ckeditor5-angular';

import { ColorPickerModule } from 'ngx-color-picker';
const routes: Routes = [
    {
        path     : '',
        canActivate: [AuthGuard]
    },
    {
        path: 'employees',
        pathMatch: 'full',
        component: ManagementEmployeesComponent,
    },
    {
        path: 'employee/:id',
        component: ManagementEmployeeComponent,
    },
    {
        path: 'members',
        pathMatch: 'full',
        component: ManagementMembersComponent,
    },
    {
        path: 'categories',
        pathMatch: 'full',
        component: ManagementCategoriesComponent,
    },
    {
        path: 'category/:id',
        component: ManagementCategoryComponent,
    },
    {
        path: 'products',
        pathMatch: 'full',
        component: ManagementProductsComponent,
    },
    {
        path: 'product/:id',
        component: ManagementProductComponent,
    },
    {
        path: 'password',
        component: ManagementChangePasswordComponent,
    }
];

@NgModule({
    declarations: [
        ManagementEmployeesComponent,
        ManagementEmployeeComponent,
        PaginatorDirective,
        ManagementChangePasswordComponent,
        ManagementMembersComponent,
        ManagementCategoriesComponent,
        ManagementCategoryComponent,
        ManagementProductsComponent,
        ManagementProductComponent,
    ],
    imports: [
        RouterModule.forChild(routes),
        // mutil sort
        MatMenuModule,
        MatListModule,
        MatDividerModule,
        DragDropModule,
        MatMultiSortModule,
        FormsModule,
        MatButtonModule,
        MatChipsModule,
        MatExpansionModule,
        MatFormFieldModule,
        MatIconModule,
        MatInputModule,
        MatPaginatorModule,
        MatRippleModule,
        MatSelectModule,
        MatOptionModule,
        MatSortModule,
        MatSnackBarModule,
        MatTableModule,
        MatTabsModule,
        MatCheckboxModule,
        MatToolbarModule,
        MatMomentDateModule,
        NgxPaginationModule,
        NgxChartsModule,
        MatDatepickerModule,
        MatGridListModule,

        FuseSharedModule,
        FuseWidgetModule,

        CKEditorModule,
        
        ColorPickerModule,
    ],
    providers: [
        ManagementEmployeesService,
        ManagementEmployeeService,
        ManagementChangePasswordService,
        ManagementMembersService,
        ExcelService,
        ManagementCategoriesService,
        ManagementCategoryService,
        ManagementProductsService,
        ManagementProductService
    ],
    exports: [PaginatorDirective]
})
export class ManagementModule {
}
