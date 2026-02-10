package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rva.models.Banka;
import rva.models.KorisnikUsluge;

@Repository
public interface KorisnikUslugeRepository extends JpaRepository<KorisnikUsluge, Integer> {

	List<KorisnikUsluge> findByImeContainingIgnoreCase(String ime);
}
