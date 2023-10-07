import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-master-data',
  templateUrl: './master-data.component.html'
})
export class MasterDataComponent {

  @Input()
  title: any

  @Input()
  icon: any

}
