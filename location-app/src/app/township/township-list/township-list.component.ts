import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-township-list',
  templateUrl: './township-list.component.html'
})
export class TownshipListComponent {

  @Output()
  onEdit = new EventEmitter

  @Input()
  townships: any[] = []

  edit(data: any) {
    this.onEdit.emit(data)
  }

}
