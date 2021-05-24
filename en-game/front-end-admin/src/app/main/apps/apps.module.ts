import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthGuard } from '../auth/auth.guard';
import { FuseSharedModule } from '@fuse/shared.module';
const routes = [
    {
        path     : '',
        canActivate: [AuthGuard]
    },
    // {
    //     path        : 'dashboards/analytics',
    //     loadChildren: () => import('./dashboards/analytics/analytics.module').then(m => m.AnalyticsDashboardModule)
    // },
    // {
    //     path        : 'dashboards/project',
    //     loadChildren: () => import('./dashboards/project/project.module').then(m => m.ProjectDashboardModule)
    // },
    // {
    //     path        : 'mail',
    //     loadChildren: () => import('./mail/mail.module').then(m => m.MailModule)
    // },
    // {
    //     path        : 'mail-ngrx',
    //     loadChildren: () => import('./mail-ngrx/mail.module').then(m => m.MailNgrxModule)
    // },
    // {
    //     path        : 'chat',
    //     loadChildren: () => import('./chat/chat.module').then(m => m.ChatModule)
    // },
    // {
    //     path        : 'calendar',
    //     loadChildren: () => import('./calendar/calendar.module').then(m => m.CalendarModule)
    // },
    {
        path        : 'error/error-404',
        loadChildren: () => import('./errors/404/error-404.module').then(m => m.Error404Module)
    },
    {
        path: 'management',
        canActivate: [AuthGuard],
        loadChildren: () => import('./management/management.module').then(m => m.ManagementModule)
    },
    // {
    //     path        : 'academy',
    //     loadChildren: () => import('./academy/academy.module').then(m => m.AcademyModule)
    // },
    // {
    //     path        : 'todo',
    //     loadChildren: () => import('./todo/todo.module').then(m => m.TodoModule)
    // },
    // {
    //     path        : 'file-manager',
    //     loadChildren: () => import('./file-manager/file-manager.module').then(m => m.FileManagerModule)
    // },
    // {
    //     path        : 'contacts',
    //     loadChildren: () => import('./contacts/contacts.module').then(m => m.ContactsModule)
    // },
    // {
    //     path        : 'scrumboard',
    //     loadChildren: () => import('./scrumboard/scrumboard.module').then(m => m.ScrumboardModule)
    // }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        FuseSharedModule
    ],
    providers: [
        [AuthGuard],
    ],
})
export class AppsModule {
}
