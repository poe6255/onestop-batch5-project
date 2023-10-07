import { Component, OnInit, ViewChild } from '@angular/core';
import { LoginFormComponent } from './util/widgets/login-form/login-form.component';
import { SecurityService } from './util/services/security.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  isActive: boolean = false

  constructor(private secuService: SecurityService) {}

  ngOnInit(): void {
      this.isActive = this.secuService.user ? true : false
  }

  @ViewChild(LoginFormComponent)
  loginForm!: LoginFormComponent

  signIn(isIn: boolean) {
    this.loginForm.show(isIn ? 'Sign In' : 'Sign Up')
  }

  loginSuccess(success: boolean) {
    this.isActive = success
  }

  logOut() {
    this.isActive = false
    this.secuService.logOut()
  }

}
