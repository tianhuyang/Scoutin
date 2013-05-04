package com.scoutin.utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class UploadUtils {
	public final static String uploadFolderName = "upload";
	public final static String rootPath = ServletActionContext.getServletContext().getRealPath(File.separator);
	
	public static String uploadImage(Integer accountId, File file, String fileName, String contentType) throws IOException{
		
		String uploadedImageUrl = getUploadedImageUrl(accountId,fileName,contentType);
		
		File outputImg = new File(rootPath + uploadedImageUrl);
		FileUtils.moveFile(file, outputImg);
		
		return uploadedImageUrl;
	}
	
	public static String uploadImage(Integer accountId, File file, String fileName, String contentType, int startX, int startY, int width, int height) throws IOException{
		
		String uploadedImageUrl = getUploadedImageUrl(accountId,fileName,contentType);
		
		File outputImg = new File(rootPath + uploadedImageUrl);
		BufferedImage img = ImageIO.read(file);
		BufferedImage croppedImg = img.getSubimage(startX, startY, width, height);
		ImageIO.write(croppedImg, getFileExtension(fileName), outputImg);
		
		return uploadedImageUrl;
	}
	
	private static String getUploadedImageUrl(Integer accountId, String fileName, String contentType){
		File userFolder = new File(rootPath + File.separator + uploadFolderName + File.separator + accountId);
		if(!userFolder.exists())
			userFolder.mkdir();
		
		String imageId = String.valueOf(System.currentTimeMillis());
		String uploadedImageUrl = File.separator + uploadFolderName + File.separator + accountId + File.separator + imageId + "." + getFileExtension(fileName);
		return uploadedImageUrl;
	}
	
	public static String getFileExtension(String fileName){
		String extension = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
		return extension;
	}
}
