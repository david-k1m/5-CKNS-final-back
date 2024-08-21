package com.example.travelday.domain.travelroom.controller;

import com.example.travelday.domain.travelroom.dto.request.WishReqDto;
import com.example.travelday.domain.travelroom.dto.response.WishlistResDto;
import com.example.travelday.domain.travelroom.service.WishService;
import com.example.travelday.global.common.ApiResponseEntity;
import com.example.travelday.global.common.ResponseText;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/rooms/{travelRoomId}/wishlist")
@RestController
public class WishController {

    private final WishService wishService;

    /**
     * 위시리스트 조회 API
     */
    @GetMapping()
    public ResponseEntity<ApiResponseEntity<List<WishlistResDto>>> getWishlist(@PathVariable Long travelRoomId) {
        return ResponseEntity.ok(ApiResponseEntity.of(wishService.getWishlist(travelRoomId)));
    }

    /**
     * 위시 추가 API
     */
    @PostMapping()
    public ResponseEntity<ApiResponseEntity<String>> addWish(@PathVariable Long travelRoomId, @Valid @RequestBody WishReqDto wishReqDto) {
        wishService.addWish(travelRoomId, wishReqDto);
        return ResponseEntity.ok(ApiResponseEntity.of(ResponseText.SUCCESS_ADD_WISH));
    }

    @DeleteMapping("/{wishId}")
    public ResponseEntity<ApiResponseEntity<String>> deleteWish(@PathVariable Long wishId) {
        wishService.deleteWish(wishId);
        return ResponseEntity.ok(ApiResponseEntity.of(ResponseText.SUCCESS_DELETE_WISH));
    }
}
