package umc.study.service.StoreService;

import umc.study.domain.Store;
import umc.study.validation.anotation.ExistStore;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);

}
