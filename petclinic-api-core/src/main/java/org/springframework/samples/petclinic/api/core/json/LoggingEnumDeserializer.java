/*
 * Copyright 2017 Allen Parslow.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.api.core.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LoggingEnumDeserializer extends BeanDeserializerModifier {
    private final Logger logger;

    LoggingEnumDeserializer() {
        this(LoggerFactory.getLogger("UNKNOWN_ENUM_CONSTANT"));
    }

    LoggingEnumDeserializer(Logger logger) {
        this.logger = logger;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JsonDeserializer<Enum> modifyEnumDeserializer(DeserializationConfig config,
                                                         JavaType type,
                                                         BeanDescription description,
                                                         JsonDeserializer<?> deserializer) {
        return new JsonDeserializer<Enum>() {
            @Override
            public Enum deserialize(JsonParser parser, DeserializationContext context) throws IOException {
                Class<? extends Enum> rawClass = (Class<Enum<?>>) type.getRawClass();

                Enum anEnum = null;
                String valueAsString = parser.getValueAsString();

                try {

                    anEnum = Enum.valueOf(rawClass, StringUtils.upperCase(valueAsString));
                } catch (Exception e) {
                    logger.warn(rawClass.getName() + "." + valueAsString);
                }

                return anEnum;
            }
        };
    }
}
