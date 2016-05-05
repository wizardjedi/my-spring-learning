package learn.sprofile;

public class ServiceImpl implements Service{
	public HttpRequester request;
	public DAO dao;
	
	public HttpRequester getRequest() {
		return request;
	}

	public void setRequest(HttpRequester request) {
		this.request = request;
	}

	public DAO getDao() {
		return dao;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}
	
	
	public String serve() {
		return request.request();
	}

	public String getData() {
		return dao.getData();
	}

}
