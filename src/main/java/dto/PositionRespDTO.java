package dto;


import lombok.Builder;
import lombok.Getter;

@Getter
public class PositionRespDTO {
    private String position;
    private String lotte;
    private String samsung;
    private String nc;

    @Builder
    public PositionRespDTO(String position, String lotte, String samsung, String nc) {
        this.position = position;
        this.lotte = lotte;
        this.samsung = samsung;
        this.nc = nc;
    }
}
