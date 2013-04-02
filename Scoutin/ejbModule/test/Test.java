package test;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Test
 */
@Stateless
@LocalBean
public class Test implements TestRemote {

    /**
     * Default constructor. 
     */
    public Test() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String getResult() {
		// TODO Auto-generated method stub
		return "Hello World!";
	}

}
