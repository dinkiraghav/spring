package com.sunglowsys.web;

import com.sunglowsys.domain.Hotel;
import com.sunglowsys.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class HotelResource {

    private final Logger log  = LoggerFactory.getLogger(HotelResource.class);
    private  final HotelService hotelService;

    public HotelResource(HotelService hotelService) {
        this.hotelService = hotelService;
    }


    @GetMapping
  public ModelAndView home(){
        log.debug("web request to get Hotels");
        Page<Hotel> page = hotelService.findAll(PageRequest.of(0,20));
        List<Hotel> hotels = page.getContent();
        return new ModelAndView("index", "hotels", hotels);
    }
    @GetMapping("/hotels/create")
    public ModelAndView createHotelForm(@ModelAttribute Hotel hotel){
        log.debug("web request to load create hotel Form ");
        return new ModelAndView("add-hotel","hotel",hotel);
    }
    @PostMapping("/hotels")
    public ModelAndView createHotel(@ModelAttribute Hotel hotel){
        log.debug("web request to create hotel: {}", hotel);
        hotelService.save(hotel);
        return new ModelAndView("redirect:/","hotel",hotel);
    }
    @GetMapping("/hotels/update/{id}")
    public ModelAndView updateHotel(@ModelAttribute Hotel hotel, @PathVariable Long id) {
        log.debug("Web request to update Hotel : {}", id);
        hotel = hotelService.findById(id).get();
        return new ModelAndView("add-hotel", "hotel", hotel);
    }
    @GetMapping("/_search/hotels")
    public ModelAndView searchHotel(String searchText){
        log.debug("web request to search Hotel : {}", searchText);
        List<Hotel> hotels = hotelService.search(searchText);
        return new ModelAndView("index", "hotels", hotels);
    }
    @GetMapping("/hotels/delete/{id}")
    public ModelAndView deleteHotel(@PathVariable Long id){
        log.debug("Web request to delete Hotel: {}", id);
        hotelService.delete(id);
        return  new ModelAndView("redirect:/");
    }
}
