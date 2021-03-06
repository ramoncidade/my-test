package br.com.cidade.business;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.cidade.entity.AddressEntity;

public interface AddressBusiness {
	public Optional<AddressEntity> findOne(AddressEntity address);
	public Page<AddressEntity> findAll(Pageable page);
	public AddressEntity save(AddressEntity address);
	public void delete(AddressEntity address);
	public boolean isValidZipcode(AddressEntity address);
}
