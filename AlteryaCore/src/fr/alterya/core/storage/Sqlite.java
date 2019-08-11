package fr.alterya.core.storage;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

public class Sqlite implements Database {
	
	private String dbUrl;
	
	public Connection connection;
	
	public Sqlite(String dbName, String dbLocation) {
		this.dbUrl = "jdbc:sqlite:" + dbLocation + File.separator + dbName;
	}
	
	@Override
	public void openDataConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(dbUrl);
        } catch (ClassNotFoundException e) {
            Bukkit.getLogger().severe("ClassNotFoundException! " + e.getMessage());
        } catch (SQLException e) {
            Bukkit.getLogger().severe("SQLException! " + e.getMessage());
        }
	}

	@Override
	public void closeDataConnection() {
		Connection connection_ = null;
		try
		{
			connection_ = this.getDataStatement().getConnection();
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}
		this.connection = connection_;
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet select(String query) {
		
		openDataConnection();

		try {
			
			return getDataStatement().executeQuery(query);
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		}

		return null;
		
	}

	@Override
	public void update(String query) {
		
		openDataConnection();
		
		Statement stmt = null;
		
		try {
		
			try {
				
				stmt = getDataStatement();
				
				stmt.executeUpdate(query);
				
			} catch(SQLException e) {
				
				e.printStackTrace();
				
			} finally {
				
				closeDataConnection();
				
				stmt.close();
				
			}
		
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void execute(String query) {

		openDataConnection();
		
		Statement stmt = null;
		
		try {
		
			try {
				
				stmt = getDataStatement();
				
				stmt.execute(query);
				
			} catch(SQLException e) {
				
				e.printStackTrace();
				
			} finally {
				
				closeDataConnection();
				
				stmt.close();
				
			}
		
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}

	@Override
	public boolean hasTable(String tableName) {
		return false;
	}

	@Override
	public Statement getDataStatement() {
		Connection connection_;
		connection_ = this.connection;
		try {
			return connection_.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
