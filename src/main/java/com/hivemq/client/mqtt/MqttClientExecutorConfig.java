/*
 * Copyright 2018 dc-square and the HiveMQ MQTT Client Project
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
 *
 */

package com.hivemq.client.mqtt;

import com.hivemq.client.annotations.DoNotImplement;
import com.hivemq.client.internal.mqtt.MqttClientExecutorConfigImplBuilder;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.Executor;

/**
 * Configuration for the executors and threads to use by {@link MqttClient MQTT clients}.
 *
 * @author Silvio Giebl
 * @since 1.0
 */
@DoNotImplement
public interface MqttClientExecutorConfig {

    @NotNull Scheduler DEFAULT_APPLICATION_SCHEDULER = Schedulers.computation();

    /**
     * Creates a builder for an executor configuration.
     *
     * @return the created builder for an executor configuration.
     */
    static @NotNull MqttClientExecutorConfigBuilder builder() {
        return new MqttClientExecutorConfigImplBuilder.Default();
    }

    /**
     * @return the optional user defined executor for Netty (network communication framework).
     */
    @NotNull Optional<Executor> getNettyExecutor();

    /**
     * @return the optional user defined amount of threads Netty (network communication framework) will use.
     */
    @NotNull OptionalInt getNettyThreads();

    /**
     * @return the {@link Scheduler} used for executing application specific code, such as callbacks.
     */
    @NotNull Scheduler getApplicationScheduler();

    /**
     * Creates a builder for extending this executor configuration.
     *
     * @return the created builder.
     * @since 1.1
     */
    @NotNull MqttClientExecutorConfigBuilder extend();
}
