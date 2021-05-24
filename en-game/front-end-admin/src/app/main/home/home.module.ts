import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TranslateModule } from '@ngx-translate/core';

import { FuseSharedModule } from '@fuse/shared.module';

import { HomeComponent } from './home.component';
import { AuthGuard } from '../auth/auth.guard';
const routes = [
    {
        path     : '',
        component: HomeComponent,
        canActivate: [AuthGuard]
    }
];

@NgModule({
    declarations: [
        HomeComponent
    ],
    imports     : [
        RouterModule.forChild(routes),

        TranslateModule,
       
        FuseSharedModule
    ],  providers: [
        [AuthGuard],
    ],
    exports     : [
        HomeComponent
    ]
})

export class HomeModule
{
}
