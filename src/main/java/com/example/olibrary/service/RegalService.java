package com.example.olibrary.service;

import com.example.olibrary.model.Regal;
import com.example.olibrary.repository.RegalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegalService {
    @Autowired
    private RegalRepository regalRepository;

    public Regal getRegalById(Long id) {
        return regalRepository.findById(id).orElse(null);
    }

    public Regal saveRegal(Regal regal) {
        return regalRepository.save(regal);
    }

    public void deleteRegalById(Long id) {
        regalRepository.deleteById(id);
    }
}
