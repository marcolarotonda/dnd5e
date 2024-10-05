package io.github.marcolarotonda.dnd5e.service;

import io.github.marcolarotonda.dnd5e.entity.InitiativeItemEntity;
import io.github.marcolarotonda.dnd5e.repository.InitiativeItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitiativeItemService {

    private final InitiativeItemRepository initiativeItemRepository;

    @Autowired
    public InitiativeItemService(InitiativeItemRepository initiativeItemRepository) {
        this.initiativeItemRepository = initiativeItemRepository;
    }

    @Transactional
    public void saveInitiative(List<InitiativeItemEntity> items) {
        initiativeItemRepository.deleteAll();
        initiativeItemRepository.saveAll(items);
    }

    public List<InitiativeItemEntity> findAll() {
        return initiativeItemRepository.findAll();
    }
}
