import { AfterViewInit, Component, Input } from '@angular/core';

declare var bootstrap: any

@Component({
  selector: 'app-modal-dialog',
  templateUrl: './modal-dialog.component.html'
})
export class ModalDialogComponent implements AfterViewInit {

  dialog: any

  @Input()
  modalId: any

  @Input()
  modalTitle: any

  @Input()
  modalTitleIcon: any

  ngAfterViewInit(): void {
    this.dialog = new bootstrap.Modal(`#${this.modalId}`)
  }

  show() {
    this.dialog.show()
  }

  hide() {
    this.dialog.hide()
  }

}
