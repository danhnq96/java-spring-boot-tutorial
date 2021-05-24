import { Component, OnInit, Input } from "@angular/core";
import { ROLE } from "../../constants";
import { User } from "../../../dto/user";
import { AuthService } from "../../../services/AuthService";

@Component({
  selector: "app-menu",
  templateUrl: "./menu.component.html",
  styleUrls: ["./menu.component.scss"],
})
export class MenuComponent implements OnInit {
  public user: User;
  public isLogin: boolean;

  constructor(public authService: AuthService) {
    this.authService.isLogin.subscribe((value) => {
      this.isLogin = value;
    });
    this.authService.user.subscribe((value) => {
      this.user = value;
    });
  }

  ngOnInit() {}

  openMegaMenu() {
    const pane = document.getElementsByClassName("cdk-overlay-pane");
    [].forEach.call(pane, function (el) {
      if (el.children.length > 0) {
        if (el.children[0].classList.contains("mega-menu")) {
          el.classList.add("mega-menu-pane");
        }
      }
    });
  }

  public get ROLE() {
    return ROLE;
  }
}
