import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

import { Items } from '../../providers';

@IonicPage()
@Component({
  selector: 'page-item-detail',
  templateUrl: 'item-detail.html'
})
export class ItemDetailPage {
  item: any;
  createTime: string;

  constructor(public navCtrl: NavController, navParams: NavParams, items: Items) {
     this.item = navParams.get('item');
     this.createTime='2019-01-01';
     console.log(this.item);
  }

}
