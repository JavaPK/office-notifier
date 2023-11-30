package com.ttsw.officenotifier.api;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Event {

    private String message;
}
