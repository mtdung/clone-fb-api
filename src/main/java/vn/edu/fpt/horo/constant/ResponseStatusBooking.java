package vn.edu.fpt.horo.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@RequiredArgsConstructor
@Getter
public enum ResponseStatusBooking {
    CANCEL("CANCEL"),
    PROCESSING("PROCESSING"),
    ACCEPT("ACCEPT");

    private final String status;
}
