package eya.zaibi.revision.services;

import eya.zaibi.revision.models.Commercial;
import eya.zaibi.revision.repository.CommercialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommercialServiceImpl implements CommercialService {

    @Autowired
    CommercialRepository agent;

    @Override
    public Commercial saveorupdate(Commercial commercial) {
        return (Commercial) agent.save(commercial);
    }

    @Override
    public List<Commercial> findAll() {
        return agent.findAll();
    }

    @Override
    public Optional<Commercial> findById(Long id) {
        return agent.findById(id);
    }

    @Override
    public void deleteById(long id) {
        agent.deleteById(id);
    }
}
