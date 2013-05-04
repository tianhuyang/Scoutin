package com.scoutin.actions.card;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.scoutin.utilities.UploadUtils;

public class UploadImageAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = -4112667837892125762L;
	private HttpServletRequest request;
	private String title;
    private File file;
    private String fileContentType;
    private String fileFileName;
	private int startX;
    private int startY;
    private int width;
    private int height;
	private Map<String,Object> dataMap;
	
	public UploadImageAction(){
		dataMap = new HashMap<String,Object>();
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public String execute() throws Exception{
		boolean isUploaded=false;
		String filename = "test.jpg";
		String path = ServletActionContext.getServletContext().getRealPath(File.separator+"upload"+File.separator+filename);
		try{
			//FileUtils.copyFile(this.file, new File(path));
			System.out.println(UploadUtils.uploadImage(new Integer(1), this.file, this.fileFileName, this.fileContentType, this.startX, this.startY, this.width, this.height));
			isUploaded = true;
		}catch(Exception e){
			isUploaded = false;
			e.printStackTrace();
		}

		dataMap.put("isUploaded", isUploaded);
		dataMap.put("path", path);
		return SUCCESS;
	}
	
	public Map<String,Object> getDataMap() {
		return dataMap;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
    public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
