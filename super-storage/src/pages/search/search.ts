import { Component,ViewChild} from '@angular/core';
import { IonicPage, NavController, NavParams, AlertController, ToastController} from 'ionic-angular';
import {Camera} from "@ionic-native/camera";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

import { Item } from '../../models/item';
import { Items, Settings } from '../../providers';

@IonicPage()
@Component({
  selector: 'page-search',
  templateUrl: 'search.html'
})
export class SearchPage {
  @ViewChild('fileInput') fileInput;

  currentItems: any = [];
  form: FormGroup;
  userId: string;

  constructor(public navCtrl: NavController, public navParams: NavParams, public items: Items,
    formBuilder: FormBuilder, public camera: Camera,
    public alertCtrl: AlertController, public toastCtrl: ToastController,
    public settings: Settings) {
      
    this.form = formBuilder.group({
      profilePic: ['', Validators.required]
    });

    this.settings.getUser().then((user)=>{
      if(user && user.tokenId){
        this.userId = user.tokenId;
      }
    });
   }

  /**
   * Perform a service for the proper items.
   */
  getItems(ev) {
    let val = ev.target.value;
    if (!val || !val.trim()) {
      this.currentItems = [];
      return;
    }
    let params = {
      rows: 10,
      page: 1,
      userId: this.userId,
      searchName: val
    };
    this.items.query(params).subscribe((res: any) => {
      if(res.code && res.code == "000000"){
        this.currentItems = res.data.rows;
      }else{
        let alert = this.alertCtrl.create({
          title: '错误',
          subTitle: res.msg,
          buttons: ['确认']
        });
        alert.present();
      }
    }, (err) => {
      // Unable to log in
      let toast = this.toastCtrl.create({
        message: "网络异常",
        duration: 3000,
        position: 'top'
      });
      toast.present();
    });
  }

  /**
   * Navigate to the detail page for this item.
   */
  openItem(item: Item) {
    this.navCtrl.push('ItemDetailPage', {
      item: item
    });
  }

  // 选择照片
  getPicture() {
    if (Camera['installed']()) {
      this.camera.getPicture({
        destinationType: this.camera.DestinationType.DATA_URL,
        targetWidth: 96,
        targetHeight: 96
      }).then((data) => {
        this.form.patchValue({ 'profilePic': 'data:image/jpg;base64,' + data });
        this.queryItemByImage();
      }, (err) => {
        alert('Unable to take photo');
      })
    } else {
      this.fileInput.nativeElement.click();
    }
  }

  // 网页选择文件
  processWebImage(event) {
    let reader = new FileReader();
    reader.onload = (readerEvent) => {
      let imageData = (readerEvent.target as any).result;
      this.form.patchValue({ 'profilePic': imageData });
      this.queryItemByImage();
    };
    reader.readAsDataURL(event.target.files[0]);
  }

  // 图片查找
  queryItemByImage() {
    debugger;
    let params = this.form.value;
    params['userId'] = this.userId;
    this.items.queryItemByImage(params).subscribe((res: any) => {
      if(res.code && res.code == "000000"){
        this.currentItems = res.data.rows;
      }else{
        let alert = this.alertCtrl.create({
          title: '错误',
          subTitle: res.msg,
          buttons: ['确认']
        });
        alert.present();
      }
    }, (err) => {
      // Unable to log in
      let toast = this.toastCtrl.create({
        message: "网络异常",
        duration: 3000,
        position: 'top'
      });
      toast.present();
    });
  }

}
