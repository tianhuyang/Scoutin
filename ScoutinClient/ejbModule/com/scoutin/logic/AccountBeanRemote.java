package com.scoutin.logic;

import java.util.Map;

import javax.ejb.Remote;
import com.scoutin.entities.*;

@Remote
public interface AccountBeanRemote {
	
	public Account signup(Map<String,Object> properties);
	public Album createAlbum(Map<String,Object> properties);
}
