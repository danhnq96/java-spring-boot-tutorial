import { Component, OnInit, HostListener, ViewChild, Inject, PLATFORM_ID } from "@angular/core";
import { Router, NavigationEnd } from "@angular/router";
import { Settings, AppSettings } from "../app.settings";
import { AppService } from "../app.service";
import { Category } from "../app.models";
import { SidenavMenuService } from "../theme/components/sidenav-menu/sidenav-menu.service";
import { isPlatformBrowser } from "@angular/common";

@Component({
  selector: "app-pages",
  templateUrl: "./pages.component.html",
  styleUrls: ["./pages.component.scss"],
  providers: [SidenavMenuService],
})
export class PagesComponent implements OnInit {
  public showBackToTop = false;
  public categories: Category[];
  public category: Category;
  public sidenavMenuItems: Array<any>;
  @ViewChild("sidenav", { static: true }) sidenav: any;

  public settings: Settings;
  constructor(
    public appSettings: AppSettings,
    public appService: AppService,
    public sidenavMenuService: SidenavMenuService,
    public router: Router,
    @Inject(PLATFORM_ID) private platformId: object,
  ) {
    this.settings = this.appSettings.settings;
  }

  ngOnInit() {
    this.getCategories();
    this.appService.getCarts();
    this.sidenavMenuItems = this.sidenavMenuService.getSidenavMenuItems();
    setTimeout(() => {
      this.settings.theme = "blue";
    });
  }

  public getCategories() {
    this.appService.getCategories().subscribe((data) => {
      this.categories = data;
      this.category = data[0];
      this.appService.Data.categories = data;
    });
  }

  public changeCategory(event) {
    if (event.target) {
      this.category = this.categories.filter((category) => category.name === event.target.innerText)[0];
    }
    if (window.innerWidth < 960) {
      this.stopClickPropagate(event);
    }
  }

  public remove(product) {
    const index: number = this.appService.Data.cartList.indexOf(product);
    if (index !== -1) {
      this.appService.resetProductCartCount(product);
    }
  }

  public clear() {
    // this.appService.Data.cartList.forEach((product) => {
    //   this.appService.resetProductCartCount(product);
    // });
    // this.appService.Data.cartList.length = 0;
    // this.appService.Data.totalPrice = 0;
    // this.appService.Data.totalCartCount = 0;
    this.appService.clearCarts();
  }

  public changeTheme(theme) {
    this.settings.theme = theme;
  }

  public stopClickPropagate(event: any) {
    event.stopPropagation();
    event.preventDefault();
  }

  public search() {}

  public scrollToTop() {
    const scrollDuration = 200;
    const scrollStep = -window.pageYOffset / (scrollDuration / 20);
    const scrollInterval = setInterval(() => {
      if (window.pageYOffset !== 0) {
        window.scrollBy(0, scrollStep);
      } else {
        clearInterval(scrollInterval);
      }
    }, 10);
    if (window.innerWidth <= 768) {
      setTimeout(() => {
        if (isPlatformBrowser(this.platformId)) {
          window.scrollTo(0, 0);
        }
      });
    }
  }
  @HostListener("window:scroll", ["$event"])
  onWindowScroll($event) {
    $event.target.documentElement.scrollTop > 300 ? (this.showBackToTop = true) : (this.showBackToTop = false);
  }

  ngAfterViewInit() {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.sidenav.close();
      }
    });
    this.sidenavMenuService.expandActiveSubMenu(this.sidenavMenuService.getSidenavMenuItems());
  }

  public closeSubMenus() {
    if (window.innerWidth < 960) {
      this.sidenavMenuService.closeAllSubMenus();
    }
  }
}
