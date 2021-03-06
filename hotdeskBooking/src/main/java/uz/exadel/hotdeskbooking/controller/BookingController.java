package uz.exadel.hotdeskbooking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.exadel.hotdeskbooking.dto.ResponseItem;
import uz.exadel.hotdeskbooking.dto.request.BookingAnyTO;
import uz.exadel.hotdeskbooking.dto.request.BookingCreateTO;
import uz.exadel.hotdeskbooking.service.BookingService;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
@CrossOrigin
public class BookingController {
    private final BookingService bookingService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COMMON_USER','ROLE_MANAGER','ROLE_MAP_EDITOR')")
    @PostMapping
    public ResponseEntity<ResponseItem> add(@RequestBody BookingCreateTO bookingCreateTO) {
        ResponseItem responseItem = bookingService.create(bookingCreateTO);
        return new ResponseEntity<>(responseItem, HttpStatus.valueOf(responseItem.getStatusCode()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COMMON_USER','ROLE_MANAGER','ROLE_MAP_EDITOR')")
    @PostMapping("/any")
    public ResponseEntity<ResponseItem> add(@RequestBody BookingAnyTO bookingAnyTO) {
        ResponseItem responseItem = bookingService.createAny(bookingAnyTO);
        return new ResponseEntity<>(responseItem, HttpStatus.valueOf(responseItem.getStatusCode()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COMMON_USER','ROLE_MANAGER','ROLE_MAP_EDITOR')")
    @PostMapping("/save")
    public ResponseEntity<ResponseItem> save() {
        ResponseItem responseItem = bookingService.save();
        return new ResponseEntity<>(responseItem, HttpStatus.valueOf(responseItem.getStatusCode()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COMMON_USER','ROLE_MANAGER','ROLE_MAP_EDITOR')")
    @PostMapping("/cancel")
    public ResponseEntity<ResponseItem> cancel(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "userId", required = false) String userId) {
        ResponseItem responseItem = bookingService.cancel(id, userId);
        return new ResponseEntity<>(responseItem, HttpStatus.valueOf(responseItem.getStatusCode()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COMMON_USER','ROLE_MANAGER','ROLE_MAP_EDITOR')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseItem> get(@PathVariable("id") String id) {
        ResponseItem responseItem = bookingService.getOne(id);
        return new ResponseEntity<>(responseItem, HttpStatus.valueOf(responseItem.getStatusCode()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COMMON_USER','ROLE_MANAGER','ROLE_MAP_EDITOR')")
    @GetMapping("/current")
    public ResponseEntity<ResponseItem> get() {
        ResponseItem responseItem = bookingService.getCurrent();
        return new ResponseEntity<>(responseItem, HttpStatus.valueOf(responseItem.getStatusCode()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COMMON_USER','ROLE_MANAGER','ROLE_MAP_EDITOR')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseItem> edit(@PathVariable("id") String id, @RequestBody BookingCreateTO bookingCreateTO) {
        ResponseItem responseItem = bookingService.edit(id, bookingCreateTO);
        return new ResponseEntity<>(responseItem, HttpStatus.valueOf(responseItem.getStatusCode()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COMMON_USER','ROLE_MANAGER','ROLE_MAP_EDITOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseItem> delete(@PathVariable("id") String id) {
        ResponseItem responseItem = bookingService.delete(id);
        return new ResponseEntity<>(responseItem, HttpStatus.valueOf(responseItem.getStatusCode()));
    }

}
