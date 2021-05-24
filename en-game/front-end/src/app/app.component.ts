import { isPlatformBrowser } from "@angular/common";
import { Component, Inject, PLATFORM_ID } from "@angular/core";
import { Router, NavigationEnd } from "@angular/router";
import { Settings, AppSettings } from "./app.settings";
import { TranslateService } from "@ngx-translate/core";
import { ACCESS_TOKEN } from "./theme/constants";
import { AuthService } from "./services/AuthService";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
})
export class AppComponent {
  loading = false;
  public settings: Settings;
  constructor(
    public appSettings: AppSettings,
    public router: Router,
    @Inject(PLATFORM_ID) private platformId: Object,
    public translate: TranslateService,
    public authService: AuthService,
  ) {
    this.settings = this.appSettings.settings;
    translate.addLangs(["en", "de", "fr", "ru", "tr", "vi"]);
    translate.setDefaultLang("en");
    translate.use("en");
  }

  async ngOnInit() {
    // this.router.navigate(['']);  //redirect other pages to homepage on browser refresh
    if (localStorage.getItem(ACCESS_TOKEN)) {
      this.authService.getUser("user/me", false);
    }
  }

  ngAfterViewInit() {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        if (isPlatformBrowser(this.platformId)) {
          window.scrollTo(0, 0);
        }
      }
    });
  }
}
