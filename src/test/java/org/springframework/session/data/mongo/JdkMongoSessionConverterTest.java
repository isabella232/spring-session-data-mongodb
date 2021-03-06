/*
 * Copyright 2014-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.session.data.mongo;

import static org.assertj.core.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;

/**
 * @author Jakub Kubrynski
 * @author Rob Winch
 * @author Greg Turnquist
 */
public class JdkMongoSessionConverterTest extends AbstractMongoSessionConverterTest {

	Duration inactiveInterval = Duration.ofMinutes(30);
	JdkMongoSessionConverter mongoSessionConverter = new JdkMongoSessionConverter(inactiveInterval);

	@Override
	AbstractMongoSessionConverter getMongoSessionConverter() {
		return this.mongoSessionConverter;
	}

	@Test
	public void constructorNullSerializer() {
		assertThatIllegalArgumentException().isThrownBy(() -> {
			new JdkMongoSessionConverter(null, new DeserializingConverter(), inactiveInterval);
		});
	}

	@Test
	public void constructorNullDeserializer() {
		assertThatIllegalArgumentException().isThrownBy(() -> {
			new JdkMongoSessionConverter(new SerializingConverter(), null, inactiveInterval);
		});
	}
}
