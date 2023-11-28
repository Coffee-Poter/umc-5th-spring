package umc.study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.StoreConverter;
import umc.study.domain.Store;
import umc.study.service.StoreService.StoreCommandService;
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
}
