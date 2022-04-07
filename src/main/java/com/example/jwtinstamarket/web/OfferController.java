package com.example.jwtinstamarket.web;

import com.example.jwtinstamarket.exception.OfferNotFound;
import com.example.jwtinstamarket.exception.UserNotFound;
import com.example.jwtinstamarket.model.binding.OfferBindingModel;
import com.example.jwtinstamarket.model.service.OfferServiceModel;
import com.example.jwtinstamarket.model.view.OfferViewModel;
import com.example.jwtinstamarket.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/api")
public class OfferController {
    private final OfferService offerService;
    private final ModelMapper modelMapper;

    public OfferController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/offers/all")
    public ResponseEntity<Page<OfferViewModel>> allOffers(
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "3") Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "createdAt") String sortBy)
    {
        Page<OfferViewModel> offers = offerService.allOffers(pageNo, pageSize, sortBy);

        return ResponseEntity.ok(offers);
    }

    @PostMapping("/protected/offers/add")
    public ResponseEntity<String> addOffer(@RequestBody @Valid OfferBindingModel offerBindingModel, Principal principal) throws UserNotFound {
        OfferServiceModel offerServiceModel = modelMapper.map(offerBindingModel, OfferServiceModel.class);

        Long savedOfferId = offerService.saveOffer(offerServiceModel, principal.getName());

        URI location = URI.create(String.format("/api/offers/view/%d", savedOfferId));

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/offers/view/{offerId}")
    public ResponseEntity<OfferViewModel> viewOffer(@PathVariable Long offerId) throws OfferNotFound {
        OfferViewModel offer = offerService.viewOffer(offerId);

        return ResponseEntity.ok(offer);
    }

    @PreAuthorize("@offerServiceImpl.isOfferOwner(#offerId, authentication.name)")
    @PutMapping("/protected/offers/edit/{offerId}")
    public ResponseEntity<OfferViewModel> editOffer(@PathVariable Long offerId, @RequestBody @Valid OfferBindingModel offerBindingModel) throws OfferNotFound {
        OfferServiceModel offerServiceModel = modelMapper.map(offerBindingModel, OfferServiceModel.class);

        OfferViewModel editedOffer = offerService.editOffer(offerId, offerServiceModel);

        return ResponseEntity.ok(editedOffer);
    }

    @PreAuthorize("@offerServiceImpl.isOfferOwner(#offerId, authentication.name)")
    @DeleteMapping("/protected/offers/delete/{offerId}")
    public ResponseEntity<?> deleteOffer(@PathVariable Long offerId) throws OfferNotFound {
        boolean isDeleted = offerService.deleteOffer(offerId);

        if(isDeleted) {
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
}
