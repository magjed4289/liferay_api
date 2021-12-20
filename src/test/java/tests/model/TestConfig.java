package tests.model;

import lombok.Data;

@Data
public class TestConfig {

	private String uri;
	private String userControllerUrl;
	private String roleControllerUrl;
	private String permissionControllerUrl;
	private String loginPage;
	private String jsnows;
	private int siteId;
	private int port;
	private String email;
	private String password;
}
