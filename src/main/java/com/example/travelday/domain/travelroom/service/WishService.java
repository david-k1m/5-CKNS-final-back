package com.example.travelday.domain.travelroom.service;

import com.example.travelday.domain.travelroom.dto.request.WishReqDto;
import com.example.travelday.domain.travelroom.dto.response.WishResDto;
import com.example.travelday.domain.travelroom.entity.TravelRoom;
import com.example.travelday.domain.travelroom.entity.Wish;
import com.example.travelday.domain.travelroom.repository.TravelRoomRepository;
import com.example.travelday.domain.travelroom.repository.WishRepository;
import com.example.travelday.global.exception.CustomException;
import com.example.travelday.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;

    private final TravelRoomRepository travelRoomRepository;

    @Transactional(readOnly = true)
    public List<WishResDto> getWishlist(final Long travelRoomId) {
        List<Wish> wishList = wishRepository.findAllByTravelRoomId(travelRoomId);
        List<WishResDto> wishResDtos = new ArrayList<>();

        for (Wish wish : wishList) {
            wishResDtos.add(WishResDto.of(wish));
        }

        return wishResDtos;
    }

    @Transactional
    public void addWish(final Long travelRoomId, WishReqDto wishReqDto) {
        TravelRoom travelRoom = travelRoomRepository.findById(travelRoomId)
                .orElseThrow(() -> new CustomException(ErrorCode.TRAVEL_ROOM_NOT_FOUND));

        Wish wish = Wish.addOf(wishReqDto, travelRoom);
        wishRepository.save(wish);
    }

    @Transactional
    public void deleteWish(final Long wishId) {
        wishRepository.deleteById(wishId);
    }
}
