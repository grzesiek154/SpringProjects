import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { error } from "protractor";
import { BehaviorSubject, Observable, throwError } from "rxjs";
import { catchError, switchMap } from "rxjs/operators";
import { AuthService } from "../services/auth.service";
import { LoginResponse } from "./LoginResponse";

@Injectable({
    providedIn: 'root'
})
export class TokenInterceptor implements HttpInterceptor {

    isTokenRefreshing = false;
    refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject(null);
  
    
    constructor(public authService: AuthService) {}


    intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {

        // Inside the intercept method, we receive the JWT through authService.getJwtToken(), if the Token is valid, we add the token to the Authorization Header which contains a value according to the Bearer scheme.

     
        const jwtToken = this.authService.getJwtToken();

        if (jwtToken) {
            this.addToken(req, jwtToken);
        }
            return next.handle(this.addToken(req, jwtToken)).pipe(catchError(error => {
                if (error instanceof HttpErrorResponse
                    && error.status === 403) {
                    return this.handleAuthErrors(req, next);
                    // If the token is invalid, due to a 403 error, then we will request a new JWT by calling the authService.refreshToken()
                } else {
                    return throwError(error);
                }
            }));   
}

    private handleAuthErrors(req: HttpRequest<any>, next: HttpHandler) {
        if (!this.isTokenRefreshing) {
            this.isTokenRefreshing = true;
            this.refreshTokenSubject.next(null);

            // In this case, we queue all the corresponding requests to the backend and keep them on hold, until our Token Refresh process is completed.

            return this.authService.refreshToken().pipe(
                switchMap((refreshTokenResponse: LoginResponse) => {
                    this.isTokenRefreshing = false;
                    this.refreshTokenSubject.next(refreshTokenResponse.authenticationToken);
                    return next.handle(this.addToken(req, refreshTokenResponse.authenticationToken));
                    //Once the refresh is completed, we release the requests again, we will accomplish this using a BehaviorSubject
                })
            )
        }
    }

    private addToken(req: HttpRequest<any>, jwtToken: string) {
        return req.clone({
            headers: req.headers.set('Authorization',
                'Bearer ' + jwtToken)
        });
    }
}