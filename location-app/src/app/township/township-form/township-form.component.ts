import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { REGIONS } from '../../util/dto/regions';
import { DivisionService } from '../../util/services/division.service';

@Component({
  selector: 'app-township-form',
  templateUrl: './township-form.component.html'
})
export class TownshipFormComponent implements OnInit {

  divisions: any[] = []

  form: FormGroup = this.fb.group({
    id: 0,
    name: ['', Validators.required],
    burmese: ['', Validators.required],
    divisionId: [0, Validators.required]
  })

  controls = {
    id: this.form.get('id'),
    name: this.form.get('name'),
    burmese: this.form.get('burmese'),
    division: this.form.get('divisionId')
  }

  @Output()
  onSave = new EventEmitter<any>

  constructor(private fb: FormBuilder, private divService: DivisionService) {}

  ngOnInit(): void {
    this.divService.search({}).subscribe(resp => this.divisions = resp)
  }

  @Input()
  set data(data: any) {
    this.initForm()

    if(data) {
      this.form.patchValue({
        id: data.id,
        name: data.name,
        burmese: data.burmese,
        divisionId: data.division.id
      })
    }
  }

  save() {
    if(this.form.valid) {
      this.onSave.emit(this.form.value)
    }
  }

  initForm() {
    this.form.patchValue({
      id: 0,
      name: '',
      burmese: '',
      divisionId: 0
    })
  }

}
