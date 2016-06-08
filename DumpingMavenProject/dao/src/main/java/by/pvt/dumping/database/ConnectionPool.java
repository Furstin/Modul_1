package by.pvt.dumping.database;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {

	private static Logger LOGGER = Logger.getLogger(ConnectionPool.class);
	public static final int DEFAULT_POOL_SIZE = 20;
	private static ConnectionPool instance;
	private BlockingQueue<Connection> pool;
	private BlockingQueue<Connection> inUse;
	private static Lock lock = new ReentrantLock();
	private static boolean giveConnection = true;

	private ConnectionPool() {
		init();
	}

	/**
	 * Inits the connections to pool.
	 */
	private void init() {
		pool = new ArrayBlockingQueue<Connection>(DEFAULT_POOL_SIZE);
		inUse = new ArrayBlockingQueue<Connection>(DEFAULT_POOL_SIZE);
		DBConnector dbConnector = new DBConnector();

		for (int i = 0; i <= DEFAULT_POOL_SIZE; i++) {
			Connection connection;
			try {
				connection = dbConnector.getConnection();
				pool.offer(connection);

			} catch (SQLException e) {
				LOGGER.fatal("fatal error", e);
				throw new RuntimeException();
			}

		}

	}

	/**
	 * Gets the single instance of ConnectionPool.
	 * 
	 * @return single instance of ConnectionPool
	 */
	public static ConnectionPool getInstance() {

		if (instance == null) {
			try {
				lock.lock();
				if (instance == null) {
					instance = new ConnectionPool();
				}
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

	/**
	 * Gets the connection.
	 * 
	 * @return the connection
	 */
	public Connection getConnection() {
		Connection conn = null;
		if (giveConnection) {
			try {
				conn = pool.take();
				inUse.add(conn);
			} catch (InterruptedException e) {
				LOGGER.error("interrupted exception in pool.");
			}
		}
		return conn;
	}

	/**
	 * Returns the connection to pool.
	 * 
	 * @param conn
	 * 
	 */
	public void close(Connection conn) {
		inUse.remove(conn);
		pool.offer(conn);
	}

	/**
	 * Cleans up the pool.
	 */
	public void cleanUp() {
		giveConnection = false;
		Iterator<Connection> iterator = pool.iterator();
		while (iterator.hasNext()) {
			try {
				iterator.next().close();
			} catch (SQLException e) {
				LOGGER.error("exception while cleaning pool");
			}
		}
		iterator = inUse.iterator();
		while (iterator.hasNext()) {
			try {
				iterator.next().close();
			} catch (SQLException e) {
				LOGGER.error("exception while cleaning pool");
			}
		}
		pool.clear();
		inUse.clear();
	}
}
