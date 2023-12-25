package umc.study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.StoreConverter;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.service.StoreService.StoreCommandService;
import umc.study.validation.anotation.ExistMember;
import umc.study.validation.anotation.ExistStore;
import umc.study.web.dto.MemberRequestDto;
import umc.study.web.dto.StoreRequestDto;
import umc.study.web.dto.StoreResponseDto;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping ("/")
    public ApiResponse<StoreResponseDto.JoinResultDto> join(@RequestBody @Valid StoreRequestDto.JoinDto request){
        Store store = storeCommandService.joinStore(request);
        return ApiResponse.onSuccess(StoreConverter.toJoinResultDTO(store));
    }

    //review 입력
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<StoreResponseDto.ReviewResultDto> createReview(
            @RequestBody @Valid StoreRequestDto.ReviewDto request,
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            @ExistMember @RequestParam(name = "memberId") Long memberId){
        Review review = storeCommandService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toReviewResultDto(review));
    }

    @PostMapping("/{storeId}/missions")
    public ApiResponse<StoreResponseDto.CreateMissionResultDto> createMission(
            @RequestBody @Valid StoreRequestDto.CreateMissionDto request){
        Mission mission = storeCommandService.createMission(request.getStoreId(), request);
        return ApiResponse.onSuccess(StoreConverter.toCreateMissionResultDto(mission));
    }

}
