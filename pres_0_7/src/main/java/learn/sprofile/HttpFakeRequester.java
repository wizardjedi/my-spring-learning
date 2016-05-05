package learn.sprofile;

public class HttpFakeRequester implements HttpRequester {
	public String request() {
		return HttpFakeRequester.class.getName();
	}
}
