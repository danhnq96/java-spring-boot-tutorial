import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PromotionListComponent } from './promotion-list/promotion-list.component';
import {Routes, RouterModule} from '@angular/router';
import { AddNewComponent } from './promotion-list/add-new/add-new.component';
import {ReactiveFormsModule} from '@angular/forms';
import { EditComponent } from './promotion-list/edit/edit.component';
import {MaterialModule} from './material.module';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatDialogModule} from '@angular/material/dialog';
import { DeleteComponent } from './promotion-list/delete/delete.component';
import {FormsModule} from '@angular/forms';
import { PromotionListForCustomerComponent } from './promotion-list-for-customer/promotion-list-for-customer.component';
import {MonComponent} from './days/mon/mon.component';
import { TuesComponent } from './days/tues/tues.component';
import { WedsComponent } from './days/weds/weds.component';
import { ThursComponent } from './days/thurs/thurs.component';
import { FriComponent } from './days/fri/fri.component';
import { SatComponent } from './days/sat/sat.component';
import { SunComponent } from './days/sun/sun.component';
import {Ng2SearchPipeModule} from 'ng2-search-filter';
import {NgxPaginationModule} from 'ngx-pagination';
import { DadSgnComponent } from './flights/dad-sgn/dad-sgn.component';


const routes: Routes = [
  {path: 'promotion', component: PromotionListForCustomerComponent},
  {path: 'promotion-list', component: PromotionListComponent},
  {path: 'add-new', component: AddNewComponent},
  {path: 'edit/:id', component: EditComponent},
  {path: 'mon', component: MonComponent},
  {path: 'tues', component: TuesComponent},
  {path: 'weds', component: WedsComponent},
  {path: 'thurs', component: ThursComponent},
  {path: 'fri', component: FriComponent},
  {path: 'sat', component: SatComponent},
  {path: 'sun', component: SunComponent},
  {path: 'dad-sgn', component: DadSgnComponent}
];

@NgModule({
  // tslint:disable-next-line:max-line-length
  declarations: [PromotionListComponent, AddNewComponent, EditComponent, DeleteComponent, PromotionListForCustomerComponent,
    MonComponent, TuesComponent, WedsComponent, ThursComponent, FriComponent, SatComponent, SunComponent, DadSgnComponent, ],
    imports: [
        CommonModule,
        RouterModule.forRoot(routes),
        ReactiveFormsModule,
        MaterialModule,
        MatDatepickerModule,
        MatFormFieldModule,
        MatInputModule,
        MatDialogModule,
        Ng2SearchPipeModule,
        FormsModule,
        NgxPaginationModule
    ],
  exports: [RouterModule, PromotionListForCustomerComponent]
})
export class AppRoutingModule { }
