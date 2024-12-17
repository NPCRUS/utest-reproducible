import utest.*
import scalasql.*
import PostgresDialect.*
import scalasql.core.SqlStr.SqlStringSyntax

object TestSpec extends TestSuite {
  val dataSource = new org.postgresql.ds.PGSimpleDataSource
  dataSource.setURL("jdbc:postgresql://localhost:5436/test?loggerLevel=OFF")
  dataSource.setUser("test")
  dataSource.setPassword("test")

  val client = new scalasql.DbClient.DataSource(
    dataSource,
    new scalasql.Config {}
  )

  val tests = Tests {
    test {
      client.transaction { db =>
        val result = db.runSql[Int](sql"SELECT 1 + 1").head
        assert(result == 2)
      }
    }
  }
}
