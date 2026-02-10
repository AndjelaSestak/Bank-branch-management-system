package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rva.models.Banka;

@Repository
public interface BankaRepository extends JpaRepository<Banka, Integer> {

	List<Banka> findByNazivContainingIgnoreCase(String naziv);
}
