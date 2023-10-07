import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DivisionService } from '../util/services/division.service';
import { Subscription } from 'rxjs';

declare const bootstrap: any

@Component({
  selector: 'app-division',
  templateUrl: './division.component.html'
})
export class DivisionComponent implements OnInit, OnDestroy {
  private findAllRegionSub?: Subscription
  private searchSub?: Subscription
  private createSub?: Subscription
  private deleteSub?: Subscription
  private uploadSub?: Subscription

  regions: string[] = []

  divisions: any[] = []

  targetDivision: any
  divisionModal: any
  searchForm: FormGroup

  constructor(fb: FormBuilder, private divService: DivisionService) {
    this.searchForm = fb.group({
      region: '',
      keyword: ''
    })
  }

  ngOnInit(): void {
    this.search()
    this.findAllRegionSub = this.divService.findAllRegions().subscribe(resp => this.regions = resp)
    this.divisionModal = new bootstrap.Modal('#divisionModalId')
  }

  search() {
    this.searchSub = this.divService.search(this.searchForm.value).subscribe(resp => {
      this.divisions = resp
      this.divService.findAllRegions().subscribe(result => this.regions = result)
    })
  }

  addNew() {
    this.targetDivision = {}
    this.divisionModal.show()
  }

  saveDivision(form: any) {
    this.createSub = this.divService.create(form).subscribe(resp => {
      if(resp) {
        this.divisionModal.hide()
        this.search()
      }
    })
  }

  edit(div: any) {
    this.targetDivision = div
    this.divisionModal.show()
  }

  delete(id: number) {
    this.deleteSub = this.divService.delete(id).subscribe(resp => {
      if(resp)
        this.search()
    })
  }

  uploadDivision(files: FileList) {
    this.uploadSub = this.divService.upload(files[0]).subscribe(resp => {
      if(resp)
        this.search()
    })
  }

  ngOnDestroy(): void {
    this.findAllRegionSub?.unsubscribe()
    this.searchSub?.unsubscribe()
    this.createSub?.unsubscribe()
    this.deleteSub?.unsubscribe()
    this.uploadSub?.unsubscribe()
  }

}
