import { Injectable } from '@angular/core';

import { Item } from '../../models/item';
import { Api } from '../api/api';

@Injectable()
export class Items {

  constructor(public api: Api) { }

  query(params?: any) {
    return this.api.get('items/queryItems', params);
  }

  add(item: Item) {
    return this.api.post('items/addItem',item);
  }

  // 新增文件夹
  addDir(item: any){
     return this.api.post('items/addDir',item);
  }

  // 删除文件（如果是文件夹则删除子文件夹）
  delete(item: Item) {
    return this.api.post('items/delItems',item);
  }

  queryItemByImage(item: any){
    return this.api.post('items/queryItemsByImage',item);
  }

}
