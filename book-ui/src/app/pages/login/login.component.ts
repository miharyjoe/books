import { Component } from "@angular/core";
import {
  AuthenticationRequest,
  AuthenticationResponse,
} from "../../services/models";
import { FormsModule } from "@angular/forms";
import { CommonModule } from "@angular/common";
import { Router } from "@angular/router";
import { AuthenticationService } from "../../services/services";

@Component({
  selector: "app-login",
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: "./login.component.html",
  styleUrl: "./login.component.scss",
})
export class LoginComponent {
  authRequest: AuthenticationRequest = { email: "", password: "" };
  errorMsg: Array<string> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationService,

  ){

  }
  login(): void{
    this.errorMsg = [];
    this.authService.authenticate({
      body: this.authRequest
    }).subscribe({
      next: () => {
        //todo: save the token
        this.router.navigate(['books']);
      },
      error: (err) => {
        console.log(err);
      }
    })
  }

  register(): void{
      this.router.navigate(['register'])
  }
}
 