package learn.sprofile;

public class HttpPlainRequester implements HttpRequester {
	public String request() {
		return HttpPlainRequester.class.getName();
	}
}
