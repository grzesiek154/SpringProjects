import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
<<<<<<< HEAD
import { timeStamp } from 'console';
=======
>>>>>>> 23246d402559f2cdd6165ead0f051aea5c0bf4d4
import { ToastrService } from 'ngx-toastr';
import { error } from 'protractor';
import { throwError } from 'rxjs';
import { LoginRequestPayload } from 'src/app/models/LoginRequestPayload';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loginRequestPayload: LoginRequestPayload;
  registerSuccessMessage: string;
  isError: boolean;

<<<<<<< HEAD
  constructor(private authService: AuthService, private activatedRoute: ActivatedRoute,
=======
  constructor(private authService: AuthService, private activatedRoute: ActivatedRoute, 
>>>>>>> 23246d402559f2cdd6165ead0f051aea5c0bf4d4
    private router: Router, private toastr: ToastrService) {

    this.loginRequestPayload = {
      username: '',
      password: ''
    };

   }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });

<<<<<<< HEAD
    this.activatedRoute.queryParams
      .subscribe(params => {
        if (params.registered !== undefined && params.registered === 'true') {
            this.toastr.success('Signup Successful');
            this.registerSuccessMessage = 'Please Check your inbox for activation email' +
            'activate your account before you login';
=======

    this.activatedRoute.queryParams
      .subscribe(params => {
        if (params.registered !== undefined && params.registered === 'true') {
          this.toastr.success('Signup Successful');
          this.registerSuccessMessage = 'Please Check your inbox for activation email '
            + 'activate your account before you Login!';
>>>>>>> 23246d402559f2cdd6165ead0f051aea5c0bf4d4
        }
      });
  }
  

  login() {
    this.loginRequestPayload.username = this.loginForm.get('username').value;
    this.loginRequestPayload.password = this.loginForm.get('password').value;

    this.authService.login(this.loginRequestPayload).subscribe(data => {
<<<<<<< HEAD
       if(data) {
         this.isError = false;
         this.router.navigateByUrl('/');
         this.toastr.success('Login Successful');
       } else {
         this.isError = true;
       }
    }, error => {
      this.isError = true;
=======
      if (data) {
        this.isError = false;
        this.router.navigateByUrl('/');
        this.toastr.success('Login Successful');
      } else {
        this.isError = true;
      }
>>>>>>> 23246d402559f2cdd6165ead0f051aea5c0bf4d4
    });
  }

}
