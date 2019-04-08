import { Component } from '@angular/core';
import { IonicPage, ModalController, NavController, PopoverController} from 'ionic-angular';

import { Item } from '../../models/item';
import { Items } from '../../providers';

@IonicPage()
@Component({
  selector: 'page-list-master',
  templateUrl: 'list-master.html'
})
export class ListMasterPage {
  currentItems: Item[];
  dirItems: Item[];

  constructor(public navCtrl: NavController, public items: Items, public modalCtrl: ModalController,
              public popoverCtrl: PopoverController) {
    this.currentItems = this.items.query();
    this.dirItems = this.items.queryDir();
  }

  /**
   * The view loaded, let's query our items for the list
   */
  ionViewDidLoad() {
  }


  /**
   * Delete an item from the list of items.
   */
  deleteItem(item) {
    this.items.delete(item);
  }

  /**
   * Navigate to the detail page for this item.
   */
  openItem(item: Item) {
    this.navCtrl.push('ItemDetailPage', {
      item: item
    });
  }

  // 打开新增工具栏
  presentPopover(myEvent) {
    // 通过组件别名创建
    let popover = this.popoverCtrl.create('PopoverPage');
    popover.present({
      ev: myEvent
    });
  }
}
