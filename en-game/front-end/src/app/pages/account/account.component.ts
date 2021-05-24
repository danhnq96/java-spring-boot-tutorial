import { Component, OnInit, ViewChild, HostListener } from "@angular/core";
import { Router, NavigationEnd } from "@angular/router";
import { AuthService } from "../../services/AuthService";

@Component({
  selector: "app-account",
  templateUrl: "./account.component.html",
  styleUrls: ["./account.component.scss"],
})
export class AccountComponent implements OnInit {
  @ViewChild("sidenav", { static: true }) sidenav: any;
  public sidenavOpen = true;
  public links = [
    { name: "SETTINGS.DASH_BOARD.ACCOUNT_DASH_BOARD", href: "dashboard", icon: "dashboard" },
    { name: "SETTINGS.ACCOUNT.ROOT", href: "information", icon: "info" },
    { name: "SETTINGS.ADDRESSES.ROOT", href: "addresses", icon: "location_on" },
    { name: "SETTINGS.ORDER_HISTORY.ROOT", href: "orders", icon: "add_shopping_cart" },
    { name: "LOGOUT", href: "#", icon: "power_settings_new" },
  ];
  constructor(public router: Router, public authService: AuthService) {}

  ngOnInit() {
    if (window.innerWidth < 960) {
      this.sidenavOpen = false;
    }
  }

  @HostListener("window:resize")
  public onWindowResize(): void {
    window.innerWidth < 960 ? (this.sidenavOpen = false) : (this.sidenavOpen = true);
  }

  ngAfterViewInit() {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        if (window.innerWidth < 960) {
          this.sidenav.close();
        }
      }
    });
  }
}
