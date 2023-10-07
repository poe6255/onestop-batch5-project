import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-form-group',
  templateUrl: './form-group.component.html'
})
export class FormGroupComponent {

  @Input()
  label?: string

  @Input()
  valid: any

  @Input()
  touch: any

  @Input()
  margin: any

  @Input()
  icon?: string

}
