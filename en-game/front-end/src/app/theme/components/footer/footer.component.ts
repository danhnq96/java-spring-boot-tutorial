import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-footer",
  templateUrl: "./footer.component.html",
  styleUrls: ["./footer.component.scss"],
})
export class FooterComponent implements OnInit {
  public lat = 16.069661729447823;
  public lng = 108.22259572666994;
  public zoom = 15;
  public currentYear = new Date().getFullYear();

  constructor() {}

  ngOnInit() {}

  subscribe() {}
}
