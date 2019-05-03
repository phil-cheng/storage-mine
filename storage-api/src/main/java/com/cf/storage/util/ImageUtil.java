package com.cf.storage.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;
/** 
 *<p>类名称     ：com.fst.util.WaterMarkUtils<p>
 *<p> 描述          ：图片工具类<p>
 *<p> 创建人     ：Janeway<p>
 *<p> 创建日期：2018年1月15日<p>
 *<p> 修改人     ：<p>
 *<p> 修改描述：<p>
 */
public class ImageUtil {

    /** 
    *<p> 方法名     :addWaterMark<p>
    *<p> 方法描述:给图片加字体 <p>
    *<p> 逻辑描述: <p>
    * @param srcImgPath 源图片路径
    * @param tarImgPath 保存的图片路径
    * @param waterMarkContent 水印内容
    * @param markContentColor 水印颜色
    * @param font 水印字体
    * @param x 字体x坐标
    * @param y 字体y坐标
    */ 
    public static void addWaterMark(String srcImgPath, String tarImgPath,Color markContentColor, 
            String waterMarkContent,Font font,int x,int y) {

        try {
            // 读取原图片信息
            File srcImgFile = new File(srcImgPath);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(markContentColor); //根据图片的背景设置水印颜色
            g.setFont(font);              //设置字体

            //设置水印的坐标
            g.drawString(waterMarkContent, x, y);  //画出水印
            g.dispose();  
            // 输出图片  
            FileOutputStream outImgStream = new FileOutputStream(tarImgPath);  
            ImageIO.write(bufImg, "jpg", outImgStream);
            System.out.println("添加水印完成");  
            outImgStream.flush();  
            outImgStream.close();  

        } catch (Exception e) {
        }
    }
    
    /** 
    *<p> 方法名     :mergeImg<p>
    *<p> 方法描述: 合并两张图片<p>
    *<p> 逻辑描述: <p>
    * @param srcImgPath  图片路径
    * @param bacImgPath  背景图路径
    * @param width  图片宽
    * @param height  图片高
    * @param x  图片放置x坐标
    * @param y  图片放置y坐标
    * @param tarImgPath  输出地址
    * @throws IOException 
    */ 
    public static void mergeImg(String srcImgPath,String bacImgPath,int width,int height,int x,int y, String tarImgPath) {
        
        try {
            BufferedImage image = ImageIO.read(new File(srcImgPath));
            BufferedImage bg= ImageIO.read(new File(bacImgPath));
            Graphics2D g=bg.createGraphics();
            g.drawImage(image,x,y,width,height,null);
            g.dispose();
            bg.flush();
            image.flush();
            ImageIO.write(bg,"png",new File(tarImgPath));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
       
    }
    
    /** 
    *<p> 方法名     :downUrlImage<p>
    *<p> 方法描述: 下载网络图片<p>
    *<p> 逻辑描述: <p>
    * @param url    网络图片路径
    * @param outPath    输出路径
    * @throws Exception 
    */ 
    public static void downUrlImage(URL url,String outPath) throws Exception{
        //打开链接  
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
        //设置请求方式为"GET"  
        conn.setRequestMethod("GET");  
        //超时响应时间为5秒  
        conn.setConnectTimeout(5 * 1000);  
        //通过输入流获取图片数据  
        InputStream inStream = conn.getInputStream();  
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性  
        byte[] data = readInputStream(inStream);  
        //new一个文件对象用来保存图片，默认保存当前工程根目录  
        File imageFile = new File(outPath);  
        //创建输出流  
        FileOutputStream outStream = new FileOutputStream(imageFile);  
        //写入数据  
        outStream.write(data);  
        //关闭输出流  
        outStream.close();
    }
    
    public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //创建一个Buffer字符串  
        byte[] buffer = new byte[1024];  
        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
        int len = 0;  
        //使用一个输入流从buffer里把数据读取出来  
        while( (len=inStream.read(buffer)) != -1 ){  
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
            outStream.write(buffer, 0, len);  
        }  
        //关闭输入流  
        inStream.close();  
        //把outStream里的数据写入内存  
        return outStream.toByteArray();  
    }  
    
    public static void main(String[] args) throws Exception {
//        String srcImgPath="E:/bj.jpg"; //源图片地址
//        String tarImgPath="E:/nbj.jpg"; //待存储的地址
//        String waterMarkContent="我是章德帅";  //水印内容
//        new ImageUtil().addWaterMark(srcImgPath, tarImgPath, new Color(60,60,60,255), waterMarkContent,new Font("华文细黑", Font.BOLD, 25),149,68);
//        mergeImg("E:/qr.jpg","E:/nbj.jpg",108,341,"E:/final.jpg");
//        mergeImg("E:/tx.png","E:/final.jpg",100,100,28,28,"E:/final1.jpg");
        //new一个URL对象  
        URL url = new URL("http://wx.qlogo.cn/mmopen/9vSgicy3VNEcacmcqXDwHwoghiawk6z6U0xqtrtFHEYpFibDm3KsCiaF2KvKEnBKN3t7MMOm0SZofiaaZWkOHQJGPBjnWvAwzCzmC/0");  
        downUrlImage(url,"E:\\BeautyGirl.jpg");
    }
}