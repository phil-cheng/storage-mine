import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { IonicPage, NavController, ToastController, AlertController } from 'ionic-angular';

import { User,Settings } from '../../providers';
import { MainPage } from '../';


@IonicPage()
@Component({
  selector: 'page-signup',
  templateUrl: 'signup.html'
})
export class SignupPage {
  // The account fields for the login form.
  // If you're using the username field with or without email, make
  // sure to add it to the type
  account: { name: string, email: string, password: string, repeatPassword: string } = {
    name: '',
    email: '',
    password: '',
    repeatPassword: ''
  };

  // Our translated text strings
  private signupErrorString: string;

  constructor(public navCtrl: NavController,
    public user: User,
    public toastCtrl: ToastController,
    public translateService: TranslateService,
    public alertCtrl: AlertController,
    public settings: Settings) {

    this.translateService.get('SIGNUP_ERROR').subscribe((value) => {
      this.signupErrorString = value;
    })
  }

  doSignup() {
    // email格式验证
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
    // 口令验证
    if(this.account.password && this.account.repeatPassword){
      if(this.account.password !== this.account.repeatPassword){
        let alert = this.alertCtrl.create({
          title: '错误',
          subTitle: '两次输入口令不一致',
          buttons: ['确认']
        });
        alert.present();
        return false;
      }
    }

    // Attempt to login in through our User service
    this.user.signup(this.account).subscribe((res: any) => {
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
      // Unable to sign up
      let toast = this.toastCtrl.create({
        message: this.signupErrorString,
        duration: 3000,
        position: 'top'
      });
      toast.present();
    });
  }
}
