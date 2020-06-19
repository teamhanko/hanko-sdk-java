package io.hanko.sdk.json.jackson;

import io.hanko.sdk.json.HankoJsonParser;
import io.hanko.sdk.json.HankoJsonParserFactory;

public class HankoJsonParserFactoryJackson implements HankoJsonParserFactory {
    @Override
    public HankoJsonParser create() {
        return new HankoJsonParserJackson();
    }
}
