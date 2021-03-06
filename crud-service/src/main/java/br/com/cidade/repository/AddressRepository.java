package br.com.cidade.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cidade.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long>{
	
	public AddressEntity findByZipcode(String zipcode);
	
	public Optional<AddressEntity> findById(AddressEntity address);

}
