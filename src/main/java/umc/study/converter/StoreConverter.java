package umc.study.converter;

import lombok.AllArgsConstructor;
import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.web.dto.StoreRequestDto;
import umc.study.web.dto.StoreResponseDto;

import java.time.LocalDateTime;

@AllArgsConstructor
public class StoreConverter {

    public static StoreResponseDto.JoinResultDto toJoinResultDTO(Store store){
        return StoreResponseDto.JoinResultDto.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Store toStore(StoreRequestDto.JoinDto request){

        Region region = Region.builder()
                .id(request.getRegionId())
                .name(request.getName())
                .build();


        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .score(request.getScore())
                .region(region)
                .build();
    }
}
