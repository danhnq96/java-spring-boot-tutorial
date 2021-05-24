import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FuseConfigService } from '@fuse/services/config.service';
import { fuseAnimations } from '@fuse/animations';
import { TokenStorageService } from '../auth/token-storage.service';
import { AuthLoginInfo } from '../auth/login-info';
import { AuthJwtService } from '../auth/auth-jwt.service';
import { Router } from '@angular/router';
import { Notification } from '../commons/Notification';
import { AngularFireDatabase } from '@angular/fire/database';
@Component({
    selector: 'login-admin',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
    encapsulation: ViewEncapsulation.None,
    animations: fuseAnimations
})

export class LoginComponent implements OnInit {
    loginForm: FormGroup;
    userInfo: AuthLoginInfo;
    errStatus: number;
    /**
     * Constructor
     *
     * @param {FuseConfigService} fuseConfigService
     * @param {FormBuilder} formBuilder
     */
    constructor(
        private fuseConfigService: FuseConfigService,
        private formBuilder: FormBuilder,
        private auth: AuthJwtService,
        private router: Router,
        private tokenStorage: TokenStorageService,
        private firebaseDB: AngularFireDatabase,
    ) {
        // Configure the layout
        this.fuseConfigService.config = {
            layout: {
                navbar: {
                    hidden: true
                },
                toolbar: {
                    hidden: true
                },
                footer: {
                    hidden: true
                },
                sidepanel: {
                    hidden: true
                }
            }
        };
    }

    /**
     * On init
     */
    ngOnInit(): void {
        this.loginForm = this.formBuilder.group({
            username: ['', [Validators.required]],
            password: ['', Validators.required]
        });
        if (this.tokenStorage.getToken()) {
            this.router.navigate(['/home']);
        }
    }
    onSubmit() {
        this.userInfo = new AuthLoginInfo(this.fusername.value, this.fpassword.value);
        this.login(this.userInfo);
    }

    get fusername() {
        return this.loginForm.get('username');
    }

    get fpassword() {
        return this.loginForm.get('password');
    }

    public login(userInfo) {
        Notification.showWaiting();
        this.auth.attemptAuth(userInfo).then(data => {
            this.tokenStorage.saveToken(data.token);
            this.tokenStorage.saveUsername(data.username);
            this.tokenStorage.saveAccountBasicInfo(data.accountBasicInfoDTO);
            this.firebaseDB.database.ref(`users/${data.username}/status`).set("online");
            this.router.navigateByUrl('/home');
        }).catch(error => {
            this.errStatus = error.status;
            console.log(error);
        })
        Notification.clearWaitNoMessage();
    }

}
