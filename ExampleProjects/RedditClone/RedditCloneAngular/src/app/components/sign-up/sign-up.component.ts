import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { SignupRequestPayload } from '../../models/SignupRequestPayload';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  signupForm: FormGroup;
  signupRequestPayload: SignupRequestPayload;

  constructor(private autService: AuthService, private router: Router, private toastr: ToastrService) {
    this.signupRequestPayload = {
      username: '',
      email: '',
      password: ''
    };
   }

  ngOnInit(): void {
    this.signupForm = new FormGroup({
      username: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required)
    });
  }


  signup() {
    this.signupRequestPayload.email = this.signupForm.get('email').value;
    this.signupRequestPayload.username = this.signupForm.get('username').value;
    this.signupRequestPayload.password = this.signupForm.get('password').value;

<<<<<<< HEAD
    this.autService.signup(this.signupRequestPayload).subscribe(data =>{
      this.router.navigate(['/login'], { queryParams: { registered: 'true'}});
      // Inside the signup method, if we received success response, we are using the injected router object to navigate to the Login page and notice that we are adding a query param registered:true to communicate with the LoginComponent that registration is successful
    }, error => {
      this.toastr.error('Registration failed! Please try again' + error);
    }); 
=======
    this.autService.signup(this.signupRequestPayload)
      .subscribe(data => {
        this.router.navigate(['/login'],
          { queryParams: { registered: 'true' } });
      }, error => {
        console.log(error);
        this.toastr.error('Registration Failed! Please try again');
      });
>>>>>>> 23246d402559f2cdd6165ead0f051aea5c0bf4d4
  }

}
