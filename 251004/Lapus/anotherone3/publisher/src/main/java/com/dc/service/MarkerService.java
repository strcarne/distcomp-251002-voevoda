package com.dc.service;

import com.dc.exception.ServiceException;
import com.dc.model.blo.Marker;
import com.dc.repository.MarkerRepository;
import com.dc.model.blo.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkerService{

    @Autowired
    private MarkerRepository repository;


    public List<Marker> getAll() {
        return repository.findAll();
    }

    public Marker getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ServiceException("Маркер не найден", 404));
    }

    public Marker save(Marker Marker) {
        if(!validator(Marker))
        {
            return null;
        }
        return repository.save(Marker);
    }

    public Marker update(Marker Marker) {
        if(!validator(Marker))
        {
            return null;
        }
        return repository.save(Marker);
    }

    public void delete(Long id) {
        repository.findById(id).orElseThrow(() -> new ServiceException("Нечего удалить", 404));
        repository.deleteById(id);
    }

    private boolean validator(Marker Marker){
        if (Marker.getName().length() < 2 || Marker.getName().length() > 32
        ) {
            return false;
        }
        return true;
    }
}
