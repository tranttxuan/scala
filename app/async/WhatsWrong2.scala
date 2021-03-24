package com.particeep.test.async
//NOTE: A Scala Future is used to create a little pocket of concurrency that you use for one-shot needs
// use it when you need to call an algorithm that runs an indeterminate amount of time — such as calling a web service or executing a long-running algorithm — so you therefore want to run it off of the main thread.
import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

case class CEO(id: String, first_name: String, last_name: String)
case class Enterprise(id: String, name: String, ceo_id: String)

object CEODao {
  val ceos = List(
    CEO("1", "Mark", "Zuckerberg"),
    CEO("2", "Sundar", "Pichai")
  )

  def byId(id: String): Future[Option[CEO]] = Future(ceos.find(_.id == id))

}

object EnterpriseDao {
  val enterprises = List(
    Enterprise("1", "Google", "1"),
    Enterprise("2", "Facebook", "2")
  )

  def byId(id:        String): Future[Option[Enterprise]] = Future(enterprises.find(_.id == id))
  def byCEOId(ceo_id: String): Future[Option[Enterprise]] = Future(enterprises.find(_.ceo_id == ceo_id))
}

object WhatsWrong2 {

  //Review this code. What could be done better ? How would you do it ?
  def getCEOAndEnterprise(ceo_id: Option[String]): Future[(Option[CEO], Option[Enterprise])] = {
    for {
      ceo        <- CEODao.byId(ceo_id.get)
      enterprise <- EnterpriseDao.byCEOId(ceo_id.get)
    } yield {
      (ceo, enterprise)
    }
  }
}
