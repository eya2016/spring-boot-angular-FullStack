package eya.zaibi.revision.services;

import eya.zaibi.revision.models.Store;
import eya.zaibi.revision.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService{

    @Autowired
    StoreRepository agent;

    @Override
    public Store saveorupdate(Store store) {
        return agent.save(store);
    }

    @Override
    public List<Store> getAll() {
        return agent.findAll();
    }

    @Override
    public void delete(long id) {
        agent.deleteById(id);
    }

    @Override
    public Optional<Store> findById(Long id) {
        return agent.findById(id);
    }
}
