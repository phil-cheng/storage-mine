package com.cf.storage.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


/** 
 *<p>类名称     ：com.fst.util.QrUtil<p>
 *<p> 描述          ：二维码工具类<p>
 *<p> 创建人     ：Janeway<p>
 *<p> 创建日期：2018年1月15日<p>
 *<p> 修改人     ：<p>
 *<p> 修改描述：<p>
 */
public class QrUtil {
    
    //二维码颜色
    private static final int BLACK = 0xFF000000;
    //二维码背景颜色
    private static final int WHITE = 0xFFFFFFFF;
    // 用于设置QR二维码参数
    private static Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>() {
        private static final long serialVersionUID = 1L;
        {
            put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);// 设置QR二维码的纠错级别（H为最高级别）具体级别信息
            put(EncodeHintType.CHARACTER_SET, "utf-8");// 设置编码方式
            put(EncodeHintType.MARGIN, 0);
        }
    };
    /** 
    *<p> 方法名     :zxingCodeCreate<p>
    *<p> 方法描述:ZXing 方式生成二维码 <p>
    *<p> 逻辑描述: <p>
    * @param text   二维码内容
    * @param width  二维码宽
    * @param height 二维码高
    * @param outPutPath 二维码生成保存路径
    * @param imageType 二维码生成格式
    */ 
    public static String zxingCodeCreate(String text, int width, int height, String outPutPath, String imageType){
        try {
            //1、生成二维码
            BitMatrix encode = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            
            //2、获取二维码宽高
            int codeWidth = encode.getWidth();
            int codeHeight = encode.getHeight();
            
            //3、将二维码放入缓冲流
            BufferedImage image = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < codeWidth; i++) {
                for (int j = 0; j < codeHeight; j++) {
                    //4、循环将二维码内容定入图片
                    image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
                }
            }
            File outPutImage = new File(outPutPath+".png");
            //如果图片不存在创建图片
            if(!outPutImage.exists())
                outPutImage.createNewFile();
            //5、将二维码写入图片
            ImageIO.write(image, imageType, outPutImage);
            return outPutPath+".png";
        } catch (WriterException e) {
            e.printStackTrace();
            System.out.println("二维码生成失败");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("生成二维码图片失败");
        }
        return null;
    }
    
    public static void main(String[] args) throws Exception {  
        zxingCodeCreate("http://weixin.qq.com/q/02NMFw4KXGcZh1vPKTNqca]", 220, 220, "E://qr.jpg", "jpg"); 
        System.out.println("success");  
    }  

}