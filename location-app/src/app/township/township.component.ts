import { Component, OnInit } from '@angular/core';
import { TownshipService } from '../util/services/township.service';

declare var bootstrap: any

@Component({
  selector: 'app-township',
  templateUrl: './township.component.html'
})
export class TownshipComponent implements OnInit {

  townshipModal: any
  townships: any[] = []
  targetTownship: any

  constructor(private tspService: TownshipService) {}

  ngOnInit(): void {
    this.townshipModal = new bootstrap.Modal('#townshipModalId')
    this.search(undefined)
  }

  search(form: any) {
    this.tspService.search(form).subscribe(resp => this.townships = resp)
  }

  openTownshipForm(event: any) {
    this.targetTownship = {}
    if(event) {
      this.townshipModal.show()
    }
  }

  saveTownship(township: any) {
    this.tspService.save(township).subscribe(resp => {
      if(resp) {
        this.townshipModal.hide()
        this.search(undefined)
      }
    })
  }

  editTownship(data: any) {
    this.targetTownship = data
    this.townshipModal.show()
  }

}
