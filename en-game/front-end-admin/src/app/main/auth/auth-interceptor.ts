import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor } from '@angular/common/http';

import { TokenStorageService } from './token-storage.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    TOKEN_HEADER_KEY = 'Authorization';
    constructor(private token: TokenStorageService) { }

    intercept(req, next) {
        let authReq = req;
        const token = this.token.getToken();
        if (token != null) {
            authReq = req.clone({
                setHeaders: {
                    'Authorization': `Bearer ${token}`,
                    'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
                },
            });
        }
        return next.handle(authReq);
    }
}

export const httpInterceptorProviders = [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];