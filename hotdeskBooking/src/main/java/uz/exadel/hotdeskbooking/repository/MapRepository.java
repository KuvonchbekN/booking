package uz.exadel.hotdeskbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.exadel.hotdeskbooking.model.Map;


public interface MapRepository extends JpaRepository<Map, String> {
}
