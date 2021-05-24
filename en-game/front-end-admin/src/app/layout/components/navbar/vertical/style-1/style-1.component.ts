import { Component, OnDestroy, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { delay, filter, take, takeUntil } from 'rxjs/operators';

import { FuseConfigService } from '@fuse/services/config.service';
import { FuseNavigationService } from '@fuse/components/navigation/navigation.service';
import { FusePerfectScrollbarDirective } from '@fuse/directives/fuse-perfect-scrollbar/fuse-perfect-scrollbar.directive';
import { FuseSidebarService } from '@fuse/components/sidebar/sidebar.service';
import { TokenStorageService } from 'app/main/auth/token-storage.service';
import { AccountBasicInfoDTO } from 'app/main/dto/Account/AccountBasicInfoDTO';
import { EmployeeBasicInfoDTO } from 'app/main/dto/Employee/EmployeeBasicInfoDTO';

@Component({
    selector: 'navbar-vertical-style-1',
    templateUrl: './style-1.component.html',
    styleUrls: ['./style-1.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class NavbarVerticalStyle1Component implements OnInit, OnDestroy {
    fuseConfig: any;
    navigation: any;
    // Private
    private fusePerfectScrollbar: FusePerfectScrollbarDirective;
    private unsubscribeAll: Subject<any>;
    accountBasicInfo: AccountBasicInfoDTO;
    /**
     * Constructor
     *
     * @param {FuseConfigService} fuseConfigService
     * @param {FuseNavigationService} fuseNavigationService
     * @param {FuseSidebarService} fuseSidebarService
     * @param {Router} router
     */
    constructor(
        private fuseConfigService: FuseConfigService,
        private fuseNavigationService: FuseNavigationService,
        private fuseSidebarService: FuseSidebarService,
        private router: Router,
        private tokenService: TokenStorageService
    ) {
        this.accountBasicInfo = this.tokenService.getAccountBasicInfo();
        if(!this.accountBasicInfo){
            this.accountBasicInfo = new AccountBasicInfoDTO();
            this.accountBasicInfo.employee = new EmployeeBasicInfoDTO();
        }
        // Set the private defaults
        this.unsubscribeAll = new Subject();
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Accessors
    // -----------------------------------------------------------------------------------------------------

    // Directive
    @ViewChild(FusePerfectScrollbarDirective, { static: true })
    set directive(theDirective: FusePerfectScrollbarDirective) {
        if (!theDirective) {
            return;
        }

        this.fusePerfectScrollbar = theDirective;

        // Update the scrollbar on collapsable item toggle
        this.fuseNavigationService.onItemCollapseToggled
            .pipe(
                delay(500),
                takeUntil(this.unsubscribeAll)
            )
            .subscribe(() => {
                this.fusePerfectScrollbar.update();
            });

        // Scroll to the active item position
        this.router.events
            .pipe(
                filter((event) => event instanceof NavigationEnd),
                take(1)
            )
            .subscribe(() => {
                setTimeout(() => {
                    this.fusePerfectScrollbar.scrollToElement('navbar .nav-link.active', -120);
                });
            }
            );
        // Get Data Employee Basic Info
        // Notification.showWaiting(); 
        // this._styleService.getEmployeeBasicInfo().then(data =>{
        //     this.employeeBasicInfo = data;
        //     Notification.clearWaitNoMessage();
        // }).catch(err => {
        //     Notification.showErrorStatus(err);
        // })
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Lifecycle hooks
    // -----------------------------------------------------------------------------------------------------

    /**
     * On init
     */
    ngOnInit(): void {
        this.router.events
            .pipe(
                filter((event) => event instanceof NavigationEnd),
                takeUntil(this.unsubscribeAll)
            )
            .subscribe(() => {
                if (this.fuseSidebarService.getSidebar('navbar')) {
                    this.fuseSidebarService.getSidebar('navbar').close();
                }
            }
            );

        // Subscribe to the config changes
        this.fuseConfigService.config
            .pipe(takeUntil(this.unsubscribeAll))
            .subscribe((config) => {
                this.fuseConfig = config;
            });

        // Get current navigation
        this.fuseNavigationService.onNavigationChanged
            .pipe(
                filter(value => value !== null),
                takeUntil(this.unsubscribeAll)
            )
            .subscribe(() => {
                this.navigation = this.fuseNavigationService.getCurrentNavigation();
            });
    }

    /**
     * On destroy
     */
    ngOnDestroy(): void {
        // Unsubscribe from all subscriptions
        this.unsubscribeAll.next();
        this.unsubscribeAll.complete();
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Public methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Toggle sidebar opened status
     */
    toggleSidebarOpened(): void {
        this.fuseSidebarService.getSidebar('navbar').toggleOpen();
    }

    /**
     * Toggle sidebar folded status
     */
    toggleSidebarFolded(): void {
        this.fuseSidebarService.getSidebar('navbar').toggleFold();
    }
}
