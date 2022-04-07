package com.example.jwtinstamarket.service;

import com.example.jwtinstamarket.exception.OfferNotFound;
import com.example.jwtinstamarket.exception.UserNotFound;
import com.example.jwtinstamarket.model.binding.OfferBindingModel;
import com.example.jwtinstamarket.model.service.OfferServiceModel;
import com.example.jwtinstamarket.model.view.OfferViewModel;
import org.springframework.data.domain.Page;

public interface OfferService {
    Long saveOffer(OfferServiceModel offer, String email) throws UserNotFound;

    OfferViewModel viewOffer(Long offerId) throws OfferNotFound;

    OfferViewModel editOffer(Long offerId, OfferServiceModel offerServiceModel) throws OfferNotFound;

    Page<OfferViewModel> allOffers(int pageNo, int pageSize, String sortBy);

    boolean isOfferOwner(Long offerId, String email) throws UserNotFound, OfferNotFound;

    boolean deleteOffer(Long offerId) throws OfferNotFound;
}
