import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { IonicPage, NavController, ToastController, AlertController } from 'ionic-angular';

import { User,Settings } from '../../providers';
import { MainPage } from '../';

@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html'
})
export class LoginPage {
  // The account fields for the login form.
  // If you're using the username field with or without email, make
  // sure to add it to the type
  account: { email: string, password: string } = {
    email: '',
    password: ''
  };

  // Our translated text strings
  private loginErrorString: string;

  constructor(public navCtrl: NavController,
    public user: User,
    public toastCtrl: ToastController,
    public translateService: TranslateService,
    public alertCtrl: AlertController,
    public settings: Settings) {

    this.translateService.get('LOGIN_ERROR').subscribe((value) => {
      this.loginErrorString = value;
    })
  }

  // Attempt to login in through our User service
  doLogin() {
    // 邮箱验证
    if(this.account.email){
      let emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
      if(!emailReg.test(this.account.email)){
        let alert = this.alertCtrl.create({
          title: '错误',
          subTitle: '请输入有效的email地址',
          buttons: ['确认']
        });
        alert.present();
        return false;
      }
    }

    // 登录
    this.user.login(this.account).subscribe((res: any) => {
      if(res.code && res.code == "000000"){
        // 保存用户信息
        this.settings.setUser({
          "email": this.account.email,
          "tokenId": res.data.tokenId
        });
        // 页面跳转
        this.navCtrl.push(MainPage);
      }else{
        let toast = this.toastCtrl.create({
          message: res.msg,
          duration: 3000,
          position: 'top'
        });
        toast.present();
      }
    }, (err) => {
      // Unable to log in
      let toast = this.toastCtrl.create({
        message: this.loginErrorString,
        duration: 3000,
        position: 'top'
      });
      toast.present();
    });
  }
}
