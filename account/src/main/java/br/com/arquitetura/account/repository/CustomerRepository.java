package br.com.arquitetura.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.arquitetura.account.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	@Query(value="SELECT c FROM Customer c WHERE c.architect.user.uid = :uidUserArchitect")
	List<Customer> getCustomersByUidUserArchitect(@Param("uidUserArchitect") Long uidUserArchitect);

}
