package com.cf.storage.util;

import java.math.BigDecimal;

/** 
 *<p>类名称     ：com.fst.util.DoubleUtil<p>
 *<p> 描述          ：小数工具类<p>
 *<p> 创建人     ：Janeway<p>
 *<p> 创建日期：2018年1月19日<p>
 *<p> 修改人     ：<p>
 *<p> 修改描述：<p>
 */
public class DoubleUtil {

    /** 
    *<p> 方法名     :getFormatDouble<p>
    *<p> 方法描述: 返回四舍五入的保留x位小数<p>
    *<p> 逻辑描述: <p>
    * @param d 传入小数
    * @param n 保留几位
    * @return 
    */ 
    public static Double getFormatDouble(Double d,int n){
        BigDecimal big  = new BigDecimal(d); 
        Double db = big.setScale(n,BigDecimal.ROUND_HALF_UP).doubleValue();
        return db; 
    }
}
