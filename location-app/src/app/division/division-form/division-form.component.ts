import { Component, EventEmitter, OnInit, Output, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { REGIONS } from '../../util/dto/regions';

@Component({
  selector: 'app-division-form',
  templateUrl: './division-form.component.html'
})
export class DivisionFormComponent implements OnInit {

  regions = REGIONS

  @Output()
  onSave = new EventEmitter<any>

  createForm: FormGroup

  constructor(fb: FormBuilder) {
    this.createForm = fb.group({
      id: 0,
      name: ['', Validators.required],
      burmese: ['', Validators.required],
      region: ['', Validators.required],
      capital: ['', Validators.required]
    })
  }

  ngOnInit(): void {

  }

  @Input()
  set data(data: any) {
    this.initForm()
    if(data) {
      this.createForm.patchValue(data)
    }
  }

  save() {
    if(this.createForm.valid) {
      this.onSave.emit(this.createForm.value)
    }
  }

  initForm() {
    this.createForm.patchValue({
      id: 0,
      name: '',
      burmese: '',
      region: '',
      capital: ''
    })
  }

}
