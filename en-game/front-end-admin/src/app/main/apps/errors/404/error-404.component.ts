import { Component, ViewEncapsulation } from '@angular/core';
import { Router } from "@angular/router";
import { TokenStorageService } from 'app/main/auth/token-storage.service';
@Component({
    selector: 'error-404',
    templateUrl: './error-404.component.html',
    styleUrls: ['./error-404.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class Error404Component {
    /**
     * Constructor
     */
    constructor(private router: Router, private token: TokenStorageService) {
        if (!this.token.getToken()) {
            this.router.navigate(["/login"]);
        }
    }
}
