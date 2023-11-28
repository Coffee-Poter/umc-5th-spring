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
        //string을 Region객체로 변경??
        Region region = null;
        region.setId(request.getRegion());
        //이름은 레포에서 찾아서 넣어야해서 불가능하다고 생각함
        //Region을 region으로 받음 -> 다른 방법이 있는지는 모르겠다. -> 서비스에서 수정 가능할 듯?, request받을때는 어떻게??

        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .score(request.getScore())
                .region(region)
                .build();
    }
}
