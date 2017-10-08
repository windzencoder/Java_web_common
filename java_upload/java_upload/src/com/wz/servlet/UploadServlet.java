package com.wz.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		InputStream is = request.getInputStream();//获取当前流信息
		//临时文件
		String tempFileName = "/Users/Miller/Pictures/upload/tempFile";
		File tempFile = new File(tempFileName);
		FileOutputStream fos = new FileOutputStream(tempFile);
		//写入临时文件
		byte[] b = new byte[1024];
		int n;
		while ((n = is.read(b)) != -1) {
			fos.write(b, 0, n);
		}
		//关闭输入流，输出流
		fos.close();
		is.close();
		
		
		//获取文件名称
		RandomAccessFile randomFile = new RandomAccessFile(tempFile, "r");
		//读取第二行内容
		randomFile.readLine();
		String str = randomFile.readLine();
		int beginIndex = str.lastIndexOf("=")+2;
		int endIndex = str.lastIndexOf("\"");//最后一个引号的位置
		String filename = str.substring(beginIndex, endIndex);
		System.out.println("filename = " + filename);
		
		System.out.println("------------------------------------------------");
		//重新定位文件指针到文件头
		randomFile.seek(0);
		long startPostion = 0;
		int i = 1;
		//文件内容的开始位置
		while ((n = randomFile.readByte()) != -1 && i <= 4) {
			System.out.println((char)(n));
			if (n == '\n') {//换行符
				startPostion = randomFile.getFilePointer();
				i++;
				System.out.println("------------换行");
			}
		}
		startPostion = startPostion ;
		System.out.println("开始位置 "+startPostion);
		System.out.println("------------------------------------------------");
		
		System.out.println("++++++++++++++++++++++++++++");
		
		//文件内容的结束位置
		randomFile.seek(randomFile.length());
        long endPosition = randomFile.getFilePointer();
        int j = 1;
        while(endPosition >= 0 && j<=2){
            endPosition--;
            randomFile.seek(endPosition);
            n = randomFile.readByte();
            System.out.println((char)n);
            if( n == '\n'){
                j++;
                System.out.println("++++++++换行");
            }
        }
        endPosition = endPosition - 1;
		System.out.println("++++++++++++++++++++++++++++");
		System.out.println("结束位置 "+endPosition);
		
		//设置保存文件上传文件的路径
        String realPath = getServletContext().getRealPath("/")+"images";
        System.out.println(realPath);
        
        File fileupload = new File(realPath);
        if(!fileupload.exists()){
            fileupload.mkdir();
        }
        File saveFile = new File(realPath, filename);
        RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile, "rw");
        //从临时文件中读取文件内容（根据起止位置获取）
        randomFile.seek(startPostion);
        byte bt;
        while(startPostion < endPosition){
        		bt = randomFile.readByte();
        		System.out.println("====== "+bt);
            randomAccessFile.write(bt);
            startPostion = randomFile.getFilePointer();
        }
        //关闭输入输出流、删除临时文件
        randomAccessFile.close();
        randomFile.close();
        //tempFile.delete();
        
        request.setAttribute("result", "上传成功！");
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/01.jsp");
        dispatcher.forward(request, response );
		
	}

}
