package com.sep.onlinedeliverysystem.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sep.onlinedeliverysystem.domain.dto.BasketItemDTO;
import com.sep.onlinedeliverysystem.domain.entities.BasketItem;
import com.sep.onlinedeliverysystem.mappers.Mapper;
import com.sep.onlinedeliverysystem.services.BasketItemService;

@RestController
public class BasketItemController {
    private BasketItemService basketItemService;
    private Mapper<BasketItem, BasketItemDTO> basketItemMapper;

    public BasketItemController(BasketItemService basketItemService, Mapper<BasketItem, BasketItemDTO> basketItemMapper) {
        this.basketItemMapper = basketItemMapper;
        this.basketItemService = basketItemService;
    }

    @PostMapping(path = "/basketItems")
    public ResponseEntity<BasketItemDTO> save(@RequestBody BasketItemDTO basketItemDTO) {
        BasketItem basketItem = basketItemMapper.mapFrom(basketItemDTO);
        BasketItem savedItem = basketItemService.save(basketItem);
        return new ResponseEntity<>(basketItemMapper.mapTo(savedItem), HttpStatus.CREATED);
    }

    @GetMapping(path = "/basketItems/{id}")
    public ResponseEntity<BasketItemDTO> getBasketItem(@PathVariable("id") Long id) {
        Optional<BasketItem> foundBasketItem = basketItemService.findOne(id);
        return foundBasketItem.map((basketItemEntity -> {
            BasketItemDTO basketItemDTO = basketItemMapper.mapTo(basketItemEntity);
            return new ResponseEntity<>(basketItemDTO, HttpStatus.OK);
        })).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/basketItems/baskets/{basketId}")
    public List<BasketItemDTO> listBasketItemsByBasket(@PathVariable("basketId") Long basketId) {
        List<BasketItem> basketItems = basketItemService.findBasketItemByBasket_Id(basketId);
        return basketItems.stream().map(basketItemMapper::mapTo).collect(Collectors.toList());
    }

    @PutMapping(path = "/basketItems/{id}")
    public ResponseEntity<BasketItemDTO> fullUpdateBasketItem(@PathVariable("id") Long id, @RequestBody BasketItemDTO basketItemDTO) {
        if (!basketItemService.Exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        basketItemDTO.setId(id);
        BasketItem basketItem = basketItemMapper.mapFrom(basketItemDTO);
        BasketItem savedItem = basketItemService.save(basketItem);
        return new ResponseEntity<>(basketItemMapper.mapTo(savedItem), HttpStatus.OK);
    }

    @PatchMapping(path = "/basketItems/{id}")
    public ResponseEntity<BasketItemDTO> partialUpdateBasketItem(@PathVariable("id") Long id, @RequestBody BasketItemDTO basketItemDTO) {
        if (!basketItemService.Exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        basketItemDTO.setId(id);
        BasketItem basketItem = basketItemMapper.mapFrom(basketItemDTO);
        BasketItem savedItem = basketItemService.partialUpdate(basketItem);
        return new ResponseEntity<>(basketItemMapper.mapTo(savedItem), HttpStatus.OK);
    }

    @DeleteMapping(path = "/basketItems/{id}")
    public ResponseEntity deleteBasketItem(@PathVariable("id") Long id) {
        basketItemService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
