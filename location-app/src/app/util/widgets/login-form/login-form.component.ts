import { AfterViewInit, Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AccountService } from '../../services/account.service';
import { SecurityService } from '../../services/security.service';

declare var bootstrap: any

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html'
})
export class LoginFormComponent implements AfterViewInit {

  formModal: any
  isIn: boolean = false

  @Input()
  loginFormId!: string

  @Output()
  onSuccess = new EventEmitter<boolean>

  btnText: string = 'Click'

  form: FormGroup

  constructor(fb: FormBuilder,
    private accService: AccountService,
    private secuService: SecurityService) {
    this.form = fb.group({
      loginId: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(5)]]
    })
  }

  ngAfterViewInit(): void {
    this.formModal = new bootstrap.Modal(`#${this.loginFormId}`)
  }

  show(btnText: string) {
    this.btnText = btnText
    this.isIn = btnText == 'Sign In' ? true : false
    this.formModal.show()
  }

  signInOrUp() {
    if(this.form.valid) {

      if(this.isIn)
        this.accService.signIn(this.form.value).subscribe(resp => this.setActiveUser(resp))
      else
        this.accService.signUp(this.form.value).subscribe(resp => this.setActiveUser(resp))
    }
    this.formModal.hide()
  }

  private setActiveUser(data: any) {
    if(data) {
      this.secuService.user = data
      this.onSuccess.emit(true)
    }
  }

  initForm() {
    this.form.patchValue({
      loginId: '',
      password: ''
    })
  }

}
