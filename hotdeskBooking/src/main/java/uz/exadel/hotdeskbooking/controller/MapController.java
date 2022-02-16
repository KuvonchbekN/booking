package uz.exadel.hotdeskbooking.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.exadel.hotdeskbooking.dto.MapDto;
import uz.exadel.hotdeskbooking.model.Map;
import uz.exadel.hotdeskbooking.response.ApiResponse;
import uz.exadel.hotdeskbooking.service.MapService;

@RestController
@RequestMapping("/map")
@RequiredArgsConstructor
public class MapController {

    @Autowired
    private final MapService mapService;


    @GetMapping
    public ApiResponse getMapList(){
        return mapService.getList();
    }

    @PostMapping
    public ApiResponse addMap(@RequestBody MapDto mapDto){
        return mapService.addMap(mapDto);
    }

    @PutMapping("/{id}")
    public ApiResponse updateMap(@RequestBody Map map, @PathVariable String id){
        return mapService.updateMap(map, id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteMap(@PathVariable String id){
        return mapService.deleteMap(id);
    }
}
