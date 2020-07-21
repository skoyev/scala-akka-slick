package api

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import model.Customer
import model.dao.CustomersDao
import spray.json.{DefaultJsonProtocol, RootJsonFormat, _}

import scala.concurrent.ExecutionContext.Implicits.global

trait JsonMapping extends DefaultJsonProtocol {
  implicit val customerFormat: RootJsonFormat[Customer] = jsonFormat4(Customer)
}

/**
 * Customer API Router.
 * @author Sergiy Koyev
 */
trait CustomersApi extends JsonMapping {
  val customersRoute: Route =
    // Get All Customers API
    (path("customers") & get) {
      complete(CustomersDao.findAll().map(_.toJson))
    } ~
      // Get Customer By ID API
      (path("customers" / IntNumber) & get) { id =>
        complete(CustomersDao.findById(id).map(_.toJson))
      } ~
      // Create A New Customer API
      (path("customers") & post) {
        entity(as[Customer]) { customer =>
          complete(CustomersDao.create(customer).map(_.toJson))
        }
      } ~
      // Update Customer API
      (path("customers" / IntNumber) & put) { id =>
        entity(as[Customer]) { customer =>
          complete(CustomersDao.update(id, customer).map(_.toJson))
        }
      } ~
      // Delete Customer By ID
      (path("customers" / IntNumber) & delete) { id =>
        complete(CustomersDao.delete(id).map(_.toJson))
      }
}
