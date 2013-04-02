package test;

import javax.ejb.Remote;

@Remote
public interface TestRemote {
String getResult();
}
