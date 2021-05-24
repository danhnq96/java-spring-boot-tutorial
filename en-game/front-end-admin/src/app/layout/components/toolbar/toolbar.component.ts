import { Component, OnDestroy, OnInit, ViewEncapsulation } from "@angular/core";
import { Subject } from "rxjs";
import { takeUntil } from "rxjs/operators";
import { TranslateService } from "@ngx-translate/core";
import * as _ from "lodash";

import { FuseConfigService } from "@fuse/services/config.service";
import { FuseSidebarService } from "@fuse/components/sidebar/sidebar.service";
import { navigation } from "app/navigation/navigation";
import { TokenStorageService } from "app/main/auth/token-storage.service";
import { Router } from "@angular/router";
import { Notification } from "../../../main/commons/Notification";
import { AccountBasicInfoDTO } from "app/main/dto/Account/AccountBasicInfoDTO";
import { EmployeeBasicInfoDTO } from "app/main/dto/Employee/EmployeeBasicInfoDTO";
import { ToolBarService } from "./toolbar.service";
import { AngularFireDatabase } from "@angular/fire/database";
@Component({
  selector: "toolbar",
  templateUrl: "./toolbar.component.html",
  styleUrls: ["./toolbar.component.scss"],
  encapsulation: ViewEncapsulation.None,
})
export class ToolbarComponent implements OnInit, OnDestroy {
  horizontalNavbar: boolean;
  rightNavbar: boolean;
  hiddenNavbar: boolean;
  languages: any;
  navigation: any;
  selectedLanguage: any;
  userStatusOptions: any[];
  accountBasicInfo: AccountBasicInfoDTO;
  // Private
  private unsubscribeAll: Subject<any>;
  /**
   * Constructor
   *
   * @param {FuseConfigService} fuseConfigService
   * @param {FuseSidebarService} fuseSidebarService
   * @param {TranslateService} translateService
   */
  constructor(
    private fuseConfigService: FuseConfigService,
    private fuseSidebarService: FuseSidebarService,
    private translateService: TranslateService,
    private tokenService: TokenStorageService,
    private router: Router,
    private toolBarService: ToolBarService,
    private firebaseDB: AngularFireDatabase,
  ) {
    this.accountBasicInfo = this.tokenService.getAccountBasicInfo();
    if (!this.accountBasicInfo) {
      this.accountBasicInfo = new AccountBasicInfoDTO();
      this.accountBasicInfo.employee = new EmployeeBasicInfoDTO();
    }
    // Set the defaults
    this.userStatusOptions = [
      {
        title: "Online",
        icon: "icon-checkbox-marked-circle",
        color: "#4CAF50",
      },
      {
        title: "Away",
        icon: "icon-clock",
        color: "#FFC107",
      },
      {
        title: "Do not Disturb",
        icon: "icon-minus-circle",
        color: "#F44336",
      },
      {
        title: "Invisible",
        icon: "icon-checkbox-blank-circle-outline",
        color: "#BDBDBD",
      },
      {
        title: "Offline",
        icon: "icon-checkbox-blank-circle-outline",
        color: "#616161",
      },
    ];

    this.languages = [
      {
        id: "en",
        title: "English",
        flag: "us",
      },
      {
        id: "tr",
        title: "Turkish",
        flag: "tr",
      },
    ];

    this.navigation = navigation;

    // Set the private defaults
    this.unsubscribeAll = new Subject();
  }

  // -----------------------------------------------------------------------------------------------------
  // @ Lifecycle hooks
  // -----------------------------------------------------------------------------------------------------

  /**
   * On init
   */
  ngOnInit(): void {
    // Subscribe to the config changes

    this.fuseConfigService.config.pipe(takeUntil(this.unsubscribeAll)).subscribe((settings) => {
      this.horizontalNavbar = settings.layout.navbar.position === "top";
      this.rightNavbar = settings.layout.navbar.position === "right";
      this.hiddenNavbar = settings.layout.navbar.hidden === true;
    });
    // Set the selected language from default languages
    this.selectedLanguage = _.find(this.languages, { id: this.translateService.currentLang });
    this.clearTokenAfterTimer();
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
   * Toggle sidebar open
   *
   * @param key
   */
  public toggleSidebarOpen(key): void {
    this.fuseSidebarService.getSidebar(key).toggleOpen();
  }

  /**
   * Search
   *
   * @param value
   */
  public search(value): void {
    // Do your search here...
    console.log(value);
  }

  /**
   * Set the language
   *
   * @param lang
   */
  public setLanguage(lang): void {
    // Set the selected language for the toolbar
    this.selectedLanguage = lang;

    // Use the selected language for translations
    this.translateService.use(lang.id);
  }

  private clearTokenAfterTimer():void{
    const timeNow = new Date().getTime();
    const timeLastLogin = new Date(this.accountBasicInfo.lastLogin).getTime();
    const tokenDeadTime = Math.floor((timeNow - timeLastLogin) / 1000 / 60 / 60);
    // set clear token
    if (tokenDeadTime > 11) {
      this.tokenService.logOut();
    }
  }

  public logOut(): void {
    this.firebaseDB.database.ref(`users/${this.tokenService.getUsername()}/status`).set("offline");
    this.tokenService.logOut();
    this.router.navigate(["/login"]);
  }

  // Get My Profile
  async myProfile(): Promise<void> {
    await this.toolBarService.getProfile().then((id) => {
      console.log("sadasđ" + id);
      this.router.navigateByUrl("apps/management/employee/" + id);
    }).catch((error) => {
      Notification.showErrorMessage("Error", error.error);
    });
  }



  // change password
  public changePassword(): void {
    this.router.navigateByUrl("apps/management/password");
  }
}
