import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RegistrationRequest } from '../../services/models';
import { AuthenticationService } from '../../services/services';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  registerRequest: RegistrationRequest = {  email: "",firstname:"",lastname: "",password:""}

  errorMsg: Array<string> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationService,
  ){
}
  register():void {
    this.errorMsg = [];
    this.authService.register({body: this.registerRequest}).subscribe({
      next: (res) => {
        this.router.navigate(['activate-account']);
      },
      error: (err) => {
          this.errorMsg = err.error.validationErrors;
      }
    })
  }
  login(): void{
    this.router.navigate(['login'])
}
}
