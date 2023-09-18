package vn.edu.fpt.horo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import vn.edu.fpt.horo.entity.DisplayMessage;
import vn.edu.fpt.horo.repository.DisplayMessageRepository;
import vn.edu.fpt.horo.service.DisplayMessageService;

/**
 * vn.edu.fpt.accounts.service.impl
 **/

@Service
@Slf4j
@RequiredArgsConstructor
public class DisplayMessageServiceImpl implements DisplayMessageService {

    private final DisplayMessageRepository displayMessageRepository;

    @Override
    public String getDisplayMessage(String code) {
        return getDisplayMessage(code, LocaleContextHolder.getLocale().getLanguage());
    }

    @Override
    public String getDisplayMessage(String code, String language) {
        DisplayMessage displayMessage = displayMessageRepository.findByCodeAndLanguage(code, language)
                .orElse(null);
        return displayMessage == null
                ? null
                : displayMessage.getMessage();
    }
}
