package rva.services;

import java.util.List;

import org.springframework.stereotype.Service;

import rva.models.Banka;
import rva.models.Filijala;

@Service
public interface FilijalaService extends CrudService<Filijala> {

	List<Filijala> getFilijalasByPosedujeSef(boolean posedujeSef);
	
	List<Filijala> findByForeignKey(Banka banka);

}
