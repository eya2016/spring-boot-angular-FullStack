package eya.zaibi.revision.services;

import eya.zaibi.revision.models.Store;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    public Store saveorupdate(Store store);
    public List<Store> getAll();
    public void delete(long id);
    public Optional<Store> findById(Long id);


}
