import { Component } from '@angular/core';

import { FuseTranslationLoaderService } from '@fuse/services/translation-loader.service';
import { locale as english } from './i18n/en';
import { locale as turkish } from './i18n/tr';
import { Router } from '@angular/router';
@Component({
    selector: 'home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent {
    /**
     * Constructor
     *
     * @param {FuseTranslationLoaderService} fuseTranslationLoaderService
     */
    constructor(
        private fuseTranslationLoaderService: FuseTranslationLoaderService,
        private router: Router
    ) {
        this.fuseTranslationLoaderService.loadTranslations(english, turkish);
    }
}
