import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";

@Component({
  selector: "app-not-found",
  templateUrl: "./activate.component.html",
  styleUrls: ["./activate.component.scss"],
})
export class ActivateComponent implements OnInit {
  constructor(public router: Router) {}

  ngOnInit() {}

  public goSignIn(): void {
    this.router.navigate(["/sign-in"]);
  }
}
