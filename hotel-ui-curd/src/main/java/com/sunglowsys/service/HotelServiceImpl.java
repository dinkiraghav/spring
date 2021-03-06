package com.sunglowsys.service;

import com.sunglowsys.domain.Hotel;
import com.sunglowsys.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HotelServiceImpl implements HotelService{
    private final Logger log = LoggerFactory.getLogger(HotelServiceImpl.class);
    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }


    @Override
    public Hotel save(Hotel hotel) {
        log.debug("web request save :{}",hotel);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel update(Hotel hotel) {
        log.debug("web request update :{}",hotel);
        return hotelRepository.save(hotel);
    }

    @Override
    public Page<Hotel> findAll(Pageable pageable) {
        log.debug("web req findall: {}",pageable.toString());
        return hotelRepository.findAll(pageable);
    }

    @Override
    public Optional<Hotel> findById(Long id) {
        log.debug("Web req find by id: {}",id);
        return hotelRepository.findById(id);
    }

    @Override
    public List<Hotel> search(String searchText) {
        return hotelRepository.search(searchText);
    }

    @Override
    public void delete(Long id) {
        log.debug("web service request: {}",id);
        hotelRepository.deleteById(id);
    }
}
