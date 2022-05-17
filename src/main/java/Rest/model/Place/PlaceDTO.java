package Rest.model.Place;

import Rest.model.Table.TableDTO;

import java.util.List;

public class PlaceDTO {
    public PlaceDTO() {
    }

    public PlaceDTO(String id, String placeName) {
        this.id = id;
        this.placeName = placeName;
    }

    public PlaceDTO(String id, String placeName, List<TableDTO> tableDTOS) {
        this.id = id;
        this.placeName = placeName;
        this.tableDTOS = tableDTOS;
    }

    public String id;
    public String placeName;

    public List<TableDTO> tableDTOS;


}
