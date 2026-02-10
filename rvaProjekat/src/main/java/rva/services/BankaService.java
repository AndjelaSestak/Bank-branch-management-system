package rva.services;

import java.util.List;

import org.springframework.stereotype.Service;

import rva.models.Banka;

@Service
public interface BankaService extends CrudService<Banka> {

	List<Banka> getBankasByNaziv(String naziv);
}
