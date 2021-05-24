import { Component, OnInit } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { AppService } from "../../../app.service";
import { AuthService } from "../../../services/AuthService";
import { Settings, AppSettings } from "../../../app.settings";
import { ROLE } from "../../constants";
import { User } from "../../../dto/user";

@Component({
  selector: "app-top-menu",
  templateUrl: "./top-menu.component.html",
})
export class TopMenuComponent implements OnInit {
  public currencies = ["USD", "EUR"];
  public currency: any;
  public isLogin: boolean;
  public roles: any;
  public user: User;

  public settings: Settings;
  constructor(
    public appSettings: AppSettings,
    public appService: AppService,
    public translateService: TranslateService,
    public authService: AuthService,
  ) {
    this.settings = this.appSettings.settings;
    this.authService.isLogin.subscribe((value) => {
      this.isLogin = value;
    });
    this.authService.user.subscribe((value) => {
      this.user = value;
    });
  }

  ngOnInit() {
    this.currency = this.currencies[0];
  }

  public changeCurrency(currency) {
    this.currency = currency;
  }

  public changeLang(lang: string) {
    this.translateService.use(lang);
  }

  public getLangText(lang) {
    switch (lang) {
      case "de":
        return "German";
      case "fr":
        return "French";
      case "ru":
        return "Russian";
      case "tr":
        return "Turkish";
      case "vi":
        return "Vietnamese";
      default:
        return "English";
    }
  }
  public get ROLE() {
    return ROLE;
  }

  public logout(): void {
    this.authService.logout();
  }
}
