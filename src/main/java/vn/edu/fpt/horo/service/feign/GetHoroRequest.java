package vn.edu.fpt.horo.service.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetHoroRequest {

    @JsonProperty("ho_ten")
    private String ho_ten;
    @JsonProperty("gioi_tinh")
    private String gioi_tinh;
    @JsonProperty("loai_lich")
    private String loai_lich;
    @JsonProperty("nam_duong")
    private String nam_duong;
    @JsonProperty("thang_duong")
    private String thang_duong;
    @JsonProperty("ngay_duong")
    private String ngay_duong;
    @JsonProperty("gio_duong")
    private String gio_duong;
    @JsonProperty("phut_duong")
    private String phut_duong;
    @JsonProperty("nam_xem")
    private String nam_xem;
    @JsonProperty("anh_mau")
    private String anh_mau;
    @JsonProperty("luutru")
    private String luutru;
    @JsonProperty("canh_bao")
    private String canh_bao;
}
