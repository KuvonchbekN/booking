package uz.exadel.hotdeskbooking.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uz.exadel.hotdeskbooking.domain.Workplace;
import uz.exadel.hotdeskbooking.dto.WorkplaceCreateDto;
import uz.exadel.hotdeskbooking.dto.WorkplaceResponseDto;
import uz.exadel.hotdeskbooking.dto.WorkplaceUpdateDto;

@Component
public class WorkplaceMapper {
    private final ModelMapper mapper;

    public WorkplaceMapper() {
        this.mapper = new ModelMapper();
    }

    public WorkplaceResponseDto entityToResponseDTO(Workplace workplace) {
        return mapper.map(workplace, WorkplaceResponseDto.class);
    }

    public Workplace createDtoToEntity(WorkplaceCreateDto workplaceCreateDto){
        return mapper.map(workplaceCreateDto, Workplace.class);
    }

    public void updateDtoToEntity(WorkplaceUpdateDto workplaceUpdateDto, Workplace workplace){
       mapper.map(workplaceUpdateDto, workplace);
    }
}