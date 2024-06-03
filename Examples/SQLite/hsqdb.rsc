import "SQLite"

connection = getConnection("jdbc:hsqldb:file:hsql.db", "", "", "org.hsqldb.jdbcDriver")
statement = connection.createStatement()
statement.executeUpdate("drop table if exists cities")
statement.executeUpdate("CREATE TABLE cities (id IDENTITY, name VARCHAR(32))")
statement.executeUpdate("INSERT INTO cities (name) VALUES('Владивосток')")
statement.executeUpdate("INSERT INTO cities (name) VALUES('Санкт-Петербург')")
statement.executeUpdate("INSERT INTO cities (name) VALUES('Москва')")

rs = statement.executeQuery("SELECT id, name FROM cities")
while(rs.next()) {
  outline("Имя = " + rs.getString("name"))
  outline("ID = " + rs.getInt("id"))
}
statement.execute("SHUTDOWN")