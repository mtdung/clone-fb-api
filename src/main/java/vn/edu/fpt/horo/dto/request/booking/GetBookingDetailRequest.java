package vn.edu.fpt.horo.dto.request.booking;

import vn.edu.fpt.horo.entity.Advisor;
import vn.edu.fpt.horo.entity._PackageService;

import java.io.Serializable;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
public class GetBookingDetailRequest implements Serializable {

    private static final long serialVersionUID = 8745190972362309054L;
    private String promotionCode;
    private Advisor advisor;
    private _PackageService packageService;
    private String description;


}
