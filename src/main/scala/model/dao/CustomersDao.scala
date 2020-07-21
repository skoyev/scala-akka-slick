package model.dao

import model.Customer
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

/**
 * Customer DAO access
 * @author Sergiy Koyev
 */
object CustomersDao extends BaseDao {
  /**
   * Find All Customers
   * @return all customers
   */
  def findAll(): Future[Seq[Customer]] = customersTable.result

  /**
   * Find By Customer by ID
   * @param id
   * @return single Customer object
   */
  def findById(id: Long): Future[Customer] = customersTable.filter(_.id === id).result.head

  /**
   * Create a new Customer records
   * @param user
   * @return customer ID
   */
  def create(user: Customer): Future[Long] = customersTable returning customersTable.map(_.id) += user

  /**
   * Update Customer record.
   *
   * @param id
   * @param newCustomer
   * @return updated Customer record
   */
  def update(id: Long, newCustomer: Customer): Future[Int] = {
    customersTable.filter(_.id === id).map(customer => (customer.name, customer.age, customer.gender))
      .update((newCustomer.name, newCustomer.age, newCustomer.gender))
  }

  /**
   * Delete Customer record by ID
   * @param id
   * @return ID of the record.
   */
  def delete(id: Long): Future[Int] = {
    customersTable.filter(_.id === id).delete
  }
}
