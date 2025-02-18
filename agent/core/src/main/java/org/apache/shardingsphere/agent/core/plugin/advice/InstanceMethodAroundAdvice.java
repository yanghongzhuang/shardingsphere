/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.agent.core.plugin.advice;

import org.apache.shardingsphere.agent.core.plugin.TargetAdviceObject;
import org.apache.shardingsphere.agent.core.plugin.MethodInvocationResult;

import java.lang.reflect.Method;

/**
 * Weaving the advice around the target method.
 */
public interface InstanceMethodAroundAdvice extends AgentAdvice {
    
    /**
     * Intercept the target method and weave the method before origin method.
     * It will invoke before the origin calling.
     *
     * @param target the target object
     * @param method the target method
     * @param args all method arguments
     * @param result wrapped class of result to detect whether or not to execute the origin method
     */
    default void beforeMethod(final TargetAdviceObject target, final Method method, final Object[] args, final MethodInvocationResult result) {
    }
    
    /**
     * Intercept the target method and weave the method after origin method.
     * It will invoke after the origin calling
     *
     * @param target the target object
     * @param method the target method
     * @param args all method arguments
     * @param result wrapped class of result to detect whether or not to execute the origin method.
     */
    default void afterMethod(final TargetAdviceObject target, final Method method, final Object[] args, final MethodInvocationResult result) {
    }
    
    /**
     * Weaving the method after origin method throwing.
     *
     * @param target the target object
     * @param method the target method
     * @param args all method arguments
     * @param throwable exception from target method
     */
    default void onThrowing(final TargetAdviceObject target, final Method method, final Object[] args, final Throwable throwable) {
    }
}
