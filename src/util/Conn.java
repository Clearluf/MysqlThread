package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conn {
	private String drivername;
	private String url;
	private String username;
	private String password;

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Connection init() throws IOException, ClassNotFoundException, SQLException {
		getConfig();
		Class.forName(getDrivername());
		Connection con=DriverManager.getConnection(getUrl(),getUsername(),getPassword());
		return con;
	}

	public void getConfig() throws IOException {
		Properties cfg = new Properties();
		BufferedReader bufferedReader = new BufferedReader(new FileReader("j2sql.properties"));
		cfg.load(bufferedReader);
		setDrivername(cfg.getProperty("drivername"));
		setUrl(cfg.getProperty("url"));
		setUsername(cfg.getProperty("username"));
		setPassword(cfg.getProperty("password"));
	}
}
