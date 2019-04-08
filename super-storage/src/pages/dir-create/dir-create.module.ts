import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { DirCreatePage } from './dir-create';
import {TranslateModule} from "@ngx-translate/core";

@NgModule({
  declarations: [
    DirCreatePage,
  ],
  imports: [
    IonicPageModule.forChild(DirCreatePage),
    TranslateModule.forChild()
  ],
  exports: [
    DirCreatePage
  ]
})
export class DirCreatePageModule {}
