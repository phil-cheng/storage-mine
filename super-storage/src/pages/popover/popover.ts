import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ViewController } from 'ionic-angular';

// 声明成一个ionicPage,name名默认为PopoverPage
@IonicPage()
@Component({
  selector: 'page-popover',
  template: `
    <ion-list>
      <button ion-item (click)="close()">新增收藏夹</button>
      <button ion-item (click)="close()">新增物品</button>
    </ion-list>
  `
})
export class PopoverPage {
  constructor(public navCtrl: NavController, public viewCtrl: ViewController, public navParams: NavParams) {
  }
  ionViewDidLoad() {
    console.log('ionViewDidLoad PopoverPage');
  }
  close() {
    this.viewCtrl.dismiss();
  }

}
