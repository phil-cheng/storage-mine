import { Component } from '@angular/core';
import {IonicPage, ModalController, NavController, NavParams, ViewController} from 'ionic-angular';

import { Items } from '../../providers';

// 声明成一个ionicPage,name名默认为PopoverPage
@IonicPage()
@Component({
  selector: 'page-popover',
  template: `
    <ion-list>
      <button ion-item (click)="addDir()">新增收藏夹</button>
      <button ion-item (click)="addItem()">新增物品</button>
    </ion-list>
  `
})
export class PopoverPage {
  constructor(public navCtrl: NavController, public viewCtrl: ViewController,
              public navParams: NavParams, public modalCtrl: ModalController,
              public items: Items) {
  }

 // 新增物品
  addItem() {
    // 隐藏popover
    // this.viewCtrl.dismiss();
    // 创建新窗口
    let addModal = this.modalCtrl.create('ItemCreatePage');
    addModal.onDidDismiss(item => {
      this.viewCtrl.dismiss(1);
    });
    addModal.present();
  }

  // 创建文件夹
  addDir() {
    // 隐藏popover
    //  this.viewCtrl.dismiss();
    // 创建新窗口
    let addModal = this.modalCtrl.create('DirCreatePage');
    addModal.onDidDismiss(item => {
      this.viewCtrl.dismiss(1);
    });
    addModal.present();
  }

}
