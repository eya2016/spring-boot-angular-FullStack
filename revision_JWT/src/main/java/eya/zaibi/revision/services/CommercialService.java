package eya.zaibi.revision.services;

import eya.zaibi.revision.models.Commercial;

import java.util.List;
import java.util.Optional;

public interface CommercialService {
    public Commercial saveorupdate(Commercial commercial);
    public List<Commercial> findAll();
    public Optional<Commercial> findById(Long id);
    public void deleteById(long id);
}
