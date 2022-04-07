package com.example.jwtinstamarket.service.impl;

import com.example.jwtinstamarket.exception.OfferNotFound;
import com.example.jwtinstamarket.exception.UserNotFound;
import com.example.jwtinstamarket.model.entity.OfferEntity;
import com.example.jwtinstamarket.model.entity.UserEntity;
import com.example.jwtinstamarket.model.service.OfferServiceModel;
import com.example.jwtinstamarket.model.view.OfferViewModel;
import com.example.jwtinstamarket.repository.OfferRepository;
import com.example.jwtinstamarket.repository.UserRepository;
import com.example.jwtinstamarket.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long saveOffer(OfferServiceModel offer, String email) throws UserNotFound {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFound());

        OfferEntity offerEntity = new OfferEntity();

        offerEntity
                .setTitle(offer.getTitle())
                .setDescription(offer.getDescription())
                .setPrice(offer.getPrice())
                .setSeller(user);

        OfferEntity savedOffer = offerRepository.save(offerEntity);

        user.getOffers().add(savedOffer);

        userRepository.save(user);

        return savedOffer.getId();
    }

    @Override
    public OfferViewModel viewOffer(Long offerId) throws OfferNotFound {
        OfferEntity offer = offerRepository.findByIdAndDeletedIsFalse(offerId).orElseThrow(()-> new OfferNotFound());

        OfferViewModel mappedOffer = this.asOffer(offer);

        return mappedOffer;
    }

    @Override
    public OfferViewModel editOffer(Long offerId, OfferServiceModel offerServiceModel) throws OfferNotFound {
        OfferEntity offer = offerRepository.findByIdAndDeletedIsFalse(offerId).orElseThrow(() -> new OfferNotFound());

        offer.setTitle(offerServiceModel.getTitle())
                .setDescription(offerServiceModel.getDescription())
                .setPrice(offerServiceModel.getPrice());

        OfferEntity editedOffer = offerRepository.save(offer);

        OfferViewModel mappedOffer = this.asOffer(editedOffer);

        return mappedOffer;
    }

    @Override
    public boolean isOfferOwner(Long offerId, String email) throws UserNotFound, OfferNotFound {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFound());

        OfferEntity offer = offerRepository.findById(offerId).orElseThrow(() -> new OfferNotFound());

        return user.getOffers().contains(offer);
    }

    @Override
    public boolean deleteOffer(Long offerId) throws OfferNotFound {
        OfferEntity offerEntity = offerRepository.findById(offerId).orElseThrow(() -> new OfferNotFound());

        offerEntity.setDeleted(true);

        OfferEntity deleteEntity = offerRepository.save(offerEntity);

        return deleteEntity.getDeleted();
    }

    @Override
    public Page<OfferViewModel> allOffers(int pageNo, int pageSize, String sortBy) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, sortBy));

        Page<OfferViewModel> offers = offerRepository.findAllByDeletedIsFalse(pageable).map(this::asOffer);

        return offers;
    }

    private OfferViewModel asOffer(OfferEntity offerEntity){
        UserEntity seller = offerEntity.getSeller();

        OfferViewModel mappedOffer = modelMapper.map(offerEntity, OfferViewModel.class);

        mappedOffer.setSellerEmail(seller.getEmail())
                .setSellerFullname(String.format("%s %s", seller.getFirstName(), seller.getLastName()));

        return mappedOffer;
    }
}
